package it.orion.myworkingday.controller.applicativo;

import it.orion.myworkingday.model.Calendar;
import it.orion.myworkingday.model.Worker;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SalaryController {

    public void calculateSalary() {

        File dataFile = new File(System.getenv("LOCALAPPDATA") + "/MWD","local_db.json");

        JSONParser parser = new JSONParser();

            try(FileReader fileReader = new FileReader(dataFile)) {

                JSONObject dayList = (JSONObject) parser.parse(fileReader);

                if(checkValidMonth() && Worker.getInstance().isLoad()){

                    JSONObject dayData;

                    int dayCount = Calendar.getInstance().getCurrentDate().lengthOfMonth();

                    double totalSalary = 0;

                    for(int i = 1; i <= dayCount; i++) {

                        dayData = (JSONObject) dayList.get(CalendarController.getDateValue(i));

                        totalSalary += calculateDaySalary(dayData);
                    }

                    Calendar.getInstance().setMonthSalary("€ " +  (double) Math.round(totalSalary * 100) / 100);

                } else {
                    Calendar.getInstance().setMonthSalary("MISSING DATA");
                }
            } catch (FileNotFoundException e) {
                LoadDataController.createFile(dataFile);

                Calendar.getInstance().setMonthSalary("FILE ERROR");
            } catch (IOException | ParseException ignored) {
                LoadDataController.initializeFile(dataFile);

                Calendar.getInstance().setMonthSalary("DATA ERROR");
            }
    }

    public double calculateDaySalary(JSONObject dayData){

        double daySalary = 0;

        if(dayData.get(LoadDataController.DAY_TYPE).equals(LoadDataController.WORKING_DAY)){

            if(dayData.get(LoadDataController.WORKING_HOURS) != null) {
                daySalary += workingSalary((JSONObject) dayData.get(LoadDataController.WORKING_HOURS), Worker.getInstance().getSalaryPerHourContent());
            }

            if(dayData.get(LoadDataController.LAUNCH_BREAK) != null) {
                daySalary -= workingSalary((JSONObject) dayData.get(LoadDataController.LAUNCH_BREAK), Worker.getInstance().getSalaryPerHourContent());
            }

            if(dayData.get(LoadDataController.PERMIT) != null) {
                daySalary += permitSalary((JSONObject) dayData.get(LoadDataController.PERMIT), Worker.getInstance().getSalaryPerHourContent());
            }

            if(dayData.get(LoadDataController.OVERTIME) != null) {
                daySalary += overtimeSalary((JSONObject) dayData.get(LoadDataController.OVERTIME), Worker.getInstance().getSalaryPerHourContent(), Worker.getInstance().getOvertimeSalaryContent());
            }

        } else if(dayData.get(LoadDataController.DAY_TYPE).equals(LoadDataController.REST)) {
            daySalary += 0;
        } else if(dayData.get(LoadDataController.DAY_TYPE).equals(LoadDataController.SICK) || dayData.get(LoadDataController.DAY_TYPE).equals(LoadDataController.HOLIDAY)) {
            daySalary += extraSalary();
        }

        return daySalary;
    }

    public boolean checkValidMonth() {

        File file = new File(System.getenv("LOCALAPPDATA") + "/MWD","local_db.json");

        try {
            if(file.exists()) {
                JSONObject dayList = (JSONObject) new JSONParser().parse(new FileReader(file));

                int dayCount = Calendar.getInstance().getCurrentDate().lengthOfMonth();

                for(int i = 1; i <= dayCount; i++) {

                    if(!checkValidDay(dayList, CalendarController.getDateValue(i))){
                        return false;
                    }

                }

                return true;
            } else {
                LoadDataController.createFile(file);
                return false;
            }
        } catch (IOException | ParseException ignored) {
            LoadDataController.initializeFile(file);
            return false;
        }
    }

    public boolean checkValidDay(JSONObject dayList, String day){

        JSONObject dayData = (JSONObject) dayList.get(day);

        if(dayData == null) {
            return false;
        } else {
            return dayData.get(LoadDataController.DAY_TYPE) != null;
        }

    }

    public double workingSalary(JSONObject data, String salaryPerHour) {
        int startMinutes = calculateMinutes(data.get(LoadDataController.START_HOUR).toString(), data.get(LoadDataController.START_MINUTE).toString());
        int endMinutes = calculateMinutes(data.get(LoadDataController.END_HOUR).toString(), data.get(LoadDataController.END_MINUTE).toString());
        double salaryPerMinutes = Double.parseDouble(salaryPerHour) / 60;

        if(startMinutes <= endMinutes) {
            return (endMinutes - startMinutes) * salaryPerMinutes;
        } else {
            return (endMinutes + 1440 - startMinutes) * salaryPerMinutes;
        }
    }

    public double permitSalary(JSONObject permitData, String salaryPerHour) {
        return calculateMinutes(permitData.get(LoadDataController.HOUR).toString(), permitData.get(LoadDataController.MINUTE).toString()) * (Double.parseDouble(salaryPerHour) / 60);
    }

    public double overtimeSalary(JSONObject overtimeData, String salaryPerHour, String overtimePercent) {
        return calculateMinutes(overtimeData.get(LoadDataController.HOUR).toString(), overtimeData.get(LoadDataController.MINUTE).toString()) * ((Double.parseDouble(overtimePercent) / 100) + 1) * (Double.parseDouble(salaryPerHour) / 60);
    }

    public double extraSalary() {
        return calculateMinutes(Worker.getInstance().getDefaultWorkingHoursHSelectionModel().getSelectedItem(), Worker.getInstance().getDefaultWorkingHoursMSelectionModel().getSelectedItem()) * (Double.parseDouble(Worker.getInstance().getSalaryPerHourContent()) / 60);
    }

    public int calculateMinutes(String hours, String minutes) {
        return (Integer.parseInt(hours) * 60) + Integer.parseInt(minutes);
    }
}
