package com.fstg.painCare.service.facade;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.fstg.painCare.dto.TypeQuestionDto;

public interface TypeQuestionService {

	List<TypeQuestionDto> findAll();
	
	TypeQuestionDto save( TypeQuestionDto typeDto );
	
	TypeQuestionDto update( TypeQuestionDto typeDto , Integer id ) throws NotFoundException;
	
	TypeQuestionDto findById( Integer id );
	
	void delete( Integer id );
	
}
