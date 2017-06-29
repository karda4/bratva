package ua.kardach.mmo.bratva.dao;

import ua.kardach.mmo.bratva.model.Mission;

public interface MissionDao {
	
	public Mission getMission(long missionId);
	
	public int amountAllMission();
}
