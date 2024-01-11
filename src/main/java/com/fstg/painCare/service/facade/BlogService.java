package com.fstg.painCare.service.facade;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.fstg.painCare.dto.BlogDto;
import com.fstg.painCare.dto.FemmeDto;

public interface BlogService {

	List<BlogDto> findAll();
	
	BlogDto save( BlogDto blogDto ) throws IOException;
	
	BlogDto update( BlogDto blogDto , Integer id ) throws NotFoundException;
	
	BlogDto findById( Integer id );
	
	List<BlogDto> findbyFemme( FemmeDto femmeDto );
	
	Resource loadImageAsResource(String imageName);
	
	void delete( Integer id );
	
}
