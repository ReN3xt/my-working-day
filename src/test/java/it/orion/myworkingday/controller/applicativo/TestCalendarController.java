package it.orion.myworkingday.controller.applicativo;

import static org.junit.jupiter.api.Assertions.*;

import it.orion.myworkingday.model.Calendar;
import org.junit.jupiter.api.Test;

class TestCalendarController {

    // Person in charge: Marco Isopi
    // Test purpose: Generate a unique ID that identify a specific date in the JSON file
    @Test
    public void testGetDayValue() {
        String result = CalendarController.getDateValue(7);

        assertEquals("20230207", result);
    }
}