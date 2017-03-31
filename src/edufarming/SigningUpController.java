/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edufarming;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Frederick
 */
public class SigningUpController implements Initializable {

    java.sql.Connection conn = null;
    @FXML
    private Button btn_back;
    @FXML
    private ImageView logo;
    @FXML
    private ComboBox<String> cb_farmingStatus;
    @FXML
    private Button btn_createAccount;
    @FXML
    private TextField txt_fname;
    @FXML
    private TextField txt_lname;
    @FXML
    private TextField txt_username;
    @FXML
    private DatePicker txt_dateOfBirth;
    @FXML
    private TextField txt_password;
    @FXML
    private TextField txt_cpasswod;
    @FXML
    private TextField txt_email;
    @FXML
    private ComboBox<String> cb_question;
    @FXML
    private TextField txt_answer;
    @FXML
    private Rectangle signup_rectBig;
    @FXML
    private Rectangle signup_rect;
    @FXML
    private Label header_label2;
    @FXML
    private Label txt_alert;
    String lname="";
    String fname="";

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cb_farmingStatus.getItems().removeAll(cb_farmingStatus.getItems());
        cb_farmingStatus.getItems().addAll("Experienced Farmer", "New To Farming");
        cb_farmingStatus.getSelectionModel().select("Experienced Farmer");

        cb_question.getItems().removeAll(cb_question.getItems());
        cb_question.getItems().addAll("What is your favourite colour?",
                "What football team do you support?",
                "What is your favourite food?");

    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_back.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.show();
    }

    @FXML
    private void after_log_in(ActionEvent event) throws IOException {
        initialize();
        Calendar cal = Calendar.getInstance();
        java.sql.Date dat =null;
        cal.setTime( java.sql.Date.valueOf(txt_dateOfBirth.getValue()));
            
        int year=cal.get(Calendar.YEAR);
        char[] arr1 = txt_fname.getText().toCharArray();
        char[] arr2 = txt_lname.getText().toCharArray();
        String chx = "!@#$%^&*()";
        boolean test = false;
        for (int i = 0; i < arr1.length; i++) {
            if (chx.indexOf(arr1[i]) != -1) {
                test=true;
            }
        }
        if(test==true){
            txt_alert.setText("Special characters not allowed");
        }
        else if((2017-year)<18){
            txt_alert.setText("Not for persons under 18!");
        }
        else{
        
        if (authentication() == 0) {
            if (txt_password.getText().equals(txt_cpasswod.getText())) {
                addRecord();
                String status = cb_farmingStatus.getValue();
                if (cb_farmingStatus.getValue().equalsIgnoreCase("New To Farming")) {
                    Stage stage = (Stage) btn_back.getScene().getWindow();
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
                if (cb_farmingStatus.getValue().equalsIgnoreCase("Experienced Farmer")) {
                    Stage stage1 = (Stage) btn_back.getScene().getWindow();
                    FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("experience_farmer.fxml"));
                    Parent root = (Parent)fxmlloader.load();
                    Scene scene1 = new Scene(root);
                    scene1.getStylesheets().add("myCSS.css");
                    stage1.setScene(scene1);
                    Experience_farmerController ef=fxmlloader.<Experience_farmerController>getController();
                    ef.passOnInfo(fname, lname);
                    ef.setUsername();
                    stage1.show();

                }

            } else {
                txt_alert.setText("Password and Confirm password\ndo not match");
            }
        } else {
            txt_alert.setText("Username already exists");
        }
    }
    }

    public void initialize() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost/ICP?user=root&password=root");
            System.out.println("Connection established");
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    public void addRecord() {

        char[] arr1 = txt_fname.getText().toCharArray();
        char[] arr2 = txt_lname.getText().toCharArray();
        String chx = "!@#$%^&*()";
        boolean test = false;
        for (int i = 0; i < arr1.length; i++) {
            if (chx.indexOf(arr1[i]) != -1) {
                test=true;
            }
        }
        if(test==true){
            txt_alert.setText("Special characters not allowed");
        }
        else{
            
       

        authentication();
        if (authentication() == 0) {
            String fname = txt_fname.getText();
            String lname = txt_lname.getText();
            String username = txt_username.getText();
            java.sql.Date d_o_b = java.sql.Date.valueOf(txt_dateOfBirth.getValue());
            String password = txt_password.getText();
            String cpassword = txt_cpasswod.getText();
            String farmingStatus = cb_farmingStatus.getValue();
            String emailAddress = txt_email.getText();
            String questions = cb_question.getValue();
            String answer = txt_answer.getText();
            try {
                PreparedStatement p = conn.prepareStatement("Insert Into sign_up set firstname=?,"
                        + " lastname=? ,username=?,Date_of_Birth=?,Farming_Status=?,password=?,"
                        + " confirm_password=?, email=?, question=?, answer=?");
                p.setString(1, fname);
                p.setString(2, lname);
                p.setString(3, username);
                p.setDate(4, d_o_b);
                p.setString(5, farmingStatus);
                p.setString(6, password);
                p.setString(7, cpassword);
                p.setString(8, emailAddress);
                p.setString(9, questions);
                p.setString(10, answer);
                p.execute(); //use execute if no results expected back
            } catch (Exception e) {
                System.out.println("Error" + e.toString());
                return;
            }

        } else if (authentication() == 1) {
            txt_alert.setText("Username already exists. Choose another username!");
        }
    }
    }
    
    public int authentication() {
        int x = 0;
        try {
            java.sql.Statement s = conn.createStatement();
            java.sql.ResultSet r = s.executeQuery("SELECT * FROM sign_up");
            while (r.next()) {
                if (r.getString("username").equalsIgnoreCase(txt_username.getText())) {
                    x = 1;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return x;
    }
    

}
