package view;

import javax.swing.*;

import controller.EmployeeHandler;
import controller.ProductHandler;
import model.Employee;
import model.Product;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class homePage extends JFrame implements ActionListener {
	
	private int pos;
	
	Container container = getContentPane();
	JLabel userLabel = new JLabel("USERNAME");
	JLabel passwordLabel = new JLabel("PASSWORD");
	JTextField userTextField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	JButton loginButton = new JButton("LOGIN");
	JButton resetButton = new JButton("RESET");
	JCheckBox showPassword = new JCheckBox("Show Password");


	public homePage() {
        setTitle("Welcome to Coffee Vibes!");
        setVisible(true);
        setBounds(10, 10, 370, 600);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
		setLayoutManager();
		setLocationAndSize();
		addComponentsToContainer();
		addActionEvent();

	}

	public void setLayoutManager() {
		container.setLayout(null);
	}

	public void setLocationAndSize() {
		userLabel.setBounds(50, 150, 100, 30);
		passwordLabel.setBounds(50, 220, 100, 30);
		userTextField.setBounds(150, 150, 150, 30);
		passwordField.setBounds(150, 220, 150, 30);
		showPassword.setBounds(150, 250, 150, 30);
		loginButton.setBounds(50, 300, 100, 30);
		resetButton.setBounds(200, 300, 100, 30);
	}

	public void addComponentsToContainer() {
		container.add(userLabel);
		container.add(passwordLabel);
		container.add(userTextField);
		container.add(passwordField);
		container.add(showPassword);
		container.add(loginButton);
		container.add(resetButton);
	}

	public void addActionEvent() {
		loginButton.addActionListener(this);
		resetButton.addActionListener(this);
		showPassword.addActionListener(this);
	}
	
	public boolean validate(String user, String pass) {
		Vector<Employee> emp = EmployeeHandler.getAllEmployees();

		for (Employee employee : emp) {
			System.out.println(employee.getEmployeeUsername() + user + employee.getEmployeePassword() + pass);
			if(employee.getEmployeeUsername().equals(user) && employee.getEmployeePassword().equals(pass)) {
				pos = employee.getPositionID();
				return true;
			}
		}
		return false;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			String user;
			char[] password;
			user = userTextField.getText();
			password = passwordField.getPassword();
			String pass = String.valueOf(password);
			
			if (validate(user, pass)) {
				dispose();
				if(pos == 1) new baristaView();
				else if(pos == 2) new homeProductAdminView();
//				else if(pos == 3) new homeManagerView();
				else new homeHRDView();
				JOptionPane.showMessageDialog(this, "Login Successful");
			} else {
				JOptionPane.showMessageDialog(this, "Invalid Username or Password");
			}

		}
		if (e.getSource() == resetButton) {
			userTextField.setText("");
			passwordField.setText("");
		}
		if (e.getSource() == showPassword) {
			if (showPassword.isSelected()) {
				passwordField.setEchoChar((char) 0);
			} else {
				passwordField.setEchoChar('*');
			}
		}
	}
}
