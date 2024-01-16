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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wideedu.posapi.domain.Cart;
import com.wideedu.posapi.domain.Product;
import com.wideedu.posapi.resource.dto.CartRequestDTO;
import com.wideedu.posapi.resource.dto.CartViewDTO;
import com.wideedu.posapi.service.ProductService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/cart")
public class CartResource {
	
	@Autowired
	ProductService productService;

	@GetMapping
	public ResponseEntity<CartViewDTO> viewMyCart(HttpSession session) {
		CartViewDTO cartViewDTO;
		if(session.getAttribute("myCart") != null) {
			cartViewDTO = (CartViewDTO) session.getAttribute("myCart");
			cartViewDTO.setCalculateTax();
			cartViewDTO.setTotalGrandPrice();
		}
		else {
			cartViewDTO = new CartViewDTO();
			cartViewDTO.setListCarts(new ArrayList<Cart>());
		}
		
		return new ResponseEntity<>(cartViewDTO, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> addToCart(@Valid @RequestBody CartRequestDTO cartRequestDTO, HttpSession session) {
		List<Cart> listCart;
		Product p = productService.findByItemCode(cartRequestDTO.getItemCode());
		
		if(p == null) {
			return new ResponseEntity<>("Item not found!", HttpStatus.BAD_REQUEST);
		}
		else {
			if(session.getAttribute("myCart") != null) {
				CartViewDTO oldCart = (CartViewDTO) session.getAttribute("myCart");
				listCart = oldCart.getListCarts();
				
				boolean checkSameItem = checkSameItem(listCart, p, cartRequestDTO.getQuantity());
				if(!checkSameItem) {
					listCart = addItemToCart(listCart, p, cartRequestDTO.getQuantity());
				}
			}
			else {
				listCart = new ArrayList<Cart>();
				listCart = addItemToCart(listCart, p, cartRequestDTO.getQuantity());
			}
			
			CartViewDTO cartViewDTO = new CartViewDTO();
			cartViewDTO.setListCarts(listCart);
			cartViewDTO.setTotalGrandPrice();
			cartViewDTO.setCalculateTax();
			
			session.setAttribute("myCart", cartViewDTO);
			return new ResponseEntity<>("Item has been added to cart", HttpStatus.OK);
		}
	}
	
	private boolean checkSameItem(List<Cart> listCart, Product p, int requestQuantity) {
		for(Cart c:listCart) {
			if(p.getItemCode().equals(c.getProduct().getItemCode())) {
				c.setQuantity(c.getQuantity() + requestQuantity);
				c.setSubPrice(c.getQuantity() * c.getPrice());
				return true;
			}
		}
		return false;
	}
	
	private List<Cart> addItemToCart(List<Cart> listCart, Product p, int quantity) {
		// TODO Auto-generated method stub
		Cart cart = new Cart();
		cart.setProduct(p);
		cart.setPrice(p.getPrice());
		cart.setQuantity(quantity);
		cart.setSubPrice(p.getPrice() * quantity);
		listCart.add(cart);	
		return listCart;
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
