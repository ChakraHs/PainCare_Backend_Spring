package com.fstg.painCare.service.facade;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.fstg.painCare.dto.AdminDto;

public interface AdminService {

	List<AdminDto> findAll();
	
	AdminDto save( AdminDto adminDto );
	
	AdminDto update( AdminDto adminDto , Integer id ) throws NotFoundException;
	
	AdminDto findById( Integer id );
	
	void delete( Integer id );
	
}
