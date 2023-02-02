package it.orion.myworkingday.controller.grafico;

import it.orion.myworkingday.controller.applicativo.CalendarController;
import it.orion.myworkingday.controller.applicativo.DayController;
import it.orion.myworkingday.controller.applicativo.LoadDataController;
import it.orion.myworkingday.model.Calendar;
import it.orion.myworkingday.model.Day;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class DayControllerGUI {

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
        selectedDate.textProperty().bind(Day.getInstance().selectedDateContentProperty());

        workingHours.disableProperty().bind(Day.getInstance().workingHoursDisableProperty());
        workingHoursStartColon.disableProperty().bind(Day.getInstance().workingHoursStartColonDisableProperty());
        workingHoursEndColon.disableProperty().bind(Day.getInstance().workingHoursEndColonDisableProperty());
        workingHoursHyphen.disableProperty().bind(Day.getInstance().workingHoursHyphenDisableProperty());

        launchBreakStartColon.disableProperty().bind(Day.getInstance().launchBreakStartColonDisableProperty());
        launchBreakEndColon.disableProperty().bind(Day.getInstance().launchBreakEndColonDisableProperty());
        launchBreakHyphen.disableProperty().bind(Day.getInstance().launchBreakHyphenDisableProperty());

        overtimeColon.disableProperty().bind(Day.getInstance().overtimeColonDisableProperty());

        sickLeave.disableProperty().bind(Day.getInstance().sickLeaveDisableProperty());
        sickLeaveColon.disableProperty().bind(Day.getInstance().sickLeaveColonDisableProperty());

        permitColon.disableProperty().bind(Day.getInstance().permitColonDisableProperty());
        permitHyphen.disableProperty().bind(Day.getInstance().permitHyphenDisableProperty());

        notes.disableProperty().bind(Day.getInstance().notesDisableProperty());

        reminders.disableProperty().bind(Day.getInstance().remindersDisableProperty());

    }

    private void toggleButtonPropertyBinding() {
        workingDayButton.disableProperty().bind(Day.getInstance().workingDayButtonDisableProperty());
        workingDayButton.selectedProperty().bindBidirectional(Day.getInstance().workingDayButtonSelectProperty());

        restButton.disableProperty().bind(Day.getInstance().restButtonDisableProperty());
        restButton.selectedProperty().bindBidirectional(Day.getInstance().restButtonSelectProperty());

        sickLeaveButton.disableProperty().bind(Day.getInstance().sickLeaveButtonDisableProperty());
        sickLeaveButton.selectedProperty().bindBidirectional(Day.getInstance().sickLeaveButtonSelectProperty());

        holidayButton.disableProperty().bind(Day.getInstance().holidayButtonDisableProperty());
        holidayButton.selectedProperty().bindBidirectional(Day.getInstance().holidayButtonSelectProperty());
    }

    private void comboBoxPropertyBinding() {
        workingHoursStartH.disableProperty().bind(Day.getInstance().workingHoursStartHDisableProperty());
        Day.getInstance().workingHoursStartHSelectionModelProperty().bind(workingHoursStartH.selectionModelProperty());

        workingHoursStartM.disableProperty().bind(Day.getInstance().workingHoursStartMDisableProperty());
        Day.getInstance().workingHoursStartMSelectionModelProperty().bind(workingHoursStartM.selectionModelProperty());

        workingHoursEndH.disableProperty().bind(Day.getInstance().workingHoursEndHDisableProperty());
        Day.getInstance().workingHoursEndHSelectionModelProperty().bind(workingHoursEndH.selectionModelProperty());

        workingHoursEndM.disableProperty().bind(Day.getInstance().workingHoursEndMDisableProperty());
        Day.getInstance().workingHoursEndMSelectionModelProperty().bind(workingHoursEndM.selectionModelProperty());

        launchBreakStartH.disableProperty().bind(Day.getInstance().launchBreakStartHDisableProperty());
        Day.getInstance().launchBreakStartHSelectionModelProperty().bind(launchBreakStartH.selectionModelProperty());

        launchBreakStartM.disableProperty().bind(Day.getInstance().launchBreakStartMDisableProperty());
        Day.getInstance().launchBreakStartMSelectionModelProperty().bind(launchBreakStartM.selectionModelProperty());

        launchBreakEndH.disableProperty().bind(Day.getInstance().launchBreakEndHDisableProperty());
        Day.getInstance().launchBreakEndHSelectionModelProperty().bind(launchBreakEndH.selectionModelProperty());

        launchBreakEndM.disableProperty().bind(Day.getInstance().launchBreakEndMDisableProperty());
        Day.getInstance().launchBreakEndMSelectionModelProperty().bind(launchBreakEndM.selectionModelProperty());

        overtimeH.disableProperty().bind(Day.getInstance().overtimeHDisableProperty());
        Day.getInstance().overtimeHSelectionModelProperty().bind(overtimeH.selectionModelProperty());

        overtimeM.disableProperty().bind(Day.getInstance().overtimeMDisableProperty());
        Day.getInstance().overtimeMSelectionModelProperty().bind(overtimeM.selectionModelProperty());

        permitH.disableProperty().bind(Day.getInstance().permitHDisableProperty());
        Day.getInstance().permitHSelectionModelProperty().bind(permitH.selectionModelProperty());

        permitM.disableProperty().bind(Day.getInstance().permitMDisableProperty());
        Day.getInstance().permitMSelectionModelProperty().bind(permitM.selectionModelProperty());
    }

    private void buttonPropertyBinding() {
        deleteButton.disableProperty().bind(Day.getInstance().deleteButtonDisableProperty());
        editButton.disableProperty().bind(Day.getInstance().editButtonDisableProperty());
        cancelButton.disableProperty().bind(Day.getInstance().cancelButtonDisableProperty());
        saveButton.disableProperty().bind(Day.getInstance().saveButtonDisableProperty());
    }


    private void checkBoxPropertyBinding() {
        launchBreak.disableProperty().bind(Day.getInstance().launchBreakDisableProperty());
        launchBreak.selectedProperty().bindBidirectional(Day.getInstance().launchBreakSelectProperty());

        overtime.disableProperty().bind(Day.getInstance().overtimeDisableProperty());
        overtime.selectedProperty().bindBidirectional(Day.getInstance().overtimeSelectProperty());

        permit.disableProperty().bind(Day.getInstance().permitDisableProperty());
        permit.selectedProperty().bindBidirectional(Day.getInstance().permitSelectProperty());
    }

    private void textFieldPropertyBinding() {
        sickLeaveProtocol.textProperty().bindBidirectional(Day.getInstance().sickLeaveProtocolContentProperty());
        sickLeaveProtocol.disableProperty().bind(Day.getInstance().sickLeaveProtocolDisableProperty());

        permitReason.textProperty().bindBidirectional(Day.getInstance().permitReasonContentProperty());
        permitReason.disableProperty().bind(Day.getInstance().permitReasonDisableProperty());
    }

    private void textAreaPropertyBinding() {
        notesTextArea.textProperty().bindBidirectional(Day.getInstance().notesTextAreaContentProperty());
        notesTextArea.disableProperty().bind(Day.getInstance().notesTextAreaDisableProperty());

        remindersTextArea.textProperty().bindBidirectional(Day.getInstance().remindersTextAreaContentProperty());
        remindersTextArea.disableProperty().bind(Day.getInstance().remindersTextAreaDisableProperty());
    }

    @FXML
    protected void onCalendarButtonClick() {
        DayController dayController = new DayController();
        CalendarController calendarController = new CalendarController();

        dayController.cancel();
        calendarController.updateColor();
        calendarController.updateUnderline();

        stage.setScene(calendarScene);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCalendarScene(Scene calendarScene) {
        this.calendarScene = calendarScene;
    }

    public void loadDay(String day) {
        DayController dayController = new DayController();

        dayController.clearForm();

        Day.getInstance().setDayValue(day);
        Day.getInstance().setMonth(String.valueOf(Calendar.getInstance().getCurrentDate().getMonth()));
        Day.getInstance().setMonthValue(Calendar.getInstance().getCurrentDate().getMonthValue());
        Day.getInstance().setYear(String.valueOf(Calendar.getInstance().getCurrentDate().getYear()));

        Day.getInstance().updateSelectedDate();

        LoadDataController loadDataController = new LoadDataController();

        loadDataController.loadData();
    }

    @FXML
    protected void onEditButtonClick() {
        DayController dayController = new DayController();

        dayController.edit();
    }

    @FXML
    protected void onCancelButtonClick() {
        DayController dayController = new DayController();

        dayController.cancel();
    }

    @FXML
    protected void onDeleteButtonClick() {
        DayController dayController = new DayController();

        dayController.delete();
    }

    @FXML
    protected void onSaveButtonClick() {
        DayController dayController = new DayController();

        dayController.save();
    }

    @FXML
    protected void onWorkingDayButtonClick() {
        DayController dayController = new DayController();

        dayController.disableWorkingDayForm(!Day.getInstance().isWorkingDayButtonSelect());
    }

    @FXML
    protected void onRestAndHolidayButtonClick() {
        DayController dayController = new DayController();

        dayController.disableWorkingDayForm(true);
    }

    @FXML
    protected void onSickLeaveButtonClick() {
        DayController dayController = new DayController();

        dayController.disableSickLeaveForm(!Day.getInstance().isSickLeaveButtonSelect());
    }

    @FXML
    protected void onLaunchBreakCheck() {
        DayController dayController = new DayController();

        dayController.disableLaunchBreakForm(!Day.getInstance().isLaunchBreakSelect());
    }

    @FXML
    protected void onOvertimeCheck() {
        DayController dayController = new DayController();

        dayController.disableOvertimeForm(!Day.getInstance().isOvertimeSelect());
    }

    @FXML
    protected void onPermitCheck() {
        DayController dayController = new DayController();

        dayController.disablePermitForm(!Day.getInstance().isPermitSelect());
    }
}
