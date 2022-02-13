package it.orion.myworkingday.model;

import javafx.beans.property.*;
import javafx.scene.control.SingleSelectionModel;

public class Worker {

    private boolean load;

    private final StringProperty firstNameContent;
    private final StringProperty lastNameContent;
    private final StringProperty workContent;
    private final StringProperty salaryPerHourContent;
    private final StringProperty overtimeSalaryContent;

    private final BooleanProperty firstNameDisable;
    private final BooleanProperty lastNameDisable;
    private final BooleanProperty workDisable;
    private final BooleanProperty defaultWorkingHoursDisable;
    private final BooleanProperty defaultWorkingHoursColonDisable;
    private final BooleanProperty salaryPerHourDisable;
    private final BooleanProperty overtimeSalaryDisable;
    private final BooleanProperty firstNameContentDisable;
    private final BooleanProperty lastNameContentDisable;
    private final BooleanProperty workContentDisable;
    private final BooleanProperty salaryPerHourContentDisable;
    private final BooleanProperty overtimeSalaryContentDisable;
    private final BooleanProperty defaultWorkingHoursHDisable;
    private final BooleanProperty defaultWorkingHoursMDisable;
    private final BooleanProperty editButtonDisable;
    private final BooleanProperty cancelButtonDisable;
    private final BooleanProperty saveButtonDisable;

    private final ObjectProperty<SingleSelectionModel<String>> defaultWorkingHoursHSelectionModel;
    private final ObjectProperty<SingleSelectionModel<String>> defaultWorkingHoursMSelectionModel;

    public Worker() {
        this.firstNameContent = new SimpleStringProperty();
        this.lastNameContent = new SimpleStringProperty();
        this.workContent = new SimpleStringProperty();
        this.salaryPerHourContent = new SimpleStringProperty();
        this.overtimeSalaryContent = new SimpleStringProperty();

        this.firstNameDisable = new SimpleBooleanProperty(true);
        this.lastNameDisable = new SimpleBooleanProperty(true);
        this.workDisable = new SimpleBooleanProperty(true);
        this.defaultWorkingHoursDisable = new SimpleBooleanProperty(true);
        this.defaultWorkingHoursColonDisable = new SimpleBooleanProperty(true);
        this.salaryPerHourDisable = new SimpleBooleanProperty(true);
        this.overtimeSalaryDisable = new SimpleBooleanProperty(true);
        this.firstNameContentDisable = new SimpleBooleanProperty(true);
        this.lastNameContentDisable = new SimpleBooleanProperty(true);
        this.workContentDisable = new SimpleBooleanProperty(true);
        this.salaryPerHourContentDisable = new SimpleBooleanProperty(true);
        this.overtimeSalaryContentDisable = new SimpleBooleanProperty(true);
        this.defaultWorkingHoursHDisable = new SimpleBooleanProperty(true);
        this.defaultWorkingHoursMDisable = new SimpleBooleanProperty(true);
        this.editButtonDisable = new SimpleBooleanProperty(false);
        this.cancelButtonDisable = new SimpleBooleanProperty(true);
        this.saveButtonDisable = new SimpleBooleanProperty(true);

        this.defaultWorkingHoursHSelectionModel = new SimpleObjectProperty<>();
        this.defaultWorkingHoursMSelectionModel = new SimpleObjectProperty<>();
    }

    public String getFirstNameContent() {
        return firstNameContent.get();
    }

    public StringProperty firstNameContentProperty() {
        return firstNameContent;
    }

    public String getLastNameContent() {
        return lastNameContent.get();
    }

    public StringProperty lastNameContentProperty() {
        return lastNameContent;
    }

    public String getWorkContent() {
        return workContent.get();
    }

    public StringProperty workContentProperty() {
        return workContent;
    }

    public String getSalaryPerHourContent() {
        return salaryPerHourContent.get();
    }

    public StringProperty salaryPerHourContentProperty() {
        return salaryPerHourContent;
    }

    public String getOvertimeSalaryContent() {
        return overtimeSalaryContent.get();
    }

    public StringProperty overtimeSalaryContentProperty() {
        return overtimeSalaryContent;
    }

    public BooleanProperty firstNameDisableProperty() {
        return firstNameDisable;
    }

    public BooleanProperty lastNameDisableProperty() {
        return lastNameDisable;
    }

    public BooleanProperty workDisableProperty() {
        return workDisable;
    }

    public BooleanProperty defaultWorkingHoursDisableProperty() {
        return defaultWorkingHoursDisable;
    }

    public BooleanProperty defaultWorkingHoursColonDisableProperty() {
        return defaultWorkingHoursColonDisable;
    }

    public BooleanProperty salaryPerHourDisableProperty() {
        return salaryPerHourDisable;
    }

    public BooleanProperty overtimeSalaryDisableProperty() {
        return overtimeSalaryDisable;
    }

    public BooleanProperty firstNameContentDisableProperty() {
        return firstNameContentDisable;
    }

