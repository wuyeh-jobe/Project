/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edufarming;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Frederick
 */
public class TablesController implements Initializable {
    
    public static java.sql.Connection conn = null;
    private final ObservableList<PieChart.Data> details = observableArrayList();

    @FXML
    private ImageView logo;
    @FXML
    private PieChart pieChart;
    @FXML
    private Button btn_back;
    @FXML
    private Button btn_logOut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost/ICP?user=root&password=root");
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        System.out.println("Connection established");
        try {
            java.sql.Statement s = conn.createStatement();
            java.sql.ResultSet r = s.executeQuery("SELECT * FROM project");
            while (r.next()) {
                Product product = new Product();
                product.setName(r.getString("name"));
                product.setQuantity(r.getInt("quantity"));
                product.setPrice(r.getDouble("price"));
                product.setDate(r.getString("date"));
                details.addAll(new PieChart.Data(product.getName(), product.getQuantity()));
                pieChart.setData(details);
                pieChart.setTitle("Inventory");
                pieChart.setLegendSide(Side.LEFT);
                pieChart.setLabelsVisible(true);
            }
            r.close();
            s.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }    

    @FXML
    private void back(ActionEvent event) throws IOException {
        Stage stage=(Stage) btn_back.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AddToTable.fxml"));
        Scene scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.show();
    }
    
    @FXML
    private void logOut(ActionEvent event) throws IOException {
        Stage stage=(Stage) btn_logOut.getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass()
                .getResource("LogOut.fxml"));
        Scene scene1 = new Scene(root2);
        stage.setScene(scene1);
        stage.show();
    }
    
}
