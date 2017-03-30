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
public class QuizController implements Initializable {

    @FXML
    private Button btn_next;
    @FXML
    private ImageView logo;
    @FXML
    private Button btn_logOut;
    @FXML
    private ToggleGroup one;
    @FXML
    private ToggleGroup two;
    @FXML
    private ToggleGroup three;
    @FXML
    private ToggleGroup four;
    @FXML
    private Button btn_back;
    @FXML
    private RadioButton rb1;
    @FXML
    private RadioButton rb2;
    @FXML
    private RadioButton rb3;
    @FXML
    private RadioButton rb4;
    @FXML
    private RadioButton rb5;
    @FXML
    private RadioButton rb6;
    @FXML
    private RadioButton rb7;
    @FXML
    private RadioButton rb8;
    @FXML
    private RadioButton rb9;
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
    private void next(ActionEvent event) throws IOException {
        if(rb2.isSelected()){
            score++;
        }
        if(rb6.isSelected()){
            score++;
        }
        if(rb8.isSelected()){
            score++;
        }
        if(rb11.isSelected()){
            score++;
        }
        
        
        Stage stage=(Stage) btn_next.getScene().getWindow();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Quiz1.fxml"));
        Parent root2 = (Parent)fxmlloader.load();
        Scene scene1 = new Scene(root2);
        stage.setScene(scene1);
        Quiz1Controller q1c=fxmlloader.<Quiz1Controller>getController();
        q1c.passOn(score);
        q1c.passOnInfo(firName, lasName);
        q1c.setUsername();
        stage.show();    
    }

    @FXML
    private void logOut(ActionEvent event) throws IOException, IOException {
        Stage stage=(Stage) btn_logOut.getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("LogOut.fxml"));
        Scene scene1 = new Scene(root2);
        stage.setScene(scene1);
        stage.show();
        
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Stage stage=(Stage) btn_back.getScene().getWindow();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("after_log_In.fxml"));
        Parent root2 = (Parent)fxmlloader.load();
        Scene scene1 = new Scene(root2);
        stage.setScene(scene1);
        After_log_InController al=fxmlloader.<After_log_InController>getController();
        al.passOnInfo(firName, lasName);
        al.setUsername();
        stage.show();
        
    }
    public void passOnInfo(String fName, String lName){
        lasName = lName;
        firName = fName;
    }
    public void setUsername(){
        txt_DisplayName.setText(firName+"."+lasName.charAt(0));
}
    
}
