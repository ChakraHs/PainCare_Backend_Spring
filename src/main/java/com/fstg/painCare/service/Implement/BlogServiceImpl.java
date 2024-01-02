package com.fstg.painCare.service.Implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.fstg.painCare.dao.BlogDao;
import com.fstg.painCare.dto.BlogDto;
import com.fstg.painCare.models.BlogEntity;
import com.fstg.painCare.service.facade.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

	private BlogDao blogDao;
	private ModelMapper modelMapper;
	
	public BlogServiceImpl(BlogDao blogDao, ModelMapper modelMapper) {
		super();
		this.blogDao = blogDao;
		this.modelMapper = modelMapper;
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
		
		BlogEntity blogEntity = modelMapper.map(blogDto, BlogEntity.class);
		BlogEntity saved = blogDao.save(blogEntity);
		
		return modelMapper.map(saved,BlogDto.class );
	}

	@Override
	public BlogDto update(BlogDto blogDto, Integer id) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BlogDto findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	
	
}
