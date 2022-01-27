package it.orion.myworkingday.controller.grafico;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WorkerControllerG {

    private Scene calendarScene, dayScene;
    private Stage stage;

    @FXML
    private Button calendarButton, dayButton;

    @FXML
    protected void onCalendarButtonClick() {
        stage.setScene(calendarScene);
    }

    @FXML
    protected void onDayButtonClick() {
        stage.setScene(dayScene);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScenes(Scene calendarScene, Scene dayScene) {
        this.calendarScene = calendarScene;
        this.dayScene = dayScene;
    }
}
