package it.orion.myworkingday.controller.applicativo;

import it.orion.myworkingday.model.Worker;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveWorkerController {

    public boolean saveWorker() {
        if(checkValidForm()) {
            File file = new File(System.getenv("LOCALAPPDATA") + "/MWD","profile.json");

            try(FileWriter fileWriter = new FileWriter(file)) {

                fileWriter.write(getWorkerJson().toJSONString());

                fileWriter.flush();
            } catch (IOException ignored) {
                Worker.getInstance().setLoad(false);

                return false;
            }

            Worker.getInstance().setLoad(true);

            return true;
        } else {
            return false;
        }
    }

    public boolean checkValidForm() {
        return checkWorkingHoursValidForm() && checkSalaryPerHourValidForm() && checkOvertimeSalaryForm();
    }

    public boolean checkWorkingHoursValidForm() {
        return !Worker.getInstance().getDefaultWorkingHoursHSelectionModel().isEmpty() && !Worker.getInstance().getDefaultWorkingHoursMSelectionModel().isEmpty();
    }

    public boolean checkSalaryPerHourValidForm() {
        if(Worker.getInstance().getSalaryPerHourContent() != null) {
            try {
                Double.parseDouble(Worker.getInstance().getSalaryPerHourContent());
                return true;
            } catch (NumberFormatException ignored) {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean checkOvertimeSalaryForm() {
        if(Worker.getInstance().getOvertimeSalaryContent() != null) {
            try {
                Integer.parseInt(Worker.getInstance().getOvertimeSalaryContent());
                return true;
            } catch (NumberFormatException ignored) {
                return false;
            }
        } else {
            return false;
        }
    }

    public JSONObject getWorkerJson (){
        JSONObject workerJson = new JSONObject();

        if(Worker.getInstance().getFirstNameContent() == null) {
            workerJson.put(LoadWorkerController.FIRST_NAME, null);
        } else {
            workerJson.put(LoadWorkerController.FIRST_NAME, Worker.getInstance().getFirstNameContent());
        }

        if(Worker.getInstance().getLastNameContent() == null) {
            workerJson.put(LoadWorkerController.LAST_NAME, null);
        } else {
            workerJson.put(LoadWorkerController.LAST_NAME, Worker.getInstance().getLastNameContent());
        }

        if(Worker.getInstance().getWorkContent() == null) {
            workerJson.put(LoadWorkerController.WORK, null);
        } else {
            workerJson.put(LoadWorkerController.WORK, Worker.getInstance().getWorkContent());
        }

        workerJson.put(LoadWorkerController.DEFAULT_HOURS, getDefaultWorkingHoursJson());
        workerJson.put(LoadWorkerController.SALARY_PER_HOUR, Worker.getInstance().getSalaryPerHourContent());
        workerJson.put(LoadWorkerController.OVERTIME_PERCENT, Worker.getInstance().getOvertimeSalaryContent());

        return workerJson;
    }

    public JSONObject getDefaultWorkingHoursJson() {
        JSONObject defaultWorkingHoursJson = new JSONObject();

        defaultWorkingHoursJson.put(LoadWorkerController.HOUR, Worker.getInstance().getDefaultWorkingHoursHSelectionModel().getSelectedItem());
        defaultWorkingHoursJson.put(LoadWorkerController.MINUTE, Worker.getInstance().getDefaultWorkingHoursMSelectionModel().getSelectedItem());

        return defaultWorkingHoursJson;
    }
}
