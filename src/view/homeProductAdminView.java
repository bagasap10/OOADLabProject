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

import controller.ProductHandler;
import controller.VoucherHandler;

public class homeProductAdminView extends JFrame implements MenuListener, ActionListener {
	JMenuBar menuBar;
	JMenu homeMenu, logoutMenu;
	JMenuItem productsMenu, vouchersMenu;
	productView prodView = null;
	voucherView vouchView = null;
	
	public homeProductAdminView() {
		menuBar = new JMenuBar();
		homeMenu = new JMenu("Home");
		logoutMenu = new JMenu("Logout");
		
		vouchersMenu = new JMenuItem("Manage Vouchers");
		vouchersMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(vouchView != null) {
					vouchView.dispose();
				}
				System.out.println("Vouchers");
				add(vouchView = VoucherHandler.viewVoucherManagementForm());
			}
		});
		
		productsMenu = new JMenuItem("Manage Products");
		productsMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(prodView != null) {
					prodView.dispose();
				}
				System.out.println("Employee");
				add(prodView = ProductHandler.viewProductManagementForm());
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

		homeMenu.add(productsMenu);
		homeMenu.add(vouchersMenu);
		menuBar.add(homeMenu);
		menuBar.add(logoutMenu);

		setJMenuBar(menuBar);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Human Resource Department Dashboard");
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
