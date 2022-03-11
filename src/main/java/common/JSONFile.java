package common;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONFile {

    String fileSeperator;
    String jsonFilepath;

    public JSONFile(String fileName) {
        this.fileSeperator = System.getProperty("file.separator");
        this.jsonFilepath = System.getProperty("user.dir")
                + fileSeperator + fileName + ".json";
    }

    public String readJsonBody() {

        try {
            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get(jsonFilepath));

            // create parser
            JsonObject parser = (JsonObject) Jsoner.deserialize(reader);
            //close reader
            reader.close();
            return parser.toJson();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}