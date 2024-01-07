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
public class CommantaireDto {
	
	Integer commantaireId ;
	
	FemmeDto femme;
	
	BlogDto blog;
	
	@NotNull(message="le message est obligatoire !!")
	String message ;
	
}
