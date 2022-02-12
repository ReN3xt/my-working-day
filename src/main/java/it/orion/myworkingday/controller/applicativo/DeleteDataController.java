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

    public void deleteData(Day day) {

        File file = new File(System.getenv("LOCALAPPDATA") + "/MWD","local_db.json");

        try(FileWriter fileWriter = new FileWriter(file)) {

            JSONObject dayList = (JSONObject) new JSONParser().parse(new FileReader(file));

            dayList.remove(day.getSelectedDate());

            fileWriter.write(dayList.toJSONString());

            fileWriter.flush();

            day.setLoad(false);
        } catch (IOException | ParseException ignored) {

        }
    }
}
