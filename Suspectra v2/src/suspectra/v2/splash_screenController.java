package suspectra.v2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class splash_screenController implements Initializable {
    
    @FXML
    private AnchorPane splash;  
    
    class SplashScreen extends Thread{
        
        @Override 
        public void run(){
            try {
               Thread.sleep(5000);
                
               Platform.runLater(() -> {
                   Parent root = null;
                   try {
                       root = FXMLLoader.load(getClass().getResource("login_screen.fxml"));
                   } catch (IOException ex) {
                       Logger.getLogger(splash_screenController.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   Scene scene = new Scene(root);
                   Stage stage = new Stage();
                   stage.setScene(scene);
                   stage.resizableProperty().setValue(false);
                   stage.show();
                   
                   splash.getScene().getWindow().hide();
               }); 
                
                
            } catch (InterruptedException ex) {
                Logger.getLogger(splash_screenController.class.getName()).log(Level.SEVERE, null, ex);                                
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new SplashScreen().start();
    }  
}