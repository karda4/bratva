package ua.kardach.mmo.bratva.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kardach.mmo.bratva.dao.MissionCompleteDao;

@Service
public class MissionCompleteService {

	@Autowired
	private MissionCompleteDao missionCompleteDao;
	
	public int getAmountMissionCompleted(long userId, long missionId){
		return missionCompleteDao.getAmountCompleted(userId, missionId);
	}
}
