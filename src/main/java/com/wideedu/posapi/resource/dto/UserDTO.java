package com.wideedu.posapi.resource.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserDTO {
	@Valid
	
	@NotNull(message = "Username is required")
	@NotBlank(message = "Username is required")
	private String username;
	
	@NotNull(message = "Password is required")
	@NotBlank(message = "Password is required")
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
