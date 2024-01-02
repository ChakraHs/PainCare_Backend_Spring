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
import com.fstg.painCare.dto.UserDto;
import com.fstg.painCare.service.facade.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	private UserService userService;

	
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("")
	public ResponseEntity<List<UserDto>> findAll(){
			return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<UserDto> save( @Valid @RequestBody() UserDto userDto){
		
		UserDto saved = userService.save(userDto);
		
		return ResponseEntity.accepted().body(saved);
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<UserDto> update(@Valid @RequestBody UserDto userDto, @PathVariable Integer id) throws NotFoundException {
		
		UserDto updated = userService.update(userDto, id);
		return ResponseEntity.accepted().body(updated);
		
	}
	
	@GetMapping("/id/{id}")
    public ResponseEntity<UserDto> findById( @PathVariable("id") Integer id) {
		UserDto UserDto = userService.findById(id);
    	return ResponseEntity.ok(UserDto);
    }
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> delete( @PathVariable("id") Integer id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
