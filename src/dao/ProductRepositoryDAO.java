package dao;

import java.util.List;

import model.Product;

public interface ProductRepositoryDAO {
	public List<Product> getAllProduct();
	
	public Product getProductById(String productId);
	
	public List<Product> getProductsByCategory(String category);
	
	public List<Product> getProductsByBrands(List<String> brands);
	
	public void addProduct(Product product);
}
