package ua.kardach.mmo.bratva.dao;

import java.util.Map;

public interface QuestInventoriesDao {
	
	public Map<Long, Integer> getRequiredIdInventories(long questId);
	public Map<Long, Integer> getBonusIdInventories(long questId);
}
