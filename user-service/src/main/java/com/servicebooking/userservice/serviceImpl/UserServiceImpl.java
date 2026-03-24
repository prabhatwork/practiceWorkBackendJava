package com.servicebooking.userservice.serviceImpl;

import java.util.List;

import com.servicebooking.userservice.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.servicebooking.userservice.dto.UserCreateResponse;
import com.servicebooking.userservice.entity.User;
import com.servicebooking.userservice.repository.UserRepository;
import com.servicebooking.userservice.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;
	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Override
	public UserCreateResponse createUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		User savedUser = userRepository.save(user);
		
		UserCreateResponse response = new UserCreateResponse();
		response.setName(savedUser.getName());
		response.setEmail(savedUser.getEmail());
		
		return response;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public String login(String email, String password) {

		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		if (!encoder.matches(password, user.getPassword())) {
			throw new RuntimeException("Invalid password");
		}

		return jwtUtil.generateToken(email);
	}

}
