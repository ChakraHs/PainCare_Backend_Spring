package com.fstg.painCare.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("client")
public class HelloWorld {
	
	@GetMapping("hello")
    public String sayHello() {
        return "Hello, World!";
    }

}
