package JsonFileAPI.JsonFileAPI.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
@Service
public class Utility {
	@Autowired
	Environment env;
	public JSONArray readJsonFromFile() throws FileNotFoundException, IOException, ParseException {
		 JSONParser jsonParser = new JSONParser();
		 String path= env.getProperty("filePath");
		 File file = new File(path);
		 if(!file.exists()) {
			createFIleIfNotExist(); 
		 }
		 Object obj = jsonParser.parse(new FileReader(path));
        JSONArray jsonArray = (JSONArray)obj;
        
        return jsonArray;
	}
	
	public void writeJsonToFile(JSONArray jsonArray) throws IOException {
		 String path= env.getProperty("filePath");
		 FileWriter file = new FileWriter(path);
        file.write(jsonArray.toJSONString());
        file.flush();
        file.close();
	}
	public boolean createFIleIfNotExist() throws IOException {
		 String path= env.getProperty("filePath");
		 String dirPath = path.substring(0, path.lastIndexOf("\\"));
		 File dir = new File(dirPath);
		 Files.createDirectory(dir.toPath());
		 File file = new File(path);
		 FileWriter fileWriter = new FileWriter(path);
		 fileWriter.write("[]");
		 fileWriter.flush();
		 fileWriter.close();
		 return true;
		 
		 
	}
}
