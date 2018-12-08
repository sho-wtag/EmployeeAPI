package JsonFileAPI.JsonFileAPI.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import JsonFileAPI.JsonFileAPI.util.Utility;

@Service
@SuppressWarnings("unchecked")
public class ApplicationService {
	
	@Autowired
	Utility util;;

	public boolean createEmplyee(Map<String,Object> emp) {
		 

	        try {
	            JSONArray jsonArray = util.readJsonFromFile();

	            JSONObject employee = new JSONObject();
	            employee.put("id", emp.get("id"));
	            employee.put("fullName", emp.get("fullName"));
	            employee.put("age", emp.get("age"));
	            employee.put("salary",emp.get("salary")); 
	            jsonArray.add(employee);
	            util.writeJsonToFile(jsonArray);
	           

	        } catch (ParseException | IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	}
	public boolean updateEmplyee(Map<String,Object> emp) {
		boolean updated = false;
		try {
		 JSONArray jsonArray = util.readJsonFromFile();
		 for(int i =0; i<jsonArray.size();i++) {
			 JSONObject employee = (JSONObject) jsonArray.get(i);
			 long jsonEmpID = (long) employee.get("id");

			 Integer id = (Integer) emp.get("id");
			 if(jsonEmpID == id) {
				 	employee.put("fullName", emp.get("fullName"));
		            employee.put("age", emp.get("age"));
		            employee.put("salary",emp.get("salary")); 
		            updated =true;
			 }
			 
		 }
		 util.writeJsonToFile(jsonArray);
		} catch (ParseException | IOException e) {
            e.printStackTrace();
            updated=false;
        }
		return updated;
	}
	public boolean deleteEmplyee(int id) {
		boolean found = false;
		try {
			int position =0;
		 JSONArray jsonArray = util.readJsonFromFile();
		 for(int i =0; i<jsonArray.size();i++) {
			 JSONObject employee = (JSONObject) jsonArray.get(i);
			 long jsonEmpID = (long) employee.get("id");

			 if(jsonEmpID == id) {
		            found =true;
		            position=i;
		            break;
			 }
			 
		 }
		 jsonArray.remove(position);
		 util.writeJsonToFile(jsonArray);
		} catch (ParseException | IOException e) {
            e.printStackTrace();
            found=false;
        }
		return found;
	}
	
	public JSONArray filterList(int val, String key,String filterOption,boolean isAsc) {
		List<JSONObject> jsonList = new ArrayList();
		JSONArray resultArray = new JSONArray();
		try {
		 JSONArray jsonArray = util.readJsonFromFile();
		 for(int i =0; i<jsonArray.size();i++) {
			 JSONObject employee = (JSONObject) jsonArray.get(i);
			 if(employee.get(key) !=null  ) {
				 long jsonVal = (long) employee.get(key);
				 switch (filterOption) {
				case "lt":
					if(jsonVal < val) {
						jsonList.add(employee);
					}
					break;
					
				case "lte":
					if(jsonVal <= val) {
						jsonList.add(employee);
					}
					break;
				case "gt":
					if(jsonVal > val) {
						jsonList.add(employee);
					}
					break;
				case "gte":
					if(jsonVal >= val) {
						jsonList.add(employee);
					}
					break;
				case "eq":
					if(val == jsonVal) {
						jsonList.add(employee);
					}
					break;
				case "neq":
					if(val != jsonVal) {
						jsonList.add(employee);
					}
					break;

				default:
					break;
				}
			 }
		 }
		 
		 //sorting
		 jsonList = sortJsonArray(jsonList, key, isAsc);
		 
		 for (int i = 0; i < jsonList.size(); i++) {
			    resultArray.add(jsonList.get(i));
			}
		 
		} catch (ParseException | IOException e) {
            e.printStackTrace();
        }
		return resultArray;
	}
	List<JSONObject> sortJsonArray(List<JSONObject> jsonList,String key,boolean asc) {
		Collections.sort( jsonList, new Comparator<JSONObject>() {

		    public int compare(JSONObject a, JSONObject b) {
		        Long valA = null;
		        Long valB = null;

		        
		        valA = (Long) a.get(key);
		        valB = (Long) b.get(key);
		        
		        if(asc)
		        	return valA.compareTo(valB);
		        else 
		        	return valB.compareTo(valA);
		    }
		});
		return jsonList;
	}
	
	
	
}
