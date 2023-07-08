package kaito.software1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *  Model class for a product.
 *  FUTURE ENHANCEMENT: I can add more detail into each product by adding a String description attribute.
 */
public class  Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int max;
    private int min;

    /**
     * Constructor class that assigns attributes whenever a product is instantiated.
     * RUNTIME ERROR: I wanted to increment the product ID counter by one whenever a product object was instantiated.
     *                After making the product ID counter private in the Inventory class, Inventory.productIdCounter++; did not work.
     *                I added getters and setters and implemented in this constructor since the variable was no longer public. :(
     * @param id Product ID
     * @param name Product Name
     * @param stock Product Stock
     * @param price Product Price
     * @param max Product Max
     * @param min Product Min
     */
    public Product(int id, String name, int stock, double price, int max, int min){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.max = max;
        this.min = min;
        Main.setProductIdCounter(Main.getProductIdCounter() + 1);
    }

    /**
     * Setter for name attribute.
     * @param name Product Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for price attribute.
     * @param price Product Price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Setter for stock attribute.
     * @param stock Product Stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Setter for ID attribute.
     * @param id Product ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter for min attribute.
     * @param min Product Minimum
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Setter for max attribute.
     * @param max Product Maximum
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Getter for ID attribute.
     * @return Product ID
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for name attribute.
     * @return Product Name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for price attribute.
     * @return Product Price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Getter for stock attribute.
     * @return Product Stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Getter for minimum attribute.
     * @return Product Minimum
     */
    public int getMin() {
        return min;
    }

    /**
     * Getter for maximum attribute.
     * @return Product Maximum
     */
    public int getMax() {
        return max;
    }

    /**
     * Adds an associated part to a product's associated part list.
     * @param part Part to add
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * Deletes an associated part from a product's associated part list.
     * @param part Part to remove
     */
    public boolean deleteAssociatedPart(Part part) {
        if (associatedParts.contains(associatedParts)) {
            associatedParts.remove(part);
            return true;
        }
        return false;
    }

    /**
     * Getter for the associated parts list.
     * @return Associated Parts List
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}