package com.example.demo.message.response;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * JwtResponse object will be returned by SpringBoot server once an authentication is successful, it contains:
 *  JWT Token
	Schema Type of Token
	Username
	Array of User’s Authorities
	
 * @author asus
 *
 */
public class JwtResponse {
	  
	private String token;
	private String type = "Bearer";
	private String username;
	private Collection<? extends GrantedAuthority> authorities;
	
	public JwtResponse(String accessToken, String username, Collection<? extends GrantedAuthority> authorities) {
	    this.token = accessToken;
	    this.username = username;
	    this.authorities = authorities;
	  }
	 
	  public String getAccessToken() {
	    return token;
	  }
	 
	  public void setAccessToken(String accessToken) {
	    this.token = accessToken;
	  }
	 
	  public String getTokenType() {
	    return type;
	  }
	 
	  public void setTokenType(String tokenType) {
	    this.type = tokenType;
	  }
	 
	  public String getUsername() {
	    return username;
	  }
	 
	  public void setUsername(String username) {
	    this.username = username;
	  }
	  
	  public Collection<? extends GrantedAuthority> getAuthorities() {
	    return authorities;
	  }

	
}
