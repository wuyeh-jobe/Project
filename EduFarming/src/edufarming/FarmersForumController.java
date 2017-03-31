/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edufarming;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Study
 */
public class FarmersForumController implements Initializable {

    @FXML
    private TextField txt_search;
    @FXML
    private Button search_btn;
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
    private Button btn_back1;
    @FXML
    private Button btn_back11;
    @FXML
    private VBox display;
    java.sql.Connection conn = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //descrip = data;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost/ICP?user=root&password=root");
            System.out.println("Connection established");
            java.sql.Statement s = conn.createStatement();
            java.sql.ResultSet r = s.executeQuery("SELECT * FROM information WHERE cropName = 'beans'");
            while (r.next()) {
                String info = r.getString("cropName").toUpperCase() + "\n\nPlanting:\n\n" + r.getString("planting")
                        + "\n\nNuture/Care:\n\n" + r.getString("care") + "\n\nPest and Disease:\n\n" + r.getString("pest_diseases")
                        + "\n\nHarvest and Storage:\n\n" + r.getString("harvest_storage");
               
            }
            //System.out.println(farming);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void search(ActionEvent event) {
    }

    @FXML
    private void back(ActionEvent event) {
    }

    @FXML
    private void goToHomePaage(ActionEvent event) {
    }

    @FXML
    private void logoutFromMenu(ActionEvent event) {
    }

    @FXML
    private void exitFromMenu(ActionEvent event) {
    }

    @FXML
    private void goOnline(ActionEvent event) {
    }

}
