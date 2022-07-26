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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.view.tdm.CustomerTM;
import lk.ijse.pos.view.util.ValidationUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class Customercontroller {
    public AnchorPane Context;
    public Label lblPersonalCare;
    public JFXButton btnNew;
    public Label lblCID;
    public JFXTextField txtCID;
    public Label lblCName;
    public Label lblCAddress;
    public JFXTextField txtCAddress;
    public Label lblCTItle;
    public JFXButton btnSave;
    public JFXButton btnCancel;
    public Label lblCity;
    public JFXTextField txtCity;
    public JFXTextField txtCName;
    public Label lblProvince;
    public JFXTextField txtProvince;
    public Label lblPostalCode;
    public TableView<CustomerTM>  table;
    public TableColumn tblCusID;
    public TableColumn tblCusTitle;
    public TableColumn tblCusName;
    public TableColumn TblAddress;
    public TableColumn tblCity;
    public TableColumn tblProvince;
    public TableColumn tblPostalCode;
    public JFXComboBox cmbtitle;
    public TextField txtSearch;
    public MenuItem btnDelete;
    public MenuItem btnEdit;
    public Label lblCID1;
    public JFXTextField txtPostal;

    private final CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    public void initialize() throws SQLException, ClassNotFoundException {
        cmbtitle.getItems().add("Mr");
        cmbtitle.getItems().add("Mrs");
        cmbtitle.getItems().add("Miss");

        table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("CID"));
        table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("CTitle"));
        table.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("CName"));
        table.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("CAddress"));
        table.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("City"));
        table.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Province"));
        table.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("PostalCode"));
        clear();
        generateId();

        txtCName.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^[A-Z][a-z]+$",newValue,txtCName,btnSave);
        });

        txtCAddress.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^[A-z]+$",newValue,txtCAddress,btnSave);
        });

        txtCity.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^[A-z]+$",newValue,txtCity,btnSave);
        });
        txtProvince.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^[A-z]+$",newValue,txtProvince,btnSave);
        });
        txtPostal.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^[0-9]+$",newValue,txtPostal,btnSave);
        });


        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);


            if (newValue != null) {
                txtCID.setText(newValue.getCID());
                cmbtitle.setValue(newValue.getCTitle());
                txtCName.setText(newValue.getCName());
                txtCAddress.setText(newValue.getCAddress());
               txtCity.setText(newValue.getCity());
               txtProvince.setText(newValue.getProvince());
               txtPostal.setText(newValue.getPostalCode());

            }

        });

        txtCAddress.setOnAction(event -> btnSave.fire());
        loadAllCustomers();
    }

    private void loadAllCustomers() {
        table.getItems().clear();
        //Get all customers
        try {
            ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomers();
            for (CustomerDTO customer : allCustomers) {
                table.getItems().add(new CustomerTM(customer.getCID(), customer.getCTitle(),customer.getCName(), customer.getCAddress(),customer.getCity(),customer.getProvince(),customer.getPostalCode()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clear() {
        txtCID.clear();
        txtCName.clear();
        txtCAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostal.clear();



        txtCID.setEditable(false);
        btnDelete.setDisable(true);
    }


    public void CancelOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do You Want To Exit", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get().equals(ButtonType.YES)) {
            Stage stage = (Stage)Context.getScene().getWindow();
            stage.close();
            URL resource = getClass().getResource("/lk.ijse.pos/view/Dashboard.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene = new Scene(load);
            Stage stage1 = new Stage();
            stage.setScene(scene);
            stage.show();
        }
    }

    public void SaveOnAction(ActionEvent actionEvent) {


        String CID = txtCID.getText();
        String CTitle= String.valueOf(cmbtitle.getValue());
        String CName=txtCName.getText();
        String CAddress=txtCAddress.getText();
        String City=txtCity.getText();
        String Province=txtProvince.getText();
        String PostalCode=txtPostal.getText();


        if (btnSave.getText().equalsIgnoreCase("save")) {
            //Save Customer
            try {
                if (existCustomer(CID)) {
                    new Alert(Alert.AlertType.ERROR, CID + " already exists").show();
                }

                customerBO.saveCustomer(new CustomerDTO(CID,CTitle,CName,CAddress,City,Province,PostalCode));
                table.getItems().add(new CustomerTM(CID,CTitle, CName, CAddress,City,Province,PostalCode));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, " Can't save the customer " + e.getMessage()).show();
                e.printStackTrace();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            //Update customer
            try {
                if (!existCustomer(CID)) {
                    new Alert(Alert.AlertType.ERROR, "There is no  customer associated with the id " + CID).show();
                }
                //Customer update
                customerBO.updateCustomer(new CustomerDTO(CID,CTitle,CName,CAddress,City,Province,PostalCode));

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the customer " + CID + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            CustomerTM selectedCustomer = table.getSelectionModel().getSelectedItem();
            selectedCustomer.setCTitle(CTitle);
            selectedCustomer.setCName(CName);
            selectedCustomer.setCAddress(CAddress);
            selectedCustomer.getCity();
            selectedCustomer.getProvince();
            selectedCustomer.getPostalCode();

            table.refresh();
        }
        btnNew.fire();
    }


    boolean existCustomer(String CID) throws SQLException, ClassNotFoundException {
        return customerBO.customerExist(CID);
    }
    public void OnMouseClicked(MouseEvent mouseEvent) throws IOException {

    }

    public void NewOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage1 = (Stage) Context.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("/lk.ijse.pos/view/Customer.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void OnKeyReleased(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {

        String search = "%" + txtSearch.getText() + "%";

        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            ArrayList<CustomerDTO> customerDTOS = customerBO.searchCustomers(search);
            ObservableList<CustomerTM> oBCustomerTMS = FXCollections.observableArrayList();

            for (CustomerDTO cusDto : customerDTOS) {
                oBCustomerTMS.add(new CustomerTM(cusDto.getCID(),
                        cusDto.getCTitle(),
                        cusDto.getCName(),
                        cusDto.getCAddress(),
                        cusDto.getCity(),
                        cusDto.getProvince(),
                        cusDto.getPostalCode()));
            }
            table.getItems().clear();
            table.getItems().addAll(oBCustomerTMS);
            table.refresh();
        }
    }
    public void DeleteOnAction(ActionEvent actionEvent) {
        String CID = table.getSelectionModel().getSelectedItem().getCID();
        try {
            if (!existCustomer(CID)) {
                new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + CID).show();
            }
            customerBO.deleteCustomer(CID);
            table.getItems().remove(table.getSelectionModel().getSelectedItem());
            table.getSelectionModel().clearSelection();
            clear();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Can't delete the customer " + CID).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void EditOnAction(ActionEvent actionEvent) {
    }
    private void generateId() throws SQLException, ClassNotFoundException {
        txtCID.setText(customerBO.generateNewCustomerID());
    }
}
