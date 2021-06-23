package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import controller.CartHandler;
import controller.ProductHandler;
import controller.TransactionHandler;
import controller.VoucherHandler;

public class baristaView extends JFrame implements MenuListener, ActionListener {
	JMenuBar menuBar;
	JMenu homeMenu, logoutMenu;
	JMenuItem cartMenu, vouchersMenu, transMenu;
	checkoutView checkView = null;

	
	public baristaView() {
		menuBar = new JMenuBar();
		homeMenu = new JMenu("Home");
		logoutMenu = new JMenu("Logout");
		
		cartMenu = new JMenuItem("Manage Cart");
		cartMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(checkView != null) {
					checkView.dispose();
				}
				System.out.println("Vouchers");
				add(checkView = CartHandler.viewCheckOutForm());
			}
		});
		
		logoutMenu.addMenuListener(new MenuListener() {
			
			@Override
			public void menuSelected(MenuEvent e) {
				int res = JOptionPane.showConfirmDialog(null, "Are you sure?");
				if(res == 0) {
					dispose();
					new homePage();
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
		});

		homeMenu.add(cartMenu);
		menuBar.add(homeMenu);
		menuBar.add(logoutMenu);

		setJMenuBar(menuBar);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Barista Dashboard");
		setSize(1280,720);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void menuCanceled(MenuEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void menuDeselected(MenuEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void menuSelected(MenuEvent arg0) {
		// TODO Auto-generated method stub

	}

}
