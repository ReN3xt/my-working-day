module it.orion.myworkingday {
    requires javafx.controls;
    requires javafx.fxml;


    opens it.orion.myworkingday to javafx.fxml;
    exports it.orion.myworkingday;
    exports it.orion.myworkingday.controller;
    opens it.orion.myworkingday.controller to javafx.fxml;
}