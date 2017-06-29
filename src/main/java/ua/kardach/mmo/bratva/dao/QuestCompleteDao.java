package ua.kardach.mmo.bratva.dao;

public interface QuestCompleteDao {
	
	public int getAmountCompleted(long userId, long questId);
}
