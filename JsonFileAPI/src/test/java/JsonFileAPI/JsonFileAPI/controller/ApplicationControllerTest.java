package JsonFileAPI.JsonFileAPI.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import JsonFileAPI.JsonFileAPI.service.ApplicationService;
import JsonFileAPI.JsonFileAPI.util.Utility;
@SuppressWarnings("unchecked")
public class ApplicationControllerTest {
	
	
	    @Mock
	    private ApplicationService service;
	    @InjectMocks
	    private ApplicationController controller;
	    @Before
	    public void setUp() throws Exception {
	        MockitoAnnotations.initMocks(this);
	    }
	    @Test
		public void testCreateEmployee() throws Exception{
			 Mockito.when(service.createEmplyee(Mockito.any())).thenReturn(true);		
			Map<String, Object> employeeMap = new HashMap<>();		
			employeeMap.put("id",1);
			employeeMap.put("fullName","Test User");
			employeeMap.put("age",23);
			employeeMap.put("salary",50000);
			String status= controller.createEmployee(employeeMap);
			if(!status.equals("Created Successfully")) {
				throw new Exception("Test Case Failure");
			}
		}
	    @Test
		public void testCreateEmployeeNegative() throws Exception{
			 Mockito.when(service.createEmplyee(Mockito.any())).thenReturn(false);		
			Map<String, Object> employeeMap = new HashMap<>();		
			employeeMap.put("id",1);
			employeeMap.put("fullName","Test User");
			employeeMap.put("age",23);
			employeeMap.put("salary",50000);
			String status= controller.createEmployee(employeeMap);
			if(!status.equals("Create Failure")) {
				throw new Exception("Test Case Failure");
			}
		}
	    
	    @Test
		public void testUpdateEmployee() throws Exception{
			 Mockito.when(service.updateEmplyee(Mockito.any())).thenReturn(true);		
			Map<String, Object> employeeMap = new HashMap<>();		
			employeeMap.put("id",1);
			employeeMap.put("fullName","Test User");
			employeeMap.put("age",23);
			employeeMap.put("salary",50000);
			String status= controller.updateEmployee(employeeMap);
			if(!status.equals("Updated Successfully")) {
				throw new Exception("Test Case Failure");
			}
		}
	    @Test
		public void testupdateEmployeeNegative() throws Exception{
			 Mockito.when(service.updateEmplyee(Mockito.any())).thenReturn(false);		
			Map<String, Object> employeeMap = new HashMap<>();		
			employeeMap.put("id",1);
			employeeMap.put("fullName","Test User");
			employeeMap.put("age",23);
			employeeMap.put("salary",50000);
			String status= controller.updateEmployee(employeeMap);
			if(!status.equals("Updated Failure")) {
				throw new Exception("Test Case Failure");
			}
		}
	    @Test
		public void testDeleteEmployee() throws Exception{
			 Mockito.when(service.deleteEmplyee(Mockito.anyInt())).thenReturn(true);		
			Map<String, Object> employeeMap = new HashMap<>();		
			employeeMap.put("id",1);
			employeeMap.put("fullName","Test User");
			employeeMap.put("age",23);
			employeeMap.put("salary",50000);
			String status= controller.deleteEmployee(employeeMap);
			if(!status.equals("Record Deleted")) {
				throw new Exception("Test Case Failure");
			}
		}
	    @Test
		public void testDeleteEmployeeNegative() throws Exception{
			 Mockito.when(service.deleteEmplyee(Mockito.anyInt())).thenReturn(false);		
			Map<String, Object> employeeMap = new HashMap<>();		
			employeeMap.put("id",1);
			String status= controller.deleteEmployee(employeeMap);
			if(!status.equals("record not deleted")) {
				throw new Exception("Test Case Failure");
			}
		}
	    
	  
		@Test
		public void testFilterByAge() throws Exception{
	    	JSONArray jsonArray = new JSONArray();
	    	JSONObject employee = new JSONObject();
			employee.put("id", 15L);
	        employee.put("fullName", "test user");
	        employee.put("age", 23L);
	        employee.put("salary",45000L);
	        jsonArray.add(employee);
	        employee = new JSONObject();
			employee.put("id", 16L);
	        employee.put("fullName", "test user1");
	        employee.put("age", 24L);
	        employee.put("salary",50000L);
	        jsonArray.add(employee);
			 Mockito.when(service.filterList(Mockito.anyInt(), Mockito.anyString(), Mockito.anyString(), Mockito.anyBoolean())).thenReturn(jsonArray);		
			Map<String, Object> employeeMap = new HashMap<>();		
			employeeMap.put("operator","lt");
			employeeMap.put("sort","asc");
			employeeMap.put("value",23);
			JSONArray status= controller.filterByAge(employeeMap);
			if(status.size() != jsonArray.size()) {
				throw new Exception("Test Case Failure");
			}
		}
	    @Test
		public void testFilterBySalary() throws Exception{
	    	JSONArray jsonArray = new JSONArray();
	    	JSONObject employee = new JSONObject();
			employee.put("id", 15L);
	        employee.put("fullName", "test user");
	        employee.put("age", 23L);
	        employee.put("salary",45000L);
	        jsonArray.add(employee);
	        employee = new JSONObject();
			employee.put("id", 16L);
	        employee.put("fullName", "test user1");
	        employee.put("age", 24L);
	        employee.put("salary",50000L);
	        jsonArray.add(employee);
			 Mockito.when(service.filterList(Mockito.anyInt(), Mockito.anyString(), Mockito.anyString(), Mockito.anyBoolean())).thenReturn(jsonArray);		
			Map<String, Object> employeeMap = new HashMap<>();		
			employeeMap.put("operator","lt");
			employeeMap.put("sort","desc");
			employeeMap.put("value",45000);
			JSONArray status= controller.filterBySalary(employeeMap);
			if(status.size() != jsonArray.size()) {
				throw new Exception("Test Case Failure");
			}
		}
}
