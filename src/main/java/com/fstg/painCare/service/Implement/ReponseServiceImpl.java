package com.fstg.painCare.service.Implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.fstg.painCare.dao.ReponseDao;
import com.fstg.painCare.dto.QuestionDto;
import com.fstg.painCare.dto.ReponseDto;
import com.fstg.painCare.exception.EntityNotFoundException;
import com.fstg.painCare.models.ReponseEntity;
import com.fstg.painCare.service.facade.QuestionService;
import com.fstg.painCare.service.facade.ReponseService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReponseServiceImpl implements ReponseService {

	private ReponseDao reponseDao;
	private QuestionService questionService;
	private ModelMapper modelMapper;
	
	@Override
	public List<ReponseDto> findAll() {

		return reponseDao
				.findAll()
				.stream().map( el->modelMapper.map(el, ReponseDto.class) )
				.collect(Collectors.toList())
				;
	}

	@Override
	public ReponseDto save(ReponseDto reponseDto) {

		Integer questionId = reponseDto.getQuestion().getQuestionId();
		QuestionDto questionDto = questionService.findById(questionId);
		
		if(questionDto != null) {
			
			reponseDto.setQuestion(questionDto);
			ReponseEntity reponseEntity = modelMapper.map(reponseDto, ReponseEntity.class);
			
			ReponseEntity saved = reponseDao.save( reponseEntity );
			
			return modelMapper.map(saved, ReponseDto.class);
		}else {
			throw new EntityNotFoundException("Question Not found");
		}
		
	}

	@Override
	public ReponseDto update(ReponseDto reponseDto, Integer id) throws NotFoundException {

		Optional<ReponseEntity> optional = reponseDao.findById(id);
		
		if(optional.isPresent()) {
			
			ReponseEntity reponseEntity = modelMapper.map(reponseDto, ReponseEntity.class);
			reponseEntity.setReponseId(id);
			reponseEntity.setQuestion(optional.get().getQuestion());
			ReponseEntity updated = reponseDao.save( reponseEntity );
			return modelMapper.map(updated, ReponseDto.class);
			
		}else {
			throw new EntityNotFoundException("reponse Not found");
		}
	}

	@Override
	public ReponseDto findById(Integer id) {

		ReponseEntity reponseEntity = reponseDao.findById(id).orElseThrow( ()->new EntityNotFoundException("reponse Not Found"));
		
		return modelMapper.map(reponseEntity , ReponseDto.class);
	}

	@Override
	public void delete(Integer id) {
		
		ReponseEntity reponseEntity = reponseDao.findById(id).orElseThrow( ()->new EntityNotFoundException("reponse Not Found"));
		reponseDao.delete(reponseEntity);
	}

}
