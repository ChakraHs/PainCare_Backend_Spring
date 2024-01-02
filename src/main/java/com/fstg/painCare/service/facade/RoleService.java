package com.fstg.painCare.service.facade;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.fstg.painCare.dto.RoleDto;

public interface RoleService {

	List<RoleDto> findAll();
	
	RoleDto findByName(String name);
	
	RoleDto save( RoleDto roleDto );
	
	RoleDto update( RoleDto roleDto , Integer id ) throws NotFoundException;
	
	RoleDto findById( Integer id );
	
	void delete( Integer id );
	
}
