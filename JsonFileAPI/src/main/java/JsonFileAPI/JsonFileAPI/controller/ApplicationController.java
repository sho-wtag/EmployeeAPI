package JsonFileAPI.JsonFileAPI.controller;

import java.util.Map;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import JsonFileAPI.JsonFileAPI.service.ApplicationService;

@RestController
public class ApplicationController {
	@Autowired
	ApplicationService appService;
	
	 @RequestMapping(value = "/createEmployee", method = RequestMethod.POST)
	    public String createEmployee(@RequestBody Map<String,Object> employee) {
		 if(appService.createEmplyee(employee)) {
			 return "Created Successfully";
		 }
		 return "Create Failure";
	    }
	 
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
	    public String updateEmployee(@RequestBody Map<String,Object> employee) {
	        if(appService.updateEmplyee(employee)) {
	        	return "Updated Successfully";
	        }
	       return "Updated Failure";
	    }
	 
	 @RequestMapping(value = "/deleteEmployee", method = RequestMethod.POST)
	    public String deleteEmployee(@RequestBody Map<String,Object> employee) {
	       if(appService.deleteEmplyee((int) employee.get("id")))
	            return "Record Deleted";
	       return "record not deleted";
	    }
	 @RequestMapping(value = "/filterByAge", method = RequestMethod.POST)
	    public JSONArray filterByAge(@RequestBody Map<String,Object> employee) {
		 	String operator = (String) employee.get("operator");
		 	String sort = (String) employee.get("sort");
		 	Integer value = (Integer) employee.get("value");
		 	return  appService.filterList(value, "age", operator, sort.equalsIgnoreCase("asc") ? true:false);
	    }
	 @RequestMapping(value = "/filterBySalary", method = RequestMethod.POST)
	    public JSONArray filterBySalary(@RequestBody Map<String,Object> employee) {
		 String operator = (String) employee.get("operator");
		 	String sort = (String) employee.get("sort");
		 	Integer value = (Integer) employee.get("value");
		 	return  appService.filterList(value, "salary", operator, sort.equalsIgnoreCase("asc") ? true:false);
	    }
	 
	 
}
