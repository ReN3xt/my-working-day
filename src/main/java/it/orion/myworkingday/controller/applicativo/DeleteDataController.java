package it.orion.myworkingday.controller.applicativo;

import it.orion.myworkingday.model.Day;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class DeleteDataController {

    public void deleteData(Day day) {

        File file = new File(System.getenv("LOCALAPPDATA") + "/MWD","local_db.json");

        try {
            JSONObject dayList = (JSONObject) new JSONParser().parse(new FileReader(file));

            dayList.remove(day.getSelectedDate());

            SaveDataController.writeData(file, dayList);

            day.setLoad(false);

        }  catch (FileNotFoundException ignored) {
            LoadDataController.createFile(file);
        } catch (ParseException | IOException ignored) {
            LoadDataController.initializeFile(file);
        }
    }

}
