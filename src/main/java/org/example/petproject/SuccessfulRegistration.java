package org.example.petproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SuccessfulRegistration {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button resume;

    // Закрытие окна
    private void handleResume() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/petproject/hello-view.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("TaskMaster");

        Scene scene = new Scene(root, 257,330);
        stage.setScene(scene);
        stage.show();
    }

    private void closeForm(){
        Stage stage = (Stage) resume.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        resume.setOnAction(event -> {
            try {
                handleResume();
                closeForm();
            }catch (IOException e){
                e.printStackTrace();
            }
        });
    }

}
