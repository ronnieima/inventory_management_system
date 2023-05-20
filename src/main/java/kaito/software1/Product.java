package kaito.software1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class  Product {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int max;
    private int min;


    public Product(int id, String name, int stock, double price, int max, int min){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.max = max;
        this.min = min;
        Inventory.productIdCounter++;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setMin(int min) {
        this.min = min;
    }
    public void setMax(int max) {
        this.max = max;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getStock() {
        return stock;
    }
    public int getMin() {
        return min;
    }
    public int getMax() {
        return max;
    }
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }
    public boolean deleteAssociatedPart(Part part) {
        if (associatedParts.contains(associatedParts)) {
            associatedParts.remove(part);
            return true;
        }
        return false;
    }
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}

