
package com.mycompany.suspectra_facematch;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.CreateCollectionRequest;
import com.amazonaws.services.rekognition.model.CreateCollectionResult;


public class collection_create {
    public static void main(String[] args) throws Exception {
                AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard()
                   .withRegion("us-east-1")
                   .build();

            String collectionId = "suspectra_collection"; //Collection Name to be Created

            System.out.println("Creating collection: " + collectionId );

            CreateCollectionRequest request = new CreateCollectionRequest()
                        .withCollectionId(collectionId);

            CreateCollectionResult createCollectionResult = rekognitionClient.createCollection(request); 
            System.out.println("CollectionArn : " +
               createCollectionResult.getCollectionArn());
            System.out.println("Status code : " +
               createCollectionResult.getStatusCode().toString());

   }
}
