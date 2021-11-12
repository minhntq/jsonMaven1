package main;

public class Employee {
	private String name;
	private int salary;
	private boolean married;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public boolean isMarried() {
		return married;
	}
	public void setMarried(boolean married) {
		this.married = married;
	}
	public Employee(String name, int salary, boolean married) {
		super();
		this.name = name;
		this.salary = salary;
		this.married = married;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + ", married=" + married + "]";
	}
	
	
}	
