package controller;

import java.util.Vector;

import model.CartItem;
import model.Product;
import view.cartView;
import view.checkoutView;
import view.homeProductAdminView;
import view.productView;

public class CartHandler {
	public static String errorMsg;

	public CartHandler() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static cartView viewAddProductToCartForm() {
		return new cartView();
	}
	
	public static checkoutView viewCheckOutForm() {
		return new checkoutView();
	}
		
	
	public static Vector<CartItem> getAllProducts(){
		CartItem cart = new CartItem();
		return cart.getAllProducts();
	}
	
	public static Product getProduct() {
		Product product = new Product();
		return (Product) product.getProduct();
	}
	
	public static boolean addToCart(int productID, int quantity) {
				
		CartItem cart = new CartItem();
		cart.setProductID(productID);
		cart.setQuantity(quantity);
		
		if(!cart.addToCart()) {
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
	
	public static boolean removeProductFromCart(int id) {
		CartItem cart = new CartItem();
		cart.setProductID(id);
		
		if(!cart.removeProductFromCart()) {
			errorMsg="Item has not been deleted!";
			return false;
		}
		return true;
	}
}
