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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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
    java.sql.Connection conn = null;
    @FXML
    private ScrollPane display;
    @FXML
    private TextArea description;
    @FXML
    private TextArea txt_display;
    @FXML
    private ImageView image;

    java.sql.ResultSet r;

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
            java.sql.ResultSet r = s.executeQuery("SELECT * FROM information WHERE cropName = 'Cabbage'");
            while (r.next()) {
                String info = r.getString("cropName").toUpperCase() + "\n\nPlanting:\n\n" + r.getString("planting")
                        + "\n\nNuture/Care:\n\n" + r.getString("care") + "\n\nPest and Disease:\n\n" + r.getString("pest_diseases")
                        + "\n\nHarvest and Storage:\n\n" + r.getString("harvest_storage");
                txt_display.setText(info);
                description.setText("\n" + r.getString("description"));
                image.setImage(SwingFXUtils.toFXImage(ImageIO.read(r.getBlob("image").getBinaryStream()), null));

            }
            //System.out.println(farming);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @FXML
    private void back(ActionEvent event) throws IOException {
        Stage stage = (Stage) description.getScene().getWindow();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("experience_farmer.fxml"));
        Parent root2 = (Parent) fxmlloader.load();
        Scene scene1 = new Scene(root2);
        stage.setScene(scene1);
        Experience_farmerController ef = fxmlloader.<Experience_farmerController>getController();
        ef.passOnInfo(firName, lasName);
        ef.setUsername();
        stage.show();
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
    String firName = "";
    String lasName = "";

    int i = 1;
    ArrayList<String> names = new ArrayList<>();

    @FXML
    private void next(ActionEvent event) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost/ICP?user=root&password=root");
            System.out.println("Connection established");
            java.sql.Statement s = conn.createStatement();
            java.sql.ResultSet r = s.executeQuery("SELECT * FROM information");
            while (r.next()) {
                names.add(r.getString("cropName"));
            }
            //java.sql.Statement a = conn.createStatement();
            java.sql.ResultSet t = s.executeQuery("SELECT * FROM information WHERE cropName = '" + names.get(i) + "'");
            
            while (t.next()) {
                String info = t.getString("cropName").toUpperCase() + "\n\nPlanting:\n\n" + t.getString("planting")
                        + "\n\nNuture/Care:\n\n" + t.getString("care") + "\n\nPest and Disease:\n\n" + t.getString("pest_diseases")
                        + "\n\nHarvest and Storage:\n\n" + t.getString("harvest_storage");
                txt_display.setText(info);
                description.setText("\n" + t.getString("description"));
                image.setImage(SwingFXUtils.toFXImage(ImageIO.read(t.getBlob("image").getBinaryStream()), null));

            }
            if (i < names.size()) {
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    private void previous(ActionEvent event) {
        if (i >= 1) {
            i--;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = java.sql.DriverManager.getConnection(
                        "jdbc:mysql://localhost/ICP?user=root&password=root");
                System.out.println("Connection established");
                java.sql.Statement s = conn.createStatement();
                java.sql.ResultSet r = s.executeQuery("SELECT * FROM information");
                while (r.next()) {
                    names.add(r.getString("cropName"));
                }
                //java.sql.Statement a = conn.createStatement();
                java.sql.ResultSet t = s.executeQuery("SELECT * FROM information WHERE cropName = '" + names.get(i) + "'");
               
                while (t.next()) {
                    String info = t.getString("cropName").toUpperCase() + "\n\nPlanting:\n\n" + t.getString("planting")
                            + "\n\nNuture/Care:\n\n" + t.getString("care") + "\n\nPest and Disease:\n\n" + t.getString("pest_diseases")
                            + "\n\nHarvest and Storage:\n\n" + t.getString("harvest_storage");
                    txt_display.setText(info);
                    description.setText("\n" + t.getString("description"));
                    image.setImage(SwingFXUtils.toFXImage(ImageIO.read(t.getBlob("image").getBinaryStream()), null));

                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    public void passOnInfo(String fName, String lName) {
        lasName = lName;
        firName = fName;
    }
    public void setUsername() {
        txt_DisplayName.setText(firName + "." + lasName.charAt(0));
    }

}
