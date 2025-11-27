/*
 * BatchAddFaces.java
 * 
 * This script automatically adds all images from a local folder to AWS Rekognition collection.
 * 
 * Features:
 * - Scans a folder for image files (.jpg, .jpeg, .png)
 * - Uploads each image to S3 bucket
 * - Indexes faces into Rekognition collection
 * - Shows progress and handles errors gracefully
 * - Skips images that already exist in collection (optional)
 * 
 * Usage:
 *   Run this script once to add all images from colorimages folder to AWS Rekognition.
 *   After running, all images will be searchable when you upload a sketch.
 */
package com.mycompany.suspectra_facematch;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.FaceRecord;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.IndexFacesRequest;
import com.amazonaws.services.rekognition.model.IndexFacesResult;
import com.amazonaws.services.rekognition.model.S3Object;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.ArrayList;

public class BatchAddFaces {
    
    // AWS Configuration
    public static final String COLLECTION_ID = "suspectra_collection";
    public static final String BUCKET_NAME = "suspectra-facematch-somzee5";
    public static final String S3_FACES_PREFIX = "faces/"; // Images will be stored in s3://bucket/faces/
    public static final String REGION = "us-east-1";
    
    // Local folder path (default). Can be overridden by passing folder path as first arg.
    public static final String LOCAL_FOLDER_PATH = "src/main/java/com/mycompany/suspectra_facematch/faces";
    
    // Statistics
    private static int totalImages = 0;
    private static int processedImages = 0;
    private static int successfulUploads = 0;
    private static int successfulIndexed = 0;
    private static int failedImages = 0;
    private static List<String> failedFiles = new ArrayList<>();
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  BATCH FACE ADDITION TO AWS REKOGNITION");
        System.out.println("========================================");
        System.out.println();
        
        // Check AWS credentials
        try {
            DefaultAWSCredentialsProviderChain.getInstance().getCredentials();
            System.out.println("✓ AWS credentials found");
        } catch (Exception e) {
            System.err.println("✗ ERROR: AWS credentials not found!");
            System.err.println("   Please configure AWS credentials using:");
            System.err.println("   1. AWS CLI: aws configure");
            System.err.println("   2. Or set AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY environment variables");
            System.exit(1);
        }
        
        // Get folder path (can be overridden via command line argument)
        String folderPath = LOCAL_FOLDER_PATH;
        if (args.length > 0) {
            folderPath = args[0];
        }
        
        // Resolve absolute path
        File folder = new File(folderPath);
        if (!folder.isAbsolute()) {
            // Try relative to project root (when run via Maven from project root)
            String projectRoot = System.getProperty("user.dir");
            folder = new File(projectRoot, folderPath);
            
            // If still not found, try relative to class location
            if (!folder.exists()) {
                try {
                    String classPath = BatchAddFaces.class.getProtectionDomain()
                            .getCodeSource().getLocation().getPath();
                    // Navigate from target/classes to project root
                    File classDir = new File(classPath);
                    if (classDir.getAbsolutePath().contains("target")) {
                        File projectDir = classDir.getParentFile().getParentFile();
                        folder = new File(projectDir, folderPath);
                    }
                } catch (Exception e) {
                    // Ignore, use project root path
                }
            }
        }
        
        System.out.println("Folder: " + folder.getAbsolutePath());
        
        if (!folder.exists() || !folder.isDirectory()) {
            System.err.println("✗ ERROR: Folder does not exist: " + folder.getAbsolutePath());
            System.err.println("   Current working directory: " + System.getProperty("user.dir"));
            System.err.println("   Please check the folder path and try again.");
            System.exit(1);
        }
        
        // Find all image files
        File[] imageFiles = findImageFiles(folder);
        
        if (imageFiles.length == 0) {
            System.out.println("⚠ No image files found in folder: " + folder.getAbsolutePath());
            System.out.println("  Supported formats: .jpg, .jpeg, .png");
            System.exit(0);
        }
        
        totalImages = imageFiles.length;
        System.out.println("Found " + totalImages + " image file(s) to process");
        System.out.println();
        System.out.println("Starting batch processing...");
        System.out.println("========================================");
        System.out.println();
        
        // Initialize AWS clients
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(REGION)
                .build();
        
        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard()
                .withRegion(REGION)
                .build();
        
