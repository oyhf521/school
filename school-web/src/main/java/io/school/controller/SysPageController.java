package io.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统页面视图
 * 
 * @author school
 * @email 
 * @date 2016年11月24日 下午11:05:27
 */
@Controller
public class SysPageController {
	
	@RequestMapping("sys/{url}.html")
	public String page(@PathVariable("url") String url){
		return "sys/" + url + ".html";
	}

	@RequestMapping("generator/{url}.html")
	public String generator(@PathVariable("url") String url){
		return "generator/" + url + ".html";
	}
	@RequestMapping("error/{url}.html")
	public String error(@PathVariable("url") String url){
		return "error/" + url + ".html";
	}
	@RequestMapping("base/{url}.html")
	public String base(@PathVariable("url") String url){
		return "base/" + url + ".html";
	}
}
