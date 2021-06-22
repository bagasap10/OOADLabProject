package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
	
	public static Vector<Product> getListCartItem(){
		Vector<Product> carts = new Vector<>();
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("SELECT * FROM product");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("productID");
				String name = rs.getString("name");
				String description = rs.getString("description");
				int price = rs.getInt("price");
				int stock = rs.getInt("stock");

				Product product = new Product(id, name, description, price, stock);
				carts.add(product);
			}
			return carts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
