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

import com.fstg.painCare.dao.DiagnosticDao;
import com.fstg.painCare.dto.DiagnosticDto;
import com.fstg.painCare.dto.FemmeDto;
import com.fstg.painCare.exception.EntityNotFoundException;
import com.fstg.painCare.models.DiagnosticEntity;
import com.fstg.painCare.models.FemmeEntity;
import com.fstg.painCare.service.facade.DiagnosticService;
import com.fstg.painCare.service.facade.FemmeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DiagnosticServiceImpl implements DiagnosticService {

	private DiagnosticDao diagnosticDao;
	private FemmeService femmeService;
	private ModelMapper modelMapper;
	private static final Logger logger = LoggerFactory.getLogger(CommantaireServiceImpl.class);
	
	@Override
	public List<DiagnosticDto> findAll() {

		return diagnosticDao
				.findAll()
				.stream().map( el->modelMapper.map(el, DiagnosticDto.class) )
				.collect(Collectors.toList())
				;
	}

	@Override
	public DiagnosticDto save(DiagnosticDto diagnosticDto) {

		Integer femmeId = diagnosticDto.getFemme().getFemmeId();
		
		DiagnosticEntity diagnosticEntity = modelMapper.map(diagnosticDto, DiagnosticEntity.class);  
		
		FemmeDto femmeDto = femmeService.findById( femmeId );
		
		if(femmeDto != null ) {
			
			diagnosticEntity.setFemme( modelMapper.map(femmeDto, FemmeEntity.class) );
			
			try {
				
				DiagnosticEntity saved = diagnosticDao.save(diagnosticEntity);
				return modelMapper.map(saved, DiagnosticDto.class);
				
			} catch (Exception e) {
				logger.error("An error occurred while saving the comment", e);
				throw new RuntimeException("Error saving comment", e);
			}
		}else {
			throw new EntityNotFoundException("Femme Not found");
		}
		
	}

	@Override
	public DiagnosticDto update(DiagnosticDto diagnosticDto, Integer id) throws NotFoundException {

		Optional<DiagnosticEntity> optional = diagnosticDao.findById(id);
		
		if(optional.isPresent()) {
			
			DiagnosticEntity diagnosticEntity = modelMapper.map(diagnosticDto, DiagnosticEntity.class);
			FemmeEntity femme = optional.get().getFemme();
			
			diagnosticEntity.setDiagId(id);
			diagnosticEntity.setFemme(femme);
			diagnosticEntity.setDiagnosticDate( optional.get().getDiagnosticDate() );
			
			DiagnosticEntity updated = diagnosticDao.save(diagnosticEntity);
			return modelMapper.map(updated, DiagnosticDto.class);
			
		}else {
			throw new EntityNotFoundException("test diagnostic Not found");
		}
	}

	@Override
	public DiagnosticDto findById(Integer id) {

		DiagnosticEntity diagnosticEntity = diagnosticDao.findById(id).orElseThrow( ()->new EntityNotFoundException("test diagnostic Not Found"));
		
		return modelMapper.map(diagnosticEntity , DiagnosticDto.class);
		
	}

	@Override
	public void delete(Integer id) {
		
		DiagnosticEntity diagnosticEntity = diagnosticDao.findById(id).orElseThrow( ()->new EntityNotFoundException("test diagnostic Not Found"));
		diagnosticDao.delete(diagnosticEntity);
	}

	@Override
	public List<DiagnosticDto> findByFemme(FemmeDto femmeDto) {
		
//		FemmeDto dto = femmeService.findById(femmeDto.getFemmeId());
		
		FemmeEntity femmeEntity = modelMapper.map(femmeDto, FemmeEntity.class);
		Pageable pageable = PageRequest.of(0, 10);
		return diagnosticDao.findByFemmeOrderByDiagnosticDateDesc(femmeEntity , pageable)
				.stream().map( el->modelMapper.map(el, DiagnosticDto.class) )
				.collect(Collectors.toList());
	}

}
