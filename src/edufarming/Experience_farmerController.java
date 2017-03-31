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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Study
 */
public class Experience_farmerController implements Initializable {

    @FXML
    private ImageView logo;
    @FXML
    private TextField txt_search;
    @FXML
    private Button btn_farmsDatabase;
    @FXML
    private Button btn_inventoryKeeping;
    @FXML
    private Button btn_addInfo;
    @FXML
    private Button btn_back;
    @FXML
    private TextArea textArea;
    @FXML
    private Text txt_DisplayName;
    String firName = "";
    String lasName = "";
    @FXML
    private Button search_btn;
    @FXML
    private VBox searchField;
    java.sql.Connection conn = null;
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

    private void anim(ActionEvent event) {
        textArea.setText("");
    }

    @FXML
    private void addInfo(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_addInfo.getScene().getWindow();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("AddInformation.fxml"));
        Parent root2 = (Parent) fxmlloader.load();
        Scene scene1 = new Scene(root2);
        scene1.getStylesheets().add("myCSS.css");
        stage.setScene(scene1);
        AddInformationController att = fxmlloader.<AddInformationController>getController();
        att.passOnInfo(firName, lasName);
        //att.setUsername();
        stage.show();
    }


    @FXML
    private void visitDatabase(ActionEvent event) {
    }

    @FXML
    private void goToInventory(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_inventoryKeeping.getScene().getWindow();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("AddToTable.fxml"));
        Parent root2 = (Parent) fxmlloader.load();
        Scene scene1 = new Scene(root2);
        scene1.getStylesheets().add("myCSS.css");
        stage.setScene(scene1);
        AddToTableController att = fxmlloader.<AddToTableController>getController();
        att.passOnInfo(firName, lasName);
        att.setUsername();
        stage.show();
    }


    @FXML
    private void back(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_back.getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass()
                .getResource("logIn.fxml"));
        Scene scene1 = new Scene(root2);
        scene1.getStylesheets().add("myCSS.css");
        stage.setScene(scene1);
        stage.show();
    }

    public void passOnInfo(String fName, String lName) {
        lasName = lName;
        firName = fName;
    }

    public void setUsername() {
        txt_DisplayName.setText(firName + "." + lasName.charAt(0));
    }

    @FXML
    private void search(ActionEvent event) {
        String descr = "";
        boolean found = false;
        ArrayList<Button> data = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost/ICP?user=root&password=root");
            System.out.println("Connection established");
            java.sql.Statement s = conn.createStatement();
            java.sql.ResultSet r = s.executeQuery("SELECT * FROM information WHERE description like "
                    + "'%" + txt_search.getText() + "%'" + " OR planting like " + 
                            "'%" + txt_search.getText() + "%'"+ " OR care like "+
                                    "'%" + txt_search.getText() + "%'"+ " OR pest_diseases like " +
                    "'%" + txt_search.getText()+ "%'"+ " OR harvest_storage like " + "'%" + txt_search.getText()+ "%'");
            while (r.next()) {
                found =true;
                descr = r.getString("description");
                Button btn = new Button();
                btn.setText(descr);
                searchField.getChildren().add(btn);
                data.add(btn);
            }
            for (int i = 0; i < data.size(); i++) {
                Button b = data.get(i);
                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            Stage stage = (Stage) b.getScene().getWindow();
                            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("InfoDisplay.fxml"));
                            Parent root2 = (Parent) fxmlloader.load();
                            Scene scene1 = new Scene(root2);
                            scene1.getStylesheets().add("myCSS.css");
                            stage.setScene(scene1);
                            InfoDisplayController id = fxmlloader.<InfoDisplayController>getController();
                            id.setDescription(b.getText());
                            id.passOnInfo(firName, lasName);
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(Experience_farmerController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        if(found==true){
            textArea.setText("Search Results: ");
        }else
            textArea.setText("No results found: ");
    }

    @FXML
    private void goToHomePaage(ActionEvent event) throws IOException {
        Stage stage = (Stage) search_btn.getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("experience_farmer.fxml"));
        Scene scene1 = new Scene(root2);
        scene1.getStylesheets().add("myCSS.css");
        stage.setScene(scene1);
        stage.show();
    }

    @FXML
    private void logoutFromMenu(ActionEvent event) throws IOException {
        Stage stage =(Stage) search_btn.getScene().getWindow();
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
