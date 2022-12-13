package com.shopme.admin;

import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControler {

	@GetMapping("")
	public String viewHomePage(){
		return "index";
	}	
	
	@GetMapping("/login")
	public String viewLoginPage(){
		return "login";
	}	
}
