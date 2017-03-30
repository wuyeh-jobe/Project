/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edufarming;

import java.util.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Frederick
 */
public class AddToTableController implements Initializable {

    public static java.sql.Connection conn = null;
    static String finalName;

    @FXML
    private ImageView logo;
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
    private Button btn_deleteInfo;
    @FXML
    private TextField txt_productName;
    @FXML
    private TextField txt_quantity;
    @FXML
    private TextField txt_price;
    @FXML
    private DatePicker txt_date;
    @FXML
    private Button btn_addInfo;
    @FXML
    private Button btn_viewTable;
    @FXML
    private Button btn_back;
    @FXML
    private Button btn_logOut;
    @FXML
    private Button btn_editInfo;
    @FXML
    private Text txt_DisplayName;
    String firName="";
    String lasName="";
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
                tableView.getItems().add(product);
            }
            
            r.close();
            s.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        if(tableView.getItems().isEmpty()){
            btn_deleteInfo.setDisable(true);
            btn_viewTable.setDisable(true);
            btn_editInfo.setDisable(true);
        }   
    }

    @FXML
    private void addInfo(ActionEvent event) throws IOException {
        TableColumn<Product, String> column = colName;
        List<String> columnData = new ArrayList();
        for (Product item : tableView.getItems()) {
            columnData.add(colName.getCellObservableValue(item).getValue());
        }
        if(!columnData.contains(txt_productName.getText())){
                    Product product = new Product();
        product.setName(txt_productName.getText());
        product.setQuantity(Integer.parseInt(txt_quantity.getText()));
        product.setPrice(Double.parseDouble(txt_price.getText()));
        product.setDate(txt_date.getEditor().getText());
        tableView.getItems().add(product);
        btn_deleteInfo.setDisable(false);
        btn_viewTable.setDisable(false);
        btn_editInfo.setDisable(false);
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost/ICP?user=root&password=root");
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        System.out.println("Connection established");
        String name = txt_productName.getText();
        int quantity = Integer.parseInt(txt_quantity.getText());
        double price = Double.parseDouble(txt_price.getText());
        String date = txt_date.getEditor().getText();
        try {
            PreparedStatement p = conn.prepareStatement("Insert Into project set name=?,"
                    + "quantity=? ,price =?, date=?");
            p.setString(1, name);
            p.setInt(2, quantity);
            p.setDouble(3, price);
            Date datee = Date.valueOf(txt_date.getValue());
            p.setDate(4, datee);
            p.execute();
        } catch (Exception e) {
            System.out.println("Error" + e.toString());
            return;

        }
        }
        txt_productName.clear();
        txt_quantity.clear();
        txt_price.clear();
        txt_date.getEditor().clear();

    }

    @FXML
    private void deleteInfo(ActionEvent event) {
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost/ICP?user=root&password=root");
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        System.out.println("Connection established");
        TablePosition pos = tableView.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        Product product = tableView.getItems().get(row);
        String name = product.getName();
        int quantity = product.getQuantity();
        String date = product.getDate();
        try {
            java.sql.Statement s = conn.createStatement();
            String sql = "DELETE FROM project WHERE name = " + "'" + name + "'";
            s.executeUpdate(sql);
            s.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }

        ObservableList<Product> productsSelected, allProducts;
        productsSelected = tableView.getSelectionModel().getSelectedItems();

        allProducts = tableView.getItems();
        productsSelected.forEach(allProducts::remove);
        if(tableView.getItems().isEmpty()){
            btn_deleteInfo.setDisable(true);
            btn_viewTable.setDisable(true);
            btn_editInfo.setDisable(true);
        } 

    }
    

    @FXML
    private void viewTable(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_viewTable.getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass()
                .getResource("tables.fxml"));
        Scene scene1 = new Scene(root2);
        stage.setScene(scene1);
        stage.show();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_back.getScene().getWindow();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("experience_farmer.fxml"));
        Parent root2 = (Parent)fxmlloader.load();     
        Scene scene1 = new Scene(root2);
        stage.setScene(scene1);
        Experience_farmerController ef=fxmlloader.<Experience_farmerController>getController();  
        ef.passOnInfo(firName, lasName);
        ef.setUsername();
        stage.show();
    }

    @FXML
    private void logOut(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_logOut.getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass()
                .getResource("LogOut.fxml"));
        Scene scene1 = new Scene(root2);
        stage.setScene(scene1);
        stage.show();
    }

    @FXML
    private void editInfo(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_editInfo.getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass()
                .getResource("EditInfo.fxml"));
        Scene scene1 = new Scene(root2);
        stage.setScene(scene1);
        stage.show();

    }
    public void passOnInfo(String fName, String lName){
        lasName = lName;
        firName = fName;
    }
    public void setUsername(){
        txt_DisplayName.setText(firName+"."+lasName.charAt(0));
}

}
