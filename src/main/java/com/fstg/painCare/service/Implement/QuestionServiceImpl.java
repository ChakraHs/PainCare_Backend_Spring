package com.fstg.painCare.service.Implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.fstg.painCare.dao.QuestionDao;
import com.fstg.painCare.dto.QuestionDto;
import com.fstg.painCare.dto.TypeQuestionDto;
import com.fstg.painCare.exception.EntityNotFoundException;
import com.fstg.painCare.models.QuestionEntity;
import com.fstg.painCare.service.facade.QuestionService;
import com.fstg.painCare.service.facade.TypeQuestionService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

	private QuestionDao questionDao;
	private TypeQuestionService typeService;
	private ModelMapper modelMapper;
	
	@Override
	public List<QuestionDto> findAll() {
		
		return questionDao
				.findAll()
				.stream().map( el->modelMapper.map(el, QuestionDto.class) )
				.collect(Collectors.toList())
				;
		
	}

	@Override
	public QuestionDto save(QuestionDto questionDto) {

		Integer typeId = questionDto.getType().getTypeId();
		TypeQuestionDto typeDto = typeService.findById(typeId);
		
		if(typeDto != null) {
			
			questionDto.setType(typeDto);
			QuestionEntity questionEntity = modelMapper.map(questionDto, QuestionEntity.class);
			System.out.println(questionEntity);
			QuestionEntity saved = questionDao.save(questionEntity);
			
			return modelMapper.map(saved, QuestionDto.class);
		}else {
			throw new EntityNotFoundException("Type Not found");
		}
		
	}

	@Override
	public QuestionDto update(QuestionDto questionDto, Integer id) throws NotFoundException {

		Optional<QuestionEntity> optional = questionDao.findById(id);
		
		if(optional.isPresent()) {
			
			Integer typeId = questionDto.getType().getTypeId();
			TypeQuestionDto typeDto = typeService.findById(typeId);
			
			if(typeDto != null) {
				
				questionDto.setType(typeDto);
				
				QuestionEntity questionEntity = modelMapper.map(questionDto, QuestionEntity.class);
				questionEntity.setQuestionId(id);
				QuestionEntity updated = questionDao.save(questionEntity);
				return modelMapper.map(updated, QuestionDto.class);
				
			}else {
				throw new EntityNotFoundException("Type Not found");
			}
			
		}else {
			throw new EntityNotFoundException("question Not found");
		}
	}

	@Override
	public QuestionDto findById(Integer id) {

		QuestionEntity questionEntity = questionDao.findById(id).orElseThrow( ()->new EntityNotFoundException("Question Not Found"));
		
		return modelMapper.map(questionEntity , QuestionDto.class);
	}

	@Override
	public void delete(Integer id) {
		
		QuestionEntity questionEntity = questionDao.findById(id).orElseThrow( ()->new EntityNotFoundException("Question Not Found"));
		questionDao.delete(questionEntity);
		
	}

}
