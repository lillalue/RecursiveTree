module com.oblig1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.oblig1 to javafx.fxml;
    exports com.oblig1;
}