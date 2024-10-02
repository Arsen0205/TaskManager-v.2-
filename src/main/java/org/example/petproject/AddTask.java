package org.example.petproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddTask {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addTaskButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextArea descriptionText;

    @FXML
    private TextField titleText;


    private void closeForm(){
        Stage stage = (Stage) addTaskButton.getScene().getWindow();
        stage.close();
    }

    private void handleTaskAdd(){
        String title = titleText.getText();
        String description = descriptionText.getText();

        SQLqueries.addTask(title, description);
    }

    private void openMainForm() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/petproject/mainForm.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Главная страница");

        Scene scene = new Scene(root, 600,400);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {
        addTaskButton.setOnAction(event -> {
            try {
                handleTaskAdd();
                openMainForm();
                closeForm();
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        });
        cancelButton.setOnAction(event -> {
            try {
                closeForm();
                openMainForm();
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        });
    }

}
