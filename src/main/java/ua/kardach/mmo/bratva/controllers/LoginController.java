package ua.kardach.mmo.bratva.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.kardach.mmo.bratva.model.User;
import ua.kardach.mmo.bratva.model.service.UserService;

/*
 * @author Yura Kardach
 * 
 */

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginForm(Model model){
		return "login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String verifyLogin(@RequestParam String nick, @RequestParam String password, HttpSession session, Model model){
		User user = userService.getUserByName(nick);
		if(user == null){
			model.addAttribute("loginError", "There isn't user with nick '" + nick + "'.");
			return "login";
		}
		else if(!user.getPassword().equals(password)){
			model.addAttribute("loginError", "Incorrect password.");
			return "login";
		}
		session.setAttribute("hero", user);
		return "redirect:/main";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session){
		session.removeAttribute("hero");
		return "login";
	}
}
