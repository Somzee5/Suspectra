# How the Face Matching System Works

## Current Workflow

### Step 1: OPEN SKETCH Button
- **What it does**: Opens a file dialog to select a sketch image from your computer
- **Result**: Displays the selected sketch in the left panel
- **Location**: The image path is stored in `sketch_path` field

### Step 2: UPLOAD SKETCH Button
**Currently, this does NOT upload to AWS!** Instead, it:
1. Saves a copy of your sketch to: `src/main/java/com/mycompany/suspectra_facematch/sketches/test.jpg`
2. **Immediately runs LOCAL matching** (not AWS)
3. Compares your sketch against images in: `src/main/java/com/mycompany/suspectra_facematch/faces/` folder

### Step 3: FIND MATCH Button
**This has TWO modes:**

#### Mode 1: AWS Rekognition (Primary - but currently broken)
- **What it should do**: 
  - Upload sketch to S3 bucket `thirdeyepics` as `test.jpg`
  - Search AWS Rekognition collection `suspectra_collection` for matching faces
  - Uses AWS's AI face recognition technology
  
- **Current Problem**: 
  - It's hardcoded to search for `test.jpg` in S3 (line 315)
  - It doesn't actually upload YOUR sketch first
  - It searches a fixed image, not your uploaded sketch

#### Mode 2: Local Fallback (Secondary - currently active)
- **When it runs**: If AWS search fails or finds no match
- **What it does**: 
  - Compares your sketch against images in the `faces` folder
  - Uses a **primitive pixel-by-pixel comparison** (Mean Squared Error)
  - This is why you're getting bad matches!

---

## Technologies Used

### 1. **AWS Rekognition** (Intended - but not working properly)
- **What it is**: Amazon's AI-powered face recognition service
- **How it works**: 
  - Uses deep learning to extract facial features (eyes, nose, mouth, face shape, etc.)
  - Creates a "face signature" (mathematical representation)
  - Compares signatures, not raw pixels
  - Much more accurate than pixel comparison
  
- **Where images come from**: 
  - Images must be uploaded to S3 bucket `thirdeyepics`
  - Then indexed into Rekognition collection `suspectra_collection`
  - Collection is created using `collection_create.java`
  - Images added using `collection_add_image.java`

### 2. **Local Pixel Matching** (Current - giving bad results)
- **What it is**: Simple pixel-by-pixel color comparison
- **How it works**:
  - Resizes both images to 200x200 pixels
  - Compares RGB values of each pixel
  - Calculates Mean Squared Error (MSE)
  - Converts to similarity percentage
  
- **Why it's bad**:
  - Compares raw pixels, not facial features
  - A sketch vs photo will have very different pixel values
  - Lighting, angle, expression changes = bad match
  - No understanding of what a "face" is

- **Where images come from**: 
  - Local folder: `src/main/java/com/mycompany/suspectra_facematch/faces/`
  - Contains ~200+ face images (m-008-01.jpg, f-005-01.jpg, etc.)

---

## Why You're Getting Bad Matches

### Problem 1: Wrong Matching Algorithm
The local matching uses **pixel comparison**, which is terrible for:
- Sketch vs Photo (completely different pixel patterns)
- Different lighting conditions
- Different angles
- Different expressions

**Example**: A pencil sketch of a face has mostly gray pixels, while a color photo has many colors. The pixel comparison will think they're completely different, even if it's the same person!

### Problem 2: AWS Not Actually Used
When you click "UPLOAD SKETCH", it:
- Does NOT upload to AWS
- Does NOT use AWS Rekognition
- Only does local pixel matching

### Problem 3: Hardcoded Image Name
The "FIND MATCH" button searches for a fixed image `test.jpg` in S3, not your actual uploaded sketch.

---

## How It SHOULD Work

### Proper Workflow:

1. **OPEN SKETCH**: Select your sketch ✓ (This works)

2. **UPLOAD SKETCH**: 
   - Upload sketch to S3 bucket `thirdeyepics`
   - Save as unique filename (e.g., `sketch_20241123_001.jpg`)
   - **Then** search AWS Rekognition collection
   - Display matches with similarity scores

3. **FIND MATCH** (if needed):
   - Re-search with updated image
   - Show top matches from AWS collection

### Where Comparison Images Come From:

**AWS Rekognition Collection** (`suspectra_collection`):
- Images uploaded to S3 bucket `thirdeyepics`
- Indexed into Rekognition using `collection_add_image.java`
- Each image has an `ExternalImageId` (filename)
- Rekognition extracts face features and stores them
- When searching, it compares face features, not pixels

**Local Faces Folder** (fallback):
- `src/main/java/com/mycompany/suspectra_facematch/faces/`
- Contains reference images
- Currently used for primitive pixel matching
- Should only be used if AWS is unavailable

---

## What Needs to Be Fixed

1. **Fix UPLOAD SKETCH**: Actually upload to S3 and use AWS Rekognition
2. **Fix FIND MATCH**: Use the uploaded sketch, not hardcoded `test.jpg`
3. **Improve Local Matching**: If AWS unavailable, use better algorithm (or disable it)
4. **Add Proper Error Handling**: Show clear messages about AWS vs local matching

---

## Summary

**Current State:**
- ❌ AWS Rekognition: Not properly integrated
- ❌ S3 Upload: Not happening
- ✅ Local Matching: Working but giving bad results (wrong algorithm)
- ✅ Image Selection: Working

**What You're Seeing:**
- Random matches because pixel comparison is comparing:
  - Sketch (grayscale, artistic) vs Photos (color, realistic)
  - Different lighting, angles, expressions
  - No understanding of facial features

**What Should Happen:**
- Upload sketch to AWS S3
- Use AWS Rekognition AI to find matches
- Get accurate similarity scores based on facial features
- Display matching photos from your collection

