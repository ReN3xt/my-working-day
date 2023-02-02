package it.orion.myworkingday.controller.applicativo;

import it.orion.myworkingday.model.Day;

public class DayController {

    public void edit() {
        disableTextArea(false);
        disableDayTypeSelection(false);
        Day.getInstance().setDeleteButtonDisable(true);
        Day.getInstance().setEditButtonDisable(true);
        Day.getInstance().setCancelButtonDisable(false);
        Day.getInstance().setSaveButtonDisable(false);

        if(Day.getInstance().isWorkingDayButtonSelect()) {
            disableWorkingDayForm(false);
        } else if(Day.getInstance().isSickLeaveButtonSelect()) {
            disableSickLeaveForm(false);
        }
    }

    public void disableWorkingDayForm(boolean b) {
        Day.getInstance().setWorkingHoursStartColonDisable(b);
        Day.getInstance().setWorkingHoursEndColonDisable(b);
        Day.getInstance().setWorkingHoursHyphenDisable(b);
        Day.getInstance().setWorkingHoursStartHDisable(b);
        Day.getInstance().setWorkingHoursStartMDisable(b);
        Day.getInstance().setWorkingHoursEndHDisable(b);
        Day.getInstance().setWorkingHoursEndMDisable(b);
        Day.getInstance().setWorkingHoursDisable(b);
        Day.getInstance().setLaunchBreakDisable(b);
        Day.getInstance().setOvertimeDisable(b);
        Day.getInstance().setPermitDisable(b);

        if(b) {
            Day.getInstance().clearWorkingHours();
            Day.getInstance().setLaunchBreakSelect(false);
            Day.getInstance().setOvertimeSelect(false);
            Day.getInstance().setPermitSelect(false);
            disableLaunchBreakForm(true);
            disableOvertimeForm(true);
            disablePermitForm(true);

            if(Day.getInstance().isRestButtonSelect() || Day.getInstance().isHolidayButtonSelect()) {
                disableSickLeaveForm(true);
            }

        } else {
            disableSickLeaveForm(true);
        }

        if(Day.getInstance().isLaunchBreakSelect()) {
            disableLaunchBreakForm(b);
        }

        if(Day.getInstance().isOvertimeSelect()) {
            disableOvertimeForm(b);
        }

        if(Day.getInstance().isPermitSelect()) {
            disablePermitForm( b);
        }
    }

    public void disableLaunchBreakForm(boolean b) {
        if(b) {
            Day.getInstance().clearLaunchBreak();
        }
        Day.getInstance().setLaunchBreakStartColonDisable(b);
        Day.getInstance().setLaunchBreakEndColonDisable(b);
        Day.getInstance().setLaunchBreakHyphenDisable(b);
        Day.getInstance().setLaunchBreakStartHDisable(b);
        Day.getInstance().setLaunchBreakStartMDisable(b);
        Day.getInstance().setLaunchBreakEndHDisable(b);
        Day.getInstance().setLaunchBreakEndMDisable(b);
    }

    public void disableOvertimeForm(boolean b) {
        if(b) {
            Day.getInstance().clearOvertime();
        }
        Day.getInstance().setOvertimeColonDisable(b);
        Day.getInstance().setOvertimeHDisable(b);
        Day.getInstance().setOvertimeMDisable(b);
    }

    public void disablePermitForm(boolean b) {
        if(b){
            Day.getInstance().clearPermit();
        }

        Day.getInstance().setPermitColonDisable(b);
        Day.getInstance().setPermitHyphenDisable(b);
        Day.getInstance().setPermitHDisable(b);
        Day.getInstance().setPermitMDisable(b);
        Day.getInstance().setPermitReasonDisable(b);
    }

    public void disableSickLeaveForm(boolean b) {
        Day.getInstance().setSickLeaveDisable(b);
        Day.getInstance().setSickLeaveColonDisable(b);
        Day.getInstance().setSickLeaveProtocolDisable(b);

        if(!b) {
            disableWorkingDayForm(true);
            disableLaunchBreakForm(true);
            disableOvertimeForm(true);
            disablePermitForm(true);
        } else {
            Day.getInstance().clearSickLeave();
        }
    }

    public void disableTextArea(boolean b) {
        if(b) {
            Day.getInstance().clearNotes();
            Day.getInstance().clearReminders();
        }

        Day.getInstance().setNotesDisable(b);
        Day.getInstance().setNotesTextAreaDisable(b);
        Day.getInstance().setRemindersDisable(b);
        Day.getInstance().setRemindersTextAreaDisable(b);
    }

    public void disableDayTypeSelection(boolean b) {
        if(b) {
            Day.getInstance().setWorkingDayButtonSelect(false);
            Day.getInstance().setRestButtonSelect(false);
            Day.getInstance().setSickLeaveButtonSelect(false);
            Day.getInstance().setHolidayButtonSelect(false);
        }

        Day.getInstance().setWorkingDayButtonDisable(b);
        Day.getInstance().setRestButtonDisable(b);
        Day.getInstance().setSickLeaveButtonDisable(b);
        Day.getInstance().setHolidayButtonDisable(b);
    }

    public void cancel() {
        Day.getInstance().setEditButtonDisable(false);
        Day.getInstance().setCancelButtonDisable(true);
        Day.getInstance().setSaveButtonDisable(true);

        disableTextArea(true);
        disableDayTypeSelection(true);
        disableWorkingDayForm(true);
        disableSickLeaveForm(true);

        if(Day.getInstance().isLoad()){
            Day.getInstance().setDeleteButtonDisable(false);

            LoadDataController controller = new LoadDataController();

            controller.loadData();
        }
    }

    public void clearForm() {
        disableTextArea(true);
        disableDayTypeSelection(true);
        disableWorkingDayForm(true);
        disableSickLeaveForm(true);
    }

    public void delete() {
        clearForm();

        DeleteDataController controller = new DeleteDataController();

        controller.deleteData();

        Day.getInstance().setDeleteButtonDisable(true);
    }

    public void save() {
        SaveDataController controller = new SaveDataController();

        if(controller.saveData()) {
            Day.getInstance().setDeleteButtonDisable(true);
            Day.getInstance().setEditButtonDisable(false);
            Day.getInstance().setCancelButtonDisable(true);
            Day.getInstance().setSaveButtonDisable(true);

            disableTextArea(true);
            disableDayTypeSelection(true);
            disableWorkingDayForm(true);
            disableSickLeaveForm(true);

            LoadDataController loadController = new LoadDataController();

            loadController.loadData();
        }
    }
}