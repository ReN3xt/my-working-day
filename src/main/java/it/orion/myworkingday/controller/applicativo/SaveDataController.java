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
import java.util.Objects;


public class SaveDataController {

    public boolean saveData() {
        if (checkValidForm()) {
            File file = new File(System.getenv("LOCALAPPDATA") + "/MWD","local_db.json");

            try(FileReader fileReader = new FileReader(file)){

                JSONObject dayList = (JSONObject) new JSONParser().parse(fileReader);

                if(Day.getInstance().isLoad()){
                    dayList.remove(Day.getInstance().getSelectedDate());
                }

                dayList.put(Day.getInstance().getSelectedDate(), getDayJson());

                writeData(file, dayList);

            } catch (FileNotFoundException e) {
                LoadDataController.createFile(file);

                Day.getInstance().setLoad(false);

                saveData();
            } catch (IOException | ParseException e) {
                LoadDataController.initializeFile(file);

                Day.getInstance().setLoad(false);

                saveData();
            }

            Day.getInstance().setLoad(true);

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
            //Do nothing
        }
    }

    public boolean checkValidForm() {

        if (Day.getInstance().isWorkingDayButtonSelect()) {

            return checkWorkingDayValidForm();

        } else if (Day.getInstance().isRestButtonSelect()) {

            return true;

        } else if (Day.getInstance().isSickLeaveButtonSelect()) {

            return checkSickLeaveValidForm();

        } else if (Day.getInstance().isHolidayButtonSelect()) {

            return true;

        }

        return checkNotesAndRemindersValidForm(); //Check If Notes Or Reminder Not Empty
    }

    public boolean checkWorkingDayValidForm() {

        if (!checkWorkingHoursValidForm()) {
            return false;
        }

        int workingStartMinutes = calculateMinutes(Day.getInstance().getWorkingHoursStartHSelectionModel().getSelectedItem(), Day.getInstance().getWorkingHoursStartMSelectionModel().getSelectedItem());
        int workingEndMinutes = calculateMinutes(Day.getInstance().getWorkingHoursEndHSelectionModel().getSelectedItem(), Day.getInstance().getWorkingHoursEndMSelectionModel().getSelectedItem());

        if (Day.getInstance().isLaunchBreakSelect() && !checkLaunchBreakValidForm()) {
            return false;
        }

        if (Day.getInstance().isOvertimeSelect() && !checkOvertimeValidForm(workingStartMinutes, workingEndMinutes)) {
            return false;
        }

        return !Day.getInstance().isPermitSelect() || checkPermitValidForm(workingStartMinutes, workingEndMinutes);
    }

    public boolean checkWorkingHoursValidForm() {
        return !Day.getInstance().getWorkingHoursStartHSelectionModel().isEmpty() &&
                !Day.getInstance().getWorkingHoursStartMSelectionModel().isEmpty() &&
                !Day.getInstance().getWorkingHoursEndHSelectionModel().isEmpty() &&
                !Day.getInstance().getWorkingHoursEndMSelectionModel().isEmpty();
    }

    public boolean checkSickLeaveValidForm() {
        return Day.getInstance().getSickLeaveProtocolContent() != null;
    }

    public boolean checkNotesAndRemindersValidForm() {
        return (Day.getInstance().getNotesTextAreaContent() != null && !Day.getInstance().getNotesTextAreaContent().isEmpty()) ||
                (Day.getInstance().getRemindersTextAreaContent() != null && !Day.getInstance().getRemindersTextAreaContent().equals(""));
    }

    public boolean checkLaunchBreakValidForm() {
        if(Day.getInstance().getLaunchBreakStartHSelectionModel().isEmpty() || Day.getInstance().getLaunchBreakStartMSelectionModel().isEmpty() || Day.getInstance().getLaunchBreakEndHSelectionModel().isEmpty() || Day.getInstance().getLaunchBreakEndMSelectionModel().isEmpty()){
            return false;
        }

        int workingStartTime = Integer.parseInt(Day.getInstance().getWorkingHoursStartHSelectionModel().getSelectedItem() + Day.getInstance().getWorkingHoursStartMSelectionModel().getSelectedItem());
        int workingEndTime = Integer.parseInt(Day.getInstance().getWorkingHoursEndHSelectionModel().getSelectedItem() + Day.getInstance().getWorkingHoursEndMSelectionModel().getSelectedItem());
        int launchStartTime = Integer.parseInt(Day.getInstance().getLaunchBreakStartHSelectionModel().getSelectedItem() + Day.getInstance().getLaunchBreakStartMSelectionModel().getSelectedItem());
        int launchEndTime = Integer.parseInt(Day.getInstance().getLaunchBreakEndHSelectionModel().getSelectedItem() + Day.getInstance().getLaunchBreakEndMSelectionModel().getSelectedItem());

        return isLaunchBreakInsideWorkingHours(workingStartTime, workingEndTime, launchStartTime, launchEndTime);
    }

