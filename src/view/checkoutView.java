package view;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import connect.Connect;
import controller.CartHandler;
import controller.EmployeeHandler;
import controller.ProductHandler;
import model.CartItem;
import model.Employee;
import model.Product;

public class checkoutView extends JInternalFrame implements MouseListener, ActionListener {

	JPanel northPanel, southPanel, centerPanel;
	JTable table;
	DefaultTableModel dtm;
	JScrollPane scrollPane;
	JLabel id, name, desc, price, stock;
	Vector<Object> rowData;
	JTextField idField, nameField, descField, priceField, stockField;
	JButton insertBtn, updateBtn, deleteBtn, refreshBtn;
	Connect con;

	public checkoutView() {

		con = new Connect();
		northPanel = new JPanel();
		southPanel = new JPanel();
		centerPanel = new JPanel(new GridLayout(8,2));

		id = new JLabel("ID");
		name = new JLabel("Name");
		desc = new JLabel("Description");
		price = new JLabel("Price");
		stock = new JLabel("Stock");

		idField = new JTextField();
		nameField = new JTextField();
		descField = new JTextField();
		priceField = new JTextField();
		stockField = new JTextField();

		insertBtn = new JButton("Insert");
		updateBtn = new JButton("Update");
		deleteBtn = new JButton("Delete");
		refreshBtn = new JButton("Refresh");

		table = new JTable(dtm);
		genTable();

		refreshBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				refreshTable();
			}

		});

		insertBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CartHandler.viewAddProductToCartForm();
			}
		});

		updateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(idField.getText());
				String name = nameField.getText();
				String description = descField.getText();
				int price = Integer.parseInt(priceField.getText());
				int stock = Integer.parseInt(stockField.getText());

				boolean status = ProductHandler.updateProduct(id, name, description, price);
				boolean status2 = ProductHandler.updateProductStock(id,stock);
				if(status && status2) {					
					refreshTable();	
					System.out.println("Update Clicked");
					idField.setText("");
					nameField.setText("");
					descField.setText("");
					priceField.setText("");
					stockField.setText("");
					JOptionPane.showMessageDialog(null, "Update Successful!");
				}
				else {
					String message = ProductHandler.errorMsg;
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int id = Integer.parseInt(idField.getText());
				boolean isInserted = CartHandler.removeProductFromCart(id);
				if(isInserted) {					
					refreshTable();	
					System.out.println("Delete Clicked");
					idField.setText("");
					nameField.setText("");
					descField.setText("");
					priceField.setText("");
					stockField.setText("");
					JOptionPane.showMessageDialog(null, "Delete Successful");
				}
				else {
					String message = CartHandler.errorMsg;
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				idField.setText(table.getValueAt(table.getSelectedRow(),0).toString());
				nameField.setText(table.getValueAt(table.getSelectedRow(),1).toString());
				descField.setText(table.getValueAt(table.getSelectedRow(),2).toString());
				priceField.setText(table.getValueAt(table.getSelectedRow(),3).toString());
				stockField.setText(table.getValueAt(table.getSelectedRow(),4).toString());
			}
		});

		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(1000, 300));

		//		northPanel.add(scrollPane);
		northPanel.add(scrollPane);
		centerPanel.add(id);
		centerPanel.add(idField);
		centerPanel.add(name);
		centerPanel.add(nameField);
		centerPanel.add(desc);
		centerPanel.add(descField);
		centerPanel.add(price);
		centerPanel.add(priceField);
		centerPanel.add(stock);
		centerPanel.add(stockField);

		southPanel.add(insertBtn);
		southPanel.add(updateBtn);
		southPanel.add(deleteBtn);
		southPanel.add(refreshBtn);

		this.add(northPanel,BorderLayout.NORTH);
		this.add(southPanel,BorderLayout.SOUTH);
		this.add(centerPanel,BorderLayout.CENTER);
		this.setClosable(true);
		this.setTitle("CHECKOUT");
		this.setVisible(true);
	}

	public void genTable() {
		Object[] column = {"ID", "Name", "Description", "Price", "Quantity"};

		dtm = new DefaultTableModel(column, 0);
		Vector<CartItem> cart = CartHandler.getAllProducts();

		for (CartItem cartItem : cart) {
			rowData = new Vector<>();
			rowData.add(cartItem.getProductID());
			rowData.add(cartItem.getName());
			rowData.add(cartItem.getDescription());
			rowData.add(cartItem.getPrice());
			rowData.add(cartItem.getQuantity());
			dtm.addRow(rowData);
		}
		table.setModel(dtm);
	}


	private void refreshTable() {
		Object[] column = {"ID", "Name", "Description", "Price", "Quantity"};

		dtm = new DefaultTableModel(column, 0);
		Vector<CartItem> cart = CartHandler.getAllProducts();

		for (CartItem cartItem : cart) {
			rowData = new Vector<>();
			rowData.add(cartItem.getProductID());
			rowData.add(cartItem.getName());
			rowData.add(cartItem.getDescription());
			rowData.add(cartItem.getPrice());
			rowData.add(cartItem.getQuantity());
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
