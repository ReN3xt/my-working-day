package it.orion.myworkingday.controller.applicativo;

import it.orion.myworkingday.model.Worker;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoadWorkerController {

    public void loadWorker(Worker worker) {

        File file = new File(System.getenv("LOCALAPPDATA") + "/MWD","profile.json");

        if(file.exists()){
            JSONParser parser = new JSONParser();

            try {

                JSONObject workerProfile = (JSONObject) parser.parse(new FileReader(file));

                if(workerProfile == null) {
                    worker.setLoad(false);
                } else {

                    if(workerProfile.get("first_name") != null) {
                        worker.setFirstNameContent(workerProfile.get("first_name").toString());
                    } else {
                        worker.setFirstNameContent(null);
                    }

                    if(workerProfile.get("last_name") != null) {
                        worker.setLastNameContent(workerProfile.get("last_name").toString());
                    } else {
                        worker.setLastNameContent(null);
                    }

                    if(workerProfile.get("work") != null) {
                        worker.setWorkContent(workerProfile.get("work").toString());
                    } else {
                        worker.setWorkContent(null);
                    }

                    if(workerProfile.get("default_hours") != null) {
                        worker.getDefaultWorkingHoursHSelectionModel().select(((JSONObject) (workerProfile.get("default_hours"))).get("h").toString());
                        worker.getDefaultWorkingHoursMSelectionModel().select(((JSONObject) (workerProfile.get("default_hours"))).get("m").toString());
                    } else {
                        worker.getDefaultWorkingHoursHSelectionModel().clearSelection();
                        worker.getDefaultWorkingHoursMSelectionModel().clearSelection();
                    }

                    if(workerProfile.get("salary_per_hour") != null) {
                        worker.setSalaryPerHourContent(workerProfile.get("salary_per_hour").toString());
                    } else {
                        worker.setSalaryPerHourContent(null);
                    }

                    if(workerProfile.get("overtime_percent") != null) {
                        worker.setOvertimeSalaryContent(workerProfile.get("overtime_percent").toString());
                    } else {
                        worker.setOvertimeSalaryContent(null);
                    }

                    worker.setLoad(true);
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }

        } else {
            LoadDataController.createFile(file);
        }


    }
}
