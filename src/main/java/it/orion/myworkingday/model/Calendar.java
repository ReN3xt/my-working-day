package it.orion.myworkingday.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Calendar {

    private final StringProperty year;
    private final StringProperty month;
    private final StringProperty[] days;
    private final BooleanProperty[] daysVisibility;

    private LocalDate currentDate;

    public Calendar() {
        this.year = new SimpleStringProperty(String.valueOf(LocalDate.now().getYear()));
        this.month = new SimpleStringProperty(String.valueOf(LocalDate.now().getMonth()));
        this.currentDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1);
        this.days = new SimpleStringProperty[37];
        this.daysVisibility = new SimpleBooleanProperty[37];
    }

    public StringProperty yearProperty() {
        return year;
    }

    public StringProperty monthProperty() {
        return month;
    }

    public StringProperty daysProperty(int i) {
        this.days[i] = new SimpleStringProperty();
        return days[i];
    }

    public BooleanProperty daysVisibilityProperty(int i) {
        this.daysVisibility[i] = new SimpleBooleanProperty();
        return daysVisibility[i];
    }

    public void setPrevMonth() {
        setCurrentDate(currentDate.minusMonths(1));
    }

    public void setNextMonth() {
        setCurrentDate(currentDate.plusMonths(1));
    }

    public void setPrevYear() {
        setCurrentDate(currentDate.minusYears(1));
    }

    public void setNextYear() {
        setCurrentDate(currentDate.plusYears(1));
    }

    public void updateDateLabel() {
        month.set(String.valueOf(currentDate.getMonth()));
        year.set(String.valueOf(currentDate.getYear()));
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public void setDays(int i, int j) {
        this.days[i].setValue(String.valueOf(j));
    }

    public void setDaysVisibility(int i, boolean visibility){
        this.daysVisibility[i].setValue(visibility);
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }
}
