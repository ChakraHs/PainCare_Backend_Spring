	package com.fstg.painCare.service.Implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.fstg.painCare.dao.BlogDao;
import com.fstg.painCare.dto.BlogDto;
import com.fstg.painCare.dto.FemmeDto;
import com.fstg.painCare.exception.EntityNotFoundException;
import com.fstg.painCare.models.BlogEntity;
import com.fstg.painCare.service.facade.BlogService;
import com.fstg.painCare.service.facade.FemmeService;

@Service
public class BlogServiceImpl implements BlogService {

	private BlogDao blogDao;
	private FemmeService femmeService;
	private ModelMapper modelMapper;
	
	public BlogServiceImpl(BlogDao blogDao, ModelMapper modelMapper,FemmeService femmeService) {
		super();
		this.blogDao = blogDao;
		this.modelMapper = modelMapper;
		this.femmeService = femmeService;
	}

	@Override
	public List<BlogDto> findAll() {
		
		return blogDao
				.findAll()
				.stream().map( el->modelMapper.map(el, BlogDto.class) )
				.collect(Collectors.toList())
				;
	}

	@Override
	public BlogDto save(BlogDto blogDto) {
		
		FemmeDto femmeDto = femmeService.findById(blogDto.getFemme().getFemmeId());
		blogDto.setFemme(femmeDto);
		BlogEntity blogEntity = modelMapper.map(blogDto, BlogEntity.class);
		BlogEntity saved = blogDao.save(blogEntity);
		
		return modelMapper.map(saved,BlogDto.class );
	}

	@Override
	public BlogDto update(BlogDto blogDto, Integer id) throws NotFoundException {
		
		Optional<BlogEntity> blogOptional = blogDao.findById(id);
		
		if(blogOptional.isPresent()) {
			
			BlogEntity blogEntity= modelMapper.map(blogDto, BlogEntity.class);
			blogEntity.setBlogId(id);
			blogEntity.setFemme( blogOptional.get().getFemme() );
			BlogEntity updated = blogDao.save(blogEntity);
			return modelMapper.map(updated, BlogDto.class);
			
		}else {
			throw new EntityNotFoundException("User Not found");
		}
	}

	@Override
	public BlogDto findById(Integer id) {
		
		BlogEntity blogEntity = blogDao.findById(id).orElseThrow( ()->new EntityNotFoundException("User Not Found"));
		
		return modelMapper.map(blogEntity , BlogDto.class);
	}

	@Override
	public void delete(Integer id) {
		
		BlogEntity blogEntity = blogDao.findById(id).orElseThrow( ()->new EntityNotFoundException("User Not Found"));
		blogDao.delete(blogEntity);
		
	}

	
	
}
