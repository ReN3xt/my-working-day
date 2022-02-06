package it.orion.myworkingday.model;

import javafx.beans.property.*;
import javafx.scene.control.SingleSelectionModel;

public class Day {

    private String day;
    private String month;
    private String year;

    //TODO Migliorare struttura dati day/month/year

    //Text Property
    private final StringProperty selectedDateContent;
    private final StringProperty sickLeaveProtocolContent;
    private final StringProperty permitReasonContent;
    private final StringProperty notesTextAreaContent;
    private final StringProperty remindersTextAreaContent;

    //Disable Property
    private final BooleanProperty workingHoursDisable;
    private final BooleanProperty workingHoursStartColonDisable;
    private final BooleanProperty workingHoursEndColonDisable;
    private final BooleanProperty workingHoursHyphenDisable;
    private final BooleanProperty launchBreakStartColonDisable;
    private final BooleanProperty launchBreakEndColonDisable;
    private final BooleanProperty launchBreakHyphenDisable;
    private final BooleanProperty overtimeColonDisable;
    private final BooleanProperty sickLeaveDisable;
    private final BooleanProperty sickLeaveColonDisable;
    private final BooleanProperty permitColonDisable;
    private final BooleanProperty permitHyphenDisable;
    private final BooleanProperty notesDisable;
    private final BooleanProperty remindersDisable;
    private final BooleanProperty workingDayButtonDisable;
    private final BooleanProperty restButtonDisable;
    private final BooleanProperty sickLeaveButtonDisable;
    private final BooleanProperty holidayButtonDisable;
    private final BooleanProperty workingHoursStartHDisable;
    private final BooleanProperty workingHoursStartMDisable;
    private final BooleanProperty workingHoursEndHDisable;
    private final BooleanProperty workingHoursEndMDisable;
    private final BooleanProperty launchBreakStartHDisable;
    private final BooleanProperty launchBreakStartMDisable;
    private final BooleanProperty launchBreakEndHDisable;
    private final BooleanProperty launchBreakEndMDisable;
    private final BooleanProperty overtimeHDisable;
    private final BooleanProperty overtimeMDisable;
    private final BooleanProperty permitHDisable;
    private final BooleanProperty permitMDisable;
    private final BooleanProperty editButtonDisable;
    private final BooleanProperty saveButtonDisable;
    private final BooleanProperty launchBreakDisable;
    private final BooleanProperty overtimeDisable;
    private final BooleanProperty permitDisable;
    private final BooleanProperty sickLeaveProtocolDisable;
    private final BooleanProperty permitReasonDisable;
    private final BooleanProperty notesTextAreaDisable;
    private final BooleanProperty remindersTextAreaDisable;

    //Selected Property
    private final BooleanProperty workingDayButtonSelect;
    private final BooleanProperty restButtonSelect;
    private final BooleanProperty sickLeaveButtonSelect;
    private final BooleanProperty holidayButtonSelect;
    private final BooleanProperty launchBreakSelect;
    private final BooleanProperty overtimeSelect;
    private final BooleanProperty permitSelect;

    //Selection Model Property
    private final ObjectProperty<SingleSelectionModel<String>> workingHoursStartHSelectionModel;
    private final ObjectProperty<SingleSelectionModel<String>> workingHoursStartMSelectionModel;
    private final ObjectProperty<SingleSelectionModel<String>> workingHoursEndHSelectionModel;
    private final ObjectProperty<SingleSelectionModel<String>> workingHoursEndMSelectionModel;
    private final ObjectProperty<SingleSelectionModel<String>> launchBreakStartHSelectionModel;
    private final ObjectProperty<SingleSelectionModel<String>> launchBreakStartMSelectionModel;
    private final ObjectProperty<SingleSelectionModel<String>> launchBreakEndHSelectionModel;
    private final ObjectProperty<SingleSelectionModel<String>> launchBreakEndMSelectionModel;
    private final ObjectProperty<SingleSelectionModel<String>> overtimeHSelectionModel;
    private final ObjectProperty<SingleSelectionModel<String>> overtimeMSelectionModel;
    private final ObjectProperty<SingleSelectionModel<String>> permitHSelectionModel;
    private final ObjectProperty<SingleSelectionModel<String>> permitMSelectionModel;



