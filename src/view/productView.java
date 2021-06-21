package view;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import connect.Connect;
import controller.EmployeeHandler;
import controller.ProductHandler;
import model.Employee;
import model.Product;

public class productView extends JInternalFrame implements MouseListener, ActionListener {

	JPanel northPanel, southPanel, centerPanel;
	JTable table;
	DefaultTableModel dtm;
	JScrollPane scrollPane;
	JLabel id, name, desc, price, stock;
	Vector<Object> rowData;
	JTextField idField, nameField, descField, priceField, stockField;
	JButton insertBtn, updateBtn, deleteBtn;
	Connect con;

	public productView() {

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

		table = new JTable(dtm);
		genTable();

		insertBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String description = descField.getText();
				int price = Integer.parseInt(priceField.getText());
				int stock = Integer.parseInt(stockField.getText());

				try {
					PreparedStatement ps = Connect.getInstance().prepareStatement("INSERT INTO product VALUES(null,?,?,?)");
					ps.setString(2, name);
					ps.setString(3, description);
					ps.setInt(4, price);
					ps.setInt(5, stock);
					
					ps.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("insert Clicked");
				JOptionPane.showMessageDialog(null, "Insert Successful");

				refreshTable();	
			}
		});

		updateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String description = descField.getText();
				int price = Integer.parseInt(priceField.getText());
				int stock = Integer.parseInt(stockField.getText());

//				String query = String.format("UPDATE `product` SET `name`=[value-2],`description`=[value-3],`price`=[value-4],`stock`=[value-5] WHERE 1", name, description, price, stock);
//				con.execUpdate(query);
				
				try {
					PreparedStatement ps = Connect.getInstance().prepareStatement("INSERT INTO product VALUES(null,?,?,?)");
					ps.setString(2, name);
					ps.setString(3, description);
					ps.setInt(4, price);
					ps.setInt(5, stock);
					
					ps.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				System.out.println("insert Clicked");
				JOptionPane.showMessageDialog(null, "Insert Successful!");

				refreshTable();	
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

		northPanel.add(scrollPane);

		southPanel.add(id);
		southPanel.add(name);
		southPanel.add(desc);
		southPanel.add(price);
		southPanel.add(stock);

		northPanel.add(scrollPane);
		//		centerPanel.add(id);
		//		centerPanel.add(idField);
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

		this.add(northPanel,BorderLayout.NORTH);
		this.add(southPanel,BorderLayout.SOUTH);
		this.add(centerPanel,BorderLayout.CENTER);
		this.setClosable(true);
		this.setTitle("Product List");
		this.setVisible(true);
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


	//	@Override
	//	public void actionPerformed(ActionEvent e) {
	//		// TODO Auto-generated method stub
	//		if(e.getSource() == insertBtn) {
	//			int id = Integer.parseInt(idfield.getText();
	//			String name = nameField.getText();
	//			String type = typeField.getText();
	//			String cuisine = cuisineField.getText();
	//			
	//			boolean isInserted = EmployeeHandler.insertEmployee(name, type, cuisine);
	//			if(isInserted) {
	//				refreshTable();
	//				idfield.setText("");
	//				nameField.setText("");
	//				typeField.setText("");
	//				cuisineField.setText("");
	//				
	//				JOptionPane.showMessageDialog(null, "Insert success!");
	//			}else {
	//				String message = EmployeeHandler.errorMessage;
	//				JOptionPane.showMessageDialog(null, message, "Error", JOptionPane. ERROR_MESSAGE);
	//			}
	//		}
	//		
	//		
	//		else if(e.getSource() == updateButton) {
	//			int employeeID = idField.getText();
	//			
	//			
	//			if(EmployeeHandler.updateEmployee(emp)) {
	//				refreshTable();
	//				
	//				idField.setText("");
	//				
	//				JOptionPane.showMessageDialog(null, "Update success!");
	//			}else {
	//				JOptionPane.showMessageDialog(null, EmployeeHandler.errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
	//			}
	//		}
	//		
	//		else if(e.getSource() == deleteButton) {
	//			String id = idField.getText();
	//			
	//			if(EmployeeHandler.deleteEmployee(id)) {
	//				
	//			}
	//		}
	//	}
	//
	//	@Override
	//	public void mouseClicked(MouseEvent e) {
	//		// TODO Auto-generated method stub
	//		
	//	}
	//
	//	@Override
	//	public void mousePressed(MouseEvent e) {
	//		// TODO Auto-generated method stub
	//		
	//	}
	//
	//	@Override
	//	public void mouseReleased(MouseEvent e) {
	//		// TODO Auto-generated method stub
	//		
	//	}
	//
	//	@Override
	//	public void mouseEntered(MouseEvent e) {
	//		// TODO Auto-generated method stub
	//		
	//	}
	//
	//	@Override
	//	public void mouseExited(MouseEvent e) {
	//		// TODO Auto-generated method stub
	//		
	//	}
	//
	//	@Override
	//	public void actionPerformed(ActionEvent arg0) {
	//		// TODO Auto-generated method stub
	//		
	//	}


}
