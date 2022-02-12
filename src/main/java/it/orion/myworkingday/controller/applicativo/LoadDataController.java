package it.orion.myworkingday.controller.applicativo;

import java.io.*;

import it.orion.myworkingday.model.Day;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LoadDataController {

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

    public void loadData(Day day) {

        File file = new File(System.getenv("LOCALAPPDATA") + "/MWD","local_db.json");

        if(file.exists()){
            JSONParser parser = new JSONParser();

            try (FileReader fileReader = new FileReader(file)) {

                JSONObject dayList = (JSONObject) parser.parse(fileReader);

                JSONObject dayData = (JSONObject) dayList.get(day.getSelectedDate());


                if(dayData == null){
                    day.setDeleteButtonDisable(true);
                    day.setLoad(false);
                } else {
                    loadDayType(day, dayData);

                    if(dayData.get(NOTES) != null) {
                        day.setNotesTextAreaContent(dayData.get(NOTES).toString());
                    } else {
                        day.setNotesTextAreaContent(null);
                    }

                    if(dayData.get(REMINDERS) != null) {
                        day.setRemindersTextAreaContent(dayData.get(REMINDERS).toString());
                    } else {
                        day.setRemindersTextAreaContent(null);
                    }

                    day.setDeleteButtonDisable(false);
                    day.setLoad(true);
                }

            } catch (FileNotFoundException ignored) {
                createFile(file);

                day.setLoad(false);
            } catch (IOException | ParseException ignored) {
                initializeFile(file);

                day.setLoad(false);
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
                initializeFile(file);
            }
        } catch (IOException ignored) {
            //Do nothing
        }
    }

    public static void initializeFile(File file) {
        try(FileWriter fileWriter = new FileWriter(file)) {

            fileWriter.write("{}");

            fileWriter.flush();
        } catch (IOException ignored) {
            //Do nothing
        }
    }

    public void loadDayType(Day day, JSONObject dayData) {
        if(dayData.get(DAY_TYPE) != null) {
            if (dayData.get(DAY_TYPE).equals(WORKING_DAY)) {
                day.setWorkingDayButtonSelect(true);
                loadWorkingHours(day, (JSONObject) dayData.get(WORKING_HOURS));
                loadLaunchBreak(day, (JSONObject) dayData.get(LAUNCH_BREAK));
                loadOvertime(day, (JSONObject) dayData.get(OVERTIME));
                loadPermit(day, (JSONObject) dayData.get(PERMIT));
            } else if (dayData.get(DAY_TYPE).equals(REST)) {
                day.setRestButtonSelect(true);
            } else if (dayData.get(DAY_TYPE).equals("sick")) {
                day.setSickLeaveButtonSelect(true);
                loadSickLeave(day, (JSONObject) dayData.get(SICK_LEAVE));
            } else if (dayData.get(DAY_TYPE).equals(HOLIDAY)) {
                day.setHolidayButtonSelect(true);
            }
        }

    }

    public void loadWorkingHours(Day day, JSONObject workingHoursData) {
        if(workingHoursData != null) {
            day.getWorkingHoursStartHSelectionModel().select(workingHoursData.get(START_HOUR).toString());
            day.getWorkingHoursStartMSelectionModel().select(workingHoursData.get(START_MINUTE).toString());
            day.getWorkingHoursEndHSelectionModel().select(workingHoursData.get(END_HOUR).toString());
            day.getWorkingHoursEndMSelectionModel().select(workingHoursData.get(END_MINUTE).toString());
        }
    }

    public void loadLaunchBreak(Day day, JSONObject launchBreakData) {
        if(launchBreakData != null) {
            day.setLaunchBreakSelect(true);
            day.getLaunchBreakStartHSelectionModel().select(launchBreakData.get(START_HOUR).toString());
            day.getLaunchBreakStartMSelectionModel().select(launchBreakData.get(START_MINUTE).toString());
            day.getLaunchBreakEndHSelectionModel().select(launchBreakData.get(END_HOUR).toString());
            day.getLaunchBreakEndMSelectionModel().select(launchBreakData.get(END_MINUTE).toString());
        }
    }

    public void loadOvertime(Day day, JSONObject overtimeData) {
        if(overtimeData != null) {
            day.setOvertimeSelect(true);
            day.getOvertimeHSelectionModel().select(overtimeData.get(HOUR).toString());
            day.getOvertimeMSelectionModel().select(overtimeData.get(MINUTE).toString());
        }
    }

    public void loadPermit(Day day, JSONObject permitData) {
        if(permitData != null) {
            day.setPermitSelect(true);
            day.getPermitHSelectionModel().select(permitData.get(HOUR).toString());
            day.getPermitMSelectionModel().select(permitData.get(MINUTE).toString());

            if(permitData.get(PERMIT_REASON) != null) {
                day.setPermitReasonContent(permitData.get(PERMIT_REASON).toString());
            } else {
                day.setPermitReasonContent(null);
            }
        }
    }

    public void loadSickLeave(Day day, JSONObject sickLeaveData) {
        if(sickLeaveData != null) {
            if(sickLeaveData.get(SICK_LEAVE_PROTOCOL) != null) {
                day.setSickLeaveProtocolContent(sickLeaveData.get(SICK_LEAVE_PROTOCOL).toString());
            } else {
                day.setSickLeaveProtocolContent(null);
            }
        }
    }
}
