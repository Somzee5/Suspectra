package com.mycompany.suspectra_facematch;

import java.io.ByteArrayInputStream;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.GetCallerIdentityRequest;
import com.amazonaws.services.securitytoken.model.GetCallerIdentityResult;

/**
 * Small debug runner: prints caller identity and attempts a small PutObject.
 * Uses default credential/provider chain. Run from project root with:
 * mvn exec:java "-Dexec.mainClass=com.mycompany.suspectra_facematch.debug_aws"
 */
public class debug_aws {
    public static void main(String[] args) {
        String bucket = "suspectra-facematch-somzee5"; // default from project
        String key = "debug-test-upload.txt";

        System.out.println("[debug_aws] Starting debug run");

        // Print caller identity via STS
        try {
            AWSSecurityTokenService sts = AWSSecurityTokenServiceClientBuilder.defaultClient();
            GetCallerIdentityResult id = sts.getCallerIdentity(new GetCallerIdentityRequest());
            System.out.println("CallerIdentity: Account=" + id.getAccount() + " Arn=" + id.getArn() + " UserId=" + id.getUserId());
            try { sts.shutdown(); } catch (Exception ignore) {}
        } catch (SdkClientException sce) {
            System.err.println("[debug_aws] Could not get caller identity: " + sce.getMessage());
            sce.printStackTrace(System.err);
        }

        // Attempt small put to S3 with the default client
        try {
            // Force S3 client to the bucket region to avoid signing/region mismatch
            AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion("us-east-1").build();
            byte[] data = "debug upload".getBytes();
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentLength(data.length);
            PutObjectRequest req = new PutObjectRequest(bucket, key, new ByteArrayInputStream(data), meta);
            s3.putObject(req);
            System.out.println("[debug_aws] PutObject succeeded to s3://" + bucket + "/" + key);
            try { s3.shutdown(); } catch (Exception ignore) {}
        } catch (AmazonServiceException ase) {
            System.err.println("[debug_aws] AmazonServiceException: ErrorCode=" + ase.getErrorCode() + " StatusCode=" + ase.getStatusCode());
            System.err.println("[debug_aws] Message: " + ase.getMessage());
            ase.printStackTrace(System.err);
        } catch (SdkClientException sce) {
            System.err.println("[debug_aws] SdkClientException: " + sce.getMessage());
            sce.printStackTrace(System.err);
        } catch (Exception e) {
            System.err.println("[debug_aws] Unexpected exception: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}
