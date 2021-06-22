package view;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import connect.Connect;
import controller.EmployeeHandler;
import controller.PositionHandler;
import controller.ProductHandler;
import model.Employee;
import model.Position;
import model.Product;

public class employeeView extends JInternalFrame implements MouseListener, ActionListener {
		
	JPanel northPanel, southPanel, centerPanel;
	JTable table;
	DefaultTableModel dtm;
	JScrollPane scrollPane;
	JLabel employeeID, positionID, empName, empStatus, salary, empUsername, empPassword;
	Vector<Object> rowData;
	JTextField employeeIDField, positionIDField, empNameField, empStatusField, salaryField, empUsernameField, empPasswordField;
	JButton insertBtn, updateBtn, deleteBtn;
	Connect con;

	public employeeView() {
		this.setTitle("Manage Employee Menu");
		this.setVisible(true);
		this.setClosable(true);
		con = new Connect();
				
		northPanel = new JPanel();
		southPanel = new JPanel();
		centerPanel = new JPanel(new GridLayout(8,5));

		employeeID = new JLabel("employeeID");
		positionID = new JLabel("positionID");
		empName = new JLabel("empName");
		empStatus = new JLabel("empStatus");
		salary = new JLabel("Salary");
		empUsername = new JLabel("Username");
		empPassword = new JLabel("Password");

		employeeIDField = new JTextField();
		positionIDField = new JTextField();
		empNameField = new JTextField();
		empStatusField = new JTextField();
		salaryField = new JTextField();
		empUsernameField = new JTextField();
		empPasswordField = new JTextField();

		insertBtn = new JButton("Insert");
		updateBtn = new JButton("Update");
		deleteBtn = new JButton("Delete");

		table = new JTable(dtm);
		genTable();

		insertBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int positionID = Integer.parseInt(positionIDField.getText());
				String empName = empNameField.getText();
				String empStatus = empStatusField.getText();
				int salary = Integer.parseInt(salaryField.getText());
				String empUsername = empUsernameField.getText();
				String empPassword = empPasswordField.getText();


				boolean status = EmployeeHandler.insertEmployee(positionID, empName, empStatus, salary, empUsername, empPassword);
				if(status) {					
					refreshTable();	
					System.out.println("Insert Clicked");
					employeeIDField.setText("");
					positionIDField.setText("");
					empNameField.setText("");
					empStatusField.setText("");
					salaryField.setText("");
					empUsernameField.setText("");
					empPasswordField.setText("");

					JOptionPane.showMessageDialog(null, "Insert Successful");
				}
				else {
					String message = EmployeeHandler.errorMsg;
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		updateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int empID = Integer.parseInt(employeeIDField.getText());
				int positionID = Integer.parseInt(positionIDField.getText());
				String empName = empNameField.getText();
				String empStatus = empStatusField.getText();
				int salary = Integer.parseInt(salaryField.getText());
				String empUsername = empUsernameField.getText();
				String empPassword = empPasswordField.getText();


				boolean status = EmployeeHandler.updateEmployee(empID, positionID, empName, empStatus, salary, empUsername, empPassword);
				if(status) {					
					refreshTable();	
					System.out.println("Update Clicked");
					employeeIDField.setText("");
					positionIDField.setText("");
					empNameField.setText("");
					empStatusField.setText("");
					salaryField.setText("");
					empUsernameField.setText("");
					empPasswordField.setText("");

					JOptionPane.showMessageDialog(null, "Update Successful");
				}
				else {
					String message = EmployeeHandler.errorMsg;
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int empID = Integer.parseInt(employeeIDField.getText());

				boolean status = EmployeeHandler.fireEmployee(empID);
				if(status) {					
					refreshTable();	
					System.out.println("Delete Clicked");
					employeeIDField.setText("");
					positionIDField.setText("");
					empNameField.setText("");
					empStatusField.setText("");
					salaryField.setText("");
					empUsernameField.setText("");
					empPasswordField.setText("");

					JOptionPane.showMessageDialog(null, "Employee has been fired successfully!");
				}
				else {
					String message = EmployeeHandler.errorMsg;
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				employeeIDField.setText(table.getValueAt(table.getSelectedRow(),0).toString());
				positionIDField.setText(table.getValueAt(table.getSelectedRow(),1).toString());
				empNameField.setText(table.getValueAt(table.getSelectedRow(),2).toString());
				empStatusField.setText(table.getValueAt(table.getSelectedRow(),3).toString());
				salaryField.setText(table.getValueAt(table.getSelectedRow(),4).toString());
				empUsernameField.setText(table.getValueAt(table.getSelectedRow(),5).toString());
				empPasswordField.setText(table.getValueAt(table.getSelectedRow(),6).toString());

			}
		});

		scrollPane = new JScrollPane(table);


		northPanel.add(scrollPane);
		centerPanel.add(employeeID);
		centerPanel.add(employeeIDField);
		centerPanel.add(positionID);
		centerPanel.add(positionIDField);
		centerPanel.add(empName);
		centerPanel.add(empNameField);
		centerPanel.add(empStatus);
		centerPanel.add(empStatusField);
		centerPanel.add(salary);
		centerPanel.add(salaryField);
		centerPanel.add(empUsername);
		centerPanel.add(empUsernameField);
		centerPanel.add(empPassword);
		centerPanel.add(empPasswordField);

		southPanel.add(insertBtn);
		southPanel.add(updateBtn);
		southPanel.add(deleteBtn);

		this.add(northPanel,BorderLayout.NORTH);
		this.add(southPanel,BorderLayout.SOUTH);
		this.add(centerPanel,BorderLayout.CENTER);
	}

