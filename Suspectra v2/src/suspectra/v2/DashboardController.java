/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suspectra.v2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Akash Sahu
 */
public class DashboardController implements Initializable {

    @FXML
    private AnchorPane guideline;
    @FXML
    private AnchorPane toolkit;
    @FXML
    private ImageView head;
    @FXML
    private ImageView hair;
    @FXML
    private ImageView eyes;
    @FXML
    private ImageView eyebrows;
    @FXML
    private ImageView nose;
    @FXML
    private ImageView lips;
    @FXML
    private ImageView mustach;
    @FXML
    private ImageView more;
    @FXML
    private ScrollPane elements_panel;
    @FXML
    private AnchorPane head_elements;
    @FXML
    private AnchorPane sketch;
    @FXML
    private AnchorPane menu_tab;
    @FXML
    private Rectangle save_btn;
    @FXML
    private Rectangle reset_btn;
    @FXML
    private Rectangle compare_btn;
    @FXML
    private Rectangle zoom_in_btn;
    @FXML
    private Rectangle zoom_out_btn;
    @FXML
    private Rectangle size_increase_btn;
    @FXML
    private Rectangle size_decrease_btn;
    @FXML
    private Rectangle sketch_size_increase_btn;
    @FXML
    private Rectangle sketch_size_decrease_btn;
    @FXML
    private Slider sizeSlider;
    @FXML
    private javafx.scene.control.Label sizeLabel;
    
    // Track currently selected component for zooming
    private ImageView currentlySelectedComponent = null;
    private String currentlySelectedType = null;
    
    // Store original dimensions for zooming (so we can reset and zoom properly)
    private Map<ImageView, double[]> originalDimensions = new HashMap<>(); // [originalWidth, originalHeight]
    @FXML
    private AnchorPane element_anchor;
    @FXML
    private AnchorPane hair_elements;
    @FXML
    private AnchorPane eyes_elements;
    @FXML
    private AnchorPane eyebrows_elements;
    @FXML
    private AnchorPane nose_elements;
    @FXML
    private AnchorPane lips_elements;
    @FXML
    private AnchorPane mustach_elements;
    @FXML
    private AnchorPane more_elements;
    @FXML
    private ImageView head_e_1;
    @FXML
    private ImageView head_e_2;
    @FXML
    private ImageView hair_e_1;
    @FXML
    private ImageView hair_e_2;
    @FXML
    private ImageView eyes_e_1;
    @FXML
    private ImageView eyes_e_2;
    @FXML
    private ImageView eyeb_e_1;
    @FXML
    private ImageView eyeb_e_2;
    @FXML
    private ImageView nose_e_1;
    @FXML
    private ImageView nose_e_2;
    @FXML
    private ImageView lips_e_1;
    @FXML
    private ImageView lips_e_2;
    @FXML
    private ImageView must_e_1;
    @FXML
    private ImageView must_e_2;
    @FXML
    private ImageView more_e_1;
    @FXML
    private ImageView more_e_2;
    @FXML
    private AnchorPane alignment;
    @FXML
    private ImageView head_s_1;
    @FXML
    private ImageView head_s_2;
    @FXML
    private ImageView hair_s_1;
    @FXML
    private ImageView hair_s_2;
    @FXML
    private ImageView eyes_s_1;
    @FXML
    private ImageView eyes_s_2;
    @FXML
    private ImageView eyeb_s_1;
    @FXML
    private ImageView eyeb_s_2;
    @FXML
    private ImageView nose_s_1;
    @FXML
    private ImageView nose_s_2;
    @FXML
    private ImageView lips_s_1;
    @FXML
    private ImageView lips_s_2;
    @FXML
    private ImageView must_s_1;
    @FXML
    private ImageView must_s_2;
    @FXML
    private ImageView more_s_1;
    @FXML
    private ImageView more_s_2;
    @FXML
    private Rectangle head_del;
    @FXML
    private Rectangle hair_del;
    @FXML
    private Rectangle eyes_del;
    @FXML
    private Rectangle eyeb_del;
    @FXML
    private Rectangle nose_del;
    @FXML
    private Rectangle lips_del;
    @FXML
    private Rectangle must_del;
    @FXML
    private Rectangle more_del;
    @FXML
    private ImageView head_e_3;
    @FXML
    private ImageView head_e_4;
    @FXML
    private ImageView head_e_5;
    @FXML
    private ImageView head_e_6;
    @FXML
    private ImageView head_e_7;
    @FXML
    private ImageView head_e_8;
    @FXML
    private ImageView head_e_9;
    @FXML
    private ImageView head_e_10;
    @FXML
    private ImageView hair_e_3;
    @FXML
    private ImageView hair_e_4;
    @FXML
    private ImageView hair_e_5;
    @FXML
    private ImageView hair_e_6;
    @FXML
    private ImageView hair_e_7;
    @FXML
    private ImageView hair_e_8;
    @FXML
    private ImageView hair_e_9;
    @FXML
    private ImageView hair_e_10;
    @FXML
    private ImageView hair_e_11;
    @FXML
    private ImageView hair_e_12;
    @FXML
    private ImageView eyes_e_3;
    @FXML
    private ImageView eyes_e_4;
    @FXML
    private ImageView eyes_e_5;
    @FXML
    private ImageView eyes_e_6;
    @FXML
    private ImageView eyes_e_7;
    @FXML
    private ImageView eyes_e_8;
    @FXML
    private ImageView eyes_e_9;
    @FXML
    private ImageView eyes_e_10;
    @FXML
    private ImageView eyes_e_11;
    @FXML
    private ImageView eyes_e_12;
    @FXML
    private ImageView eyeb_e_3;
    @FXML
    private ImageView eyeb_e_4;
    @FXML
    private ImageView eyeb_e_5;
    @FXML
    private ImageView eyeb_e_6;
    @FXML
    private ImageView eyeb_e_7;
    @FXML
    private ImageView eyeb_e_8;
    @FXML
    private ImageView eyeb_e_9;
    @FXML
    private ImageView eyeb_e_10;
    @FXML
    private ImageView eyeb_e_11;
    @FXML
    private ImageView eyeb_e_12;
    @FXML
    private ImageView nose_e_3;
    @FXML
    private ImageView nose_e_4;
    @FXML
    private ImageView nose_e_5;
    @FXML
    private ImageView nose_e_6;
    @FXML
    private ImageView nose_e_7;
    @FXML
    private ImageView nose_e_8;
    @FXML
    private ImageView nose_e_9;
    @FXML
    private ImageView nose_e_10;
    @FXML
    private ImageView nose_e_11;
    @FXML
    private ImageView nose_e_12;
    @FXML
    private ImageView lips_e_3;
    @FXML
    private ImageView lips_e_4;
    @FXML
    private ImageView lips_e_5;
    @FXML
    private ImageView lips_e_6;
    @FXML
    private ImageView lips_e_7;
    @FXML
    private ImageView lips_e_8;
    @FXML
    private ImageView lips_e_9;
    @FXML
    private ImageView lips_e_10;
    @FXML
    private ImageView lips_e_11;
    @FXML
    private ImageView lips_e_12;
    @FXML
    private ImageView must_e_3;
    @FXML
    private ImageView must_e_4;
    @FXML
    private ImageView must_e_5;
    @FXML
    private ImageView must_e_6;
    @FXML
    private ImageView must_e_7;
    @FXML
    private ImageView must_e_8;
    @FXML
    private ImageView must_e_9;
    @FXML
    private ImageView must_e_10;
    @FXML
    private ImageView must_e_11;
    @FXML
    private ImageView must_e_12;
    @FXML
    private ImageView more_s_5;
    @FXML
    private ImageView more_s_6;
    @FXML
    private ImageView head_s_3;
    @FXML
    private ImageView head_s_4;
    @FXML
    private ImageView head_s_5;
    @FXML
    private ImageView head_s_6;
    @FXML
    private ImageView head_s_7;
    @FXML
    private ImageView head_s_8;
    @FXML
    private ImageView head_s_9;
    @FXML
    private ImageView head_s_10;
    @FXML
    private ImageView more_s_3;
    @FXML
    private ImageView more_s_4;
    @FXML
    private ImageView hair_s_3;
    @FXML
    private ImageView hair_s_4;
    @FXML
    private ImageView hair_s_5;
    @FXML
    private ImageView hair_s_6;
    @FXML
    private ImageView hair_s_7;
    @FXML
    private ImageView hair_s_8;
    @FXML
    private ImageView hair_s_9;
    @FXML
    private ImageView hair_s_10;
    @FXML
    private ImageView hair_s_11;
    @FXML
    private ImageView hair_s_12;
    @FXML
    private ImageView eyes_s_3;
    @FXML
    private ImageView eyes_s_4;
    @FXML
    private ImageView eyes_s_5;
    @FXML
    private ImageView eyes_s_6;
    @FXML
    private ImageView eyes_s_7;
    @FXML
    private ImageView eyes_s_8;
    @FXML
    private ImageView eyes_s_9;
    @FXML
    private ImageView eyes_s_10;
    @FXML
    private ImageView eyes_s_11;
    @FXML
    private ImageView eyes_s_12;
    @FXML
    private ImageView eyeb_s_3;
    @FXML
    private ImageView eyeb_s_4;
    @FXML
    private ImageView eyeb_s_5;
    @FXML
    private ImageView eyeb_s_6;
    @FXML
    private ImageView eyeb_s_7;
    @FXML
    private ImageView eyeb_s_8;
    @FXML
    private ImageView eyeb_s_9;
    @FXML
    private ImageView eyeb_s_10;
    @FXML
    private ImageView eyeb_s_11;
    @FXML
    private ImageView eyeb_s_12;
    @FXML
    private ImageView nose_s_3;
    @FXML
    private ImageView nose_s_4;
    @FXML
    private ImageView nose_s_5;
    @FXML
    private ImageView nose_s_6;
    @FXML
    private ImageView nose_s_7;
    @FXML
    private ImageView nose_s_8;
    @FXML
    private ImageView nose_s_9;
    @FXML
    private ImageView nose_s_10;
    @FXML
    private ImageView nose_s_11;
    @FXML
    private ImageView nose_s_12;
    @FXML
    private ImageView lips_s_3;
    @FXML
    private ImageView lips_s_4;
    @FXML
    private ImageView lips_s_5;
    @FXML
    private ImageView lips_s_6;
    @FXML
    private ImageView lips_s_7;
    @FXML
    private ImageView lips_s_8;
    @FXML
    private ImageView lips_s_9;
    @FXML
    private ImageView lips_s_10;
    @FXML
    private ImageView lips_s_11;
    @FXML
    private ImageView lips_s_12;
    @FXML
    private ImageView must_s_3;
    @FXML
    private ImageView must_s_4;
    @FXML
    private ImageView must_s_5;
    @FXML
    private ImageView must_s_6;
    @FXML
    private ImageView must_s_7;
    @FXML
    private ImageView must_s_8;
    @FXML
    private ImageView must_s_9;
    @FXML
    private ImageView must_s_10;
    @FXML
    private ImageView must_s_11;
    @FXML
    private ImageView must_s_12;
    @FXML
    private ImageView more_e_3;
    @FXML
    private ImageView more_e_4;
    @FXML
    private ImageView more_e_5;
    @FXML
    private ImageView more_e_6;
    
    // Dynamic element storage
    private Map<String, List<ImageView>> elementImageViews = new HashMap<>(); // element_e_X ImageViews
    private Map<String, List<ImageView>> sketchImageViews = new HashMap<>(); // element_s_X ImageViews for canvas
    private Map<String, Map<ImageView, ImageView>> elementToSketchMap = new HashMap<>(); // Maps element ImageView to sketch ImageView
    
