package com.fstg.painCare.service.Implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.fstg.painCare.dao.FemmeDao;
import com.fstg.painCare.dto.FemmeDto;
import com.fstg.painCare.dto.UserDto;
import com.fstg.painCare.exception.EntityNotFoundException;
import com.fstg.painCare.models.FemmeEntity;
import com.fstg.painCare.models.UserEntity;
import com.fstg.painCare.service.facade.FemmeService;
import com.fstg.painCare.service.facade.UserService;

@Service
public class FemmeServiceImpl implements FemmeService {

	private FemmeDao femmeDao;
	private UserService userService;
	private ModelMapper modelMapper;
	
	public FemmeServiceImpl(FemmeDao femmeDao, ModelMapper modelMapper , UserService userService) {
		super();
		this.femmeDao = femmeDao;
		this.modelMapper = modelMapper;
		this.userService = userService;
	}

	@Override
	public List<FemmeDto> findAll() {
		return femmeDao
				.findAll()
				.stream().map( el->modelMapper.map(el, FemmeDto.class) )
				.collect(Collectors.toList())
				;
	}

	@Override
	public FemmeDto save(FemmeDto femmeDto) {

		FemmeEntity femmeEntity = modelMapper.map(femmeDto, FemmeEntity.class);
		FemmeEntity saved = femmeDao.save(femmeEntity);
		return modelMapper.map(saved, FemmeDto.class);
	}
	
	@Override
	public FemmeDto saveWithUser(FemmeDto femmeDto) {

		UserDto userDto = femmeDto.getUser();
		FemmeEntity femmeEntity = modelMapper.map(femmeDto, FemmeEntity.class);
		UserDto usersaved = userService.save(userDto);
		UserEntity userEntity = modelMapper.map(usersaved , UserEntity.class);
		femmeEntity.setUser(userEntity);
		
		FemmeEntity saved = femmeDao.save(femmeEntity);
		return modelMapper.map(saved, FemmeDto.class);
	}

	@Override
	public FemmeDto update(FemmeDto femmeDto, Integer id) throws NotFoundException {
		
		Optional<FemmeEntity> femmeOptional = femmeDao.findById(id);
		
		if(femmeOptional.isPresent()) {
			
			UserDto userDto = femmeDto.getUser();
			userDto.setUserId( femmeOptional.get().getUser().getUserId() );
			userDto.setPassword( femmeOptional.get().getUser().getPassword() );
			
			FemmeEntity femmeEntity = modelMapper.map(femmeDto, FemmeEntity.class);
			
			UserDto usersaved = userService.update( userDto , femmeOptional.get().getUser().getUserId() );
			
			UserEntity userEntity = modelMapper.map(usersaved , UserEntity.class);
			
			femmeEntity.setUser(userEntity);
			femmeEntity.setFemmeId(id);
			
			FemmeEntity updated = femmeDao.save(femmeEntity);
			return modelMapper.map(updated, FemmeDto.class);
			
		}else {
			throw new EntityNotFoundException("Role Not found");
		}
	}

	@Override
	public FemmeDto findById(Integer id) {

		FemmeEntity femmeEntity = femmeDao.findById(id).orElseThrow( ()->new EntityNotFoundException("Femme Not Found"));
		
		return modelMapper.map(femmeEntity , FemmeDto.class);
	}

	@Override
	public void delete(Integer id) {
		
		FemmeEntity femmeEntity = femmeDao.findById(id).orElseThrow( ()->new EntityNotFoundException("Femme Not Found"));
		userService.delete( femmeEntity.getUser().getUserId() );
		
	}

}
