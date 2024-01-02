package com.fstg.painCare.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("")
	public ResponseEntity<BlogDto> save( @Valid @RequestBody() BlogDto blogDto){
		
		System.out.println(blogDto);
		
		return null;
	}
	
}