	public void genTable() {
		Object[] column = {"employeeID", "Position", "Name", "Status", "Salary", "Username", "Password"};

		dtm = new DefaultTableModel(column, 0);

		//		System.out.println("debug");
		con.rs = con.execQuery("SELECT * FROM employee");
		//		System.out.println("debug2");

		try {
			while(con.rs.next()) {
				rowData = new Vector<>();

				int empID = con.rs.getInt("employeeID");
				int positionID = con.rs.getInt("positionID");
				String empName = con.rs.getString("name");
				String empStatus = con.rs.getString("status");
				int salary = con.rs.getInt("salary");
				String empUsername = con.rs.getString("username");
				String empPassword = con.rs.getString("password");
				
				String posName = PositionHandler.getPosition(positionID);
				
				Vector<Position> pos = PositionHandler.getAllPositions();
				for (Position position : pos) {
					System.out.println(position.getPositionID());
					System.out.println(position.getName());
//					if(positionID == position.getPositionID()) {
//						posName = position.getName();
//						break;
//					}
				}

				rowData.add(empID);
				rowData.add(posName);
				rowData.add(empName);
				rowData.add(empStatus);
				rowData.add(salary);
				rowData.add(empUsername);
				rowData.add(empPassword);

				dtm.addRow(rowData);
				System.out.println(posName);
				//				System.out.println("debug3");
			}
		}
		catch(SQLException e1) {
			e1.printStackTrace();
		}
		table.setModel(dtm);
	}


	private void refreshTable() {
		Object[] column = {"employeeID", "positionID", "Name", "Status", "Salary", "Username", "Password"};

		dtm = new DefaultTableModel(column, 0);
		Vector<Employee> employees = EmployeeHandler.getAllEmployees();

		for (Employee employee : employees) {
			rowData = new Vector<>();
			rowData.add(employee.getEmployeeID());
			rowData.add(employee.getPositionID());
			rowData.add(employee.getEmployeeName());
			rowData.add(employee.getEmployeeStatus());
			rowData.add(employee.getSalary());
			rowData.add(employee.getEmployeeUsername());
			rowData.add(employee.getEmployeePassword());
			dtm.addRow(rowData);
		}

		table.setModel(dtm);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//		if(arg0.getSource() == insertBtn) {
		//			
		//		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
