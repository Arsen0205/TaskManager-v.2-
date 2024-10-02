package org.example.petproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private Button cancelButton;

    @FXML
    void initialize() {
        // Действие при нажатии кнопки "Зарегистрироваться"
        registerButton.setOnAction(event ->{
            try{
                handleRegister();
                closeForm();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        // Действие при нажатии кнопки "Отмена"
        cancelButton.setOnAction(event -> {
            try {
                handleCancel();
                closeForm();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    // Обработка регистрации
    private void handleRegister() throws IOException {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String password = passwordField.getText();

        String hashPassword = PasswordUtils.hashPassword(password);


       if(SQLqueries.register(name, phone, hashPassword)){
           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/petproject/successful-registration.fxml"));
           Parent root = fxmlLoader.load();

           Stage stage = new Stage();
           stage.setTitle("Успешная регистрация");

           Scene scene = new Scene(root, 487, 94);
           stage.setScene(scene);
           stage.show();
       };
    }

    // Закрытие окна
    private void handleCancel() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/petproject/hello-view.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("TaskMaster");

        Scene scene = new Scene(root, 257,330);
        stage.setScene(scene);
        stage.show();
    }

    private void closeForm(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    
}
