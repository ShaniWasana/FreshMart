package lk.ijse.pos.view.tdm;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderTM {
    private String orderid;
    private LocalDate orderdate;
    private String id;
    private String name;
    private double total;

    public OrderTM(String icode, String description, int quantity, double unitPrice, double discount, double total) {
    }

    public OrderTM(String orderid, LocalDate orderdate, String id, String name, double total) {
        this.orderid = orderid;
        this.orderdate = orderdate;
        this.id = id;
        this.name = name;
        this.total = total;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public LocalDate getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(LocalDate orderdate) {
        this.orderdate = orderdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderTM{" +
                "orderid='" + orderid + '\'' +
                ", orderdate=" + orderdate +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", total=" + total +
                '}';
    }
}