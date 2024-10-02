// TaskDetailsController.java
package org.example.petproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class TaskDetailsController {

    @FXML
    private Label taskTitleLabel;

    @FXML
    private TextArea taskDescriptionArea;

    @FXML
    private Button cancelButtonTask;

    @FXML
    private Button perfomButton;

    private Task task;

    @FXML
    void initialize() {
        cancelButtonTask.setOnAction(event -> {
            try {
                closeForm();
                openMainForm();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        perfomButton.setOnAction(event -> {
            try {
                deleteTask();
                openMainForm();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    // Метод для установки данных задачи
    public void setTask(Task task) {
        this.task = task;
        taskTitleLabel.setText(task.getTitle());
        taskDescriptionArea.setText(task.getDescription());
    }

    private void closeForm(){
        Stage stage = (Stage) cancelButtonTask.getScene().getWindow();
        stage.close();
    }


    private void deleteTask() throws Exception{
        SQLqueries.perfomTask(task.getId());

        Stage stage = (Stage) perfomButton.getScene().getWindow();
        stage.close();
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
}