    // Default positions for each component type on the canvas (relative to sketch container center)
    // Sketch canvas is 620x620, so center is approximately 310, 310
    private static final Map<String, double[]> DEFAULT_POSITIONS = new HashMap<>();
    static {
        // Format: [x, y] positions for each component type (centered on 620x620 canvas)
        DEFAULT_POSITIONS.put("head", new double[]{160, 200}); // Head at center-bottom
        DEFAULT_POSITIONS.put("hair", new double[]{160, 100}); // Hair above head (top of head)
        DEFAULT_POSITIONS.put("eyes", new double[]{160, 180}); // Eyes on head (middle-upper)
        DEFAULT_POSITIONS.put("eyebrows", new double[]{160, 160}); // Eyebrows above eyes
        DEFAULT_POSITIONS.put("nose", new double[]{160, 220}); // Nose below eyes (center of face)
        DEFAULT_POSITIONS.put("lips", new double[]{160, 260}); // Lips below nose
        DEFAULT_POSITIONS.put("mustach", new double[]{160, 250}); // Mustache above lips
        DEFAULT_POSITIONS.put("more", new double[]{160, 200}); // More elements at center
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize slider
        if (sizeSlider != null) {
            sizeSlider.setVisible(true);
            sizeSlider.setDisable(false);
            if (sizeLabel != null) {
                sizeLabel.setText("100%");
            }
            System.out.println("Size slider initialized and visible");
        } else {
            System.err.println("ERROR: sizeSlider is null in initialize!");
        }
        
        // Initialize size buttons (menu bar)
        if (size_increase_btn != null) {
            size_increase_btn.setVisible(true);
            size_increase_btn.setDisable(false);
            System.out.println("Size increase button (menu) initialized and visible");
        } else {
            System.err.println("ERROR: size_increase_btn is null in initialize!");
        }
        
        if (size_decrease_btn != null) {
            size_decrease_btn.setVisible(true);
            size_decrease_btn.setDisable(false);
            System.out.println("Size decrease button (menu) initialized and visible");
        } else {
            System.err.println("ERROR: size_decrease_btn is null in initialize!");
        }
        
        // Initialize size buttons (sketch area)
        if (sketch_size_increase_btn != null) {
            sketch_size_increase_btn.setVisible(true);
            sketch_size_increase_btn.setDisable(false);
            System.out.println("Size increase button (sketch) initialized and visible");
        } else {
            System.err.println("ERROR: sketch_size_increase_btn is null in initialize!");
        }
        
        if (sketch_size_decrease_btn != null) {
            sketch_size_decrease_btn.setVisible(true);
            sketch_size_decrease_btn.setDisable(false);
            System.out.println("Size decrease button (sketch) initialized and visible");
        } else {
            System.err.println("ERROR: sketch_size_decrease_btn is null in initialize!");
        }

        // Clip the sketch area so oversized/skewed images cannot draw outside the canvas
        if (sketch != null) {
            // Enforce intended sketch size so it doesn't expand and overlap other UI
            sketch.setPrefSize(620, 620);
            sketch.setMinSize(620, 620);
            sketch.setMaxSize(620, 620);

            javafx.scene.shape.Rectangle clipRect = new javafx.scene.shape.Rectangle();
            clipRect.setWidth(620);
            clipRect.setHeight(620);
            // Also bind clip to sketch size in case layout changes
            clipRect.widthProperty().bind(sketch.widthProperty());
            clipRect.heightProperty().bind(sketch.heightProperty());
            sketch.setClip(clipRect);
            System.out.println("Set fixed size and clip on sketch area to prevent overflow of images");
        }
        
        // Load elements dynamically
        loadElementsDynamically();
        
        // Setup drag handlers for dynamically created elements
        setupDragHandlers();
        
        // Keep existing drag setup for any hardcoded elements
        dragSketch();
        
        // Bring size buttons to front so they're always visible
        if (sketch_size_increase_btn != null) {
            sketch_size_increase_btn.toFront();
        }
        if (sketch_size_decrease_btn != null) {
            sketch_size_decrease_btn.toFront();
        }
        // Also bring the labels to front
        if (sketch != null) {
            sketch.getChildren().forEach(child -> {
                if (child instanceof Label && (child.getId() == null || child.getId().contains("SIZE"))) {
                    child.toFront();
                }
            });
        }

        // Ensure resize controls are visible, interactive and always on top
        Rectangle[] resizeControls = new Rectangle[]{size_increase_btn, size_decrease_btn, sketch_size_increase_btn, sketch_size_decrease_btn};
        for (Rectangle rc : resizeControls) {
            if (rc != null) {
                rc.setVisible(true);
                rc.setDisable(false);
                rc.setManaged(true);
                rc.setMouseTransparent(false);
                rc.setOpacity(1.0);
                rc.toFront();
                rc.setPickOnBounds(true);

                // Attach a debug click handler so clicks are logged even if FXML handler isn't firing
                rc.setOnMousePressed(evt -> {
                    System.out.println("DEBUG: Resize control clicked -> id=" + rc.getId() + " source=" + evt.getSource());
                });

                // Print debug state
                try {
                    String parentId = (rc.getParent() != null && rc.getParent().getId() != null) ? rc.getParent().getId() : String.valueOf(rc.getParent());
                    System.out.println(String.format("DEBUG: Control '%s' visible=%b managed=%b mouseTransparent=%b opacity=%.2f layout=(%.1f,%.1f) translate=(%.1f,%.1f) parent=%s bounds=%s",
                            rc.getId(), rc.isVisible(), rc.isManaged(), rc.isMouseTransparent(), rc.getOpacity(), rc.getLayoutX(), rc.getLayoutY(), rc.getTranslateX(), rc.getTranslateY(), parentId, rc.getBoundsInParent().toString()));
                } catch (Exception e) {
                    System.out.println("DEBUG: Could not print control bounds for " + rc.getId() + ": " + e.getMessage());
                }
            }
        }
        // Also ensure menu_tab is on top for visibility
        if (menu_tab != null) {
            menu_tab.toFront();
            System.out.println("DEBUG: menu_tab layoutY=" + menu_tab.getLayoutY() + " visible=" + menu_tab.isVisible());
        }

        // Fallback: if FXML didn't inject controls, create them programmatically
        try {
            if (size_increase_btn == null && menu_tab != null) {
                Rectangle r = new Rectangle(45, 45, Color.web("#1F93FF"));
                r.setArcWidth(5);
                r.setArcHeight(5);
                r.setLayoutX(720);
                r.setLayoutY(21);
                r.setId("size_increase_btn");
                r.setOnMousePressed(evt -> onSizeIncrease(null));
                menu_tab.getChildren().add(r);
                size_increase_btn = r;
                System.out.println("DEBUG: created fallback size_increase_btn");
            }

            if (size_decrease_btn == null && menu_tab != null) {
                Rectangle r = new Rectangle(45, 45, Color.web("#1F93FF"));
                r.setArcWidth(5);
                r.setArcHeight(5);
                r.setLayoutX(775);
                r.setLayoutY(21);
                r.setId("size_decrease_btn");
                r.setOnMousePressed(evt -> onSizeDecrease(null));
                menu_tab.getChildren().add(r);
                size_decrease_btn = r;
                System.out.println("DEBUG: created fallback size_decrease_btn");
            }

            if (sizeSlider == null && menu_tab != null) {
                Slider s = new Slider(20, 300, 100);
                s.setLayoutX(830);
                s.setLayoutY(30);
                s.setPrefWidth(180);
                s.valueProperty().addListener((obs, oldV, newV) -> onSizeSliderChanged());
                menu_tab.getChildren().add(s);
                sizeSlider = s;
                System.out.println("DEBUG: created fallback sizeSlider");
            }

            if (sizeLabel == null && menu_tab != null) {
                Label lbl = new Label("100%");
                lbl.setLayoutX(1030);
                lbl.setLayoutY(33);
                menu_tab.getChildren().add(lbl);
                sizeLabel = lbl;
                System.out.println("DEBUG: created fallback sizeLabel");
            }

            if (sketch_size_increase_btn == null && sketch != null) {
                Rectangle r2 = new Rectangle(50,50, Color.web("#1F93FF"));
                r2.setArcWidth(8);
                r2.setArcHeight(8);
                r2.setLayoutX(70);
                r2.setLayoutY(5);
                r2.setId("sketch_size_increase_btn");
                r2.setOnMousePressed(evt -> onSizeIncrease(null));
                sketch.getChildren().add(r2);
                sketch_size_increase_btn = r2;
                System.out.println("DEBUG: created fallback sketch_size_increase_btn");
            }

            if (sketch_size_decrease_btn == null && sketch != null) {
                Rectangle r2 = new Rectangle(50,50, Color.web("#1F93FF"));
                r2.setArcWidth(8);
                r2.setArcHeight(8);
                r2.setLayoutX(130);
                r2.setLayoutY(5);
                r2.setId("sketch_size_decrease_btn");
                r2.setOnMousePressed(evt -> onSizeDecrease(null));
                sketch.getChildren().add(r2);
                sketch_size_decrease_btn = r2;
                System.out.println("DEBUG: created fallback sketch_size_decrease_btn");
            }
        } catch (Exception ex) {
            System.out.println("DEBUG: error creating fallback controls: " + ex.getMessage());
        }
    }
    /**
     * Dynamically load all sketch elements from folders
     */
    private void loadElementsDynamically() {
        System.out.println("=== Starting dynamic element loading ===");
        // Clear existing ImageView elements from containers to avoid duplicates
        clearHardcodedElements();
        
        // IMPORTANT: Load HEAD first so it's at the bottom layer (z-index -1)
        System.out.println("\n--- Loading HEAD elements (bottom layer) ---");
        loadElementType("head", head_elements, "head_e_", "head_s_");
        
        // Then load all other elements (they will be on top)
        System.out.println("\n--- Loading HAIR elements ---");
        loadElementType("hair", hair_elements, "hair_e_", "hair_s_");
        System.out.println("\n--- Loading EYES elements ---");
        loadElementType("eyes", eyes_elements, "eyes_e_", "eyes_s_");
        System.out.println("\n--- Loading EYEBROWS elements ---");
        loadElementType("eyebrows", eyebrows_elements, "eyeb_e_", "eyeb_s_");
        System.out.println("\n--- Loading NOSE elements ---");
        loadElementType("nose", nose_elements, "nose_e_", "nose_s_");
        System.out.println("\n--- Loading LIPS elements ---");
        loadElementType("lips", lips_elements, "lips_e_", "lips_s_");
        System.out.println("\n--- Loading MUSTACHE elements ---");
        loadElementType("mustach", mustach_elements, "must_e_", "must_s_");
        System.out.println("\n--- Loading MORE elements ---");
        loadElementType("more", more_elements, "more_e_", "more_s_");
        
        // Ensure all head elements are at the back
        ensureHeadAtBack();
        
        // Bring size buttons to front after all elements are loaded
        if (sketch_size_increase_btn != null) {
            sketch_size_increase_btn.toFront();
            System.out.println("Brought size increase button to front after loading");
        }
        if (sketch_size_decrease_btn != null) {
            sketch_size_decrease_btn.toFront();
            System.out.println("Brought size decrease button to front after loading");
        }
        
        System.out.println("\n=== Dynamic element loading complete ===");
    }
    
    /**
     * Ensure all head sketch elements are at the back (lowest z-index)
     */
    private void ensureHeadAtBack() {
        List<ImageView> headSketches = sketchImageViews.get("head");
        if (headSketches != null) {
            for (ImageView headSketch : headSketches) {
                headSketch.toBack();
            }
        }
        // Also handle hardcoded head elements
        if (head_s_1 != null) head_s_1.toBack();
        if (head_s_2 != null) head_s_2.toBack();
        if (head_s_3 != null) head_s_3.toBack();
        if (head_s_4 != null) head_s_4.toBack();
        if (head_s_5 != null) head_s_5.toBack();
        if (head_s_6 != null) head_s_6.toBack();
        if (head_s_7 != null) head_s_7.toBack();
        if (head_s_8 != null) head_s_8.toBack();
        if (head_s_9 != null) head_s_9.toBack();
        if (head_s_10 != null) head_s_10.toBack();
    }
    
    /**
     * Load elements for a specific type
     * Group images are shown in selection sidebar, numbered images (01, 02, etc.) are shown on canvas
     */
    private void loadElementType(String elementType, AnchorPane container, String elementPrefix, String sketchPrefix) {
        // Get Group images for selection sidebar
        List<String> groupImages = DynamicElementLoader.loadElementFiles(elementType, getClass(), true);
        // Get numbered images for canvas
        List<String> numberedImages = DynamicElementLoader.loadElementFiles(elementType, getClass(), false);
        
        System.out.println("Loading " + elementType + ":");
        System.out.println("  Group images found: " + groupImages.size() + " - " + groupImages);
        System.out.println("  Numbered images found: " + numberedImages.size() + " - " + numberedImages);
        
        List<ImageView> elementViews = new ArrayList<>();
        List<ImageView> sketchViews = new ArrayList<>();
        Map<ImageView, ImageView> elementToSketch = new HashMap<>();
        
        // Grid layout parameters
        int columns = 3;
        double imageSize = 100.0;
        double spacingX = 135.0;
        double spacingY = 134.0;
        double startX = 30.0;
        double startY = 129.0;
        
        // Create sketch ImageViews for all numbered images first
        Map<String, ImageView> numberedImageMap = new HashMap<>();
        for (int i = 0; i < numberedImages.size(); i++) {
            String numberedImageName = numberedImages.get(i);
            ImageView sketchView = createSketchImageView(elementType, numberedImageName, sketchPrefix + (i + 1), elementType);
            
            // Set default position for this component type
            double[] defaultPos = DEFAULT_POSITIONS.get(elementType.toLowerCase());
            if (defaultPos != null) {
                sketchView.setLayoutX(defaultPos[0]);
                sketchView.setLayoutY(defaultPos[1]);
            }
            
            // If it's a head component, add it first (so it's at the bottom/z-index -1)
            // Otherwise add it normally (on top)
            if (elementType.equalsIgnoreCase("head")) {
                sketch.getChildren().add(0, sketchView); // Add at beginning (bottom layer)
            } else {
                sketch.getChildren().add(sketchView); // Add at end (top layer)
            }
            
            sketchViews.add(sketchView);
            numberedImageMap.put(numberedImageName, sketchView);
        }
        
        // After adding all sketch views, ensure buttons are on top
        if (sketch_size_increase_btn != null) {
            sketch_size_increase_btn.toFront();
        }
        if (sketch_size_decrease_btn != null) {
            sketch_size_decrease_btn.toFront();
        }
        
        // Create element ImageViews for Group images and map them to numbered images
        // First tries exact number match (Group 101 -> 101), then falls back to positional mapping
        for (int i = 0; i < groupImages.size(); i++) {
            String groupImageName = groupImages.get(i);
            
            // Calculate position in grid
            int col = i % columns;
            int row = i / columns;
            double x = startX + (col * spacingX);
            double y = startY + (row * spacingY);
            
            // Try to find exact number match first
            String correspondingNumberedImage = mapGroupToNumbered(groupImageName, numberedImages);
            
            // If no exact match, use positional mapping (first Group -> first numbered, etc.)
            if (correspondingNumberedImage == null) {
                if (i < numberedImages.size()) {
                    correspondingNumberedImage = numberedImages.get(i);
                    System.out.println("Using positional mapping: " + groupImageName + " (index " + i + ") -> " + correspondingNumberedImage);
                } else if (!numberedImages.isEmpty()) {
                    // If more Groups than numbered images, use the last numbered image
                    correspondingNumberedImage = numberedImages.get(numberedImages.size() - 1);
                    System.out.println("Using last numbered image for " + groupImageName + " -> " + correspondingNumberedImage);
                } else {
                    System.out.println("Warning: No numbered images available for " + groupImageName + ". Skipping.");
                    continue; // Skip this Group image if no numbered images exist
                }
            } else {
                System.out.println("Exact match found: " + groupImageName + " -> " + correspondingNumberedImage);
            }
            
            // Decide which image file to use for the selection panel thumbnail.
            // Prefer the numbered image (same one used on the sketch) so thumbnails
            // match the canvas image and do not include unexpected background/bounds.
            String thumbnailImageToUse = correspondingNumberedImage != null ? correspondingNumberedImage : groupImageName;
            ImageView elementView = createElementImageView(elementType, thumbnailImageToUse, x, y, imageSize, elementPrefix + (i + 1), elementType);
            // Store the Group and numbered names as properties for debugging
            elementView.getProperties().put("groupImageName", groupImageName);
            elementView.getProperties().put("numberedImageName", correspondingNumberedImage);
            System.out.println("Thumbnail for " + groupImageName + " will use file: " + thumbnailImageToUse);
            container.getChildren().add(elementView);
            elementViews.add(elementView);
            
            // Map Group image view to corresponding numbered sketch view
            if (numberedImageMap.containsKey(correspondingNumberedImage)) {
                ImageView sketchView = numberedImageMap.get(correspondingNumberedImage);
                elementToSketch.put(elementView, sketchView);
            } else {
                System.out.println("Error: Numbered image '" + correspondingNumberedImage + "' not found in numberedImageMap for " + groupImageName);
            }
        }
        
        // Store references
        elementImageViews.put(elementType, elementViews);
        sketchImageViews.put(elementType, sketchViews);
        elementToSketchMap.put(elementType, elementToSketch);
    }
    
    /**
     * Maps a Group image name to a corresponding numbered image
     * First tries exact number match (Group 101 -> "101", Group 17 -> "17")
     * If no exact match, falls back to positional mapping (first Group -> first numbered, etc.)
     */
    private String mapGroupToNumbered(String groupImageName, List<String> numberedImages) {
        try {
            // Extract number from Group name (e.g., "Group 1", "Group 17", "Group 101")
            String numberStr = groupImageName.replace("Group", "").trim();
            
            if (numberStr.isEmpty() || numberedImages.isEmpty()) {
                return null;
            }
            
            int groupNumber = Integer.parseInt(numberStr);
            
            // Priority 1: Try exact number match as string (17 -> "17", 101 -> "101")
            String directNumber = String.valueOf(groupNumber);
            if (numberedImages.contains(directNumber)) {
                return directNumber;
            }
            
            // Priority 2: Try zero-padded 2-digit format (01, 02, etc.)
            if (groupNumber < 100) {
                String padded02 = String.format("%02d", groupNumber);
                if (numberedImages.contains(padded02)) {
                    return padded02;
                }
            }
            
            // Priority 3: Try 3-digit padding for numbers >= 100 (101 -> "101")
            if (groupNumber >= 100 && groupNumber < 1000) {
                String padded03 = String.format("%03d", groupNumber);
                if (numberedImages.contains(padded03)) {
                    return padded03;
                }
            }
            
            // Priority 4: Try to find by parsing numbered images and matching numeric value
            for (String numbered : numberedImages) {
                try {
                    int numberedValue = Integer.parseInt(numbered);
                    if (numberedValue == groupNumber) {
                        return numbered; // Return the actual filename format found
                    }
                } catch (NumberFormatException e) {
                    // Skip non-numeric numbered images
                }
            }
            
            // Priority 5: If no exact match, use positional mapping
            // Find the index of this Group in the sorted list and map to same index in numbered images
            // This is a fallback - it will be handled by the caller using index-based mapping
            
            return null; // Return null to indicate no exact match, caller will use index
            
        } catch (NumberFormatException e) {
            System.out.println("Error parsing Group number from: " + groupImageName);
            return null;
        }
    }
    
    /**
     * Create an ImageView for the element selection panel
     */
    private ImageView createElementImageView(String elementType, String imageName, double x, double y, double size, String id, String type) {
        ImageView imageView = new ImageView();
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        imageView.setPreserveRatio(true);
        imageView.setPickOnBounds(true);
        imageView.setId(id);
        imageView.setCursor(Cursor.HAND);
        
        // Load image - try multiple extensions
        try {
            String[] extensions = {".png", ".jpg", ".jpeg"};
            Image loadedImage = null;
            for (String ext : extensions) {
                String imagePath = "elements/sketch elements/" + elementType + "/" + imageName + ext;
                URL imageUrl = getClass().getResource(imagePath);
                if (imageUrl != null) {
                    loadedImage = new Image(imageUrl.toExternalForm());
                    break; // Found the image, stop trying other extensions
                }
            }
            if (loadedImage != null) {
                imageView.setImage(loadedImage);
            } else {
                System.err.println("Error: Could not find image " + imageName + " with any extension (.png, .jpg, .jpeg)");
            }
        } catch (Exception e) {
            System.err.println("Error loading element image " + imageName + ": " + e.getMessage());
        }
        
        // Set event handler based on element type to call appropriate selection method
        final String finalType = elementType;
        imageView.setOnMousePressed(event -> {
            switch(finalType.toLowerCase()) {
                case "hair":
                    onHairSelect(event);
                    break;
                case "head":
                    onHeadSelect(event);
                    break;
                case "eyes":
                    onEyesSelect(event);
                    break;
                case "eyebrows":
                    onEyeBSelect(event);
                    break;
                case "nose":
                    onNoseSelect(event);
                    break;
                case "lips":
                    onLipsSelect(event);
                    break;
                case "mustach":
                    onMustSelect(event);
                    break;
                case "more":
                    onMoreSelect(event);
                    break;
            }
        });
        
        return imageView;
    }
    
