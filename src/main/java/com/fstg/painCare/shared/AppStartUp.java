package com.fstg.painCare.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fstg.painCare.dto.AdminDto;
import com.fstg.painCare.dto.FemmeDto;
import com.fstg.painCare.dto.RoleDto;
import com.fstg.painCare.dto.UserDto;
import com.fstg.painCare.service.facade.AdminService;
import com.fstg.painCare.service.facade.FemmeService;
import com.fstg.painCare.service.facade.RoleService;
import com.fstg.painCare.service.facade.UserService;

@Component
public class AppStartUp implements CommandLineRunner {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private FemmeService femmeService;
	
	@Override
	public void run(String... args) throws Exception {
		
		if(roleService.findAll().isEmpty()) {
			
			roleService.save(new RoleDto(null,"ROLE_USER"));
			roleService.save(new RoleDto(null,"ROLE_ADMIN"));
			
		}
		
		if(userService.findAll().isEmpty()) {
			
			
			UserDto userDto = new UserDto(null, null, "ismailtelhouni123@gmail.com", "123456");
			
			AdminDto adminDto = new AdminDto(null, "telhouni", "ismail", userDto);
			System.out.println("test 1");
			adminService.save(adminDto);
			System.out.println("test 2");
			UserDto userDto2 = new UserDto(null, null, "nouratelhouni123@gmail.com", "123456");
			
			FemmeDto femmeDto = new FemmeDto(null, "telhouni", "noura", "path", "berrechid", "Rue mohammed 5", "50", "0600112233", "WA241535", userDto2);
			
			femmeService.saveWithUser(femmeDto);
			
		}
		
	}

}
