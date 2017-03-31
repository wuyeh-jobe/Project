/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edufarming;

/**
 *
 * @author Frederick
 */
public class Product {
    public String name;
    public int quantity;
    public double price;
    public String date;
    
    public Product(){
        this.name="";
        this.price=0;
        this.quantity=0;
        this.date="";
    }
    
    public Product(String name, int quantity, double price, String date){
        this.name=name;
        this.date=date;
        this.price=price;
        this.quantity=quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
    
}
