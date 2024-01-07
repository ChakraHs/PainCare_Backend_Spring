package com.fstg.painCare.dto;

import java.util.Date;

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
public class TestDto {

	Integer testId ;
	
	@NotNull(message="la reponse est obligatoire !!")
	String reponse;
	
	QuestionDto question ;
	
	Date testDate;
	
	FemmeDto femme;
}
