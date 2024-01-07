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

import com.fstg.painCare.dto.TypeQuestionDto;
import com.fstg.painCare.service.facade.TypeQuestionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("typesQuestion")
@AllArgsConstructor
public class TypeQuestionController {

	private TypeQuestionService typeService;
	
	@GetMapping("")
	public ResponseEntity<List<TypeQuestionDto>> findAll(){
			return new ResponseEntity<>(typeService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<TypeQuestionDto> save( @Valid @RequestBody() TypeQuestionDto typeQuestionDto){
		
		TypeQuestionDto saved = typeService.save(typeQuestionDto);
		
		return ResponseEntity.accepted().body(saved);
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<TypeQuestionDto> update(@Valid @RequestBody TypeQuestionDto typeQuestionDto, @PathVariable Integer id) throws NotFoundException {
		TypeQuestionDto updated = typeService.update(typeQuestionDto, id);
		return ResponseEntity.accepted().body(updated);
	}
	
	@GetMapping("/id/{id}")
    public ResponseEntity<TypeQuestionDto> findById( @PathVariable("id") Integer id) {
		TypeQuestionDto typeQuestionDto = typeService.findById(id);
    	return ResponseEntity.ok(typeQuestionDto);
    }
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> delete( @PathVariable("id") Integer id) {
		typeService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
