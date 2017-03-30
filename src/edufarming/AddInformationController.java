/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edufarming;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.*;
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
 * @author study
 */
public class AddInformationController implements Initializable {
    
    Connection connect = null;
    @FXML
    private Button addinfo_btn;
    @FXML
    private TextArea input_bar;
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
    private Label user_id;
    @FXML
    private Button back_btn;
    @FXML
    private Button btn_logOut;
    @FXML
    private TextArea content_txtArea;
    @FXML
    private Rectangle imgBorder_rect;
    @FXML
    private Label username_lab;
    @FXML
    private Button addImg_btn;
    @FXML
    private ImageView addImage_img;
    
    private File file;
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
    private void addInform(ActionEvent event) throws IOException {
        /*the information to be added is to be appended to the database-->
        *still under construction
        */
        initialize();
        String title = title_bar.getText();
        String input = content_txtArea.getText();
        try{
            FileInputStream fis = new FileInputStream(file);
            
            PreparedStatement info = connect.prepareStatement("Insert into farmers_Info set title=?, information=?, image=?");
            info.setString(1, title);
            info.setString(2, input);
            info.setBlob(3, fis);
            info.execute();
            
        }catch(Exception e){
            System.out.println(e.toString());
        }
        //takes the user back to experienced_farmer window
        Stage stage=(Stage) addinfo_btn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("experience_farmer.fxml"));
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
        /*Parent root = FXMLLoader.load(getClass().getResource("experience_farmer.fxml"));
        Scene scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.show();*/
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("experience_farmer.fxml"));
        Parent root2 = (Parent)fxmlloader.load();     
        Scene scene1 = new Scene(root2);
        stage.setScene(scene1);
        Experience_farmerController ef=fxmlloader.<Experience_farmerController>getController();  
        ef.passOnInfo(firName, lasName);
        ef.setUsername();
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
    public void passOnInfo(String fName, String lName){
        lasName = lName;
        firName = fName;
    }
    
    
}
