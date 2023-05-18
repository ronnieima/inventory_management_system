package kaito.software1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class  Product {

    private int productId;
    private String productName;
    private int productInv;
    private double productPrice;
    public static ObservableList<Product> productList = FXCollections.observableArrayList();

    public Product(int productId, String productName, int productInv, double productPrice){
        this.productId = productId;
        this.productName = productName;
        this.productInv = productInv;
        this.productPrice = productPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductInv() {
        return productInv;
    }

    public void setProductInv(int productInv) {
        this.productInv = productInv;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}

