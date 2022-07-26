package lk.ijse.pos.entity;


public class OrderDetail {
    private String oid;
    private String Icode;
    private int Oquantity;
    private double discount;

    public OrderDetail() {
    }

    public OrderDetail(String oid, String icode, int oquantity, double discount) {
        this.oid = oid;
        Icode = icode;
        Oquantity = oquantity;
        this.discount = discount;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getIcode() {
        return Icode;
    }

    public void setIcode(String icode) {
        Icode = icode;
    }

    public int getOquantity() {
        return Oquantity;
    }

    public void setOquantity(int oquantity) {
        Oquantity = oquantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}