/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suspectra.v2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 */
public class MenuController implements Initializable {

    @FXML
    private Rectangle sketch;
    @FXML
    private Rectangle upload;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML //Open the sketch dashboard   
    private void sketch(MouseEvent event) {
            try {
                System.out.println("Create Sketch button clicked!");
                FXMLLoader fxmlLoader = new FXMLLoader();
                URL fxmlUrl = getClass().getResource("dashboard.fxml");
                System.out.println("Dashboard FXML URL: " + fxmlUrl);
                
                if (fxmlUrl == null) {
                    System.err.println("Error: dashboard.fxml not found!");
                    return;
                }
                
                fxmlLoader.setLocation(fxmlUrl);
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Suspectra v2 - Sketch Dashboard");
                stage.setScene(scene);
                stage.setResizable(true); // Enable resizing
                stage.setMinWidth(1350); // Set minimum width
                stage.setMinHeight(700); // Set minimum height
                stage.setMaximized(false); // Don't start maximized
                stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();
                
            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to load dashboard", e);
                System.err.println("Error loading dashboard: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("Unexpected error: " + e.getMessage());
                e.printStackTrace();
            }
    }

    @FXML //Open upload sketch window - Launch FaceMatch application
    private void upload(MouseEvent event) {
         try {
                // Launch the FaceMatch application using Maven
                ProcessBuilder processBuilder = new ProcessBuilder(
                    "cmd.exe", "/c", 
                    "cd /d \"d:\\Suspectra\\Suspectra_FaceMatch\" && mvn exec:java -Dexec.mainClass=com.mycompany.suspectra_facematch.face_rekognition"
                );
                processBuilder.directory(new File("d:\\Suspectra\\Suspectra_FaceMatch"));
                processBuilder.start();
                
                // Close the current menu window
                ((Node)(event.getSource())).getScene().getWindow().hide();
                
            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to launch FaceMatch application", e);
            }
    }
    
}