    public Day() {
        this.selectedDateContent = new SimpleStringProperty();
        this.sickLeaveProtocolContent = new SimpleStringProperty();
        this.permitReasonContent = new SimpleStringProperty();
        this.notesTextAreaContent = new SimpleStringProperty();
        this.remindersTextAreaContent = new SimpleStringProperty();

        this.workingHoursDisable = new SimpleBooleanProperty(true);
        this.workingHoursStartColonDisable = new SimpleBooleanProperty(true);
        this.workingHoursEndColonDisable = new SimpleBooleanProperty(true);
        this.workingHoursHyphenDisable = new SimpleBooleanProperty(true);
        this.launchBreakStartColonDisable = new SimpleBooleanProperty(true);
        this.launchBreakEndColonDisable = new SimpleBooleanProperty(true);
        this.launchBreakHyphenDisable = new SimpleBooleanProperty(true);
        this.overtimeColonDisable = new SimpleBooleanProperty(true);
        this.sickLeaveDisable = new SimpleBooleanProperty(true);
        this.sickLeaveColonDisable = new SimpleBooleanProperty(true);
        this.permitColonDisable = new SimpleBooleanProperty(true);
        this.permitHyphenDisable = new SimpleBooleanProperty(true);
        this.notesDisable = new SimpleBooleanProperty(true);
        this.remindersDisable = new SimpleBooleanProperty(true);
        this.workingDayButtonDisable = new SimpleBooleanProperty(true);
        this.restButtonDisable = new SimpleBooleanProperty(true);
        this.sickLeaveButtonDisable = new SimpleBooleanProperty(true);
        this.holidayButtonDisable = new SimpleBooleanProperty(true);
        this.workingHoursStartHDisable = new SimpleBooleanProperty(true);
        this.workingHoursStartMDisable = new SimpleBooleanProperty(true);
        this.workingHoursEndHDisable = new SimpleBooleanProperty(true);
        this.workingHoursEndMDisable = new SimpleBooleanProperty(true);
        this.launchBreakStartHDisable = new SimpleBooleanProperty(true);
        this.launchBreakStartMDisable = new SimpleBooleanProperty(true);
        this.launchBreakEndHDisable = new SimpleBooleanProperty(true);
        this.launchBreakEndMDisable = new SimpleBooleanProperty(true);
        this.overtimeHDisable = new SimpleBooleanProperty(true);
        this.overtimeMDisable = new SimpleBooleanProperty(true);
        this.permitHDisable = new SimpleBooleanProperty(true);
        this.permitMDisable = new SimpleBooleanProperty(true);
        this.editButtonDisable = new SimpleBooleanProperty(false);
        this.saveButtonDisable = new SimpleBooleanProperty(true);
        this.launchBreakDisable = new SimpleBooleanProperty(true);
        this.overtimeDisable = new SimpleBooleanProperty(true);
        this.permitDisable = new SimpleBooleanProperty(true);
        this.sickLeaveProtocolDisable = new SimpleBooleanProperty(true);
        this.permitReasonDisable = new SimpleBooleanProperty(true);
        this.notesTextAreaDisable = new SimpleBooleanProperty(true);
        this.remindersTextAreaDisable = new SimpleBooleanProperty(true);

        this.workingDayButtonSelect = new SimpleBooleanProperty(false);
        this.restButtonSelect = new SimpleBooleanProperty(false);
        this.sickLeaveButtonSelect = new SimpleBooleanProperty(false);
        this.holidayButtonSelect = new SimpleBooleanProperty(false);
        this.launchBreakSelect = new SimpleBooleanProperty(false);
        this.overtimeSelect = new SimpleBooleanProperty(false);
        this.permitSelect = new SimpleBooleanProperty(false);

        this.workingHoursStartHSelectionModel = new SimpleObjectProperty<>();
        this.workingHoursStartMSelectionModel = new SimpleObjectProperty<>();
        this.workingHoursEndHSelectionModel = new SimpleObjectProperty<>();
        this.workingHoursEndMSelectionModel = new SimpleObjectProperty<>();
        this.launchBreakStartHSelectionModel = new SimpleObjectProperty<>();
        this.launchBreakStartMSelectionModel = new SimpleObjectProperty<>();
        this.launchBreakEndHSelectionModel = new SimpleObjectProperty<>();
        this.launchBreakEndMSelectionModel = new SimpleObjectProperty<>();
        this.overtimeHSelectionModel = new SimpleObjectProperty<>();
        this.overtimeMSelectionModel = new SimpleObjectProperty<>();
        this.permitHSelectionModel = new SimpleObjectProperty<>();
        this.permitMSelectionModel = new SimpleObjectProperty<>();
    }

    public String getSelectedDateContent() {
        return selectedDateContent.get();
    }

    public StringProperty selectedDateContentProperty() {
        return selectedDateContent;
    }

