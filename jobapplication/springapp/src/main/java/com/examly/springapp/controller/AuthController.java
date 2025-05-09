package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.examly.springapp.config.JwtUtils;
import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;
import com.examly.springapp.service.UserService;
import com.examly.springapp.service.UserServiceImpl;

@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	private JwtUtils jwtUtil;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	JwtUtils jwtUtils;

	// @PostConstruct
	// public void initializeAdminUser() {
	// if(!userRepo.existsByEmail("admin@gmail.com"))
	// {
	// User user= new
	// User("admin","admin@gmail.com","Admin@123","Admin","1234567890");
	// userService.createUser(user);
	// }

	// if (!userRepo.existsByEmail("loanManager@gmail.com")) {
	// User branchManagerUser = new User("LoanManager", "loanManager@gmail.com",
	// "LoanManager@123", "LoanManager", "0987654321");
	// userService.createUser(branchManagerUser);
	// }
	// }

	@PostMapping("/register")
	public ResponseEntity<User> handler1(@RequestBody User user) {
		User b = userService.createUser(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<?> handler2(@RequestBody User user) throws Exception {

		try {
			this.authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("bad credentials");
		}

		UserDetails userDetails = this.userService.loadUserByUsername(user.getEmail());
		String token = this.jwtUtil.generateToken(userDetails);

		User loggeduser = userRepo.findByEmail(user.getEmail()).get();

		LoginDTO login = new LoginDTO();
		login.setToken(token);
		login.setUserRole(loggeduser.getUserRole());
		login.setUserId(loggeduser.getUserId());
		login.setUsername(loggeduser.getUsername());

		return ResponseEntity.ok(login);
	}

}
