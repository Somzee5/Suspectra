/*
 * Dynamic Element Loader - Loads sketch elements dynamically from folders
 */
package suspectra.v2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * Utility class to dynamically load sketch elements from folders
 * @author Dynamic Loading System
 */
public class DynamicElementLoader {
    
    // Map to store element types and their folder paths
    private static final Map<String, String> ELEMENT_FOLDERS = new HashMap<>();
    
    static {
        ELEMENT_FOLDERS.put("head", "elements/sketch elements/head");
        ELEMENT_FOLDERS.put("hair", "elements/sketch elements/hair");
        ELEMENT_FOLDERS.put("eyes", "elements/sketch elements/eyes");
        ELEMENT_FOLDERS.put("eyebrows", "elements/sketch elements/eyebrows");
        ELEMENT_FOLDERS.put("nose", "elements/sketch elements/nose");
        ELEMENT_FOLDERS.put("lips", "elements/sketch elements/lips");
        ELEMENT_FOLDERS.put("mustach", "elements/sketch elements/mustach");
        ELEMENT_FOLDERS.put("more", "elements/sketch elements/more");
    }
    
    /**
     * Loads all PNG images from a specific element folder
     * @param elementType Type of element (head, hair, eyes, etc.)
     * @param baseClass Class used for resource loading
     * @param forSelection If true, returns only "Group" prefixed images. If false, returns only numbered images (01, 02, etc.)
     * @return List of image file names (without extension)
     */
    public static List<String> loadElementFiles(String elementType, Class<?> baseClass, boolean forSelection) {
        List<String> imageFiles = new ArrayList<>();
        
        try {
            String folderPath = ELEMENT_FOLDERS.get(elementType.toLowerCase());
            if (folderPath == null) {
                System.out.println("Unknown element type: " + elementType);
                return imageFiles;
            }
            
            // Try to get resource URL
            URL resourceUrl = baseClass.getResource("/" + folderPath);
            if (resourceUrl == null) {
                resourceUrl = baseClass.getResource(folderPath);
            }
            
            File folder = null;
            
            if (resourceUrl != null) {
                if (resourceUrl.getProtocol().equals("file")) {
                    // Development mode - load from file system
                    String path = resourceUrl.getPath();
                    // Handle URL encoding (spaces become %20)
                    if (path.contains("%20")) {
                        try {
                            path = URLDecoder.decode(path, "UTF-8");
                        } catch (Exception e) {
                            // Ignore
                        }
                    }
                    folder = new File(path);
                } else if (resourceUrl.getProtocol().equals("jar")) {
                    // JAR mode - try alternative path resolution
                    // Get the class location and construct path
                    String classLocation = baseClass.getProtectionDomain().getCodeSource().getLocation().getPath();
                    if (classLocation.endsWith(".jar")) {
                        // Running from JAR - would need special handling
                        System.out.println("Running from JAR - dynamic loading may be limited");
                    }
                }
            }
            
            // Fallback: try multiple path resolutions
            if (folder == null || !folder.exists()) {
                // Get the class file location
                String classPath = baseClass.getProtectionDomain().getCodeSource().getLocation().getPath();
                if (classPath.contains("%20")) {
                    try {
                        classPath = URLDecoder.decode(classPath, "UTF-8");
                    } catch (Exception e) {
                        // Ignore
                    }
                }
                
                // Try build directory path (when running from build/)
                File classFile = new File(classPath);
                if (classFile.isFile()) {
                    // It's a JAR file
                    classFile = classFile.getParentFile();
                }
                
                // The class is at: build/suspectra/v2/DashboardController.class
                // So classFile should be: build/suspectra/v2/
                // Elements are at: build/suspectra/v2/elements/sketch elements/hair
                
                // Try: build/suspectra/v2/elements/sketch elements/hair
                folder = new File(classFile, folderPath);
                
                if (!folder.exists()) {
                    // Try going up one level if classFile is suspectra/v2
                    File parent = classFile.getParentFile();
                    if (parent != null && parent.getName().equals("suspectra")) {
                        folder = new File(parent.getParentFile(), folderPath);
                    }
                }
                
                if (!folder.exists()) {
                    // Try: current working directory + build/suspectra/v2/elements/...
                    String cwd = System.getProperty("user.dir");
                    folder = new File(cwd, "build/suspectra/v2/" + folderPath);
                }
                
                if (!folder.exists()) {
                    // Try: current working directory + Suspectra v2/build/suspectra/v2/elements/...
                    String cwd = System.getProperty("user.dir");
                    folder = new File(cwd, "Suspectra v2/build/suspectra/v2/" + folderPath);
                }
                
                if (!folder.exists()) {
                    // Try: src path (for development)
                    String cwd = System.getProperty("user.dir");
                    folder = new File(cwd, "Suspectra v2/src/suspectra/v2/" + folderPath);
                }
            }
            
            // Debug output
            if (folder != null && folder.exists()) {
                System.out.println("Found folder for " + elementType + " at: " + folder.getAbsolutePath());
            }
            
            if (folder != null && folder.exists() && folder.isDirectory()) {
                File[] files = folder.listFiles((dir, name) -> {
                    String lowerName = name.toLowerCase();
                    return lowerName.endsWith(".png") || lowerName.endsWith(".jpg") || lowerName.endsWith(".jpeg");
                });
                
                if (files != null) {
                    System.out.println("Found " + files.length + " image files in " + elementType + " folder");
                    // Sort files by name for consistent ordering
                    java.util.Arrays.sort(files, (f1, f2) -> f1.getName().compareToIgnoreCase(f2.getName()));
                    
                    for (File file : files) {
                        String fileName = file.getName();
                        // Remove extension (.png, .jpg, .jpeg)
                        String nameWithoutExt = fileName.substring(0, fileName.lastIndexOf('.'));
                        
                        if (forSelection) {
                            // For selection sidebar: only include "Group" prefixed images
                            if (nameWithoutExt.startsWith("Group")) {
                                imageFiles.add(nameWithoutExt);
                                System.out.println("  Added Group image: " + nameWithoutExt + " (from " + fileName + ")");
                            }
                        } else {
                            // For canvas: only include numbered images (01, 02, 03, 13, 101, etc.)
                            // Must be pure numbers (all digits) and NOT start with "Group"
                            if (!nameWithoutExt.startsWith("Group") && nameWithoutExt.matches("^\\d+$")) {
                                imageFiles.add(nameWithoutExt);
                                System.out.println("  Added numbered image: " + nameWithoutExt + " (from " + fileName + ")");
                            }
                        }
                    }
                    System.out.println("Total " + (forSelection ? "Group" : "numbered") + " images loaded: " + imageFiles.size());
                } else {
                    System.out.println("Warning: No files found in folder or folder.listFiles() returned null");
                }
            } else {
                System.out.println("Warning: Could not find folder for " + elementType + " at path: " + folderPath);
            }
        } catch (Exception e) {
            System.err.println("Error loading elements for " + elementType + ": " + e.getMessage());
            e.printStackTrace();
        }
        
        return imageFiles;
    }
    
