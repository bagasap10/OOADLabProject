package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import connect.Connect;

public class Product {
	
	private static Connect con = new Connect();

	private int productID;
	private String name;
	private String description;
	private int price;
	private int stock;
	
	
	public Product() {
		
	}

	public Product(int productID, String name, String description, int price, int stock) {
		super();
		this.productID = productID;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}

	public Vector<Product> getAllProducts(){
		Vector<Product> products = new Vector<>();
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
				products.add(product);
			}
			return products;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

//	public Product insertNewProduct() {
//		Product product = new Product();
//		
//		try{
//			String query = String.format("INSERT INTO Product (productID, name, description, price, stock) VALUES ('%d', '%s', '%s', '%d', '%d')", productID, name, description, price, stock);
//			con.stat.execute(query);
//		}
//		catch(Exception e) {
//			return null;
//		}
//		return product;
//	}

	
	

//	public static Product getProduct(int productID) {
//		Product product = new Product();
//		
//		try {
//			con.rs = con.execQuery("SELECT * FROM Employee WHERE ID = " + productID);
//			
//			if(con.rs.next() == true) {
//				product.setProductID(con.rs.getInt("ID"));
//				product.setName(con.rs.getString("name"));
//				product.setDescription(con.rs.getString("description"));
//				product.setPrice(con.rs.getInt("price"));
//				product.setStock(con.rs.getInt("stock"));
//			}
//		}
//		
//		catch(Exception e) {
//			return null;
//		}
//		return product;
//	}
//	
//	public Product updateProduct() {
//		Product product = new Product();
//		try {
//			PreparedStatement ps = con.prepareStatement("UPDATE Employee SET roleID=?, name=?, username=?, salary=?, status=?, password=? WHERE id=?");
//			
//			ps.setInt(1, productID);
//			ps.setString(2, name);
//			ps.setString(3, description);
//			ps.setInt(4, price);
//			ps.setInt(5, stock);
//		}
//		catch(Exception e) {
//			return null;
//		}
//		return product;
//	}
//	
//	public boolean deleteProduct() {
//		
//	}
	

	/**
	 * @return the productID
	 */
	public int getProductID() {
		return productID;
	}

	/**
	 * @param productID the productID to set
	 */
	public void setProductID(int productID) {
		this.productID = productID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
}
