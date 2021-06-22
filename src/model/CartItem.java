package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.Connect;

public class CartItem {

	private Integer productID;
	private Integer quantity;
	
	public CartItem(Integer productID, Integer quantity) {
		super();
		this.productID = productID;
		this.quantity = quantity;
	}
	
	public CartItem() {
		// TODO Auto-generated constructor stub
	}
	
	public static List<CartItem> getListCartItem(){
		List<CartItem> listCartItem= new ArrayList<CartItem>();
		String query = "SELECT * from cart_item";
		
		try {
			PreparedStatement ps = (PreparedStatement) Connect.getInstance().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int productID = rs.getInt("productID");
				int quantity = rs.getInt("quantity");
						
				listCartItem.add(new CartItem(productID, quantity));
			}
			
			return listCartItem;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return null;
	}

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
