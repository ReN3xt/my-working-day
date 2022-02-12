package it.orion.myworkingday.controller.applicativo;

import it.orion.myworkingday.model.Day;
import javafx.scene.control.SingleSelectionModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class SaveDataController {

    //Key name in JSON file
    public static final String DAY_TYPE = "day_type";
    public static final String WORKING_DAY = "working";
    public static final String REST = "rest";
    public static final String SICK_LEAVE = "sick_leave";
    public static final String HOLIDAY = "holiday";
    public static final String WORKING_HOURS = "working_hours";
    public static final String START_HOUR = "start_h";
    public static final String START_MINUTE = "start_m";
    public static final String END_HOUR = "end_h";
    public static final String END_MINUTE = "end_m";
    public static final String LAUNCH_BREAK = "launch_break";
    public static final String OVERTIME = "overtime";
    public static final String PERMIT = "permit";
    public static final String PERMIT_REASON = "reason";
    public static final String SICK_LEAVE_PROTOCOL = "protocol";
    public static final String HOUR = "h";
    public static final String MINUTE = "m";
    public static final String NOTES = "notes";
    public static final String REMINDERS = "reminders";

    public boolean saveData(Day day) {
        if (checkValidForm(day)) {
            File file = new File(System.getenv("LOCALAPPDATA") + "/MWD","local_db.json");

            try (FileWriter fileWriter = new FileWriter(file)){

                JSONObject dayList = (JSONObject) new JSONParser().parse(new FileReader(file));

                if(day.isLoad()){
                    dayList.remove(day.getSelectedDate());
                }

                dayList.put(day.getSelectedDate(), getDayJson(day));

                fileWriter.write(dayList.toJSONString());

                fileWriter.flush();
            } catch (FileNotFoundException ignored) {
                LoadDataController.createFile(file);

                day.setLoad(false);

                return false;
            } catch (IOException | ParseException ignored) {
                day.setLoad(false);

                return false;
            }


            day.setLoad(true);

            return true;
        } else {
            return false;
        }
    }

    public boolean checkValidForm(Day day) {

        if (day.isWorkingDayButtonSelect()) {

            return checkWorkingDayValidForm(day);

        } else if (day.isRestButtonSelect()) {

            return true;

        } else if (day.isSickLeaveButtonSelect()) {

            return checkSickLeaveValidForm(day);

        } else if (day.isHolidayButtonSelect()) {

            return true;

        }

        return checkNotesAndRemindersValidForm(day);//Check If Notes Or Reminder Not Empty
    }

    public boolean checkWorkingDayValidForm(Day day) {

        if (!checkWorkingHoursValidForm(day)) {
            return false;
        }

        int workingStartMinutes = calculateMinutes(day.getWorkingHoursStartHSelectionModel(), day.getWorkingHoursStartMSelectionModel());
        int workingEndMinutes = calculateMinutes(day.getWorkingHoursEndHSelectionModel(), day.getWorkingHoursEndMSelectionModel());

        if (day.isLaunchBreakSelect() && !checkLaunchBreakValidForm(day)) {
            return false;
        }

        if (day.isOvertimeSelect() && !checkOvertimeValidForm(day, workingStartMinutes, workingEndMinutes)) {
            return false;
        }

        return !day.isPermitSelect() || checkPermitValidForm(day, workingStartMinutes, workingEndMinutes);
    }

    public boolean checkWorkingHoursValidForm(Day day) {
        return !day.getWorkingHoursStartHSelectionModel().isEmpty() &&
                !day.getWorkingHoursStartMSelectionModel().isEmpty() &&
                !day.getWorkingHoursEndHSelectionModel().isEmpty() &&
                !day.getWorkingHoursEndMSelectionModel().isEmpty();
    }

    public boolean checkSickLeaveValidForm(Day day) {
        return day.getSickLeaveProtocolContent() != null;
    }

    public boolean checkNotesAndRemindersValidForm(Day day) {
        return day.getNotesTextAreaContent() != null || day.getRemindersTextAreaContent() != null;
    }

    public boolean checkLaunchBreakValidForm(Day day) {
        if(day.getLaunchBreakStartHSelectionModel().isEmpty() || day.getLaunchBreakStartMSelectionModel().isEmpty() || day.getLaunchBreakEndHSelectionModel().isEmpty() || day.getLaunchBreakEndMSelectionModel().isEmpty()){
            return false;
        }

        int workingStartTime = Integer.parseInt(day.getWorkingHoursStartHSelectionModel().getSelectedItem() + day.getWorkingHoursStartMSelectionModel().getSelectedItem());
        int workingEndTime = Integer.parseInt(day.getWorkingHoursEndHSelectionModel().getSelectedItem() + day.getWorkingHoursEndMSelectionModel().getSelectedItem());
        int launchStartTime = Integer.parseInt(day.getLaunchBreakStartHSelectionModel().getSelectedItem() + day.getLaunchBreakStartMSelectionModel().getSelectedItem());
        int launchEndTime = Integer.parseInt(day.getLaunchBreakEndHSelectionModel().getSelectedItem() + day.getLaunchBreakEndMSelectionModel().getSelectedItem());

        if(workingStartTime < workingEndTime) {
            if(launchStartTime <= launchEndTime) {
                return workingStartTime <= launchStartTime && workingEndTime >= launchEndTime;
            } else {
                return false;
            }
        } else if(workingStartTime > workingEndTime) {
            if(launchStartTime <= launchEndTime) {
                return launchStartTime >= workingStartTime || launchEndTime <= workingEndTime;
            } else {
                return launchStartTime >= workingStartTime && launchEndTime <= workingEndTime;
            }
        } else {
            return workingStartTime == launchStartTime && workingEndTime == launchEndTime;
        }
    }

    public boolean checkOvertimeValidForm(Day day, int workingStartMinutes, int workingEndMinutes) {
        if(day.getOvertimeHSelectionModel().isEmpty() || day.getOvertimeMSelectionModel().isEmpty()){
            return false;
        }

        int overtimeMinutes = calculateMinutes(day.getOvertimeHSelectionModel(), day.getOvertimeMSelectionModel());

        if(!day.isPermitSelect()) {
            if (workingStartMinutes <= workingEndMinutes) {
                return workingEndMinutes - workingStartMinutes + overtimeMinutes <= 1440;
            } else {
                return workingStartMinutes - workingEndMinutes >= overtimeMinutes;
            }
        } else if(!day.getPermitHSelectionModel().isEmpty() && !day.getPermitMSelectionModel().isEmpty()) {
            int permitsMinutes = calculateMinutes(day.getPermitHSelectionModel(), day.getPermitMSelectionModel());

            if (workingStartMinutes <= workingEndMinutes) {
                return workingEndMinutes - workingStartMinutes + overtimeMinutes + permitsMinutes <= 1440;
            } else {
                return workingStartMinutes - workingEndMinutes >= overtimeMinutes + permitsMinutes;
            }
        } else {
            return false;
        }
    }

    public boolean checkPermitValidForm(Day day, int workingStartMinutes,int workingEndMinutes) {
        if (day.getPermitHSelectionModel().isEmpty() || day.getPermitMSelectionModel().isEmpty()) {
            return false;
        }

        int permitsMinutes = calculateMinutes(day.getPermitHSelectionModel(), day.getPermitMSelectionModel());

        if (!day.isOvertimeSelect()) {
            if (workingStartMinutes <= workingEndMinutes) {
                return workingEndMinutes - workingStartMinutes + permitsMinutes <= 1440;
            } else {
                return workingStartMinutes - workingEndMinutes >= permitsMinutes;
            }
        } else if (!day.getOvertimeHSelectionModel().isEmpty() && !day.getOvertimeMSelectionModel().isEmpty()){
            int overtimeMinutes = calculateMinutes(day.getOvertimeHSelectionModel(), day.getOvertimeMSelectionModel());

            if (workingStartMinutes <= workingEndMinutes) {
                return workingEndMinutes - workingStartMinutes + permitsMinutes + overtimeMinutes <= 1440;
            } else {
                return workingStartMinutes - workingEndMinutes >= permitsMinutes + overtimeMinutes;
            }
        } else {
            return false;
        }
    }

    public int calculateMinutes(SingleSelectionModel<String> hours, SingleSelectionModel<String> minutes) {
        return (Integer.parseInt(hours.getSelectedItem()) * 60) + Integer.parseInt(minutes.getSelectedItem());
    }

    public JSONObject getDayJson(Day day) {
        JSONObject dayJson = new JSONObject();

        if(day.isWorkingDayButtonSelect()){
            dayJson.put(DAY_TYPE, WORKING_DAY);
            dayJson.put(WORKING_HOURS, getWorkingHoursJson(day));

            if(day.isLaunchBreakSelect()){
                dayJson.put(LAUNCH_BREAK, getLaunchBreakJson(day));
            } else {
                dayJson.put(LAUNCH_BREAK, null);
            }

            if(day.isOvertimeSelect()) {
                dayJson.put(OVERTIME, getOvertimeJson(day));
            } else {
                dayJson.put(OVERTIME, null);
            }

            if(day.isPermitSelect()){
                dayJson.put(PERMIT, getPermitJson(day));
            } else {
                dayJson.put(PERMIT, null);
            }

            dayJson.put(SICK_LEAVE, null);

        } else if (day.isRestButtonSelect()) {
            dayJson.put(DAY_TYPE, REST);
            dayJson.put(WORKING_HOURS, null);
            dayJson.put(LAUNCH_BREAK, null);
            dayJson.put(OVERTIME, null);
            dayJson.put(PERMIT, null);
            dayJson.put(SICK_LEAVE, null);

        } else if (day.isSickLeaveButtonSelect()) {
            dayJson.put(DAY_TYPE, "sick");
            dayJson.put(WORKING_HOURS, null);
            dayJson.put(LAUNCH_BREAK, null);
            dayJson.put(OVERTIME, null);
            dayJson.put(PERMIT, null);

            if(day.getSickLeaveProtocolContent() == null) {
                dayJson.put(SICK_LEAVE, null);
            } else{
                dayJson.put(SICK_LEAVE, getSickLeaveJson(day));
            }

        } else if (day.isHolidayButtonSelect()) {
            dayJson.put(DAY_TYPE, HOLIDAY);
            dayJson.put(WORKING_HOURS, null);
            dayJson.put(LAUNCH_BREAK, null);
            dayJson.put(OVERTIME, null);
            dayJson.put(PERMIT, null);
            dayJson.put(SICK_LEAVE, null);

        }

        if(day.getNotesTextAreaContent() == null) {
            dayJson.put(NOTES, null);
        } else {
            dayJson.put(NOTES, day.getNotesTextAreaContent());
        }

        if(day.getRemindersTextAreaContent() == null) {
            dayJson.put(REMINDERS, null);
        } else {
            dayJson.put(REMINDERS, day.getRemindersTextAreaContent());
        }

        return dayJson;
    }

    public JSONObject getWorkingHoursJson(Day day) {
        JSONObject workingHoursJson = new JSONObject();

        workingHoursJson.put(START_HOUR, day.getWorkingHoursStartHSelectionModel().getSelectedItem());
        workingHoursJson.put(START_MINUTE, day.getWorkingHoursStartMSelectionModel().getSelectedItem());
        workingHoursJson.put(END_HOUR, day.getWorkingHoursEndHSelectionModel().getSelectedItem());
        workingHoursJson.put(END_MINUTE, day.getWorkingHoursEndMSelectionModel().getSelectedItem());

        return workingHoursJson;
    }

    public JSONObject getLaunchBreakJson(Day day) {
        JSONObject launchBreakJson = new JSONObject();

        launchBreakJson.put(START_HOUR, day.getLaunchBreakStartHSelectionModel().getSelectedItem());
        launchBreakJson.put(START_MINUTE, day.getLaunchBreakStartMSelectionModel().getSelectedItem());
        launchBreakJson.put(END_HOUR, day.getLaunchBreakEndHSelectionModel().getSelectedItem());
        launchBreakJson.put(END_MINUTE, day.getLaunchBreakEndMSelectionModel().getSelectedItem());

        return launchBreakJson;
    }

    public JSONObject getOvertimeJson(Day day) {
        JSONObject overtimeJson = new JSONObject();

        overtimeJson.put(HOUR, day.getOvertimeHSelectionModel().getSelectedItem());
        overtimeJson.put(MINUTE, day.getOvertimeMSelectionModel().getSelectedItem());

        return overtimeJson;
    }

    public JSONObject getPermitJson(Day day) {
        JSONObject permitJson = new JSONObject();

        permitJson.put(HOUR, day.getPermitHSelectionModel().getSelectedItem());
        permitJson.put(MINUTE, day.getPermitMSelectionModel().getSelectedItem());

        if(day.getPermitReasonContent() == null) {
            permitJson.put(PERMIT_REASON, null);
        } else {
            permitJson.put(PERMIT_REASON, day.getPermitReasonContent());
        }

        return permitJson;
    }

    public JSONObject getSickLeaveJson(Day day) {
        JSONObject sickLeaveJson = new JSONObject();

        sickLeaveJson.put(SICK_LEAVE_PROTOCOL, day.getSickLeaveProtocolContent());

        return sickLeaveJson;
    }
}
