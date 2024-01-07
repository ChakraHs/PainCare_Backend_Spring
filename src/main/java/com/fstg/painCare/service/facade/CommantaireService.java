package com.fstg.painCare.service.facade;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.fstg.painCare.dto.CommantaireDto;

public interface CommantaireService {

	List<CommantaireDto> findAll();
	
	CommantaireDto save( CommantaireDto commantaireDto )throws NotFoundException;
	
	CommantaireDto update( CommantaireDto commantaireDto , Integer id ) throws NotFoundException;
	
	CommantaireDto findById( Integer id );
	
	void delete( Integer id );
	
}
