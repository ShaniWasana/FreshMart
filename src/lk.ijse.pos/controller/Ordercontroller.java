package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.PurchaseOrderBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailDTO;
import lk.ijse.pos.view.tdm.OrderDetailsTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Ordercontroller {

    public TableColumn tblTotal;
    public TableColumn tblDiscount;
    public AnchorPane Context;
    public Label lblPersonalCare;
    public JFXButton btnNew;
    public Label lblCID;
    public Label lblCName;
    public Label lblDescription;
    public JFXTextField txtDescription;
    public JFXButton btnPlaceOrder;
    public JFXButton btnCancel;
    public JFXTextField txtCName;
    public Label lblItemCode;
    public TableView<OrderDetailsTM> table;
    public TableColumn tblItemCode;
    public TableColumn tblDescription;
    public TableColumn tblQuantity;
    public TableColumn tblUnitPrice;
    public JFXComboBox<String> cmbCID;
    public JFXComboBox<String> cmbItemCode;
    public Label lblQtyOnHand;
    public Label lblUnitPrice;
    public Label lblQuantity;
    public Label lblDiscount;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQuantity;
    public JFXTextField txtDiscount;
    public Button btnCart;
    public Label lblTotal;
    public Label lblTotlalAmount;
    public Label lblDateAdd;
    public Label lblOrderAdd;
    ObservableList<OrderDetailsTM> obOrderDetailTmList = FXCollections.observableArrayList();
    PurchaseOrderBO purchaseOrderBO = (PurchaseOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PURCHASE_ORDER);
    private String orderId;
    private double Discount;

    public void initialize() throws SQLException, ClassNotFoundException {

        table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Description"));
        table.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        table.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitprice"));
        table.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Discount"));
        table.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Total"));



        orderId = generateNewOrderId();
        lblOrderAdd.setText(orderId);
        txtCName.setFocusTraversable(false);
        txtCName.setEditable(false);

        txtDescription.setFocusTraversable(false);
        txtDescription.setEditable(false);
        txtUnitPrice.setFocusTraversable(false);
        txtUnitPrice.setEditable(false);
        txtQtyOnHand.setFocusTraversable(false);
        txtQtyOnHand.setEditable(false);

        getItemCode();
        getCustomerId();


        cmbCID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                try {
                    CustomerDTO customerDTO = purchaseOrderBO.searchCustomer(newValue + "");

                    txtCName.setText(customerDTO.getCName());

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }
        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                try {
                    ItemDTO itemDTO = purchaseOrderBO.searchItem(newValue + "");
                    txtDescription.setText(itemDTO.getDescription());
                    txtQtyOnHand.setText(String.valueOf(itemDTO.getQtyOnHand()));
                    txtUnitPrice.setText(String.valueOf(itemDTO.getUnitPrice()));

                    if (obOrderDetailTmList != null) {
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    private void getCustomerId() {
        try {
            ArrayList<CustomerDTO> all = purchaseOrderBO.getAllCustomers();
            for (CustomerDTO customerDTO : all) {
                cmbCID.getItems().add(customerDTO.getCID());
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Can't load customer ID").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void getItemCode() {
        try {
            //Get all items
            ArrayList<ItemDTO> all = purchaseOrderBO.getAllItems();
            for (ItemDTO dto : all) {
                cmbItemCode.getItems().add(dto.getIcode());
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String generateNewOrderId() throws SQLException, ClassNotFoundException {
        return purchaseOrderBO.generateNewOrderID();
    }


    public void NewOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage1 = (Stage) Context.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("/lk.ijse.pos/view/Order.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void CartOnAction(ActionEvent actionEvent) {
        if (!txtQuantity.getText().matches("\\d+") || Integer.parseInt(txtQuantity.getText()) <= 0 ||
                Integer.parseInt(txtQuantity.getText()) > Integer.parseInt(txtQtyOnHand.getText())) {
            new Alert(Alert.AlertType.ERROR, "Invalid Quantity").show();
            txtQuantity.requestFocus();
            txtQuantity.selectAll();
            return;

        }


        String itemCode = cmbItemCode.getSelectionModel().getSelectedItem();
        String Description = txtDescription.getText();
        double UnitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qty = Integer.parseInt(txtQuantity.getText());
        double Discount = Double.parseDouble(txtDiscount.getText());
        double total = ((UnitPrice * qty) - ((UnitPrice * qty) * (Discount / 100)));

        System.out.println(itemCode + " " + Description + " " + UnitPrice + " " + qty + " " + Discount + " " + total);

        if (isExist(itemCode)) {
            for (OrderDetailsTM orderDetailsTM : obOrderDetailTmList) {
                if (orderDetailsTM.getItemcode().equals(cmbItemCode.getValue())) {
                    orderDetailsTM.setQuantity(orderDetailsTM.getQuantity() + qty);
                    orderDetailsTM.setTotal(orderDetailsTM.getTotal() + total);

                    System.out.println("If EKE API ME INNE" + itemCode + " " + Description + " " + UnitPrice + " " + qty + " " + Discount + " " + total);

                }
            }
            table.refresh();
        } else {
            cmbCID.setDisable(true);
            txtCName.setDisable(true);
            System.out.println("ELSE EKE API ME INNE" + itemCode + " " + Description + " " + UnitPrice + " " + qty + " " + Discount + " " + total);

            //Add data to the Observable List
            OrderDetailsTM orderDetail = new OrderDetailsTM(
                    cmbItemCode.getValue(),
                    Description,
                    qty,
                    UnitPrice,
                    Discount,
                    total);


            //Added OrderDetail To the Observable list
            obOrderDetailTmList.add(orderDetail);

            for (OrderDetailsTM orderDetailsTM : obOrderDetailTmList) {

                System.out.println(orderDetailsTM.getItemcode() + " " + orderDetailsTM.getDescription() + " " + orderDetailsTM.getQuantity() + " " + orderDetailsTM.getUnitprice() + " " + orderDetailsTM.getDiscount() + " " + orderDetailsTM.getTotal()
                );

            }


            //Added Observable list to the table
            table.setItems(obOrderDetailTmList);

        }

        lblTotal.setText(String.valueOf(getAllTotalCost()));


    }

    private double getAllTotalCost() {
        double tCost = 0;
        for (OrderDetailsTM orderDetailsTM : obOrderDetailTmList) {
            tCost += orderDetailsTM.getTotal() ;
        }
        return tCost;

    }

    private boolean isExist(String code) {
        for (OrderDetailsTM orderDetailsTM : obOrderDetailTmList) {
            if (orderDetailsTM.getItemcode().equals(code)) {
                return true;
            }
        }
        return false;

    }


    public void EditOnAction(ActionEvent actionEvent) {
    }

    public void DeleteOnAction(ActionEvent actionEvent) {

        OrderDetailsTM selectedText = table.getSelectionModel().getSelectedItem();

        table.getItems().removeAll(selectedText);
    }

    public void PlaceOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        List<OrderDetailDTO> orderList = new ArrayList<>();

        for (OrderDetailsTM orderDetailsTM : obOrderDetailTmList) {
            orderList.add(new OrderDetailDTO(lblOrderAdd.getText(), orderDetailsTM.getItemcode(),
                    orderDetailsTM.getQuantity(), orderDetailsTM.getDiscount()));
        }

        for (OrderDetailsTM orderDetailsTM : obOrderDetailTmList) {

            System.out.println(orderDetailsTM.getItemcode());

        }

        for (OrderDetailDTO orderDetailDTO : orderList) {
            System.out.println("CONTRLLR EK PLCE ORDR EK YT INNE" + orderDetailDTO.getOid());

        }


        boolean b = saveOrder(orderId, LocalDate.now(), cmbCID.getValue(), orderList);
        if (b) {
            new Alert(Alert.AlertType.INFORMATION, "Order Placed Successfully").show();
         lblOrderAdd.setText(generateNewOrderId());
        cleartext();
        } else {
            new Alert(Alert.AlertType.WARNING, "Order Placed Un-Successful").show();
            orderId = generateNewOrderId();
            lblOrderAdd.setText("Order Id: " + orderId);
            cmbCID.getSelectionModel().clearSelection();
            cmbItemCode.getSelectionModel().clearSelection();
            table.getItems().clear();
            txtQuantity.clear();



        }

    }

    private void cleartext() {
        cmbCID.setValue(null);
        txtCName.clear();
        cmbItemCode.setValue(null);
        txtDescription.clear();
        txtQuantity.clear();
        txtQtyOnHand.clear();
        txtUnitPrice.clear();
    }


    private boolean saveOrder(String orderId, LocalDate orderDate, String CID, List<OrderDetailDTO> orderDetails) {

        System.out.println(orderId + " " + orderDate.toString());
        try {
            return purchaseOrderBO.purchaseOrder(new OrderDTO(orderId, orderDate, CID, orderDetails));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void CancelOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do You Want To Exit", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get().equals(ButtonType.YES)) {
            Stage stage = (Stage) Context.getScene().getWindow();
            stage.close();
            URL resource = getClass().getResource("/lk.ijse.pos/view/Dashboard.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene = new Scene(load);
            Stage stage1 = new Stage();
            stage.setScene(scene);
            stage.show();
        }


    }

    public void OnMouseClicked(MouseEvent mouseEvent) {
    }


}
