package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class menuFrame extends JFrame implements MenuListener, ActionListener {
	
	JMenuBar menuBar;
	JMenu homeMenu, logoutMenu;
	JMenuItem insertMenu, manageMenu, viewMenu;
	JSeparator separator1;
	productView viewIntFrame;
	
	public menuFrame() {
		menuBar = new JMenuBar();
		homeMenu = new JMenu("Home");
		logoutMenu = new JMenu("Logout");
		logoutMenu.addMenuListener(this);
		
		insertMenu = new JMenuItem("Employee Menu");
		insertMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Employee");
				dispose();
				new homeHRDView();
				
			}
		});
		
		manageMenu = new JMenuItem("Manage Menu");
		manageMenu.addActionListener(this);
		
		viewMenu = new JMenuItem("View Menu");
		viewMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new homeProductAdminView();
			}
		});
		
		separator1 = new JSeparator();
		
		
		homeMenu.add(insertMenu);
		homeMenu.add(manageMenu);
		homeMenu.add(separator1);
		homeMenu.add(viewMenu);
		
		menuBar.add(homeMenu);
		menuBar.add(logoutMenu);
		
		setJMenuBar(menuBar);
		
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Homepage");
		setSize(750,780);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {

	}
	@Override
	public void menuSelected(MenuEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==logoutMenu) {
			System.out.println("Logout menu is selected");
			
			//JOptionPane.showMessageDialog(null, "Logout");
			int result = JOptionPane.showConfirmDialog(null, "Are you sure?");
			if(result == 0) {
				this.dispose();
				registerPage rf = new registerPage();
			}
		}
	}
	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
