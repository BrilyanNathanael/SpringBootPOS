package com.wideedu.posapi.resource;

import java.util.ArrayList;
import java.util.Date;
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

import com.wideedu.posapi.domain.Cart;
import com.wideedu.posapi.domain.Cashier;
import com.wideedu.posapi.domain.Payment;
import com.wideedu.posapi.domain.Sale;
import com.wideedu.posapi.domain.SaleItem;
import com.wideedu.posapi.resource.dto.CartViewDTO;
import com.wideedu.posapi.resource.dto.PaymentDTO;
import com.wideedu.posapi.resource.dto.SaleDTO;
import com.wideedu.posapi.service.SaleService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/transaction")
public class SaleResource {

	@Autowired
	SaleService saleService;
	
	@GetMapping
	public ResponseEntity<List<SaleDTO>> getAllTransactions(){
		List<Sale> sales = saleService.findALL();
		List<SaleDTO> saleDTO = mapToListSaleDTO(sales);
		return new ResponseEntity<>(saleDTO, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> addTransaction(@Valid @RequestBody PaymentDTO paymentDTO, HttpSession session) {
		CartViewDTO cartViewDTO = (CartViewDTO) session.getAttribute("myCart");
		
		List<SaleItem> listSaleItem = new ArrayList<SaleItem>();
		Sale sale = new Sale();
		
		Cashier c = new Cashier();
		c.setCashier("Nathan");
		sale.setCashier(c);
		
		Payment p = new Payment();
		p.setPayment(paymentDTO.getPayment());
		p.setAmount(paymentDTO.getAmount());
		p.setCash_in_hand(paymentDTO.getAmount() - (cartViewDTO.getGrandPrice() + cartViewDTO.getTax()));
		sale.setPayment(p);
		
		sale.setTax(cartViewDTO.getTax());
		sale.setTransDate(new Date().toString());
		
		for(Cart cart:cartViewDTO.getListCarts()) {
			SaleItem saleItem = new SaleItem();
			saleItem.setProduct(cart.getProduct());
			saleItem.setPrice(cart.getPrice());
			saleItem.setQuantity(cart.getQuantity());
			saleItem.setSale(sale);
			listSaleItem.add(saleItem);
		}
		
		sale.setSaleItems(listSaleItem);
		saleService.saveSale(sale);
		
		session.removeAttribute("myCart");
		return new ResponseEntity<>("Pembayaran Berhasil!", HttpStatus.OK);
	}
	
	private SaleDTO mapToObjSaleDTO(Sale s) {
		SaleDTO sl = new SaleDTO();
		sl.setSaleNumber(s.getSaleNumber());
		sl.setTransDate(s.getTransDate());
		sl.setCashier(s.getCashier());
		sl.setPayment(s.getPayment());
		sl.setListSaleItems(s.getSaleItems());
		sl.setTax(s.getTax());
		return sl;
	}
	
	private List<SaleDTO> mapToListSaleDTO(List<Sale> sales) {
		// TODO Auto-generated method stub
		List<SaleDTO> listSalesDTO = new ArrayList<SaleDTO>();
		
		for(Sale s:sales) {
			SaleDTO sl = new SaleDTO();
			sl.setSaleNumber(s.getSaleNumber());
			sl.setTransDate(s.getTransDate());
			sl.setCashier(s.getCashier());
			sl.setPayment(s.getPayment());
			sl.setListSaleItems(s.getSaleItems());
			sl.setTax(s.getTax());
			listSalesDTO.add(sl);
		}
		
		return listSalesDTO;
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
