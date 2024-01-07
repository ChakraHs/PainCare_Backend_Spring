package com.fstg.painCare.service.Implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public UserDto save(UserDto userDto) {
		
		RoleDto roleDto = roleService.findByName("ROLE_USER");
		
		// Ensure the UserDto has a roles set (initialized or created)
		if( userDto.getRole()== null ) {
			userDto.setRole(roleDto);
		}
		System.out.println("Before adding role: " + userDto);
		System.out.println("Roles set before adding role: " + userDto.getRole());
		
		
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		try {
			
			// Set the roles directly on the userEntity (to avoid detached entity issue)
			System.out.println(userEntity);
			
			UserEntity saved = userDao.save(userEntity);
			userDao.flush();
			
			return modelMapper.map(saved, UserDto.class);
		} catch (Exception e) {
			// Log or handle the exception as needed
	        e.printStackTrace(); // or log.error("Error saving user", e);
	        // Optionally, you can throw a custom exception or return a specific error response.
	        return null; 
		}
		
	}

	@Override
	public UserDto update(UserDto userDto, Integer id) throws NotFoundException {
		
		Optional<UserEntity> userOptional = userDao.findById(id);
		
		if(userOptional.isPresent()) {
			
			UserEntity userEntity= modelMapper.map(userDto, UserEntity.class);
			userEntity.setUserId(id);

			userEntity.setRole(userOptional.get().getRole());
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
