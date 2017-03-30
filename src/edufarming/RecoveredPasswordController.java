/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edufarming;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Frederick
 */
public class RecoveredPasswordController implements Initializable {
    
    public static java.sql.Connection conn = null;
    @FXML
    private ImageView logo;
    @FXML
    private Label txt_username;
    @FXML
    private Label txt_password;
    String uName="";
    String pword="";
    @FXML
    private Button btn_pro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
    }    

    @FXML
    private void proceed(ActionEvent event) throws IOException {
        Stage stage=(Stage) btn_pro.getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("logIn.fxml"));
        Scene scene1 = new Scene(root2);
        stage.setScene(scene1);
        stage.show();
    }
    
    public void passOn(String user){
        uName=user;
    }
    
    public void show(){
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost/ICP?user=root&password=root");
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        System.out.println("Connection established");
        try {
            java.sql.Statement s = conn.createStatement();
            java.sql.ResultSet r = s.executeQuery("SELECT * FROM sign_up WHERE "
                    + "username= " + "'"+uName+"'");
            while (r.next()) {
                pword = r.getString("password");
                
            }
            
            r.close();
            s.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        txt_username.setText("The Username is: " + uName);
        txt_password.setText("The Password is: " + pword);
    }
}
