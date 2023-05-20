package kaito.software1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class  Product {

    private int productId;
    private String productName;
    private int productInv;
    private double productPrice;
    private int max;
    private int min;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    public Product(int productId, String productName, int productInv, double productPrice, int max, int min){
        this.productId = productId;
        this.productName = productName;
        this.productInv = productInv;
        this.productPrice = productPrice;
        this.max = max;
        this.min = min;
    }

    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    public boolean removeAssociatedPart(Part part) {
        if (associatedParts.contains(associatedParts)) {
            associatedParts.remove(part);
            return true;
        }
        return false;
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

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }
}

