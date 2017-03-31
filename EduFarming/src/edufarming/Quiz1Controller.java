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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Frederick
 */
public class Quiz1Controller implements Initializable {

    @FXML
    private Button btn_back;
    @FXML
    private Button btn_next;
    @FXML
    private ImageView logo;
    @FXML
    private Button btn_logOut;
    @FXML
    private RadioButton rb1;
    @FXML
    private ToggleGroup one;
    @FXML
    private RadioButton rb2;
    @FXML
    private RadioButton rb3;
    @FXML
    private RadioButton rb4;
    @FXML
    private ToggleGroup two;
    @FXML
    private RadioButton rb5;
    @FXML
    private RadioButton rb6;
    @FXML
    private RadioButton rb7;
    @FXML
    private ToggleGroup three;
    @FXML
    private RadioButton rb8;
    @FXML
    private RadioButton rb9;
    @FXML
    private ToggleGroup four;
    @FXML
    private RadioButton rb10;
    @FXML
    private RadioButton rb11;
    @FXML
    private RadioButton rb12;
    int score=0;
    String firName="";
    String lasName="";
    @FXML
    private Text txt_DisplayName;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void back(ActionEvent event) throws IOException {
        Stage stage=(Stage) btn_back.getScene().getWindow();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Quiz.fxml"));
        Parent root2 = (Parent)fxmlloader.load();
        Scene scene1 = new Scene(root2);
        stage.setScene(scene1);
        QuizController qc=fxmlloader.<QuizController>getController();  
        qc.passOnInfo(firName, lasName);
        qc.setUsername();
        stage.show();
    }

    @FXML
    private void next(ActionEvent event) throws IOException {
        if(rb1.isSelected()){
            score++;
        }
        if(rb4.isSelected()){
            score++;
        }
        if(rb7.isSelected()){
            score++;
        }
        if(rb12.isSelected()){
            score++;
        }
        Stage stage=(Stage) btn_next.getScene().getWindow();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Quiz2.fxml"));
        Parent root2 = (Parent)fxmlloader.load();
        Scene scene1 = new Scene(root2);
        stage.setScene(scene1);
        Quiz2Controller q2c=fxmlloader.<Quiz2Controller>getController();
        q2c.passOn(score);
        q2c.passOnInfo(firName, lasName);
        q2c.setUsername();
        stage.show();
    }

    @FXML
    private void logOut(ActionEvent event) throws IOException {
        Stage stage=(Stage) btn_logOut.getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("LogOut.fxml"));
        Scene scene1 = new Scene(root2);
        stage.setScene(scene1);
        stage.show();
    }
    
    public void passOn(int s){
        score = s;      
    }
    public void passOnInfo(String fName, String lName){
        lasName = lName;
        firName = fName;
    }
    public void setUsername(){
        txt_DisplayName.setText(firName+"."+lasName.charAt(0));
}
    
}
