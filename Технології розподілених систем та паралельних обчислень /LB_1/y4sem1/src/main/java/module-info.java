module com.example.y4sem1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.Lab1 to javafx.fxml;
    exports com.example.Lab1;
}