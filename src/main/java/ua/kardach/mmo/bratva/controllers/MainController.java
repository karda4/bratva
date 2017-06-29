package ua.kardach.mmo.bratva.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * @author Yura Kardach
 * 
 */

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String redirectToMainPage() {
		return "redirect:/main";
	}

	@RequestMapping("/main")
	public String showMainPage(HttpSession session, Model model) {
		model.addAttribute("title", "main");
		model.addAttribute("menuBottomBack", false);
		model.addAttribute("menuBottomMenu", true);
		model.addAttribute("menuBottomHelp", true);
		return "main";
	}
}
