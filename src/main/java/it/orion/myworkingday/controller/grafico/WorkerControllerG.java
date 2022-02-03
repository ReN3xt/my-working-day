package it.orion.myworkingday.controller.grafico;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WorkerControllerG {

    private Scene calendarScene;
    private Stage stage;

    @FXML
    protected void onCalendarButtonClick() {
        stage.setScene(calendarScene);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCalendarScene(Scene calendarScene) {
        this.calendarScene = calendarScene;
    }
}
