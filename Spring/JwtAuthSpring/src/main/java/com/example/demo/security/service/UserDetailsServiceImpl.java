package com.example.demo.security.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.UserRepository;
import com.example.demo.model.AppUser;

// UserDetailsServiceImpl implements UserDetailsService and overrides loadUserByUsername() method.
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	/**
	 * loadUserByUsername method finds a record from users database tables to build a
	 * UserDetails object for authentication.
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		AppUser user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found => username or email : " + username));
				
		return UserPrinciple.build(user);
	}

}
