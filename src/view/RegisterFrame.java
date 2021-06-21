package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.UserController;

public class RegisterFrame extends JFrame implements ActionListener{

	JPanel mainPanel, northPanel, southPanel, centerPanel, genderPanel;
	JLabel registerLabel, nameLabel, addressLabel, genderLabel, ageLabel, profileLabel, profileImageLabel;
	JTextField nameField;
	JTextArea addressArea;
	JRadioButton maleBtn, femaleBtn;
	ButtonGroup genderGrup;
	JComboBox ageBox;
	
//	ImageIcon profileIcon;
	JCheckBox tnc;
	
	//Add new field
	JLabel usernameLabel, passwordLabel, confirmPasswordLabel;
	JTextField usernameField;
	JPasswordField passwordField, confirmPasswordField;
	
	JButton registerbtn;
	
	Vector<Integer> age = new Vector<>();
	
	public RegisterFrame() {
		mainPanel = new JPanel(new BorderLayout());
		northPanel = new JPanel();
		southPanel = new JPanel();
		//Change grid Layout to 9
		centerPanel = new JPanel(new GridLayout(9, 2));
		genderPanel = new JPanel(new FlowLayout());
		genderGrup = new ButtonGroup();
		
		//NORTH PANEL
		registerLabel = new JLabel("Register");
		registerLabel.setForeground(Color.BLUE);
		registerLabel.setFont(new Font("Calibri", Font.BOLD, 25));
		northPanel.add(registerLabel);
		
		//CENTER PANEL
		nameLabel = new JLabel("Name: ");
		nameField = new JTextField();
		
		addressLabel = new JLabel("Address");
		addressArea = new JTextArea();
		
		genderLabel = new JLabel("Gender");
		
		maleBtn = new JRadioButton("Male");
		femaleBtn = new JRadioButton("Female");
		//Add kedalam Button Group
		genderGrup.add(femaleBtn);
		genderGrup.add(maleBtn);
		
		genderPanel.add(maleBtn);
		genderPanel.add(femaleBtn);
		
		ageLabel = new JLabel("Age");
		
		for (int i = 1; i < 101; i++) {
			age.add(i);
		}
		ageBox = new JComboBox(age);
		
		usernameLabel = new JLabel("Username: ");
		usernameField = new JTextField();
		
		passwordLabel = new JLabel("Password: ");
		passwordField = new JPasswordField();
		
		confirmPasswordLabel = new JLabel("Confirm Password: ");
		confirmPasswordField = new JPasswordField();
		
//		profileLabel = new JLabel("Profile");

//		//image icon harus di masukan kedalam label
//		profileIcon = new ImageIcon("IU.png");
//		profileImageLabel = new JLabel(profileIcon);
		
		tnc = new JCheckBox("Terms and Condition");
		
		
		centerPanel.add(nameLabel);
		centerPanel.add(nameField);
		//Add usernameField
		centerPanel.add(usernameLabel);
		centerPanel.add(usernameField);
		centerPanel.add(addressLabel);
		centerPanel.add(addressArea);
		centerPanel.add(genderLabel);
		centerPanel.add(genderPanel);
		centerPanel.add(ageLabel);
		centerPanel.add(ageBox);
		//Add password field
		centerPanel.add(passwordLabel);
		centerPanel.add(passwordField);
		centerPanel.add(confirmPasswordLabel);
		centerPanel.add(confirmPasswordField);
		//IMAGE
//		centerPanel.add(profileLabel);
//		centerPanel.add(profileImageLabel);
		centerPanel.add(tnc);
		
		//SOUTH
		registerbtn = new JButton("Register");
		registerbtn.addActionListener(this);
		southPanel.add(registerbtn);
		
		//SET MAIN PANEL
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		add(mainPanel);
		
		//SET JFrame
		setSize(new Dimension(600,600));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(registerbtn)) {
			String name = nameField.getText();
			String username = usernameField.getText();
			String password = String.valueOf(passwordField.getPassword());
			String address = addressArea.getText();
			String confirmsPassword = String.valueOf(confirmPasswordField.getPassword());
			
			int age = Integer.parseInt(ageBox.getSelectedItem().toString());
			String gender = null;
			if(femaleBtn.isSelected() == true) {
				gender = femaleBtn.getText();
			}else if(maleBtn.isSelected()== true ) {
				gender = maleBtn.getText();
			}
			
			
			//Coba untuk register
			try {
				UserController.getInstance().registerUser(username, name, password, address, age, gender, confirmsPassword);
				JOptionPane.showMessageDialog(null, "Registered successfully!", "Register", JOptionPane.INFORMATION_MESSAGE);
			} catch (IllegalArgumentException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}

}
