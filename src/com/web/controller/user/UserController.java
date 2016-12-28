package com.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	@RequestMapping(value="/user_main.htm")
	public String user_main() {
		
		return "user/user_main";
	}
}
