package model;

import java.util.*;

import connect.Connect;

import java.sql.*;

public class Employee {
	
	private static Connect con = new Connect();
	
	//Instance Fields
	private int employeeID;
	private int positionID;
	private String empName;
	private String empStatus;
	private int salary;
	private String empUsername;
	private String empPassword;
	

	public Employee(int employeeID, int positionID, String empName, String empStatus, int salary, String empUsername, String empPassword) {
		super();
		this.employeeID = employeeID;
		this.positionID = positionID;
		this.empName = empName;
		this.empStatus = empStatus;
		this.salary = salary;
		this.empUsername = empUsername;
		this.empPassword = empPassword;
	}

	public Employee() {
		// TODO Auto-generated constructor stub

	}
	
	public Vector<Employee> getAllEmployees(){
		Vector<Employee> emps = new Vector<>();
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("SELECT * FROM employee");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int empid = rs.getInt("employeeID");
				int posid = rs.getInt("positionID");
				String name = rs.getString("empName");
				String status = rs.getString("empStatus");
				int salary = rs.getInt("salary");
				String empuser = rs.getString("empUsername");
				String emppass = rs.getString("empPassword");
				
				Employee emp = new Employee(empid, posid, name, status, salary, empuser, emppass);
				emps.add(emp);
			}
			return emps;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Employee getEmployee() {
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("SELECT * FROM product WHERE productID = ?");
			ps.setInt(1, employeeID);

			Employee emp = new Employee();
			return emp;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean insertEmployee() {
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("INSERT INTO employee VALUES(null,?,?,?,?,?,?)");
			ps.setInt(1, positionID);
			ps.setString(2, empName);
			ps.setString(3, empStatus);
			ps.setInt(4, salary);
			ps.setString(5, empUsername);
			ps.setString(6, empPassword);
			
			

			return ps.executeUpdate() == 1;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	public boolean updateEmployee() {
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("UPDATE employee SET positionID = ?, name= ?, status = ?, salary= ?, username = ?, password = ? WHERE employeeID = ?");
			ps.setInt(1, positionID);
			ps.setString(2, empName);
			ps.setString(3, empStatus);
			ps.setInt(4, salary);
			ps.setString(5, empUsername);
			ps.setString(6, empPassword);
			ps.setInt(7, employeeID);

			return ps.executeUpdate() == 1;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	public boolean fireEmployee() {
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("DELETE FROM employee WHERE employeeID = ?");
			ps.setInt(1,employeeID);

			return ps.executeUpdate() == 1;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}
	
	
	
	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getEmployeeName() {
		return empName;
	}

	public void setEmployeeName(String empName) {
		this.empName = empName;
	}

	public String getEmployeeUsername() {
		return empUsername;
	}

	public void setEmployeeUsername(String empUsername) {
		this.empUsername = empUsername;
	}

	public String getEmployeeStatus() {
		return empStatus;
	}

	public void setEmployeeStatus(String empStatus) {
		this.empStatus = empStatus;
	}

	public String getEmployeePassword() {
		return empPassword;
	}

	public void setEmployeePassword(String empPassword) {
		this.empPassword = empPassword;
	}

	public int getPositionID() {
		return positionID;
	}

	public void setPositionID(int positionID) {
		this.positionID = positionID;
	}
}
