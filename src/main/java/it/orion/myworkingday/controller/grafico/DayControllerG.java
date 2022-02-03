package it.orion.myworkingday.controller.grafico;

import it.orion.myworkingday.model.Calendar;
import it.orion.myworkingday.model.Day;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DayControllerG {

    Day day;

    Calendar calendar;

    public void setCalendarModel (Calendar calendar){
        this.calendar = calendar;
    }

    private Scene calendarScene;
    private Stage stage;

    @FXML
    private Label selectedDate;

    public void initialize() {

        day = new Day();

        selectedDate.textProperty().bind(day.selectedDateProperty());
    }

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

    public void loadDate(String day, String month, String year){
        this.day.setDay(day);
        this.day.setMonth(month);
        this.day.setYear(year);
        this.day.updateSelectedDate();
    }
}
