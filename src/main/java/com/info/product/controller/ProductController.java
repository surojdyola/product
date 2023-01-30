package com.info.product.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.product.model.RestResponse;
import com.info.product.util.RestHelper;
import com.info.product.entity.Product;
import com.info.product.service.ProductService;

@RestController
@RequestMapping("/")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@GetMapping("products")
	public ResponseEntity<RestResponse> getProducts() {
		List<Product> products = productService.findAll();
		return RestHelper.responseSuccess("products", products);
	}
	
	@GetMapping("product/{id}")
	public ResponseEntity<RestResponse> getProduct(@PathVariable Long id) {
		Product product = productService.findById(id);
		if(!Objects.nonNull(product)) {
			return RestHelper.responseError("Product not found", HttpStatus.BAD_REQUEST);
		}
		return RestHelper.responseSuccess("product", product);
	}
	
	@PostMapping("product")
	public ResponseEntity<RestResponse> saveProduct(@RequestBody Product product) {
		Product newProd = new Product();
		newProd.setName(product.getName());
		newProd.setCode(product.getCode());
		newProd.setPrice(product.getPrice());
		productService.save(product);
		return RestHelper.responseMessage("Phone saved successfully.", HttpStatus.OK);
	}
	
	@PutMapping("product/{id}")
	public ResponseEntity<RestResponse> editProduct(@PathVariable Long id, @RequestBody Product product) {
		Product oldProd = productService.findById(id);
		if(!Objects.nonNull(product)) {
			return RestHelper.responseError("Product not found", HttpStatus.BAD_REQUEST);
		}
		oldProd.setName(product.getName());
		oldProd.setCode(product.getCode());
		oldProd.setPrice(product.getPrice());
		productService.save(product);
		return RestHelper.responseMessage("Phone updated successfully.", HttpStatus.OK);
	}
	
}
