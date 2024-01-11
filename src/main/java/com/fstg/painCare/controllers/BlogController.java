package com.fstg.painCare.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fstg.painCare.config.service.UserAuthentication;
import com.fstg.painCare.dto.BlogDto;
import com.fstg.painCare.dto.FemmeDto;
import com.fstg.painCare.service.facade.BlogService;
import com.fstg.painCare.service.facade.FemmeService;

@RestController
@RequestMapping("blogs")
public class BlogController {

	final static Logger log = LogManager.getLogger(BlogController.class);
	private BlogService blogService;
	private FemmeService femmeService;
	
	public BlogController(BlogService blogService, FemmeService femmeService) {
		super();
		this.blogService = blogService;
		this.femmeService = femmeService;
	}

	@Value("${upload.dir}") 
    private String uploadDir;
	
	@GetMapping("")
	public ResponseEntity<List<BlogDto>> findAll(){
			return new ResponseEntity<>(blogService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping(value = "",consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
	public ResponseEntity<BlogDto> save( @Valid @RequestParam("title")  String title , @RequestParam("description")  String description , @RequestPart("imageFile") MultipartFile imageFile)throws IOException{
		
		BlogDto blogDto = new BlogDto(null, title, null , description, null, null,null,null);
		
		if (imageFile == null) {
		      log.warn("Please select a file to upload");
		      return ResponseEntity.accepted().body(blogDto);
		}
		
		
		String fileId = UUID.randomUUID().toString().substring(0, 8);
		
		String filename = StringUtils.cleanPath(imageFile.getOriginalFilename());
		
		// Trouvez l'index du dernier point dans le nom du fichier
        int lastIndex = filename.lastIndexOf('.');
        
     // Extraire le nom de fichier sans l'extension
        String fileNameWithoutExtension = lastIndex >= 0 ? filename.substring(0, lastIndex) : filename;

        // Extraire l'extension du fichier
        String fileExtension = lastIndex >= 0 && lastIndex < filename.length() - 1
                ? filename.substring(lastIndex + 1)
                : "";

		
		// Ajoutez l'identifiant au nom du fichier
	    String newFileName = fileNameWithoutExtension + "-" + fileId + "."+fileExtension;
		
	    Path pathFile = Paths.get( uploadDir , newFileName);
		try {
			
			if (imageFile.isEmpty()) {
		        log.error("Failed to store empty file " + filename);
		        return ResponseEntity.accepted().body(blogDto);
		    }
			if (filename.contains("..")) {
		        // This is a security check
		        log.error("Cannot store file with relative path outside current directory " + filename);
		        return ResponseEntity.accepted().body(blogDto);
		    }
			try (InputStream inputStream = imageFile.getInputStream()) {
		        
		        // save file to target(server)
		        Files.copy(inputStream, pathFile, StandardCopyOption.REPLACE_EXISTING);

		        // read file to bytes
		        // Files.readAllBytes(pathFile);
		        
		        blogDto.setTitre(title);
		        blogDto.setImage(newFileName);
		        
		        UserAuthentication authController = new UserAuthentication();
		        Integer userId = authController.getUserIdAfterAuthentication();
		        FemmeDto femmeDto = femmeService.findByUser(userId);
		        
		        blogDto.setFemme(femmeDto);
		        
		        log.info("You successfully uploaded '" + filename + "'");
		        
		        BlogDto saved = blogService.save( blogDto );
		        
		        return ResponseEntity.accepted().body(saved);
		      }
			
		} catch (IOException e) {
			log.error("Failed to store file " + filename, e);
		}
//		BlogDto saved = blogService.save(blogDto,imageFile);
		return ResponseEntity.accepted().body(blogDto);
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<BlogDto> update(@Valid @RequestBody BlogDto blogDto, @PathVariable Integer id) throws NotFoundException {
		
		BlogDto updated = blogService.update(blogDto, id);
		return ResponseEntity.accepted().body(updated);
		
	}
	
	@GetMapping("/id/{id}")
    public ResponseEntity<BlogDto> findById( @PathVariable("id") Integer id) {
		BlogDto blogDto = blogService.findById(id);
    	return ResponseEntity.ok(blogDto);
    }
	
	@GetMapping("/getImage/{imageName}")
	public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
		
        Resource imageResource = blogService.loadImageAsResource(imageName);
        
        if (imageResource != null) {
            return ResponseEntity.ok().body(imageResource);
        } else {
            // Gérer le cas où l'image n'est pas trouvée
            // Vous pouvez retourner une image par défaut ou générer une réponse d'erreur
            // selon vos besoins.
            return ResponseEntity.notFound().build();
        }
    }
	
	
	@GetMapping("/myblogs")
    public ResponseEntity<List<BlogDto>> findByFemme( ) {
		
		UserAuthentication authController = new UserAuthentication();
        Integer userId = authController.getUserIdAfterAuthentication();
        FemmeDto femmeDto = femmeService.findByUser(userId);
		
		List<BlogDto> blogDtos = blogService.findbyFemme(femmeDto);
    	return ResponseEntity.ok(blogDtos);
    }
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> delete( @PathVariable("id") Integer id) {
		blogService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
