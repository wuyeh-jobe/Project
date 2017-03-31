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
import javafx.event.EventHandler;
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
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Study
 */
public class InfoDisplayController implements Initializable {

    @FXML
    private TextArea text_Area_farm;
    @FXML
    private Button next;
    @FXML
    private Button prev;
    @FXML
    private Button back_btn;
    String descrip;
    java.sql.Connection conn = null;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private ImageView cropAninName;
    String lasName="";
    String firsName="";
    @FXML
    private Text txt_DisplayName;
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
        // TODO
    }

    @FXML
    private void nextPage(ActionEvent event) {
    }

    @FXML
    private void prevPage(ActionEvent event) {
    }

    @FXML
    private void backOpt(ActionEvent event) throws IOException {
        Stage stage = (Stage) back_btn.getScene().getWindow();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("experience_farmer.fxml"));
        Parent root2 = (Parent) fxmlloader.load();
        Scene scene1 = new Scene(root2);
        stage.setScene(scene1);
        Experience_farmerController ef = fxmlloader.<Experience_farmerController>getController();
        ef.passOnInfo(firsName, lasName);
        ef.setUsername();
        stage.show();
    }


    public void setDescription(String data) {
        descrip = data;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost/ICP?user=root&password=root");
            System.out.println("Connection established");
            java.sql.Statement s = conn.createStatement();
            java.sql.ResultSet r = s.executeQuery("SELECT * FROM information WHERE description like "
                    + "'%" + data + "%'");
            while (r.next()) {
                String info = r.getString("cropName").toUpperCase() + "\n\nPlanting:\n\n" + r.getString("planting")
                        + "\n\nNuture/Care:\n\n" + r.getString("care") + "\n\nPest and Disease:\n\n" + r.getString("pest_diseases")
                        + "\n\nHarvest and Storage:\n\n" + r.getString("harvest_storage");
                text_Area_farm.setText(info);
                descriptionArea.setText("\n" + r.getString("description"));
                cropAninName.setImage(SwingFXUtils.toFXImage(ImageIO.read(r.getBlob("image").getBinaryStream()), null));

            }
            //System.out.println(farming);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
   public void passOnInfo(String fName, String lName) {
        lasName = lName;
        firsName = fName;
    }

    @FXML
    private void goToHomePaage(ActionEvent event) throws IOException {
        Stage stage = (Stage) back_btn.getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("experience_farmer.fxml"));
        Scene scene1 = new Scene(root2);
        scene1.getStylesheets().add("myCSS.css");
        stage.setScene(scene1);
        stage.show();
    }

    @FXML
    private void logoutFromMenu(ActionEvent event) throws IOException {
        Stage stage =(Stage) back_btn.getScene().getWindow();
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
