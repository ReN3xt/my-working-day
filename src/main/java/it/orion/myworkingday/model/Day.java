package it.orion.myworkingday.model;

import javafx.beans.property.*;

public class Day {

    private String day;
    private String month;
    private String year;

    //TODO Migliorare struttura dati day/month/year

    private final StringProperty selectedDate;

    public Day() {
        this.selectedDate = new SimpleStringProperty();
    }

    public StringProperty selectedDateProperty(){
        return selectedDate;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void updateSelectedDate() {
        selectedDate.set(day + " " + month + " " + year);
    }
}