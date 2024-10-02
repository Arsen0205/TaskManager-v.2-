package org.example.petproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField enteringANumber;

    @FXML
    private PasswordField enteringThePassword;

    @FXML
    private Button login;

    @FXML
    private Button register;

    private void handleLogin() throws Exception{
        String phone = enteringANumber.getText();
        String password = enteringThePassword.getText();

        if (SQLqueries.login(phone, password)){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/petproject/mainForm.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Главная страница");

            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.show();
        }
    }

    private void openRegisterForm() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/petproject/registerForm.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Регистрация");

        Scene scene = new Scene(root, 257,330);
        stage.setScene(scene);
        stage.show();
    }

    private void closeForm(){
        Stage stage = (Stage) register.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        register.setOnAction(event ->{
            try {
                openRegisterForm();
                closeForm();
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        login.setOnAction(event -> {
            try {
                handleLogin();
                closeForm();
            }catch(Exception e){
                throw new RuntimeException(e);
            }
        });
    }

}
