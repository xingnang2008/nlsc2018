package com.nlsc.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

	
	/**
	 * 打开首页
	 */
	@RequestMapping("/")
	public String showIndex() {
		return "login";
	}
	@RequestMapping("/admin")
	public String showAdminIndex() {
		return "index";
	}
	

	/**
	 * 展示其他页面
	 * <p>Title: showpage</p>
	 * <p>Description: </p>
	 * @param page
	 * @return
	 */
	@RequestMapping("/admin/{page}")
	public String showpage(@PathVariable String page) {
		return page;
	}

    
}
