package com.springboot.springbootcrudrestful.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.springbootcrudrestful.entity.User;
import com.springboot.springbootcrudrestful.exception.ResourceNotFoundException;
import com.springboot.springbootcrudrestful.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	//get all users
	@GetMapping
	public List<User> getAllUsers(){
		return this.userRepository.findAll();
	}
	@GetMapping("/{id}")
	// get user by id
	public User getUserById(@PathVariable (value = "id") Long userId) {
		return this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
	
	}
	@PostMapping
	//create user
	public User createUser(@RequestBody User user) {
		return this.userRepository.save(user);
		
	}
	@PutMapping("/{id}")
	//update user
	public User updateUser(@RequestBody User user, @PathVariable ("id") Long userId) {
		User existingUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		return this.userRepository.save(existingUser);
		
		
		
		
	}
	@DeleteMapping("/{id}")
	//Delete user by id
	public ResponseEntity<User> deleteUser(@PathVariable ("id") Long userId) {
		User existingUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
		this.userRepository.delete(existingUser);
		return ResponseEntity.ok().build();
		
	}

}
