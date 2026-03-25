/*
 * AccuracyAnalyzer.java
 * 
 * Analyzes the accuracy of AWS Rekognition face matching for the Suspectra system.
 * Tests different similarity thresholds and generates performance metrics.
 * 
 * Key Metrics:
 * - True Positive Rate (TPR) / Recall / Sensitivity
 * - False Positive Rate (FPR)
 * - Precision
 * - F1 Score
 * - Accuracy
 * 
 * Usage:
 *   Run this to test your indexed faces and generate accuracy report.
 */
package com.mycompany.suspectra_facematch;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class AccuracyAnalyzer {
    
    private static final String COLLECTION_ID = "suspectra_collection";
    private static final String BUCKET_NAME = "suspectra-facematch-somzee5";
    private static final String REGION = "us-east-1";
    
    // Thresholds to test
    private static final float[] THRESHOLDS = {50f, 60f, 70f, 75f, 80f, 85f, 90f, 95f};
    
    public static void main(String[] args) throws IOException {
        System.out.println("========================================");
        System.out.println("  SUSPECTRA ACCURACY ANALYSIS");
        System.out.println("========================================");
        System.out.println();
        
        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard()
                .withRegion(REGION)
                .build();
        
        // Get collection statistics
        printCollectionStats(rekognitionClient);
        
        // Test different thresholds
        System.out.println("\n========================================");
        System.out.println("  TESTING SIMILARITY THRESHOLDS");
        System.out.println("========================================\n");
        
        List<ThresholdResult> results = new ArrayList<>();
        
        for (float threshold : THRESHOLDS) {
            ThresholdResult result = testThreshold(rekognitionClient, threshold);
            results.add(result);
            printThresholdResult(result);
        }
        
        // Generate report
        generateReport(results);
        generateCSVReport(results);
        
        System.out.println("\n========================================");
        System.out.println("  ANALYSIS COMPLETE");
        System.out.println("========================================");
        System.out.println("\nReports generated:");
        System.out.println("  - accuracy_report.txt (detailed report)");
        System.out.println("  - accuracy_data.csv (for graphing)");
        System.out.println("\nUse Excel/Python to create graphs from accuracy_data.csv");
    }
    
    private static void printCollectionStats(AmazonRekognition client) {
        try {
            DescribeCollectionRequest request = new DescribeCollectionRequest()
                    .withCollectionId(COLLECTION_ID);
            DescribeCollectionResult result = client.describeCollection(request);
            
            System.out.println("Collection: " + COLLECTION_ID);
            System.out.println("Face Count: " + result.getFaceCount());
            System.out.println("Created: " + new Date(result.getCreationTimestamp().getTime()));
            System.out.println("Model Version: " + result.getFaceModelVersion());
        } catch (Exception e) {
            System.err.println("Error getting collection stats: " + e.getMessage());
        }
    }
    
    private static ThresholdResult testThreshold(AmazonRekognition client, float threshold) {
        ThresholdResult result = new ThresholdResult(threshold);
        
        try {
            // List all faces in collection
            ListFacesRequest listRequest = new ListFacesRequest()
                    .withCollectionId(COLLECTION_ID)
                    .withMaxResults(1000);
            ListFacesResult listResult = client.listFaces(listRequest);
            List<Face> faces = listResult.getFaces();
            
            result.totalFaces = faces.size();
            
            // Sample testing: test first 50 faces or all if less
            int testCount = Math.min(50, faces.size());
            
            for (int i = 0; i < testCount; i++) {
                Face queryFace = faces.get(i);
                String queryId = queryFace.getExternalImageId();
                
                // Search for this face
                SearchFacesRequest searchRequest = new SearchFacesRequest()
                        .withCollectionId(COLLECTION_ID)
                        .withFaceId(queryFace.getFaceId())
                        .withFaceMatchThreshold(threshold)
                        .withMaxFaces(5);
                
                SearchFacesResult searchResult = client.searchFaces(searchRequest);
                List<FaceMatch> matches = searchResult.getFaceMatches();
                
                // Check if top match is correct (should match itself)
                if (!matches.isEmpty()) {
                    FaceMatch topMatch = matches.get(0);
                    String matchedId = topMatch.getFace().getExternalImageId();
                    
                    if (queryId.equals(matchedId)) {
                        result.truePositives++;
                    } else {
                        result.falsePositives++;
                    }
                } else {
                    // No match found (false negative)
                    result.falseNegatives++;
                }
            }
            
            result.tested = testCount;
            result.calculateMetrics();
            
        } catch (Exception e) {
            System.err.println("Error testing threshold " + threshold + ": " + e.getMessage());
        }
        
        return result;
    }
    
    private static void printThresholdResult(ThresholdResult result) {
        System.out.println(String.format("Threshold: %.0f%%", result.threshold));
        System.out.println(String.format("  Tested: %d faces", result.tested));
        System.out.println(String.format("  True Positives:  %d", result.truePositives));
        System.out.println(String.format("  False Positives: %d", result.falsePositives));
        System.out.println(String.format("  False Negatives: %d", result.falseNegatives));
        System.out.println(String.format("  Accuracy:   %.2f%%", result.accuracy * 100));
        System.out.println(String.format("  Precision:  %.2f%%", result.precision * 100));
        System.out.println(String.format("  Recall:     %.2f%%", result.recall * 100));
        System.out.println(String.format("  F1 Score:   %.2f%%", result.f1Score * 100));
        System.out.println();
    }
    
    private static void generateReport(List<ThresholdResult> results) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter("accuracy_report.txt"));
        
        writer.println("========================================");
        writer.println("  SUSPECTRA ACCURACY ANALYSIS REPORT");
        writer.println("========================================");
        writer.println();
        writer.println("Generated: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        writer.println();
        writer.println("AWS Rekognition Collection: " + COLLECTION_ID);
        writer.println("Region: " + REGION);
        writer.println();
        
        writer.println("========================================");
        writer.println("  PERFORMANCE SUMMARY");
        writer.println("========================================");
        writer.println();
        
        // Find best threshold
        ThresholdResult best = results.stream()
                .max(Comparator.comparingDouble(r -> r.f1Score))
                .orElse(null);
        
        if (best != null) {
            writer.println("RECOMMENDED THRESHOLD: " + String.format("%.0f%%", best.threshold));
            writer.println("  This threshold provides the best balance between precision and recall.");
            writer.println();
            writer.println("  Performance at recommended threshold:");
            writer.println("    Accuracy:  " + String.format("%.2f%%", best.accuracy * 100));
            writer.println("    Precision: " + String.format("%.2f%%", best.precision * 100));
            writer.println("    Recall:    " + String.format("%.2f%%", best.recall * 100));
            writer.println("    F1 Score:  " + String.format("%.2f%%", best.f1Score * 100));
            writer.println();
        }
        
        writer.println("========================================");
        writer.println("  DETAILED RESULTS");
        writer.println("========================================");
        writer.println();
        
        writer.printf("%-12s %-12s %-12s %-12s %-12s %-12s%n", 
                "Threshold", "Accuracy", "Precision", "Recall", "F1 Score", "Tested");
        writer.println("------------------------------------------------------------------------");
        
        for (ThresholdResult r : results) {
            writer.printf("%-12s %-12s %-12s %-12s %-12s %-12d%n",
                    String.format("%.0f%%", r.threshold),
                    String.format("%.2f%%", r.accuracy * 100),
                    String.format("%.2f%%", r.precision * 100),
                    String.format("%.2f%%", r.recall * 100),
                    String.format("%.2f%%", r.f1Score * 100),
                    r.tested);
        }
        
        writer.println();
        writer.println("========================================");
        writer.println("  METRIC DEFINITIONS");
        writer.println("========================================");
        writer.println();
        writer.println("ACCURACY: Overall correctness of predictions");
        writer.println("  Formula: (TP + TN) / (TP + TN + FP + FN)");
        writer.println();
        writer.println("PRECISION: When model predicts a match, how often is it correct?");
        writer.println("  Formula: TP / (TP + FP)");
        writer.println("  High precision = fewer false alarms");
        writer.println();
        writer.println("RECALL (Sensitivity): Of all actual matches, how many did we find?");
        writer.println("  Formula: TP / (TP + FN)");
        writer.println("  High recall = fewer missed matches");
        writer.println();
        writer.println("F1 SCORE: Harmonic mean of precision and recall");
        writer.println("  Formula: 2 * (Precision * Recall) / (Precision + Recall)");
        writer.println("  Balances precision and recall");
        writer.println();
        writer.println("TP = True Positives (correct match)");
        writer.println("FP = False Positives (incorrect match)");
        writer.println("FN = False Negatives (missed match)");
        writer.println("TN = True Negatives (correct rejection)");
        
        writer.close();
        System.out.println("Report saved to accuracy_report.txt");
    }
    
    private static void generateCSVReport(List<ThresholdResult> results) throws IOException {
        PrintWriter csv = new PrintWriter(new FileWriter("accuracy_data.csv"));
        
        csv.println("Threshold,Accuracy,Precision,Recall,F1_Score,True_Positives,False_Positives,False_Negatives,Tested");
        
        for (ThresholdResult r : results) {
            csv.printf("%.0f,%.4f,%.4f,%.4f,%.4f,%d,%d,%d,%d%n",
                    r.threshold,
                    r.accuracy * 100,
                    r.precision * 100,
                    r.recall * 100,
                    r.f1Score * 100,
                    r.truePositives,
                    r.falsePositives,
                    r.falseNegatives,
                    r.tested);
        }
        
        csv.close();
        System.out.println("CSV data saved to accuracy_data.csv");
    }
    
    static class ThresholdResult {
        float threshold;
        int totalFaces;
        int tested;
        int truePositives = 0;
        int falsePositives = 0;
        int falseNegatives = 0;
        int trueNegatives = 0;
        
        double accuracy = 0;
        double precision = 0;
        double recall = 0;
        double f1Score = 0;
        
        ThresholdResult(float threshold) {
            this.threshold = threshold;
        }
        
        void calculateMetrics() {
            int total = truePositives + falsePositives + falseNegatives + trueNegatives;
            
            if (total > 0) {
                accuracy = (double)(truePositives + trueNegatives) / total;
            }
            
            if (truePositives + falsePositives > 0) {
                precision = (double)truePositives / (truePositives + falsePositives);
            }
            
            if (truePositives + falseNegatives > 0) {
                recall = (double)truePositives / (truePositives + falseNegatives);
            }
            
            if (precision + recall > 0) {
                f1Score = 2 * (precision * recall) / (precision + recall);
            }
        }
    }
}
