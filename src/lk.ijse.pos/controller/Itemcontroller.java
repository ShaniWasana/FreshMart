package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.view.tdm.CustomerTM;
import lk.ijse.pos.view.tdm.ItemTM;
import lk.ijse.pos.view.util.ValidationUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class Itemcontroller {

    private ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);
    public MenuItem btnDelete;
    public MenuItem btnEdit;
    public AnchorPane Context;
    public Label lblPersonalCare;
    public JFXButton btnNew;
    public Label lblItemCode;
    public JFXTextField txtItemCode;
    public JFXTextField txtDescription;
    public Label lblQtyOnHand;
    public Label lblDescription;
    public JFXButton btnSave;
    public JFXButton btnCancel;
    public Label lblPackSize;
    public JFXTextField txtPackSize;
    public JFXTextField txtQtyOnHand;
    public Label lblUnitPrice;
    public JFXTextField txtUnitPrice;
    public TableView<ItemTM> table;
    public TableColumn tblItemCode;
    public TableColumn tblDescription;
    public TableColumn tblPackSize;
    public TableColumn tblUnitPrice;
    public TableColumn tblQtyOnHand;
    public TextField txtSearch;
    public Label lblSearch;
    public ImageView imgSearch;

    public void initialize() throws SQLException, ClassNotFoundException {
        table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Icode"));
        table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Description"));
        table.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("PackSize"));
        table.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        table.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("QtyOnHand"));

        clear();
        generateId();

        txtDescription.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^[A-z]+$",newValue,txtDescription,btnSave);
        });

        txtPackSize.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^[A-z]+$",newValue,txtPackSize,btnSave);
        });

        txtUnitPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^[0-9].[0-9]{1,2}$",newValue,txtUnitPrice,btnSave);
        });
        txtQtyOnHand.textProperty().addListener((observable, oldValue, newValue) -> {
            ValidationUtil.validate("^[0-9]+$",newValue,txtQtyOnHand,btnSave);
        });



        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtItemCode.setText(newValue.getIcode());
                txtDescription.setText(newValue.getDescription());
                txtPackSize.setText(newValue.getPackSize());
                txtUnitPrice.setText(newValue.getUnitPrice()+ "");
                txtQtyOnHand.setText(newValue.getQtyOnHand() + "");




            }
        });

        txtQtyOnHand.setOnAction(event -> btnSave.fire());
        loadAllItems();
    }

    private void loadAllItems() {
        table.getItems().clear();
        try {
            /*Get all items*/
            ArrayList<ItemDTO> allItems = itemBO.getAllItems();
            for (ItemDTO item : allItems) {
                table.getItems().add(new ItemTM(item.getIcode(), item.getDescription(), item.getPackSize(), item.getUnitPrice(), item.getQtyOnHand()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void clear() {
        txtItemCode.clear();
        txtDescription.clear();
        txtPackSize.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();

    }

    public void EditOnAction(ActionEvent actionEvent) {
    }

    public void DeleteOnAction(ActionEvent actionEvent) {
        String Icode = table.getSelectionModel().getSelectedItem().getIcode();
        try {
            if (!existItem(Icode)) {
                new Alert(Alert.AlertType.ERROR, "There is no such item associated with the Code " + Icode).show();
            }
            itemBO.deleteItem(Icode);
            table.getItems().remove(table.getSelectionModel().getSelectedItem());
            table.getSelectionModel().clearSelection();
            clear();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Can't delete the item " + Icode).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void SearchKeyReleased(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        String search = "%" + txtSearch.getText() + "%";

        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            ArrayList<ItemDTO> itemDTOS = itemBO.searchitem(search);
            ObservableList<ItemTM> itemTMS = FXCollections.observableArrayList();

            for (ItemDTO itemdto : itemDTOS) {
                itemTMS.add(new ItemTM(itemdto.getIcode(),
                        itemdto.getDescription(),
                        itemdto.getPackSize(),
                        itemdto.getUnitPrice(),
                        itemdto.getQtyOnHand()));
            }
            table.getItems().clear();
            table.getItems().addAll(itemTMS);
            table.refresh();
        }

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

    public void SaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if(btnSave.getText().equalsIgnoreCase("Update Now")){

            if (itemBO.updateItem(new ItemDTO(txtItemCode.getText(),txtDescription.getText(),txtPackSize.getText(),
                    Double.parseDouble(txtUnitPrice.getText()),Integer.parseInt(txtQtyOnHand.getText())))){
                new Alert(Alert.AlertType.INFORMATION,"Item Updated Successful").show();
            }


            btnSave.setText("Add Item");
            loadAllItems();
        }else {

            itemBO.saveItem(new ItemDTO(txtItemCode.getText(),
                    txtDescription.getText(),
                    txtPackSize.getText(),
                    Double.parseDouble(txtUnitPrice.getText()),
                    Integer.parseInt(txtQtyOnHand.getText())));


            table.getItems().add(new ItemTM(txtItemCode.getText(),
                    txtDescription.getText(),
                    txtPackSize.getText(),
                    Double.parseDouble(txtUnitPrice.getText()),
                    Integer.parseInt(txtQtyOnHand.getText())));
            table.refresh();
                clear();

        }
    }



    private boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemBO.itemExist(code);
    }




    public void NewOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage1 = (Stage) Context.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("/lk.ijse.pos/view/Item.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }
    private void generateId() throws SQLException, ClassNotFoundException {
        txtItemCode.setText(itemBO.generateNewItemCode());
    }
}
