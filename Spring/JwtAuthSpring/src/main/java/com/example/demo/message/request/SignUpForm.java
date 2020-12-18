package com.example.demo.message.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * SignUpForm includes:
		name
		username
		email
		role
		password
		
 * @author asus
 *
 */

@Setter
@Getter
public class SignUpForm {
	
	@NotBlank
    @Size(min = 3, max = 50)
    private String name;
 
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;
 
    @NotBlank
    @Size(max = 60)
    @Email
    private String email;
    
    private Set<String> role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

}
