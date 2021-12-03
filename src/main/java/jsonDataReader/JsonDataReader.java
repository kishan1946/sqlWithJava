package jsonDataReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonDataReader {
    JSONObject jsonObj;
    public JSONObject readJson(String fileName, String packageName,String objectName) {
        try {
            JSONParser jsonParser = new JSONParser();
            String fileLocation = String.format("./src/main/java/%s/%s.json", packageName, fileName);
            FileReader reader = new FileReader(fileLocation);
            Object obj = jsonParser.parse(reader);
            jsonObj = (JSONObject) obj;
            if (objectName != null) {
                jsonObj = (JSONObject) jsonObj.get(objectName);
            }
            return jsonObj;
        }catch (IOException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }
        return jsonObj;
    }
}
