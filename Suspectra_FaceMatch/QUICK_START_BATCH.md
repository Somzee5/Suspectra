# Quick Start: Batch Add Faces to AWS Rekognition

## Run the Batch Script

```bash
cd Suspectra_FaceMatch
mvn exec:java@batch-add-faces
```

That's it! The script will:
- Find all images in `colorimages/` folder (251 images)
- Upload each to S3 bucket
- Index faces into Rekognition collection
- Show progress and results

## Expected Output

```
========================================
  BATCH FACE ADDITION TO AWS REKOGNITION
========================================

✓ AWS credentials found
Folder: .../colorimages
Found 251 image file(s) to process

[1/251] Processing: 001.jpg
  ✓ Uploaded to S3: s3://suspectra-facematch-somzee5/faces/001.jpg
    Face ID: abc123...
  ✓ Face indexed in Rekognition collection

[2/251] Processing: 002.jpg
...

========================================
  BATCH PROCESSING SUMMARY
========================================
Total images:        251
Processed:           251
Successful uploads:  251
Faces indexed:       248
Failed:              0
Time taken:          450 seconds

✓ Successfully added 248 image(s) to AWS Rekognition collection
  These images are now searchable when you upload a sketch!
========================================
```

## After Running

1. **All indexed images are now searchable**
2. **Open the face match application**: `mvn exec:java`
3. **Upload a sketch** and click "Find Match"
4. **It will automatically compare** against all 251 images!

## Troubleshooting

**"AWS credentials not found"**
```bash
aws configure
```

**"Access Denied"**
- Check IAM permissions: `AmazonS3FullAccess` and `AmazonRekognitionFullAccess`

**Images not found in search**
- Wait a few seconds (propagation delay)
- Check that faces were detected during indexing

## More Details

See `BATCH_ADD_FACES_README.md` for complete documentation.

