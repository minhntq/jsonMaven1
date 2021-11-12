package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JsonParser {
	public static List<Employee> parseJSOn(String url) throws ParseException {
		List<Employee> employeeList = new ArrayList();
		Client client = Client.create();
		WebResource webResource = client.resource(url);
		ClientResponse clientResponse = webResource.accept("application/json").get(ClientResponse.class);
		if (clientResponse.getStatus() != 200) {
			throw new RuntimeException("Failed" + clientResponse.toString());
		}
		JSONArray jsonArray = (JSONArray) new JSONParser().parse(clientResponse.getEntity(String.class));
		System.out.println(jsonArray);
		Iterator<Object> it = jsonArray.iterator();
		String name;
		int salary;
		boolean married;
		while (it.hasNext()) {
			JSONObject jsonObject = (JSONObject) it.next();
			name = (String) jsonObject.get("name");
			salary = Integer.parseInt(jsonObject.get("salary").toString());
			married = (Boolean) jsonObject.get("married");
			employeeList.add(new Employee(name, salary, married));
		}
		return employeeList;
	}

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		List<Employee> employeeList = parseJSOn("https://hccs-advancejava.s3.amazonaws.com/employee.json"); 
		for(Employee e: employeeList){ 
			System.out.println(e);
		}
	}

}
