package com.fstg.painCare.service.Implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.fstg.painCare.dao.TypeQuestionDao;
import com.fstg.painCare.dto.TypeQuestionDto;
import com.fstg.painCare.exception.EntityNotFoundException;
import com.fstg.painCare.models.TypeQuestionEntity;
import com.fstg.painCare.service.facade.TypeQuestionService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TypeQuestionServiceImpl implements TypeQuestionService {

	private TypeQuestionDao typeDao;
	private ModelMapper modelMapper ;
	
	@Override
	public List<TypeQuestionDto> findAll() {
		
		return typeDao
				.findAll()
				.stream().map( el->modelMapper.map(el, TypeQuestionDto.class) )
				.collect(Collectors.toList())
				;
		
	}

	@Override
	public TypeQuestionDto save(TypeQuestionDto typeDto) {
		
		TypeQuestionEntity typeQuestionEntity = modelMapper.map(typeDto, TypeQuestionEntity.class);
		TypeQuestionEntity saved = typeDao.save(typeQuestionEntity);
		
		return modelMapper.map(saved, TypeQuestionDto.class);
		
	}

	@Override
	public TypeQuestionDto update(TypeQuestionDto typeDto, Integer id) throws NotFoundException {

		Optional<TypeQuestionEntity> typeOptional = typeDao.findById(id);
		
		if(typeOptional.isPresent()) {
			
			TypeQuestionEntity typeQuestionEntity = modelMapper.map(typeDto, TypeQuestionEntity.class);
			typeQuestionEntity.setTypeId(id);
			TypeQuestionEntity updated = typeDao.save(typeQuestionEntity);
			return modelMapper.map(updated, TypeQuestionDto.class);
			
		}else {
			throw new EntityNotFoundException("Type Not found");
		}
	}

	@Override
	public TypeQuestionDto findById(Integer id) {
		
		TypeQuestionEntity typeQuestionEntity = typeDao.findById(id).orElseThrow( ()->new EntityNotFoundException("Twpe Not Found"));
		
		return modelMapper.map(typeQuestionEntity , TypeQuestionDto.class);
		
	}

	@Override
	public void delete(Integer id) {

		TypeQuestionEntity typeQuestionEntity = typeDao.findById(id).orElseThrow( ()->new EntityNotFoundException("Twpe Not Found"));
		typeDao.delete(typeQuestionEntity);
		
	}

}
