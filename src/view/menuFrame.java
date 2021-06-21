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
	insertIntFrame insertIntFrame;
	
	public menuFrame() {
		menuBar = new JMenuBar();
		homeMenu = new JMenu("Home");
		logoutMenu = new JMenu("Logout");
		logoutMenu.addMenuListener(this);
		
		insertMenu = new JMenuItem("Insert Menu");
		insertMenu.addActionListener(this);
		
		manageMenu = new JMenuItem("Manage Menu");
		manageMenu.addActionListener(this);
		
		viewMenu = new JMenuItem("View Menu");
		viewMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("View Menu 2\n");
				if(viewIntFrame != null) {
					viewIntFrame.dispose();
				}
				add(viewIntFrame = new productView());
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
		// TODO Auto-generated method stub
		if(e.getSource() == insertMenu) {
			System.out.println("Insert menu\n");
			if(insertIntFrame != null) {
				insertIntFrame.dispose();
			}
			add(insertIntFrame = new insertIntFrame());
		}
		
		if(e.getSource() == manageMenu) {
			System.out.println("Manage menu\n");
		}
		if(e.getSource() == viewMenu) {
			System.out.println("View menu\n");
		}
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
