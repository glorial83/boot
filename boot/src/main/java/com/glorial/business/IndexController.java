package com.glorial.business;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping(value= {"/index", "/login", "/"})
	public String loginpage() {
		return "login";
	}

}
