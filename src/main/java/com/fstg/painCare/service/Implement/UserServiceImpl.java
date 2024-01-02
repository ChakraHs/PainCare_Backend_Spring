package com.fstg.painCare.service.Implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.fstg.painCare.dao.UserDao;
import com.fstg.painCare.dto.RoleDto;
import com.fstg.painCare.dto.UserDto;
import com.fstg.painCare.exception.EntityNotFoundException;
import com.fstg.painCare.models.UserEntity;
import com.fstg.painCare.service.facade.RoleService;
import com.fstg.painCare.service.facade.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	private ModelMapper modelMapper;
	private RoleService roleService;
	
	public UserServiceImpl(UserDao userDao, ModelMapper modelMapper, RoleService roleService) {
		super();
		this.userDao = userDao;
		this.modelMapper = modelMapper;
		this.roleService = roleService;
		
	}

	@Override
	public List<UserDto> findAll() {
		return userDao
				.findAll()
				.stream().map( el->modelMapper.map(el, UserDto.class) )
				.collect(Collectors.toList())
				;
	}

	@Override
	public UserDto save(UserDto userDto) {
		
		RoleDto roleDto = roleService.findById(1);
		userDto.setRole(roleDto);
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		UserEntity saved = userDao.save(userEntity);
		
		return modelMapper.map(saved, UserDto.class);
	}

	@Override
	public UserDto update(UserDto userDto, Integer id) throws NotFoundException {
		
		RoleDto roleDto = this.findById(id).getRole();
		userDto.setRole(roleDto);
		Optional<UserEntity> userOptional = userDao.findById(id);
		
		if(userOptional.isPresent()) {
			
			UserEntity userEntity= modelMapper.map(userDto, UserEntity.class);
			userEntity.setUserId(id);
			UserEntity updated = userDao.save(userEntity);
			return modelMapper.map(updated, UserDto.class);
			
		}else {
			throw new EntityNotFoundException("User Not found");
		}
	}

	@Override
	public UserDto findById(Integer id) {
		
		UserEntity userEntity = userDao.findById(id).orElseThrow( ()->new EntityNotFoundException("User Not Found"));
		
		return modelMapper.map(userEntity , UserDto.class);
	}

	@Override
	public void delete(Integer id) {
		
		UserEntity userEntity = userDao.findById(id).orElseThrow( ()->new EntityNotFoundException("User Not Found"));
		userDao.delete(userEntity);
		
	}

	
	
}