    public boolean isLaunchBreakInsideWorkingHours(int workingStartTime, int workingEndTime, int launchStartTime, int launchEndTime){
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

    public boolean checkOvertimeValidForm(int workingStartMinutes, int workingEndMinutes) {
        if(Day.getInstance().getOvertimeHSelectionModel().isEmpty() || Day.getInstance().getOvertimeMSelectionModel().isEmpty()){
            return false;
        }

        int overtimeMinutes = calculateMinutes(Day.getInstance().getOvertimeHSelectionModel().getSelectedItem(), Day.getInstance().getOvertimeMSelectionModel().getSelectedItem());

        if(!Day.getInstance().isPermitSelect()) {
            if (workingStartMinutes <= workingEndMinutes) {
                return workingEndMinutes - workingStartMinutes + overtimeMinutes <= 1440;
            } else {
                return workingStartMinutes - workingEndMinutes >= overtimeMinutes;
            }
        } else if(!Day.getInstance().getPermitHSelectionModel().isEmpty() && !Day.getInstance().getPermitMSelectionModel().isEmpty()) {
            int permitsMinutes = calculateMinutes(Day.getInstance().getPermitHSelectionModel().getSelectedItem(), Day.getInstance().getPermitMSelectionModel().getSelectedItem());

            if (workingStartMinutes <= workingEndMinutes) {
                return workingEndMinutes - workingStartMinutes + overtimeMinutes + permitsMinutes <= 1440;
            } else {
                return workingStartMinutes - workingEndMinutes >= overtimeMinutes + permitsMinutes;
            }
        } else {
            return false;
        }
    }

    public boolean checkPermitValidForm(int workingStartMinutes,int workingEndMinutes) {
        if (Day.getInstance().getPermitHSelectionModel().isEmpty() || Day.getInstance().getPermitMSelectionModel().isEmpty()) {
            return false;
        }

        int permitsMinutes = calculateMinutes(Day.getInstance().getPermitHSelectionModel().getSelectedItem(), Day.getInstance().getPermitMSelectionModel().getSelectedItem());

        if (!Day.getInstance().isOvertimeSelect()) {
            if (workingStartMinutes <= workingEndMinutes) {
                return workingEndMinutes - workingStartMinutes + permitsMinutes <= 1440;
            } else {
                return workingStartMinutes - workingEndMinutes >= permitsMinutes;
            }
        } else if (!Day.getInstance().getOvertimeHSelectionModel().isEmpty() && !Day.getInstance().getOvertimeMSelectionModel().isEmpty()){
            int overtimeMinutes = calculateMinutes(Day.getInstance().getOvertimeHSelectionModel().getSelectedItem(), Day.getInstance().getOvertimeMSelectionModel().getSelectedItem());

            if (workingStartMinutes <= workingEndMinutes) {
                return workingEndMinutes - workingStartMinutes + permitsMinutes + overtimeMinutes <= 1440;
            } else {
                return workingStartMinutes - workingEndMinutes >= permitsMinutes + overtimeMinutes;
            }
        } else {
            return false;
        }
    }

    public int calculateMinutes(String hours, String minutes) {
        return (Integer.parseInt(hours) * 60) + Integer.parseInt(minutes);
    }

    public JSONObject getDayJson() {
        JSONObject dayJson = new JSONObject();

        if(Day.getInstance().isWorkingDayButtonSelect()){
            setWorkingDayJson(dayJson);

        } else if (Day.getInstance().isRestButtonSelect()) {
            setRestJson(dayJson);

        } else if (Day.getInstance().isSickLeaveButtonSelect()) {
            setSickLeaveJson(dayJson);

        } else if (Day.getInstance().isHolidayButtonSelect()) {
            setHolidayJson(dayJson);

        }

        if(Day.getInstance().getNotesTextAreaContent() == null) {
            dayJson.put(LoadDataController.NOTES, null);
        } else {
            dayJson.put(LoadDataController.NOTES, Day.getInstance().getNotesTextAreaContent());
        }

        if(Day.getInstance().getRemindersTextAreaContent() == null) {
            dayJson.put(LoadDataController.REMINDERS, null);
        } else {
            dayJson.put(LoadDataController.REMINDERS, Day.getInstance().getRemindersTextAreaContent());
        }

        return dayJson;
    }

    public void setWorkingDayJson(JSONObject dayJson){
        dayJson.put(LoadDataController.DAY_TYPE, LoadDataController.WORKING_DAY);
        dayJson.put(LoadDataController.WORKING_HOURS, getWorkingHoursJson());

        if(Day.getInstance().isLaunchBreakSelect()){
            dayJson.put(LoadDataController.LAUNCH_BREAK, getLaunchBreakJson());
        } else {
            dayJson.put(LoadDataController.LAUNCH_BREAK, null);
        }

        if(Day.getInstance().isOvertimeSelect()) {
            dayJson.put(LoadDataController.OVERTIME, getOvertimeJson());
        } else {
            dayJson.put(LoadDataController.OVERTIME, null);
        }

        if(Day.getInstance().isPermitSelect()){
            dayJson.put(LoadDataController.PERMIT, getPermitJson());
        } else {
            dayJson.put(LoadDataController.PERMIT, null);
        }

        dayJson.put(LoadDataController.SICK_LEAVE, null);
    }

    public void setRestJson(JSONObject dayJson){
        dayJson.put(LoadDataController.DAY_TYPE, LoadDataController.REST);
        dayJson.put(LoadDataController.WORKING_HOURS, null);
        dayJson.put(LoadDataController.LAUNCH_BREAK, null);
        dayJson.put(LoadDataController.OVERTIME, null);
        dayJson.put(LoadDataController.PERMIT, null);
        dayJson.put(LoadDataController.SICK_LEAVE, null);
    }

    public void setSickLeaveJson(JSONObject dayJson){
        dayJson.put(LoadDataController.DAY_TYPE, LoadDataController.SICK);
        dayJson.put(LoadDataController.WORKING_HOURS, null);
        dayJson.put(LoadDataController.LAUNCH_BREAK, null);
        dayJson.put(LoadDataController.OVERTIME, null);
        dayJson.put(LoadDataController.PERMIT, null);

        if(Day.getInstance().getSickLeaveProtocolContent() == null) {
            dayJson.put(LoadDataController.SICK_LEAVE, null);
        } else{
            dayJson.put(LoadDataController.SICK_LEAVE, getSickLeaveJson());
        }
    }

    public void setHolidayJson(JSONObject dayJson){
        dayJson.put(LoadDataController.DAY_TYPE, LoadDataController.HOLIDAY);
        dayJson.put(LoadDataController.WORKING_HOURS, null);
        dayJson.put(LoadDataController.LAUNCH_BREAK, null);
        dayJson.put(LoadDataController.OVERTIME, null);
        dayJson.put(LoadDataController.PERMIT, null);
        dayJson.put(LoadDataController.SICK_LEAVE, null);
    }

    public JSONObject getWorkingHoursJson() {
        JSONObject workingHoursJson = new JSONObject();

        workingHoursJson.put(LoadDataController.START_HOUR, Day.getInstance().getWorkingHoursStartHSelectionModel().getSelectedItem());
        workingHoursJson.put(LoadDataController.START_MINUTE, Day.getInstance().getWorkingHoursStartMSelectionModel().getSelectedItem());
        workingHoursJson.put(LoadDataController.END_HOUR, Day.getInstance().getWorkingHoursEndHSelectionModel().getSelectedItem());
        workingHoursJson.put(LoadDataController.END_MINUTE, Day.getInstance().getWorkingHoursEndMSelectionModel().getSelectedItem());

        return workingHoursJson;
    }

    public JSONObject getLaunchBreakJson() {
        JSONObject launchBreakJson = new JSONObject();

        launchBreakJson.put(LoadDataController.START_HOUR, Day.getInstance().getLaunchBreakStartHSelectionModel().getSelectedItem());
        launchBreakJson.put(LoadDataController.START_MINUTE, Day.getInstance().getLaunchBreakStartMSelectionModel().getSelectedItem());
        launchBreakJson.put(LoadDataController.END_HOUR, Day.getInstance().getLaunchBreakEndHSelectionModel().getSelectedItem());
        launchBreakJson.put(LoadDataController.END_MINUTE, Day.getInstance().getLaunchBreakEndMSelectionModel().getSelectedItem());

        return launchBreakJson;
    }

    public JSONObject getOvertimeJson() {
        JSONObject overtimeJson = new JSONObject();

        overtimeJson.put(LoadDataController.HOUR, Day.getInstance().getOvertimeHSelectionModel().getSelectedItem());
        overtimeJson.put(LoadDataController.MINUTE, Day.getInstance().getOvertimeMSelectionModel().getSelectedItem());

        return overtimeJson;
    }

    public JSONObject getPermitJson() {
        JSONObject permitJson = new JSONObject();

        permitJson.put(LoadDataController.HOUR, Day.getInstance().getPermitHSelectionModel().getSelectedItem());
        permitJson.put(LoadDataController.MINUTE, Day.getInstance().getPermitMSelectionModel().getSelectedItem());

        if(Day.getInstance().getPermitReasonContent() == null) {
            permitJson.put(LoadDataController.PERMIT_REASON, null);
        } else {
            permitJson.put(LoadDataController.PERMIT_REASON, Day.getInstance().getPermitReasonContent());
        }

        return permitJson;
    }

    public JSONObject getSickLeaveJson() {
        JSONObject sickLeaveJson = new JSONObject();

        sickLeaveJson.put(LoadDataController.SICK_LEAVE_PROTOCOL, Day.getInstance().getSickLeaveProtocolContent());

        return sickLeaveJson;
    }
}
