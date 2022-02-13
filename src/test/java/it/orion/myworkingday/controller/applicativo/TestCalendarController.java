package it.orion.myworkingday.controller.applicativo;

import static org.junit.jupiter.api.Assertions.*;

import it.orion.myworkingday.model.Calendar;
import org.junit.jupiter.api.Test;

class TestCalendarController {

    @Test
    public void testGetDayValue() {
        Calendar calendar = new Calendar();

        String result = CalendarController.getDateValue(calendar, 7);

        assertEquals("20220207", result);
    }
}