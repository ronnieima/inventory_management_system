module kaito.software1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens kaito.software1 to javafx.fxml;
    exports kaito.software1;

}