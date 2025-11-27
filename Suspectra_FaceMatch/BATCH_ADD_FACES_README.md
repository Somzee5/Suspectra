# Batch Add Faces to AWS Rekognition

## Overview
The `BatchAddFaces.java` script automatically adds all images from the `colorimages` folder to your AWS Rekognition collection. This eliminates the need to manually add images one by one.

## What It Does
1. **Scans** the `colorimages` folder for image files (.jpg, .jpeg, .png)
2. **Uploads** each image to S3 bucket (`suspectra-facematch-somzee5/faces/`)
3. **Indexes** faces into Rekognition collection (`suspectra_collection`)
4. **Reports** progress and results

## Prerequisites
- AWS credentials configured (via `aws configure` or environment variables)
- AWS permissions:
  - `AmazonS3FullAccess` - to upload images
  - `AmazonRekognitionFullAccess` - to index faces
- Rekognition collection `suspectra_collection` already created

## Folder Location
Images should be placed in:
```
Suspectra_FaceMatch/src/main/java/com/mycompany/suspectra_facematch/colorimages/
```

Current images: 001.jpg, 002.jpg, ..., 251.jpg (251 images total)

## How to Run

### Option 1: Using Maven (Recommended)
```bash
cd Suspectra_FaceMatch
mvn exec:java -Dexec.mainClass="com.mycompany.suspectra_facematch.BatchAddFaces"
```

### Option 2: Using Maven Execution Profile
```bash
cd Suspectra_FaceMatch
mvn exec:java@batch-add-faces
```

### Option 3: Using Different Folder Path
You can specify a custom folder path as an argument:
```bash
mvn exec:java -Dexec.mainClass="com.mycompany.suspectra_facematch.BatchAddFaces" -Dexec.args="path/to/your/images"
```

## What Happens

1. **Scanning**: Script finds all image files in the folder
2. **Upload**: Each image is uploaded to S3 at `s3://suspectra-facematch-somzee5/faces/{filename}`
3. **Indexing**: Faces are detected and indexed into the Rekognition collection
4. **External Image ID**: The filename (e.g., "001.jpg") is used as the External Image ID

## Example Output

```
========================================
  BATCH FACE ADDITION TO AWS REKOGNITION
========================================

✓ AWS credentials found
Folder: D:\Suspectra\Suspectra_FaceMatch\src\main\java\com\mycompany\suspectra_facematch\colorimages
Found 251 image file(s) to process

Starting batch processing...
========================================

[1/251] Processing: 001.jpg
  ✓ Uploaded to S3: s3://suspectra-facematch-somzee5/faces/001.jpg
    Face ID: abc123...
  ✓ Face indexed in Rekognition collection

[2/251] Processing: 002.jpg
  ✓ Uploaded to S3: s3://suspectra-facematch-somzee5/faces/002.jpg
    Face ID: def456...
  ✓ Face indexed in Rekognition collection

...

========================================
  BATCH PROCESSING SUMMARY
========================================
Total images:        251
Processed:           251
Successful uploads:  250
Faces indexed:       248
Failed:              1
Time taken:          45 seconds

✓ Successfully added 248 image(s) to AWS Rekognition collection: suspectra_collection
  These images are now searchable when you upload a sketch!
========================================
```

## Error Handling

- **No faces detected**: Image is uploaded to S3 but not indexed (warning shown)
- **Upload fails**: Image is skipped, error logged, processing continues
- **AWS errors**: Detailed error messages with suggestions for fixes
- **Access Denied**: Check IAM permissions for S3 and Rekognition

## After Running

Once the batch processing completes:

1. **All indexed images** are now searchable in your Rekognition collection
2. **Upload a sketch** using the face match application
3. **Click "Find Match"** - it will compare against all images in the collection
4. **Matched images** will be displayed with similarity scores

## Tips

- **First time**: Run the script to add all 251 images (may take 5-10 minutes)
- **Adding new images**: Just drop new images in the `colorimages` folder and run again
- **Duplicate handling**: The script will try to index all images. If an image already exists in S3, it will be overwritten.
- **Rate limiting**: Script includes small delays between requests to avoid AWS rate limits

## Troubleshooting

### "AWS credentials not found"
Run: `aws configure` and enter your AWS credentials

### "Access Denied" errors
Check IAM user permissions:
- AmazonS3FullAccess
- AmazonRekognitionFullAccess

### "Collection not found"
Create the collection first:
```bash
mvn exec:java -Dexec.mainClass="com.mycompany.suspectra_facematch.collection_create"
```

### Images not showing in search results
- Wait a few seconds after indexing (propagation delay)
- Check that faces were actually detected (look for "Face ID" in output)
- Verify the collection name matches: `suspectra_collection`

## Notes

- **S3 Storage**: Images are stored in S3 under `faces/` prefix
- **External Image ID**: Uses the filename (e.g., "001.jpg") as the External Image ID
- **Face Detection**: Only images with detectable faces will be indexed
- **Processing Time**: ~2-3 seconds per image (251 images ≈ 8-12 minutes total)

