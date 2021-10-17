package main;

import java.util.ArrayList;

public class StudentList {
	private static ArrayList<Student> student = null;

	public static ArrayList<Student> getStudentList() {
		if ((student == null) || (student.size() == 0)) {

			student = new ArrayList<Student>();
		}
//		System.out.println("Form List ---->" + student);
//		for (Student st : student) {
//			System.out.println(st.getName() + st.getEmail() + st.getSubject() + st.getMessage());
//		}
		return student;
	}

	public static void setStudentList(ArrayList<Student> form) {
		student = form;
	}
}
