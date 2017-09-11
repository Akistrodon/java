package com.ngm01.hellohuman.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index(@RequestParam(value="first_name", required=false) String first_name, @RequestParam(value="last_name", required=false) String last_name, Model model) {
		String name = first_name + " " + last_name;
		if(first_name == null && last_name == null) {
			name = "Human";
		}
		model.addAttribute("name", name);
		return "index.jsp";
	}
}
