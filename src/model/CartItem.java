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
	private String name;
	private String description;
	private Integer price;

	public CartItem(Integer productID, Integer quantity, String name, String description, Integer price) {
		super();
		this.productID = productID;
		this.quantity = quantity;
		this.name = name;
		this.description = description;
		this.price = price;
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



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Integer getPrice() {
		return price;
	}



	public void setPrice(Integer price) {
		this.price = price;
	}



	public CartItem() {
		// TODO Auto-generated constructor stub
	}

	public Vector<CartItem> getAllProducts(){
		Vector<CartItem> carts = new Vector<>();
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("SELECT * FROM cart");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("productID");
				String name = rs.getString("name");
				String description = rs.getString("description");
				int price = rs.getInt("price");
				int qty = rs.getInt("quantity");

				CartItem cart = new CartItem(id, qty, name, description, price);
				carts.add(cart);
			}
			return carts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean addToCart() {
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("INSERT INTO cart VALUES(?,?,?,?,?)");
			PreparedStatement ps2 = Connect.getInstance().prepareStatement("SELECT * FROM product WHERE productID = ?");
			PreparedStatement ps3 = Connect.getInstance().prepareStatement("UPDATE product SET stock = ? WHERE productID = ?");
			ps2.setInt(1, productID);
			ResultSet rs = ps2.executeQuery();		
			int stock = 0;
			int result = 0;

			while(rs.next()) {
				stock = rs.getInt("stock");
				ps.setInt(1, rs.getInt("productID"));
				ps.setString(2, rs.getString("name"));
				ps.setString(3, rs.getString("description"));
				ps.setInt(4, rs.getInt("price"));
			}

			result = stock-quantity;

			ps.setInt(5, quantity);
			ps3.setInt(1, result);
			ps3.setInt(2, productID);
			ps3.executeUpdate();

			return ps.executeUpdate() == 1;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}





	public static Vector<Product> getListCartItem(){
		Vector<Product> carts = new Vector<>();
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("SELECT * FROM cart");
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

	public boolean clearCart() {
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("DELETE * FROM cart");	
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean removeProductFromCart() {
		try {

			PreparedStatement ps = Connect.getInstance().prepareStatement("DELETE FROM cart WHERE productID = ?");
			PreparedStatement ps2 = Connect.getInstance().prepareStatement("SELECT * FROM product WHERE productID = ?");
			PreparedStatement ps3 = Connect.getInstance().prepareStatement("UPDATE product SET stock = ? WHERE productID = ?");
			ps2.setInt(1, productID);
			ResultSet rs = ps2.executeQuery();		
			int stock = 0;
			//			int result = 0;


			while (rs.next()) {
				stock = rs.getInt("stock");
			}

			ps.setInt(1, productID);

			ps3.setInt(1, stock+quantity);
			ps3.setInt(2, productID);
			ps3.executeUpdate();

			return ps.executeUpdate() == 1;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	public static boolean updateProductStock(int id, int stock) {
		String errorMsg = "";
		Product product = new Product();

		product.setStock(stock);
		product.setProductID(id);

		if(!product.updateProductStock()) {
			errorMsg="Update Failed!";
			return false;
		}
		return true;
	}
}