    /**
     * Create an ImageView for the sketch canvas
     */
    private ImageView createSketchImageView(String elementType, String imageName, String id, String type) {
        ImageView sketchView = new ImageView();
        sketchView.setPreserveRatio(true);
        sketchView.setPickOnBounds(true);
        sketchView.setId(id);
        sketchView.setVisible(false); // Initially hidden
        
        // Set default position for this component type
        double[] defaultPos = DEFAULT_POSITIONS.get(elementType.toLowerCase());
        if (defaultPos != null) {
            sketchView.setLayoutX(defaultPos[0]);
            sketchView.setLayoutY(defaultPos[1]);
        }
        
        // Set initial size based on element type (will be adjusted when image loads)
        // Default sizes for different component types
        Map<String, double[]> defaultSizes = new HashMap<>();
        defaultSizes.put("head", new double[]{288, 464});
        defaultSizes.put("hair", new double[]{400, 500});
        defaultSizes.put("eyes", new double[]{150, 80});
        defaultSizes.put("eyebrows", new double[]{200, 60});
        defaultSizes.put("nose", new double[]{100, 120});
        defaultSizes.put("lips", new double[]{120, 60});
        defaultSizes.put("mustach", new double[]{200, 80});
        defaultSizes.put("more", new double[]{100, 100});
        
        double[] size = defaultSizes.get(elementType.toLowerCase());
        if (size != null) {
            sketchView.setFitWidth(size[0]);
            sketchView.setFitHeight(size[1]);
        } else {
            // Default size if type not found
            sketchView.setFitWidth(200);
            sketchView.setFitHeight(200);
        }
        
        // Load image - try multiple extensions
        try {
            String[] extensions = {".png", ".jpg", ".jpeg"};
            Image loadedImage = null;
            for (String ext : extensions) {
                String imagePath = "elements/sketch elements/" + elementType + "/" + imageName + ext;
                URL imageUrl = getClass().getResource(imagePath);
                if (imageUrl != null) {
                    loadedImage = new Image(imageUrl.toExternalForm());
                    break; // Found the image, stop trying other extensions
                }
            }
            if (loadedImage != null) {
                sketchView.setImage(loadedImage);
                
                // Store original dimensions for zooming (use actual image dimensions)
                double origWidth = loadedImage.getWidth();
                double origHeight = loadedImage.getHeight();
                
                // If fitWidth/fitHeight are set, use those as original
                if (sketchView.getFitWidth() > 0) {
                    origWidth = sketchView.getFitWidth();
                }
                if (sketchView.getFitHeight() > 0) {
                    origHeight = sketchView.getFitHeight();
                }
                
                originalDimensions.put(sketchView, new double[]{origWidth, origHeight});
            } else {
                System.err.println("Error: Could not find sketch image " + imageName + " with any extension (.png, .jpg, .jpeg)");
            }
        } catch (Exception e) {
            System.err.println("Error loading sketch image " + imageName + ": " + e.getMessage());
        }
        
        return sketchView;
    }
    
    /**
     * Clear hardcoded element ImageViews from containers to avoid conflicts with dynamic ones
     */
    private void clearHardcodedElements() {
        // Remove existing ImageView elements from each container
        // Keep non-ImageView children (like Labels, Rectangles)
        hair_elements.getChildren().removeIf(node -> 
            node instanceof ImageView && node.getId() != null && node.getId().startsWith("hair_e_"));
        head_elements.getChildren().removeIf(node -> 
            node instanceof ImageView && node.getId() != null && node.getId().startsWith("head_e_"));
        eyes_elements.getChildren().removeIf(node -> 
            node instanceof ImageView && node.getId() != null && node.getId().startsWith("eyes_e_"));
        eyebrows_elements.getChildren().removeIf(node -> 
            node instanceof ImageView && node.getId() != null && node.getId().startsWith("eyeb_e_"));
        nose_elements.getChildren().removeIf(node -> 
            node instanceof ImageView && node.getId() != null && node.getId().startsWith("nose_e_"));
        lips_elements.getChildren().removeIf(node -> 
            node instanceof ImageView && node.getId() != null && node.getId().startsWith("lips_e_"));
        mustach_elements.getChildren().removeIf(node -> 
            node instanceof ImageView && node.getId() != null && node.getId().startsWith("must_e_"));
        more_elements.getChildren().removeIf(node -> 
            node instanceof ImageView && node.getId() != null && node.getId().startsWith("more_e_"));
        
        // Note: Sketch ImageViews (s_*) are kept in the sketch container
        // They can coexist with dynamic ones or be replaced
    }
    
    /**
     * Generic handler for element selection (called from dynamically created ImageViews)
     */
    private void handleElementSelect(MouseEvent event, String elementType) {
        if (event.getSource() instanceof ImageView) {
            ImageView clickedView = (ImageView) event.getSource();
            
            // Get debug info
            String groupName = (String) clickedView.getProperties().get("groupImageName");
            String numberedName = (String) clickedView.getProperties().get("numberedImageName");
            System.out.println("Clicked: " + groupName + " -> should show: " + numberedName);
            
            // Find the corresponding sketch view
            Map<ImageView, ImageView> mapping = elementToSketchMap.get(elementType);
            if (mapping != null) {
                ImageView sketchView = mapping.get(clickedView);
                if (sketchView != null) {
                    System.out.println("Found sketch view, showing it now");
                    
                    // Hide all other sketches of this type, show selected one
                    List<ImageView> sketches = sketchImageViews.get(elementType);
                    if (sketches != null) {
                        for (ImageView sketch : sketches) {
                            if (sketch == sketchView) {
                                sketch.setVisible(true);
                                // Track currently selected component (except head)
                                if (!elementType.equalsIgnoreCase("head")) {
                                    currentlySelectedComponent = sketch;
                                    currentlySelectedType = elementType;
                                    
                                    // Update slider to reflect current size
                                    if (sizeSlider != null) {
                                        // Store original dimensions if not already stored
                                        if (!originalDimensions.containsKey(sketch)) {
                                            double origWidth = sketch.getFitWidth() > 0 ? sketch.getFitWidth() : (sketch.getImage() != null ? sketch.getImage().getWidth() : 300);
                                            double origHeight = sketch.getFitHeight() > 0 ? sketch.getFitHeight() : (sketch.getImage() != null ? sketch.getImage().getHeight() : 300);
                                            originalDimensions.put(sketch, new double[]{origWidth, origHeight});
                                        }
                                        
                                        // Calculate current percentage based on original dimensions
                                        double[] origDims = originalDimensions.get(sketch);
                                        double currentWidth = sketch.getFitWidth() > 0 ? sketch.getFitWidth() : origDims[0];
                                        double currentPercentage = (currentWidth / origDims[0]) * 100.0;
                                        
                                        // Clamp to slider range (20-300)
                                        currentPercentage = Math.max(20, Math.min(300, currentPercentage));
                                        sizeSlider.setValue(currentPercentage);
                                        if (sizeLabel != null) {
                                            sizeLabel.setText(String.format("%.0f%%", currentPercentage));
                                        }
                                    }
                                } else {
                                    currentlySelectedComponent = null;
                                    currentlySelectedType = null;
                                    // Reset slider when no component selected or head selected
                                    if (sizeSlider != null) {
                                        sizeSlider.setValue(100);
                                        if (sizeLabel != null) {
                                            sizeLabel.setText("100%");
                                        }
                                    }
                                }
                                
                                // Reset to default position for this component type
                                double[] defaultPos = DEFAULT_POSITIONS.get(elementType.toLowerCase());
                                if (defaultPos != null) {
                                    sketch.setLayoutX(defaultPos[0]);
                                    sketch.setLayoutY(defaultPos[1]);
                                    sketch.setTranslateX(0); // Reset any drag translations
                                    sketch.setTranslateY(0);
                                }
                                
                                // Reset size when selecting new component (restore original dimensions)
                                if (originalDimensions.containsKey(sketch)) {
                                    double[] origDims = originalDimensions.get(sketch);
                                    sketch.setFitWidth(origDims[0]);
                                    sketch.setFitHeight(origDims[1]);
                                } else {
                                    // Store original dimensions for first time
                                    double origWidth = sketch.getFitWidth() > 0 ? sketch.getFitWidth() : 
                                                      (sketch.getImage() != null ? sketch.getImage().getWidth() : 100);
                                    double origHeight = sketch.getFitHeight() > 0 ? sketch.getFitHeight() : 
                                                       (sketch.getImage() != null ? sketch.getImage().getHeight() : 100);
                                    originalDimensions.put(sketch, new double[]{origWidth, origHeight});
                                }
                                
                                // Bring to front (except head which should stay at back)
                                if (!elementType.equalsIgnoreCase("head")) {
                                    sketch.toFront();
                                } else {
                                    sketch.toBack(); // Head always at back
                                }
                                
                                // Always keep size buttons on top
                                if (sketch_size_increase_btn != null) {
                                    sketch_size_increase_btn.toFront();
                                }
                                if (sketch_size_decrease_btn != null) {
                                    sketch_size_decrease_btn.toFront();
                                }
                            } else {
                                sketch.setVisible(false);
                            }
                        }
                    }
                } else {
                    System.out.println("Error: No sketch view mapped for clicked element");
                }
            } else {
                System.out.println("Error: No mapping found for element type: " + elementType);
            }
        }
    }
    
