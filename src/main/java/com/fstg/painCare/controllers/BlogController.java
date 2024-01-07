package com.fstg.painCare.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fstg.painCare.dto.BlogDto;
import com.fstg.painCare.service.facade.BlogService;

@RestController
@RequestMapping("blogs")
public class BlogController {

	private BlogService blogService;

	public BlogController(BlogService blogService) {
		super();
		this.blogService = blogService;
	}
	
	@GetMapping("")
	public ResponseEntity<List<BlogDto>> findAll(){
			return new ResponseEntity<>(blogService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<BlogDto> save( @Valid @RequestBody() BlogDto blogDto){
		
		BlogDto saved = blogService.save(blogDto);
		
		return ResponseEntity.accepted().body(saved);
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
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> delete( @PathVariable("id") Integer id) {
		blogService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
