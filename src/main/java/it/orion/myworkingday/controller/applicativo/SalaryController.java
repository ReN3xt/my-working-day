package it.orion.myworkingday.controller.applicativo;

import it.orion.myworkingday.model.Calendar;
import it.orion.myworkingday.model.Worker;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SalaryController {

    public void calculateSalary(Calendar calendar, Worker worker) {
        if(checkValidMonth(calendar) && worker.isLoad()){
            try {
                File dataFile = new File(System.getenv("LOCALAPPDATA") + "/MWD","local_db.json");

                JSONObject dayList = (JSONObject) new JSONParser().parse(new FileReader(dataFile));

                JSONObject dayData;

                int dayCount = calendar.getCurrentDate().lengthOfMonth();

                double totalSalary = 0;

                for(int i = 1; i <= dayCount; i++) {

                    dayData = (JSONObject) dayList.get(getDayValue(calendar, i));

                    if(dayData.get("day_type").equals("working")){

                        if(dayData.get("working_hours") != null) {
                            totalSalary += workingSalary((JSONObject) dayData.get("working_hours"), worker.getSalaryPerHourContent());
                        }

                        if(dayData.get("launch_break") != null) {
                            totalSalary -= workingSalary((JSONObject) dayData.get("launch_break"), worker.getSalaryPerHourContent());
                        }

                        if(dayData.get("permit") != null) {
                            totalSalary += permitSalary((JSONObject) dayData.get("permit"), worker.getSalaryPerHourContent());
                        }

                        if(dayData.get("overtime") != null) {
                            totalSalary += overtimeSalary((JSONObject) dayData.get("overtime"), worker.getSalaryPerHourContent(), worker.getOvertimeSalaryContent());
                        }

                    } else if(dayData.get("day_type").equals("rest")) {
                        totalSalary += 0;
                    } else if(dayData.get("day_type").equals("sick") || dayData.get("day_type").equals("holiday")) {
                        totalSalary += extraSalary(worker);
                    }
                }

                calendar.setMonthSalary("€ " +  (double) Math.round(totalSalary * 100) / 100);

            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        } else {
            calendar.setMonthSalary("ERROR");
        }
    }

    public boolean checkValidMonth(Calendar calendar) {
        try {
            File file = new File(System.getenv("LOCALAPPDATA") + "/MWD","local_db.json");

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
              return false;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return false;
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
            return dayData.get("day_type") != null;
        }

    }

    public double workingSalary(JSONObject data, String salaryPerHour) {
        int startMinutes = calculateMinutes(data.get("start_h").toString(), data.get("start_m").toString());
        int endMinutes = calculateMinutes(data.get("end_h").toString(), data.get("end_m").toString());
        double salaryPerMinutes = Double.parseDouble(salaryPerHour) / 60;

        if(startMinutes <= endMinutes) {
            return (endMinutes - startMinutes) * salaryPerMinutes;
        } else {
            return (endMinutes + 1440 - startMinutes) * salaryPerMinutes;
        }
    }

    public double permitSalary(JSONObject permitData, String salaryPerHour) {
        return calculateMinutes(permitData.get("h").toString(), permitData.get("m").toString()) * (Double.parseDouble(salaryPerHour) / 60);
    }

    public double overtimeSalary(JSONObject overtimeData, String salaryPerHour, String overtimePercent) {
        return calculateMinutes(overtimeData.get("h").toString(), overtimeData.get("m").toString()) * ((Double.parseDouble(overtimePercent) / 100) + 1) * (Double.parseDouble(salaryPerHour) / 60);
    }

    public double extraSalary(Worker worker) {
        return calculateMinutes(worker.getDefaultWorkingHoursHSelectionModel().getSelectedItem(), worker.getDefaultWorkingHoursMSelectionModel().getSelectedItem()) * (Double.parseDouble(worker.getSalaryPerHourContent()) / 60);
    }

    public int calculateMinutes(String hours, String minutes) {
        return (Integer.parseInt(hours) * 60) + Integer.parseInt(minutes);
    }
}
