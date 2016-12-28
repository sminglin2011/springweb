package com.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

	Logger log = Logger.getLogger(UserService.class);
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	public List fetchUserList() {
		List list = null;
		String sql = "select staffid, name from m03staff";
		list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
}
