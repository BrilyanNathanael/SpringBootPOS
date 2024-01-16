package com.wideedu.posapi.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wideedu.posapi.domain.Product;
import com.wideedu.posapi.repository.ProductRepository;
import com.wideedu.posapi.resource.dto.ProductDTO;
import com.wideedu.posapi.resource.dto.ProductUpdateDTO;
import com.wideedu.posapi.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/product")
public class ProductResource {

	@Autowired
	ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll(){
		List<Product> products = productService.findALL();
		return new ResponseEntity<>(mapToListProductDTO(products), HttpStatus.OK);
	}
	
	@GetMapping("/{itemCode}")
	public ResponseEntity<ProductDTO> findByItemCode(@PathVariable String itemCode) {
		Product p = productService.findByItemCode(itemCode);
		if(p == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(mapToObjProductDTO(p), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> saveProduct(@Valid @RequestBody ProductDTO productDTO) {
		Product p = mapToProduct(productDTO);
		productService.saveProduct(p);
		return new ResponseEntity<>("Success Add Product!", HttpStatus.OK);
	}
	
	@PutMapping("/{itemCode}")
	public ResponseEntity<String> updateProduct(@PathVariable String itemCode, @Valid @RequestBody ProductUpdateDTO productDTO) {
		Product product = productService.findByItemCode(itemCode);
		
		product.setDescription(productDTO.getDescription());
		product.setPrice(productDTO.getPrice());
		product.setType(productDTO.getType());
		product.setTax(productDTO.getTax());
		
		productService.updateProduct(product);
		
		return new ResponseEntity<>("Success Update Product!", HttpStatus.OK);
	}
	
    @DeleteMapping("/{itemCode}")
    public ResponseEntity<String> deleteProduct(@PathVariable String itemCode) {
        productService.deleteProduct(itemCode);
        return new ResponseEntity<>("Success Delete Product!", HttpStatus.OK);
    }
	
	private Product mapToProduct(ProductDTO productDTO) {
		// TODO Auto-generated method stub
		Product p = new Product();
		p.setItemCode(productDTO.getItemCode());
		p.setDescription(productDTO.getDescription());
		p.setPrice(productDTO.getPrice());
		p.setType(productDTO.getType());
		p.setTax(productDTO.getTax());
		return p;
	}

	private ProductDTO mapToObjProductDTO(Product p) {
		ProductDTO prod = new ProductDTO();
		prod.setitemCode(p.getItemCode());
		prod.setDescription(p.getDescription());
		prod.setPrice(p.getPrice());
		prod.setType(p.getType());
		prod.setTax(p.getTax());
		return prod;
	}
	
	private List<ProductDTO> mapToListProductDTO(List<Product> products) {
		// TODO Auto-generated method stub
		List<ProductDTO> listProductDTO = new ArrayList<ProductDTO>();
		
		for(Product p:products) {
			ProductDTO prod = new ProductDTO();
			prod.setitemCode(p.getItemCode());
			prod.setDescription(p.getDescription());
			prod.setPrice(p.getPrice());
			prod.setType(p.getType());
			prod.setTax(p.getTax());
			listProductDTO.add(prod);
		}
		
		return listProductDTO;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}
