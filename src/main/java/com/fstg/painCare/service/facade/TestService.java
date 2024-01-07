package com.fstg.painCare.service.facade;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.fstg.painCare.dto.TestDto;

public interface TestService {


	List<TestDto> findAll();
	
	TestDto save( TestDto testDto );
	
	TestDto update( TestDto testDto , Integer id ) throws NotFoundException;
	
	TestDto findById( Integer id );
	
	void delete( Integer id );
	
	
}
