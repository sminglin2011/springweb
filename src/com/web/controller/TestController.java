package com.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.service.TestService;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@Controller
public class TestController {
	
	Logger log = Logger.getLogger(TestController.class);
	
	@Autowired
	private TestService testSrvice;

	@ResponseBody
	@RequestMapping(value="/testList.htm")
	public String returnJsp(String name) {
//		JSONObject json = new JSONObject();
//		Map<String, String> map = new HashMap<>();
//		map.put("username", "user1");
//		List list = new ArrayList<>();
//		list.a
		List list = testSrvice.getList();
		//JSONArray jarray = new JSONArray();
		log.debug(list);
		//testSrvice.saveTest();
		return "index";
	}
}
