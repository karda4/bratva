package ua.kardach.mmo.bratva.dao;

import ua.kardach.mmo.bratva.model.User;

public interface UserDao {
	//create
	public boolean saveUser(User user);
	
	//read
	public User findUser(String nick, String password);
	
	public User findUserById(Long userId);

	public User findUserByNick(String name);
	
	//update
	public void updateUser(User user);
	
	//delete
	public void deleteUser(User user);
}
