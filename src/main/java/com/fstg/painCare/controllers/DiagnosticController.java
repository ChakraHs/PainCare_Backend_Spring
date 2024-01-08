package com.fstg.painCare.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fstg.painCare.dto.DiagnosticDto;
import com.fstg.painCare.dto.FemmeDto;
import com.fstg.painCare.service.facade.DiagnosticService;
import com.fstg.painCare.service.facade.FemmeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("diagnostics")
@AllArgsConstructor
public class DiagnosticController {

	private DiagnosticService diagnosticService;
	private FemmeService femmeService;
	
	@GetMapping("")
	public ResponseEntity<List<DiagnosticDto>> findAll(){
			return new ResponseEntity<>(diagnosticService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<DiagnosticDto> save( @Valid @RequestBody() DiagnosticDto diagnosticDto){
		
		DiagnosticDto saved = diagnosticService.save(diagnosticDto);
		
		return ResponseEntity.accepted().body(saved);
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<DiagnosticDto> update(@Valid @RequestBody DiagnosticDto diagnosticDto, @PathVariable Integer id) throws NotFoundException {
		DiagnosticDto updated = diagnosticService.update(diagnosticDto, id);
		return ResponseEntity.accepted().body(updated);
	}
	
	@GetMapping("/id/{id}")
    public ResponseEntity<DiagnosticDto> findById( @PathVariable("id") Integer id) {
		DiagnosticDto diagnosticDto = diagnosticService.findById(id);
    	return ResponseEntity.ok(diagnosticDto);
    }
	
	@GetMapping("/byfemme/{id}")
    public ResponseEntity<List<DiagnosticDto>> findByFemme( @PathVariable Integer id ) {
		
		System.out.println(id);
		FemmeDto dto = femmeService.findById( id );
    	return ResponseEntity.ok(diagnosticService.findByFemme(dto));
    	
    }
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> delete( @PathVariable("id") Integer id) {
		diagnosticService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
