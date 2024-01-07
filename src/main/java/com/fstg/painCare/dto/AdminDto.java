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
public class AdminDto {

	Integer adminId;
	
	@NotNull(message="le nom est obligatoire !!")
	String nom ;
	
	@NotNull(message="le prenom est obligatoire !!")
	String  prenom;
	
	@NotNull(message="l'email et le mots de pass sont obligatoires !!")
	UserDto user;
	
}
