/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edufarming;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Study
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private ImageView logo;
    @FXML
    private AnchorPane main_page;
    @FXML
    private Button btn_farmer;
    @FXML
    private Button btn_newToFarming;
    @FXML
    private Button btn_logIn;
    @FXML
    private Button btn_signUp;
    @FXML
    private Button btn_close;
    @FXML
    private TextArea display;
    @FXML
    private Button admin_btn;
    
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void signUp(ActionEvent event) throws IOException {
        Stage stage=(Stage) btn_logIn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("SigningUp.fxml"));
        Scene scene1 = new Scene(root);
        scene1.getStylesheets().add("myCSS.css");
        stage.setScene(scene1);
        stage.show();
    }

    @FXML
    private void logIn(ActionEvent event) throws IOException {
        Stage stage=(Stage) btn_logIn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("logIn.fxml"));
        Scene scene1 = new Scene(root);
        scene1.getStylesheets().add("myCSS.css");
        stage.setScene(scene1);
        stage.show();
        
        

    }

    @FXML
    private void close(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void expFarmer(ActionEvent event) {
        display.setText("As an experienced farmer, you should be "
                 + "well acquinted with the following:\n•Different modes of farming"
                 + "\n•Animal Farming \nThis system also allows you to keep "
                 + "track of your inventories  and also allows you to give"
                 + "additional tips for those who are new to farming! We would "
                 + "love to hear from you.");
        display.setWrapText(true);
    }

    @FXML
    private void newFarmer(ActionEvent event) {
        display.setText("As a new farmer, here's what to consider: \n"
                 + "•You should carefully plan on how your farm should look like for maximum efficiency \n"
                 + "• The location that you are to set your farm matters, so choose wisely. \n"
                 + "•Your farm needs to have a well defined business plan(viability, feasibility and durability).\n"
                 + "•Preparation, knowledge and training are essential and also the need to adapt quickly.\n"
                 + "•It is also helpful to carefully plan to manage risks because business activities come with risks \n"
                 + "•Always start small to allow details to be worked out.");
        display.setWrapText(true);
    }

    @FXML
    private void admin(ActionEvent event) throws IOException {
        Stage stage=(Stage) btn_logIn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AddInformation2.fxml"));
        Scene scene1 = new Scene(root);
        scene1.getStylesheets().add("myCSS.css");
        stage.setScene(scene1);
        stage.show();
    }

    

    
    

    
}
