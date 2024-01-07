package com.fstg.painCare.service.facade;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.fstg.painCare.dto.DiagnosticDto;
import com.fstg.painCare.dto.FemmeDto;

public interface DiagnosticService {

	List<DiagnosticDto> findAll();
	
	DiagnosticDto save( DiagnosticDto diagnosticDto );
	
	List<DiagnosticDto> findByFemme( FemmeDto femmeDto );
	
	DiagnosticDto update( DiagnosticDto diagnosticDto , Integer id ) throws NotFoundException;
	
	DiagnosticDto findById( Integer id );
	
	void delete( Integer id );
	
}
