package com.fstg.painCare.dto;



import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlogDto {

	Integer blogId;
	
	@NotNull(message="le titre est obligatoire !!")
	@Size(min=5,max=20 ,message="la nombre de caractere doit compris entre 5 et 20")
	String titre;
	
	@NotNull(message="l'image est obligatoire !!")
	String image;
	
	@NotNull(message="la description est obligatoire !!")
	@NotBlank(message="la description est obligatoire !!")
	@Size(min=5,max=20 ,message="la nombre de caractere doit compris entre 5 et 20")
	String description;
	
	Date blogDate;
	
	FemmeDto femme ;
}
