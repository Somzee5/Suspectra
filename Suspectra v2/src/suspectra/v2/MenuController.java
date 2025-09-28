/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suspectra.v2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
 * @author Akash Sahu
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
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("dashboard.fxml"));
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

    @FXML //Open upload sketch window
    private void upload(MouseEvent event) {
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
    
}
