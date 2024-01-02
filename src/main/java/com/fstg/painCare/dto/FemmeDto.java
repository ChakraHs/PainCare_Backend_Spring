package com.fstg.painCare.dto;

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
	
	String nom;
	
	String prenom;
	
	String profil;
	
	String ville;
	
	String rue_adresse;
	
	String numero_adresse;
	
	String numero_telephone;
	
	String numero_cin;
	
	UserDto user ;
	
}
