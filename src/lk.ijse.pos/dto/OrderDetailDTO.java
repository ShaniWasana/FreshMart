package lk.ijse.pos.dto;

import java.io.Serializable;


public class OrderDetailDTO implements Serializable {
    private String Oid;
    private String Icode;
    private int Oquantity;
    private double discount;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String oid, String icode, int oquantity, double discount) {
       this.Oid = oid;
        this.Icode = icode;
        this.Oquantity = oquantity;
        this.discount = discount;
    }

    public String getOid() {
        return Oid;
    }

    public void setOid(String oid) {
        Oid = oid;
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

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "Oid='" + Oid + '\'' +
                ", Icode='" + Icode + '\'' +
                ", Oquantity=" + Oquantity +
                ", discount=" + discount +
                '}';
    }
}
