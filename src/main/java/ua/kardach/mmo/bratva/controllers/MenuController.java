package ua.kardach.mmo.bratva.controllers;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MenuController {
	
	String[] menuArray = { "main", "mission", "fight", "inventory", "bratva", "life", "finance", "rate", "terki", "forum", "help" };
	List<String> menuList = Arrays.asList(menuArray);
	
	@RequestMapping("/menu")
	public String showMenuPage(HttpSession session, Model model) {
		model.addAttribute("title", "menu");
		model.addAttribute("menuList", menuList);
		return "menu";
	}
}