    public String getSickLeaveProtocolContent() {
        return sickLeaveProtocolContent.get();
    }

    public StringProperty sickLeaveProtocolContentProperty() {
        return sickLeaveProtocolContent;
    }

    public String getPermitReasonContent() {
        return permitReasonContent.get();
    }

    public StringProperty permitReasonContentProperty() {
        return permitReasonContent;
    }

    public String getNotesTextAreaContent() {
        return notesTextAreaContent.get();
    }

    public StringProperty notesTextAreaContentProperty() {
        return notesTextAreaContent;
    }

    public String getRemindersTextAreaContent() {
        return remindersTextAreaContent.get();
    }

    public StringProperty remindersTextAreaContentProperty() {
        return remindersTextAreaContent;
    }

    public boolean isWorkingHoursDisable() {
        return workingHoursDisable.get();
    }

    public BooleanProperty workingHoursDisableProperty() {
        return workingHoursDisable;
    }

    public boolean isWorkingHoursStartColonDisable() {
        return workingHoursStartColonDisable.get();
    }

    public BooleanProperty workingHoursStartColonDisableProperty() {
        return workingHoursStartColonDisable;
    }

    public boolean isWorkingHoursEndColonDisable() {
        return workingHoursEndColonDisable.get();
    }

    public BooleanProperty workingHoursEndColonDisableProperty() {
        return workingHoursEndColonDisable;
    }

    public boolean isWorkingHoursHyphenDisable() {
        return workingHoursHyphenDisable.get();
    }

    public BooleanProperty workingHoursHyphenDisableProperty() {
        return workingHoursHyphenDisable;
    }

    public boolean isLaunchBreakStartColonDisable() {
        return launchBreakStartColonDisable.get();
    }

    public BooleanProperty launchBreakStartColonDisableProperty() {
        return launchBreakStartColonDisable;
    }

    public boolean isLaunchBreakEndColonDisable() {
        return launchBreakEndColonDisable.get();
    }

    public BooleanProperty launchBreakEndColonDisableProperty() {
        return launchBreakEndColonDisable;
    }

    public boolean isLaunchBreakHyphenDisable() {
        return launchBreakHyphenDisable.get();
    }

    public BooleanProperty launchBreakHyphenDisableProperty() {
        return launchBreakHyphenDisable;
    }

    public boolean isOvertimeColonDisable() {
        return overtimeColonDisable.get();
    }

    public BooleanProperty overtimeColonDisableProperty() {
        return overtimeColonDisable;
    }

    public boolean isSickLeaveDisable() {
        return sickLeaveDisable.get();
    }

    public BooleanProperty sickLeaveDisableProperty() {
        return sickLeaveDisable;
    }

    public boolean isSickLeaveColonDisable() {
        return sickLeaveColonDisable.get();
    }

    public BooleanProperty sickLeaveColonDisableProperty() {
        return sickLeaveColonDisable;
    }

    public boolean isPermitColonDisable() {
        return permitColonDisable.get();
    }

    public BooleanProperty permitColonDisableProperty() {
        return permitColonDisable;
    }

    public boolean isPermitHyphenDisable() {
        return permitHyphenDisable.get();
    }

    public BooleanProperty permitHyphenDisableProperty() {
        return permitHyphenDisable;
    }

    public boolean isNotesDisable() {
        return notesDisable.get();
    }

    public BooleanProperty notesDisableProperty() {
        return notesDisable;
    }

    public boolean isRemindersDisable() {
        return remindersDisable.get();
    }

    public BooleanProperty remindersDisableProperty() {
        return remindersDisable;
    }

    public boolean isWorkingDayButtonDisable() {
        return workingDayButtonDisable.get();
    }

    public BooleanProperty workingDayButtonDisableProperty() {
        return workingDayButtonDisable;
    }

    public boolean isRestButtonDisable() {
        return restButtonDisable.get();
    }

    public BooleanProperty restButtonDisableProperty() {
        return restButtonDisable;
    }

    public boolean isSickLeaveButtonDisable() {
        return sickLeaveButtonDisable.get();
    }

    public BooleanProperty sickLeaveButtonDisableProperty() {
        return sickLeaveButtonDisable;
    }

    public boolean isHolidayButtonDisable() {
        return holidayButtonDisable.get();
    }

    public BooleanProperty holidayButtonDisableProperty() {
        return holidayButtonDisable;
    }

    public boolean isWorkingHoursStartHDisable() {
        return workingHoursStartHDisable.get();
    }

    public BooleanProperty workingHoursStartHDisableProperty() {
        return workingHoursStartHDisable;
    }

