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

    public boolean saveData(Day day) {
        if (checkValidForm(day)) {
            File file = new File(System.getenv("LOCALAPPDATA") + "/MWD","local_db.json");

            try(FileReader fileReader = new FileReader(file)){

                JSONObject dayList = (JSONObject) new JSONParser().parse(fileReader);

                if(day.isLoad()){
                    dayList.remove(day.getSelectedDate());
                }

                dayList.put(day.getSelectedDate(), getDayJson(day));

                writeData(file, dayList);

            } catch (FileNotFoundException e) {
                LoadDataController.createFile(file);

                day.setLoad(false);

                saveData(day);
            } catch (IOException | ParseException e) {
                LoadDataController.initializeFile(file);

                day.setLoad(false);

                saveData(day);
            }

            day.setLoad(true);

            return true;
        } else {
            return false;
        }
    }

    public static void writeData(File file, JSONObject data) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(data.toJSONString());
            fileWriter.flush();
        } catch (IOException ignored) {
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
            dayJson.put(LoadDataController.DAY_TYPE, LoadDataController.WORKING_DAY);
            dayJson.put(LoadDataController.WORKING_HOURS, getWorkingHoursJson(day));

            if(day.isLaunchBreakSelect()){
                dayJson.put(LoadDataController.LAUNCH_BREAK, getLaunchBreakJson(day));
            } else {
                dayJson.put(LoadDataController.LAUNCH_BREAK, null);
            }

            if(day.isOvertimeSelect()) {
                dayJson.put(LoadDataController.OVERTIME, getOvertimeJson(day));
            } else {
                dayJson.put(LoadDataController.OVERTIME, null);
            }

            if(day.isPermitSelect()){
                dayJson.put(LoadDataController.PERMIT, getPermitJson(day));
            } else {
                dayJson.put(LoadDataController.PERMIT, null);
            }

            dayJson.put(LoadDataController.SICK_LEAVE, null);

        } else if (day.isRestButtonSelect()) {
            dayJson.put(LoadDataController.DAY_TYPE, LoadDataController.REST);
            dayJson.put(LoadDataController.WORKING_HOURS, null);
            dayJson.put(LoadDataController.LAUNCH_BREAK, null);
            dayJson.put(LoadDataController.OVERTIME, null);
            dayJson.put(LoadDataController.PERMIT, null);
            dayJson.put(LoadDataController.SICK_LEAVE, null);

        } else if (day.isSickLeaveButtonSelect()) {
            dayJson.put(LoadDataController.DAY_TYPE, "sick");
            dayJson.put(LoadDataController.WORKING_HOURS, null);
            dayJson.put(LoadDataController.LAUNCH_BREAK, null);
            dayJson.put(LoadDataController.OVERTIME, null);
            dayJson.put(LoadDataController.PERMIT, null);

            if(day.getSickLeaveProtocolContent() == null) {
                dayJson.put(LoadDataController.SICK_LEAVE, null);
            } else{
                dayJson.put(LoadDataController.SICK_LEAVE, getSickLeaveJson(day));
            }

        } else if (day.isHolidayButtonSelect()) {
            dayJson.put(LoadDataController.DAY_TYPE, LoadDataController.HOLIDAY);
            dayJson.put(LoadDataController.WORKING_HOURS, null);
            dayJson.put(LoadDataController.LAUNCH_BREAK, null);
            dayJson.put(LoadDataController.OVERTIME, null);
            dayJson.put(LoadDataController.PERMIT, null);
            dayJson.put(LoadDataController.SICK_LEAVE, null);

        }

        if(day.getNotesTextAreaContent() == null) {
            dayJson.put(LoadDataController.NOTES, null);
        } else {
            dayJson.put(LoadDataController.NOTES, day.getNotesTextAreaContent());
        }

        if(day.getRemindersTextAreaContent() == null) {
            dayJson.put(LoadDataController.REMINDERS, null);
        } else {
            dayJson.put(LoadDataController.REMINDERS, day.getRemindersTextAreaContent());
        }

        return dayJson;
    }

    public JSONObject getWorkingHoursJson(Day day) {
        JSONObject workingHoursJson = new JSONObject();

        workingHoursJson.put(LoadDataController.START_HOUR, day.getWorkingHoursStartHSelectionModel().getSelectedItem());
        workingHoursJson.put(LoadDataController.START_MINUTE, day.getWorkingHoursStartMSelectionModel().getSelectedItem());
        workingHoursJson.put(LoadDataController.END_HOUR, day.getWorkingHoursEndHSelectionModel().getSelectedItem());
        workingHoursJson.put(LoadDataController.END_MINUTE, day.getWorkingHoursEndMSelectionModel().getSelectedItem());

        return workingHoursJson;
    }

    public JSONObject getLaunchBreakJson(Day day) {
        JSONObject launchBreakJson = new JSONObject();

        launchBreakJson.put(LoadDataController.START_HOUR, day.getLaunchBreakStartHSelectionModel().getSelectedItem());
        launchBreakJson.put(LoadDataController.START_MINUTE, day.getLaunchBreakStartMSelectionModel().getSelectedItem());
        launchBreakJson.put(LoadDataController.END_HOUR, day.getLaunchBreakEndHSelectionModel().getSelectedItem());
        launchBreakJson.put(LoadDataController.END_MINUTE, day.getLaunchBreakEndMSelectionModel().getSelectedItem());

        return launchBreakJson;
    }

    public JSONObject getOvertimeJson(Day day) {
        JSONObject overtimeJson = new JSONObject();

        overtimeJson.put(LoadDataController.HOUR, day.getOvertimeHSelectionModel().getSelectedItem());
        overtimeJson.put(LoadDataController.MINUTE, day.getOvertimeMSelectionModel().getSelectedItem());

        return overtimeJson;
    }

    public JSONObject getPermitJson(Day day) {
        JSONObject permitJson = new JSONObject();

        permitJson.put(LoadDataController.HOUR, day.getPermitHSelectionModel().getSelectedItem());
        permitJson.put(LoadDataController.MINUTE, day.getPermitMSelectionModel().getSelectedItem());

        if(day.getPermitReasonContent() == null) {
            permitJson.put(LoadDataController.PERMIT_REASON, null);
        } else {
            permitJson.put(LoadDataController.PERMIT_REASON, day.getPermitReasonContent());
        }

        return permitJson;
    }

    public JSONObject getSickLeaveJson(Day day) {
        JSONObject sickLeaveJson = new JSONObject();

        sickLeaveJson.put(LoadDataController.SICK_LEAVE_PROTOCOL, day.getSickLeaveProtocolContent());

        return sickLeaveJson;
    }
}
