package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Dashboardcontroller {

    public AnchorPane Dashboard;
    public JFXButton btnOrder;
    public JFXButton btnCustomer;
    public JFXButton btnReport;
    public JFXButton btnItem;

    public void OrderOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage1= (Stage)Dashboard.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("/lk.ijse.pos/view/Order.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void CustomerOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage1= (Stage)Dashboard.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("/lk.ijse.pos/view/Customer.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();

    }

    public void ReportOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage1= (Stage)Dashboard.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("/lk.ijse.pos/view/Report.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void ItemOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage1= (Stage)Dashboard.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("/lk.ijse.pos/view/Item.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
