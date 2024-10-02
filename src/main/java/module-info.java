module org.example.petproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;


    opens org.example.petproject to javafx.fxml;
    exports org.example.petproject;
}