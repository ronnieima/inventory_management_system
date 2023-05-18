package kaito.software1;

public class InHouse extends Part{

    int machineId;

    public InHouse(int partId, String partName, int partInv, double partPrice, int machineId) {
        super(partId, partName, partInv, partPrice);
        this.machineId = machineId;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
