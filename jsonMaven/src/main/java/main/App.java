package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class App {
	static ArrayList<Student> students = new ArrayList<Student>();
	public static ArrayList<Student> parseJSOn(String url) throws ParseException {
        Client client =  Client.create();
        WebResource webResource = client.resource(url);

        ClientResponse clientResponse  = webResource.accept("application/json").get(ClientResponse.class);
        if(clientResponse.getStatus() != 200){
            throw new RuntimeException("Failed" + clientResponse);
        }

        JSONArray jsonArray = (JSONArray) new JSONParser().parse(clientResponse.getEntity(String.class));

        Iterator<Object> it = jsonArray.iterator();
        
        while (it.hasNext()){ 
            JSONObject jsonObject = (JSONObject) it.next();
            int id          = Integer.parseInt(String.valueOf(jsonObject.get("id")));
            String name     = String.valueOf(jsonObject.get("first_name"));
            String gender   = String.valueOf(jsonObject.get("gender"));
            String mail     = String.valueOf(jsonObject.get("email"));
            String gpa      = String.valueOf(jsonObject.get("gpa"));

            Student student = new Student(id,name,gpa,mail,gender);
            //list.addStudent(student);
            students.add(student);
        }
        return students;
    }
	
	public static void search(String name) {

		ArrayList<Student> result = new ArrayList<Student>();
		for (Student stu : students) {
			if (stu.getFirstName().contains(name)) {
				result.add(stu);
			}
			else if (stu.getGender().contains(name)) {
				result.add(stu);
			}
		}
		if (result.toString() != "[]") 
			System.out.println("Here's the you've been searching for: " + result.toString());
		else
			System.out.println("Sorry! Your model might not be available this time.");
	}

    public static void main(String[] args) throws ParseException {
        StudentList.setStudentList(parseJSOn("https://hccs-advancejava.s3.amazonaws.com/student.json"));
        System.out.println(students.toString());
        search("Aida");
    }

}
