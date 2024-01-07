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
public class DiagnosticDto {

	Integer DiagId;
	
	@NotNull(message="painLevel est obligatoire !!")
	String painLevel;
	
	@NotNull(message="painLocations est obligatoire !!")
	String painLocations;
	
	@NotNull(message="symptoms est obligatoire !!")
	String symptoms;
	
	@NotNull(message="le nom est obligatoire !!")
	String painWorse;
	
	@NotNull(message="painWorse est obligatoire !!")
	String fellings;
	
	Date diagnosticDate;
	
	FemmeDto femme;
	
}
