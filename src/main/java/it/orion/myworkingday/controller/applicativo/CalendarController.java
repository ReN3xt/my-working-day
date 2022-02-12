package it.orion.myworkingday.controller.applicativo;

import it.orion.myworkingday.model.Calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class CalendarController {

    public void updateCalendar(Calendar calendar) {
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

        calendar.updateDateLabel();
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
}