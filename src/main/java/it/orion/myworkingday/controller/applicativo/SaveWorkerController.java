package it.orion.myworkingday.controller.applicativo;

import it.orion.myworkingday.model.Worker;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveWorkerController {

    public boolean saveWorker(Worker worker) {
        if(checkValidForm(worker)) {
            try {
                File file = new File(System.getenv("LOCALAPPDATA") + "/MWD","profile.json");

                FileWriter fileWriter = new FileWriter(file);

                fileWriter.write(getWorkerJson(worker).toJSONString());

                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();

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
            workerJson.put("first_name", null);
        } else {
            workerJson.put("first_name", worker.getFirstNameContent());
        }

        if(worker.getLastNameContent() == null) {
            workerJson.put("last_name", null);
        } else {
            workerJson.put("last_name", worker.getLastNameContent());
        }

        if(worker.getWorkContent() == null) {
            workerJson.put("work", null);
        } else {
            workerJson.put("work", worker.getWorkContent());
        }
        workerJson.put("default_hours", getDefaultWorkingHoursJson(worker));
        workerJson.put("salary_per_hour", worker.getSalaryPerHourContent());
        workerJson.put("overtime_percent", worker.getOvertimeSalaryContent());

        return workerJson;
    }

    public JSONObject getDefaultWorkingHoursJson(Worker worker) {
        JSONObject defaultWorkingHoursJson = new JSONObject();

        defaultWorkingHoursJson.put("h", worker.getDefaultWorkingHoursHSelectionModel().getSelectedItem());
        defaultWorkingHoursJson.put("m", worker.getDefaultWorkingHoursMSelectionModel().getSelectedItem());

        return defaultWorkingHoursJson;
    }
}
