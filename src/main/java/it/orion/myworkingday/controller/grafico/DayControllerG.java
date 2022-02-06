package it.orion.myworkingday.controller.grafico;

import it.orion.myworkingday.model.Calendar;
import it.orion.myworkingday.model.Day;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class DayControllerG {

    Day day;

    Calendar calendar;

    private Stage stage;

    private Scene calendarScene;

    @FXML
    private Button editButton;

    @FXML
    private Button saveButton;

    @FXML
    private Label selectedDate;

    @FXML
    private ToggleButton workingDayButton;

    @FXML
    private ToggleButton restButton;

    @FXML
    private ToggleButton sickLeaveButton;

    @FXML
    private ToggleButton holidayButton;

    @FXML
    private Label workingHours;

    @FXML
    private Label workingHoursStartColon;

    @FXML
    private Label workingHoursEndColon;

    @FXML
    private Label workingHoursHyphen;

    @FXML
    private ComboBox<String> workingHoursStartH;

    @FXML
    private ComboBox<String> workingHoursStartM;

    @FXML
    private ComboBox<String> workingHoursEndH;

    @FXML
    private ComboBox<String> workingHoursEndM;

    @FXML
    private CheckBox launchBreak;

    @FXML
    private Label launchBreakStartColon;

    @FXML
    private Label launchBreakEndColon;

    @FXML
    private Label launchBreakHyphen;

    @FXML
    private ComboBox<String> launchBreakStartH;

    @FXML
    private ComboBox<String> launchBreakStartM;

    @FXML
    private ComboBox<String> launchBreakEndH;

    @FXML
    private ComboBox<String> launchBreakEndM;

    @FXML
    private CheckBox overtime;

    @FXML
    private Label overtimeColon;

    @FXML
    private ComboBox<String> overtimeH;

    @FXML
    private ComboBox<String> overtimeM;

    @FXML
    private Label sickLeave;

    @FXML
    private Label sickLeaveColon;

    @FXML
    private TextField sickLeaveProtocol;

    @FXML
    private CheckBox permit;

    @FXML
    private Label permitColon;

    @FXML
    private Label permitHyphen;

    @FXML
    private ComboBox<String> permitH;

    @FXML
    private ComboBox<String> permitM;

    @FXML
    private TextField permitReason;

    @FXML
    private Label notes;

    @FXML
    private TextArea notesTextArea;

    @FXML
    private Label reminders;

    @FXML
    private TextArea remindersTextArea;


    public void initialize() {

        day = new Day();

        initializePropertyBinding();
    }

    public void initializePropertyBinding() {
        labelPropertyBinding();

        toggleButtonPropertyBinding();

        comboBoxPropertyBinding();

        buttonPropertyBinding();

        checkBoxPropertyBinding();

        textFieldPropertyBinding();

        textAreaPropertyBinding();
    }

    private void labelPropertyBinding() {
        selectedDate.textProperty().bind(day.selectedDateContentProperty());

        workingHours.disableProperty().bind(day.workingHoursDisableProperty());
        workingHoursStartColon.disableProperty().bind(day.workingHoursStartColonDisableProperty());
        workingHoursEndColon.disableProperty().bind(day.workingHoursEndColonDisableProperty());
        workingHoursHyphen.disableProperty().bind(day.workingHoursHyphenDisableProperty());

        launchBreakStartColon.disableProperty().bind(day.launchBreakStartColonDisableProperty());
        launchBreakEndColon.disableProperty().bind(day.launchBreakEndColonDisableProperty());
        launchBreakHyphen.disableProperty().bind(day.launchBreakHyphenDisableProperty());

        overtimeColon.disableProperty().bind(day.overtimeColonDisableProperty());

        sickLeave.disableProperty().bind(day.sickLeaveDisableProperty());
        sickLeaveColon.disableProperty().bind(day.sickLeaveColonDisableProperty());

        permitColon.disableProperty().bind(day.permitColonDisableProperty());
        permitHyphen.disableProperty().bind(day.permitHyphenDisableProperty());

        notes.disableProperty().bind(day.notesDisableProperty());

        reminders.disableProperty().bind(day.remindersDisableProperty());
    }

    private void toggleButtonPropertyBinding() {
        workingDayButton.disableProperty().bind(day.workingDayButtonDisableProperty());
        workingDayButton.selectedProperty().bind(day.workingDayButtonSelectProperty());

        restButton.disableProperty().bind(day.restButtonDisableProperty());
        restButton.selectedProperty().bind(day.restButtonSelectProperty());

        sickLeaveButton.disableProperty().bind(day.sickLeaveButtonDisableProperty());
        sickLeaveButton.selectedProperty().bind(day.sickLeaveButtonSelectProperty());

        holidayButton.disableProperty().bind(day.holidayButtonDisableProperty());
        holidayButton.selectedProperty().bind(day.holidayButtonSelectProperty());
    }

    private void comboBoxPropertyBinding() {
        workingHoursStartH.disableProperty().bind(day.workingHoursStartHDisableProperty());
        day.workingHoursStartHSelectionModelProperty().bind(workingHoursStartH.selectionModelProperty());

        workingHoursStartM.disableProperty().bind(day.workingHoursStartMDisableProperty());
        day.workingHoursStartMSelectionModelProperty().bind(workingHoursStartM.selectionModelProperty());

        workingHoursEndH.disableProperty().bind(day.workingHoursEndHDisableProperty());
        day.workingHoursEndHSelectionModelProperty().bind(workingHoursEndH.selectionModelProperty());

        workingHoursEndM.disableProperty().bind(day.workingHoursEndMDisableProperty());
        day.workingHoursEndMSelectionModelProperty().bind(workingHoursEndM.selectionModelProperty());

        launchBreakStartH.disableProperty().bind(day.launchBreakStartHDisableProperty());
        day.launchBreakStartHSelectionModelProperty().bind(launchBreakStartH.selectionModelProperty());

        launchBreakStartM.disableProperty().bind(day.launchBreakStartMDisableProperty());
        day.launchBreakStartMSelectionModelProperty().bind(launchBreakStartM.selectionModelProperty());

        launchBreakEndH.disableProperty().bind(day.launchBreakEndHDisableProperty());
        day.launchBreakEndHSelectionModelProperty().bind(launchBreakEndH.selectionModelProperty());

        launchBreakEndM.disableProperty().bind(day.launchBreakEndMDisableProperty());
        day.launchBreakEndMSelectionModelProperty().bind(launchBreakEndM.selectionModelProperty());

        overtimeH.disableProperty().bind(day.overtimeHDisableProperty());
        day.overtimeHSelectionModelProperty().bind(overtimeH.selectionModelProperty());

        overtimeM.disableProperty().bind(day.overtimeMDisableProperty());
        day.overtimeMSelectionModelProperty().bind(overtimeM.selectionModelProperty());

        permitH.disableProperty().bind(day.permitHDisableProperty());
        day.permitHSelectionModelProperty().bind(permitH.selectionModelProperty());

        permitM.disableProperty().bind(day.permitMDisableProperty());
        day.permitMSelectionModelProperty().bind(permitM.selectionModelProperty());
    }

    private void buttonPropertyBinding() {
        editButton.disableProperty().bind(day.editButtonDisableProperty());
        saveButton.disableProperty().bind(day.saveButtonDisableProperty());
    }

    private void checkBoxPropertyBinding() {
        launchBreak.disableProperty().bind(day.launchBreakDisableProperty());
        launchBreak.selectedProperty().bind(day.launchBreakSelectProperty());

        overtime.disableProperty().bind(day.overtimeDisableProperty());
        overtime.selectedProperty().bind(day.overtimeSelectProperty());

        permit.disableProperty().bind(day.permitDisableProperty());
        permit.selectedProperty().bind(day.permitSelectProperty());
    }

    private void textFieldPropertyBinding() {
        sickLeaveProtocol.textProperty().bind(day.sickLeaveProtocolContentProperty());
        sickLeaveProtocol.disableProperty().bind(day.sickLeaveProtocolDisableProperty());

        permitReason.textProperty().bind(day.permitReasonContentProperty());
        permitReason.disableProperty().bind(day.permitReasonDisableProperty());
    }

    private void textAreaPropertyBinding() {
        notesTextArea.textProperty().bind(day.notesTextAreaContentProperty());
        notesTextArea.disableProperty().bind(day.notesTextAreaDisableProperty());

        remindersTextArea.textProperty().bind(day.remindersTextAreaContentProperty());
        remindersTextArea.disableProperty().bind(day.remindersTextAreaDisableProperty());
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

    public void onEditButtonClick() {
    }
}
