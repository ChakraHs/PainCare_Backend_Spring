package com.fstg.painCare.service.Implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.fstg.painCare.dao.RoleDao;
import com.fstg.painCare.dto.RoleDto;
import com.fstg.painCare.exception.EntityNotFoundException;
import com.fstg.painCare.models.RoleEntity;
import com.fstg.painCare.service.facade.RoleService;

@Service
public class RoleServicImpl implements RoleService {

	private RoleDao roleDao;
	private ModelMapper modelMapper;
	
	public RoleServicImpl(RoleDao roleDao, ModelMapper modelMapper) {
		super();
		this.roleDao = roleDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<RoleDto> findAll() {
		return roleDao
				.findAll()
				.stream().map( el->modelMapper.map(el, RoleDto.class) )
				.collect(Collectors.toList())
				;
	}

	@Override
	public RoleDto save(RoleDto roleDto) {
		
		RoleEntity roleEntity = modelMapper.map(roleDto, RoleEntity.class);
		RoleEntity saved = roleDao.save(roleEntity);
		
		return modelMapper.map(saved, RoleDto.class);
	}

	@Override
	public RoleDto update(RoleDto roleDto, Integer id) throws NotFoundException {
		
		Optional<RoleEntity> roleOptional = roleDao.findById(id);
		
		if(roleOptional.isPresent()) {
			
			RoleEntity roleEntity = modelMapper.map(roleDto, RoleEntity.class);
			roleEntity.setRoleId(id);
			RoleEntity updated = roleDao.save(roleEntity);
			return modelMapper.map(updated, RoleDto.class);
			
		}else {
			throw new EntityNotFoundException("Role Not found");
		}
	}

	@Override
	public RoleDto findById(Integer id) {
		
		RoleEntity roleEntity = roleDao.findById(id).orElseThrow( ()->new EntityNotFoundException("Role Not Found"));
		
		return modelMapper.map(roleEntity , RoleDto.class);
	}

	@Override
	public void delete(Integer id) {
		
		RoleEntity roleEntity = roleDao.findById(id).orElseThrow( ()->new EntityNotFoundException("Role Not Found"));
		roleDao.delete(roleEntity);
		
	}

	@Override
	public RoleDto findByName(String name) {
		
		RoleEntity roleEntity = roleDao.findByName(name);
		return modelMapper.map( roleEntity, RoleDto.class );
	}

}
