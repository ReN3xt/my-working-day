package it.orion.myworkingday.controller.applicativo;

import it.orion.myworkingday.model.Worker;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoadWorkerController {

    //Key name in JSON file
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String WORK = "work";
    private static final String DEFAULT_HOURS = "default_hours";
    private static final String SALARY_PER_HOUR = "salary_per_hour";
    private static final String OVERTIME_PERCENT = "overtime_percent";
    private static final String HOUR = "h";
    private static final String MINUTE = "m";

    public void loadWorker(Worker worker) {

        File file = new File(System.getenv("LOCALAPPDATA") + "/MWD","profile.json");

        if(file.exists()){
            JSONParser parser = new JSONParser();

            try {

                JSONObject workerProfile = (JSONObject) parser.parse(new FileReader(file));

                if(workerProfile == null) {
                    worker.setLoad(false);
                } else {

                    if(workerProfile.get(FIRST_NAME) != null) {
                        worker.setFirstNameContent(workerProfile.get(FIRST_NAME).toString());
                    } else {
                        worker.setFirstNameContent(null);
                    }

                    if(workerProfile.get(LAST_NAME) != null) {
                        worker.setLastNameContent(workerProfile.get(LAST_NAME).toString());
                    } else {
                        worker.setLastNameContent(null);
                    }

                    if(workerProfile.get(WORK) != null) {
                        worker.setWorkContent(workerProfile.get(WORK).toString());
                    } else {
                        worker.setWorkContent(null);
                    }

                    if(workerProfile.get(DEFAULT_HOURS) != null) {
                        worker.getDefaultWorkingHoursHSelectionModel().select(((JSONObject) (workerProfile.get(DEFAULT_HOURS))).get(HOUR).toString());
                        worker.getDefaultWorkingHoursMSelectionModel().select(((JSONObject) (workerProfile.get(DEFAULT_HOURS))).get(MINUTE).toString());
                    } else {
                        worker.getDefaultWorkingHoursHSelectionModel().clearSelection();
                        worker.getDefaultWorkingHoursMSelectionModel().clearSelection();
                    }

                    if(workerProfile.get(SALARY_PER_HOUR) != null) {
                        worker.setSalaryPerHourContent(workerProfile.get(SALARY_PER_HOUR).toString());
                    } else {
                        worker.setSalaryPerHourContent(null);
                    }

                    if(workerProfile.get(OVERTIME_PERCENT) != null) {
                        worker.setOvertimeSalaryContent(workerProfile.get(OVERTIME_PERCENT).toString());
                    } else {
                        worker.setOvertimeSalaryContent(null);
                    }

                    worker.setLoad(true);
                }
            } catch (FileNotFoundException ignored) {
                LoadDataController.createFile(file);
            } catch (IOException | ParseException ignored) {

            }

        } else {
            LoadDataController.createFile(file);
        }


    }
}
