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

import com.fstg.painCare.dto.TestDto;
import com.fstg.painCare.service.facade.TestService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("tests")
@AllArgsConstructor
public class TestController {

	private TestService testService;
	
	@GetMapping("")
	public ResponseEntity<List<TestDto>> findAll(){
			return new ResponseEntity<>(testService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<TestDto> save( @Valid @RequestBody() TestDto testDto) throws NotFoundException{
		
		TestDto saved = testService.save(testDto);
		
		return ResponseEntity.accepted().body(saved);
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<TestDto> update(@Valid @RequestBody TestDto testDto, @PathVariable Integer id ) throws NotFoundException {
		
		TestDto updated = testService.update(testDto, id);
		return ResponseEntity.accepted().body(updated);
		
	}
	
	@GetMapping("/id/{id}")
    public ResponseEntity<TestDto> findById( @PathVariable Integer id) {
		
		TestDto TestDto = testService.findById( id );
    	return ResponseEntity.ok(TestDto);
    }
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> delete( @PathVariable Integer id ) {
		
		testService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/femme/{id}")
    public ResponseEntity<List<TestDto>> findByFemme( @PathVariable Integer id ) {
		
    	return ResponseEntity.ok(testService.findByFemme(id));
    	
    }
	
}
