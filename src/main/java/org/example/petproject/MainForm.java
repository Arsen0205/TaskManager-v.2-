package org.example.petproject;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainForm {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addTask;

    @FXML
    private VBox taskButtonsContainer;

    @FXML
    private TextArea taskDescriptionField;

    @FXML
    private TextField taskTitleField;

    private void openAddTaskForm() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/petproject/addTask.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Добавить задачу");

        Scene scene = new Scene(root, 259,400);
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    void initialize() {
        addTask.setOnAction(event ->{
            try {
                openAddTaskForm();
                closeForm();
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        });

        try {
            loadAndDisplayTasks();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void loadAndDisplayTasks() throws Exception {
        // Загрузка задач из базы данных
        List<Task> tasks = SQLqueries.loadTasks(CurrentUser.getUserId());

        // Создание кнопок для каждой задачи
        for (Task task : tasks) {
            Button taskButton = new Button(task.getTitle());
            taskButton.setOnAction(event -> {
                try {
                    openTaskDetailsForm(task);
                    closeForm();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            taskButtonsContainer.getChildren().add(taskButton);
        }
    }

    private void openTaskDetailsForm(Task task) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/petproject/taskDetailsController.fxml"));
        Parent root = loader.load();

        TaskDetailsController taskDetailsController = loader.getController();
        taskDetailsController.setTask(task);

        Stage stage = new Stage();
        stage.setTitle("Детали задачи");
        stage.setScene(new Scene(root, 221, 400));
        stage.show();
    }

    private void closeForm(){
        Stage stage = (Stage) addTask.getScene().getWindow();
        stage.close();
    }
}
