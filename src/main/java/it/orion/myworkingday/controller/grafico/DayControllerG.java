package it.orion.myworkingday.controller.grafico;

import it.orion.myworkingday.controller.applicativo.DayControllerA;
import it.orion.myworkingday.controller.applicativo.LoadDataController;
import it.orion.myworkingday.model.Calendar;
import it.orion.myworkingday.model.Day;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class DayControllerG {

    Day day;

    Calendar calendar;

    private Stage stage;

    private Scene calendarScene;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Button cancelButton;

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
        workingDayButton.selectedProperty().bindBidirectional(day.workingDayButtonSelectProperty());

        restButton.disableProperty().bind(day.restButtonDisableProperty());
        restButton.selectedProperty().bindBidirectional(day.restButtonSelectProperty());

        sickLeaveButton.disableProperty().bind(day.sickLeaveButtonDisableProperty());
        sickLeaveButton.selectedProperty().bindBidirectional(day.sickLeaveButtonSelectProperty());

        holidayButton.disableProperty().bind(day.holidayButtonDisableProperty());
        holidayButton.selectedProperty().bindBidirectional(day.holidayButtonSelectProperty());
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
        deleteButton.disableProperty().bind(day.deleteButtonDisableProperty());
        editButton.disableProperty().bind(day.editButtonDisableProperty());
        cancelButton.disableProperty().bind(day.cancelButtonDisableProperty());
        saveButton.disableProperty().bind(day.saveButtonDisableProperty());
    }


    private void checkBoxPropertyBinding() {
        launchBreak.disableProperty().bind(day.launchBreakDisableProperty());
        launchBreak.selectedProperty().bindBidirectional(day.launchBreakSelectProperty());

        overtime.disableProperty().bind(day.overtimeDisableProperty());
        overtime.selectedProperty().bindBidirectional(day.overtimeSelectProperty());

        permit.disableProperty().bind(day.permitDisableProperty());
        permit.selectedProperty().bindBidirectional(day.permitSelectProperty());
    }

    private void textFieldPropertyBinding() {
        sickLeaveProtocol.textProperty().bindBidirectional(day.sickLeaveProtocolContentProperty());
        sickLeaveProtocol.disableProperty().bind(day.sickLeaveProtocolDisableProperty());

        permitReason.textProperty().bindBidirectional(day.permitReasonContentProperty());
        permitReason.disableProperty().bind(day.permitReasonDisableProperty());
    }

    private void textAreaPropertyBinding() {
        notesTextArea.textProperty().bindBidirectional(day.notesTextAreaContentProperty());
        notesTextArea.disableProperty().bind(day.notesTextAreaDisableProperty());

        remindersTextArea.textProperty().bindBidirectional(day.remindersTextAreaContentProperty());
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

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public void loadDay(String day) {

        this.day.setDay(day);
        this.day.setMonth(String.valueOf(calendar.getCurrentDate().getMonth()));
        this.day.setMonthValue(calendar.getCurrentDate().getMonthValue());
        this.day.setYear(String.valueOf(calendar.getCurrentDate().getYear()));

        this.day.updateSelectedDate();

        LoadDataController loadDataController = new LoadDataController();

        loadDataController.loadData(this.day);
    }

    public void onEditButtonClick() {
        DayControllerA dayController = new DayControllerA();

        dayController.edit(day);
    }

    public void onCancelButtonClick() {
        DayControllerA dayControllerA = new DayControllerA();

        dayControllerA.cancel(day);
    }

    public void onDeleteButtonClick() {
        DayControllerA dayControllerA = new DayControllerA();

        dayControllerA.delete(day);

    }
    public void onSaveButtonClick() {
        DayControllerA dayControllerA = new DayControllerA();

        dayControllerA.save(day);
    }

    public void onWorkingDayButtonClick() {
        DayControllerA dayControllerA = new DayControllerA();

        dayControllerA.disableWorkingDayForm(day, !day.isWorkingDayButtonSelect());
    }

    public void onRestButtonClick() {
        DayControllerA dayControllerA = new DayControllerA();

        dayControllerA.disableWorkingDayForm(day, true);
    }

    public void onSickLeaveButtonClick() {
        DayControllerA dayControllerA = new DayControllerA();

        dayControllerA.disableSickLeaveForm(day, !day.isSickLeaveButtonSelect());
    }

    public void onHolidayButtonClick() {
        DayControllerA dayControllerA = new DayControllerA();

        dayControllerA.disableWorkingDayForm(day, true);
    }

    public void onLaunchBreakCheck() {
        DayControllerA dayControllerA = new DayControllerA();

        dayControllerA.disableLaunchBreakForm(day, !day.isLaunchBreakSelect());
    }

    public void onOvertimeCheck() {
        DayControllerA dayControllerA = new DayControllerA();

        dayControllerA.disableOvertimeForm(day, !day.isOvertimeSelect());
    }

    public void onPermitCheck() {
        DayControllerA dayControllerA = new DayControllerA();

        dayControllerA.disablePermitForm(day, !day.isPermitSelect());
    }
}