    public BooleanProperty lastNameContentDisableProperty() {
        return lastNameContentDisable;
    }

    public BooleanProperty workContentDisableProperty() {
        return workContentDisable;
    }

    public BooleanProperty salaryPerHourContentDisableProperty() {
        return salaryPerHourContentDisable;
    }

    public BooleanProperty overtimeSalaryContentDisableProperty() {
        return overtimeSalaryContentDisable;
    }

    public BooleanProperty defaultWorkingHoursHDisableProperty() {
        return defaultWorkingHoursHDisable;
    }

    public BooleanProperty defaultWorkingHoursMDisableProperty() {
        return defaultWorkingHoursMDisable;
    }

    public BooleanProperty editButtonDisableProperty() {
        return editButtonDisable;
    }

    public BooleanProperty cancelButtonDisableProperty() {
        return cancelButtonDisable;
    }

    public BooleanProperty saveButtonDisableProperty() {
        return saveButtonDisable;
    }

    public SingleSelectionModel<String> getDefaultWorkingHoursHSelectionModel() {
        return defaultWorkingHoursHSelectionModel.get();
    }

    public ObjectProperty<SingleSelectionModel<String>> defaultWorkingHoursHSelectionModelProperty() {
        return defaultWorkingHoursHSelectionModel;
    }

    public SingleSelectionModel<String> getDefaultWorkingHoursMSelectionModel() {
        return defaultWorkingHoursMSelectionModel.get();
    }

    public ObjectProperty<SingleSelectionModel<String>> defaultWorkingHoursMSelectionModelProperty() {
        return defaultWorkingHoursMSelectionModel;
    }

    public void setFirstNameContent(String firstNameContent) {
        this.firstNameContent.set(firstNameContent);
    }

    public void setLastNameContent(String lastNameContent) {
        this.lastNameContent.set(lastNameContent);
    }

    public void setWorkContent(String workContent) {
        this.workContent.set(workContent);
    }

    public void setSalaryPerHourContent(String salaryPerHourContent) {
        this.salaryPerHourContent.set(salaryPerHourContent);
    }

    public void setOvertimeSalaryContent(String overtimeSalaryContent) {
        this.overtimeSalaryContent.set(overtimeSalaryContent);
    }

    public void setFirstNameDisable(boolean firstNameDisable) {
        this.firstNameDisable.set(firstNameDisable);
    }

    public void setLastNameDisable(boolean lastNameDisable) {
        this.lastNameDisable.set(lastNameDisable);
    }

    public void setWorkDisable(boolean workDisable) {
        this.workDisable.set(workDisable);
    }

    public void setDefaultWorkingHoursDisable(boolean defaultWorkingHoursDisable) {
        this.defaultWorkingHoursDisable.set(defaultWorkingHoursDisable);
    }

    public void setDefaultWorkingHoursColonDisable(boolean defaultWorkingHoursColonDisable) {
        this.defaultWorkingHoursColonDisable.set(defaultWorkingHoursColonDisable);
    }

    public void setSalaryPerHourDisable(boolean salaryPerHourDisable) {
        this.salaryPerHourDisable.set(salaryPerHourDisable);
    }

    public void setOvertimeSalaryDisable(boolean overtimeSalaryDisable) {
        this.overtimeSalaryDisable.set(overtimeSalaryDisable);
    }

    public void setFirstNameContentDisable(boolean firstNameContentDisable) {
        this.firstNameContentDisable.set(firstNameContentDisable);
    }

    public void setLastNameContentDisable(boolean lastNameContentDisable) {
        this.lastNameContentDisable.set(lastNameContentDisable);
    }

    public void setWorkContentDisable(boolean workContentDisable) {
        this.workContentDisable.set(workContentDisable);
    }

    public void setSalaryPerHourContentDisable(boolean salaryPerHourContentDisable) {
        this.salaryPerHourContentDisable.set(salaryPerHourContentDisable);
    }

    public void setOvertimeSalaryContentDisable(boolean overtimeSalaryContentDisable) {
        this.overtimeSalaryContentDisable.set(overtimeSalaryContentDisable);
    }

    public void setDefaultWorkingHoursHDisable(boolean defaultWorkingHoursHDisable) {
        this.defaultWorkingHoursHDisable.set(defaultWorkingHoursHDisable);
    }

    public void setDefaultWorkingHoursMDisable(boolean defaultWorkingHoursMDisable) {
        this.defaultWorkingHoursMDisable.set(defaultWorkingHoursMDisable);
    }

    public void setEditButtonDisable(boolean editButtonDisable) {
        this.editButtonDisable.set(editButtonDisable);
    }

    public void setCancelButtonDisable(boolean cancelButtonDisable) {
        this.cancelButtonDisable.set(cancelButtonDisable);
    }

    public void setSaveButtonDisable(boolean saveButtonDisable) {
        this.saveButtonDisable.set(saveButtonDisable);
    }

    public void setLoad(boolean load) {
        this.load = load;
    }

    public boolean isLoad() {
        return this.load;
    }
}
