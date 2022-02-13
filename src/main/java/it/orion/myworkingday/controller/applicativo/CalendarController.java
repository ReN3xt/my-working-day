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

        File file = new File(System.getenv("LOCALAPPDATA") + "/MWD", "local_db.json");

        JSONParser parser = new JSONParser();

        try (FileReader fileReader = new FileReader(file)) {

            JSONObject dayList = (JSONObject) parser.parse(fileReader);

            calendar.updateDateLabel();

            int firstDayOfMonth = DayOfWeek.from(calendar.getCurrentDate()).getValue() - 1;

            JSONObject dayData;

            for (int i = 0; i < 37; i++) {
                if (i < calendar.getCurrentDate().lengthOfMonth() + firstDayOfMonth) {
                    dayData = (JSONObject) dayList.get(getDate(calendar, i - firstDayOfMonth + 1));

                    if(dayData == null) {
                        calendar.setDaysColor(i,"#000000");
                    } else if (dayData.get(LoadDataController.DAY_TYPE).equals(LoadDataController.WORKING_DAY)) {
                        calendar.setDaysColor(i,"#8fce00");
                    } else if (dayData.get(LoadDataController.DAY_TYPE).equals(LoadDataController.REST)) {
                        calendar.setDaysColor(i,"#2986cc");
                    } else if (dayData.get(LoadDataController.DAY_TYPE).equals(LoadDataController.SICK)) {
                        calendar.setDaysColor(i,"#c90076");
                    } else if (dayData.get(LoadDataController.DAY_TYPE).equals(LoadDataController.HOLIDAY)) {
                        calendar.setDaysColor(i,"#6a329f");
                    } else {
                        calendar.setDaysColor(i,"#000000");
                    }
                }
            }
        } catch (FileNotFoundException ignored) {
            LoadDataController.createFile(file);
        } catch (IOException | ParseException ignored) {
            LoadDataController.initializeFile(file);
        }
    }

    public String getDate(Calendar calendar, int day) {
        String date = calendar.getYear();

        date += calendar.getMontValue();

        if(day <= 9) {
            date += "0";
        }

        return date + day;
    }
}