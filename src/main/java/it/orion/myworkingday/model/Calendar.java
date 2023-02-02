package it.orion.myworkingday.model;

import javafx.beans.property.*;
import javafx.scene.paint.Paint;

import java.time.LocalDate;

public class Calendar {

    private static Calendar instance;

    private final StringProperty monthSalary;
    private final StringProperty year;
    private final StringProperty month;
    private final StringProperty monthValue;
    private final StringProperty[] days;
    private final BooleanProperty[] daysVisibility;
    private final BooleanProperty[] daysUnderline;
    private final ObjectProperty<Paint>[] daysColor;

    private LocalDate currentDate;

    private boolean secondView;

    public Calendar() {
        this.monthSalary = new SimpleStringProperty();
        this.year = new SimpleStringProperty(String.valueOf(LocalDate.now().getYear()));
        this.month = new SimpleStringProperty(String.valueOf(LocalDate.now().getMonth()));
        this.monthValue = new SimpleStringProperty(String.valueOf(LocalDate.now().getMonthValue()));
        this.currentDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1);
        this.days = new SimpleStringProperty[37];
        this.daysVisibility = new SimpleBooleanProperty[37];
        this.daysUnderline = new SimpleBooleanProperty[37];
        this.daysColor = new SimpleObjectProperty[37];
        this.secondView = false;
    }

    public static Calendar getInstance() {
        if(instance == null) {
            instance = new Calendar();
        }
        return instance;
    }

    public StringProperty monthSalaryProperty() {
        return monthSalary;
    }

    public StringProperty yearProperty() {
        return year;
    }

    public StringProperty monthProperty() {
        return month;
    }

    public StringProperty monthValueProperty() {
        return monthValue;
    }

    public StringProperty daysProperty(int i) {
        this.days[i] = new SimpleStringProperty();
        return days[i];
    }

    public BooleanProperty daysVisibilityProperty(int i) {
        this.daysVisibility[i] = new SimpleBooleanProperty();
        return daysVisibility[i];
    }

    public BooleanProperty daysUnderlineProperty(int i) {
        this.daysUnderline[i] = new SimpleBooleanProperty(false);
        return daysUnderline[i];
    }

    public ObjectProperty<Paint> daysColorProperty(int i) {
        this.daysColor[i] = new SimpleObjectProperty<>(Paint.valueOf("#000000"));
        return daysColor[i];
    }

    public void setMonthSalary(String monthSalary) {
        this.monthSalary.set(monthSalary);
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

    public void setSecondView(boolean secondView) {
        this.secondView = secondView;
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

    public void setDaysUnderline(int i, boolean b){
        this.daysUnderline[i].setValue(b);
    }

    public void setDaysColor(int i, String color){
        this.daysColor[i].setValue(Paint.valueOf(color));
    }

    public boolean isSecondView() {
        return this.secondView;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void updateDateLabel() {
        if(currentDate.getMonthValue() <= 9) {
            monthValue.set("0" + currentDate.getMonthValue());
        } else {
            monthValue.set(String.valueOf(currentDate.getMonthValue()));
        }

        month.set(String.valueOf(currentDate.getMonth()));
        year.set(String.valueOf(currentDate.getYear()));
    }
}
