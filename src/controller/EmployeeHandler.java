package controller;

import java.util.*;

import model.Employee;

public class EmployeeHandler {

	public EmployeeHandler() {
		
	}
	
	public static String errorMessage
	
	public static List<Employee> getAllEmployees(){
		Employee employee = new Employee();
		return employee.getAll();
	}
	
	public static boolean insertEmployee(String) {
		if(employeeID.isEmpty()) {
			errorMassage = "ID cannot be empty!";
			return false;
		}else if(employeeName.length()<5 || !Character.isUpperCase(employeeName.charAt(0))) {
			return false;
		}
		
		Employee employee = new Employee();
		employee.setEmployeeID(employeeID);
		
		return employee.insert();
	}
}