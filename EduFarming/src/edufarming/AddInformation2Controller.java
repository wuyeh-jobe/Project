/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edufarming;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Study
 */
public class AddInformation2Controller implements Initializable {

    @FXML
    private TextArea description;
    @FXML
    private Button addinfo_btn;
    @FXML
    private Label farmTips;
    @FXML
    private TextField title_bar;
    @FXML
    private Label title_text;
    @FXML
    private Label content_txt;
    @FXML
    private TextField search_bar;
    @FXML
    private Label search_txt;
    @FXML
    private Rectangle imgBorder_rect;
    @FXML
    private Button btn_logOut;
    @FXML
    private Label user_id;
    @FXML
    private Button back_btn;
    @FXML
    private Label username_lab;
    @FXML
    private Button addImg_btn;
    @FXML
    private ImageView addImage_img;
    @FXML
    private TextArea planting_reproduction;
    @FXML
    private Label content_txt1;
    @FXML
    private TextArea care;
    @FXML
    private Label content_txt11;
    @FXML
    private TextArea harvest_storage;
    @FXML
    private Label content_txt12;
    @FXML
    private TextArea pest_disease;
    @FXML
    private Label content_txt121;

    private File file;
    Connection connect = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addInform(ActionEvent event) throws IOException {
        /*the information to be added is to be appended to the database-->
        *still under construction
        */
        initialize();
        String title = title_bar.getText();
        String descr = description.getText();
        String plant_repr = planting_reproduction.getText();
        String plantCare = care.getText();
        String pes_dis = pest_disease.getText();
        String har_store = harvest_storage.getText();
        try{
            FileInputStream fis = new FileInputStream(file);
            
            PreparedStatement info = connect.prepareStatement("Insert into Information set cropName=?, "
                    + "description=?, planting=?, care=?, pest_diseases=?, harvest_storage=?, image=?");
            info.setString(1, title);
            info.setString(2, descr);
            info.setString(3, plant_repr);
            info.setString(4, plantCare);
            info.setString(5, pes_dis);
            info.setString(6, har_store);
            info.setBlob(7, fis);
            info.execute();
            
        }catch(Exception e){
            System.out.println(e.toString());
        }
        Stage stage=(Stage) addinfo_btn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AddInformation2.fxml"));
        Scene scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.show();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Stage stage=(Stage) btn_logOut.getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("LogOut.fxml"));
        Scene scene1 = new Scene(root2);
        stage.setScene(scene1);
        stage.show();
    }

    @FXML
    private void go_back(ActionEvent event) throws IOException {
        Stage stage=(Stage) addinfo_btn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("experience_farmer.fxml"));
        Scene scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.show();
    }
    
    public void initialize() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost/ICP?user=root&password=root");
            System.out.println("Connection established");
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    @FXML
    private void addImg(ActionEvent event) {
        FileChooser fileC = new FileChooser();
        
        FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("Portable Network Graphics files (*.png)","*.png");
        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPEG Files (*.jpg)","*.jpg");
        
        fileC.getExtensionFilters().add(pngFilter);
        fileC.getExtensionFilters().add(jpgFilter);
        
        file = fileC.showOpenDialog(null);
        if (file !=null){
            try{
                BufferedImage br = ImageIO.read(file);
                Image img = SwingFXUtils.toFXImage(br, null);
                addImage_img.setImage(img);         
            }catch(Exception e){
                e.toString();
            }
        }
    }
    
    
}
