package com.example.demo.security.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * UserPrinciple is not used directly by Spring Security for security purposes.
It simply stores user information which is later encapsulated into Authentication objects. 
This allows non-security related user information (such as email addresses, 
telephone numbers etc) to be stored.
 * @author asus
 *
 */

@AllArgsConstructor
@Getter
public class UserPrinciple implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String username;
	private String email;
	
	@JsonIgnore
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public static UserPrinciple build(AppUser user) {
		List<GrantedAuthority> authorities = user.getRoles().stream().map(role -> 
		new SimpleGrantedAuthority(role.getName().name()) 
		).collect(Collectors.toList());
		
		return new UserPrinciple(
				user.getId(),
				user.getName(),
				user.getUsername(),
				user.getEmail(),
				user.getPassword(),
				authorities
				);
	}
	
	@Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }

}
