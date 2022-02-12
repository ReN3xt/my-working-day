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

    public void calculateSalary(Calendar calendar, Worker worker) {

        File dataFile = new File(System.getenv("LOCALAPPDATA") + "/MWD","local_db.json");

        JSONParser parser = new JSONParser();

            try(FileReader fileReader = new FileReader(dataFile)) {

                JSONObject dayList = (JSONObject) parser.parse(fileReader);

                if(checkValidMonth(calendar) && worker.isLoad()){

                    JSONObject dayData;

                    int dayCount = calendar.getCurrentDate().lengthOfMonth();

                    double totalSalary = 0;

                    for(int i = 1; i <= dayCount; i++) {

                        dayData = (JSONObject) dayList.get(getDayValue(calendar, i));

                        if(dayData.get(LoadDataController.DAY_TYPE).equals(LoadDataController.WORKING_DAY)){

                            if(dayData.get(LoadDataController.WORKING_HOURS) != null) {
                                totalSalary += workingSalary((JSONObject) dayData.get(LoadDataController.WORKING_HOURS), worker.getSalaryPerHourContent());
                            }

                            if(dayData.get(LoadDataController.LAUNCH_BREAK) != null) {
                                totalSalary -= workingSalary((JSONObject) dayData.get(LoadDataController.LAUNCH_BREAK), worker.getSalaryPerHourContent());
                            }

                            if(dayData.get(LoadDataController.PERMIT) != null) {
                                totalSalary += permitSalary((JSONObject) dayData.get(LoadDataController.PERMIT), worker.getSalaryPerHourContent());
                            }

                            if(dayData.get(LoadDataController.OVERTIME) != null) {
                                totalSalary += overtimeSalary((JSONObject) dayData.get(LoadDataController.OVERTIME), worker.getSalaryPerHourContent(), worker.getOvertimeSalaryContent());
                            }

                        } else if(dayData.get(LoadDataController.DAY_TYPE).equals(LoadDataController.REST)) {
                            totalSalary += 0;
                        } else if(dayData.get(LoadDataController.DAY_TYPE).equals(LoadDataController.SICK_LEAVE) || dayData.get(LoadDataController.DAY_TYPE).equals(LoadDataController.HOLIDAY)) {
                            totalSalary += extraSalary(worker);
                        }
                    }

                    calendar.setMonthSalary("€ " +  (double) Math.round(totalSalary * 100) / 100);

                } else {
                    calendar.setMonthSalary("MISSING DATA");
                }
            } catch (FileNotFoundException e) {
                LoadDataController.createFile(dataFile);

                calendar.setMonthSalary("FILE ERROR");
            } catch (IOException | ParseException ignored) {
                LoadDataController.initializeFile(dataFile);

                calendar.setMonthSalary("DATA ERROR");
            }
    }

    public boolean checkValidMonth(Calendar calendar) {

        File file = new File(System.getenv("LOCALAPPDATA") + "/MWD","local_db.json");

        try {
            if(file.exists()) {
                JSONObject dayList = (JSONObject) new JSONParser().parse(new FileReader(file));

                int dayCount = calendar.getCurrentDate().lengthOfMonth();

                for(int i = 1; i <= dayCount; i++) {

                    if(!checkValidDay(dayList, getDayValue(calendar, i))){
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

    public String getDayValue(Calendar calendar, int i) {
        String day = String.valueOf(calendar.getCurrentDate().getYear());

        if(calendar.getCurrentDate().getMonthValue() <= 9){
            day += "0";
        }

        day += String.valueOf(calendar.getCurrentDate().getMonthValue());

        if(i <= 9) {
            day += "0";
        }

        day += String.valueOf(i);

        return day;
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

    public double extraSalary(Worker worker) {
        return calculateMinutes(worker.getDefaultWorkingHoursHSelectionModel().getSelectedItem(), worker.getDefaultWorkingHoursMSelectionModel().getSelectedItem()) * (Double.parseDouble(worker.getSalaryPerHourContent()) / 60);
    }

    public int calculateMinutes(String hours, String minutes) {
        return (Integer.parseInt(hours) * 60) + Integer.parseInt(minutes);
    }
}
