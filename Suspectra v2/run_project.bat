@echo off
echo Setting up Suspectra v2 Project...
echo.

REM Set JavaFX SDK path
set JAVAFX_SDK=javafx-sdk-17.0.2\lib

REM Set classpath
set CLASSPATH=.;%JAVAFX_SDK%\javafx.base.jar;%JAVAFX_SDK%\javafx.controls.jar;%JAVAFX_SDK%\javafx.fxml.jar;%JAVAFX_SDK%\javafx.graphics.jar;%JAVAFX_SDK%\javafx.media.jar;%JAVAFX_SDK%\javafx.swing.jar;%JAVAFX_SDK%\javafx.web.jar;lib\activation.jar;lib\mail-1.4.7.jar;lib\sqlite-jdbc-3.30.1.jar

REM Compile the project
echo Compiling project...
javac -cp "%CLASSPATH%" -d build src/suspectra/v2/*.java

if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed! Please check the errors above.
    pause
    exit /b 1
)

echo Compilation successful!
echo.

REM Copy resources to build directory
echo Copying resources...
if not exist "build\suspectra\v2" mkdir "build\suspectra\v2"
copy "src\suspectra\v2\*.fxml" "build\suspectra\v2\" >nul
copy "src\suspectra\v2\*.css" "build\suspectra\v2\" >nul
copy "src\suspectra\v2\*.png" "build\suspectra\v2\" >nul
copy "src\suspectra\v2\*.jpg" "build\suspectra\v2\" >nul
xcopy "src\suspectra\v2\elements" "build\suspectra\v2\elements\" /E /I /Q >nul
echo Resources copied successfully!
echo.

REM Run the project
echo Running Suspectra v2...
java -cp "%CLASSPATH%;build" --module-path "%JAVAFX_SDK%" --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.swing,javafx.web suspectra.v2.SuspectraV2

pause
