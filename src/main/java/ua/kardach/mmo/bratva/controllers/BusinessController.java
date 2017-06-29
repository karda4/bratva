package ua.kardach.mmo.bratva.controllers;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BusinessController {
	
	String[] businessMenuArray = { "mission", "business"};
	List<String> businessMenuList = Arrays.asList(businessMenuArray);
	
	@RequestMapping("/business")
	public String showBusinessPage(HttpSession session, Model model) {
		model.addAttribute("title", "business");
		model.addAttribute("businessMenuList", businessMenuList);
		model.addAttribute("menuBottomBack", false);
		model.addAttribute("menuBottomMenu", true);
		model.addAttribute("menuBottomHelp", true);
		return "business";
	}
}
