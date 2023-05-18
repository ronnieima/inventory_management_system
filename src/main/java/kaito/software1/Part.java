package kaito.software1;

public class Part {

    private int id;
    private String name;
    private int inv;
    private double price;

    public Part(int id, String name, int inv, double price) {
        this.id = id;
        this.name = name;
        this.inv = inv;
        this.price = price;
    }

    public int getId(){
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInv() {
        return inv;
    }

    public void setInv(int inv) {
        this.inv = inv;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
