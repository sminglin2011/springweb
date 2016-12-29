package com.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.service.UserService;

@Controller
public class UserController {
	
	Logger log = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/user_main.htm")
	public ModelAndView user_main() {
		log.debug("user_main");
		List list = userService.fetchUserList();
		log.debug("list = " + list);
		return new ModelAndView("user/user_main", "list", list);
	}
	
	@RequestMapping(value="/user_main_datatable.htm")
	public ModelAndView user_main2() {
		log.debug("user_main_datatable");
		List list = userService.fetchUserList();
		log.debug("list = " + list);
		return new ModelAndView("user/user_main_datatable", "list", list);
	}
	
	@RequestMapping(value="/new_user.htm")
	public ModelAndView new_user() {
		
		return new ModelAndView("user/new_user", "list", "");
	}
}
