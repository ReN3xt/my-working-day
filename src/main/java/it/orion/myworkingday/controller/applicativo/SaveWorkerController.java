package it.orion.myworkingday.controller.applicativo;

import it.orion.myworkingday.model.Worker;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveWorkerController {

    //Key name in JSON file
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String WORK = "work";
    private static final String DEFAULT_HOURS = "default_hours";
    private static final String SALARY_PER_HOUR = "salary_per_hour";
    private static final String OVERTIME_PERCENT = "overtime_percent";
    private static final String HOUR = "h";
    private static final String MINUTE = "m";

    public boolean saveWorker(Worker worker) {
        if(checkValidForm(worker)) {
            File file = new File(System.getenv("LOCALAPPDATA") + "/MWD","profile.json");

            try(FileWriter fileWriter = new FileWriter(file)) {

                fileWriter.write(getWorkerJson(worker).toJSONString());

                fileWriter.flush();
            } catch (IOException ignored) {
                worker.setLoad(false);

                return false;
            }

            worker.setLoad(true);

            return true;
        } else {
            return false;
        }
    }

    public boolean checkValidForm(Worker worker) {
        return checkWorkingHoursValidForm(worker) && checkSalaryPerHourValidForm(worker) && checkOvertimeSalaryForm(worker);
    }

    public boolean checkWorkingHoursValidForm(Worker worker) {
        return !worker.getDefaultWorkingHoursHSelectionModel().isEmpty() && !worker.getDefaultWorkingHoursMSelectionModel().isEmpty();
    }

    public boolean checkSalaryPerHourValidForm(Worker worker) {
        if(worker.getSalaryPerHourContent() != null) {
            try {
                Double.parseDouble(worker.getSalaryPerHourContent());
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean checkOvertimeSalaryForm(Worker worker) {
        if(worker.getOvertimeSalaryContent() != null) {
            try {
                Integer.parseInt(worker.getOvertimeSalaryContent());
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public JSONObject getWorkerJson (Worker worker){
        JSONObject workerJson = new JSONObject();

        if(worker.getFirstNameContent() == null) {
            workerJson.put(FIRST_NAME, null);
        } else {
            workerJson.put(FIRST_NAME, worker.getFirstNameContent());
        }

        if(worker.getLastNameContent() == null) {
            workerJson.put(LAST_NAME, null);
        } else {
            workerJson.put(LAST_NAME, worker.getLastNameContent());
        }

        if(worker.getWorkContent() == null) {
            workerJson.put(WORK, null);
        } else {
            workerJson.put(WORK, worker.getWorkContent());
        }
        workerJson.put(DEFAULT_HOURS, getDefaultWorkingHoursJson(worker));
        workerJson.put(SALARY_PER_HOUR, worker.getSalaryPerHourContent());
        workerJson.put(OVERTIME_PERCENT, worker.getOvertimeSalaryContent());

        return workerJson;
    }

    public JSONObject getDefaultWorkingHoursJson(Worker worker) {
        JSONObject defaultWorkingHoursJson = new JSONObject();

        defaultWorkingHoursJson.put(HOUR, worker.getDefaultWorkingHoursHSelectionModel().getSelectedItem());
        defaultWorkingHoursJson.put(MINUTE, worker.getDefaultWorkingHoursMSelectionModel().getSelectedItem());

        return defaultWorkingHoursJson;
    }
}
