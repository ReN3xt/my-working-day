package it.orion.myworkingday.controller.applicativo;

import java.io.*;

import it.orion.myworkingday.model.Day;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LoadDataController {

    public LoadDataController() {

    }

    public void loadData(Day day) {

        File file = new File(System.getenv("LOCALAPPDATA") + "/MWD","local_db.json");

        if(file.exists()){
            JSONParser parser = new JSONParser();

            try {

                JSONObject dayList = (JSONObject) parser.parse(new FileReader(file));

                JSONObject dayData = (JSONObject) dayList.get(day.getSelectedDate());


                if(dayData == null){
                    day.setDeleteButtonDisable(true);
                    day.setLoad(false);
                } else {
                    loadDayType(day, dayData);

                    if(dayData.get("notes") != null) {
                        day.setNotesTextAreaContent(dayData.get("notes").toString());
                    } else {
                        day.setNotesTextAreaContent(null);
                    }

                    if(dayData.get("reminders") != null) {
                        day.setRemindersTextAreaContent(dayData.get("reminders").toString());
                    } else {
                        day.setRemindersTextAreaContent(null);
                    }

                    day.setDeleteButtonDisable(false);
                    day.setLoad(true);
                }

            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }

        } else {
            createFile(file);

            day.setLoad(false);
        }
    }

    public static void createFile(File file) {
        File folder = new File(System.getenv("LOCALAPPDATA") + "/MWD");

        try {
            if(folder.mkdirs() || file.createNewFile()) {
                FileWriter fileWriter = new FileWriter(file);

                fileWriter.write("{}");

                fileWriter.flush();
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDayType(Day day, JSONObject dayData) {
        if(dayData.get("day_type") != null) {
            if (dayData.get("day_type").equals("working")) {
                day.setWorkingDayButtonSelect(true);
                loadWorkingHours(day, (JSONObject) dayData.get("working_hours"));
                loadLaunchBreak(day, (JSONObject) dayData.get("launch_break"));
                loadOvertime(day, (JSONObject) dayData.get("overtime"));
                loadPermit(day, (JSONObject) dayData.get("permit"));
            } else if (dayData.get("day_type").equals("rest")) {
                day.setRestButtonSelect(true);
            } else if (dayData.get("day_type").equals("sick")) {
                day.setSickLeaveButtonSelect(true);
                loadSickLeave(day, (JSONObject) dayData.get("sick_leave"));
            } else if (dayData.get("day_type").equals("holiday")) {
                day.setHolidayButtonSelect(true);
            }
        }

    }

    public void loadWorkingHours(Day day, JSONObject workingHoursData) {
        if(workingHoursData != null) {
            day.getWorkingHoursStartHSelectionModel().select(workingHoursData.get("start_h").toString());
            day.getWorkingHoursStartMSelectionModel().select(workingHoursData.get("start_m").toString());
            day.getWorkingHoursEndHSelectionModel().select(workingHoursData.get("end_h").toString());
            day.getWorkingHoursEndMSelectionModel().select(workingHoursData.get("end_m").toString());
        }
    }

    public void loadLaunchBreak(Day day, JSONObject launchBreakData) {
        if(launchBreakData != null) {
            day.setLaunchBreakSelect(true);
            day.getLaunchBreakStartHSelectionModel().select(launchBreakData.get("start_h").toString());
            day.getLaunchBreakStartMSelectionModel().select(launchBreakData.get("start_m").toString());
            day.getLaunchBreakEndHSelectionModel().select(launchBreakData.get("end_h").toString());
            day.getLaunchBreakEndMSelectionModel().select(launchBreakData.get("end_m").toString());
        }
    }

    public void loadOvertime(Day day, JSONObject overtimeData) {
        if(overtimeData != null) {
            day.setOvertimeSelect(true);
            day.getOvertimeHSelectionModel().select(overtimeData.get("h").toString());
            day.getOvertimeMSelectionModel().select(overtimeData.get("m").toString());
        }
    }

    public void loadPermit(Day day, JSONObject permitData) {
        if(permitData != null) {
            day.setPermitSelect(true);
            day.getPermitHSelectionModel().select(permitData.get("h").toString());
            day.getPermitMSelectionModel().select(permitData.get("m").toString());

            if(permitData.get("reason") != null) {
                day.setPermitReasonContent(permitData.get("reason").toString());
            } else {
                day.setPermitReasonContent(null);
            }
        }
    }

    public void loadSickLeave(Day day, JSONObject sickLeaveData) {
        if(sickLeaveData != null) {
            day.setSickLeaveProtocolContent(sickLeaveData.get("protocol").toString());
        }
    }
}
