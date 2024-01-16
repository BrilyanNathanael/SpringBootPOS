package com.wideedu.posapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wideedu.posapi.domain.Product;
import com.wideedu.posapi.domain.Sale;
import com.wideedu.posapi.repository.ProductRepository;
import com.wideedu.posapi.repository.SaleRepository;
import com.wideedu.posapi.resource.dto.ProductDTO;

import jakarta.transaction.Transactional;

@Service
public class SaleService {
	
	@Autowired
	SaleRepository saleRepository;
	
	@Transactional
	public List<Sale> findALL(){
		return saleRepository.findAll();
	}
	
	@Transactional
	public void saveSale(Sale sale) {
		saleRepository.save(sale);
	}
	
//	@Transactional
//	public Product findByItemCode(String itemCode) {
//		return productRepository.findByItemCode(itemCode);
//	}
//	
//	@Transactional
//	public void saveProduct(Product product) {
//		productRepository.save(product);
//	}
//	
//	@Transactional
//	public void updateProduct(Product product) {
//        productRepository.save(product);
//	}
//	
//	@Transactional
//	public void deleteProduct(String itemCode) {
//		productRepository.deleteByItemCode(itemCode);
//	}
}
