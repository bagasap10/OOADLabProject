package controller;

import java.util.*;

import model.Employee;
import model.Product;

public class EmployeeHandler {
	public static String errorMsg;

	public EmployeeHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public static Vector<Employee> getAllEmployees(){
		Employee emp = new Employee();
		return emp.getAllEmployees();
	}
	
	public static Employee getEmployee() {
		Employee emp = new Employee();		
		return (Employee) emp.getEmployee();
	}
	
	public static boolean insertEmployee(int positionID, String empName, String empStatus, int salary, String empUsername, String empPassword) {
		if(empName.length() < 5) {
			errorMsg = "Name must be at least 5 characters!";
			return false;
		}
		else if(positionID > 4 && positionID < 1) {
			errorMsg = "Position index is out of bounds!";
			return false;
		}
		else if(!(empStatus.equals("Single") || empStatus.equals("Married"))) {
			errorMsg = "Status has to be \"Single\" or \"Married\"";
			return false;
		}
		
		Employee emp = new Employee();
		emp.setPositionID(positionID);
		emp.setEmployeeName(empName);
		emp.setEmployeeStatus(empStatus);
		emp.setSalary(salary);
		emp.setEmployeeUsername(empUsername);
		emp.setEmployeePassword(empPassword);
		
		if(!emp.insertEmployee()) {
			errorMsg="Insert Failed!";
			return false;
		}
		return true;
	}
	
	public static boolean updateEmployee(int employeeID, int positionID, String empName, String empStatus, int salary, String empUsername, String empPassword) {
		if(empName.length() < 5) {
			errorMsg = "Name must be at least 5 characters!";
			return false;
		}
		else if(positionID > 4 && positionID < 1) {
			errorMsg = "Position index is out of bounds!";
			return false;
		}
		else if(!(empStatus.equals("Single") || empStatus.equals("Married"))) {
			errorMsg = "Status has to be \"Single\" or \"Married\"";
			return false;
		}

		
		Employee emp = new Employee();
		emp.setEmployeeID(employeeID);
		emp.setPositionID(positionID);
		emp.setEmployeeName(empName);
		emp.setEmployeeStatus(empStatus);
		emp.setSalary(salary);
		emp.setEmployeeUsername(empUsername);
		emp.setEmployeePassword(empPassword);
		
		if(!emp.updateEmployee()) {
			errorMsg="Update Failed!";
			return false;
		}
		return true;
	}
	
	public static boolean fireEmployee(int employeeID) {
		Employee emp = new Employee();
		emp.setEmployeeID(employeeID);
		
		if(!emp.fireEmployee()) {
			errorMsg="Employee has not been fired!";
			return false;
		}
		return true;
	}
	
}
