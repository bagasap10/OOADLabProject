package controller;

import java.util.Vector;

import model.Employee;
import model.Transaction;
import model.Voucher;
import view.transactionView;
import view.voucherView;;

public class TransactionHandler {
	public static String errorMsg;
	
	public TransactionHandler() {
		
	}
	
	public static transactionView viewTransactionManagementForm() {
		return new transactionView();
	}
	
	public static Vector<Transaction> getAllTransactions(){
		Transaction transaction = new Transaction();
		return transaction.getAllTransactions();
	}
	
	public static Transaction getTransaction() {
		Transaction transaction = new Transaction();
		return (Transaction) Transaction.getTransaction();
	}
	
	public static boolean insertNewTransaction(String purchaseDate, int totalPrice) {
		if(totalPrice < 1) {
			errorMsg = "Total Price must be at least 1!";
			return false;
		}
		
		Transaction transaction = new Transaction();
		transaction.setPurchaseDate(purchaseDate);
		transaction.setTotalPrice(totalPrice);
		
		if(!transaction.insertNewTransaction()) {
			errorMsg="Insert Failed!";
			return false;
		}
		return true;
	}
	
	public static boolean updateTransaction(int transactionID, String purchaseDate, int totalPrice) {
		if(totalPrice < 1) {
			errorMsg = "Total Price must be at least 1!";
			return false;
		}
		
		Transaction transaction = new Transaction();
		transaction.setPurchaseDate(purchaseDate);
		transaction.setTotalPrice(totalPrice);
		transaction.setTransactionID(transactionID);
		
		if(!transaction.updateTransaction()) {
			errorMsg="Update Failed!";
			return false;
		}
		return true;
	}
	
	public static boolean deleteTransaction(int transactionID) {
		Transaction transaction = new Transaction();
		transaction.setTransactionID(transactionID);
		
		if(!transaction.deleteTransaction()) {
			errorMsg="Transaction has not been deleted!";
			return false;
		}
		return true;
	}
	
}



















