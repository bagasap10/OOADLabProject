package controller;

import java.util.Vector;
import model.Product;

public class ProductHandler {

	public ProductHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public static Vector<Product> getAllProducts(){
		Product product = new Product();
		return product.getAllProducts();
	}
}
