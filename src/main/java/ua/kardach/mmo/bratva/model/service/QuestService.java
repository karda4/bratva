package ua.kardach.mmo.bratva.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kardach.mmo.bratva.dao.QuestDao;
import ua.kardach.mmo.bratva.model.Quest;

@Service
public class QuestService {
	
	@Autowired
	private QuestDao questDao;
	
	@Autowired
	private QuestCompleteService questCompleteService;
	@Autowired
	private QuestInventoriesService questInventoriesService;
	@Autowired
	private QuestPodgonsService questPodgonsService;
	
	public Quest getQuest(long userId, long questId){
		Quest quest = questDao.getQuest(questId);
		initQuest(quest, userId);
		return quest;
	}
	
	public List<Quest> getQuestsByMissionId(long userId, long missionId){
		List<Quest> questList = questDao.getQuestsByMissionId(missionId);
		for(Quest quest : questList){
			initQuest(quest, userId);
		}
		return questList;
	}
	
	private void initQuest(Quest quest, long userId){
		long questId = quest.getId();
		quest.setAmountComplete(questCompleteService.getAmountCompleted(userId, questId));
		quest.setRequiredInventories(questInventoriesService.getRequiredInventories(questId));
		quest.setBonusInventories(questInventoriesService.getBonusInventories(questId));
		quest.setBonusPodgons(questPodgonsService.getBonusPodgons(questId));
	}
}
