package com.fstg.painCare.service.facade;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.fstg.painCare.dto.FemmeDto;
import com.fstg.painCare.dto.UserDto;

public interface FemmeService {

	List<FemmeDto> findAll();
	
	FemmeDto save( FemmeDto femmeDto );
	
	FemmeDto saveWithUser(FemmeDto femmeDto);
	
	FemmeDto update( FemmeDto femmeDto , Integer id ) throws NotFoundException;
	
	FemmeDto findById( Integer id );
	
	FemmeDto findByUser( Integer id );
	
	void delete( Integer id );
	
}
