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
    public static final String SICK = "sick";
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
    public static final String SICK_LEAVE = "sick_leave";
    public static final String SICK_LEAVE_PROTOCOL = "protocol";
    public static final String HOUR = "h";
    public static final String MINUTE = "m";
    public static final String NOTES = "notes";
    public static final String REMINDERS = "reminders";

    public void loadData() {

        File file = new File(System.getenv("LOCALAPPDATA") + "/MWD","local_db.json");

        JSONParser parser = new JSONParser();

        try (FileReader fileReader = new FileReader(file)) {

            JSONObject dayList = (JSONObject) parser.parse(fileReader);

            JSONObject dayData = (JSONObject) dayList.get(Day.getInstance().getSelectedDate());


            if(dayData == null){
                Day.getInstance().setDeleteButtonDisable(true);
                Day.getInstance().setLoad(false);
            } else {
                loadDayType(dayData);

                if(dayData.get(NOTES) != null) {
                    Day.getInstance().setNotesTextAreaContent(dayData.get(NOTES).toString());
                } else {
                    Day.getInstance().setNotesTextAreaContent(null);
                }

                if(dayData.get(REMINDERS) != null) {
                    Day.getInstance().setRemindersTextAreaContent(dayData.get(REMINDERS).toString());
                } else {
                    Day.getInstance().setRemindersTextAreaContent(null);
                }

                Day.getInstance().setDeleteButtonDisable(false);
                Day.getInstance().setLoad(true);
            }

        } catch (FileNotFoundException ignored) {
            createFile(file);
            Day.getInstance().setLoad(false);
        } catch (IOException | ParseException ignored) {
            initializeFile(file);
            Day.getInstance().setLoad(false);
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

    public void loadDayType(JSONObject dayData) {
        if(dayData.get(DAY_TYPE) != null) {
            if (dayData.get(DAY_TYPE).equals(WORKING_DAY)) {
                Day.getInstance().setWorkingDayButtonSelect(true);
                loadWorkingHours((JSONObject) dayData.get(WORKING_HOURS));
                loadLaunchBreak((JSONObject) dayData.get(LAUNCH_BREAK));
                loadOvertime((JSONObject) dayData.get(OVERTIME));
                loadPermit((JSONObject) dayData.get(PERMIT));
            } else if (dayData.get(DAY_TYPE).equals(REST)) {
                Day.getInstance().setRestButtonSelect(true);
            } else if (dayData.get(DAY_TYPE).equals(SICK)) {
                Day.getInstance().setSickLeaveButtonSelect(true);
                loadSickLeave((JSONObject) dayData.get(SICK_LEAVE));
            } else if (dayData.get(DAY_TYPE).equals(HOLIDAY)) {
                Day.getInstance().setHolidayButtonSelect(true);
            }
        }

    }

    public void loadWorkingHours(JSONObject workingHoursData) {
        if(workingHoursData != null) {
            Day.getInstance().getWorkingHoursStartHSelectionModel().select(workingHoursData.get(START_HOUR).toString());
            Day.getInstance().getWorkingHoursStartMSelectionModel().select(workingHoursData.get(START_MINUTE).toString());
            Day.getInstance().getWorkingHoursEndHSelectionModel().select(workingHoursData.get(END_HOUR).toString());
            Day.getInstance().getWorkingHoursEndMSelectionModel().select(workingHoursData.get(END_MINUTE).toString());
        }
    }

    public void loadLaunchBreak(JSONObject launchBreakData) {
        if(launchBreakData != null) {
            Day.getInstance().setLaunchBreakSelect(true);
            Day.getInstance().getLaunchBreakStartHSelectionModel().select(launchBreakData.get(START_HOUR).toString());
            Day.getInstance().getLaunchBreakStartMSelectionModel().select(launchBreakData.get(START_MINUTE).toString());
            Day.getInstance().getLaunchBreakEndHSelectionModel().select(launchBreakData.get(END_HOUR).toString());
            Day.getInstance().getLaunchBreakEndMSelectionModel().select(launchBreakData.get(END_MINUTE).toString());
        }
    }

    public void loadOvertime(JSONObject overtimeData) {
        if(overtimeData != null) {
            Day.getInstance().setOvertimeSelect(true);
            Day.getInstance().getOvertimeHSelectionModel().select(overtimeData.get(HOUR).toString());
            Day.getInstance().getOvertimeMSelectionModel().select(overtimeData.get(MINUTE).toString());
        }
    }

    public void loadPermit(JSONObject permitData) {
        if(permitData != null) {
            Day.getInstance().setPermitSelect(true);
            Day.getInstance().getPermitHSelectionModel().select(permitData.get(HOUR).toString());
            Day.getInstance().getPermitMSelectionModel().select(permitData.get(MINUTE).toString());

            if(permitData.get(PERMIT_REASON) != null) {
                Day.getInstance().setPermitReasonContent(permitData.get(PERMIT_REASON).toString());
            } else {
                Day.getInstance().setPermitReasonContent(null);
            }
        }
    }

    public void loadSickLeave(JSONObject sickLeaveData) {
        if(sickLeaveData != null) {
            if(sickLeaveData.get(SICK_LEAVE_PROTOCOL) != null) {
                Day.getInstance().setSickLeaveProtocolContent(sickLeaveData.get(SICK_LEAVE_PROTOCOL).toString());
            } else {
                Day.getInstance().setSickLeaveProtocolContent(null);
            }
        }
    }
}