        // Process each image
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < imageFiles.length; i++) {
            File imageFile = imageFiles[i];
            processedImages = i + 1;
            
            System.out.println(String.format("[%d/%d] Processing: %s", 
                    processedImages, totalImages, imageFile.getName()));
            
            try {
                // Step 1: Upload to S3
                String s3Key = S3_FACES_PREFIX + imageFile.getName();
                // Use the original filename as ExternalImageId (no path). Rekognition does not allow '/'
                String fileName = imageFile.getName();
                String externalId = fileName; // keep original filename so S3 key mapping is straightforward
                boolean uploaded = uploadToS3(s3Client, imageFile, s3Key);
                
                if (!uploaded) {
                    failedImages++;
                    failedFiles.add(imageFile.getName() + " (S3 upload failed)");
                    System.out.println("  ✗ Failed to upload to S3");
                    continue;
                }
                
                successfulUploads++;
                System.out.println("  ✓ Uploaded to S3: s3://" + BUCKET_NAME + "/" + s3Key);
                
                // Step 2: Index face in Rekognition collection
                // Use the full S3 key as ExternalImageId so retrieval works correctly
                boolean indexed = indexFaceInCollection(rekognitionClient, s3Key, externalId);
                
                if (!indexed) {
                    System.out.println("  ⚠ Uploaded but failed to index face (no face detected or error)");
                } else {
                    successfulIndexed++;
                    System.out.println("  ✓ Face indexed in Rekognition collection");
                }
                
            } catch (Exception e) {
                failedImages++;
                failedFiles.add(imageFile.getName() + " (" + e.getMessage() + ")");
                System.err.println("  ✗ ERROR: " + e.getMessage());
                e.printStackTrace();
            }
            
            System.out.println();
            
            // Add small delay to avoid rate limiting
            if (processedImages < totalImages) {
                try {
                    Thread.sleep(100); // 100ms delay between requests
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime) / 1000; // seconds
        
        // Print summary
        printSummary(duration);
    }
    
    /**
     * Find all image files in the folder
     */
    private static File[] findImageFiles(File folder) {
        FileFilter imageFilter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (!file.isFile()) {
                    return false;
                }
                String name = file.getName().toLowerCase();
                return name.endsWith(".jpg") || 
                       name.endsWith(".jpeg") || 
                       name.endsWith(".png");
            }
        };
        
        return folder.listFiles(imageFilter);
    }
    
    /**
     * Upload image file to S3 bucket
     */
    private static boolean uploadToS3(AmazonS3 s3Client, File imageFile, String s3Key) {
        try {
            // Check if file exists
            if (!imageFile.exists()) {
                System.err.println("    ERROR: File does not exist: " + imageFile.getAbsolutePath());
                return false;
            }
            
            // Upload to S3
            PutObjectRequest putRequest = new PutObjectRequest(BUCKET_NAME, s3Key, imageFile);
            
            // Set content type based on file extension
            String contentType = "image/jpeg";
            String fileName = imageFile.getName().toLowerCase();
            if (fileName.endsWith(".png")) {
                contentType = "image/png";
            }
            
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(contentType);
            putRequest.setMetadata(metadata);
            
            s3Client.putObject(putRequest);
            return true;
            
        } catch (AmazonServiceException e) {
            System.err.println("    AWS Service Error: " + e.getErrorMessage());
            if (e.getErrorCode().equals("AccessDenied")) {
                System.err.println("    Access Denied - Check IAM permissions for S3 write access");
            }
            return false;
        } catch (SdkClientException e) {
            System.err.println("    AWS Client Error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("    Unexpected error: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Index face in Rekognition collection
     */
    private static boolean indexFaceInCollection(AmazonRekognition rekognitionClient, 
                                                  String s3Key, 
                                                  String externalImageId) {
        try {
            // Create image object from S3
            Image image = new Image()
                    .withS3Object(new S3Object()
                            .withBucket(BUCKET_NAME)
                            .withName(s3Key));
            
            // Create index faces request
            IndexFacesRequest indexFacesRequest = new IndexFacesRequest()
                    .withImage(image)
                    .withCollectionId(COLLECTION_ID)
                    .withExternalImageId(externalImageId) // Use filename as external ID
                    .withDetectionAttributes("DEFAULT");
            
            // Index faces
            IndexFacesResult indexFacesResult = rekognitionClient.indexFaces(indexFacesRequest);
            
            // Check if faces were detected
            List<FaceRecord> faceRecords = indexFacesResult.getFaceRecords();
            if (faceRecords.isEmpty()) {
                System.out.println("    ⚠ No faces detected in image");
                return false;
            }
            
            // Log face information
            for (FaceRecord faceRecord : faceRecords) {
                System.out.println("    Face ID: " + faceRecord.getFace().getFaceId());
            }
            
            return true;
            
        } catch (AmazonServiceException e) {
            System.err.println("    AWS Service Error: " + e.getErrorMessage());
            if (e.getErrorCode().equals("AccessDenied")) {
                System.err.println("    Access Denied - Check IAM permissions for Rekognition access");
            }
            return false;
        } catch (SdkClientException e) {
            System.err.println("    AWS Client Error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("    Unexpected error: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Print summary of batch processing
     */
    private static void printSummary(long durationSeconds) {
        System.out.println();
        System.out.println("========================================");
        System.out.println("  BATCH PROCESSING SUMMARY");
        System.out.println("========================================");
        System.out.println("Total images:        " + totalImages);
        System.out.println("Processed:           " + processedImages);
        System.out.println("Successful uploads:  " + successfulUploads);
        System.out.println("Faces indexed:       " + successfulIndexed);
        System.out.println("Failed:              " + failedImages);
        System.out.println("Time taken:          " + durationSeconds + " seconds");
        System.out.println();
        
        if (failedImages > 0) {
            System.out.println("Failed files:");
            for (String failedFile : failedFiles) {
                System.out.println("  - " + failedFile);
            }
            System.out.println();
        }
        
        if (successfulIndexed > 0) {
            System.out.println("✓ Successfully added " + successfulIndexed + 
                             " image(s) to AWS Rekognition collection: " + COLLECTION_ID);
            System.out.println("  These images are now searchable when you upload a sketch!");
        } else {
            System.out.println("⚠ No faces were successfully indexed.");
            System.out.println("  Check error messages above for details.");
        }
        
        System.out.println("========================================");
    }
}

