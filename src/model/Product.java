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

	public Product getProduct() {
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("SELECT * FROM product WHERE productID = ?");
			ps.setInt(1, productID);

			Product product = new Product(productID, name, description, price, stock);
			return product;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean insertNewProduct() {
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("INSERT INTO product VALUES(null,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, description);
			ps.setInt(3, price);
			ps.setInt(4, stock);

			return ps.executeUpdate() == 1;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	public boolean updateProduct() {
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("UPDATE product SET name = ?, description = ?, price = ? WHERE productID = ?");
			ps.setString(1, name);
			ps.setString(2, description);
			ps.setInt(3, price);
			ps.setInt(4,productID);

			return ps.executeUpdate() == 1;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	public boolean updateProductStock() {
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("UPDATE product SET stock = ? WHERE productID = ?");
			ps.setInt(1, stock);
			ps.setInt(2,productID);

			return ps.executeUpdate() == 1;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	public boolean deleteProduct() {
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("DELETE FROM product WHERE productID = ?");
			ps.setInt(1,productID);

			return ps.executeUpdate() == 1;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

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
