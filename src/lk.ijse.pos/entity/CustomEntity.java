package lk.ijse.pos.entity;

import java.time.LocalDate;

public class CustomEntity {
    private String CID;
    private String CTitle;
    private String CName;
    private String CAddress;
    private String City;
    private String Province;
    private String PostalCode;

    private String Icode;
    private String Description;
    private String PackSize;
    private double UnitPrice;
    private int QtyOnHand;

    private String oid;
    private LocalDate date;
    //private String CID;

   // private String oid;
    //private String Icode;
    private int Oquantity;
    private double discount;

    public CustomEntity(String oid, LocalDate date, String CID, String Icode, String Description, int Oquantity, double discount) {
        this.CID = CID;
        this.date= date;
        this.Icode=Icode;
        this.Description=Description;
        this.Oquantity=Oquantity;
        this.discount=discount;
    }

    public CustomEntity(String CID, String CTitle, String CName, String CAddress, String city, String province, String postalCode, String icode, String description, String packSize, double unitPrice, int qtyOnHand, String oid, LocalDate date, int oquantity, double discount) {
        this.CID = CID;
        this.CTitle = CTitle;
        this.CName = CName;
        this.CAddress = CAddress;
        City = city;
        Province = province;
        PostalCode = postalCode;
        Icode = icode;
        Description = description;
        PackSize = packSize;
        UnitPrice = unitPrice;
        QtyOnHand = qtyOnHand;
        this.oid = oid;
        this.date = date;
        Oquantity = oquantity;
        this.discount = discount;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getCTitle() {
        return CTitle;
    }

    public void setCTitle(String CTitle) {
        this.CTitle = CTitle;
    }

    public String getCName() {
        return CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public String getCAddress() {
        return CAddress;
    }

    public void setCAddress(String CAddress) {
        this.CAddress = CAddress;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
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

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

