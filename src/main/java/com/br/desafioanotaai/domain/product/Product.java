package com.br.desafioanotaai.domain.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.br.desafioanotaai.domain.category.Category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
   @Id
   private String id;
   private String title;
   private String description;
   private String ownerId;
   private Integer price;
   private Category category;
   
   public Product(String id, String title, String description, String ownerId, Integer price, Category category) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.ownerId = ownerId;
		this.price = price;
		this.category = category;
   }

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getOwnerId() {
		return ownerId;
	}
	
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
	public Integer getPrice() {
		return price;
	}
	
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Product(ProductDTO productDTO) {
			this.title = productDTO.title();
			this.description = productDTO.description();
			this.ownerId = productDTO.ownerId();
			this.price = productDTO.price();
		}
}
