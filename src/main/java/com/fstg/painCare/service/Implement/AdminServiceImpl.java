package com.fstg.painCare.service.Implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fstg.painCare.dao.AdminDao;
import com.fstg.painCare.dto.AdminDto;
import com.fstg.painCare.dto.RoleDto;
import com.fstg.painCare.dto.UserDto;
import com.fstg.painCare.exception.EntityNotFoundException;
import com.fstg.painCare.models.AdminEntity;
import com.fstg.painCare.models.UserEntity;
import com.fstg.painCare.service.facade.AdminService;
import com.fstg.painCare.service.facade.RoleService;
import com.fstg.painCare.service.facade.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

	private AdminDao adminDao;
	private UserService userService;
	private ModelMapper modelMapper;
	private RoleService roleService;
	
	@Override
	public List<AdminDto> findAll() {
		
		return adminDao
				.findAll()
				.stream().map( el->modelMapper.map(el, AdminDto.class) )
				.collect(Collectors.toList())
				;
		
	}

	@Override
	@Transactional
	public AdminDto save(AdminDto adminDto) {

		
		RoleDto roleDto = roleService.findByName("ROLE_ADMIN");
		UserDto userDto = adminDto.getUser();
		
		userDto.setRole(roleDto);
		
		System.out.println("test 3");
		UserDto usersaved = userService.save(userDto);
		System.out.println("test 4");
		AdminEntity adminEntity = modelMapper.map(adminDto, AdminEntity.class);
		UserEntity userEntity = modelMapper.map(usersaved , UserEntity.class);
		
		adminEntity.setUser(userEntity);
		
		AdminEntity saved = adminDao.save(adminEntity);
		return modelMapper.map(saved, AdminDto.class);
	}

	@Override
	public AdminDto update(AdminDto adminDto, Integer id) throws NotFoundException {

		Optional<AdminEntity> optional = adminDao.findById(id);
		
		if(optional.isPresent()) {
			
			UserDto userDto = adminDto.getUser();
			
			userDto.setPassword( optional.get().getUser().getPassword() );
			
			AdminEntity adminEntity = modelMapper.map(adminDto, AdminEntity.class);
			
			
			UserDto usersaved = userService.update(userDto , optional.get().getUser().getUserId());
			
			UserEntity userEntity = modelMapper.map(usersaved , UserEntity.class);
			
			adminEntity.setUser(userEntity);
			adminEntity.setAdminId(id);
			
			AdminEntity updated = adminDao.save(adminEntity);
			return modelMapper.map(updated, AdminDto.class);
			
		}else {
			throw new EntityNotFoundException("User Not found");
		}
	}

	@Override
	public AdminDto findById(Integer id) {

		AdminEntity adminEntity = adminDao.findById(id).orElseThrow( ()->new EntityNotFoundException("User Not Found"));
		
		return modelMapper.map(adminEntity , AdminDto.class);
	}

	@Override
	public void delete(Integer id) {
		
		AdminEntity adminEntity = adminDao.findById(id).orElseThrow( ()->new EntityNotFoundException("User Not Found"));
		userService.delete(adminEntity.getUser().getUserId());
		
		
	}

}
