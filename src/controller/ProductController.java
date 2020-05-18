package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import exception.ProductNotFoundException;
import service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
		private ProductService productService;
	
	@RequestMapping
	public String list(Model model) {
		model.addAttribute("products",productService.getAllProduct());
		return "03.webstore.view/productList";
	}
	@RequestMapping("/all")
	public ModelAndView listAllProducts() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("products", productService.getAllProduct());
		modelAndView.setViewName("03.webstore.view/productList");
		return modelAndView;
	}
	
	@RequestMapping("/category/{productCategory}")
	public String listProductsByCategory(@PathVariable("productCategory") String productCategory ,Model model) {
		model.addAttribute("products",productService.getProductsByCategory(productCategory));
		return "03.webstore.view/productList";
	}
	
	@RequestMapping("/product")
	public String getProductById(@RequestParam(required = false,name = "id") String productId,Model model) {
		model.addAttribute("product", productService.getProductById(productId));
		return "03.webstore.view/productDetail";
	}
	@RequestMapping("/brand/{brands}")
	public String listProductByBrands(@MatrixVariable(pathVar = "brands") List<String> brands,Model model) {
		model.addAttribute("products", productService.getProductsByBrands(brands));
		return "03.webstore.view/productList";
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	  public ModelAndView handleError(HttpServletRequest req, ProductNotFoundException ex) {
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("exception", ex.getProductId());
	    
	    mav.setViewName("03.webstore.view/productNotFound");
	    return mav;
	  }
	
}
