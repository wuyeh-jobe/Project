/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edufarming;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ekiyor.odoko
 */
public class Farmers_DatabaseController implements Initializable {

    @FXML
    private Button back_btn;
    @FXML
    private Button logout_btn;
    @FXML
    private ImageView edufarming_img;
    @FXML
    private TextField search_txtField;
    @FXML
    private ImageView searchIcon_img;
    @FXML
    private Label search_lab;
    @FXML
    private Label fPlatmorm_lab;
    @FXML
    private Label username_label;
    @FXML
    private TextArea info_txtArea;
    @FXML
    private Button next_btn;
    @FXML
    private Button prev_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void go_back(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
    }

    @FXML
    private void next(ActionEvent event) {
    }

    @FXML
    private void previous(ActionEvent event) {
    }
    
}
