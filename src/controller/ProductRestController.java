package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Product;

//REST CONTROLLER = @CONTROLLER + @RESPONSEBODY
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {

	@RequestMapping("/sample")
	public Product getProduct() {
		return new Product("1","PC",5.5 );
	}
}
