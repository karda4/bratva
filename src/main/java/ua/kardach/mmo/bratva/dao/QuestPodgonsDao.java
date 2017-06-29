package ua.kardach.mmo.bratva.dao;

import java.util.Map;

public interface QuestPodgonsDao {
	
	Map<Long, Integer> getBonusPodgons(long questId);
}
