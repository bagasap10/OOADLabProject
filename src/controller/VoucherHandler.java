package controller;

import java.util.Vector;

import model.Product;
import model.Voucher;
import view.voucherView;;

public class VoucherHandler {
    public static String errorMsg;
    
    public VoucherHandler() {
        
    }
    
    public static voucherView viewVoucherManagementForm() {
        return new voucherView();
    }
    
    public static Vector<Voucher> getAllVouchers(){
        Voucher voucher = new Voucher();
        return voucher.getAllVouchers();
    }
    
    public static Voucher getVoucher() {
        Voucher voucher = new Voucher();
        return (Voucher) voucher.getVoucher();
    }
    
    public static boolean insertNewVoucher(int discount, String status) {
        if(discount < 1 && discount > 100) {
            errorMsg = "Discount must be at least 1 and maximum of 100!";
            return false;
        }
        else if(!(status.equals("Available") || status.equals("Not Available"))) {
            errorMsg = "Status must be \"Available\" or \"Not Available\"!";
            return false;
        }

        
        
        
        Voucher voucher = new Voucher();
        voucher.setDiscount(discount);
        voucher.setStatus(status);
        
        if(!voucher.insertNewVoucher()) {
            errorMsg="Insert Failed!";
            return false;
        }
        return true;
    }
    
    public static boolean updateVoucher(int id, int discount, String status) {
        if(discount < 1) {
            errorMsg = "Discount must be at least 1!";
            return false;
        }
        else if(status.length() < 1) {
            errorMsg = "Status cannot be empty!";
            return false;
        }

        
        
        
        Voucher voucher = new Voucher();
        voucher.setDiscount(discount);
        voucher.setStatus(status);
        voucher.setVoucherID(id);
        
        if(!voucher.updateVoucher()) {
            errorMsg="Update Failed!";
            return false;
        }
        return true;
    }
    
    public static boolean deleteVoucher(int id) {
        Voucher voucher = new Voucher();
        voucher.setVoucherID(id);
        
        if(!voucher.deleteVoucher()) {
            errorMsg="Item has not been deleted!";
            return false;
        }
        return true;
    }
}