package com.servicebooking.userservice.service;

import java.util.List;

import com.servicebooking.userservice.dto.UserCreateResponse;
import com.servicebooking.userservice.entity.User;

public interface UserService {
	
	UserCreateResponse createUser(User user);
	List<User> getAllUsers();
	String login(String email, String password);

}
