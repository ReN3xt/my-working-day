package it.orion.myworkingday.controller.applicativo;

import it.orion.myworkingday.model.Day;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DeleteDataController {

    public DeleteDataController() {

    }

    public void deleteData(Day day) {
        try {
            File file = new File(System.getenv("LOCALAPPDATA") + "/MWD","local_db.json");

            JSONObject dayList = (JSONObject) new JSONParser().parse(new FileReader(file));

            dayList.remove(day.getSelectedDate());

            FileWriter fileWriter = new FileWriter(file);

            fileWriter.write(dayList.toJSONString());

            fileWriter.flush();
            fileWriter.close();

            day.setLoad(false);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
