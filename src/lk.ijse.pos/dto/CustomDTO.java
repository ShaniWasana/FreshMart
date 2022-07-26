package lk.ijse.pos.dto;


import java.time.LocalDate;

public class CustomDTO {
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

    private String Oid;
    // private String Icode;
    private int Oquantity;
    private double discount;

    // private String Oid;
    private LocalDate date;

    public CustomDTO() {
    }

    public CustomDTO(String CID, String CTitle, String CName, String CAddress, String city, String province, String postalCode, String icode, String description, String packSize, double unitPrice, int qtyOnHand, String oid, int oquantity, double discount, LocalDate date) {
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
        Oid = oid;
        Oquantity = oquantity;
        this.discount = discount;
        this.date = date;
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
        return Oid;
    }

    public void setOid(String oid) {
        Oid = oid;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    // private String CID;

  }