package lk.ijse.pos.view.tdm;


public class OrderDetailsTM {
    private String itemcode;
    private String description;
    private int quantity;
    private double unitprice;
    private double total;
    private double discount;

    public OrderDetailsTM(String itemCode, String description, int qty , double unitPrice,double discount, double total) {
        this.itemcode=itemCode;
        this.description=description;
        this.quantity=qty;
        this.unitprice=unitPrice;
        this.total=total;
        this.discount= discount;
    }

    public OrderDetailsTM(String itemcode, String description, int quantity, double unitprice, double total, int discount) {
        this.itemcode = itemcode;
        this.description = description;
        this.quantity = quantity;
        this.unitprice = unitprice;
        this.total = total;
        this.discount = discount;
    }

    public OrderDetailsTM(String itemCode, String description, double unitPrice, int qty, double discount, double total) {
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "OrderDetailsTM{" +
                "itemcode='" + itemcode + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", unitprice=" + unitprice +
                ", total=" + total +
                ", discount=" + discount +
                '}';
    }
}