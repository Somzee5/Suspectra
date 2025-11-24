# Face Matching Technology Stack

## Overview
This application uses **AWS Rekognition** - Amazon's cloud-based AI face recognition service - to match sketch images against a database of face photos.

---

## Core Technology: AWS Rekognition

### What is AWS Rekognition?
AWS Rekognition is Amazon's **deep learning-based computer vision service** that can:
- Detect faces in images
- Extract facial features (eyes, nose, mouth, face shape, etc.)
- Create mathematical "face signatures" (facial feature vectors)
- Compare faces and calculate similarity scores
- Recognize faces across different lighting, angles, and expressions

### How It Works (Technical Details)

#### 1. **Face Detection**
- Uses convolutional neural networks (CNNs) trained on millions of images
- Detects faces in images regardless of:
  - Lighting conditions
  - Face angle/pose
  - Facial expressions
  - Image quality
  - Background

#### 2. **Feature Extraction**
- Extracts 128-dimensional feature vector (face signature)
- Captures:
  - Facial geometry (distances between features)
  - Facial landmarks (eye positions, nose shape, etc.)
  - Texture patterns
  - Face shape characteristics
- This signature is **invariant** to:
  - Lighting changes
  - Minor angle variations
  - Different expressions
  - Image quality differences

#### 3. **Face Comparison**
- Compares feature vectors using **cosine similarity** or **Euclidean distance**
- Returns similarity score (0-100%)
- Higher score = more similar faces

#### 4. **Face Collections**
- Stores indexed faces in a "collection"
- Each face has:
  - **Face ID**: Unique identifier
  - **External Image ID**: Your custom identifier (filename)
  - **Face Signature**: The 128-dimensional feature vector
- Allows fast searching across thousands of faces

---

## Architecture

### Components Used:

#### 1. **AWS S3 (Simple Storage Service)**
- **Purpose**: Stores images (both sketches and reference photos)
- **Bucket**: `thirdeyepics`
- **Region**: `ap-south-1` (Asia Pacific - Mumbai)
- **How it's used**:
  - Upload sketch images to S3
  - Store reference face photos
  - Rekognition reads images from S3

#### 2. **AWS Rekognition**
- **Purpose**: Face recognition and matching
- **Collection**: `suspectra_collection`
- **How it's used**:
  - Index faces from S3 images into collection
  - Search collection for matching faces
  - Return similarity scores

#### 3. **AWS SDK for Java**
- **Version**: 1.11.775
- **Dependencies**:
  - `aws-java-sdk` (core SDK)
  - `aws-java-sdk-rekognition` (Rekognition client)
  - `aws-java-sdk-s3` (S3 client)
- **Purpose**: Java API to interact with AWS services

#### 4. **Local Fallback (Pixel Matching)**
- **Technology**: Basic image processing
- **Algorithm**: Mean Squared Error (MSE)
- **Purpose**: Fallback when AWS is unavailable
- **Limitation**: Very inaccurate (pixel comparison, not face recognition)

---

## Workflow

### Step 1: Setup (One-time)
1. **Create S3 Bucket**: `thirdeyepics`
2. **Create Rekognition Collection**: `suspectra_collection`
   ```java
   collection_create.java
   ```
3. **Add Reference Images to Collection**:
   - Upload photos to S3
   - Index faces into collection
   ```java
   collection_add_image.java
   ```

### Step 2: Upload Sketch
1. User selects sketch image
2. **Upload to S3**: 
   - Bucket: `thirdeyepics`
   - Path: `sketches/{timestamp}_{filename}`
3. Store S3 key for later use

### Step 3: Find Match
1. **Read sketch from S3**:
   ```java
   Image image = new Image()
       .withS3Object(new S3Object()
           .withBucket("thirdeyepics")
           .withName(s3Key));
   ```

2. **Search Rekognition Collection**:
   ```java
   SearchFacesByImageRequest request = new SearchFacesByImageRequest()
       .withCollectionId("suspectra_collection")
       .withImage(image)
       .withFaceMatchThreshold(70F)  // Minimum 70% similarity
       .withMaxFaces(5);              // Get top 5 matches
   ```

3. **Rekognition Process**:
   - Detects face in sketch
   - Extracts face features
   - Compares against all faces in collection
   - Returns matches sorted by similarity

4. **Display Results**:
   - Show best match
   - Display similarity score
   - Show matched photo from S3

---

## Technology Comparison

### AWS Rekognition (Current - Proper Method)
| Feature | Description |
|---------|-------------|
| **Algorithm** | Deep Learning (CNN) |
| **Accuracy** | Very High (95%+ for good quality images) |
| **Speed** | Fast (milliseconds per search) |
| **Scalability** | Can handle millions of faces |
| **Robustness** | Works with sketches, photos, different angles |
| **Cost** | Pay per API call (~$1 per 1000 searches) |

### Local Pixel Matching (Fallback - Not Recommended)
| Feature | Description |
|---------|-------------|
| **Algorithm** | Mean Squared Error (MSE) |
| **Accuracy** | Very Low (30-50% for similar images) |
| **Speed** | Slow (compares each pixel) |
| **Scalability** | Limited (slow with many images) |
| **Robustness** | Poor (fails with different lighting/angles) |
| **Cost** | Free (local processing) |

---

## Why AWS Rekognition is Better

### 1. **Feature-Based vs Pixel-Based**
- **Rekognition**: Compares facial features (eyes, nose, mouth positions)
- **Pixel Matching**: Compares raw RGB values

### 2. **Invariant to Changes**
- **Rekognition**: Works with:
  - Different lighting
  - Different angles
  - Different expressions
  - Sketches vs Photos
- **Pixel Matching**: Fails with any variation

### 3. **Scalability**
- **Rekognition**: Can search millions of faces in milliseconds
- **Pixel Matching**: Gets slower with more images

### 4. **Accuracy**
- **Rekognition**: 95%+ accuracy for good quality images
- **Pixel Matching**: 30-50% accuracy (often wrong)

---

## API Calls Used

### 1. **S3 PutObject**
```java
s3Client.putObject(bucket, s3Key, file);
```
- Uploads sketch image to S3

### 2. **Rekognition SearchFacesByImage**
```java
rekognitionClient.searchFacesByImage(request);
```
- Searches collection for matching faces
- Returns similarity scores

### 3. **Rekognition IndexFaces** (Setup)
```java
rekognitionClient.indexFaces(request);
```
- Adds faces to collection (one-time setup)

---

## Configuration

### AWS Credentials
- Stored in: `~/.aws/credentials`
- Format:
  ```
  [default]
  aws_access_key_id = YOUR_ACCESS_KEY
  aws_secret_access_key = YOUR_SECRET_KEY
  ```

### AWS Region
- S3 Bucket Region: `ap-south-1` (Mumbai)
- Rekognition: Uses same region as S3

### Collection Settings
- **Collection ID**: `suspectra_collection`
- **Face Match Threshold**: 70% (minimum similarity)
- **Max Faces**: 5 (number of matches to return)

---

## Summary

**Primary Technology**: AWS Rekognition (AI-powered face recognition)
- Uses deep learning neural networks
- Extracts facial features, not pixels
- Highly accurate and scalable
- Cloud-based service

**Storage**: AWS S3
- Stores images (sketches and reference photos)
- Accessed by Rekognition for processing

**Fallback**: Local pixel matching (not recommended)
- Only used when AWS is unavailable
- Very inaccurate
- Should be improved or removed