    public boolean isWorkingHoursStartMDisable() {
        return workingHoursStartMDisable.get();
    }

    public BooleanProperty workingHoursStartMDisableProperty() {
        return workingHoursStartMDisable;
    }

    public boolean isWorkingHoursEndHDisable() {
        return workingHoursEndHDisable.get();
    }

    public BooleanProperty workingHoursEndHDisableProperty() {
        return workingHoursEndHDisable;
    }

    public boolean isWorkingHoursEndMDisable() {
        return workingHoursEndMDisable.get();
    }

    public BooleanProperty workingHoursEndMDisableProperty() {
        return workingHoursEndMDisable;
    }

    public boolean isLaunchBreakStartHDisable() {
        return launchBreakStartHDisable.get();
    }

    public BooleanProperty launchBreakStartHDisableProperty() {
        return launchBreakStartHDisable;
    }

    public boolean isLaunchBreakStartMDisable() {
        return launchBreakStartMDisable.get();
    }

    public BooleanProperty launchBreakStartMDisableProperty() {
        return launchBreakStartMDisable;
    }

    public boolean isLaunchBreakEndHDisable() {
        return launchBreakEndHDisable.get();
    }

    public BooleanProperty launchBreakEndHDisableProperty() {
        return launchBreakEndHDisable;
    }

    public boolean isLaunchBreakEndMDisable() {
        return launchBreakEndMDisable.get();
    }

    public BooleanProperty launchBreakEndMDisableProperty() {
        return launchBreakEndMDisable;
    }

    public boolean isOvertimeHDisable() {
        return overtimeHDisable.get();
    }

    public BooleanProperty overtimeHDisableProperty() {
        return overtimeHDisable;
    }

    public boolean isOvertimeMDisable() {
        return overtimeMDisable.get();
    }

    public BooleanProperty overtimeMDisableProperty() {
        return overtimeMDisable;
    }

    public boolean isPermitHDisable() {
        return permitHDisable.get();
    }

    public BooleanProperty permitHDisableProperty() {
        return permitHDisable;
    }

    public boolean isPermitMDisable() {
        return permitMDisable.get();
    }

    public BooleanProperty permitMDisableProperty() {
        return permitMDisable;
    }

    public boolean isEditButtonDisable() {
        return editButtonDisable.get();
    }

    public BooleanProperty editButtonDisableProperty() {
        return editButtonDisable;
    }

    public boolean isSaveButtonDisable() {
        return saveButtonDisable.get();
    }

    public BooleanProperty saveButtonDisableProperty() {
        return saveButtonDisable;
    }

    public boolean isLaunchBreakDisable() {
        return launchBreakDisable.get();
    }

    public BooleanProperty launchBreakDisableProperty() {
        return launchBreakDisable;
    }

    public boolean isOvertimeDisable() {
        return overtimeDisable.get();
    }

    public BooleanProperty overtimeDisableProperty() {
        return overtimeDisable;
    }

    public boolean isPermitDisable() {
        return permitDisable.get();
    }

    public BooleanProperty permitDisableProperty() {
        return permitDisable;
    }

    public boolean isSickLeaveProtocolDisable() {
        return sickLeaveProtocolDisable.get();
    }

    public BooleanProperty sickLeaveProtocolDisableProperty() {
        return sickLeaveProtocolDisable;
    }

    public boolean isPermitReasonDisable() {
        return permitReasonDisable.get();
    }

    public BooleanProperty permitReasonDisableProperty() {
        return permitReasonDisable;
    }

    public boolean isNotesTextAreaDisable() {
        return notesTextAreaDisable.get();
    }

    public BooleanProperty notesTextAreaDisableProperty() {
        return notesTextAreaDisable;
    }

    public boolean isRemindersTextAreaDisable() {
        return remindersTextAreaDisable.get();
    }

    public BooleanProperty remindersTextAreaDisableProperty() {
        return remindersTextAreaDisable;
    }

    public boolean isWorkingDayButtonSelect() {
        return workingDayButtonSelect.get();
    }

    public BooleanProperty workingDayButtonSelectProperty() {
        return workingDayButtonSelect;
    }

    public boolean isRestButtonSelect() {
        return restButtonSelect.get();
    }

    public BooleanProperty restButtonSelectProperty() {
        return restButtonSelect;
    }

    public boolean isSickLeaveButtonSelect() {
        return sickLeaveButtonSelect.get();
    }

    public BooleanProperty sickLeaveButtonSelectProperty() {
        return sickLeaveButtonSelect;
    }

