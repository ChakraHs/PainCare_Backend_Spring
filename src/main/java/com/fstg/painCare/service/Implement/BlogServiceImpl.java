package com.fstg.painCare.service.Implement;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.fstg.painCare.dao.BlogDao;
import com.fstg.painCare.dto.BlogDto;
import com.fstg.painCare.dto.FemmeDto;
import com.fstg.painCare.exception.EntityNotFoundException;
import com.fstg.painCare.models.BlogEntity;
import com.fstg.painCare.models.FemmeEntity;
import com.fstg.painCare.service.facade.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

	private BlogDao blogDao;
	private ModelMapper modelMapper;
	
	@Value("${upload.dir}") // Specify the directory where images will be stored
    private String uploadDir;
	
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
	public BlogDto save(BlogDto blogDto ) throws IOException {
		
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

	@Override
	public List<BlogDto> findbyFemme(FemmeDto femmeDto) {
		
		FemmeEntity femmeEntity = modelMapper.map(femmeDto, FemmeEntity.class);
		
		List<BlogDto> dtos = blogDao
				.findByFemme(femmeEntity)
				.stream().map( el->modelMapper.map(el, BlogDto.class) )
				.collect(Collectors.toList());
		
		dtos.forEach(el -> el.setNbrCommantaire(el.getCommantaires().size()));
		
		return dtos;
	}

	@Override
	public Resource loadImageAsResource(String imageName) {

		try {
            Resource resource = new ClassPathResource("static/image/" + imageName);
            
            if (resource.exists()) {
            	
                return resource;
            } else {
                // Gérer le cas où l'image n'est pas trouvée
                // Vous pouvez retourner une image par défaut ou générer une réponse d'erreur
                // selon vos besoins.
                return null;
            }
        } catch (Exception ex) {
            // Gérer les exceptions liées au chargement de l'image
            return null;
        }
	}

	
	
}
