package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SignIncontroller {
    public AnchorPane Loginform;
    public Label lblUserName;
    public Label lblPassword;
    public JFXTextField txtUserName;
    public JFXPasswordField pwdPassword;
    public Label lblSignIn;
    public Label lblUser;
    public JFXComboBox cmbUser;
    int attempts=0;


    public void initialize(){
        cmbUser.getItems().add("Admin");
        cmbUser.getItems().add("User");
    }


    public void SignInOnAction(ActionEvent actionEvent) throws IOException {



        attempts++;

        if (attempts < 5) {
            if (txtUserName.getText().equals("shani") & pwdPassword.getText().equals("1234")) {
                Stage stage = (Stage) Loginform.getScene().getWindow();
                stage.close();
                URL resource = getClass().getResource("/lk.ijse.pos/view/Dashboard.fxml");
                Parent load = FXMLLoader.load(resource);
                Scene scene = new Scene(load);
                Stage stage1 = new Stage();
                stage1.setScene(scene);
                stage1.show();

              /*  if( cmbUser.getValue().equals("Admin")){
                    Dashboardcontroller..Disable("true");


                }*/

                try {

                } catch (Exception e) {
                    System.out.println(e);

                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Please Try Again,Your Usaer Name Or Password Is Incorrect.").showAndWait();

            }
        } else {
            txtUserName.setVisible(false);
            pwdPassword.setVisible(false);
        }
    }
    public void SignUpOnAction(ActionEvent actionEvent) throws IOException {
            Stage stage1= (Stage) Loginform.getScene().getWindow();
            stage1.close();
            URL resource = getClass().getResource("/lk.ijse.pos/view/SignUp.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene=new Scene(load);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();

        }

    public void ForgetOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage1= (Stage) Loginform.getScene().getWindow();
        stage1.close();
        URL resource = getClass().getResource("/lk.ijse.pos/view/Forget.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene=new Scene(load);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
