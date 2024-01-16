package com.wideedu.posapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wideedu.posapi.domain.Product;
import com.wideedu.posapi.domain.Sale;
import com.wideedu.posapi.resource.dto.ProductDTO;

public interface SaleRepository extends CrudRepository<Sale, Integer> {
	List<Sale> findAll();
//	Product findByItemCode(String itemCode);
//	void deleteByItemCode(String itemCode);
}
