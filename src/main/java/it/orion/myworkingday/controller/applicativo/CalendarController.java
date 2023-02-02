package it.orion.myworkingday.controller.applicativo;

import it.orion.myworkingday.model.Calendar;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class CalendarController {

    public void updateCalendar() {

        Calendar.getInstance().updateDateLabel();

        int firstDayOfMonth = DayOfWeek.from(Calendar.getInstance().getCurrentDate()).getValue() - 1;

        for (int i = 0; i < 37; i++) {
            if (i < firstDayOfMonth) {
                Calendar.getInstance().setDaysVisibility(i,false);
            } else if (i < Calendar.getInstance().getCurrentDate().lengthOfMonth() + firstDayOfMonth) {
                Calendar.getInstance().setDaysVisibility(i,true);
                Calendar.getInstance().setDays(i, i - firstDayOfMonth + 1);
            } else {
                Calendar.getInstance().setDaysVisibility(i,false);
            }
        }

        updateUnderline();
    }

    public void updateSelectedDate(String type, String operation) {
        if(type.equals("month")) {
            if(operation.equals("next")) {
                Calendar.getInstance().setNextMonth();
            } else if(operation.equals("prev")) {
                Calendar.getInstance().setPrevMonth();
            }
        } else if(type.equals("year")) {
            if(operation.equals("next")) {
                Calendar.getInstance().setNextYear();
            } else if(operation.equals("prev")) {
                Calendar.getInstance().setPrevYear();
            }
        }

        updateCalendar();
    }

    public void resetCalendar() {
        Calendar.getInstance().setCurrentDate(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1));

        updateCalendar();
    }

    public void updateColor() {
        if(Calendar.getInstance().isSecondView()) {
            File file = new File(System.getenv("LOCALAPPDATA") + "/MWD", "local_db.json");

            JSONParser parser = new JSONParser();

            try (FileReader fileReader = new FileReader(file)) {

                JSONObject dayList = (JSONObject) parser.parse(fileReader);

                Calendar.getInstance().updateDateLabel();

                int firstDayOfMonth = DayOfWeek.from(Calendar.getInstance().getCurrentDate()).getValue() - 1;

                JSONObject dayData;

                for (int i = 0; i < 37; i++) {
                    if (i < Calendar.getInstance().getCurrentDate().lengthOfMonth() + firstDayOfMonth) {
                        dayData = (JSONObject) dayList.get(getDateValue(i - firstDayOfMonth + 1));

                        setColor(dayData, i);
                    }
                }
            } catch (FileNotFoundException ignored) {
                LoadDataController.createFile(file);
            } catch (IOException | ParseException ignored) {
                LoadDataController.initializeFile(file);
            }
        }
    }

    public void setColor(JSONObject dayData, int day) {
        if (dayData == null) {
            Calendar.getInstance().setDaysColor(day, "#999999");
        } else if (dayData.get(LoadDataController.DAY_TYPE) == null) {
            Calendar.getInstance().setDaysColor(day, "#111111");
        } else if (dayData.get(LoadDataController.DAY_TYPE).equals(LoadDataController.WORKING_DAY)) {
            Calendar.getInstance().setDaysColor(day, "#75c900");
        } else if (dayData.get(LoadDataController.DAY_TYPE).equals(LoadDataController.REST)) {
            Calendar.getInstance().setDaysColor(day, "#2986cc");
        } else if (dayData.get(LoadDataController.DAY_TYPE).equals(LoadDataController.SICK)) {
            Calendar.getInstance().setDaysColor(day, "#c90076");
        } else if (dayData.get(LoadDataController.DAY_TYPE).equals(LoadDataController.HOLIDAY)) {
            Calendar.getInstance().setDaysColor(day, "#6a329f");
        }
    }

    public void updateUnderline() {
        JSONParser parser = new JSONParser();

        File fileData = new File(System.getenv("LOCALAPPDATA") + "/MWD", "local_db.json");

        try (FileReader fileReader = new FileReader(fileData)) {

            Calendar.getInstance().updateDateLabel();

            JSONObject dayData;

            JSONObject dayList = (JSONObject) parser.parse(fileReader);

            int firstDayOfMonth = DayOfWeek.from(Calendar.getInstance().getCurrentDate()).getValue() - 1;

            for (int i = 0; i < 37; i++) {
                if (i < Calendar.getInstance().getCurrentDate().lengthOfMonth() + firstDayOfMonth) {
                    dayData = (JSONObject) dayList.get(getDateValue(i - firstDayOfMonth + 1));

                    Calendar.getInstance().setDaysUnderline(i, dayData != null && (dayData.get(LoadDataController.REMINDERS) != null && dayData.get(LoadDataController.REMINDERS) != ""));
                }
            }
        } catch (FileNotFoundException ignored) {
            LoadDataController.createFile(fileData);
        } catch (IOException | ParseException ignored) {
            LoadDataController.initializeFile(fileData);
        }
    }

    public static String getDateValue(int day) {
        String date = String.valueOf(Calendar.getInstance().getCurrentDate().getYear());

        if(Calendar.getInstance().getCurrentDate().getMonthValue() <= 9){
            date += "0";
        }

        date += String.valueOf(Calendar.getInstance().getCurrentDate().getMonthValue());

        if(day <= 9) {
            date += "0";
        }

        date += String.valueOf(day);

        return date;
    }
}