    /**
     * Handle dynamic element selection (for use in onHairSelect, onHeadSelect, etc.)
     * @return true if handled dynamically, false otherwise
     */
    private boolean handleDynamicElementSelect(MouseEvent event, String elementType) {
        if (event.getSource() instanceof ImageView) {
            ImageView clickedView = (ImageView) event.getSource();
            
            // Check if it's one of our dynamically created element views
            List<ImageView> elementViews = elementImageViews.get(elementType);
            if (elementViews != null && elementViews.contains(clickedView)) {
                handleElementSelect(event, elementType);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Hide all sketch elements of a specific type
     */
    private void hideAllSketchElements(String elementType) {
        List<ImageView> sketches = sketchImageViews.get(elementType);
        if (sketches != null) {
            for (ImageView sketch : sketches) {
                sketch.setVisible(false);
            }
        }
    }
    
    /**
     * Get delete button for element type
     */
    private Rectangle getDeleteButton(String elementType) {
        switch (elementType.toLowerCase()) {
            case "hair": return hair_del;
            case "head": return head_del;
            case "eyes": return eyes_del;
            case "eyebrows": return eyeb_del;
            case "nose": return nose_del;
            case "lips": return lips_del;
            case "mustach": return must_del;
            case "more": return more_del;
            default: return null;
        }
    }
    
    /**
     * Setup drag handlers for dynamically created sketch elements
     */
    private void setupDragHandlers() {
        for (List<ImageView> sketches : sketchImageViews.values()) {
            for (ImageView sketch : sketches) {
                sketch.setOnMouseDragged(event -> drag(event));
            }
        }
    }
    
    /**
     * Show a sketch view and position it at default location with proper z-ordering
     */
    private void showSketchAtDefaultPosition(ImageView sketchView, String elementType) {
        if (sketchView == null) return;
        
        sketchView.setVisible(true);
        
        // Track currently selected component (except head)
        if (!elementType.equalsIgnoreCase("head")) {
            currentlySelectedComponent = sketchView;
            currentlySelectedType = elementType;
            
            // Update slider to reflect current size
            if (sizeSlider != null) {
                // Store original dimensions if not already stored
                if (!originalDimensions.containsKey(sketchView)) {
                    double origWidth = sketchView.getFitWidth() > 0 ? sketchView.getFitWidth() : (sketchView.getImage() != null ? sketchView.getImage().getWidth() : 300);
                    double origHeight = sketchView.getFitHeight() > 0 ? sketchView.getFitHeight() : (sketchView.getImage() != null ? sketchView.getImage().getHeight() : 300);
                    originalDimensions.put(sketchView, new double[]{origWidth, origHeight});
                }
                
                // Calculate current percentage based on original dimensions
                double[] origDims = originalDimensions.get(sketchView);
                double currentWidth = sketchView.getFitWidth() > 0 ? sketchView.getFitWidth() : origDims[0];
                double currentPercentage = (currentWidth / origDims[0]) * 100.0;
                
                // Clamp to slider range (20-300)
                currentPercentage = Math.max(20, Math.min(300, currentPercentage));
                sizeSlider.setValue(currentPercentage);
                if (sizeLabel != null) {
                    sizeLabel.setText(String.format("%.0f%%", currentPercentage));
                }
            }
        } else {
            currentlySelectedComponent = null;
            currentlySelectedType = null;
            // Reset slider when no component selected or head selected
            if (sizeSlider != null) {
                sizeSlider.setValue(100);
                if (sizeLabel != null) {
                    sizeLabel.setText("100%");
                }
            }
        }
        
        // Reset to default position for this component type
        double[] defaultPos = DEFAULT_POSITIONS.get(elementType.toLowerCase());
        if (defaultPos != null) {
            sketchView.setLayoutX(defaultPos[0]);
            sketchView.setLayoutY(defaultPos[1]);
            sketchView.setTranslateX(0); // Reset any drag translations
            sketchView.setTranslateY(0);
        }
        
        // Store original dimensions for zooming (if not already stored)
        if (!originalDimensions.containsKey(sketchView)) {
            // Get original size from image or use current fitWidth/fitHeight
            double origWidth = sketchView.getFitWidth() > 0 ? sketchView.getFitWidth() : (sketchView.getImage() != null ? sketchView.getImage().getWidth() : 300);
            double origHeight = sketchView.getFitHeight() > 0 ? sketchView.getFitHeight() : (sketchView.getImage() != null ? sketchView.getImage().getHeight() : 300);
            originalDimensions.put(sketchView, new double[]{origWidth, origHeight});
        }
        
        // Z-ordering: Head always at back, others at front
        if (elementType.equalsIgnoreCase("head")) {
            sketchView.toBack(); // Head at bottom layer
        } else {
            sketchView.toFront(); // Other components on top
        }
        
        // Always keep size buttons on top
        if (sketch_size_increase_btn != null) {
            sketch_size_increase_btn.toFront();
        }
        if (sketch_size_decrease_btn != null) {
            sketch_size_decrease_btn.toFront();
        }
    }
    
    /**
     * Get currently visible/selected component (for zooming) - excludes head
     */
    private ImageView getCurrentlySelectedComponent() {
        // Check dynamic components first
        if (currentlySelectedComponent != null && currentlySelectedComponent.isVisible()) {
            String id = currentlySelectedComponent.getId();
            if (id != null && !id.startsWith("head_s_")) {
                return currentlySelectedComponent;
            }
        }
        
        // Check all dynamic sketch views (except head)
        for (Map.Entry<String, List<ImageView>> entry : sketchImageViews.entrySet()) {
            String elementType = entry.getKey();
            if (!elementType.equalsIgnoreCase("head")) {
                List<ImageView> sketches = entry.getValue();
                if (sketches != null) {
                    for (ImageView sketch : sketches) {
                        if (sketch != null && sketch.isVisible()) {
                            return sketch;
                        }
                    }
                }
            }
        }
        
        // Check hardcoded components (except head)
        // Check hair
        if (hair_s_1 != null && hair_s_1.isVisible()) return hair_s_1;
        if (hair_s_2 != null && hair_s_2.isVisible()) return hair_s_2;
        if (hair_s_3 != null && hair_s_3.isVisible()) return hair_s_3;
        if (hair_s_4 != null && hair_s_4.isVisible()) return hair_s_4;
        if (hair_s_5 != null && hair_s_5.isVisible()) return hair_s_5;
        if (hair_s_6 != null && hair_s_6.isVisible()) return hair_s_6;
        if (hair_s_7 != null && hair_s_7.isVisible()) return hair_s_7;
        if (hair_s_8 != null && hair_s_8.isVisible()) return hair_s_8;
        if (hair_s_9 != null && hair_s_9.isVisible()) return hair_s_9;
        if (hair_s_10 != null && hair_s_10.isVisible()) return hair_s_10;
        if (hair_s_11 != null && hair_s_11.isVisible()) return hair_s_11;
        if (hair_s_12 != null && hair_s_12.isVisible()) return hair_s_12;
        
        // Check eyes
        if (eyes_s_1 != null && eyes_s_1.isVisible()) return eyes_s_1;
        if (eyes_s_2 != null && eyes_s_2.isVisible()) return eyes_s_2;
        if (eyes_s_3 != null && eyes_s_3.isVisible()) return eyes_s_3;
        if (eyes_s_4 != null && eyes_s_4.isVisible()) return eyes_s_4;
        if (eyes_s_5 != null && eyes_s_5.isVisible()) return eyes_s_5;
        if (eyes_s_6 != null && eyes_s_6.isVisible()) return eyes_s_6;
        if (eyes_s_7 != null && eyes_s_7.isVisible()) return eyes_s_7;
        if (eyes_s_8 != null && eyes_s_8.isVisible()) return eyes_s_8;
        if (eyes_s_9 != null && eyes_s_9.isVisible()) return eyes_s_9;
        if (eyes_s_10 != null && eyes_s_10.isVisible()) return eyes_s_10;
        if (eyes_s_11 != null && eyes_s_11.isVisible()) return eyes_s_11;
        if (eyes_s_12 != null && eyes_s_12.isVisible()) return eyes_s_12;
        
        // Check eyebrows
        if (eyeb_s_1 != null && eyeb_s_1.isVisible()) return eyeb_s_1;
        if (eyeb_s_2 != null && eyeb_s_2.isVisible()) return eyeb_s_2;
        if (eyeb_s_3 != null && eyeb_s_3.isVisible()) return eyeb_s_3;
        if (eyeb_s_4 != null && eyeb_s_4.isVisible()) return eyeb_s_4;
        if (eyeb_s_5 != null && eyeb_s_5.isVisible()) return eyeb_s_5;
        if (eyeb_s_6 != null && eyeb_s_6.isVisible()) return eyeb_s_6;
        if (eyeb_s_7 != null && eyeb_s_7.isVisible()) return eyeb_s_7;
        if (eyeb_s_8 != null && eyeb_s_8.isVisible()) return eyeb_s_8;
        if (eyeb_s_9 != null && eyeb_s_9.isVisible()) return eyeb_s_9;
        if (eyeb_s_10 != null && eyeb_s_10.isVisible()) return eyeb_s_10;
        if (eyeb_s_11 != null && eyeb_s_11.isVisible()) return eyeb_s_11;
        if (eyeb_s_12 != null && eyeb_s_12.isVisible()) return eyeb_s_12;
        
        // Check nose
        if (nose_s_1 != null && nose_s_1.isVisible()) return nose_s_1;
        if (nose_s_2 != null && nose_s_2.isVisible()) return nose_s_2;
        if (nose_s_3 != null && nose_s_3.isVisible()) return nose_s_3;
        if (nose_s_4 != null && nose_s_4.isVisible()) return nose_s_4;
        if (nose_s_5 != null && nose_s_5.isVisible()) return nose_s_5;
        if (nose_s_6 != null && nose_s_6.isVisible()) return nose_s_6;
        if (nose_s_7 != null && nose_s_7.isVisible()) return nose_s_7;
        if (nose_s_8 != null && nose_s_8.isVisible()) return nose_s_8;
        if (nose_s_9 != null && nose_s_9.isVisible()) return nose_s_9;
        if (nose_s_10 != null && nose_s_10.isVisible()) return nose_s_10;
        if (nose_s_11 != null && nose_s_11.isVisible()) return nose_s_11;
        if (nose_s_12 != null && nose_s_12.isVisible()) return nose_s_12;
        
        // Check lips
        if (lips_s_1 != null && lips_s_1.isVisible()) return lips_s_1;
        if (lips_s_2 != null && lips_s_2.isVisible()) return lips_s_2;
        if (lips_s_3 != null && lips_s_3.isVisible()) return lips_s_3;
        if (lips_s_4 != null && lips_s_4.isVisible()) return lips_s_4;
        if (lips_s_5 != null && lips_s_5.isVisible()) return lips_s_5;
        if (lips_s_6 != null && lips_s_6.isVisible()) return lips_s_6;
        if (lips_s_7 != null && lips_s_7.isVisible()) return lips_s_7;
        if (lips_s_8 != null && lips_s_8.isVisible()) return lips_s_8;
        if (lips_s_9 != null && lips_s_9.isVisible()) return lips_s_9;
        if (lips_s_10 != null && lips_s_10.isVisible()) return lips_s_10;
        if (lips_s_11 != null && lips_s_11.isVisible()) return lips_s_11;
        if (lips_s_12 != null && lips_s_12.isVisible()) return lips_s_12;
        
        // Check mustache
        if (must_s_1 != null && must_s_1.isVisible()) return must_s_1;
        if (must_s_2 != null && must_s_2.isVisible()) return must_s_2;
        if (must_s_3 != null && must_s_3.isVisible()) return must_s_3;
        if (must_s_4 != null && must_s_4.isVisible()) return must_s_4;
        if (must_s_5 != null && must_s_5.isVisible()) return must_s_5;
        if (must_s_6 != null && must_s_6.isVisible()) return must_s_6;
        if (must_s_7 != null && must_s_7.isVisible()) return must_s_7;
        if (must_s_8 != null && must_s_8.isVisible()) return must_s_8;
        if (must_s_9 != null && must_s_9.isVisible()) return must_s_9;
        if (must_s_10 != null && must_s_10.isVisible()) return must_s_10;
        if (must_s_11 != null && must_s_11.isVisible()) return must_s_11;
        if (must_s_12 != null && must_s_12.isVisible()) return must_s_12;
        
        // Check more
        if (more_s_1 != null && more_s_1.isVisible()) return more_s_1;
        if (more_s_2 != null && more_s_2.isVisible()) return more_s_2;
        if (more_s_3 != null && more_s_3.isVisible()) return more_s_3;
        if (more_s_4 != null && more_s_4.isVisible()) return more_s_4;
        if (more_s_5 != null && more_s_5.isVisible()) return more_s_5;
        if (more_s_6 != null && more_s_6.isVisible()) return more_s_6;
        
        return null; // No non-head component selected
    }
    
    @FXML
    private void onZoomIn(MouseEvent event) {
        ImageView selected = getCurrentlySelectedComponent();
        if (selected != null) {
            // Don't zoom head
            if (selected.getId() != null && selected.getId().startsWith("head_s_")) {
                System.out.println("Cannot zoom head component");
                return;
            }
            
            // Store original dimensions if not already stored
            if (!originalDimensions.containsKey(selected)) {
                double origWidth = selected.getFitWidth() > 0 ? selected.getFitWidth() : selected.getImage().getWidth();
                double origHeight = selected.getFitHeight() > 0 ? selected.getFitHeight() : selected.getImage().getHeight();
                originalDimensions.put(selected, new double[]{origWidth, origHeight});
            }
            
            double[] origDims = originalDimensions.get(selected);
            double currentWidth = selected.getFitWidth() > 0 ? selected.getFitWidth() : origDims[0];
            double currentHeight = selected.getFitHeight() > 0 ? selected.getFitHeight() : origDims[1];
            
            // Increase size by 10%
            double newWidth = currentWidth * 1.1;
            double newHeight = currentHeight * 1.1;
            
            // Limit max zoom to 300% of original
            double maxWidth = origDims[0] * 3.0;
            double maxHeight = origDims[1] * 3.0;
            
            if (newWidth <= maxWidth && newHeight <= maxHeight) {
                // Clamp to sketch bounds to avoid visual overflow
                double[] clamped = clampSizeToSketch(selected, newWidth, newHeight);
                selected.setFitWidth(clamped[0]);
                selected.setFitHeight(clamped[1]);
                System.out.println("Zoomed in: " + selected.getId() + " to " + (clamped[0]/origDims[0]*100) + "%");
            } else {
                System.out.println("Maximum zoom reached (300%)");
            }
        } else {
            System.out.println("No component selected for zooming");
        }
    }
    
    @FXML
    private void onZoomOut(MouseEvent event) {
        ImageView selected = getCurrentlySelectedComponent();
        if (selected != null) {
            // Don't zoom head
            if (selected.getId() != null && selected.getId().startsWith("head_s_")) {
                System.out.println("Cannot zoom head component");
                return;
            }
            
            // Store original dimensions if not already stored
            if (!originalDimensions.containsKey(selected)) {
                double origWidth = selected.getFitWidth() > 0 ? selected.getFitWidth() : selected.getImage().getWidth();
                double origHeight = selected.getFitHeight() > 0 ? selected.getFitHeight() : selected.getImage().getHeight();
                originalDimensions.put(selected, new double[]{origWidth, origHeight});
            }
            
            double[] origDims = originalDimensions.get(selected);
            double currentWidth = selected.getFitWidth() > 0 ? selected.getFitWidth() : origDims[0];
            double currentHeight = selected.getFitHeight() > 0 ? selected.getFitHeight() : origDims[1];
            
            // Decrease size by 10%
            double newWidth = currentWidth * 0.9;
            double newHeight = currentHeight * 0.9;
            
            // Limit min zoom to 20% of original
            double minWidth = origDims[0] * 0.2;
            double minHeight = origDims[1] * 0.2;
            
            if (newWidth >= minWidth && newHeight >= minHeight) {
                double[] clamped = clampSizeToSketch(selected, newWidth, newHeight);
                selected.setFitWidth(clamped[0]);
                selected.setFitHeight(clamped[1]);
                System.out.println("Zoomed out: " + selected.getId() + " to " + (clamped[0]/origDims[0]*100) + "%");
            } else {
                System.out.println("Minimum zoom reached (20%)");
            }
        } else {
            System.out.println("No component selected for zooming");
        }
    }
    
    @FXML
    private void onSizeIncrease(MouseEvent event) {
        ImageView selected = getCurrentlySelectedComponent();
        if (selected != null) {
            // Don't resize head
            if (selected.getId() != null && selected.getId().startsWith("head_s_")) {
                System.out.println("Cannot resize head component");
                return;
            }
            
            // Store original dimensions if not already stored
            if (!originalDimensions.containsKey(selected)) {
                double origWidth = selected.getFitWidth() > 0 ? selected.getFitWidth() : (selected.getImage() != null ? selected.getImage().getWidth() : 300);
                double origHeight = selected.getFitHeight() > 0 ? selected.getFitHeight() : (selected.getImage() != null ? selected.getImage().getHeight() : 300);
                originalDimensions.put(selected, new double[]{origWidth, origHeight});
            }
            
            double[] origDims = originalDimensions.get(selected);
            double currentWidth = selected.getFitWidth() > 0 ? selected.getFitWidth() : origDims[0];
            double currentHeight = selected.getFitHeight() > 0 ? selected.getFitHeight() : origDims[1];
            
            // Increase size by 15%
            double newWidth = currentWidth * 1.15;
            double newHeight = currentHeight * 1.15;
            
            // Limit max size to 300% of original
            double maxWidth = origDims[0] * 3.0;
            double maxHeight = origDims[1] * 3.0;
            
            if (newWidth <= maxWidth && newHeight <= maxHeight) {
                // Clamp to sketch to prevent overflow
                double[] clamped = clampSizeToSketch(selected, newWidth, newHeight);
                selected.setFitWidth(clamped[0]);
                selected.setFitHeight(clamped[1]);
                
                // Update slider if it exists
                if (sizeSlider != null) {
                    double percentage = (newWidth / origDims[0]) * 100.0;
                    percentage = Math.max(20, Math.min(300, percentage));
                    sizeSlider.setValue(percentage);
                    if (sizeLabel != null) {
                        sizeLabel.setText(String.format("%.0f%%", percentage));
                    }
                }
                
                System.out.println("Size increased: " + selected.getId() + " to " + String.format("%.1f", (clamped[0]/origDims[0]*100)) + "%");
            } else {
                System.out.println("Maximum size reached (300%)");
            }
        } else {
            System.out.println("No component selected. Please select a component first.");
        }
    }
    
    @FXML
    private void onSizeDecrease(MouseEvent event) {
        ImageView selected = getCurrentlySelectedComponent();
        if (selected != null) {
            // Don't resize head
            if (selected.getId() != null && selected.getId().startsWith("head_s_")) {
                System.out.println("Cannot resize head component");
                return;
            }
            
            // Store original dimensions if not already stored
            if (!originalDimensions.containsKey(selected)) {
                double origWidth = selected.getFitWidth() > 0 ? selected.getFitWidth() : (selected.getImage() != null ? selected.getImage().getWidth() : 300);
                double origHeight = selected.getFitHeight() > 0 ? selected.getFitHeight() : (selected.getImage() != null ? selected.getImage().getHeight() : 300);
                originalDimensions.put(selected, new double[]{origWidth, origHeight});
            }
            
            double[] origDims = originalDimensions.get(selected);
            double currentWidth = selected.getFitWidth() > 0 ? selected.getFitWidth() : origDims[0];
            double currentHeight = selected.getFitHeight() > 0 ? selected.getFitHeight() : origDims[1];
            
            // Decrease size by 15%
            double newWidth = currentWidth * 0.85;
            double newHeight = currentHeight * 0.85;
            
            // Limit min size to 20% of original
            double minWidth = origDims[0] * 0.2;
            double minHeight = origDims[1] * 0.2;
            
            if (newWidth >= minWidth && newHeight >= minHeight) {
                double[] clamped = clampSizeToSketch(selected, newWidth, newHeight);
                selected.setFitWidth(clamped[0]);
                selected.setFitHeight(clamped[1]);
                
                // Update slider if it exists
                if (sizeSlider != null) {
                    double percentage = (newWidth / origDims[0]) * 100.0;
                    percentage = Math.max(20, Math.min(300, percentage));
                    sizeSlider.setValue(percentage);
                    if (sizeLabel != null) {
                        sizeLabel.setText(String.format("%.0f%%", percentage));
                    }
                }
                
                System.out.println("Size decreased: " + selected.getId() + " to " + String.format("%.1f", (clamped[0]/origDims[0]*100)) + "%");
            } else {
                System.out.println("Minimum size reached (20%)");
            }
        } else {
            System.out.println("No component selected. Please select a component first.");
        }
    }
    
    @FXML
    private void onSizeSliderChanged() {
        if (sizeSlider == null) return;
        
        ImageView selected = getCurrentlySelectedComponent();
        if (selected != null) {
            // Don't resize head
            if (selected.getId() != null && selected.getId().startsWith("head_s_")) {
                System.out.println("Cannot resize head component");
                return;
            }
            
            // Store original dimensions if not already stored
            if (!originalDimensions.containsKey(selected)) {
                double origWidth = selected.getFitWidth() > 0 ? selected.getFitWidth() : (selected.getImage() != null ? selected.getImage().getWidth() : 300);
                double origHeight = selected.getFitHeight() > 0 ? selected.getFitHeight() : (selected.getImage() != null ? selected.getImage().getHeight() : 300);
                originalDimensions.put(selected, new double[]{origWidth, origHeight});
            }
            
            double[] origDims = originalDimensions.get(selected);
            double percentage = sizeSlider.getValue(); // Slider value is already in percentage (20-300)
            
            // Calculate new size based on percentage
            double newWidth = origDims[0] * (percentage / 100.0);
            double newHeight = origDims[1] * (percentage / 100.0);

            double[] clamped = clampSizeToSketch(selected, newWidth, newHeight);
            selected.setFitWidth(clamped[0]);
            selected.setFitHeight(clamped[1]);
            
            // Update size label
            if (sizeLabel != null) {
                sizeLabel.setText(String.format("%.0f%%", percentage));
            }
            
            System.out.println("Slider resized: " + selected.getId() + " to " + String.format("%.1f", percentage) + "% (" + String.format("%.0f", newWidth) + "x" + String.format("%.0f", newHeight) + ")");
        } else {
            // No component selected - disable slider or show message
            if (sizeLabel != null) {
                sizeLabel.setText("Select component");
            }
        }
    }    

    /**
     * Clamp desired image size so it cannot visually overflow the sketch area.
     * Returns [width, height] adjusted to fit within sketch bounds if needed.
     */
    private double[] clampSizeToSketch(ImageView iv, double desiredW, double desiredH) {
        double sketchW = (sketch != null && sketch.getWidth() > 0) ? sketch.getWidth() : sketch.getPrefWidth();
        double sketchH = (sketch != null && sketch.getHeight() > 0) ? sketch.getHeight() : sketch.getPrefHeight();

        // If sketchW/H are not defined yet, fall back to 620 (design default)
        if (sketchW <= 0) sketchW = 620;
        if (sketchH <= 0) sketchH = 620;

        double maxW = sketchW; // do not allow width larger than sketch width
        double maxH = sketchH; // do not allow height larger than sketch height

        double w = Math.min(desiredW, maxW);
        double h = Math.min(desiredH, maxH);

        // Preserve aspect ratio based on original image if present
        if (iv.getImage() != null && iv.isPreserveRatio()) {
            double imgW = iv.getImage().getWidth();
            double imgH = iv.getImage().getHeight();
            if (imgW > 0 && imgH > 0) {
                double imgRatio = imgW / imgH;
                if (w / h > imgRatio) {
                    w = h * imgRatio;
                } else {
                    h = w / imgRatio;
                }
            }
        }

        return new double[]{w, h};
    }
    
    //DRAG AND MOVE CODE Class
    public void drag(MouseEvent event) {
        Node n = (Node)event.getSource();
        n.setTranslateX(n.getTranslateX() + event.getX());
        n.setTranslateY(n.getTranslateY() + event.getY());
        
        // Bring dragged element to front (except head which should stay at back)
        if (n instanceof ImageView) {
            ImageView imgView = (ImageView) n;
            String id = imgView.getId();
            if (id != null && !id.startsWith("head_s_")) {
                imgView.toFront(); // Bring to front when dragging (except head)
            }
        }
    }
    
    //Drag Event assigned to each fx:id in the canvas
    public void dragSketch() {
        head_s_1.setOnMouseDragged(event -> drag(event));
        head_s_2.setOnMouseDragged(event -> drag(event));
        head_s_3.setOnMouseDragged(event -> drag(event));
        head_s_4.setOnMouseDragged(event -> drag(event));
        head_s_5.setOnMouseDragged(event -> drag(event));
        head_s_6.setOnMouseDragged(event -> drag(event));
        head_s_7.setOnMouseDragged(event -> drag(event));
        head_s_8.setOnMouseDragged(event -> drag(event));
        head_s_9.setOnMouseDragged(event -> drag(event));
        head_s_10.setOnMouseDragged(event -> drag(event));
             
        hair_s_1.setOnMouseDragged(event -> drag(event));
        hair_s_2.setOnMouseDragged(event -> drag(event));
        hair_s_3.setOnMouseDragged(event -> drag(event));
        hair_s_4.setOnMouseDragged(event -> drag(event));
        hair_s_5.setOnMouseDragged(event -> drag(event));
        hair_s_6.setOnMouseDragged(event -> drag(event));
        hair_s_7.setOnMouseDragged(event -> drag(event));
        hair_s_8.setOnMouseDragged(event -> drag(event));
        hair_s_9.setOnMouseDragged(event -> drag(event));
        hair_s_10.setOnMouseDragged(event -> drag(event));
        hair_s_11.setOnMouseDragged(event -> drag(event));
        hair_s_12.setOnMouseDragged(event -> drag(event));
        
        eyes_s_1.setOnMouseDragged(event -> drag(event));
        eyes_s_2.setOnMouseDragged(event -> drag(event));
        eyes_s_3.setOnMouseDragged(event -> drag(event));
        eyes_s_4.setOnMouseDragged(event -> drag(event));
        eyes_s_5.setOnMouseDragged(event -> drag(event));
        eyes_s_6.setOnMouseDragged(event -> drag(event));
        eyes_s_7.setOnMouseDragged(event -> drag(event));
        eyes_s_8.setOnMouseDragged(event -> drag(event));
        eyes_s_9.setOnMouseDragged(event -> drag(event));
        eyes_s_10.setOnMouseDragged(event -> drag(event));
        eyes_s_11.setOnMouseDragged(event -> drag(event));
        eyes_s_12.setOnMouseDragged(event -> drag(event));
        
        eyeb_s_1.setOnMouseDragged(event -> drag(event));
        eyeb_s_2.setOnMouseDragged(event -> drag(event));
        eyeb_s_3.setOnMouseDragged(event -> drag(event));
        eyeb_s_4.setOnMouseDragged(event -> drag(event));
        eyeb_s_5.setOnMouseDragged(event -> drag(event));
        eyeb_s_6.setOnMouseDragged(event -> drag(event));
        eyeb_s_7.setOnMouseDragged(event -> drag(event));
        eyeb_s_8.setOnMouseDragged(event -> drag(event));
        eyeb_s_9.setOnMouseDragged(event -> drag(event));
        eyeb_s_10.setOnMouseDragged(event -> drag(event));
        eyeb_s_11.setOnMouseDragged(event -> drag(event));
        eyeb_s_12.setOnMouseDragged(event -> drag(event));
        
        lips_s_1.setOnMouseDragged(event -> drag(event));
        lips_s_2.setOnMouseDragged(event -> drag(event));
        lips_s_3.setOnMouseDragged(event -> drag(event));
        lips_s_4.setOnMouseDragged(event -> drag(event));
        lips_s_5.setOnMouseDragged(event -> drag(event));
        lips_s_6.setOnMouseDragged(event -> drag(event));
        lips_s_7.setOnMouseDragged(event -> drag(event));
        lips_s_8.setOnMouseDragged(event -> drag(event));
        lips_s_9.setOnMouseDragged(event -> drag(event));
        lips_s_10.setOnMouseDragged(event -> drag(event));
        lips_s_11.setOnMouseDragged(event -> drag(event));
        lips_s_12.setOnMouseDragged(event -> drag(event));
        
        nose_s_1.setOnMouseDragged(event -> drag(event));
        nose_s_2.setOnMouseDragged(event -> drag(event));
        nose_s_3.setOnMouseDragged(event -> drag(event));
        nose_s_4.setOnMouseDragged(event -> drag(event));
        nose_s_5.setOnMouseDragged(event -> drag(event));
        nose_s_6.setOnMouseDragged(event -> drag(event));
        nose_s_7.setOnMouseDragged(event -> drag(event));
        nose_s_8.setOnMouseDragged(event -> drag(event));
        nose_s_9.setOnMouseDragged(event -> drag(event));
        nose_s_10.setOnMouseDragged(event -> drag(event));
        nose_s_11.setOnMouseDragged(event -> drag(event));
        nose_s_12.setOnMouseDragged(event -> drag(event));
        
        must_s_1.setOnMouseDragged(event -> drag(event));
        must_s_2.setOnMouseDragged(event -> drag(event));
        must_s_3.setOnMouseDragged(event -> drag(event));
        must_s_4.setOnMouseDragged(event -> drag(event));
        must_s_5.setOnMouseDragged(event -> drag(event));
        must_s_6.setOnMouseDragged(event -> drag(event));
        must_s_7.setOnMouseDragged(event -> drag(event));
        must_s_8.setOnMouseDragged(event -> drag(event));
        must_s_9.setOnMouseDragged(event -> drag(event));
        must_s_10.setOnMouseDragged(event -> drag(event));
        must_s_11.setOnMouseDragged(event -> drag(event));
        must_s_12.setOnMouseDragged(event -> drag(event));
        
        more_s_1.setOnMouseDragged(event -> drag(event));
        more_s_2.setOnMouseDragged(event -> drag(event));
        more_s_3.setOnMouseDragged(event -> drag(event));
        more_s_4.setOnMouseDragged(event -> drag(event));
        more_s_5.setOnMouseDragged(event -> drag(event));
        more_s_6.setOnMouseDragged(event -> drag(event));
    }

    //Save Sketch to Image Code Class
    public void save_img(){
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Image");
            //Directory 
            fileChooser.setInitialDirectory(new File("./src/thirdeye/v2/elements/output/"));
            
            //Extension Filter
            FileChooser.ExtensionFilter extFilter;
            extFilter = new FileChooser.ExtensionFilter("PNG", "*.png");
            fileChooser.getExtensionFilters().add(extFilter);
            Image snapshot = sketch.snapshot(null, null); //Convert Anchor Pane to image i.e. fx_id of anchorpane
            File file = fileChooser.showSaveDialog(stage);
                       
            if (file != null) {
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(snapshot,null), "png", file);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
    }

    @FXML //toolkit elements to show
    private void toolKit(MouseEvent event) {
        if(event.getSource()==head) {
            head_elements.setVisible(true);
            hair_elements.setVisible(false);
            eyes_elements.setVisible(false);
            eyebrows_elements.setVisible(false);
            nose_elements.setVisible(false);
            lips_elements.setVisible(false);
            mustach_elements.setVisible(false);
            more_elements.setVisible(false);
        } else if(event.getSource()==hair) {
            head_elements.setVisible(false);
            hair_elements.setVisible(true);
            eyes_elements.setVisible(false);
            eyebrows_elements.setVisible(false);
            nose_elements.setVisible(false);
            lips_elements.setVisible(false);
            mustach_elements.setVisible(false);
            more_elements.setVisible(false);
        } else if(event.getSource()==eyes) {
            head_elements.setVisible(false);
            hair_elements.setVisible(false);
            eyes_elements.setVisible(true);
            eyebrows_elements.setVisible(false);
            nose_elements.setVisible(false);
            lips_elements.setVisible(false);
            mustach_elements.setVisible(false);
            more_elements.setVisible(false);
        } else if(event.getSource()==eyebrows) {
            head_elements.setVisible(false);
            hair_elements.setVisible(false);
            eyes_elements.setVisible(false);
            eyebrows_elements.setVisible(true);
            nose_elements.setVisible(false);
            lips_elements.setVisible(false);
            mustach_elements.setVisible(false);
            more_elements.setVisible(false);
        } else if(event.getSource()==nose) {
            head_elements.setVisible(false);
            hair_elements.setVisible(false);
            eyes_elements.setVisible(false);
            eyebrows_elements.setVisible(false);
            nose_elements.setVisible(true);
            lips_elements.setVisible(false);
            mustach_elements.setVisible(false);
            more_elements.setVisible(false);
        } else if(event.getSource()==lips) {
            head_elements.setVisible(false);
            hair_elements.setVisible(false);
            eyes_elements.setVisible(false);
            eyebrows_elements.setVisible(false);
            nose_elements.setVisible(false);
            lips_elements.setVisible(true);
            mustach_elements.setVisible(false);
            more_elements.setVisible(false);
        } else if(event.getSource()==mustach) {
            head_elements.setVisible(false);
            hair_elements.setVisible(false);
            eyes_elements.setVisible(false);
            eyebrows_elements.setVisible(false);
            nose_elements.setVisible(false);
            lips_elements.setVisible(false);
            mustach_elements.setVisible(true);
            more_elements.setVisible(false);
        } else if(event.getSource()==more) {
            head_elements.setVisible(false);
            hair_elements.setVisible(false);
            eyes_elements.setVisible(false);
            eyebrows_elements.setVisible(false);
            nose_elements.setVisible(false);
            lips_elements.setVisible(false);
            mustach_elements.setVisible(false);
            more_elements.setVisible(true);
        }
    }

    @FXML //Save the sketch to image
    private void onSave(MouseEvent event) {
        save_img(); //save image
    }
    
    @FXML //Open the upload and compare page
    private void onCompare(MouseEvent event) {
        save_img(); //Save image
        
        // open the upload the sketch page
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("upload_sketch.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("New Window");
                stage.setScene(scene);
                stage.resizableProperty().setValue(false); //Disable maximize button
                stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();
                
            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
            }
    }

    @FXML //Reset the sketch and delete all element
    private void onReset(MouseEvent event) {
        // Hide all dynamic elements
        for (List<ImageView> sketches : sketchImageViews.values()) {
            if (sketches != null) {
                for (ImageView sketch : sketches) {
                    sketch.setVisible(false);
                }
            }
        }
        
        // Also hide hardcoded elements for backward compatibility
        if(head_s_1 != null) head_s_1.setVisible(false);
        head_s_2.setVisible(false);
        head_s_3.setVisible(false);
        head_s_4.setVisible(false);
        head_s_5.setVisible(false);
        head_s_6.setVisible(false);
        head_s_7.setVisible(false);
        head_s_8.setVisible(false);
        head_s_9.setVisible(false);
        head_s_10.setVisible(false);
                
        hair_s_1.setVisible(false);
        hair_s_2.setVisible(false);
        hair_s_3.setVisible(false);
        hair_s_4.setVisible(false);
        hair_s_5.setVisible(false);
        hair_s_6.setVisible(false);
        hair_s_7.setVisible(false);
        hair_s_8.setVisible(false);
        hair_s_9.setVisible(false);
        hair_s_10.setVisible(false);
        hair_s_11.setVisible(false);
        hair_s_12.setVisible(false);
                
        eyes_s_1.setVisible(false);
        eyes_s_2.setVisible(false);
        eyes_s_3.setVisible(false);
        eyes_s_4.setVisible(false);
        eyes_s_5.setVisible(false);
        eyes_s_6.setVisible(false);
        eyes_s_7.setVisible(false);
        eyes_s_8.setVisible(false);
        eyes_s_9.setVisible(false);
        eyes_s_10.setVisible(false);
        eyes_s_11.setVisible(false);
        eyes_s_12.setVisible(false);
                
        eyeb_s_1.setVisible(false);
        eyeb_s_2.setVisible(false);
        eyeb_s_3.setVisible(false);
        eyeb_s_4.setVisible(false);
        eyeb_s_5.setVisible(false);
        eyeb_s_6.setVisible(false);
        eyeb_s_7.setVisible(false);
        eyeb_s_8.setVisible(false);
        eyeb_s_9.setVisible(false);
        eyeb_s_10.setVisible(false);
        eyeb_s_11.setVisible(false);
        eyeb_s_12.setVisible(false);
                
        lips_s_1.setVisible(false);
        lips_s_2.setVisible(false);
        lips_s_3.setVisible(false);
        lips_s_4.setVisible(false);
        lips_s_5.setVisible(false);
        lips_s_6.setVisible(false);
        lips_s_7.setVisible(false);
        lips_s_8.setVisible(false);
        lips_s_9.setVisible(false);
        lips_s_10.setVisible(false);
        lips_s_11.setVisible(false);
        lips_s_12.setVisible(false);
                
        nose_s_1.setVisible(false);
        nose_s_2.setVisible(false);
        nose_s_3.setVisible(false);
        nose_s_4.setVisible(false);
        nose_s_5.setVisible(false);
        nose_s_6.setVisible(false);
        nose_s_7.setVisible(false);
        nose_s_8.setVisible(false);
        nose_s_9.setVisible(false);
        nose_s_10.setVisible(false);
        nose_s_11.setVisible(false);
        nose_s_12.setVisible(false);
                
        must_s_1.setVisible(false);
        must_s_2.setVisible(false);
        must_s_3.setVisible(false);
        must_s_4.setVisible(false);
        must_s_5.setVisible(false);
        must_s_6.setVisible(false);
        must_s_7.setVisible(false);
        must_s_8.setVisible(false);
        must_s_9.setVisible(false);
        must_s_10.setVisible(false);
        must_s_11.setVisible(false);
        must_s_12.setVisible(false);
                
        more_s_1.setVisible(false);
        more_s_2.setVisible(false);
        more_s_3.setVisible(false);
        more_s_4.setVisible(false);
        more_s_5.setVisible(false);
        more_s_6.setVisible(false);
    }

        // Select the Elements to Show on CANVAS
        @FXML
        private void onHeadSelect(MouseEvent event) {
            // First check if it's dynamic element
            if (handleDynamicElementSelect(event, "head")) {
                return;
            }
            
            if(event.getSource()==head_del) {
                hideAllSketchElements("head");
                head_s_1.setVisible(false);
                head_s_2.setVisible(false);
                head_s_3.setVisible(false);
                head_s_4.setVisible(false);
                head_s_5.setVisible(false);
                head_s_6.setVisible(false);
                head_s_7.setVisible(false);
                head_s_8.setVisible(false);
                head_s_9.setVisible(false);
                head_s_10.setVisible(false);
            } else if(event.getSource()==head_e_1) {
                showSketchAtDefaultPosition(head_s_1, "head");
                head_s_2.setVisible(false);
                head_s_3.setVisible(false);
                head_s_4.setVisible(false);
                head_s_5.setVisible(false);
                head_s_6.setVisible(false);
                head_s_7.setVisible(false);
                head_s_8.setVisible(false);
                head_s_9.setVisible(false);
                head_s_10.setVisible(false);
            } else if(event.getSource()==head_e_2) {
                head_s_1.setVisible(false);
                showSketchAtDefaultPosition(head_s_2, "head");
                head_s_3.setVisible(false);
                head_s_4.setVisible(false);
                head_s_5.setVisible(false);
                head_s_6.setVisible(false);
                head_s_7.setVisible(false);
                head_s_8.setVisible(false);
                head_s_9.setVisible(false);
                head_s_10.setVisible(false);
            } else if(event.getSource()==head_e_3) {
                head_s_1.setVisible(false);
                head_s_2.setVisible(false);
                head_s_3.setVisible(true);
                head_s_4.setVisible(false);
                head_s_5.setVisible(false);
                head_s_6.setVisible(false);
                head_s_7.setVisible(false);
                head_s_8.setVisible(false);
                head_s_9.setVisible(false);
                head_s_10.setVisible(false);
            } else if(event.getSource()==head_e_4) {
                head_s_1.setVisible(false);
                head_s_2.setVisible(false);
                head_s_3.setVisible(false);
                head_s_4.setVisible(true);
                head_s_5.setVisible(false);
                head_s_6.setVisible(false);
                head_s_7.setVisible(false);
                head_s_8.setVisible(false);
                head_s_9.setVisible(false);
                head_s_10.setVisible(false);
            } else if(event.getSource()==head_e_5) {
                head_s_1.setVisible(false);
                head_s_2.setVisible(false);
                head_s_3.setVisible(false);
                head_s_4.setVisible(false);
                head_s_5.setVisible(true);
                head_s_6.setVisible(false);
                head_s_7.setVisible(false);
                head_s_8.setVisible(false);
                head_s_9.setVisible(false);
                head_s_10.setVisible(false);
            } else if(event.getSource()==head_e_6) {
                head_s_1.setVisible(false);
                head_s_2.setVisible(false);
                head_s_3.setVisible(false);
                head_s_4.setVisible(false);
                head_s_5.setVisible(false);
                head_s_6.setVisible(true);
                head_s_7.setVisible(false);
                head_s_8.setVisible(false);
                head_s_9.setVisible(false);
                head_s_10.setVisible(false);
            } else if(event.getSource()==head_e_7) {
                head_s_1.setVisible(false);
                head_s_2.setVisible(false);
                head_s_3.setVisible(false);
                head_s_4.setVisible(false);
                head_s_5.setVisible(false);
                head_s_6.setVisible(false);
                head_s_7.setVisible(true);
                head_s_8.setVisible(false);
                head_s_9.setVisible(false);
                head_s_10.setVisible(false);
            } else if(event.getSource()==head_e_8) {
                head_s_1.setVisible(false);
                head_s_2.setVisible(false);
                head_s_3.setVisible(false);
                head_s_4.setVisible(false);
                head_s_5.setVisible(false);
                head_s_6.setVisible(false);
                head_s_7.setVisible(false);
                head_s_8.setVisible(true);
                head_s_9.setVisible(false);
                head_s_10.setVisible(false);
            } else if(event.getSource()==head_e_9) {
                head_s_1.setVisible(false);
                head_s_2.setVisible(false);
                head_s_3.setVisible(false);
                head_s_4.setVisible(false);
                head_s_5.setVisible(false);
                head_s_6.setVisible(false);
                head_s_7.setVisible(false);
                head_s_8.setVisible(false);
                head_s_9.setVisible(true);
                head_s_10.setVisible(false);
            } else if(event.getSource()==head_e_10) {
                head_s_1.setVisible(false);
                head_s_2.setVisible(false);
                head_s_3.setVisible(false);
                head_s_4.setVisible(false);
                head_s_5.setVisible(false);
                head_s_6.setVisible(false);
                head_s_7.setVisible(false);
                head_s_8.setVisible(false);
                head_s_9.setVisible(false);
                head_s_10.setVisible(true);
            }
        }

        @FXML
        private void onHairSelect(MouseEvent event) {
            // First check if it's dynamic element
            if (handleDynamicElementSelect(event, "hair")) {
                return;
            }
            
            // Fallback to hardcoded elements
            if(event.getSource()==hair_del) {
                hideAllSketchElements("hair");
                // Also hide hardcoded ones for backward compatibility
                if(hair_s_1 != null) hair_s_1.setVisible(false);
                if(hair_s_2 != null) hair_s_2.setVisible(false);
                if(hair_s_3 != null) hair_s_3.setVisible(false);
                if(hair_s_4 != null) hair_s_4.setVisible(false);
                if(hair_s_5 != null) hair_s_5.setVisible(false);
                if(hair_s_6 != null) hair_s_6.setVisible(false);
                if(hair_s_7 != null) hair_s_7.setVisible(false);
                if(hair_s_8 != null) hair_s_8.setVisible(false);
                if(hair_s_9 != null) hair_s_9.setVisible(false);
                if(hair_s_10 != null) hair_s_10.setVisible(false);
                if(hair_s_11 != null) hair_s_11.setVisible(false);
                if(hair_s_12 != null) hair_s_12.setVisible(false);
            } else if(hair_e_1 != null && event.getSource()==hair_e_1) {
                hair_s_1.setVisible(true);
                hair_s_2.setVisible(false);
                hair_s_3.setVisible(false);
                hair_s_4.setVisible(false);
                hair_s_5.setVisible(false);
                hair_s_6.setVisible(false);
                hair_s_7.setVisible(false);
                hair_s_8.setVisible(false);
                hair_s_9.setVisible(false);
                hair_s_10.setVisible(false);
                hair_s_11.setVisible(false);
                hair_s_12.setVisible(false);
            } else if(event.getSource()==hair_e_2) {
                hair_s_1.setVisible(false);
                showSketchAtDefaultPosition(hair_s_2, "hair");
                hair_s_3.setVisible(false);
                hair_s_4.setVisible(false);
                hair_s_5.setVisible(false);
                hair_s_6.setVisible(false);
                hair_s_7.setVisible(false);
                hair_s_8.setVisible(false);
                hair_s_9.setVisible(false);
                hair_s_10.setVisible(false);
                hair_s_11.setVisible(false);
                hair_s_12.setVisible(false);
            } else if(event.getSource()==hair_e_3) {
                hair_s_1.setVisible(false);
                hair_s_2.setVisible(false);
                hair_s_3.setVisible(true);
                hair_s_4.setVisible(false);
                hair_s_5.setVisible(false);
                hair_s_6.setVisible(false);
                hair_s_7.setVisible(false);
                hair_s_8.setVisible(false);
                hair_s_9.setVisible(false);
                hair_s_10.setVisible(false);
                hair_s_11.setVisible(false);
                hair_s_12.setVisible(false);
            } else if(event.getSource()==hair_e_4) {
                hair_s_1.setVisible(false);
                hair_s_2.setVisible(false);
                hair_s_3.setVisible(false);
                hair_s_4.setVisible(true);
                hair_s_5.setVisible(false);
                hair_s_6.setVisible(false);
                hair_s_7.setVisible(false);
                hair_s_8.setVisible(false);
                hair_s_9.setVisible(false);
                hair_s_10.setVisible(false);
                hair_s_11.setVisible(false);
                hair_s_12.setVisible(false);
            } else if(event.getSource()==hair_e_5) {
                hair_s_1.setVisible(false);
                hair_s_2.setVisible(false);
                hair_s_3.setVisible(false);
                hair_s_4.setVisible(false);
                hair_s_5.setVisible(true);
                hair_s_6.setVisible(false);
                hair_s_7.setVisible(false);
                hair_s_8.setVisible(false);
                hair_s_9.setVisible(false);
                hair_s_10.setVisible(false);
                hair_s_11.setVisible(false);
                hair_s_12.setVisible(false);
            } else if(event.getSource()==hair_e_6) {
                hair_s_1.setVisible(false);
                hair_s_2.setVisible(false);
                hair_s_3.setVisible(false);
                hair_s_4.setVisible(false);
                hair_s_5.setVisible(false);
                hair_s_6.setVisible(true);
                hair_s_7.setVisible(false);
                hair_s_8.setVisible(false);
                hair_s_9.setVisible(false);
                hair_s_10.setVisible(false);
                hair_s_11.setVisible(false);
                hair_s_12.setVisible(false);
            } else if(event.getSource()==hair_e_7) {
                hair_s_1.setVisible(false);
                hair_s_2.setVisible(false);
                hair_s_3.setVisible(false);
                hair_s_4.setVisible(false);
                hair_s_5.setVisible(false);
                hair_s_6.setVisible(false);
                hair_s_7.setVisible(true);
                hair_s_8.setVisible(false);
                hair_s_9.setVisible(false);
                hair_s_10.setVisible(false);
                hair_s_11.setVisible(false);
                hair_s_12.setVisible(false);
            } else if(event.getSource()==hair_e_8) {
                hair_s_1.setVisible(false);
                hair_s_2.setVisible(false);
                hair_s_3.setVisible(false);
                hair_s_4.setVisible(false);
                hair_s_5.setVisible(false);
                hair_s_6.setVisible(false);
                hair_s_7.setVisible(false);
                hair_s_8.setVisible(true);
                hair_s_9.setVisible(false);
                hair_s_10.setVisible(false);
                hair_s_11.setVisible(false);
                hair_s_12.setVisible(false);
            } else if(event.getSource()==hair_e_9) {
                hair_s_1.setVisible(false);
                hair_s_2.setVisible(false);
                hair_s_3.setVisible(false);
                hair_s_4.setVisible(false);
                hair_s_5.setVisible(false);
                hair_s_6.setVisible(false);
                hair_s_7.setVisible(false);
                hair_s_8.setVisible(false);
                hair_s_9.setVisible(true);
                hair_s_10.setVisible(false);
                hair_s_11.setVisible(false);
                hair_s_12.setVisible(false);
            } else if(event.getSource()==hair_e_10) {
                hair_s_1.setVisible(false);
                hair_s_2.setVisible(false);
                hair_s_3.setVisible(false);
                hair_s_4.setVisible(false);
                hair_s_5.setVisible(false);
                hair_s_6.setVisible(false);
                hair_s_7.setVisible(false);
                hair_s_8.setVisible(false);
                hair_s_9.setVisible(false);
                hair_s_10.setVisible(true);
                hair_s_11.setVisible(false);
                hair_s_12.setVisible(false);
            } else if(event.getSource()==hair_e_11) {
                hair_s_1.setVisible(false);
                hair_s_2.setVisible(false);
                hair_s_3.setVisible(false);
                hair_s_4.setVisible(false);
                hair_s_5.setVisible(false);
                hair_s_6.setVisible(false);
                hair_s_7.setVisible(false);
                hair_s_8.setVisible(false);
                hair_s_9.setVisible(false);
                hair_s_10.setVisible(false);
                hair_s_11.setVisible(true);
                hair_s_12.setVisible(false);
            } else if(event.getSource()==hair_e_12) {
                hair_s_1.setVisible(false);
                hair_s_2.setVisible(false);
                hair_s_3.setVisible(false);
                hair_s_4.setVisible(false);
                hair_s_5.setVisible(false);
                hair_s_6.setVisible(false);
                hair_s_7.setVisible(false);
                hair_s_8.setVisible(false);
                hair_s_9.setVisible(false);
                hair_s_10.setVisible(false);
                hair_s_11.setVisible(false);
                hair_s_12.setVisible(true);
            } 
        }

        @FXML
        private void onEyesSelect(MouseEvent event) {
            // First check if it's dynamic element
            if (handleDynamicElementSelect(event, "eyes")) {
                return;
            }
            
            if(event.getSource()==eyes_del) {
                hideAllSketchElements("eyes");
                eyes_s_1.setVisible(false);
                eyes_s_2.setVisible(false);
                eyes_s_3.setVisible(false);
                eyes_s_4.setVisible(false);
                eyes_s_5.setVisible(false);
                eyes_s_6.setVisible(false);
                eyes_s_7.setVisible(false);
                eyes_s_8.setVisible(false);
                eyes_s_9.setVisible(false);
                eyes_s_10.setVisible(false);
                eyes_s_11.setVisible(false);
                eyes_s_12.setVisible(false);
            } else if(event.getSource()==eyes_e_1) {
                showSketchAtDefaultPosition(eyes_s_1, "eyes");
                eyes_s_2.setVisible(false);
                eyes_s_3.setVisible(false);
                eyes_s_4.setVisible(false);
                eyes_s_5.setVisible(false);
                eyes_s_6.setVisible(false);
                eyes_s_7.setVisible(false);
                eyes_s_8.setVisible(false);
                eyes_s_9.setVisible(false);
                eyes_s_10.setVisible(false);
                eyes_s_11.setVisible(false);
                eyes_s_12.setVisible(false);
            } else if(event.getSource()==eyes_e_2) {
                eyes_s_1.setVisible(false);
                eyes_s_2.setVisible(true);
                eyes_s_3.setVisible(false);
                eyes_s_4.setVisible(false);
                eyes_s_5.setVisible(false);
                eyes_s_6.setVisible(false);
                eyes_s_7.setVisible(false);
                eyes_s_8.setVisible(false);
                eyes_s_9.setVisible(false);
                eyes_s_10.setVisible(false);
                eyes_s_11.setVisible(false);
                eyes_s_12.setVisible(false);
            } else if(event.getSource()==eyes_e_3) {
                eyes_s_1.setVisible(false);
                eyes_s_2.setVisible(false);
                eyes_s_3.setVisible(true);
                eyes_s_4.setVisible(false);
                eyes_s_5.setVisible(false);
                eyes_s_6.setVisible(false);
                eyes_s_7.setVisible(false);
                eyes_s_8.setVisible(false);
                eyes_s_9.setVisible(false);
                eyes_s_10.setVisible(false);
                eyes_s_11.setVisible(false);
                eyes_s_12.setVisible(false);
            } else if(event.getSource()==eyes_e_4) {
                eyes_s_1.setVisible(false);
                eyes_s_2.setVisible(false);
                eyes_s_3.setVisible(false);
                eyes_s_4.setVisible(false);
                eyes_s_5.setVisible(false);
                eyes_s_6.setVisible(false);
                eyes_s_7.setVisible(false);
                eyes_s_8.setVisible(false);
                eyes_s_9.setVisible(false);
                eyes_s_10.setVisible(false);
                eyes_s_11.setVisible(false);
                eyes_s_12.setVisible(false);
            } else if(event.getSource()==eyes_e_5) {
                eyes_s_1.setVisible(false);
                eyes_s_2.setVisible(false);
                eyes_s_3.setVisible(false);
                eyes_s_4.setVisible(false);
                eyes_s_5.setVisible(true);
                eyes_s_6.setVisible(false);
                eyes_s_7.setVisible(false);
                eyes_s_8.setVisible(false);
                eyes_s_9.setVisible(false);
                eyes_s_10.setVisible(false);
                eyes_s_11.setVisible(false);
                eyes_s_12.setVisible(false);
            } else if(event.getSource()==eyes_e_6) {
                eyes_s_1.setVisible(false);
                eyes_s_2.setVisible(false);
                eyes_s_3.setVisible(false);
                eyes_s_4.setVisible(false);
                eyes_s_5.setVisible(false);
                eyes_s_6.setVisible(true);
                eyes_s_7.setVisible(false);
                eyes_s_8.setVisible(false);
                eyes_s_9.setVisible(false);
                eyes_s_10.setVisible(false);
                eyes_s_11.setVisible(false);
                eyes_s_12.setVisible(false);
            } else if(event.getSource()==eyes_e_7) {
                eyes_s_1.setVisible(false);
                eyes_s_2.setVisible(false);
                eyes_s_3.setVisible(false);
                eyes_s_4.setVisible(false);
                eyes_s_5.setVisible(false);
                eyes_s_6.setVisible(false);
                eyes_s_7.setVisible(true);
                eyes_s_8.setVisible(false);
                eyes_s_9.setVisible(false);
                eyes_s_10.setVisible(false);
                eyes_s_11.setVisible(false);
                eyes_s_12.setVisible(false);
            } else if(event.getSource()==eyes_e_8) {
                eyes_s_1.setVisible(false);
                eyes_s_2.setVisible(false);
                eyes_s_3.setVisible(false);
                eyes_s_4.setVisible(false);
                eyes_s_5.setVisible(false);
                eyes_s_6.setVisible(false);
                eyes_s_7.setVisible(false);
                eyes_s_8.setVisible(true);
                eyes_s_9.setVisible(false);
                eyes_s_10.setVisible(false);
                eyes_s_11.setVisible(false);
                eyes_s_12.setVisible(false);
            } else if(event.getSource()==eyes_e_9) {
                eyes_s_1.setVisible(false);
                eyes_s_2.setVisible(false);
                eyes_s_3.setVisible(false);
                eyes_s_4.setVisible(false);
                eyes_s_5.setVisible(false);
                eyes_s_6.setVisible(false);
                eyes_s_7.setVisible(false);
                eyes_s_8.setVisible(false);
                eyes_s_9.setVisible(true);
                eyes_s_10.setVisible(false);
                eyes_s_11.setVisible(false);
                eyes_s_12.setVisible(false);
            } else if(event.getSource()==eyes_e_10) {
                eyes_s_1.setVisible(false);
                eyes_s_2.setVisible(false);
                eyes_s_3.setVisible(false);
                eyes_s_4.setVisible(false);
                eyes_s_5.setVisible(false);
                eyes_s_6.setVisible(false);
                eyes_s_7.setVisible(false);
                eyes_s_8.setVisible(false);
                eyes_s_9.setVisible(false);
                eyes_s_10.setVisible(true);
                eyes_s_11.setVisible(false);
                eyes_s_12.setVisible(false);
            } else if(event.getSource()==eyes_e_11) {
                eyes_s_1.setVisible(false);
                eyes_s_2.setVisible(false);
                eyes_s_3.setVisible(false);
                eyes_s_4.setVisible(false);
                eyes_s_5.setVisible(false);
                eyes_s_6.setVisible(false);
                eyes_s_7.setVisible(false);
                eyes_s_8.setVisible(false);
                eyes_s_9.setVisible(false);
                eyes_s_10.setVisible(false);
                eyes_s_11.setVisible(true);
                eyes_s_12.setVisible(false);
            } else if(event.getSource()==eyes_e_12) {
                eyes_s_1.setVisible(false);
                eyes_s_2.setVisible(false);
                eyes_s_3.setVisible(false);
                eyes_s_4.setVisible(true);
                eyes_s_5.setVisible(false);
                eyes_s_6.setVisible(false);
                eyes_s_7.setVisible(false);
                eyes_s_8.setVisible(false);
                eyes_s_9.setVisible(false);
                eyes_s_10.setVisible(false);
                eyes_s_11.setVisible(false);
                eyes_s_12.setVisible(true);
            } 
        }

        @FXML
        private void onEyeBSelect(MouseEvent event) {
            // First check if it's dynamic element
            if (handleDynamicElementSelect(event, "eyebrows")) {
                return;
            }
            
            if(event.getSource()==eyeb_del) {
                hideAllSketchElements("eyebrows");
                eyeb_s_1.setVisible(false);
                eyeb_s_2.setVisible(false);
                eyeb_s_3.setVisible(false);
                eyeb_s_4.setVisible(false);
                eyeb_s_5.setVisible(false);
                eyeb_s_6.setVisible(false);
                eyeb_s_7.setVisible(false);
                eyeb_s_8.setVisible(false);
                eyeb_s_9.setVisible(false);
                eyeb_s_10.setVisible(false);
                eyeb_s_11.setVisible(false);
                eyeb_s_12.setVisible(false);
            } else if(event.getSource()==eyeb_e_1) {
                eyeb_s_1.setVisible(true);
                eyeb_s_2.setVisible(false);
                eyeb_s_3.setVisible(false);
                eyeb_s_4.setVisible(false);
                eyeb_s_5.setVisible(false);
                eyeb_s_6.setVisible(false);
                eyeb_s_7.setVisible(false);
                eyeb_s_8.setVisible(false);
                eyeb_s_9.setVisible(false);
                eyeb_s_10.setVisible(false);
                eyeb_s_11.setVisible(false);
                eyeb_s_12.setVisible(false);
            } else if(event.getSource()==eyeb_e_2) {
                eyeb_s_1.setVisible(false);
                eyeb_s_2.setVisible(true);
                eyeb_s_3.setVisible(false);
                eyeb_s_4.setVisible(false);
                eyeb_s_5.setVisible(false);
                eyeb_s_6.setVisible(false);
                eyeb_s_7.setVisible(false);
                eyeb_s_8.setVisible(false);
                eyeb_s_9.setVisible(false);
                eyeb_s_10.setVisible(false);
                eyeb_s_11.setVisible(false);
                eyeb_s_12.setVisible(false);
            } else if(event.getSource()==eyeb_e_3) {
                 eyeb_s_1.setVisible(false);
                eyeb_s_2.setVisible(false);
                eyeb_s_3.setVisible(true);
                eyeb_s_4.setVisible(false);
                eyeb_s_5.setVisible(false);
                eyeb_s_6.setVisible(false);
                eyeb_s_7.setVisible(false);
                eyeb_s_8.setVisible(false);
                eyeb_s_9.setVisible(false);
                eyeb_s_10.setVisible(false);
                eyeb_s_11.setVisible(false);
                eyeb_s_12.setVisible(false);
            } else if(event.getSource()==eyeb_e_4) {
                 eyeb_s_1.setVisible(false);
                eyeb_s_2.setVisible(false);
                eyeb_s_3.setVisible(false);
                eyeb_s_4.setVisible(true);
                eyeb_s_5.setVisible(false);
                eyeb_s_6.setVisible(false);
                eyeb_s_7.setVisible(false);
                eyeb_s_8.setVisible(false);
                eyeb_s_9.setVisible(false);
                eyeb_s_10.setVisible(false);
                eyeb_s_11.setVisible(false);
                eyeb_s_12.setVisible(false);
            } else if(event.getSource()==eyeb_e_5) {
                 eyeb_s_1.setVisible(false);
                eyeb_s_2.setVisible(false);
                eyeb_s_3.setVisible(false);
                eyeb_s_4.setVisible(false);
                eyeb_s_5.setVisible(true);
                eyeb_s_6.setVisible(false);
                eyeb_s_7.setVisible(false);
                eyeb_s_8.setVisible(false);
                eyeb_s_9.setVisible(false);
                eyeb_s_10.setVisible(false);
                eyeb_s_11.setVisible(false);
                eyeb_s_12.setVisible(false);
            } else if(event.getSource()==eyeb_e_6) {
                 eyeb_s_1.setVisible(false);
                eyeb_s_2.setVisible(false);
                eyeb_s_3.setVisible(false);
                eyeb_s_4.setVisible(false);
                eyeb_s_5.setVisible(false);
                eyeb_s_6.setVisible(true);
                eyeb_s_7.setVisible(false);
                eyeb_s_8.setVisible(false);
                eyeb_s_9.setVisible(false);
                eyeb_s_10.setVisible(false);
                eyeb_s_11.setVisible(false);
                eyeb_s_12.setVisible(false);
            } else if(event.getSource()==eyeb_e_7) {
                 eyeb_s_1.setVisible(false);
                eyeb_s_2.setVisible(false);
                eyeb_s_3.setVisible(false);
                eyeb_s_4.setVisible(false);
                eyeb_s_5.setVisible(false);
                eyeb_s_6.setVisible(false);
                eyeb_s_7.setVisible(true);
                eyeb_s_8.setVisible(false);
                eyeb_s_9.setVisible(false);
                eyeb_s_10.setVisible(false);
                eyeb_s_11.setVisible(false);
                eyeb_s_12.setVisible(false);
            } else if(event.getSource()==eyeb_e_8) {
                 eyeb_s_1.setVisible(false);
                eyeb_s_2.setVisible(false);
                eyeb_s_3.setVisible(false);
                eyeb_s_4.setVisible(false);
                eyeb_s_5.setVisible(false);
                eyeb_s_6.setVisible(false);
                eyeb_s_7.setVisible(false);
                eyeb_s_8.setVisible(true);
                eyeb_s_9.setVisible(false);
                eyeb_s_10.setVisible(false);
                eyeb_s_11.setVisible(false);
                eyeb_s_12.setVisible(false);
            } else if(event.getSource()==eyeb_e_9) {
                 eyeb_s_1.setVisible(false);
                eyeb_s_2.setVisible(false);
                eyeb_s_3.setVisible(false);
                eyeb_s_4.setVisible(false);
                eyeb_s_5.setVisible(false);
                eyeb_s_6.setVisible(false);
                eyeb_s_7.setVisible(false);
                eyeb_s_8.setVisible(false);
                eyeb_s_9.setVisible(true);
                eyeb_s_10.setVisible(false);
                eyeb_s_11.setVisible(false);
                eyeb_s_12.setVisible(false);
            } else if(event.getSource()==eyeb_e_10) {
                 eyeb_s_1.setVisible(false);
                eyeb_s_2.setVisible(false);
                eyeb_s_3.setVisible(false);
                eyeb_s_4.setVisible(false);
                eyeb_s_5.setVisible(false);
                eyeb_s_6.setVisible(false);
                eyeb_s_7.setVisible(false);
                eyeb_s_8.setVisible(false);
                eyeb_s_9.setVisible(false);
                eyeb_s_10.setVisible(true);
                eyeb_s_11.setVisible(false);
                eyeb_s_12.setVisible(false);
            } else if(event.getSource()==eyeb_e_11) {
                 eyeb_s_1.setVisible(false);
                eyeb_s_2.setVisible(false);
                eyeb_s_3.setVisible(false);
                eyeb_s_4.setVisible(false);
                eyeb_s_5.setVisible(false);
                eyeb_s_6.setVisible(false);
                eyeb_s_7.setVisible(false);
                eyeb_s_8.setVisible(false);
                eyeb_s_9.setVisible(false);
                eyeb_s_10.setVisible(false);
                eyeb_s_11.setVisible(true);
                eyeb_s_12.setVisible(false);
            } else if(event.getSource()==eyeb_e_12) {
                 eyeb_s_1.setVisible(false);
                eyeb_s_2.setVisible(false);
                eyeb_s_3.setVisible(false);
                eyeb_s_4.setVisible(false);
                eyeb_s_5.setVisible(false);
                eyeb_s_6.setVisible(false);
                eyeb_s_7.setVisible(false);
                eyeb_s_8.setVisible(false);
                eyeb_s_9.setVisible(false);
                eyeb_s_10.setVisible(false);
                eyeb_s_11.setVisible(false);
                eyeb_s_12.setVisible(true);
            } 
        }

        @FXML
        private void onNoseSelect(MouseEvent event) {
            // First check if it's dynamic element
            if (handleDynamicElementSelect(event, "nose")) {
                return;
            }
            
            if(event.getSource()==nose_del) {
                hideAllSketchElements("nose");
                nose_s_1.setVisible(false);
                nose_s_2.setVisible(false);
                nose_s_3.setVisible(false);
                nose_s_4.setVisible(false);
                nose_s_5.setVisible(false);
                nose_s_6.setVisible(false);
                nose_s_7.setVisible(false);
                nose_s_8.setVisible(false);
                nose_s_9.setVisible(false);
                nose_s_10.setVisible(false);
                nose_s_11.setVisible(false);
                nose_s_12.setVisible(false);
            } else if(event.getSource()==nose_e_1) {
                showSketchAtDefaultPosition(nose_s_1, "nose");
                nose_s_2.setVisible(false);
                nose_s_3.setVisible(false);
                nose_s_4.setVisible(false);
                nose_s_5.setVisible(false);
                nose_s_6.setVisible(false);
                nose_s_7.setVisible(false);
                nose_s_8.setVisible(false);
                nose_s_9.setVisible(false);
                nose_s_10.setVisible(false);
                nose_s_11.setVisible(false);
                nose_s_12.setVisible(false);
            } else if(event.getSource()==nose_e_2) {
                nose_s_1.setVisible(false);
                nose_s_2.setVisible(true);
                nose_s_3.setVisible(false);
                nose_s_4.setVisible(false);
                nose_s_5.setVisible(false);
                nose_s_6.setVisible(false);
                nose_s_7.setVisible(false);
                nose_s_8.setVisible(false);
                nose_s_9.setVisible(false);
                nose_s_10.setVisible(false);
                nose_s_11.setVisible(false);
                nose_s_12.setVisible(false);
            } else if(event.getSource()==nose_e_3) {
                nose_s_1.setVisible(false);
                nose_s_2.setVisible(false);
                nose_s_3.setVisible(true);
                nose_s_4.setVisible(false);
                nose_s_5.setVisible(false);
                nose_s_6.setVisible(false);
                nose_s_7.setVisible(false);
                nose_s_8.setVisible(false);
                nose_s_9.setVisible(false);
                nose_s_10.setVisible(false);
                nose_s_11.setVisible(false);
                nose_s_12.setVisible(false);
            } else if(event.getSource()==nose_e_4) {
                nose_s_1.setVisible(false);
                nose_s_2.setVisible(false);
                nose_s_3.setVisible(false);
                nose_s_4.setVisible(true);
                nose_s_5.setVisible(false);
                nose_s_6.setVisible(false);
                nose_s_7.setVisible(false);
                nose_s_8.setVisible(false);
                nose_s_9.setVisible(false);
                nose_s_10.setVisible(false);
                nose_s_11.setVisible(false);
                nose_s_12.setVisible(false);
            } else if(event.getSource()==nose_e_5) {
                nose_s_1.setVisible(false);
                nose_s_2.setVisible(false);
                nose_s_3.setVisible(false);
                nose_s_4.setVisible(false);
                nose_s_5.setVisible(true);
                nose_s_6.setVisible(false);
                nose_s_7.setVisible(false);
                nose_s_8.setVisible(false);
                nose_s_9.setVisible(false);
                nose_s_10.setVisible(false);
                nose_s_11.setVisible(false);
                nose_s_12.setVisible(false);
            } else if(event.getSource()==nose_e_6) {
                nose_s_1.setVisible(false);
                nose_s_2.setVisible(false);
                nose_s_3.setVisible(false);
                nose_s_4.setVisible(false);
                nose_s_5.setVisible(false);
                nose_s_6.setVisible(true);
                nose_s_7.setVisible(false);
                nose_s_8.setVisible(false);
                nose_s_9.setVisible(false);
                nose_s_10.setVisible(false);
                nose_s_11.setVisible(false);
                nose_s_12.setVisible(false);
            } else if(event.getSource()==nose_e_7) {
                nose_s_1.setVisible(false);
                nose_s_2.setVisible(false);
                nose_s_3.setVisible(false);
                nose_s_4.setVisible(false);
                nose_s_5.setVisible(false);
                nose_s_6.setVisible(false);
                nose_s_7.setVisible(true);
                nose_s_8.setVisible(false);
                nose_s_9.setVisible(false);
                nose_s_10.setVisible(false);
                nose_s_11.setVisible(false);
                nose_s_12.setVisible(false);
            } else if(event.getSource()==nose_e_8) {
                nose_s_1.setVisible(false);
                nose_s_2.setVisible(false);
                nose_s_3.setVisible(false);
                nose_s_4.setVisible(false);
                nose_s_5.setVisible(false);
                nose_s_6.setVisible(false);
                nose_s_7.setVisible(false);
                nose_s_8.setVisible(true);
                nose_s_9.setVisible(false);
                nose_s_10.setVisible(false);
                nose_s_11.setVisible(false);
                nose_s_12.setVisible(false);
            } else if(event.getSource()==nose_e_9) {
                nose_s_1.setVisible(false);
                nose_s_2.setVisible(false);
                nose_s_3.setVisible(false);
                nose_s_4.setVisible(false);
                nose_s_5.setVisible(false);
                nose_s_6.setVisible(false);
                nose_s_7.setVisible(false);
                nose_s_8.setVisible(false);
                nose_s_9.setVisible(true);
                nose_s_10.setVisible(false);
                nose_s_11.setVisible(false);
                nose_s_12.setVisible(false);
            } else if(event.getSource()==nose_e_10) {
                nose_s_1.setVisible(false);
                nose_s_2.setVisible(false);
                nose_s_3.setVisible(false);
                nose_s_4.setVisible(false);
                nose_s_5.setVisible(false);
                nose_s_6.setVisible(false);
                nose_s_7.setVisible(false);
                nose_s_8.setVisible(false);
                nose_s_9.setVisible(false);
                nose_s_10.setVisible(true);
                nose_s_11.setVisible(false);
                nose_s_12.setVisible(false);
            } else if(event.getSource()==nose_e_11) {
                nose_s_1.setVisible(false);
                nose_s_2.setVisible(false);
                nose_s_3.setVisible(false);
                nose_s_4.setVisible(false);
                nose_s_5.setVisible(false);
                nose_s_6.setVisible(false);
                nose_s_7.setVisible(false);
                nose_s_8.setVisible(false);
                nose_s_9.setVisible(false);
                nose_s_10.setVisible(false);
                nose_s_11.setVisible(true);
                nose_s_12.setVisible(false);
            } else if(event.getSource()==nose_e_12) {
                nose_s_1.setVisible(false);
                nose_s_2.setVisible(false);
                nose_s_3.setVisible(false);
                nose_s_4.setVisible(false);
                nose_s_5.setVisible(false);
                nose_s_6.setVisible(false);
                nose_s_7.setVisible(false);
                nose_s_8.setVisible(false);
                nose_s_9.setVisible(false);
                nose_s_10.setVisible(false);
                nose_s_11.setVisible(false);
                nose_s_12.setVisible(true);
            } 
        }

        @FXML
        private void onLipsSelect(MouseEvent event) {
            // First check if it's dynamic element
            if (handleDynamicElementSelect(event, "lips")) {
                return;
            }
            
            if(event.getSource()==lips_del) {
                hideAllSketchElements("lips");
                lips_s_1.setVisible(false);
                lips_s_2.setVisible(false);
                lips_s_3.setVisible(false);
                lips_s_4.setVisible(false);
                lips_s_5.setVisible(false);
                lips_s_6.setVisible(false);
                lips_s_7.setVisible(false);
                lips_s_8.setVisible(false);
                lips_s_9.setVisible(false);
                lips_s_10.setVisible(false);
                lips_s_11.setVisible(false);
                lips_s_12.setVisible(false);
            } else if(event.getSource()==lips_e_1) {
                showSketchAtDefaultPosition(lips_s_1, "lips");
                lips_s_2.setVisible(false);
                lips_s_3.setVisible(false);
                lips_s_4.setVisible(false);
                lips_s_5.setVisible(false);
                lips_s_6.setVisible(false);
                lips_s_7.setVisible(false);
                lips_s_8.setVisible(false);
                lips_s_9.setVisible(false);
                lips_s_10.setVisible(false);
                lips_s_11.setVisible(false);
                lips_s_12.setVisible(false);
            } else if(event.getSource()==lips_e_2) {
                lips_s_1.setVisible(false);
                lips_s_2.setVisible(true);
                lips_s_3.setVisible(false);
                lips_s_4.setVisible(false);
                lips_s_5.setVisible(false);
                lips_s_6.setVisible(false);
                lips_s_7.setVisible(false);
                lips_s_8.setVisible(false);
                lips_s_9.setVisible(false);
                lips_s_10.setVisible(false);
                lips_s_11.setVisible(false);
                lips_s_12.setVisible(false);
            } else if(event.getSource()==lips_e_3) {
                lips_s_1.setVisible(false);
                lips_s_2.setVisible(false);
                lips_s_3.setVisible(true);
                lips_s_4.setVisible(false);
                lips_s_5.setVisible(false);
                lips_s_6.setVisible(false);
                lips_s_7.setVisible(false);
                lips_s_8.setVisible(false);
                lips_s_9.setVisible(false);
                lips_s_10.setVisible(false);
                lips_s_11.setVisible(false);
                lips_s_12.setVisible(false);
            } else if(event.getSource()==lips_e_4) {
                lips_s_1.setVisible(false);
                lips_s_2.setVisible(false);
                lips_s_3.setVisible(false);
                lips_s_4.setVisible(true);
                lips_s_5.setVisible(false);
                lips_s_6.setVisible(false);
                lips_s_7.setVisible(false);
                lips_s_8.setVisible(false);
                lips_s_9.setVisible(false);
                lips_s_10.setVisible(false);
                lips_s_11.setVisible(false);
                lips_s_12.setVisible(false);
            } else if(event.getSource()==lips_e_5) {
                lips_s_1.setVisible(false);
                lips_s_2.setVisible(false);
                lips_s_3.setVisible(false);
                lips_s_4.setVisible(false);
                lips_s_5.setVisible(true);
                lips_s_6.setVisible(false);
                lips_s_7.setVisible(false);
                lips_s_8.setVisible(false);
                lips_s_9.setVisible(false);
                lips_s_10.setVisible(false);
                lips_s_11.setVisible(false);
                lips_s_12.setVisible(false);
            } else if(event.getSource()==lips_e_6) {
                lips_s_1.setVisible(false);
                lips_s_2.setVisible(false);
                lips_s_3.setVisible(false);
                lips_s_4.setVisible(false);
                lips_s_5.setVisible(false);
                lips_s_6.setVisible(true);
                lips_s_7.setVisible(false);
                lips_s_8.setVisible(false);
                lips_s_9.setVisible(false);
                lips_s_10.setVisible(false);
                lips_s_11.setVisible(false);
                lips_s_12.setVisible(false);
            } else if(event.getSource()==lips_e_7) {
                lips_s_1.setVisible(false);
                lips_s_2.setVisible(false);
                lips_s_3.setVisible(false);
                lips_s_4.setVisible(false);
                lips_s_5.setVisible(false);
                lips_s_6.setVisible(false);
                lips_s_7.setVisible(true);
                lips_s_8.setVisible(false);
                lips_s_9.setVisible(false);
                lips_s_10.setVisible(false);
                lips_s_11.setVisible(false);
                lips_s_12.setVisible(false);
            } else if(event.getSource()==lips_e_8) {
                lips_s_1.setVisible(false);
                lips_s_2.setVisible(false);
                lips_s_3.setVisible(false);
                lips_s_4.setVisible(false);
                lips_s_5.setVisible(false);
                lips_s_6.setVisible(false);
                lips_s_7.setVisible(false);
                lips_s_8.setVisible(true);
                lips_s_9.setVisible(false);
                lips_s_10.setVisible(false);
                lips_s_11.setVisible(false);
                lips_s_12.setVisible(false);
            } else if(event.getSource()==lips_e_9) {
                lips_s_1.setVisible(false);
                lips_s_2.setVisible(false);
                lips_s_3.setVisible(false);
                lips_s_4.setVisible(false);
                lips_s_5.setVisible(false);
                lips_s_6.setVisible(false);
                lips_s_7.setVisible(false);
                lips_s_8.setVisible(false);
                lips_s_9.setVisible(true);
                lips_s_10.setVisible(false);
                lips_s_11.setVisible(false);
                lips_s_12.setVisible(false);
            } else if(event.getSource()==lips_e_10) {
                lips_s_1.setVisible(false);
                lips_s_2.setVisible(false);
                lips_s_3.setVisible(false);
                lips_s_4.setVisible(false);
                lips_s_5.setVisible(false);
                lips_s_6.setVisible(false);
                lips_s_7.setVisible(false);
                lips_s_8.setVisible(false);
                lips_s_9.setVisible(false);
                lips_s_10.setVisible(true);
                lips_s_11.setVisible(false);
                lips_s_12.setVisible(false);
            } else if(event.getSource()==lips_e_11) {
                lips_s_1.setVisible(false);
                lips_s_2.setVisible(false);
                lips_s_3.setVisible(false);
                lips_s_4.setVisible(false);
                lips_s_5.setVisible(false);
                lips_s_6.setVisible(false);
                lips_s_7.setVisible(false);
                lips_s_8.setVisible(false);
                lips_s_9.setVisible(false);
                lips_s_10.setVisible(false);
                lips_s_11.setVisible(true);
                lips_s_12.setVisible(false);
            } else if(event.getSource()==lips_e_12) {
                lips_s_1.setVisible(false);
                lips_s_2.setVisible(false);
                lips_s_3.setVisible(false);
                lips_s_4.setVisible(false);
                lips_s_5.setVisible(false);
                lips_s_6.setVisible(false);
                lips_s_7.setVisible(false);
                lips_s_8.setVisible(false);
                lips_s_9.setVisible(false);
                lips_s_10.setVisible(false);
                lips_s_11.setVisible(false);
                lips_s_12.setVisible(true);
            } 
        }

        @FXML
        private void onMustSelect(MouseEvent event) {
            // First check if it's dynamic element
            if (handleDynamicElementSelect(event, "mustach")) {
                return;
            }
            
            if(event.getSource()==must_del) {
                hideAllSketchElements("mustach");
                must_s_1.setVisible(false);
                must_s_2.setVisible(false);
                must_s_3.setVisible(false);
                must_s_4.setVisible(false);
                must_s_5.setVisible(false);
                must_s_6.setVisible(false);
                must_s_7.setVisible(false);
                must_s_8.setVisible(false);
                must_s_9.setVisible(false);
                must_s_10.setVisible(false);
                must_s_11.setVisible(false);
                must_s_12.setVisible(false);
            } else if(event.getSource()==must_e_1) {
                must_s_1.setVisible(true);
                must_s_2.setVisible(false);
                must_s_3.setVisible(false);
                must_s_4.setVisible(false);
                must_s_5.setVisible(false);
                must_s_6.setVisible(false);
                must_s_7.setVisible(false);
                must_s_8.setVisible(false);
                must_s_9.setVisible(false);
                must_s_10.setVisible(false);
                must_s_11.setVisible(false);
                must_s_12.setVisible(false);
            } else if(event.getSource()==must_e_2) {
                must_s_1.setVisible(false);
                must_s_2.setVisible(true);
                must_s_3.setVisible(false);
                must_s_4.setVisible(false);
                must_s_5.setVisible(false);
                must_s_6.setVisible(false);
                must_s_7.setVisible(false);
                must_s_8.setVisible(false);
                must_s_9.setVisible(false);
                must_s_10.setVisible(false);
                must_s_11.setVisible(false);
                must_s_12.setVisible(false);
            } else if(event.getSource()==must_e_3) {
                must_s_1.setVisible(false);
                must_s_2.setVisible(false);
                must_s_3.setVisible(true);
                must_s_4.setVisible(false);
                must_s_5.setVisible(false);
                must_s_6.setVisible(false);
                must_s_7.setVisible(false);
                must_s_8.setVisible(false);
                must_s_9.setVisible(false);
                must_s_10.setVisible(false);
                must_s_11.setVisible(false);
                must_s_12.setVisible(false);
            } else if(event.getSource()==must_e_4) {
                must_s_1.setVisible(false);
                must_s_2.setVisible(false);
                must_s_3.setVisible(false);
                must_s_4.setVisible(true);
                must_s_5.setVisible(false);
                must_s_6.setVisible(false);
                must_s_7.setVisible(false);
                must_s_8.setVisible(false);
                must_s_9.setVisible(false);
                must_s_10.setVisible(false);
                must_s_11.setVisible(false);
                must_s_12.setVisible(false);
            } else if(event.getSource()==must_e_5) {
                must_s_1.setVisible(false);
                must_s_2.setVisible(false);
                must_s_3.setVisible(false);
                must_s_4.setVisible(false);
                must_s_5.setVisible(true);
                must_s_6.setVisible(false);
                must_s_7.setVisible(false);
                must_s_8.setVisible(false);
                must_s_9.setVisible(false);
                must_s_10.setVisible(false);
                must_s_11.setVisible(false);
                must_s_12.setVisible(false);
            } else if(event.getSource()==must_e_6) {
                must_s_1.setVisible(false);
                must_s_2.setVisible(false);
                must_s_3.setVisible(false);
                must_s_4.setVisible(false);
                must_s_5.setVisible(false);
                must_s_6.setVisible(true);
                must_s_7.setVisible(false);
                must_s_8.setVisible(false);
                must_s_9.setVisible(false);
                must_s_10.setVisible(false);
                must_s_11.setVisible(false);
                must_s_12.setVisible(false);
            } else if(event.getSource()==must_e_7) {
                must_s_1.setVisible(false);
                must_s_2.setVisible(false);
                must_s_3.setVisible(false);
                must_s_4.setVisible(false);
                must_s_5.setVisible(false);
                must_s_6.setVisible(false);
                must_s_7.setVisible(true);
                must_s_8.setVisible(false);
                must_s_9.setVisible(false);
                must_s_10.setVisible(false);
                must_s_11.setVisible(false);
                must_s_12.setVisible(false);
            } else if(event.getSource()==must_e_8) {
                must_s_1.setVisible(false);
                must_s_2.setVisible(false);
                must_s_3.setVisible(false);
                must_s_4.setVisible(false);
                must_s_5.setVisible(false);
                must_s_6.setVisible(false);
                must_s_7.setVisible(false);
                must_s_8.setVisible(true);
                must_s_9.setVisible(false);
                must_s_10.setVisible(false);
                must_s_11.setVisible(false);
                must_s_12.setVisible(false);
            } else if(event.getSource()==must_e_9) {
                must_s_1.setVisible(false);
                must_s_2.setVisible(false);
                must_s_3.setVisible(false);
                must_s_4.setVisible(false);
                must_s_5.setVisible(false);
                must_s_6.setVisible(false);
                must_s_7.setVisible(false);
                must_s_8.setVisible(false);
                must_s_9.setVisible(true);
                must_s_10.setVisible(false);
                must_s_11.setVisible(false);
                must_s_12.setVisible(false);
            } else if(event.getSource()==must_e_10) {
                must_s_1.setVisible(false);
                must_s_2.setVisible(false);
                must_s_3.setVisible(false);
                must_s_4.setVisible(false);
                must_s_5.setVisible(false);
                must_s_6.setVisible(false);
                must_s_7.setVisible(false);
                must_s_8.setVisible(false);
                must_s_9.setVisible(false);
                must_s_10.setVisible(true);
                must_s_11.setVisible(false);
                must_s_12.setVisible(false);
            } else if(event.getSource()==must_e_11) {
                must_s_1.setVisible(false);
                must_s_2.setVisible(false);
                must_s_3.setVisible(false);
                must_s_4.setVisible(false);
                must_s_5.setVisible(false);
                must_s_6.setVisible(false);
                must_s_7.setVisible(false);
                must_s_8.setVisible(false);
                must_s_9.setVisible(false);
                must_s_10.setVisible(false);
                must_s_11.setVisible(true);
                must_s_12.setVisible(false);
            } else if(event.getSource()==must_e_12) {
                must_s_1.setVisible(false);
                must_s_2.setVisible(false);
                must_s_3.setVisible(false);
                must_s_4.setVisible(false);
                must_s_5.setVisible(false);
                must_s_6.setVisible(false);
                must_s_7.setVisible(false);
                must_s_8.setVisible(false);
                must_s_9.setVisible(false);
                must_s_10.setVisible(false);
                must_s_11.setVisible(false);
                must_s_12.setVisible(true);
            } 
        }

        @FXML
        private void onMoreSelect(MouseEvent event) {
            // First check if it's dynamic element
            if (handleDynamicElementSelect(event, "more")) {
                return;
            }
            
            if(event.getSource()==more_del) {
                hideAllSketchElements("more");
                more_s_1.setVisible(false);
                more_s_2.setVisible(false);
                more_s_3.setVisible(false);
                more_s_4.setVisible(false);
                more_s_5.setVisible(false);
                more_s_6.setVisible(false);
            } else if(event.getSource()==more_e_1) {
                more_s_1.setVisible(true);
                more_s_2.setVisible(false);
                more_s_3.setVisible(false);
                more_s_4.setVisible(false);
            } else if(event.getSource()==more_e_2) {
                more_s_1.setVisible(true);
                more_s_2.setVisible(true);
                more_s_3.setVisible(false);
                more_s_4.setVisible(false);
            } else if(event.getSource()==more_e_3) {
                more_s_1.setVisible(false);
                more_s_2.setVisible(false);
                more_s_3.setVisible(true);
                more_s_4.setVisible(false);
            } else if(event.getSource()==more_e_4) {
                more_s_1.setVisible(false);
                more_s_2.setVisible(false);
                more_s_3.setVisible(true);
                more_s_4.setVisible(true);
            } else if(event.getSource()==more_e_5) {
                more_s_5.setVisible(true);
                more_s_6.setVisible(false);
            } else if(event.getSource()==more_e_6) {
                more_s_5.setVisible(false);
                more_s_6.setVisible(true);
            } 
        }
    
}

