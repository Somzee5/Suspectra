/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thirdeye_facematch;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.DeleteCollectionRequest;
import com.amazonaws.services.rekognition.model.DeleteCollectionResult;

/**
 *
 * @author Akash Sahu
 */
public class collection_delete {
    public static void main(String[] args) throws Exception {
      AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();

      String collectionId = "Records"; //Collection Name to be deleted

      System.out.println("Deleting collections");
      
      DeleteCollectionRequest request = new DeleteCollectionRequest()
         .withCollectionId(collectionId);
      DeleteCollectionResult deleteCollectionResult = rekognitionClient.deleteCollection(request);        
  
      System.out.println(collectionId + ": " + deleteCollectionResult.getStatusCode()
         .toString());
    }
}
