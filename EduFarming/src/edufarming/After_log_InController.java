/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edufarming;

import static edufarming.AddToTableController.conn;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Study
 */
public class After_log_InController implements Initializable {

    @FXML
    private ImageView logo;
    @FXML
    private TextField txt_search;
    @FXML
    private Button btn_FAQs;
    @FXML
    private Button btn_infoFromExpFarmers;
    
    @FXML
    private Button btn_infoOnFarming;
    @FXML
    private Button btn_quiz;
    @FXML
    private Button btn_back;
    @FXML
    private Button btn_infoOnAnimalInfo;
    @FXML
    private Label topicArea;
    String firName="";
    String lasName="";
    @FXML
    private Text txt_DisplayName;
    @FXML
    private Button search_btn;
    @FXML
    private MenuItem hm_page;
    @FXML
    private MenuItem expfarmLogout_btn;
    @FXML
    private MenuItem expFarmExit_btn;
    @FXML
    private MenuItem expOnlRes_menu;
    @FXML
    private MenuItem aboutEduFarm;
    @FXML
    private MenuItem aboutCompany_menu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    private void back(ActionEvent event) throws IOException {
        Stage stage=(Stage) btn_back.getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene1 = new Scene(root2);
        stage.setScene(scene1);
        stage.show();
    
    }

    private void logOut(ActionEvent event) throws IOException {
        Stage stage=(Stage) search_btn.getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass()
                .getResource("LogOut.fxml"));
        Scene scene1 = new Scene(root2);
        stage.setScene(scene1);
        stage.show();
    }
    
    @FXML
    private void infoFarming(ActionEvent event) throws IOException {
        Stage stage=(Stage) btn_infoOnFarming.getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("Farming_info.fxml"));
        Scene scene1 = new Scene(root2);
        stage.setScene(scene1);
        stage.show();
    }

    @FXML
    private void startQuiz(ActionEvent event) throws IOException {
        Stage stage=(Stage) btn_quiz.getScene().getWindow();
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
    private void animInfo(ActionEvent event) throws IOException {
        Stage stage=(Stage) btn_infoOnAnimalInfo.getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("Animal_farming_info.fxml"));
        Scene scene1 = new Scene(root2);
        stage.setScene(scene1);
        stage.show();
    }
    public void passOnInfo(String fName, String lName){
        lasName = lName;
        firName = fName;
    }
    public void setUsername(){
        txt_DisplayName.setText(firName+"."+lasName.charAt(0));
}

    @FXML
    private void search(ActionEvent event) {
    }

    @FXML
    private void goToHomePaage(ActionEvent event) {
    }

    @FXML
    private void logoutFromMenu(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoad = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root2 = (Parent) fxmlLoad.load();
        Scene scene1 = new Scene(root2);
        Stage stage = new Stage();
        scene1.getStylesheets().add("myCSS.css");
        stage.setScene(scene1);
        stage.show();
    }

    @FXML
    private void exitFromMenu(ActionEvent event) {
    }

    @FXML
    private void goOnline(ActionEvent event) {
    }

    @FXML
    private void viewAboutApp(ActionEvent event) {
    }

    @FXML
    private void viewAboutCompany(ActionEvent event) {
    }

    
}
