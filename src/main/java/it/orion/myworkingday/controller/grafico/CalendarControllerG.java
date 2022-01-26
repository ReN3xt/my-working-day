package it.orion.myworkingday.controller.grafico;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CalendarControllerG {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}