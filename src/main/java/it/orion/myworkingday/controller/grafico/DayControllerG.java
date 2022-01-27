package it.orion.myworkingday.controller.grafico;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class DayControllerG {

    private Scene calendarScene, workerScene;
    private Stage stage;

    @FXML
    private Button calendarButton, workerButton;

    @FXML
    protected void onCalendarButtonClick() {
        stage.setScene(calendarScene);
    }

    @FXML
    protected void onWorkerButtonClick() {
        stage.setScene(workerScene);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScenes (Scene calendarScene, Scene workerScene) {
        this.calendarScene = calendarScene;
        this.workerScene = workerScene;
    }
}
