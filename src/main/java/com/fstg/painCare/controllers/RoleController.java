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

import com.fstg.painCare.dto.RoleDto;
import com.fstg.painCare.service.facade.RoleService;

@RestController
@RequestMapping("roles")
public class RoleController {

	private RoleService roleService;

	public RoleController(RoleService roleService) {
		super();
		this.roleService = roleService;
	}
	
	@GetMapping("")
	public ResponseEntity<List<RoleDto>> findAll(){
			return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<RoleDto> save( @Valid @RequestBody() RoleDto roleDto){
		
		RoleDto saved = roleService.save(roleDto);
		
		return ResponseEntity.accepted().body(saved);
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<RoleDto> update(@Valid @RequestBody RoleDto roleDto, @PathVariable Integer id) throws NotFoundException {
		RoleDto updated = roleService.update(roleDto, id);
		return ResponseEntity.accepted().body(updated);
	}
	
	@GetMapping("/id/{id}")
    public ResponseEntity<RoleDto> findById( @PathVariable("id") Integer id) {
		RoleDto roleDto = roleService.findById(id);
    	return ResponseEntity.ok(roleDto);
    }
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> delete( @PathVariable("id") Integer id) {
		roleService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/name/{name}")
    public ResponseEntity<RoleDto> findByName( @PathVariable("name") String name) {
		RoleDto roleDto = roleService.findByName(name);
    	return ResponseEntity.ok(roleDto);
    }
	
	
}
