package com.info.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.product.entity.Product;
import com.info.product.repo.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	public List<Product> findAll() {
		return productRepo.findAll();
	}
	
	public Product save(Product product) {
		return productRepo.save(product);
	}
	
	public Product findById(Long id) {
		return productRepo.findById(id).get();
	}
	
}
