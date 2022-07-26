package lk.ijse.pos.dto;

import java.time.LocalDate;
import java.util.List;

public class OrderDTO {
    private String Oid;
    private LocalDate date;
    private String CID;

    private List<OrderDetailDTO> orderDetail;

    private String CName;
    private double total;
    private double discount;

    public OrderDTO(String orderId, LocalDate orderDate, String CID, List<OrderDetailDTO> orderDetails) {
    this.Oid=orderId;
    this.date=orderDate;
    this.CID=CID;
    this.orderDetail=orderDetails;
    }

    public OrderDTO(String oid, LocalDate date, String CID, List<OrderDetailDTO> orderDetail, String CName, double total, double discount) {
        Oid = oid;
        this.date = date;
        this.CID = CID;
        this.orderDetail = orderDetail;
        this.CName = CName;
        this.total = total;
        this.discount = discount;
    }

    public String getOid() {
        return Oid;
    }

    public void setOid(String oid) {
        Oid = oid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public List<OrderDetailDTO> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetailDTO> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public String getCName() {
        return CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
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

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "Oid='" + Oid + '\'' +
                ", date=" + date +
                ", CID='" + CID + '\'' +
                ", orderDetail=" + orderDetail +
                ", CName='" + CName + '\'' +
                ", total=" + total +
                ", discount=" + discount +
                '}';
    }
}