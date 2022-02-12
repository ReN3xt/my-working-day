package it.orion.myworkingday.model;

import javafx.beans.property.*;
import javafx.scene.control.SingleSelectionModel;

public class Day {

    private String dayValue;
    private String month;
    private String year;
    private String selectedDate;

    private int monthValue;

    private boolean load;

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
    private final BooleanProperty deleteButtonDisable;
    private final BooleanProperty editButtonDisable;
    private final BooleanProperty cancelButtonDisable;
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
        this.deleteButtonDisable = new SimpleBooleanProperty(true);
        this.editButtonDisable = new SimpleBooleanProperty(false);
        this.cancelButtonDisable = new SimpleBooleanProperty(true);
        this.saveButtonDisable = new SimpleBooleanProperty(true);
        this.launchBreakDisable = new SimpleBooleanProperty(true);
        this.overtimeDisable = new SimpleBooleanProperty(true);
        this.permitDisable = new SimpleBooleanProperty(true);
        this.sickLeaveProtocolDisable = new SimpleBooleanProperty(true);
        this.permitReasonDisable = new SimpleBooleanProperty(true);
        this.notesTextAreaDisable = new SimpleBooleanProperty(true);
        this.remindersTextAreaDisable = new SimpleBooleanProperty(true);

        this.workingDayButtonSelect = new SimpleBooleanProperty();
        this.restButtonSelect = new SimpleBooleanProperty();
        this.sickLeaveButtonSelect = new SimpleBooleanProperty();
        this.holidayButtonSelect = new SimpleBooleanProperty();
        this.launchBreakSelect = new SimpleBooleanProperty();
        this.overtimeSelect = new SimpleBooleanProperty();
        this.permitSelect = new SimpleBooleanProperty();

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

    public BooleanProperty workingHoursDisableProperty() {
        return workingHoursDisable;
    }

    public BooleanProperty workingHoursStartColonDisableProperty() {
        return workingHoursStartColonDisable;
    }

    public BooleanProperty workingHoursEndColonDisableProperty() {
        return workingHoursEndColonDisable;
    }

    public BooleanProperty workingHoursHyphenDisableProperty() {
        return workingHoursHyphenDisable;
    }

    public BooleanProperty launchBreakStartColonDisableProperty() {
        return launchBreakStartColonDisable;
    }

    public BooleanProperty launchBreakEndColonDisableProperty() {
        return launchBreakEndColonDisable;
    }

    public BooleanProperty launchBreakHyphenDisableProperty() {
        return launchBreakHyphenDisable;
    }

    public BooleanProperty overtimeColonDisableProperty() {
        return overtimeColonDisable;
    }

    public BooleanProperty sickLeaveDisableProperty() {
        return sickLeaveDisable;
    }

    public BooleanProperty sickLeaveColonDisableProperty() {
        return sickLeaveColonDisable;
    }

    public BooleanProperty permitColonDisableProperty() {
        return permitColonDisable;
    }

    public BooleanProperty permitHyphenDisableProperty() {
        return permitHyphenDisable;
    }

    public BooleanProperty notesDisableProperty() {
        return notesDisable;
    }

    public BooleanProperty remindersDisableProperty() {
        return remindersDisable;
    }

    public BooleanProperty workingDayButtonDisableProperty() {
        return workingDayButtonDisable;
    }

    public BooleanProperty restButtonDisableProperty() {
        return restButtonDisable;
    }

    public BooleanProperty sickLeaveButtonDisableProperty() {
        return sickLeaveButtonDisable;
    }

    public BooleanProperty holidayButtonDisableProperty() {
        return holidayButtonDisable;
    }

    public BooleanProperty workingHoursStartHDisableProperty() {
        return workingHoursStartHDisable;
    }

    public BooleanProperty workingHoursStartMDisableProperty() {
        return workingHoursStartMDisable;
    }

    public BooleanProperty workingHoursEndHDisableProperty() {
        return workingHoursEndHDisable;
    }

    public BooleanProperty workingHoursEndMDisableProperty() {
        return workingHoursEndMDisable;
    }

    public BooleanProperty launchBreakStartHDisableProperty() {
        return launchBreakStartHDisable;
    }

    public BooleanProperty launchBreakStartMDisableProperty() {
        return launchBreakStartMDisable;
    }

    public BooleanProperty launchBreakEndHDisableProperty() {
        return launchBreakEndHDisable;
    }

    public BooleanProperty launchBreakEndMDisableProperty() {
        return launchBreakEndMDisable;
    }

    public BooleanProperty overtimeHDisableProperty() {
        return overtimeHDisable;
    }

    public BooleanProperty overtimeMDisableProperty() {
        return overtimeMDisable;
    }

    public BooleanProperty permitHDisableProperty() {
        return permitHDisable;
    }

    public BooleanProperty permitMDisableProperty() {
        return permitMDisable;
    }

