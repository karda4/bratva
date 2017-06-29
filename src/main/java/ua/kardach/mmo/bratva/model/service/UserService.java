package ua.kardach.mmo.bratva.model.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.kardach.mmo.bratva.dao.UserDao;
import ua.kardach.mmo.bratva.model.Avatar;
import ua.kardach.mmo.bratva.model.Personage;
import ua.kardach.mmo.bratva.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private AvatarService avatarService;
	@Autowired
	private PersonageService personageService;
	
	public User getUserById(Long userId) {
		return userDao.findUserById(userId);
	}
	
	public User getUserByName(String name) {
		User user = userDao.findUserByNick(name);
		Personage personage = personageService.findPersonageByUserId(user.getId());
		user.setPersonage(personage);
		return user;
	}
	
	public User loginUser(String name, String password) {
		User user = this.getUserByName(name);
		if(user != null && user.getPassword().equals(password)){
			return user;
		}
		return null;
	}

	@Transactional
	public User addUser(String nick, String password, String phone, int sex){
		User user = new User();
		user.setNick(nick);
		user.setPassword(password);
		user.setPhone(phone);
		user.setSex(sex);
		user.setRegistrationTime(Calendar.getInstance().getTime());
		
		Avatar avatar = avatarService.getRandomAvatar(sex, false);
		user.setAvatar(avatar);
		
		if(userDao.saveUser(user)){
			user = userDao.findUserByNick(nick);
			
			if(personageService.addPersonage(user.getId())){
				Personage pers = personageService.findPersonageByUserId(user.getId());
				user.setPersonage(pers);
				return user;
			}
			else{
				userDao.deleteUser(user);
			}
		}
		return null;
	}
	
	public boolean isUserNickUnique(String nick){
		User user = this.getUserByName(nick);
		return user == null;
	}
}
