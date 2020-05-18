package controller;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.Product;
import service.ProductService;

@Controller
@RequestMapping("/products/management")
public class ProductManagementController {

	@Autowired
	private ProductService productService;

	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setAllowedFields("productId", "name", "unitPrice", "description", "manufacturer", "category",
				"unitsInStock", "condition");
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String productAddGet( @ModelAttribute("newProduct")  Product newProduct) {
		
		newProduct.setDescription("defaultDescription");
		newProduct.setCondition("new");
		newProduct.setCategory("Tablet");
		newProduct.setManufacturer("Samsung");
		return "03.webstore.view/productAdd";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String productAddPost(@Valid @ModelAttribute("newProduct") Product productAdd, BindingResult result) {
		
		String[] supressedFields = result.getSuppressedFields();
		productService.addProduct(productAdd, supressedFields);
		return "redirect:/products";
	}

	@ModelAttribute("manufacturerList")
	public List<String> prepareManufacturerList() {
		return Arrays.asList("Apple", "Google", "Samsung");
	}

	@ModelAttribute("categoryList")
	public List<String> CategoryList() {
		return Arrays.asList("Laptop", "Tablet", "SmartPhone");
	}

	@ModelAttribute("conditionMap")
	public Map<String, String> prepareConditionMap() {
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("new", "New");
		conditions.put("old", "Old");
		conditions.put("refurbished", "Refurbished");
		return conditions;
	}

}
