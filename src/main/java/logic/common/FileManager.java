package logic.common;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {

    public static JSONObject getJsonFromFile(String path){
        JSONObject jObj = null;
        try {
            jObj = new JSONObject(new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jObj;
    }
}
