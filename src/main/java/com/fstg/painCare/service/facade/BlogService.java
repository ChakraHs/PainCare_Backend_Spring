package com.fstg.painCare.service.facade;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.fstg.painCare.dto.BlogDto;

public interface BlogService {

	List<BlogDto> findAll();
	
	BlogDto save( BlogDto blogDto );
	
	BlogDto update( BlogDto blogDto , Integer id ) throws NotFoundException;
	
	BlogDto findById( Integer id );
	
	void delete( Integer id );
	
}
