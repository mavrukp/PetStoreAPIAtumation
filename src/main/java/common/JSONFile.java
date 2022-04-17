package common;

import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONFile {

    String fileSeperator;
    String jsonFilepath;

    public JSONFile(String fileName) {
        this.fileSeperator = System.getProperty("file.separator");
        this.jsonFilepath = System.getProperty("user.dir")
                + fileSeperator + "src" + fileSeperator + "main" + fileSeperator + "resources" + fileSeperator + fileName + ".json";
    }

    public String readJsonBody() {
        try {
            JsonObject parser = getBodyFromFile(jsonFilepath);
            return parser.toJson();
        }catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getUserNameFromBodyFile() {
        try {
            JsonObject parser = getBodyFromFile(jsonFilepath);
            return parser.get("username").toString();
        }catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private JsonObject getBodyFromFile(String bodyFileName){
        try {
            Reader reader = Files.newBufferedReader(Paths.get(bodyFileName));
            JsonObject parser = (JsonObject) Jsoner.deserialize(reader);
            reader.close();

            return parser;
        }catch (IOException | JsonException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
