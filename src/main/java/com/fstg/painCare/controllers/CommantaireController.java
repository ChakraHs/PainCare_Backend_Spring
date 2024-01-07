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

import com.fstg.painCare.dto.CommantaireDto;
import com.fstg.painCare.service.facade.CommantaireService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("commataires")
@AllArgsConstructor
public class CommantaireController {

	private CommantaireService commantaireService;
	
	@GetMapping("")
	public ResponseEntity<List<CommantaireDto>> findAll(){
			return new ResponseEntity<>(commantaireService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<CommantaireDto> save( @Valid @RequestBody() CommantaireDto commantaireDto) throws NotFoundException{
		System.out.println(commantaireDto);
		CommantaireDto saved = commantaireService.save(commantaireDto);
		
		return ResponseEntity.accepted().body(saved);
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<CommantaireDto> update(@Valid @RequestBody CommantaireDto commantaireDto, @PathVariable Integer id ) throws NotFoundException {
		
		CommantaireDto updated = commantaireService.update(commantaireDto, id);
		return ResponseEntity.accepted().body(updated);
		
	}
	
	@GetMapping("/id/{id}")
    public ResponseEntity<CommantaireDto> findById( @PathVariable Integer id) {
		
		CommantaireDto commantaireDto = commantaireService.findById( id );
    	return ResponseEntity.ok(commantaireDto);
    }
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> delete( @PathVariable Integer id ) {
		
		commantaireService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
