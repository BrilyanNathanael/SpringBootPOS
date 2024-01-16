package com.wideedu.posapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wideedu.posapi.domain.Product;
import com.wideedu.posapi.resource.dto.ProductDTO;

public interface ProductRepository extends CrudRepository<Product, Integer> {
	List<Product> findAll();
	Product findByItemCode(String itemCode);
	void deleteByItemCode(String itemCode);
}
