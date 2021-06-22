package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import connect.Connect;

public class Voucher {
    
    private static Connect con = new Connect();
    
    private int voucherID;
    private int discount;
    private String status;
    
    public Voucher() {
        
    }
    
    public Voucher(int voucherID, int discount, String status) {
        super();
        this.voucherID = voucherID;
        this.discount = discount;
        this.status = status;
    }
    
    public static Vector<Voucher> getAllVouchers() {
        Vector<Voucher> vouchers = new Vector<>();
        try {
            PreparedStatement ps = Connect.getInstance().prepareStatement("SELECT * FROM voucher");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("voucherID");
                int discount = rs.getInt("discount");
                String status = rs.getString("status");

                Voucher voucher = new Voucher(id, discount, status);
                vouchers.add(voucher);
            }
            return vouchers;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    public Voucher getVoucher() {
        try {
            PreparedStatement ps = Connect.getInstance().prepareStatement("SELECT * FROM voucher WHERE voucherID = ?");
            ps.setInt(1, voucherID);

            Voucher voucher = new Voucher(voucherID, discount, status);
            return voucher;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean insertNewVoucher() {
        try {
            PreparedStatement ps = Connect.getInstance().prepareStatement("INSERT INTO voucher VALUES(null,?,?)");
            ps.setInt(1, discount);
            ps.setString(2, status);

            return ps.executeUpdate() == 1;
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return false;
    }
    
    public boolean updateVoucher() {
        try {
            PreparedStatement ps = Connect.getInstance().prepareStatement("UPDATE voucher SET status = ?, discount = ? WHERE voucherID = ?");
            ps.setString(1, status);
            ps.setInt(2, discount);
            ps.setInt(3, voucherID);

            return ps.executeUpdate() == 1;
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteVoucher() {
        try {
            PreparedStatement ps = Connect.getInstance().prepareStatement("DELETE FROM voucher WHERE voucherID = ?");
            ps.setInt(1,voucherID);

            return ps.executeUpdate() == 1;
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return false;
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
     * @return the discount
     */
    public int getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}