/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edufarming;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Frederick
 */
public class EditInfoController implements Initializable {
    public static java.sql.Connection conn = null;

    @FXML
    private ImageView logo;
    @FXML
    private Button btn_back;
    @FXML
    private Button btn_editInfo;
    @FXML
    private TextField txt_productName;
    @FXML
    private TextField txt_quantity;
    @FXML
    private TextField txt_price;
    @FXML
    private DatePicker txt_date;
    @FXML
    private TableView<Product> tableView;
    @FXML
    private TableColumn<Product, String> colName;
    @FXML
    private TableColumn<Product, Integer> colQuantity;
    @FXML
    private TableColumn<Product, Double> colPrice;
    @FXML
    private TableColumn<Product, String> colDate;
    @FXML
    private Button btn_logOut;
    @FXML
    private Button btn_refresh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       txt_productName.setPromptText("Name");
       txt_quantity.setPromptText("Quantity");
       txt_price.setPromptText("Price");
       txt_date.setPromptText("Date");
       
       colName.setCellValueFactory(new PropertyValueFactory<>("name"));
       colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
       colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
       colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
       
       try {
    
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conn = java.sql.DriverManager.getConnection(
        "jdbc:mysql://localhost/ICP?user=root&password=root");
        }
        catch (Exception e) {
        System.out.println(e);
        System.exit(0);
        }
        System.out.println("Connection established");
        try {
            java.sql.Statement s = conn.createStatement();
            java.sql.ResultSet r = s.executeQuery("SELECT * FROM project");
            while(r.next()){
            Product product = new Product();
            product.setName(r.getString("name"));
            product.setQuantity(r.getInt("quantity"));
            product.setPrice(r.getDouble("price"));
            product.setDate(r.getString("date"));
            tableView.getItems().add(product);
            }
                
        r.close();
        s.close();
        conn.close();
        }
        catch (Exception e) {
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
    private void confirmEdit(ActionEvent event) {
        TablePosition pos1 = tableView.getSelectionModel().getSelectedCells().get(0);
        int row = pos1.getRow();
        Product product = tableView.getItems().get(row);
        
        try {
    
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conn = java.sql.DriverManager.getConnection(
        "jdbc:mysql://localhost/ICP?user=root&password=root");
        }
        catch (Exception e) {
        System.out.println(e);
        System.exit(0);
        }
        System.out.println("Connection established");
        String name = product.getName();
        int quantity = product.getQuantity();
        String date = product.getDate();
        String name1 = txt_productName.getText();
        int quantity1 = Integer.parseInt(txt_quantity.getText());
        double price1 = Double.parseDouble(txt_price.getText());
        Date datee = Date.valueOf(txt_date.getValue());
        try {
            String sql = "UPDATE project SET name=" + "'"+name1+"'" + ", quantity="
                    + "'"+quantity1+"'" + ", price=" + "'"+price1+"'" 
                    + ", date=" + "'" +datee+"'" + " WHERE name=" 
                    + "'"+name+"'" + "AND date =" + "'"+ date +"'";
            java.sql.Statement s = conn.createStatement();
            s.executeUpdate(sql);
          
       //   r.close();
          s.close();
          conn.close();
        }
        catch (Exception e) {
        System.out.println(e);
        System.exit(0);
        }
        txt_productName.clear();
        txt_quantity.clear();
        txt_price.clear();
        txt_date.getEditor().clear();
        
    }

    private void close(ActionEvent event) {
        Platform.exit();
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

    @FXML
    private void refresh(ActionEvent event) {
       colName.setCellValueFactory(new PropertyValueFactory<>("name"));
       colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
       colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
       colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
       
       try {
    
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conn = java.sql.DriverManager.getConnection(
        "jdbc:mysql://localhost/ICP?user=root&password=root");
        }
        catch (Exception e) {
        System.out.println(e);
        System.exit(0);
        }
        System.out.println("Connection established");
        try {
            java.sql.Statement s = conn.createStatement();
            java.sql.ResultSet r = s.executeQuery("SELECT * FROM project");
            while(r.next()){
            Product product = new Product();
            product.setName(r.getString("name"));
            product.setQuantity(r.getInt("quantity"));
            product.setPrice(r.getDouble("price"));
            product.setDate(r.getString("date"));
            tableView.getItems().clear();
            tableView.getItems().add(product);
            }
                
        r.close();
        s.close();
        conn.close();
        }
        catch (Exception e) {
        System.out.println(e);
        System.exit(0);
        }
    }
    
}
