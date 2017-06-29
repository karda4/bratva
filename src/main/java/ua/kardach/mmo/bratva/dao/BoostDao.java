package ua.kardach.mmo.bratva.dao;

import java.util.List;

import ua.kardach.mmo.bratva.model.Boost;

public interface BoostDao {
	
	//read
	public List<Boost> getBoosts(long userId);
	
	//update
	public boolean updateBoost(Boost boost);
	
	//delete
	public boolean deleteBoost(Boost boost);
}
