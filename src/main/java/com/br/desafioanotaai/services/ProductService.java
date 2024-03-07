package com.br.desafioanotaai.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.br.desafioanotaai.domain.category.Category;
import com.br.desafioanotaai.domain.category.exceptions.CategoryNotFoundException;
import com.br.desafioanotaai.domain.product.Product;
import com.br.desafioanotaai.domain.product.ProductDTO;
import com.br.desafioanotaai.domain.product.exceptions.ProductNotFoundException;
import com.br.desafioanotaai.repositories.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepository;
	private CategoryService categoryService;
	private final AwsSnsService snsService;
	
	public ProductService(ProductRepository repository, CategoryService category, AwsSnsService snsService) {
		this.productRepository = repository;
		this.categoryService = category;
		this.snsService = snsService;
	}
	
	public Product create(ProductDTO productDTO) {
		Category category = this.categoryService.getById(productDTO.categoryId()).orElseThrow(CategoryNotFoundException::new);
		Product newProduct = new Product(productDTO);
		newProduct.setCategory(category);
		this.productRepository.save(newProduct);
		this.snsService.publish(new MessageDTO(newProduct.getOwnerId()));
		return newProduct;
	}
	
	public List<Product> getAll() {
		return this.productRepository.findAll();
	}
	
	public Product update(String id, ProductDTO productDTO) {
		Product product = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
		if (productDTO.categoryId() != null) {
			this.categoryService.getById(productDTO.categoryId()).ifPresent(product::setCategory);
		}
		if (!productDTO.title().isEmpty()) product.setTitle(productDTO.title());
		if (!productDTO.description().isEmpty()) product.setDescription(productDTO.description());
		if (!(productDTO.price() == null)) product.setPrice(productDTO.price());
		this.productRepository.save(product);
		
		this.snsService.publish(new MessageDTO(product.getOwnerId()));
		return product;
	}
	
	public void delete(String id) {
		Product product = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
		this.productRepository.delete(product);
	}
}
