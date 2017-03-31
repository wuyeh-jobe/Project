/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edufarming;

import static edufarming.AddToTableController.conn;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
    private Button search_btn;
    @FXML
    private MenuItem hm_page;
    @FXML
    private MenuItem expfarmLogout_btn;
    @FXML
    private MenuItem expFarmExit_btn;
    @FXML
    private MenuItem expOnlRes_menu;

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
        Parent root2 = FXMLLoader.load(getClass().getResource("logIn.fxml"));
        Scene scene1 = new Scene(root2);
        scene1.getStylesheets().add("myCSS.css");
        stage.setScene(scene1);
        stage.show();
    
    }
    
    @FXML
    private void infoFarming(ActionEvent event) throws IOException {
        Stage stage=(Stage) btn_quiz.getScene().getWindow();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Farming_info.fxml"));
        Parent root2 = (Parent)fxmlloader.load();     
        Scene scene1 = new Scene(root2);
        scene1.getStylesheets().add("myCSS.css");
        stage.setScene(scene1);
        Farming_infoController fc=fxmlloader.<Farming_infoController>getController();  
        fc.passOnInfo(firName, lasName);
        stage.show();
    }

    @FXML
    private void startQuiz(ActionEvent event) throws IOException {
        Stage stage=(Stage) btn_quiz.getScene().getWindow();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Quiz.fxml"));
        Parent root2 = (Parent)fxmlloader.load();     
        Scene scene1 = new Scene(root2);
        scene1.getStylesheets().add("myCSS.css");
        stage.setScene(scene1);
        QuizController qc=fxmlloader.<QuizController>getController();  
        qc.passOnInfo(firName, lasName);
        qc.setUsername();
        stage.show();
    }

    @FXML
    private void animInfo(ActionEvent event) throws IOException {
        Stage stage=(Stage) btn_quiz.getScene().getWindow();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Animal_farming_info.fxml"));
        Parent root2 = (Parent)fxmlloader.load();     
        Scene scene1 = new Scene(root2);
        scene1.getStylesheets().add("myCSS.css");
        stage.setScene(scene1);
        Animal_farming_infoController ac=fxmlloader.<Animal_farming_infoController>getController();  
        ac.passOnInfo(firName, lasName);
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
    private void goToHomePaage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoad = new FXMLLoader(getClass().getResource("after_log_in.fxml"));
        Parent root2 = (Parent) fxmlLoad.load();
        Scene scene1 = new Scene(root2);
        Stage stage = new Stage();
        scene1.getStylesheets().add("myCSS.css");
        stage.setScene(scene1);
        stage.show();
    }

    @FXML
    private void logoutFromMenu(ActionEvent event) throws IOException{
        Stage stage=(Stage) btn_infoOnAnimalInfo.getScene().getWindow();
        FXMLLoader fxmlLoad = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root2 = (Parent) fxmlLoad.load();
        Scene scene1 = new Scene(root2);
        scene1.getStylesheets().add("myCSS.css");
        stage.setScene(scene1);
        stage.show();
    }

    @FXML
    private void exitFromMenu(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void goOnline(ActionEvent event) throws URISyntaxException, IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(new URI("http://almanac.com/plants"));
    }


    
}
