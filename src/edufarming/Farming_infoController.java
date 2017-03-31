/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edufarming;

import static edufarming.AddToTableController.conn;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Wayne Gakuo
 */
public class Farming_infoController implements Initializable {

    @FXML
    private Label topicFarm;
    @FXML
    private TextArea text_Area_farm;
    @FXML
    private Button next;
    @FXML
    private Button prev;
    @FXML
    private Button back_btn;
    
    int i=1;

    /**
     * Initializes the controller class.
     * connects to the database
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
         
         try {
            java.sql.Statement s = conn.createStatement();
            java.sql.ResultSet r = s.executeQuery("SELECT * FROM farmers_Info where title='Farming for Beginners'");
            while(r.next()) {
                String t= r.getString("title");
                String info=r.getString("information");
                topicFarm.setText(t);
                text_Area_farm.setText(info);
                text_Area_farm.setWrapText(true);
                
        }
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }    

    /**
     * 
     * @param button event for next
     * auto-increment feature in the database
     * as it moves next
     */
    @FXML
    private void nextPage(ActionEvent event) {
        String x = "SELECT * FROM farmers_Info where id="+i;
        System.out.println(x);
        try {
            java.sql.Statement s = conn.createStatement();
            java.sql.ResultSet r = s.executeQuery(x);
            while(r.next()) {
                String t= r.getString("title");
                String info=r.getString("information");
                topicFarm.setText(t);
                text_Area_farm.setText(info);
                text_Area_farm.setWrapText(true);
                i++;
                
        }
        }
        catch (Exception e) {
            System.out.println(e);
        }  
    }
    
    /**
     * 
     * @param button event for previous
     * auto-decrement in the database
     * as it accesses previous files
     */
    @FXML
    private void prevPage(ActionEvent event) {
        if (i>1){
        i--;
        }
        try {
            java.sql.Statement s = conn.createStatement();
            java.sql.ResultSet r = s.executeQuery("SELECT * FROM farmers_Info where id="+i);
            while(r.next()) {
                String t= r.getString("title");
                String info=r.getString("information");
                topicFarm.setText(t);
                text_Area_farm.setText(info);
                text_Area_farm.setWrapText(true);
                
        }
       }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
    /**
     * 
     * @param button action for back
     * @throws IOException 
     */
    @FXML
    private void backOpt(ActionEvent event) throws IOException {
        Stage stage=(Stage) back_btn.getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("after_log_In.fxml"));
        Scene scene1 = new Scene(root2);
        stage.setScene(scene1);
        stage.show();
    }
}
