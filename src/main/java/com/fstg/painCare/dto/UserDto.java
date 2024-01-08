package com.fstg.painCare.dto;

import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

	Integer userId;
	
	RoleDto role;
	
	@NotNull(message="l'email est obligatoire !!")
	String email;
	
	@NotNull(message="le mots de pass est obligatoire !!")
	String password;
	
	FemmeDto femmeDto;
	
}
