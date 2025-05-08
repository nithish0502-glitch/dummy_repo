package com.examly.springappuser.service;

import java.util.List;

import com.examly.springappuser.model.User;

public interface UserService {

	public User createUser(User user);
	
	public User loginUser(User user);

	public User getUserById(int userID);
		
}
