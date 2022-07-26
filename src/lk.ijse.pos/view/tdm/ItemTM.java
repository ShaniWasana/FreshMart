package lk.ijse.pos.view.tdm;



public class ItemTM {
    private String Icode;
    private String Description;
    private String PackSize;
    private double UnitPrice;
    private int QtyOnHand;

    public ItemTM() {
    }

    public ItemTM(String icode, String description, String packSize, double unitPrice, int qtyOnHand) {
        Icode = icode;
        Description = description;
        PackSize = packSize;
        UnitPrice = unitPrice;
        QtyOnHand = qtyOnHand;
    }

    public String getIcode() {
        return Icode;
    }

    public void setIcode(String icode) {
        Icode = icode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPackSize() {
        return PackSize;
    }

    public void setPackSize(String packSize) {
        PackSize = packSize;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return QtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        QtyOnHand = qtyOnHand;
    }

    @Override
    public String toString() {
        return "ItemTM{" +
                "Icode='" + Icode + '\'' +
                ", Description='" + Description + '\'' +
                ", PackSize='" + PackSize + '\'' +
                ", UnitPrice=" + UnitPrice +
                ", QtyOnHand=" + QtyOnHand +
                '}';
    }
}