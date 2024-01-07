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

import com.fstg.painCare.dto.ReponseDto;
import com.fstg.painCare.service.facade.ReponseService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("reponses")
@AllArgsConstructor
public class ReponseController {

	private ReponseService reponseService;
	
	@GetMapping("")
	public ResponseEntity<List<ReponseDto>> findAll(){
			return new ResponseEntity<>(reponseService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<ReponseDto> save( @Valid @RequestBody() ReponseDto reponseDto) throws NotFoundException{
		
		ReponseDto saved = reponseService.save(reponseDto);
		
		return ResponseEntity.accepted().body(saved);
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<ReponseDto> update(@Valid @RequestBody ReponseDto reponseDto, @PathVariable Integer id ) throws NotFoundException {
		
		ReponseDto updated = reponseService.update(reponseDto, id);
		return ResponseEntity.accepted().body(updated);
		
	}
	
	@GetMapping("/id/{id}")
    public ResponseEntity<ReponseDto> findById( @PathVariable Integer id) {
		
		ReponseDto ReponseDto = reponseService.findById( id );
    	return ResponseEntity.ok(ReponseDto);
    }
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> delete( @PathVariable Integer id ) {
		
		reponseService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
