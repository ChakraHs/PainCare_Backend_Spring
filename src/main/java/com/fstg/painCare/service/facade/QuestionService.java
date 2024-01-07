package com.fstg.painCare.service.facade;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.fstg.painCare.dto.QuestionDto;

public interface QuestionService {

	List<QuestionDto> findAll();
	
	QuestionDto save( QuestionDto questionDto );
	
	QuestionDto update( QuestionDto questionDto , Integer id ) throws NotFoundException;
	
	QuestionDto findById( Integer id );
	
	void delete( Integer id );
	
}
