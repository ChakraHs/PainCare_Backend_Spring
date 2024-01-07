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

import com.fstg.painCare.dto.AdminDto;
import com.fstg.painCare.service.facade.AdminService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("admins")
@AllArgsConstructor
public class AdminController {

	private AdminService adminService;
	
	@GetMapping("")
	public ResponseEntity<List<AdminDto>> findAll(){
			return new ResponseEntity<>(adminService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<AdminDto> save( @Valid @RequestBody() AdminDto adminDto){
		
		AdminDto saved = adminService.save(adminDto);
		return ResponseEntity.accepted().body(saved);
		
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<AdminDto> update(@Valid @RequestBody AdminDto adminDto, @PathVariable Integer id) throws NotFoundException {

		AdminDto updated = adminService.update(adminDto, id);
		return ResponseEntity.accepted().body(updated);
	}
	
	@GetMapping("/id/{id}")
    public ResponseEntity<AdminDto> findById( @PathVariable("id") Integer id) {
		AdminDto AdminDto = adminService.findById(id);
    	return ResponseEntity.ok(AdminDto);
    }
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> delete( @PathVariable("id") Integer id) {
		adminService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
