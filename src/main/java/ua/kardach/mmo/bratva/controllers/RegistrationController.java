package ua.kardach.mmo.bratva.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.kardach.mmo.bratva.model.User;
import ua.kardach.mmo.bratva.model.service.UserService;

@Controller
public class RegistrationController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		User user = new User();
		model.addAttribute("userForm", user);
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String doRegistration(@Valid @ModelAttribute("userForm") User userForm, BindingResult result, @RequestParam("friend") String friendNick, HttpSession session, Model model) {
		if (result.hasErrors()) {
			return "registration";
		}
		
		//check unique nick
		if(!userService.isUserNickUnique(userForm.getNick())){
			model.addAttribute("registrationError", "Already exist user with nick " + userForm.getNick());
			return "registration";
		}
		//check correct friend
		User friendUser = getFriendUser(friendNick);
		if (isFriendFulfilled(friendNick)) {
			if (friendUser == null) {
				model.addAttribute("registrationError", "Incorrect typed friend's nick: " + friendNick);
				return "registration";
			}
		}
				
		User user = userService.addUser(userForm.getNick(), userForm.getPassword(), userForm.getPhone(), userForm.getSex());
		session.setAttribute("loggedInUser", user);
		return "redirect:/main";
	}
	
	public boolean isFriendFulfilled(String friendNick){
		return friendNick != null && friendNick.length() > 0;
	}
	
	public User getFriendUser(String friendNick){
		if(!isFriendFulfilled(friendNick)) return null;
		return userService.getUserByName(friendNick);
	}
}
