package kaito.software1;

public class Outsourced extends Part{
    private String companyName;

    public Outsourced(int partId, String partName, int partInv, double partPrice, String companyName) {
        super(partId, partName, partInv, partPrice);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
