package com.web.controller.user;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	Logger log = Logger.getLogger(UserController.class);

	@RequestMapping(value="/user_main.htm")
	public String user_main() {
		log.debug("user_main");

		return "user/user_main";
	}
}
