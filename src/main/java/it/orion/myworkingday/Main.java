package it.orion.myworkingday;

import it.orion.myworkingday.controller.grafico.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import it.orion.myworkingday.model.Calendar;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        // Load FXML of all pages
        FXMLLoader calendarFxml = getFxmlLoader("calendarPrimaryView.fxml");

        // Create a Scene for each page
        Scene calendarScene = getScene(calendarFxml);

        // Get Controller Reference for each FXML
        CalendarControllerPrimaryGUI calendarController = calendarFxml.getController();

        // Pass Stage to the controllers
        calendarController.setStage(stage);

        // Set the Windows Title and show the Stage
        stage.setTitle("My Working Day");
        stage.setResizable(false);
        stage.setScene(calendarScene);
        stage.show();
    }

    public static FXMLLoader getFxmlLoader(String fxml) {
        return new FXMLLoader(Main.class.getResource(fxml));
    }

    public static Scene getScene(FXMLLoader fxml) {
        try {
            return new Scene(fxml.load());
        } catch (IOException ignored) {
            return null;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}