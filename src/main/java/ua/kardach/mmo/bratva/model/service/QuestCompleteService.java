package ua.kardach.mmo.bratva.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kardach.mmo.bratva.dao.QuestCompleteDao;

@Service
public class QuestCompleteService {
	
	@Autowired
	private QuestCompleteDao questCompleteDao;
	
	public int getAmountCompleted(long userId, long questId){
		return questCompleteDao.getAmountCompleted(userId, questId);
	}
}
