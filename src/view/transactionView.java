package view;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import connect.Connect;
import controller.EmployeeHandler;
import controller.TransactionHandler;
import model.Employee;
import controller.VoucherHandler;
import model.Voucher;
import model.Transaction;

public class transactionView extends JInternalFrame implements MouseListener, ActionListener{

	JPanel northPanel, southPanel, centerPanel;
	JTable table;
	DefaultTableModel dtm;
	JScrollPane scrollPane;
	JLabel transactionID, purchaseDate, voucherID, employeeID, totalPrice;
	Vector<Object> rowData;
	JTextField transactionIDField, purchaseDateField, voucherIDField, employeeIDField, totalPriceField;
	JButton insertBtn, updateBtn, deleteBtn;
	Connect con;

	public transactionView() {

		con = new Connect();
		northPanel = new JPanel();
		southPanel = new JPanel();
		centerPanel = new JPanel(new GridLayout(8,2));

		transactionID = new JLabel("TransactionID");
		purchaseDate = new JLabel("PurchaseDate");
		voucherID = new JLabel("VoucherID");
		employeeID = new JLabel("EmployeeID");
		totalPrice = new JLabel("TotalPrice");

		transactionIDField = new JTextField();
		purchaseDateField = new JTextField();
		voucherIDField = new JTextField();
		employeeIDField = new JTextField();
		totalPriceField = new JTextField();

		insertBtn = new JButton("Insert");
		updateBtn = new JButton("Update");
		deleteBtn = new JButton("Delete");

		table = new JTable(dtm);
		genTable();

		insertBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String purchaseDate = purchaseDateField.getText();
				Voucher voucherID = VoucherHandler.getVoucher();
				Employee employeeID = EmployeeHandler.getEmployee();
				int totalPrice = Integer.parseInt(totalPriceField.getText());

				boolean isInserted = TransactionHandler.insertNewTransaction(purchaseDate, totalPrice);
				if(isInserted) {					
					refreshTable();	
					System.out.println("insert Clicked");
					transactionIDField.setText("");
					purchaseDateField.setText("");
					voucherIDField.setText("");
					employeeIDField.setText("");
					totalPriceField.setText("");
					JOptionPane.showMessageDialog(null, "Insert Successful");
				}
				else {
					String message = TransactionHandler.errorMsg;
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		updateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int transactionID = Integer.parseInt(transactionIDField.getText());
				String purchaseDate = purchaseDateField.getText();
				Voucher voucherID = VoucherHandler.getVoucher();
				Employee employeeID = EmployeeHandler.getEmployee();
				int totalPrice = Integer.parseInt(totalPriceField.getText());

				boolean status1 = TransactionHandler.updateTransaction(transactionID, purchaseDate, totalPrice);
				if(status1) {					
					refreshTable();	
					System.out.println("Update Clicked");
					transactionIDField.setText("");
					purchaseDateField.setText("");
					voucherIDField.setText("");
					employeeIDField.setText("");
					totalPriceField.setText("");
					JOptionPane.showMessageDialog(null, "Update Successful!");
				}
				else {
					String message = TransactionHandler.errorMsg;
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int transactionID = Integer.parseInt(transactionIDField.getText());
				String purchaseDate = purchaseDateField.getText();
				Voucher voucherID = VoucherHandler.getVoucher();
				Employee employeeID = EmployeeHandler.getEmployee();
				int totalPrice = Integer.parseInt(totalPriceField.getText());

				boolean status1 = VoucherHandler.deleteVoucher(transactionID);
				if(status1) {					
					refreshTable();	
					System.out.println("Delete Clicked");
					transactionIDField.setText("");
					purchaseDateField.setText("");
					voucherIDField.setText("");
					employeeIDField.setText("");
					totalPriceField.setText("");
					JOptionPane.showMessageDialog(null, "Item has been deleted!");
				}
				else {
					String message = TransactionHandler.errorMsg;
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				transactionIDField.setText(table.getValueAt(table.getSelectedRow(),0).toString());
				purchaseDateField.setText(table.getValueAt(table.getSelectedRow(),1).toString());
				voucherIDField.setText(table.getValueAt(table.getSelectedRow(),2).toString());
				employeeIDField.setText(table.getValueAt(table.getSelectedRow(),3).toString());
				totalPriceField.setText(table.getValueAt(table.getSelectedRow(),4).toString());
			}
		});

		scrollPane = new JScrollPane(table);

		northPanel.add(scrollPane);
		centerPanel.add(transactionID);
		centerPanel.add(transactionIDField);
		centerPanel.add(purchaseDate);
		centerPanel.add(purchaseDateField);
		centerPanel.add(voucherID);
		centerPanel.add(voucherIDField);
		centerPanel.add(employeeID);
		centerPanel.add(employeeIDField);
		centerPanel.add(totalPrice);
		centerPanel.add(totalPriceField);

		southPanel.add(insertBtn);
		southPanel.add(updateBtn);
		southPanel.add(deleteBtn);

		this.add(northPanel,BorderLayout.NORTH);
		this.add(southPanel,BorderLayout.SOUTH);
		this.add(centerPanel,BorderLayout.CENTER);
		this.setClosable(true);
		this.setTitle("Transaction List");
		this.setVisible(true);
	}

	public void genTable() {
		Object[] column = {"TransactionID", "PurchaseDate", "VoucherID", "EmployeeID", "TotalPrice"};

		dtm = new DefaultTableModel(column, 0);
		Vector<Transaction> transactions = TransactionHandler.getAllTransactions();

		for (Transaction transaction : transactions) {
			rowData = new Vector<>();
			rowData.add(transaction.getTransactionID());
			rowData.add(transaction.getPurchaseDate());
			rowData.add(transaction.getVoucherID());
			rowData.add(transaction.getEmployeeID());
			rowData.add(transaction.getTotalPrice());

			dtm.addRow(rowData);
		}
		table.setModel(dtm);
	}



	private void refreshTable() {
		Object[] column = {"TransactionID", "PurchaseDate", "VoucherID", "EmployeeID", "TotalPrice"};

		dtm = new DefaultTableModel(column, 0);
		Vector<Transaction> transactions = TransactionHandler.getAllTransactions();

		for (Transaction transaction : transactions) {
			rowData = new Vector<>();
			rowData.add(transaction.getTransactionID());
			rowData.add(transaction.getPurchaseDate());
			rowData.add(transaction.getVoucherID());
			rowData.add(transaction.getEmployeeID());
			rowData.add(transaction.getTotalPrice());

			dtm.addRow(rowData);
		}
		table.setModel(dtm);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
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
}
















