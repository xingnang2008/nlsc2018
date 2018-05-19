package com.nlsc.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {
	
	 @RequestMapping(value = "/main", method = RequestMethod.POST)
	    public String login(String username, String password, Model model) {
	        Subject subject = SecurityUtils.getSubject();
	        
	        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
	        System.out.println(password);
	        try {
	            subject.login(token);
	        } catch (UnknownAccountException e) {
	           // e.printStackTrace();
	            model.addAttribute("username", "用户名错误！");
	            return "login";
	        } catch (IncorrectCredentialsException e) {
	           // e.printStackTrace();
	            model.addAttribute("password", "密码错误");
	            return "login";
	        }
	        return "index";
	    }

}
