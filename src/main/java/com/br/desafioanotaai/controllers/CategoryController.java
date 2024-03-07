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
import com.br.desafioanotaai.services.CategoryService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	private CategoryService categoryService;
	
	public CategoryController(CategoryService service) {
		this.categoryService = service;
	}
	@PostMapping
	public ResponseEntity<Category> create(@RequestBody CategoryDTO categoryDTO) {
		Category newCategory = this.categoryService.create(categoryDTO);
		return ResponseEntity.ok().body(newCategory);
	}
	
    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
    	List<Category> listCategory = this.categoryService.getAll();
    	return ResponseEntity.ok().body(listCategory);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") String id, @RequestBody CategoryDTO categoryData ) {
		Category updateCategory = this.categoryService.update(id, categoryData);
		return ResponseEntity.ok().body(updateCategory);
    }
    
    @DeleteMapping
    public ResponseEntity<Category> delete(@PathParam("id") String id) {
    	this.categoryService.delete(id);
    	return ResponseEntity.noContent().build();
    }
}
