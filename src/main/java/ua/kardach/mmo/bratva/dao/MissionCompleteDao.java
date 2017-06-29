package ua.kardach.mmo.bratva.dao;

public interface MissionCompleteDao {
	
	public int getAmountCompleted(long userId, long missionId);
}
