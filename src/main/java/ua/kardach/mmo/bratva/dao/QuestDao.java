package ua.kardach.mmo.bratva.dao;

import java.util.List;

import ua.kardach.mmo.bratva.model.Quest;

public interface QuestDao {
	
	public Quest getQuest(long questId);
	
	public List<Quest> getQuestsByMissionId(long missionId);
}
