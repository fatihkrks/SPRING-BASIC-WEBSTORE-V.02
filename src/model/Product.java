package model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class Product {
	@Size(min = 2,max = 5)
		private String productId;
	@NotEmpty(message ="{Size.Product.name.validation}")
		private String name;
	@NotNull @Min(18) @Max(100)
		private double unitPrice;
		private String description;
		private String manufacturer;
		private String category;
		private long unitsInStock;
		private String condition;
		
		private MultipartFile productImage;
		
		
		public Product() {
			super();
		}
		public Product(String productId, String name, double unitPrice) {
			super();
			this.productId = productId;
			this.name = name;
			this.unitPrice = unitPrice;
		}
		public String getProductId() {
			return productId;
		}
		public void setProductId(String productId) {
			this.productId = productId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public double getUnitPrice() {
			return unitPrice;
		}
		public void setUnitPrice(double unitPrice) {
			this.unitPrice = unitPrice;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getManufacturer() {
			return manufacturer;
		}
		public void setManufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public long getUnitsInStock() {
			return unitsInStock;
		}
		public void setUnitsInStock(long unitsInStock) {
			this.unitsInStock = unitsInStock;
		}
		public String getCondition() {
			return condition;
		}
		public void setCondition(String condition) {
			this.condition = condition;
		}
		public MultipartFile getProductImage() {
			return productImage;
		}
		public void setProductImage(MultipartFile productImage) {
			this.productImage = productImage;
		}
		
}
