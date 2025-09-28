/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.suspectra_facematch;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.FaceMatch;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.S3Object;
import com.amazonaws.services.rekognition.model.SearchFacesByImageRequest;
import com.amazonaws.services.rekognition.model.SearchFacesByImageResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Akash Sahu
 */
public class collection_search_face {
    public static final String collectionId = "Records";
    public static final String bucket = "thirdeyepics";
    public static final String photo = "test.jpg";
      
    public static void main(String[] args) throws Exception {

      AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();
        
      ObjectMapper objectMapper = new ObjectMapper();
       
       // Get an image object from S3 bucket.
      Image image=new Image()
              .withS3Object(new S3Object()
                      .withBucket(bucket)
                      .withName(photo));
      
      // Search collection for faces similar to the largest face in the image.
      SearchFacesByImageRequest searchFacesByImageRequest = new SearchFacesByImageRequest()
              .withCollectionId(collectionId)
              .withImage(image)
              .withFaceMatchThreshold(70F)
              .withMaxFaces(2);
      
      
       SearchFacesByImageResult searchFacesByImageResult = 
               rekognitionClient.searchFacesByImage(searchFacesByImageRequest);
      
        //System.out.println("Faces matching largest face in image from " + photo);
        List < FaceMatch > faceImageMatches = searchFacesByImageResult.getFaceMatches();
        for (FaceMatch face: faceImageMatches) {
           System.out.println(objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(face));
           System.out.println(face);
        }
   }
}
