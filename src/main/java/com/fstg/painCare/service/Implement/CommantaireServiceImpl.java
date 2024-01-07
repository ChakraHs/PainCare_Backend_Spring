package com.fstg.painCare.service.Implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fstg.painCare.dao.CommantaireDao;
import com.fstg.painCare.dto.BlogDto;
import com.fstg.painCare.dto.CommantaireDto;
import com.fstg.painCare.dto.FemmeDto;
import com.fstg.painCare.exception.EntityNotFoundException;
import com.fstg.painCare.models.BlogEntity;
import com.fstg.painCare.models.CommantaireEntity;
import com.fstg.painCare.models.FemmeEntity;
import com.fstg.painCare.service.facade.BlogService;
import com.fstg.painCare.service.facade.CommantaireService;
import com.fstg.painCare.service.facade.FemmeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CommantaireServiceImpl implements CommantaireService{

	private CommantaireDao commantaireDao;
	private FemmeService femmeService;
	private BlogService blogService;
	private ModelMapper modelMapper;
	private static final Logger logger = LoggerFactory.getLogger(CommantaireServiceImpl.class);


	@Override
	public List<CommantaireDto> findAll() {
		return commantaireDao
				.findAll()
				.stream().map( el->modelMapper.map(el, CommantaireDto.class) )
				.collect(Collectors.toList())
				;
	}

	@Override
	@Transactional
	public CommantaireDto save(CommantaireDto commantaireDto) throws NotFoundException {
		
		Integer femmeId = commantaireDto.getFemme().getFemmeId();
		Integer blogId = commantaireDto.getBlog().getBlogId();
		CommantaireEntity commantaireEntity = modelMapper.map(commantaireDto, CommantaireEntity.class);  
		
		FemmeDto femmeDto = femmeService.findById( femmeId );
		BlogDto blogDto = blogService.findById( blogId );
		
		if(femmeDto != null && blogDto != null) {
			
			commantaireEntity.setFemme( modelMapper.map(femmeDto, FemmeEntity.class) );
			commantaireEntity.setBlog( modelMapper.map( blogDto , BlogEntity.class ) );
			
			try {
				
				CommantaireEntity saved = commantaireDao.save(commantaireEntity);
				return modelMapper.map(saved, CommantaireDto.class);
				
			} catch (Exception e) {
				logger.error("An error occurred while saving the comment", e);
				throw new RuntimeException("Error saving comment", e);
			}
		}else {
			throw new EntityNotFoundException("Femme or blog Not found");
		}
	}

	@Override
	public CommantaireDto update(CommantaireDto commantaireDto, Integer id) throws NotFoundException {
		
		Optional<CommantaireEntity> optional = commantaireDao.findById(id);
		
		if(optional.isPresent()) {
			
			CommantaireEntity commantaireEntity = modelMapper.map(commantaireDto, CommantaireEntity.class);
			FemmeEntity femme = optional.get().getFemme();
			BlogEntity blog = optional.get().getBlog();
			
			commantaireEntity.setCommantaireId(id);
			commantaireEntity.setBlog(blog);
			commantaireEntity.setFemme(femme);
			
			CommantaireEntity updated = commantaireDao.save(commantaireEntity);
			return modelMapper.map(updated, CommantaireDto.class);
			
		}else {
			throw new EntityNotFoundException("Commantaire Not found");
		}
	}

	@Override
	public CommantaireDto findById(Integer id) {
		
		CommantaireEntity commantaireEntity = commantaireDao.findById(id).orElseThrow( ()->new EntityNotFoundException("Commantaire Not Found"));
		
		return modelMapper.map(commantaireEntity, CommantaireDto.class);
	}

	@Override
	public void delete(Integer id) {
		
		CommantaireEntity commantaireEntity = commantaireDao.findById(id).orElseThrow( ()->new EntityNotFoundException("Commantaire Not Found"));
		commantaireDao.delete(commantaireEntity);
		
	}

}
