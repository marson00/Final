/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.sql.Blob;

public class Product 
{
    private String   prodID;    //ProductID
    private String   prodName;  //ProductName
    private String   desc;      //Description
    private int      price;     //OriginalPrice
    private int      qty;
    private Blob     image;     
    private Status      status;
    private Category category;
 
    public Product(){
    
    }
    
    public Product(String prodID, String prodName, String desc, int price, int qty, Blob image, Status status, Category category){
        this.prodID = prodID;
        this.prodName = prodName;
        this.desc = desc;
        this.price = price;
        this.qty = qty;
        this.image = image;
        this.status = status;
        this.category = category;
    }

    public String getProdID() {
        return prodID;
    }

    public String getProdName() {
        return prodName;
    }

    public String getDesc() {
        return desc;
    }

    public int getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public Blob getImage() {
        return image;
    }

    public Status getStatus() {
        return status;
    }

    public Category getCategory() {
        return category;
    }

    public void setProdID(String prodID) {
        this.prodID = prodID;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    
    public void setImage(Blob image) {
        this.image = image;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
 
    
}
