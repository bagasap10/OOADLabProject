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

public class voucherView extends JInternalFrame implements MouseListener, ActionListener{

	JPanel northPanel, southPanel, centerPanel;
	JTable table;
	DefaultTableModel dtm;
	JScrollPane scrollPane;
	JLabel id, discount, status;
	Vector<Object> rowData;
	JTextField idField, discountField, statusField;
	JButton insertBtn, updateBtn, deleteBtn;
	Connect con;
	
	public voucherView() {
		
		con = new Connect();
		northPanel = new JPanel();
		southPanel = new JPanel();
		centerPanel = new JPanel(new GridLayout(8,2));

		id = new JLabel("ID");
		discount = new JLabel("Discount");
		status = new JLabel("Status");

		idField = new JTextField();
		discountField = new JTextField();
		statusField = new JTextField();

		insertBtn = new JButton("Insert");
		updateBtn = new JButton("Update");
		deleteBtn = new JButton("Delete");

		table = new JTable(dtm);
		genTable();
		
		insertBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = statusField.getText();
				int price = Integer.parseInt(discountField.getText());
				int stock = Integer.parseInt(idField.getText());
				
				boolean isInserted = VoucherHandler.insertNewVoucher(discount, status);
				if(isInserted) {					
					refreshTable();	
					System.out.println("insert Clicked");
					idField.setText("");
					discountField.setText("");
					statusField.setText("");
					JOptionPane.showMessageDialog(null, "Insert Successful");
			}
				else {
					String message = VoucherHandler.errorMsg;
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		updateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(idField.getText());
				int discount = Integer.parseInt(discountField.getText());
				String status = statusField.getText();
				
				boolean status = VoucherHandler.updateVoucher(id, discount, status);
				boolean status2 = VoucherHandler.updateProductStock(id,status);
				if(status && status2) {					
					refreshTable();	
					System.out.println("Update Clicked");
					idField.setText("");
					discountField.setText("");
					statusField.setText("");
					JOptionPane.showMessageDialog(null, "Update Successful!");
				}
				else {
					String message = VoucherHandler.errorMsg;
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(idField.getText());
				int discount = Integer.parseInt(discountField.getText());
				String status = statusField.getText();
				
				boolean status = VoucherHandler.deleteVoucher(id);
				if(status) {					
					refreshTable();	
					System.out.println("Delete Clicked");
					idField.setText("");
					discountField.setText("");
					statusField.setText("");
					JOptionPane.showMessageDialog(null, "Item has been deleted!");
				}
				else {
					String message = VoucherHandler.errorMsg;
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				idField.setText(table.getValueAt(table.getSelectedRow(),0).toString());
				discountField.setText(table.getValueAt(table.getSelectedRow(),1).toString());
				statusField.setText(table.getValueAt(table.getSelectedRow(),2).toString());
			}
		});
		
		scrollPane = new JScrollPane(table);
		
		northPanel.add(scrollPane);
		centerPanel.add(id);
		centerPanel.add(idField);
		centerPanel.add(discount);
		centerPanel.add(discountField);
		centerPanel.add(status);
		centerPanel.add(statusField);
		
		southPanel.add(insertBtn);
		southPanel.add(updateBtn);
		southPanel.add(deleteBtn);
		
		this.add(northPanel,BorderLayout.NORTH);
		this.add(southPanel,BorderLayout.SOUTH);
		this.add(centerPanel,BorderLayout.CENTER);
		this.setClosable(true);
		this.setTitle("Voucher List");
		this.setVisible(true);
	}
	
	public void genTable() {
		Object[] column = {"ID", "Discount", "Status"};

		dtm = new DefaultTableModel(column, 0);

		//		System.out.println("debug");
		con.rs = con.execQuery("SELECT * FROM voucher");
		//		System.out.println("debug2");

		try {
			while(con.rs.next()) {
				rowData = new Vector<>();

				int id = con.rs.getInt("voucherID");
				String status = con.rs.getString("status");
				int discount = con.rs.getInt("discount");

				rowData.add(id);
				rowData.add(discount);
				rowData.add(status);

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
		Object[] column = {"ID", "Discount", "Status"};

		dtm = new DefaultTableModel(column, 0);
		Vector<Voucher> vouchers = VoucherHandler.getAllVoucher();

		for (Voucher voucher : vouchers) {
			rowData = new Vector<>();
			rowData.add(voucher.getVoucherID());
			rowData.add(voucher.getDiscount());
			rowData.add(voucher.getStatus());
			dtm.addRow(rowData);
		}
		table.setModel(dtm);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}