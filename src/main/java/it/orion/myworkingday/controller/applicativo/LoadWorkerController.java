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
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String WORK = "work";
    public static final String DEFAULT_HOURS = "default_hours";
    public static final String SALARY_PER_HOUR = "salary_per_hour";
    public static final String OVERTIME_PERCENT = "overtime_percent";
    public static final String HOUR = "h";
    public static final String MINUTE = "m";

    public void loadWorker() {

        File file = new File(System.getenv("LOCALAPPDATA") + "/MWD","profile.json");

        JSONParser parser = new JSONParser();

        try (FileReader fileReader = new FileReader(file)){

            JSONObject workerProfile = (JSONObject) parser.parse(fileReader);

            if(workerProfile == null || workerProfile.isEmpty()) {
                Worker.getInstance().setLoad(false);
            } else {
                fillWorkerForm(workerProfile);

                Worker.getInstance().setLoad(true);
            }
        } catch (FileNotFoundException ignored) {
            LoadDataController.createFile(file);
            Worker.getInstance().setLoad(false);
        } catch (IOException | ParseException ignored) {
            LoadDataController.initializeFile(file);
            Worker.getInstance().setLoad(false);
        }
    }

    public void fillWorkerForm(JSONObject workerProfile){
        if(workerProfile.get(FIRST_NAME) != null) {
            Worker.getInstance().setFirstNameContent(workerProfile.get(FIRST_NAME).toString());
        } else {
            Worker.getInstance().setFirstNameContent(null);
        }

        if(workerProfile.get(LAST_NAME) != null) {
            Worker.getInstance().setLastNameContent(workerProfile.get(LAST_NAME).toString());
        } else {
            Worker.getInstance().setLastNameContent(null);
        }

        if(workerProfile.get(WORK) != null) {
            Worker.getInstance().setWorkContent(workerProfile.get(WORK).toString());
        } else {
            Worker.getInstance().setWorkContent(null);
        }

        if(workerProfile.get(DEFAULT_HOURS) != null) {
            Worker.getInstance().getDefaultWorkingHoursHSelectionModel().select(((JSONObject) (workerProfile.get(DEFAULT_HOURS))).get(HOUR).toString());
            Worker.getInstance().getDefaultWorkingHoursMSelectionModel().select(((JSONObject) (workerProfile.get(DEFAULT_HOURS))).get(MINUTE).toString());
        } else {
            Worker.getInstance().getDefaultWorkingHoursHSelectionModel().clearSelection();
            Worker.getInstance().getDefaultWorkingHoursMSelectionModel().clearSelection();
        }

        if(workerProfile.get(SALARY_PER_HOUR) != null) {
            Worker.getInstance().setSalaryPerHourContent(workerProfile.get(SALARY_PER_HOUR).toString());
        } else {
            Worker.getInstance().setSalaryPerHourContent(null);
        }

        if(workerProfile.get(OVERTIME_PERCENT) != null) {
            Worker.getInstance().setOvertimeSalaryContent(workerProfile.get(OVERTIME_PERCENT).toString());
        } else {
            Worker.getInstance().setOvertimeSalaryContent(null);
        }
    }
}
