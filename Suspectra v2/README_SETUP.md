# Suspectra v2 - Forensic Face Sketch Application

## Project Overview
Suspectra v2 is a JavaFX-based application for creating forensic face sketches. It allows users to build composite sketches using various facial elements like eyes, nose, lips, hair, etc.

## Prerequisites
- Java 8 or higher (JDK 1.8+)
- JavaFX SDK (included in the project)
- SQLite Browser (for database management)

## Quick Setup Guide

### 1. Database Setup
1. Open SQLite Browser
2. Open the `login.sqlite` file from the project folder
3. Run the SQL commands from `setup_database.sql`:
   ```sql
   CREATE TABLE IF NOT EXISTS login_data (
       id INTEGER PRIMARY KEY AUTOINCREMENT,
       email TEXT NOT NULL,
       password TEXT NOT NULL
   );
   
   INSERT OR REPLACE INTO login_data (email, password) VALUES ('test@example.com', 'test123');
   ```
4. Save the database

### 2. Run the Application

#### Option A: Using the Batch File (Recommended)
1. Double-click `run_project.bat`
2. The script will compile and run the project automatically

#### Option B: Manual Compilation
1. Open Command Prompt in the project directory
2. Run the following commands:

```bash
# Set JavaFX SDK path
set JAVAFX_SDK=javafx-sdk-17.0.2\lib

# Set classpath
set CLASSPATH=.;%JAVAFX_SDK%\javafx.base.jar;%JAVAFX_SDK%\javafx.controls.jar;%JAVAFX_SDK%\javafx.fxml.jar;%JAVAFX_SDK%\javafx.graphics.jar;%JAVAFX_SDK%\javafx.media.jar;%JAVAFX_SDK%\javafx.swing.jar;%JAVAFX_SDK%\javafx.web.jar;lib\activation.jar;lib\mail-1.4.7.jar;lib\sqlite-jdbc-3.30.1.jar

# Compile
javac -cp "%CLASSPATH%" -d build src/thirdeye/v2/*.java

# Run
java -cp "%CLASSPATH%;build" --module-path "%JAVAFX_SDK%" --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.swing,javafx.web thirdeye.v2.ThirdEyeV2
```

### 3. Login
- **Email**: akash29595@gmail.com
- **Password**: imakashsahu
- **Note**: OTP functionality uses the original author's Gmail credentials

## Project Structure
```
Suspectra v2/
├── src/suspectra/v2/         # Source code
├── javafx-sdk-17.0.2/        # JavaFX SDK
├── lib/                       # External libraries
├── elements/                  # Facial element images
├── login.sqlite              # Database file
├── run_project.bat           # Windows run script
└── setup_database.sql        # Database setup script
```

## Features
- **Splash Screen**: Application startup
- **Login System**: Database authentication
- **Main Menu**: Navigation interface
- **Sketch Creation**: Drag-and-drop facial elements
- **Image Export**: Save sketches as PNG files

## Troubleshooting

### Common Issues:
1. **"javac not found"**: Install Java JDK and add to PATH
2. **"JavaFX modules not found"**: Ensure JavaFX SDK is in the correct location
3. **"Database connection failed"**: Check if login.sqlite exists and is accessible
4. **"Class not found"**: Verify all JAR files are in the lib folder

### For NetBeans Users:
1. Open the project in NetBeans
2. Right-click Libraries → Add JAR/Folder
3. Add all files from the `lib` folder
4. Run the project (F6)

## Notes
- The application uses test credentials for initial setup
- Gmail OTP is disabled for testing purposes
- File paths have been updated to be relative
- All required libraries are included in the project

## Support
For issues or questions, check the project documentation or contact the development team.
