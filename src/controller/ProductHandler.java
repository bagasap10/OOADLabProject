package controller;

import java.util.Vector;
import model.Product;

public class ProductHandler {
	public static String errorMsg;

	public ProductHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public static Vector<Product> getAllProducts(){
		Product product = new Product();
		return product.getAllProducts();
	}
	
	public static Product getProduct() {
		Product product = new Product();
		return (Product) product.getProduct();
	}
	
	public static boolean insertNewProduct(String name, String description, int price, int stock) {
		if(name.length() < 5) {
			errorMsg = "Name must be at least 5 characters!";
			return false;
		}
		else if(price < 5000) {
			errorMsg = "Price must be at least Rp5000";
			return false;
		}

		
		
		
		Product product = new Product();
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		product.setStock(stock);
		
		if(!product.insertNewProduct()) {
			errorMsg="Insert Failed!";
			return false;
		}
		return true;
	}
	
	public static boolean updateProduct(int id, String name, String description, int price) {
		if(name.length() < 5) {
			errorMsg = "Name must be at least 5 characters!";
			return false;
		}
		else if(price < 5000) {
			errorMsg = "Price must be at least Rp5000";
			return false;
		}

		
		
		
		Product product = new Product();
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		product.setProductID(id);
		
		if(!product.updateProduct()) {
			errorMsg="Update Failed!";
			return false;
		}
		return true;
	}
	
	public static boolean updateProductStock(int id, int stock) {
		Product product = new Product();

		product.setStock(stock);
		product.setProductID(id);
		
		if(!product.updateProductStock()) {
			errorMsg="Update Failed!";
			return false;
		}
		return true;
	}
	
	public static boolean deleteProduct(int id) {
		Product product = new Product();
		product.setProductID(id);
		
		if(!product.deleteProduct()) {
			errorMsg="Item has not been deleted!";
			return false;
		}
		return true;
	}
}
