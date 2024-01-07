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

import com.fstg.painCare.dto.FemmeDto;
import com.fstg.painCare.dto.UserDto;
import com.fstg.painCare.service.facade.FemmeService;
import com.fstg.painCare.service.facade.UserService;

@RestController
@RequestMapping("femmes")
public class FemmeController {

	private FemmeService femmeService;
	private UserService userService;
	
	public FemmeController(FemmeService femmeService, UserService userService) {
		super();
		this.femmeService = femmeService;
		this.userService = userService;
	}
	
	@GetMapping("")
	public ResponseEntity<List<FemmeDto>> findAll(){
			return new ResponseEntity<>(femmeService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<FemmeDto> save( @Valid @RequestBody() FemmeDto femmeDto){
		
		if( femmeDto.getUser().getUserId() != null) {
			UserDto userDto = userService.findById( femmeDto.getUser().getUserId() );
			femmeDto.setUser(userDto);
			FemmeDto saved = femmeService.save(femmeDto);
			
			return ResponseEntity.accepted().body(saved);
		}else {

			FemmeDto saved = femmeService.saveWithUser(femmeDto);
			return ResponseEntity.accepted().body(saved);
			
		}
		
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<FemmeDto> update(@Valid @RequestBody FemmeDto femmeDto, @PathVariable Integer id) throws NotFoundException {

		FemmeDto updated = femmeService.update(femmeDto, id);
		return ResponseEntity.accepted().body(updated);
	}
	
	@GetMapping("/id/{id}")
    public ResponseEntity<FemmeDto> findById( @PathVariable("id") Integer id) {
		FemmeDto femmeDto = femmeService.findById(id);
    	return ResponseEntity.ok(femmeDto);
    }
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> delete( @PathVariable("id") Integer id) {
		femmeService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
