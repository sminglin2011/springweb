package com.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.service.TestService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class TestController {
	
	@Autowired
	private TestService testSrvice;

	@ResponseBody
	@RequestMapping(value="/returnJSP.htm")
	public String returnJsp(String name) {
//		JSONObject json = new JSONObject();
//		Map<String, String> map = new HashMap<>();
//		map.put("username", "user1");
//		List list = new ArrayList<>();
//		list.a
		testSrvice.getList();
		//testSrvice.saveTest();
		return "index";
	}
}
