package kaito.software1;

/**
 * Model class for an outsourced part. Inherits attributes from the Part class, and has a unique attribute called companyName.
 * FUTURE ENHANCEMENT: I can add more attributes that can describe an in-house part such as its physical condition. (Brand New, Used)
 * LOGIC ERROR: I originally put machineId as a public int variable, but I changed it to private to promote the good practice
 * of encapsulation.
 */
public class InHouse extends Part{
    private int machineId;

    /**
     * Constructor class that assigns attributes whenever an in-house part is instantiated.
     * @param id Part ID
     * @param name Part Name
     * @param price Part Price
     * @param stock Part Stock
     * @param min Part Minimum
     * @param max Part Maximum
     * @param machineId Part Machine ID
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * Setter for machine ID attribute.
     * @param machineId Machine ID
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /**
     * Getter for machine ID attribute.
     * @return Machine ID
     */
    public int getMachineId() {
        return machineId;
    }
}
