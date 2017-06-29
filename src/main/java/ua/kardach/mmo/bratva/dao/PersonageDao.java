package ua.kardach.mmo.bratva.dao;

import ua.kardach.mmo.bratva.model.Personage;

public interface PersonageDao {
	
	//create
	public boolean addPersonage(Personage pers);
	
	//read
	public Personage findPersonageByUserId(Long userId);
	
	//update
	public boolean updatePersonage(Personage pers);
	
	//delete
	public boolean deletePersonage(Personage pers);
	
}
