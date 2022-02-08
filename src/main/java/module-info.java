module it.orion.myworkingday {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires json.simple;


    exports it.orion.myworkingday;
    exports it.orion.myworkingday.model;
    exports it.orion.myworkingday.controller.grafico;
    opens it.orion.myworkingday.controller.grafico to javafx.fxml;
    exports it.orion.myworkingday.controller.applicativo;
    opens it.orion.myworkingday.controller.applicativo to javafx.fxml;
}