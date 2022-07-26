package lk.ijse.pos.entity;

import java.time.LocalDate;
public class Order {
    private String oid;
    private LocalDate date;
    private String CID;

    public Order() {

    }

    public Order(String oid, LocalDate date, String CID) {
        this.oid = oid;
        this.date = date;
        this.CID = CID;
    }

    public String getOid() {

        return oid;
    }

    public void setOid(String oid) {

        this.oid = oid;

    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public String getCID() {

        return CID;
    }

    public void setCID(String CID)
    {
        this.CID = CID;
    }
}