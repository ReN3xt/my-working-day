package it.orion.myworkingday.controller.applicativo;

import it.orion.myworkingday.model.Day;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class DayControllerA {

    public DayControllerA() {

    }

    public void edit(Day day) {
        disableTextArea(day, false);
        disableDayTypeSelection(day, false);
        day.setDeleteButtonDisable(true);
        day.setEditButtonDisable(true);
        day.setCancelButtonDisable(false);
        day.setSaveButtonDisable(false);

        if(day.isWorkingDayButtonSelect()) {
            disableWorkingDayForm(day, false);
        } else if(day.isSickLeaveButtonSelect()) {
            disableSickLeaveForm(day, false);
        }
    }

    public void disableWorkingDayForm(Day day, boolean b) {
        day.setWorkingHoursStartColonDisable(b);
        day.setWorkingHoursEndColonDisable(b);
        day.setWorkingHoursHyphenDisable(b);
        day.setWorkingHoursStartHDisable(b);
        day.setWorkingHoursStartMDisable(b);
        day.setWorkingHoursEndHDisable(b);
        day.setWorkingHoursEndMDisable(b);
        day.setWorkingHoursDisable(b);
        day.setLaunchBreakDisable(b);
        day.setOvertimeDisable(b);
        day.setPermitDisable(b);

        if(b) {
            day.clearWorkingHours();
            day.setLaunchBreakSelect(false);
            day.setOvertimeSelect(false);
            day.setPermitSelect(false);
            disableLaunchBreakForm(day, true);
            disableOvertimeForm(day, true);
            disablePermitForm(day, true);

            if(day.isRestButtonSelect() || day.isHolidayButtonSelect()) {
                disableSickLeaveForm(day, true);
            }

        } else {
            disableSickLeaveForm(day, true);
        }

        if(day.isLaunchBreakSelect()) {
            disableLaunchBreakForm(day, b);
        }

        if(day.isOvertimeSelect()) {
            disableOvertimeForm(day, b);
        }

        if(day.isPermitSelect()) {
            disablePermitForm(day, b);
        }
    }

    public void disableLaunchBreakForm(Day day, boolean b) {
        if(b) {
            day.clearLaunchBreak();
        }
        day.setLaunchBreakStartColonDisable(b);
        day.setLaunchBreakEndColonDisable(b);
        day.setLaunchBreakHyphenDisable(b);
        day.setLaunchBreakStartHDisable(b);
        day.setLaunchBreakStartMDisable(b);
        day.setLaunchBreakEndHDisable(b);
        day.setLaunchBreakEndMDisable(b);
    }

    public void disableOvertimeForm(Day day, boolean b) {
        if(b) {
            day.clearOvertime();
        }
        day.setOvertimeColonDisable(b);
        day.setOvertimeHDisable(b);
        day.setOvertimeMDisable(b);
    }

    public void disablePermitForm(Day day, boolean b) {
        if(b){
            day.clearPermit();
        }

        day.setPermitColonDisable(b);
        day.setPermitHyphenDisable(b);
        day.setPermitHDisable(b);
        day.setPermitMDisable(b);
        day.setPermitReasonDisable(b);
    }

    public void disableSickLeaveForm(Day day, boolean b) {
        day.setSickLeaveDisable(b);
        day.setSickLeaveColonDisable(b);
        day.setSickLeaveProtocolDisable(b);

        if(!b) {
            disableWorkingDayForm(day, true);
            disableLaunchBreakForm(day, true);
            disableOvertimeForm(day, true);
            disablePermitForm(day, true);
        } else {
            day.clearSickLeave();
        }
    }

    public void disableTextArea(Day day, boolean b) {
        if(b) {
            day.clearNotes();
            day.clearReminders();
        }

        day.setNotesDisable(b);
        day.setNotesTextAreaDisable(b);
        day.setRemindersDisable(b);
        day.setRemindersTextAreaDisable(b);
    }

    public void disableDayTypeSelection(Day day, boolean b) {
        if(b) {
            day.setWorkingDayButtonSelect(false);
            day.setRestButtonSelect(false);
            day.setSickLeaveButtonSelect(false);
            day.setHolidayButtonSelect(false);
        }

        day.setWorkingDayButtonDisable(b);
        day.setRestButtonDisable(b);
        day.setSickLeaveButtonDisable(b);
        day.setHolidayButtonDisable(b);
    }

    public void cancel(Day day) {
        day.setEditButtonDisable(false);
        day.setCancelButtonDisable(true);
        day.setSaveButtonDisable(true);

        disableTextArea(day, true);
        disableDayTypeSelection(day, true);
        disableWorkingDayForm(day, true);
        disableSickLeaveForm(day, true);

        if(day.isLoad()){
            day.setDeleteButtonDisable(false);

            LoadDataController controller = new LoadDataController();

            controller.loadData(day);
        }
    }

    public void delete(Day day) {

        disableTextArea(day, true);
        disableDayTypeSelection(day, true);
        disableWorkingDayForm(day, true);
        disableSickLeaveForm(day, true);

        DeleteDataController controller = new DeleteDataController();

        controller.deleteData(day);

        day.setDeleteButtonDisable(true);
    }

    public void save(Day day) {
        SaveDataController controller = new SaveDataController();

        if(controller.saveData(day)) {
            day.setDeleteButtonDisable(true);
            day.setEditButtonDisable(false);
            day.setCancelButtonDisable(true);
            day.setSaveButtonDisable(true);

            disableTextArea(day, true);
            disableDayTypeSelection(day, true);
            disableWorkingDayForm(day, true);
            disableSickLeaveForm(day, true);

            LoadDataController loadController = new LoadDataController();

            loadController.loadData(day);
        }
    }
}