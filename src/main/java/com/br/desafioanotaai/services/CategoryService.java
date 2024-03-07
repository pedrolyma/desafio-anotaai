package com.br.desafioanotaai.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.br.desafioanotaai.domain.category.Category;
import com.br.desafioanotaai.domain.category.CategoryDTO;
import com.br.desafioanotaai.domain.category.exceptions.CategoryNotFoundException;
import com.br.desafioanotaai.repositories.CategoryRepository;

@Service
public class CategoryService {

	private CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository repository) {
		categoryRepository = repository;
	}
	
	public Category create(CategoryDTO categoryDTO) {
		Category newCategory = new Category(categoryDTO);
		this.categoryRepository.save(newCategory);
		return newCategory;
	}
	
	public List<Category> getAll() {
		return this.categoryRepository.findAll();
	}
	
	public Category update(String id, CategoryDTO categoryDTO) {
		Category category = this.categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
		
		if (!categoryDTO.title().isEmpty()) category.setTitle(categoryDTO.title());
		if (!categoryDTO.description().isEmpty()) category.setDescription(categoryDTO.description());
		this.categoryRepository.save(category);
		return category;
	}
	
	public void delete(String id) {
		Category category = this.categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
		this.categoryRepository.delete(category);
	}
	
	public Optional<Category> getById(String id) {
		return this.categoryRepository.findById(id);
	}
}
