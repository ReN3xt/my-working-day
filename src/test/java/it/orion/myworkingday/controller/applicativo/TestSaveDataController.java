package it.orion.myworkingday.controller.applicativo;

import static org.junit.jupiter.api.Assertions.*;

import it.orion.myworkingday.model.Day;
import org.junit.jupiter.api.Test;

class TestSaveDataController {

    @Test
    public void testCalculateMinutes() {
        SaveDataController saveDataController = new SaveDataController();

        String hours = "03";
        String minutes = "24";
        int result = saveDataController.calculateMinutes(hours, minutes);

        assertEquals(204, result);
    }

    @Test
    public void testCheckNotesAndRemindersValidForm() {
        SaveDataController saveDataController = new SaveDataController();

        Day day = new Day();

        day.setNotesTextAreaContent(null);
        day.setRemindersTextAreaContent("Some Reminders");

        boolean result = saveDataController.checkNotesAndRemindersValidForm(day);

        assertTrue(result);
    }

    @Test
    public void testIsLaunchBreakInsideWorkingHours() {
        SaveDataController saveDataController = new SaveDataController();

        //Working Time: 20:30 - 6:30
        String workingHoursStartHour = "20";
        String workingHoursStartMinute = "30";
        String workingHoursEndHour = "6";
        String workingHoursEndMinute = "30";

        //Launch Break Time: 23:15 - 00:15
        String launchBreakStartHour = "23";
        String launchBreakStartMinute = "15";
        String launchBreakEndHour = "00";
        String launchBreakEndMinute = "00";

        int workingStartTime = Integer.parseInt(workingHoursStartHour + workingHoursStartMinute);
        int workingEndTime = Integer.parseInt(workingHoursEndHour + workingHoursEndMinute);

        int launchStartTime = Integer.parseInt(launchBreakStartHour + launchBreakStartMinute);
        int launchEndTime = Integer.parseInt(launchBreakEndHour + launchBreakEndMinute);

        boolean result = saveDataController.isLaunchBreakInsideWorkingHours(workingStartTime, workingEndTime, launchStartTime, launchEndTime);

        assertTrue(result);
    }
}