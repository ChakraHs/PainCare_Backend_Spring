package com.fstg.painCare.dto.auth;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtResponseDto {

	String token;
	String type = "Bearer";
	Integer id;
	String email;
	List<String> roles;
	String message;
	public JwtResponseDto(String token, Integer id, String email, List<String> roles , String message) {
		super();
		this.token = token;
		this.id = id;
		this.email = email;
		this.roles = roles;
		this.message= message;
	}
	
	
}
