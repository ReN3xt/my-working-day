package it.orion.myworkingday.controller.grafico;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CalendarControllerG {

    private Scene dayScene, workerScene;
    private Stage stage;
    private int x = 0;

    @FXML
    private Label welcomeText;
    @FXML
    private Button workerButton, dayButton;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Hai premuto Hello " + x + " volte!");
        x++;
    }

    @FXML
    protected void onWorkerButtonClick() {
        stage.setScene(workerScene);
    }

    @FXML
    protected void onDayButtonClick() {
        stage.setScene(dayScene);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScenes(Scene dayScene, Scene workerScene) {
        this.dayScene = dayScene;
        this.workerScene = workerScene;
    }
}