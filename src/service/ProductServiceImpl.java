package service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import dao.ProductRepositoryDAO;
import model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepositoryDAO productRepository;
	
	@Override
	public List<Product> getAllProduct() {
		
		return productRepository.getAllProduct();
	}

	@Override
	public Product getProductById(String productId) {
		return productRepository.getProductById(productId);
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		
		return productRepository.getProductsByCategory(category);
	}

	@Override
	public List<Product> getProductsByBrands(List<String> brands) {
		return productRepository.getProductsByBrands(brands);
	}

	@Override
	public void addProduct(Product product, String[] supressedFields) {
		
		if(supressedFields.length>0) {
			throw new RuntimeException("Attempting to bind disallowed fields :" +StringUtils.arrayToCommaDelimitedString(supressedFields));
		}
		productRepository.addProduct(product);
		
	}

	

}
