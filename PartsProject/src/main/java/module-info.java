module humphrey.partsproject {
    requires javafx.controls;
    requires javafx.fxml;



    exports humphrey.model;
    opens humphrey.model to javafx.fxml;
    exports humphrey.controller;
    opens humphrey.controller to javafx.fxml;
}