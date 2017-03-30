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
import java.util.*;
/**
 * FXML Controller class
 *
 * @author ekiyor.odoko
 */
public class CertificateFXMLController implements Initializable {

    @FXML
    private ImageView cert_img;
    @FXML
    private Label name_lbl;
    @FXML
    private Label percScore_lbl;
    @FXML
    private Button logout_btn;
    @FXML
    private Button home_btn;
    
    int score=0;
    
    String firName="";
    String lasName="";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void logOut(ActionEvent event) throws IOException {
        Stage stage=(Stage) logout_btn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("LogOut.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToHomepage(ActionEvent event) throws IOException {
        System.out.println(score);
        Stage stage = (Stage) home_btn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("experience_farmer.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void passOn(int s){
        score = s;      
    }
    public void passOnInfo(String fName, String lName){
        lasName = lName;
        firName = fName;
    }
    public void setResult(){
        int percent = score*10;
        percScore_lbl.setText(percent + "%");
    }
    public void setName(){
        name_lbl.setText(lasName + ", " + firName);//We will use wuyeh's passOn() mwthod to transfer the name of the user
    }
}
