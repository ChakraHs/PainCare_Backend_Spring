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
public class FemmeDto {

	Integer femmeId ;
	
	@NotNull(message="le nom est obligatoire !!")
	String nom;
	
	@NotNull(message="le prenom est obligatoire !!")
	String prenom;
	
	@NotNull(message="le profil est obligatoire !!")
	String profil;
	
	@NotNull(message="la ville est obligatoire !!")
	String ville;
	
	@NotNull(message="la rue_adresse est obligatoire !!")
	String rue_adresse;
	
	@NotNull(message="la numero_adresse est obligatoire !!")
	String numero_adresse;
	
	@NotNull(message="le numero_telephone est obligatoire !!")
	String numero_telephone;
	
	@NotNull(message="la numero_cin est obligatoire !!")
	String numero_cin;
	
	@NotNull(message="le user est obligatoire !!")
	UserDto user ;
	
}
