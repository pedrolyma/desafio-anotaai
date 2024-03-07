package com.br.desafioanotaai.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.br.desafioanotaai.domain.category.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

}
