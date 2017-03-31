/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edufarming;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Study
 */
public class LogInController implements Initializable {
    java.sql.Connection conn = null;
    @FXML
    private ImageView logo;
    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_password;
    @FXML
    private Button btn_logIn;
    @FXML
    private Button btn_forgotPassword;
    @FXML
    private Button btn_back;
    
    @FXML
    private Rectangle rect;
    @FXML
    private Label header_label;
    @FXML
    private Rectangle rect1;
    @FXML
    private Label wpalert;
    
    String lname="";
    String fname="";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void log_in(ActionEvent event) throws IOException {
        initialize();

        if (check_log_In() == 1) {
            if (check_farming_status() == 0) {
                Stage stage = (Stage) btn_logIn.getScene().getWindow();
                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("after_log_In.fxml"));
                Parent root = (Parent)fxmlloader.load();
                Scene scene1 = new Scene(root);
                scene1.getStylesheets().add("myCSS.css");
                stage.setScene(scene1);
                After_log_InController al=fxmlloader.<After_log_InController>getController();
                al.passOnInfo(fname, lname);
                al.setUsername();
                stage.show();
            }
            else if (check_farming_status() == 1){
                Stage stage = (Stage) btn_logIn.getScene().getWindow();
                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("experience_farmer.fxml"));
                Parent root = (Parent)fxmlloader.load();
                Scene scene1 = new Scene(root);
                scene1.getStylesheets().add("myCSS.css");
                stage.setScene(scene1);
                Experience_farmerController ef=fxmlloader.<Experience_farmerController>getController();
                ef.passOnInfo(fname, lname);
                ef.setUsername();
                stage.show();
                
            }
        }
        else{
           wpalert.setText("Username/Password does not exist.\n "
                   + "Click Forgot Password if you've forgotten");
        }
           
    }

//    private void forget_password(ActionEvent event) throws IOException {
//        
//        FXMLLoader fxmlloader2 = new FXMLLoader(getClass().getResource("ForgotPassword.fxml"));
//        Parent root = (Parent)fxmlloader2.load();
//        Scene scene = new Scene(root);
//        Stage stage = new Stage(StageStyle.DECORATED);
//        scene.getStylesheets().add("myCSS.css");
//        stage.setScene(scene);
//        stage.show();
//        
//    }
    public int check_log_In(){
        int x=0;
        try {
            java.sql.Statement s = conn.createStatement();
            java.sql.ResultSet r = s.executeQuery("SELECT * FROM sign_up");
            while(r.next()) {
                if(r.getString("username").equalsIgnoreCase(txt_username.getText()) 
                        && r.getString("password").equalsIgnoreCase(txt_password.getText())){                   
                    x=1;
                    lname = r.getString("lastname").toUpperCase();
                    fname = r.getString("firstname");
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return x;
        }
        public void initialize(){
        try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = java.sql.DriverManager.getConnection(
        "jdbc:mysql://localhost/ICP?user=root&password=root");
                System.out.println("Connection established");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void forgetPassword(ActionEvent event) throws IOException {
        Stage stage=(Stage) btn_forgotPassword.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ForgotPassword.fxml"));
        Scene scene1 = new Scene(root);
        scene1.getStylesheets().add("myCSS.css");
        stage.setScene(scene1);
        stage.show();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Stage stage=(Stage) btn_logIn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene1 = new Scene(root);
        scene1.getStylesheets().add("myCSS.css");
        stage.setScene(scene1);
        stage.show();
    }
    
    
    public int check_farming_status(){
        String farming="";
        String userName ="";
        int y=0;
        try {
            java.sql.Statement s = conn.createStatement();
            java.sql.ResultSet r = s.executeQuery("SELECT * FROM sign_up WHERE username =" 
                    + "'" + txt_username.getText() + "'");
            while(r.next()) {
                 farming= r.getString("Farming_Status");
                 userName = r.getString("username");
                 
                if(farming.equalsIgnoreCase("Experienced Farmer") 
                        && txt_username.getText().equalsIgnoreCase(userName)){
                    y=1;
                }
                    
        }
            //System.out.println(farming);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return y;
        }


}
