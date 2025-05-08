package com.examly.springappuser.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.examly.springappuser.model.User;
import com.examly.springappuser.repository.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepo userRepo;
	
	
	@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User users = userRepo.findByEmail(email).get();
        if (users==null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        //User user = users.get(0);
        return new UserPrinciple(users);
    }

}
