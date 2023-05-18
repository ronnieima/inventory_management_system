package kaito.software1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class Part {

    private int partId;
    private String partName;
    private int partInv;
    private double partPrice;
    public static ObservableList<Part> partList = FXCollections.observableArrayList();

    public Part(int partId, String partName, int partInv, double partPrice) {
        this.partId = partId;
        this.partName = partName;
        this.partInv = partInv;
        this.partPrice = partPrice;
    }

    public int getPartId(){
        return this.partId;
    }
    public void setPartId(int partId) {
        this.partId = partId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public int getPartInv() {
        return partInv;
    }

    public void setPartInv(int partInv) {
        this.partInv = partInv;
    }

    public double getPartPrice() {
        return partPrice;
    }

    public void setPartPrice(double partPrice) {
        this.partPrice = partPrice;
    }
}
