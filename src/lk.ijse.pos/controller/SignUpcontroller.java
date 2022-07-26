package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SignUpcontroller {
    public AnchorPane SignUpForm;
    public Label lblSignIn;
    public Label lblFullName;
    public Label lblEmail;
    public JFXTextField txtFullName;
    public JFXTextField txtEmail;
    public Label lblUserName;
    public JFXTextField txtUserName;
    public Label lblPassword;
    public Label lblConfirmPassword;
    public JFXPasswordField pwdPassword;
    public JFXPasswordField pwdConfirmPassword;

    public void SignUpOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage1= (Stage) SignUpForm.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("/lk.ijse.pos/view/Dashboard.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void SignInOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage1= (Stage) SignUpForm.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("/lk.ijse.pos/view/SignIn.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
