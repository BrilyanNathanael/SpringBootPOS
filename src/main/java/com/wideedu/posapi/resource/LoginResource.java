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
import com.wideedu.posapi.resource.dto.UserDTO;
import com.wideedu.posapi.service.ProductService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
public class LoginResource {

	@PostMapping("/login")
	public ResponseEntity<String> login(@Valid @RequestBody UserDTO userDTO, HttpSession session) {
		if("Brilyan".equals(userDTO.getUsername()) && "password".equals(userDTO.getPassword())) {
			session.setAttribute("userContext", userDTO.getUsername());
			return new ResponseEntity<>("Hai " + userDTO.getUsername() + ", Anda berhasil login!", HttpStatus.OK);			
		}
		return new ResponseEntity<>("Mohon masukkan Username dan Password yang benar!", HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) {
		if(session.getAttribute("userContext") != null) {
			session.removeAttribute("userContext");
			return new ResponseEntity<>("Anda berhasil logout!", HttpStatus.OK);			
		}
		return new ResponseEntity<>("Mohon login terlebih dahulu!", HttpStatus.BAD_REQUEST);
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
