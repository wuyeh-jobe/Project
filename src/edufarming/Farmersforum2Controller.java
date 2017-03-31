/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edufarming;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Study
 */
public class Farmersforum2Controller implements Initializable {
    java.sql.Connection conn = null;
    @FXML
    private ImageView logo;
    @FXML
    private Text txt_DisplayName;
    @FXML
    private Button btn_back;
    @FXML
    private MenuItem hm_page;
    @FXML
    private MenuItem expfarmLogout_btn;
    @FXML
    private MenuItem expFarmExit_btn;
    @FXML
    private MenuItem expOnlRes_menu;
    @FXML
    private ScrollPane display;
    @FXML
    private TextArea txt_display;
    String lasName = "";
    String firName = "";
    @FXML
    private Label farmTips;
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
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("after_log_In.fxml"));
        Parent root2 = (Parent)fxmlloader.load();     
        Scene scene1 = new Scene(root2);
        stage.setScene(scene1);
        After_log_InController al=fxmlloader.<After_log_InController>getController();  
        al.passOnInfo(firName, lasName);
        al.setUsername();
        stage.show();
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
    private void logoutFromMenu(ActionEvent event) throws IOException {
        Stage stage=(Stage) btn_back.getScene().getWindow();
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

    public void passOnInfo(String fName, String lName){
        lasName = lName;
        firName = fName;
    }
    public void setInfo(){
        StringBuilder b = new StringBuilder();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost/ICP?user=root&password=root");
            System.out.println("Connection established");
            java.sql.Statement s = conn.createStatement();
            java.sql.ResultSet r = s.executeQuery("SELECT * FROM experience_farmer_info");
            while (r.next()) {
                b.append("Title: "+r.getString("title")  + "\nPost: "+r.getString("information")+ "\nFarmer: "+r.getString("personName") 
                        +"   Date/Time: "+r.getString("timeposted")+"\n\n");
            }
            txt_display.setText(b.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
