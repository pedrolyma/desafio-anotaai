package com.br.desafioanotaai.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.desafioanotaai.domain.category.Category;
import com.br.desafioanotaai.domain.category.CategoryDTO;
import com.br.desafioanotaai.domain.product.Product;
import com.br.desafioanotaai.domain.product.ProductDTO;
import com.br.desafioanotaai.services.CategoryService;
import com.br.desafioanotaai.services.ProductService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private ProductService productService;
	
	public ProductController(ProductService service) {
		this.productService = service;
	}
	@PostMapping
	public ResponseEntity<Product> create(@RequestBody ProductDTO productDTO) {
		Product newProduct = this.productService.create(productDTO);
		return ResponseEntity.ok().body(newProduct);
	}
	
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
    	List<Product> listProduct = this.productService.getAll();
    	return ResponseEntity.ok().body(listProduct);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") String id, @RequestBody ProductDTO productData ) {
		Product updateProduct = this.productService.update(id, productData);
		return ResponseEntity.ok().body(updateProduct);
    }
    
    @DeleteMapping
    public ResponseEntity<Product> delete(@PathParam("id") String id) {
    	this.productService.delete(id);
    	return ResponseEntity.noContent().build();
    }
}
