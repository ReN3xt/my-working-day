package it.orion.myworkingday.controller.applicativo;

import static org.junit.jupiter.api.Assertions.*;

import it.orion.myworkingday.model.Calendar;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

class TestSalaryController {

    // Person in charge: Valerio Pernasetti
    // Test purpose: Check if a specific month is correctly filled in order to calculate the salary
    @Test
    public void checkValidMonth() {
        SalaryController salaryController = new SalaryController();

        Calendar.getInstance().setPrevMonth();

        boolean result = salaryController.checkValidMonth();

        assertTrue(result);
    }


    // Person in charge: Marco Isopi
    // Test purpose: Calculate the salary of a regular working day
    @Test
    public void workingSalary() {
        SalaryController salaryController = new SalaryController();
        JSONObject dayData = new JSONObject();

        String salaryPerHour = "8.45";

        dayData.put("start_h", "22");
        dayData.put("start_m", "30");
        dayData.put("end_h", "06");
        dayData.put("end_m", "30");

        double result = salaryController.workingSalary(dayData, salaryPerHour);

        assertEquals(67.6, result);
    }
}