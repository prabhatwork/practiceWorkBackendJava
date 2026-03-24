package com.servicebooking.userservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicebooking.userservice.dto.LoginRequest;
import com.servicebooking.userservice.dto.UserCreateResponse;
import com.servicebooking.userservice.entity.User;
import com.servicebooking.userservice.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public ResponseEntity<UserCreateResponse> createUser (@RequestBody User user) {
		return new ResponseEntity<>(
				userService.createUser(user),
				HttpStatus.CREATED);
	}
	
	@RequestMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login (@RequestBody LoginRequest request) {
		String token = userService.login(request.getEmail(), request.getPassword());
		return ResponseEntity.ok(token);
	}

}
