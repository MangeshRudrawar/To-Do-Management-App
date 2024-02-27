package com.mangesh.Mywebapp.hello;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class hello {
	@RequestMapping("/welcome")
	public String sayHello() {
		return("Welcome");
	}

}
