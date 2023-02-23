package com.appdemo.app.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import com.appdemo.app.domain.User;
import com.appdemo.app.domain.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository uRepository;
	
	@PostMapping("/user/save")
	public User saveUser(@RequestBody User user) {
		return this.uRepository.save(user);
	}
	
	@GetMapping("/user/all")
	public Iterable<User> getUsers(){
		
		return this.uRepository.findAll();
	}

	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable(value = "id") Long id) throws ResourceAccessException{
		User user = this.uRepository.findById(id).orElseThrow(
		
			()-> new ResourceAccessException("User Not Found")
		);
		
		return ResponseEntity.ok().body(user);
	}
	
}
