package ua.kardach.mmo.bratva.dao;

import java.util.List;

import ua.kardach.mmo.bratva.model.Podgon;
import ua.kardach.mmo.bratva.model.UserPodgon;

public interface PodgonDao {
	
	public Podgon getPodgon(long podgonId);
	
	public List<UserPodgon> getUserPodgons(long userId);
}
