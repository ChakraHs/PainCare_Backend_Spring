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
public class QuestionDto {

	Integer questionId ;
	
	@NotNull(message="la question est obligatoire !!")
	String question;
	
	@NotNull(message="le type est obligatoire !!")
	TypeQuestionDto type;
	
}
