package kaito.software1; /**
 * Supplied class Part.java
 */

/**
 * Model class for a part. An abstract class that is the parent class of the outsourced and inhouse classes.
 * @author Ronnie Kaito Imagawa
 */
public abstract class Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Getter for ID attribute.
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for ID attribute.
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /** Getter for name attribute.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name attribute.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Getter for price attribute.
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /** Setter for price attribute.
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /** Getter for stock attribute.
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /** Setter for stock attribute.
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** Getter for min attribute.
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /** Setter for min attribute.
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /** Getter for max attribute.
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /** Setter for max attribute.
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

}