package com.fstg.painCare.service.Implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fstg.painCare.dao.TestDao;
import com.fstg.painCare.dto.DiagnosticDto;
import com.fstg.painCare.dto.FemmeDto;
import com.fstg.painCare.dto.QuestionDto;
import com.fstg.painCare.dto.TestDto;
import com.fstg.painCare.exception.EntityNotFoundException;
import com.fstg.painCare.models.FemmeEntity;
import com.fstg.painCare.models.QuestionEntity;
import com.fstg.painCare.models.TestEntity;
import com.fstg.painCare.service.facade.FemmeService;
import com.fstg.painCare.service.facade.QuestionService;
import com.fstg.painCare.service.facade.TestService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {

	private TestDao testDao;
	private FemmeService femmeService;
	private QuestionService questionService;
	private ModelMapper modelMapper;
	private static final Logger logger = LoggerFactory.getLogger(CommantaireServiceImpl.class);
	
	@Override
	public List<TestDto> findAll() {
		
		return testDao
				.findAll()
				.stream().map( el->modelMapper.map(el, TestDto.class) )
				.collect(Collectors.toList())
				;
	}
	@Override
	public TestDto save(TestDto testDto) {
		
		Integer femmeId = testDto.getFemme().getFemmeId();
		
		TestEntity testEntity = modelMapper.map(testDto, TestEntity.class);  
		
		FemmeDto femmeDto = femmeService.findById( femmeId );
		
		if(femmeDto != null ) {
			
			testEntity.setFemme( modelMapper.map(femmeDto, FemmeEntity.class) );
			
			try {
				
				TestEntity saved = testDao.save(testEntity);
				return modelMapper.map(saved, TestDto.class);
				
			} catch (Exception e) {
				logger.error("An error occurred while saving the comment", e);
				throw new RuntimeException("Error saving comment", e);
			}
		}else {
			throw new EntityNotFoundException("Femme or question Not found");
		}
		
	}
	@Override
	public TestDto update(TestDto testDto, Integer id) throws NotFoundException {

		Optional<TestEntity> optional = testDao.findById(id);
		
		if(optional.isPresent()) {
			
			TestEntity testEntity = modelMapper.map(testDto, TestEntity.class);
			FemmeEntity femme = optional.get().getFemme();
			
			testEntity.setTestId(id);
			testEntity.setFemme(femme);
			
			TestEntity updated = testDao.save(testEntity);
			return modelMapper.map(updated, TestDto.class);
			
		}else {
			throw new EntityNotFoundException("Test Not found");
		}
	}
	@Override
	public TestDto findById(Integer id) {

		TestEntity testEntity = testDao.findById(id).orElseThrow( ()->new EntityNotFoundException("Test Not Found"));
		
		return modelMapper.map(testEntity, TestDto.class);
	}
	@Override
	public void delete(Integer id) {
		
		TestEntity testEntity = testDao.findById(id).orElseThrow( ()->new EntityNotFoundException("Test Not Found"));
		testDao.delete(testEntity);
	}
	@Override
	public List<TestDto> findByFemme(Integer id) {
		
		FemmeDto dto = femmeService.findById(id);
		
		FemmeEntity femmeEntity = modelMapper.map(dto, FemmeEntity.class);
		Pageable pageable = PageRequest.of(0, 1);
		
		
		return testDao.findByFemmeOrderByTestDateDesc(femmeEntity, pageable)
				.stream().map( el->modelMapper.map(el, TestDto.class) )
				.collect(Collectors.toList());
	}
	
	
	
}
