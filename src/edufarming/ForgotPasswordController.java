/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edufarming;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Frederick
 */
public class ForgotPasswordController implements Initializable {
    
    java.sql.Connection conn = null;
    @FXML
    private TextField txt_fname;
    @FXML
    private TextField txt_lname;
    @FXML
    private TextField txt_email;
    @FXML
    private ImageView logo;
    @FXML
    private TextField txt_username;
    @FXML
    private Button btn_back;
    @FXML
    private ComboBox<String> cb_question;
    @FXML
    private TextField txt_answer;
    @FXML
    private Button btn_confirm;
    @FXML
    private Label txt_error;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cb_question.getItems().removeAll(cb_question.getItems());
        cb_question.getItems().addAll("What is your favourite colour?",
                "What football team do you support?",
                "What is your favourite food?");
    }    

    @FXML
    private void back(ActionEvent event) throws IOException {
        Stage stage=(Stage) btn_back.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("logIn.fxml"));
        Scene scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.show();
    }

    @FXML
    private void confirm(ActionEvent event) throws IOException {
        if(checkDetails()==1){
           Stage stage=(Stage) btn_confirm.getScene().getWindow();
           FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("recoveredPassword.fxml"));
           Parent root = (Parent)fxmlloader.load();
           Scene scene1 = new Scene(root);
           stage.setScene(scene1);
           RecoveredPasswordController rp=fxmlloader.<RecoveredPasswordController>getController();
           rp.passOn(txt_username.getText());
           rp.show();
           stage.show();
        }
        else{
            txt_error.setText("Please check your details again");
        }
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
    
    public int checkDetails(){   
        initialize();
        
        String firstName = txt_fname.getText();
        String lastName = txt_lname.getText();
        String email = txt_email.getText();
        String userName = txt_username.getText();
        String question = cb_question.getValue();
        String answer = txt_answer.getText();
        int x =0;
        try {
            
            java.sql.Statement s = conn.createStatement();
            java.sql.ResultSet r = s.executeQuery("SELECT * FROM sign_up");
            while(r.next()) {
                if(r.getString("username").equalsIgnoreCase(userName) 
                        && r.getString("firstname").equalsIgnoreCase(firstName)
                        && r.getString("lastname").equalsIgnoreCase(lastName)
                        && r.getString("email").equalsIgnoreCase(email)
                        && r.getString("question").equalsIgnoreCase(question)
                        && r.getString("answer").equalsIgnoreCase(answer)){
                    x=1;
                }   
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return x;
    }
    

    
}
