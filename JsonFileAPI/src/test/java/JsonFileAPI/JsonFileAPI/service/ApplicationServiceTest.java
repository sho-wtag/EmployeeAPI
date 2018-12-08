package JsonFileAPI.JsonFileAPI.service;
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

import JsonFileAPI.JsonFileAPI.util.Utility;
@SuppressWarnings("unchecked")
public class ApplicationServiceTest {
	
	 @Mock
	    private Utility utilityMock;
	    @InjectMocks
	    private ApplicationService service;
	    @Before
	    public void setUp() throws Exception {
	        MockitoAnnotations.initMocks(this);
	    }


   
	@Test
	public void testCreateEmployee() throws Exception{
		JSONArray jsonArray = new JSONArray();
		 Mockito.when(utilityMock.readJsonFromFile()).thenReturn(jsonArray);		
		Map<String, Object> employeeMap = new HashMap<>();		
		employeeMap.put("id",1);
		employeeMap.put("fullName","Test User");
		employeeMap.put("age",23);
		employeeMap.put("salary",50000);
		boolean status = service.createEmplyee(employeeMap);
		if(!status) {
			throw new Exception("Test Case Failure");
		}
	}
	
	@Test
	public void testUpdateEmployee() throws Exception{
		JSONArray jsonArray = new JSONArray();
		JSONObject employee = new JSONObject();
		employee.put("id", 15L);
        employee.put("fullName", "test user");
        employee.put("age", 23);
        employee.put("salary",45000);
        jsonArray.add(employee);
		
        Mockito.when(utilityMock.readJsonFromFile()).thenReturn(jsonArray);		
		Map<String, Object> employeeMap = new HashMap<>();		
		employeeMap.put("id",15);
		employeeMap.put("fullName","Test User");
		employeeMap.put("age",23);
		employeeMap.put("salary",50000);
		
		boolean status = service.updateEmplyee(employeeMap);
		if(!status) {
			throw new Exception("Test Case Failure");
		}
	}
	
	@Test
	public void testDeleteEmployee() throws Exception{
		JSONArray jsonArray = new JSONArray();
		JSONObject employee = new JSONObject();
		employee.put("id", 15L);
        employee.put("fullName", "test user");
        employee.put("age", 23);
        employee.put("salary",45000);
        jsonArray.add(employee);
		
        Mockito.when(utilityMock.readJsonFromFile()).thenReturn(jsonArray);		
		boolean status = service.deleteEmplyee(15);
		if(!status) {
			throw new Exception("Test Case Failure");
		}
	}
	
	@Test
	public void testFilterList() throws Exception{
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
        Mockito.when(utilityMock.readJsonFromFile()).thenReturn(jsonArray);	
		JSONArray result = service.filterList(20, "age", "gt", true);
		if(result== null) {
			throw new Exception("Test Case Failure");
		}
		System.out.println(result);
		
		result = service.filterList(45000, "salary", "gt", false);
		if(result== null) {
			throw new Exception("Test Case Failure");
		}
		System.out.println(result);
	}
}
