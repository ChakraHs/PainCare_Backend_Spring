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

import com.fstg.painCare.dto.QuestionDto;
import com.fstg.painCare.service.facade.QuestionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("questions")
@AllArgsConstructor
public class QuestionController {

	private QuestionService questionService;
	
	@GetMapping("")
	public ResponseEntity<List<QuestionDto>> findAll(){
			return new ResponseEntity<>(questionService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<QuestionDto> save( @Valid @RequestBody() QuestionDto questionDto){
		
		QuestionDto saved = questionService.save(questionDto);
		
		return ResponseEntity.accepted().body(saved);
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<QuestionDto> update(@Valid @RequestBody QuestionDto questionDto, @PathVariable Integer id) throws NotFoundException {
		QuestionDto updated = questionService.update(questionDto, id);
		return ResponseEntity.accepted().body(updated);
	}
	
	@GetMapping("/id/{id}")
    public ResponseEntity<QuestionDto> findById( @PathVariable("id") Integer id) {
		QuestionDto questionDto = questionService.findById(id);
    	return ResponseEntity.ok(questionDto);
    }
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> delete( @PathVariable("id") Integer id) {
		questionService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
