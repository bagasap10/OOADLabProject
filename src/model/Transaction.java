package model;

import java.util.*;

import connect.Connect;

public class Transaction {

	private static Connect con = new Connect();
	
	private static int transactionID;
	private Date purchaseDate;
	private int voucherID;
	private int employeeID;
	private int totalPrice;
	
	public Transaction insertTransaction() {
		Transaction trans = new Transaction();

		try{
			String query = String.format("INSERT INTO Transaction (transactionID, purchaseDate, voucherID, employeeID, totalPrice) VALUES ('%d', '%s', '%d', '%d', '%d')", transactionID, purchaseDate, voucherID, employeeID, totalPrice);
			con.stat.execute(query);
		}
		catch(Exception e) {
			return null;
		}
		return trans;
	}
	
	public static List<Transaction> getAllTransaction() {
		List<Transaction> listTransaction = new ArrayList<Transaction>();
		
		try {
			con.rs = con.execQuery("SELECT * FROM Transaction");
			
			while(con.rs.next() == true) {
				Transaction trans = new Transaction();
				
				trans.setTransactionID(con.rs.getInt("transactionID"));
				trans.setPurchaseDate(con.rs.getDate("purchaseDate"));
				trans.setVoucherID(con.rs.getInt("voucherID"));
				trans.setEmployeeID(con.rs.getInt("employeeID"));
				trans.setTotalPrice(con.rs.getInt("price"));

			}
		}
		
		catch (Exception e) {
			return null;
		}
		
		return listTransaction;
	}
	
	public static Transaction getTransactionDetail() {
		Transaction trans = new Transaction();
		
		try {
			con.rs = con.execQuery("SELECT * FROM Employee WHERE ID = " + transactionID);
			
			if(con.rs.next() == true) {
				trans.setTransactionID(con.rs.getInt("transactionID"));
				trans.setPurchaseDate(con.rs.getDate("purchaseDate"));
				trans.setVoucherID(con.rs.getInt("voucherID"));
				trans.setEmployeeID(con.rs.getInt("employeeID"));
				trans.setTotalPrice(con.rs.getInt("price"));
			}
		}
		
		catch(Exception e) {
			return null;
		}
		return trans;
	}

	/**
	 * @return the transactionID
	 */
	public int getTransactionID() {
		return transactionID;
	}

	/**
	 * @param transactionID the transactionID to set
	 */
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	/**
	 * @return the purchaseDate
	 */
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * @param purchaseDate the purchaseDate to set
	 */
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	/**
	 * @return the voucherID
	 */
	public int getVoucherID() {
		return voucherID;
	}

	/**
	 * @param voucherID the voucherID to set
	 */
	public void setVoucherID(int voucherID) {
		this.voucherID = voucherID;
	}

	/**
	 * @return the employeeID
	 */
	public int getEmployeeID() {
		return employeeID;
	}

	/**
	 * @param employeeID the employeeID to set
	 */
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	/**
	 * @return the totalPrice
	 */
	public int getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
}

/*
 * import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import connection.Connector;
import models.Transaction;

public class Transaction {
	// id, purchaseDate, employeeID, paymentType
	// addTransaction(), getAllTransaction(), getTransactionReport(date)
	
	private Integer transactionID;
	private Date purchaseDate;
	private Integer employeeID;
	private String paymentType;
	private Timestamp paymentTimeStamp;
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}
	
	// Transaction Constructor
	public Transaction(Integer transactionID, Date purchaseDate, Integer employeeID, String paymentType, Timestamp paymentTimeStamp) {
		super();
		this.transactionID = transactionID;
		this.purchaseDate = purchaseDate;
		this.employeeID = employeeID;
		this.paymentType = paymentType;
		this.paymentTimeStamp = paymentTimeStamp;
	}
	
	// Runs an INSERT query
	// If success @return Transaction, return null if fail
	public Transaction save() {
		String query = "INSERT into transaction"
				+ " (transactionID, purchaseDate, employeeID, paymentType)"
				+ " values (?, ?, ?, ?)";
		
		int res = 0;
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setInt(1, this.transactionID);
			ps.setDate(2,  (java.sql.Date) this.purchaseDate);
			ps.setInt(3, this.employeeID);
			ps.setString(4, this.paymentType);
			
			res = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return res == 1 ? this : null;
	}
	
	// Runs a SELECT query
	// if success @return List<Transaction>, if fail return null
	public static List<Transaction> getAll(){
		List<Transaction> listTransaction = new ArrayList<Transaction>();
		String query = "SELECT * from transaction";
				
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
					
			while (rs.next()) {
				int transaction_ID = rs.getInt("transactionID");
				Date purchase_Date = rs.getDate("purchaseDate");
				int employee_ID = rs.getInt("employeeID");
				String payment_Type = rs.getString("paymentType");
				Timestamp payment_timestamp = rs.getTimestamp("paymentTimeStamp");
						
				listTransaction.add(new Transaction(transaction_ID, purchase_Date, employee_ID, payment_Type, payment_timestamp));
			}
					
			return listTransaction;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static List<Transaction> getTransactionReport(Date date){
		List<Transaction> listTransaction = new ArrayList<Transaction>();
		String query = "SELECT * from transaction WHERE purchaseDate = ?";
				
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setDate(1, (java.sql.Date) date);
			ResultSet rs = ps.executeQuery();
					
			while (rs.next()) {
				int transaction_ID = rs.getInt("transactionID");
				Date purchase_Date = rs.getDate("purchaseDate");
				int employee_ID = rs.getInt("employeeID");
				String payment_Type = rs.getString("paymentType");
				Timestamp payment_timestamp = rs.getTimestamp("paymentTimeStamp");
						
				listTransaction.add(new Transaction(transaction_ID, purchase_Date, employee_ID, payment_Type, payment_timestamp));
			}
					
			return listTransaction;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
			
	// Runs a SELECT query
	// if success @return List<Transaction>, if fail return null
	public static Transaction get(Integer transactionID) {
		String query = "SELECT * from transaction where transactionID = ?";
				
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setInt(1, transactionID);
			ResultSet rs = ps.executeQuery();
					
			rs.next();
			int transaction_ID = rs.getInt("transactionID");
			Date purchase_Date = rs.getDate("purchaseDate");
			int employee_ID = rs.getInt("employeeID");
			String payment_Type = rs.getString("paymentType");
			Timestamp payment_timestamp = rs.getTimestamp("paymentTimeStamp");
					
			return new Transaction(transaction_ID, purchase_Date, employee_ID, payment_Type, payment_timestamp);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public List<Transaction> getAllMonthlyTransaction(){
		List<Transaction> listTransaction = new ArrayList<Transaction>();
		String query = "SELECT * FROM transaction WHERE YEAR(purchaseDate)=? AND MONTH(purchaseDate)=?";
		
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setInt(1, this.purchaseDate.getYear());
			ps.setInt(2, this.purchaseDate.getMonth());
			ResultSet rs = ps.executeQuery();
			
			
			while (rs.next()) {
				int transaction_ID = rs.getInt("transactionID");
				Date purchase_Date = rs.getDate("purchaseDate");
				int employee_ID = rs.getInt("employeeID");
				String payment_Type = rs.getString("paymentType");
				Timestamp payment_timestamp = rs.getTimestamp("paymentTimeStamp");
						
				listTransaction.add(new Transaction(transaction_ID, purchase_Date, employee_ID, payment_Type, payment_timestamp));
			}
			
			return listTransaction;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public Integer getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(Integer transactionID) {
		this.transactionID = transactionID;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
}
*/
