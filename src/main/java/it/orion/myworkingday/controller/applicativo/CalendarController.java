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

    public void updateCalendar(Calendar calendar) {

        calendar.updateDateLabel();

        int firstDayOfMonth = DayOfWeek.from(calendar.getCurrentDate()).getValue() - 1;

        for (int i = 0; i < 37; i++) {
            if (i < firstDayOfMonth) {
                calendar.setDaysVisibility(i,false);
            } else if (i < calendar.getCurrentDate().lengthOfMonth() + firstDayOfMonth) {
                calendar.setDaysVisibility(i,true);
                calendar.setDays(i, i - firstDayOfMonth + 1);
            } else {
                calendar.setDaysVisibility(i,false);
            }
        }

        updateUnderline(calendar);
    }

    public void updateSelectedDate(Calendar calendar, String type, String operation) {
        if(type.equals("month")) {
            if(operation.equals("next")) {
                calendar.setNextMonth();
            } else if(operation.equals("prev")) {
                calendar.setPrevMonth();
            }
        } else if(type.equals("year")) {
            if(operation.equals("next")) {
                calendar.setNextYear();
            } else if(operation.equals("prev")) {
                calendar.setPrevYear();
            }
        }

        updateCalendar(calendar);
    }

    public void resetCalendar(Calendar calendar) {
        calendar.setCurrentDate(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1));

        updateCalendar(calendar);
    }

    public void updateColor(Calendar calendar) {
        if(calendar.isSecondView()) {
            File file = new File(System.getenv("LOCALAPPDATA") + "/MWD", "local_db.json");

            JSONParser parser = new JSONParser();

            try (FileReader fileReader = new FileReader(file)) {

                JSONObject dayList = (JSONObject) parser.parse(fileReader);

                calendar.updateDateLabel();

                int firstDayOfMonth = DayOfWeek.from(calendar.getCurrentDate()).getValue() - 1;

                JSONObject dayData;

                for (int i = 0; i < 37; i++) {
                    if (i < calendar.getCurrentDate().lengthOfMonth() + firstDayOfMonth) {
                        dayData = (JSONObject) dayList.get(getDateValue(calendar, i - firstDayOfMonth + 1));

                        if (dayData == null) {
                            calendar.setDaysColor(i, "#999999");
                        } else if (dayData.get(LoadDataController.DAY_TYPE) == null) {
                            calendar.setDaysColor(i, "#111111");
                        } else if (dayData.get(LoadDataController.DAY_TYPE).equals(LoadDataController.WORKING_DAY)) {
                            calendar.setDaysColor(i, "#75c900");
                        } else if (dayData.get(LoadDataController.DAY_TYPE).equals(LoadDataController.REST)) {
                            calendar.setDaysColor(i, "#2986cc");
                        } else if (dayData.get(LoadDataController.DAY_TYPE).equals(LoadDataController.SICK)) {
                            calendar.setDaysColor(i, "#c90076");
                        } else if (dayData.get(LoadDataController.DAY_TYPE).equals(LoadDataController.HOLIDAY)) {
                            calendar.setDaysColor(i, "#6a329f");
                        }
                    }
                }
            } catch (FileNotFoundException ignored) {
                LoadDataController.createFile(file);
            } catch (IOException | ParseException ignored) {
                LoadDataController.initializeFile(file);
            }
        }
    }

    public void updateUnderline(Calendar calendar) {
        JSONParser parser = new JSONParser();

        File fileData = new File(System.getenv("LOCALAPPDATA") + "/MWD", "local_db.json");

        try (FileReader fileReader = new FileReader(fileData)) {

            calendar.updateDateLabel();

            JSONObject dayData;

            JSONObject dayList = (JSONObject) parser.parse(fileReader);

            int firstDayOfMonth = DayOfWeek.from(calendar.getCurrentDate()).getValue() - 1;

            for (int i = 0; i < 37; i++) {
                if (i < calendar.getCurrentDate().lengthOfMonth() + firstDayOfMonth) {
                    dayData = (JSONObject) dayList.get(getDateValue(calendar, i - firstDayOfMonth + 1));

                    calendar.setDaysUnderline(i, dayData != null && (dayData.get(LoadDataController.REMINDERS) != null && dayData.get(LoadDataController.REMINDERS) != ""));
                }
            }
        } catch (FileNotFoundException ignored) {
            LoadDataController.createFile(fileData);
        } catch (IOException | ParseException ignored) {
            LoadDataController.initializeFile(fileData);
        }
    }

    public static String getDateValue(Calendar calendar, int day) {
        String date = String.valueOf(calendar.getCurrentDate().getYear());

        if(calendar.getCurrentDate().getMonthValue() <= 9){
            date += "0";
        }

        date += String.valueOf(calendar.getCurrentDate().getMonthValue());

        if(day <= 9) {
            date += "0";
        }

        date += String.valueOf(day);

        return date;
    }
}