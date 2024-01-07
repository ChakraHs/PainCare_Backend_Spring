package com.fstg.painCare.service.facade;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.fstg.painCare.dto.ReponseDto;

public interface ReponseService {

	List<ReponseDto> findAll();
	
	ReponseDto save( ReponseDto reponseDto );
	
	ReponseDto update( ReponseDto reponseDto , Integer id ) throws NotFoundException;
	
	ReponseDto findById( Integer id );
	
	void delete( Integer id );
	
}
