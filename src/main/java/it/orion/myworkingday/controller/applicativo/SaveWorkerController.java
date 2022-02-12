package it.orion.myworkingday.controller.applicativo;

import it.orion.myworkingday.model.Worker;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveWorkerController {

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
            workerJson.put(LoadWorkerController.FIRST_NAME, null);
        } else {
            workerJson.put(LoadWorkerController.FIRST_NAME, worker.getFirstNameContent());
        }

        if(worker.getLastNameContent() == null) {
            workerJson.put(LoadWorkerController.LAST_NAME, null);
        } else {
            workerJson.put(LoadWorkerController.LAST_NAME, worker.getLastNameContent());
        }

        if(worker.getWorkContent() == null) {
            workerJson.put(LoadWorkerController.WORK, null);
        } else {
            workerJson.put(LoadWorkerController.WORK, worker.getWorkContent());
        }
        workerJson.put(LoadWorkerController.DEFAULT_HOURS, getDefaultWorkingHoursJson(worker));
        workerJson.put(LoadWorkerController.SALARY_PER_HOUR, worker.getSalaryPerHourContent());
        workerJson.put(LoadWorkerController.OVERTIME_PERCENT, worker.getOvertimeSalaryContent());

        return workerJson;
    }

    public JSONObject getDefaultWorkingHoursJson(Worker worker) {
        JSONObject defaultWorkingHoursJson = new JSONObject();

        defaultWorkingHoursJson.put(LoadWorkerController.HOUR, worker.getDefaultWorkingHoursHSelectionModel().getSelectedItem());
        defaultWorkingHoursJson.put(LoadWorkerController.MINUTE, worker.getDefaultWorkingHoursMSelectionModel().getSelectedItem());

        return defaultWorkingHoursJson;
    }
}
