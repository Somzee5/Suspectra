package suspectra.v2;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Login_screenController implements Initializable {

    @FXML
    private Text ip_add;
    @FXML
    private Text mac_add;
    @FXML
    private Text net_add;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField otp;
    @FXML
    private Text error;
    @FXML
    private Button send;
    @FXML
    private Button verify;
    @FXML
    private Text hide;
    
    
    //Connect DB
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    @FXML
    private Text loginerror;
    @FXML
    private Text hide1;
    @FXML
    private Text loginmsg;
    @FXML
    private AnchorPane login_page;
    
    public Login_screenController(){
        conn = connectdb.ConnectDB();
    }

    
    // CODE FOR MAC AND IP
    class ip_mac extends Thread{
        @Override 
        public void run(){
            String ipmac;
            InetAddress ip;
            try {
                //IP Address Code
                ip = InetAddress.getLocalHost();
                ip_add.setText(ip.getHostAddress());
                
                //MAC Address Code
                    //get port name
                    NetworkInterface network = NetworkInterface.getByInetAddress(ip);
                    System.out.println(network);
                    net_add.setText("[ " +network.toString()+ " ]");
                                        
                    //get mac address
                    byte[] mac = network.getHardwareAddress();
                    //display mac adress
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < mac.length; i++) {
			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
                    }             
                    ipmac=sb.toString();
                    System.out.println(ipmac);
                    mac_add.setText(ipmac); // Display Mac Add
                    
            }    catch (UnknownHostException | SocketException e) {
		Logger.getLogger(Login_screenController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
    //OTP GENERATE FUNTION
    public void Random(){
        Random rd=new Random();
        hide.setText(""+rd.nextInt(10000+1));
    }
    
    //SEND OTP FUNTION
    public void Sendotp(){
        // TEMPORARY: Show OTP directly in app instead of sending email
        // This allows testing without Gmail setup issues
        
        try {
            // Generate and display OTP directly
            String otpValue = hide.getText();
            error.setText("OTP is: " + otpValue + " (Email sending disabled for testing)");
            
            // Uncomment the email code below once Gmail is properly configured
            /*
            Properties props=new Properties();
            props.put("mail.smtp.host","smtp.gmail.com");
            props.put("mail.smtp.port",587);
            props.put("mail.smtp.user","sohampatilsp55@gmail.com");
            props.put("mail.smtp.auth",true);
            props.put("mail.smtp.starttls.enable",true);
            props.put("mail.smtp.debug",true);
            
            Session session = Session.getDefaultInstance(props, null);
            session.setDebug(true);
            MimeMessage message = new MimeMessage(session);
            message.setText("Your OTP is " + hide.getText());
            message.setSubject("OTP For your ThirdEye Account");
            message.setFrom(new InternetAddress("sohampatilsp55@gmail.com"));
            message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email.getText().trim()));
            message.saveChanges();
            
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com","sohampatilsp55@gmail.com","fjci ztfm achj pcqc");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            
            error.setText("OTP is been sent to your registered Email");
            */
            
        } catch (Exception e) {
            error.setText("Error: " + e.getMessage());
            System.out.println("OTP Error: " + e.getMessage());
        }
    }
    
    // LOGIN FUNCTION
    private String Login() { 
        //test connection 
        try {
            // db parameters
            // create a connection to the database
            conn = DriverManager.getConnection("jdbc:sqlite:login.sqlite");
            System.out.println("Connection to SQLite has been established.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        
        //Login Code
        String status = "Success";
        String e_mail = email.getText();
        String pass = password.getText();
        if(e_mail.isEmpty() || pass.isEmpty()) {
            error.setText("Empty credentials");
            status = "Error";
        } else {
            //query 
            String sql = "SELECT * FROM login_data Where email = ? and password = ?";
            try {
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, e_mail);
                preparedStatement.setString(2, pass);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    System.out.println("Enter Correct Email/Password");
                    status = "Error";
                } else {
                    System.out.println("Login Successful");
                    loginerror.setText("successful");
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }
        return status;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new ip_mac().start(); //RUN FOR MAC AND IP SHOWING
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource()==send){
            Login();  // Log IN Code
            // This code will run only when the login is successful
            if (loginerror.getText().equals(loginmsg.getText())) {
                Random(); //OTP Generator
                send.setVisible(false);
                otp.setVisible(true);
                verify.setVisible(true);
                Sendotp(); // OTP Funtion
            } else {
                error.setText("Enter Correct Email/Password");
            }
        }else if(event.getSource()==verify){
            // If OTP is verified only then move to new screen
            if (hide.getText().equals(otp.getText())) {
                //OPEN NEW WINDOW FUNTION
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("menu.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setTitle("New Window");
                    stage.setScene(scene);
                    stage.resizableProperty().setValue(false);
                    stage.show();
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                } catch (IOException e) {
                    Logger logger = Logger.getLogger(getClass().getName());
                    logger.log(Level.SEVERE, "Failed to create new Window.", e);
                }
            } else {
                error.setText("OTP is Incorrect");
            }
        }
    }
}