package it.orion.myworkingday;

import it.orion.myworkingday.controller.grafico.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        // Load FXML of all pages
        FXMLLoader calendarFxml = getFxmlLoader("calendarView.fxml");
        FXMLLoader dayFxml = getFxmlLoader("dayView.fxml");
        FXMLLoader workerFxml = getFxmlLoader("workerView.fxml");

        // Create a Scene for each page
        Scene calendarScene = getScene(calendarFxml);
        Scene dayScene = getScene(dayFxml);
        Scene workerScene = getScene(workerFxml);

        // Get Controller Reference for each FXML
        CalendarControllerG calendarController = calendarFxml.getController();
        DayControllerG dayController = dayFxml.getController();
        WorkerControllerG workerController = workerFxml.getController();

        // Pass Stage to the controllers
        calendarController.setStage(stage);
        dayController.setStage(stage);
        workerController.setStage(stage);

        // Pass all Scene References to the controllers
        calendarController.setScenes(dayScene, workerScene);
        dayController.setScenes(calendarScene, workerScene);
        workerController.setScenes(calendarScene, dayScene);

        // Set the Windows Title and show the Stage
        stage.setTitle("My Working Day");
        stage.setScene(calendarScene);
        stage.show();
    }

    public FXMLLoader getFxmlLoader(String fxml) {
        return new FXMLLoader(Main.class.getResource(fxml));
    }

    public Scene getScene(FXMLLoader fxml) throws IOException {
        return new Scene(fxml.load());
    }

    public static void main(String[] args) {
        launch();
    }
}