    public BooleanProperty deleteButtonDisableProperty() {
        return deleteButtonDisable;
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

    public BooleanProperty launchBreakDisableProperty() {
        return launchBreakDisable;
    }

    public BooleanProperty overtimeDisableProperty() {
        return overtimeDisable;
    }

    public BooleanProperty permitDisableProperty() {
        return permitDisable;
    }

    public BooleanProperty sickLeaveProtocolDisableProperty() {
        return sickLeaveProtocolDisable;
    }

    public BooleanProperty permitReasonDisableProperty() {
        return permitReasonDisable;
    }

    public BooleanProperty notesTextAreaDisableProperty() {
        return notesTextAreaDisable;
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


    public void setWorkingHoursDisable(boolean workingHoursDisable) {
        this.workingHoursDisable.set(workingHoursDisable);
    }

    public void setWorkingHoursStartColonDisable(boolean workingHoursStartColonDisable) {
        this.workingHoursStartColonDisable.set(workingHoursStartColonDisable);
    }

    public void setWorkingHoursEndColonDisable(boolean workingHoursEndColonDisable) {
        this.workingHoursEndColonDisable.set(workingHoursEndColonDisable);
    }

    public void setWorkingHoursHyphenDisable(boolean workingHoursHyphenDisable) {
        this.workingHoursHyphenDisable.set(workingHoursHyphenDisable);
    }

    public void setLaunchBreakStartColonDisable(boolean launchBreakStartColonDisable) {
        this.launchBreakStartColonDisable.set(launchBreakStartColonDisable);
    }

    public void setLaunchBreakEndColonDisable(boolean launchBreakEndColonDisable) {
        this.launchBreakEndColonDisable.set(launchBreakEndColonDisable);
    }

    public void setLaunchBreakHyphenDisable(boolean launchBreakHyphenDisable) {
        this.launchBreakHyphenDisable.set(launchBreakHyphenDisable);
    }

    public void setOvertimeColonDisable(boolean overtimeColonDisable) {
        this.overtimeColonDisable.set(overtimeColonDisable);
    }

    public void setSickLeaveDisable(boolean sickLeaveDisable) {
        this.sickLeaveDisable.set(sickLeaveDisable);
    }

    public void setSickLeaveColonDisable(boolean sickLeaveColonDisable) {
        this.sickLeaveColonDisable.set(sickLeaveColonDisable);
    }

    public void setPermitColonDisable(boolean permitColonDisable) {
        this.permitColonDisable.set(permitColonDisable);
    }

    public void setPermitHyphenDisable(boolean permitHyphenDisable) {
        this.permitHyphenDisable.set(permitHyphenDisable);
    }

    public void setNotesDisable(boolean notesDisable) {
        this.notesDisable.set(notesDisable);
    }

    public void setRemindersDisable(boolean remindersDisable) {
        this.remindersDisable.set(remindersDisable);
    }

    public void setWorkingDayButtonDisable(boolean workingDayButtonDisable) {
        this.workingDayButtonDisable.set(workingDayButtonDisable);
    }

    public void setRestButtonDisable(boolean restButtonDisable) {
        this.restButtonDisable.set(restButtonDisable);
    }

    public void setSickLeaveButtonDisable(boolean sickLeaveButtonDisable) {
        this.sickLeaveButtonDisable.set(sickLeaveButtonDisable);
    }

    public void setHolidayButtonDisable(boolean holidayButtonDisable) {
        this.holidayButtonDisable.set(holidayButtonDisable);
    }

    public void setWorkingHoursStartHDisable(boolean workingHoursStartHDisable) {
        this.workingHoursStartHDisable.set(workingHoursStartHDisable);
    }

    public void setWorkingHoursStartMDisable(boolean workingHoursStartMDisable) {
        this.workingHoursStartMDisable.set(workingHoursStartMDisable);
    }

    public void setWorkingHoursEndHDisable(boolean workingHoursEndHDisable) {
        this.workingHoursEndHDisable.set(workingHoursEndHDisable);
    }

    public void setWorkingHoursEndMDisable(boolean workingHoursEndMDisable) {
        this.workingHoursEndMDisable.set(workingHoursEndMDisable);
    }

    public void setLaunchBreakStartHDisable(boolean launchBreakStartHDisable) {
        this.launchBreakStartHDisable.set(launchBreakStartHDisable);
    }

    public void setLaunchBreakStartMDisable(boolean launchBreakStartMDisable) {
        this.launchBreakStartMDisable.set(launchBreakStartMDisable);
    }

    public void setLaunchBreakEndHDisable(boolean launchBreakEndHDisable) {
        this.launchBreakEndHDisable.set(launchBreakEndHDisable);
    }

    public void setLaunchBreakEndMDisable(boolean launchBreakEndMDisable) {
        this.launchBreakEndMDisable.set(launchBreakEndMDisable);
    }

    public void setOvertimeHDisable(boolean overtimeHDisable) {
        this.overtimeHDisable.set(overtimeHDisable);
    }

    public void setOvertimeMDisable(boolean overtimeMDisable) {
        this.overtimeMDisable.set(overtimeMDisable);
    }

    public void setPermitHDisable(boolean permitHDisable) {
        this.permitHDisable.set(permitHDisable);
    }

    public void setPermitMDisable(boolean permitMDisable) {
        this.permitMDisable.set(permitMDisable);
    }

    public void setDeleteButtonDisable(boolean deleteButtonDisable) {
        this.deleteButtonDisable.set(deleteButtonDisable);
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

    public void setLaunchBreakDisable(boolean launchBreakDisable) {
        this.launchBreakDisable.set(launchBreakDisable);
    }

    public void setOvertimeDisable(boolean overtimeDisable) {
        this.overtimeDisable.set(overtimeDisable);
    }

    public void setPermitDisable(boolean permitDisable) {
        this.permitDisable.set(permitDisable);
    }

    public void setSickLeaveProtocolDisable(boolean sickLeaveProtocolDisable) {
        this.sickLeaveProtocolDisable.set(sickLeaveProtocolDisable);
    }

    public void setPermitReasonDisable(boolean permitReasonDisable) {
        this.permitReasonDisable.set(permitReasonDisable);
    }

    public void setNotesTextAreaDisable(boolean notesTextAreaDisable) {
        this.notesTextAreaDisable.set(notesTextAreaDisable);
    }

    public void setRemindersTextAreaDisable(boolean remindersTextAreaDisable) {
        this.remindersTextAreaDisable.set(remindersTextAreaDisable);
    }

    public void setWorkingDayButtonSelect(boolean workingDayButtonSelect) {
        this.workingDayButtonSelect.set(workingDayButtonSelect);
    }

    public void setRestButtonSelect(boolean restButtonSelect) {
        this.restButtonSelect.set(restButtonSelect);
    }

    public void setSickLeaveButtonSelect(boolean sickLeaveButtonSelect) {
        this.sickLeaveButtonSelect.set(sickLeaveButtonSelect);
    }

    public void setHolidayButtonSelect(boolean holidayButtonSelect) {
        this.holidayButtonSelect.set(holidayButtonSelect);
    }

    public void setLaunchBreakSelect(boolean launchBreakSelect) {
        this.launchBreakSelect.set(launchBreakSelect);
    }

    public void setOvertimeSelect(boolean overtimeSelect) {
        this.overtimeSelect.set(overtimeSelect);
    }

    public void setPermitSelect(boolean permitSelect) {
        this.permitSelect.set(permitSelect);
    }

    public void setPermitReasonContent(String permitReasonContent) {
        this.permitReasonContent.set(permitReasonContent);
    }

    public void setSickLeaveProtocolContent(String sickLeaveProtocolContent) {
        this.sickLeaveProtocolContent.set(sickLeaveProtocolContent);
    }

    public void setNotesTextAreaContent(String notesTextAreaContent) {
        this.notesTextAreaContent.set(notesTextAreaContent);
    }

    public void setRemindersTextAreaContent(String remindersTextAreaContent) {
        this.remindersTextAreaContent.set(remindersTextAreaContent);
    }

    /*-------------------------------------------------------------------------\\
    ||--------------------------------END SETTER-------------------------------||
    \\-------------------------------------------------------------------------*/

    public boolean isLoad() {
        return this.load;
    }

    public void setLoad(boolean load) {
        this.load = load;
    }

    public void setDayValue(String dayValue) {
        this.dayValue = dayValue;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setMonthValue(int monthValue){
        this.monthValue = monthValue;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void updateSelectedDate() {
        this.selectedDateContent.set(this.dayValue + " " + this.month + " " + this.year);

        this.selectedDate = year;

        if(this.monthValue <= 9){
            this.selectedDate = this.selectedDate + "0";
        }

        this.selectedDate = this.selectedDate + this.monthValue;

        if(Integer.parseInt(this.dayValue) <= 9) {
            this.selectedDate = this.selectedDate + "0";
        }

        this.selectedDate = this.selectedDate + this.dayValue;
    }

    public String getSelectedDate() {
        return this.selectedDate;
    }

    public void clearWorkingHours() {
        this.workingHoursStartHSelectionModel.get().clearSelection();
        this.workingHoursStartMSelectionModel.get().clearSelection();
        this.workingHoursEndHSelectionModel.get().clearSelection();
        this.workingHoursEndMSelectionModel.get().clearSelection();
    }

    public void clearLaunchBreak() {
        this.launchBreakStartHSelectionModel.get().clearSelection();
        this.launchBreakStartMSelectionModel.get().clearSelection();
        this.launchBreakEndHSelectionModel.get().clearSelection();
        this.launchBreakEndMSelectionModel.get().clearSelection();
    }

    public void clearOvertime() {
        this.overtimeHSelectionModel.get().clearSelection();
        this.overtimeMSelectionModel.get().clearSelection();
    }

    public void clearPermit() {
        this.permitHSelectionModel.get().clearSelection();
        this.permitMSelectionModel.get().clearSelection();
        this.permitReasonContent.set(null);
    }

    public void clearSickLeave() {
        this.sickLeaveProtocolContent.set(null);
    }

    public void clearNotes() {
        this.notesTextAreaContent.set(null);
    }

    public void clearReminders() {
        this.remindersTextAreaContent.set(null);
    }
}