    public boolean isHolidayButtonSelect() {
        return holidayButtonSelect.get();
    }

    public BooleanProperty holidayButtonSelectProperty() {
        return holidayButtonSelect;
    }

    public boolean isLaunchBreakSelect() {
        return launchBreakSelect.get();
    }

    public BooleanProperty launchBreakSelectProperty() {
        return launchBreakSelect;
    }

    public boolean isOvertimeSelect() {
        return overtimeSelect.get();
    }

    public BooleanProperty overtimeSelectProperty() {
        return overtimeSelect;
    }

    public boolean isPermitSelect() {
        return permitSelect.get();
    }

    public BooleanProperty permitSelectProperty() {
        return permitSelect;
    }

    public SingleSelectionModel<String> getWorkingHoursStartHSelectionModel() {
        return workingHoursStartHSelectionModel.get();
    }

    public ObjectProperty<SingleSelectionModel<String>> workingHoursStartHSelectionModelProperty() {
        return workingHoursStartHSelectionModel;
    }

    public SingleSelectionModel<String> getWorkingHoursStartMSelectionModel() {
        return workingHoursStartMSelectionModel.get();
    }

    public ObjectProperty<SingleSelectionModel<String>> workingHoursStartMSelectionModelProperty() {
        return workingHoursStartMSelectionModel;
    }

    public SingleSelectionModel<String> getWorkingHoursEndHSelectionModel() {
        return workingHoursEndHSelectionModel.get();
    }

    public ObjectProperty<SingleSelectionModel<String>> workingHoursEndHSelectionModelProperty() {
        return workingHoursEndHSelectionModel;
    }

    public SingleSelectionModel<String> getWorkingHoursEndMSelectionModel() {
        return workingHoursEndMSelectionModel.get();
    }

    public ObjectProperty<SingleSelectionModel<String>> workingHoursEndMSelectionModelProperty() {
        return workingHoursEndMSelectionModel;
    }

    public SingleSelectionModel<String> getLaunchBreakStartHSelectionModel() {
        return launchBreakStartHSelectionModel.get();
    }

    public ObjectProperty<SingleSelectionModel<String>> launchBreakStartHSelectionModelProperty() {
        return launchBreakStartHSelectionModel;
    }

    public SingleSelectionModel<String> getLaunchBreakStartMSelectionModel() {
        return launchBreakStartMSelectionModel.get();
    }

    public ObjectProperty<SingleSelectionModel<String>> launchBreakStartMSelectionModelProperty() {
        return launchBreakStartMSelectionModel;
    }

    public SingleSelectionModel<String> getLaunchBreakEndHSelectionModel() {
        return launchBreakEndHSelectionModel.get();
    }

    public ObjectProperty<SingleSelectionModel<String>> launchBreakEndHSelectionModelProperty() {
        return launchBreakEndHSelectionModel;
    }

    public SingleSelectionModel<String> getLaunchBreakEndMSelectionModel() {
        return launchBreakEndMSelectionModel.get();
    }

    public ObjectProperty<SingleSelectionModel<String>> launchBreakEndMSelectionModelProperty() {
        return launchBreakEndMSelectionModel;
    }

    public SingleSelectionModel<String> getOvertimeHSelectionModel() {
        return overtimeHSelectionModel.get();
    }

    public ObjectProperty<SingleSelectionModel<String>> overtimeHSelectionModelProperty() {
        return overtimeHSelectionModel;
    }

    public SingleSelectionModel<String> getOvertimeMSelectionModel() {
        return overtimeMSelectionModel.get();
    }

    public ObjectProperty<SingleSelectionModel<String>> overtimeMSelectionModelProperty() {
        return overtimeMSelectionModel;
    }

    public SingleSelectionModel<String> getPermitHSelectionModel() {
        return permitHSelectionModel.get();
    }

    public ObjectProperty<SingleSelectionModel<String>> permitHSelectionModelProperty() {
        return permitHSelectionModel;
    }

    public SingleSelectionModel<String> getPermitMSelectionModel() {
        return permitMSelectionModel.get();
    }

    public ObjectProperty<SingleSelectionModel<String>> permitMSelectionModelProperty() {
        return permitMSelectionModel;
    }

    /*-------------------------------------------------------------------------\\
    ||----------------------END GETTER AND BINDING PROPERTY--------------------||
    \\-------------------------------------------------------------------------*/

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
        this.selectedDateContent.set(day + " " + month + " " + year);
    }

    /*public void reset() {
        System.out.println(this.prova.get().getSelectedItem());
        this.prova.get().clearSelection();
    }*/
}