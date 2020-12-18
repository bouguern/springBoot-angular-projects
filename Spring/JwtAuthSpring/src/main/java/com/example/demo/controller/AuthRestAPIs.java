package com.example.demo.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;



import com.example.demo.Repository.RoleRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.message.request.LoginForm;
import com.example.demo.message.request.SignUpForm;
import com.example.demo.message.response.JwtResponse;
import com.example.demo.message.response.ResponseMessage;
import com.example.demo.model.AppRole;
import com.example.demo.model.AppUser;
import com.example.demo.model.RoleName;
import com.example.demo.security.jwt.JwtProvider;

/**
 * AuthRestAPIs defines 2 APIs:
 * ==> /api/auth/signup: sign up
 * 		--> check username/email is already in use.
 * 		--> create User object
 * 		--> store to database
 * ==> /api/auth/signin: sign in
 * 		--> attempt to authenticate with AuthenticationManager bean.
 * 		--> add authentication object to SecurityContextHolder
 * 		--> Generate JWT token, then return JWT to client
 * @author asus
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtProvider jwtProvider;
	
	@PostMapping("/signin") 
		public ResponseEntity<?> authenticationUser(@Valid @RequestBody LoginForm loginRequest) {
		
			
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
					);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			String jwt = jwtProvider.generateJwtToken(authentication);
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			
			return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
			
		}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		
		if(userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"), HttpStatus.BAD_REQUEST);
		}
		
		if(userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"), HttpStatus.BAD_REQUEST);
		}
		
		// Creating user's account
		AppUser user = new AppUser(
					
					signUpRequest.getName(),
					signUpRequest.getUsername(),
					signUpRequest.getEmail(),
					passwordEncoder.encode(signUpRequest.getPassword())
					
				);
		
		Set<String> strRoles = signUpRequest.getRole();
		Set<AppRole> roles = new HashSet<>();
		
		strRoles.forEach(role -> {
			switch (role) {
			case "admin" : 
				AppRole adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN).orElseThrow(() -> 
				new RuntimeException("Fail! -> Cause: User Role not find admin"));
				roles.add(adminRole);break;
			case "manager" : 
				AppRole managerRole = roleRepository.findByName(RoleName.ROLE_MANAGER).orElseThrow(() -> 
				new RuntimeException("Fail! -> Cause: User Role not find manager"));
		        roles.add(managerRole);break;
			default:
		        AppRole userRole = roleRepository.findByName(RoleName.ROLE_USER)
		            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find default"));
		        roles.add(userRole);  
			}
		});
		
		user.setRoles(roles);
		userRepository.save(user);
		
		return new ResponseEntity<>(new ResponseMessage("User registered successfuly!"), HttpStatus.OK);
	}
	

}
