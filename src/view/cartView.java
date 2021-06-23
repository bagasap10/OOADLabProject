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
import model.Employee;
import model.Product;

public class cartView extends JFrame implements MouseListener, ActionListener {

	JPanel northPanel, southPanel, centerPanel;
	JTable table;
	DefaultTableModel dtm;
	JScrollPane scrollPane;
	JLabel quantity;
	Vector<Object> rowData;
	JTextField quantityField;
	JButton insertBtn;
	Connect con;

	int idField;
	String nameField;
	String descField;
	int priceField;
	int stock;
	int qty;

	public cartView() {


		con = new Connect();
		northPanel = new JPanel();
		southPanel = new JPanel();
		centerPanel = new JPanel(new GridLayout(8,2));

		quantity = new JLabel("Quantity");
		quantityField = new JTextField();

		insertBtn = new JButton("Insert");

		table = new JTable(dtm);
		genTable();

		insertBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				qty = Integer.parseInt(quantityField.getText());
				if(qty>stock) JOptionPane.showMessageDialog(null, "Quantity EXCEEDS stock", "Error", JOptionPane.ERROR_MESSAGE);
				else {
					boolean isInserted = CartHandler.addToCart(idField, qty);
					if(isInserted) {					
						refreshTable();	
						System.out.println("insert Clicked");
						quantityField.setText("");
						JOptionPane.showMessageDialog(null, "Insert Successful");
					}
					else {
						String message = CartHandler.errorMsg;
						JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				idField = Integer.parseInt(table.getValueAt(table.getSelectedRow(),0).toString());
				nameField = table.getValueAt(table.getSelectedRow(),1).toString();
				descField = table.getValueAt(table.getSelectedRow(),2).toString();
				priceField = Integer.parseInt(table.getValueAt(table.getSelectedRow(),3).toString());
				stock = Integer.parseInt(table.getValueAt(table.getSelectedRow(),4).toString());
			}
		});

		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(1000, 300));

		northPanel.add(scrollPane);
		centerPanel.add(quantity);
		centerPanel.add(quantityField);

		southPanel.add(insertBtn);


		this.add(northPanel,BorderLayout.NORTH);
		this.add(southPanel,BorderLayout.SOUTH);
		this.add(centerPanel,BorderLayout.CENTER);
		setResizable(false);
		setSize(1280,720);
		setLocationRelativeTo(null);
		setTitle("ADD TO CART");
		setVisible(true);
	}

	public void genTable() {
		Object[] column = {"ID", "Name", "Description", "Price", "Stock"};

		dtm = new DefaultTableModel(column, 0);

		//		System.out.println("debug");
		con.rs = con.execQuery("SELECT * FROM product");
		//		System.out.println("debug2");

		try {
			while(con.rs.next()) {
				rowData = new Vector<>();

				int id = con.rs.getInt("productID");
				String name = con.rs.getString("name");
				String desc = con.rs.getString("description");
				int price = con.rs.getInt("price");
				int stock = con.rs.getInt("stock");

				rowData.add(id);
				rowData.add(name);
				rowData.add(desc);
				rowData.add(price);
				rowData.add(stock);

				dtm.addRow(rowData);
				//				System.out.println("debug3");
			}
		}
		catch(SQLException e1) {
			e1.printStackTrace();
		}
		table.setModel(dtm);
	}


	private void refreshTable() {
		Object[] column = {"ID", "Name", "Description", "Price", "Stock"};

		dtm = new DefaultTableModel(column, 0);
		Vector<Product> products = ProductHandler.getAllProducts();

		for (Product product : products) {
			rowData = new Vector<>();
			rowData.add(product.getProductID());
			rowData.add(product.getName());
			rowData.add(product.getDescription());
			rowData.add(product.getPrice());
			rowData.add(product.getStock());
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
