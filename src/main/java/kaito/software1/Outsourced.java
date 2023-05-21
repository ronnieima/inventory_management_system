package kaito.software1;

/**
 * Model class for an outsourced part. Inherits attributes from the Part class, and has a unique attribute called companyName.
 * FUTURE ENHANCEMENT: I can add contact information attributes from the company such as name of point of contact, email, and phone number.
 */
public class Outsourced extends Part{
    private String companyName;

    /**
     * Constructor class that assigns attributes whenever an outsourced part is instantiated.
     * RUNTIME ERROR: For the constructor, I originally used this.id = id, this.name, etc. This is where I discovered the super() method, and I used it to
     *                inherit the attributes from the parent class.
     * @param id Part ID
     * @param name Part Name
     * @param price Part Price
     * @param stock Part Stock
     * @param min Part Minimum
     * @param max Part Maximum
     * @param companyName Part Company Name
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Setter for company name attribute.
     * @param companyName Company Name
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Getter for company name attribute.
     * @return Company Name
     */
    public String getCompanyName() {
        return companyName;
    }
}
