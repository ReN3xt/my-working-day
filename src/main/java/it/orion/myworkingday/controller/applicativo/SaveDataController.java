package it.orion.myworkingday.controller.applicativo;

import it.orion.myworkingday.model.Day;
import javafx.scene.control.SingleSelectionModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class SaveDataController {

    public SaveDataController() {

    }

    //TODO Implementare eccezione per creazione del file nel caso in cui non sia presente
    public boolean saveData(Day day) {
        if (checkValidForm(day)) {
            try {
                File file = new File(System.getenv("LOCALAPPDATA") + "/MWD","local_db.json");

                JSONObject dayList = (JSONObject) new JSONParser().parse(new FileReader(file));

                if(day.isLoad()){
                    dayList.remove(day.getSelectedDate());
                }

                dayList.put(day.getSelectedDate(), getDayJson(day));

                FileWriter fileWriter = new FileWriter(file);

                fileWriter.write(dayList.toJSONString());

                fileWriter.flush();
                fileWriter.close();
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }

            day.setLoad(true);

            return true;
        } else {
            System.out.println("Form non valida");

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
                !day.getWorkingHoursEndMSelectionModel().isEmpty() &&
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
            dayJson.put("day_type", "working");
            dayJson.put("working_hours", getWorkingHoursJson(day));

            if(day.isLaunchBreakSelect()){
                dayJson.put("launch_break", getLaunchBreakJson(day));
            } else {
                dayJson.put("launch_break", null);
            }

            if(day.isOvertimeSelect()) {
                dayJson.put("overtime", getOvertimeJson(day));
            } else {
                dayJson.put("overtime", null);
            }

            if(day.isPermitSelect()){
                dayJson.put("permit", getPermitJson(day));
            } else {
                dayJson.put("permit", null);
            }

            dayJson.put("sick_leave", null);

        } else if (day.isRestButtonSelect()) {
            dayJson.put("day_type", "rest");
            dayJson.put("working_hours", null);
            dayJson.put("launch_break", null);
            dayJson.put("overtime", null);
            dayJson.put("permit", null);
            dayJson.put("sick_leave", null);

        } else if (day.isSickLeaveButtonSelect()) {
            dayJson.put("day_type", "sick");
            dayJson.put("working_hours", null);
            dayJson.put("launch_break", null);
            dayJson.put("overtime", null);
            dayJson.put("permit", null);

            if(day.getSickLeaveProtocolContent() == null) {
                dayJson.put("sick_leave", null);
            } else{
                dayJson.put("sick_leave", getSickLeaveJson(day));
            }

        } else if (day.isHolidayButtonSelect()) {
            dayJson.put("day_type", "holiday");
            dayJson.put("working_hours", null);
            dayJson.put("launch_break", null);
            dayJson.put("overtime", null);
            dayJson.put("permit", null);
            dayJson.put("sick_leave", null);

        }

        if(day.getNotesTextAreaContent() == null) {
            dayJson.put("notes", null);
        } else {
            dayJson.put("notes", day.getNotesTextAreaContent());
        }

        if(day.getRemindersTextAreaContent() == null) {
            dayJson.put("reminders", null);
        } else {
            dayJson.put("reminders", day.getRemindersTextAreaContent());
        }

        return dayJson;
    }

    public JSONObject getWorkingHoursJson(Day day) {
        JSONObject workingHoursJson = new JSONObject();

        workingHoursJson.put("start_h", day.getWorkingHoursStartHSelectionModel().getSelectedItem());
        workingHoursJson.put("start_m", day.getWorkingHoursStartMSelectionModel().getSelectedItem());
        workingHoursJson.put("end_h", day.getWorkingHoursEndHSelectionModel().getSelectedItem());
        workingHoursJson.put("end_m", day.getWorkingHoursEndMSelectionModel().getSelectedItem());

        return workingHoursJson;
    }

    public JSONObject getLaunchBreakJson(Day day) {
        JSONObject launchBreakJson = new JSONObject();

        launchBreakJson.put("start_h", day.getLaunchBreakStartHSelectionModel().getSelectedItem());
        launchBreakJson.put("start_m", day.getLaunchBreakStartMSelectionModel().getSelectedItem());
        launchBreakJson.put("end_h", day.getLaunchBreakEndHSelectionModel().getSelectedItem());
        launchBreakJson.put("end_m", day.getLaunchBreakEndMSelectionModel().getSelectedItem());

        return launchBreakJson;
    }

    public JSONObject getOvertimeJson(Day day) {
        JSONObject overtimeJson = new JSONObject();

        overtimeJson.put("h", day.getOvertimeHSelectionModel().getSelectedItem());
        overtimeJson.put("m", day.getOvertimeMSelectionModel().getSelectedItem());

        return overtimeJson;
    }

    public JSONObject getPermitJson(Day day) {
        JSONObject permitJson = new JSONObject();

        permitJson.put("h", day.getPermitHSelectionModel().getSelectedItem());
        permitJson.put("m", day.getPermitMSelectionModel().getSelectedItem());

        if(day.getPermitReasonContent() == null) {
            permitJson.put("reason", null);
        } else {
            System.out.println(day.getPermitReasonContent());
            permitJson.put("reason", day.getPermitReasonContent());
        }

        return permitJson;
    }

    public JSONObject getSickLeaveJson(Day day) {
        JSONObject sickLeaveJson = new JSONObject();

        sickLeaveJson.put("protocol", day.getSickLeaveProtocolContent());

        return sickLeaveJson;
    }
}