    /**
     * Creates ImageView elements dynamically and adds them to the container
     * @param elementType Type of element
     * @param container AnchorPane to add ImageViews to
     * @param baseClass Class for resource loading
     * @param imagePrefix Prefix for fx:id (e.g., "hair_e_" for hair elements)
     * @param onSelectHandler Event handler for selection
     * @return List of created ImageViews
     */
    public static List<ImageView> createElementImageViews(
            String elementType, 
            AnchorPane container, 
            Class<?> baseClass,
            String imagePrefix,
            javafx.event.EventHandler<javafx.scene.input.MouseEvent> onSelectHandler) {
        
        List<ImageView> imageViews = new ArrayList<>();
        List<String> imageFiles = loadElementFiles(elementType, baseClass, true); // true = for selection
        
        // Grid layout parameters
        int columns = 3;
        double imageSize = 100.0;
        double spacingX = 135.0;
        double spacingY = 134.0;
        double startX = 30.0;
        double startY = 129.0;
        
        for (int i = 0; i < imageFiles.size(); i++) {
            String imageName = imageFiles.get(i);
            
            // Calculate position
            int col = i % columns;
            int row = i / columns;
            double x = startX + (col * spacingX);
            double y = startY + (row * spacingY);
            
            // Create ImageView
            ImageView imageView = new ImageView();
            imageView.setFitWidth(imageSize);
            imageView.setFitHeight(imageSize);
            imageView.setLayoutX(x);
            imageView.setLayoutY(y);
            imageView.setPreserveRatio(true);
            imageView.setPickOnBounds(true);
            imageView.setId(imagePrefix + (i + 1)); // hair_e_1, hair_e_2, etc.
            imageView.setCursor(Cursor.HAND);
            
            // Load image
            try {
                String imagePath = ELEMENT_FOLDERS.get(elementType.toLowerCase()) + "/" + imageName + ".png";
                URL imageUrl = baseClass.getResource(imagePath);
                if (imageUrl != null) {
                    Image image = new Image(imageUrl.toExternalForm());
                    imageView.setImage(image);
                }
            } catch (Exception e) {
                System.err.println("Error loading image " + imageName + ": " + e.getMessage());
            }
            
            // Add event handler
            if (onSelectHandler != null) {
                imageView.setOnMousePressed(onSelectHandler);
            }
            
            // Add to container
            container.getChildren().add(imageView);
            imageViews.add(imageView);
        }
        
        return imageViews;
    }
    
    /**
     * Creates sketch ImageViews (the ones that appear on canvas when selected)
     * @param elementType Type of element
     * @param container AnchorPane (sketch canvas)
     * @param baseClass Class for resource loading
     * @param imagePrefix Prefix for fx:id (e.g., "hair_s_" for hair sketches)
     * @return Map of image name to ImageView
     */
    public static Map<String, ImageView> createSketchImageViews(
            String elementType,
            AnchorPane container,
            Class<?> baseClass,
            String imagePrefix) {
        
        Map<String, ImageView> sketchViews = new HashMap<>();
        List<String> imageFiles = loadElementFiles(elementType, baseClass, false); // false = for canvas
        
        for (int i = 0; i < imageFiles.size(); i++) {
            String imageName = imageFiles.get(i);
            
            // Create ImageView for sketch canvas
            ImageView sketchView = new ImageView();
            sketchView.setPreserveRatio(true);
            sketchView.setPickOnBounds(true);
            sketchView.setId(imagePrefix + (i + 1)); // hair_s_1, hair_s_2, etc.
            sketchView.setVisible(false); // Initially hidden
            
            // Load image
            try {
                String imagePath = ELEMENT_FOLDERS.get(elementType.toLowerCase()) + "/" + imageName + ".png";
                URL imageUrl = baseClass.getResource(imagePath);
                if (imageUrl != null) {
                    Image image = new Image(imageUrl.toExternalForm());
                    sketchView.setImage(image);
                }
            } catch (Exception e) {
                System.err.println("Error loading sketch image " + imageName + ": " + e.getMessage());
            }
            
            // Add to container
            container.getChildren().add(sketchView);
            sketchViews.put(imageName, sketchView);
        }
        
        return sketchViews;
    }
}

