package ua.kardach.mmo.bratva.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kardach.mmo.bratva.dao.MissionDao;
import ua.kardach.mmo.bratva.model.Mission;

@Service
public class MissionService {

	@Autowired
	private MissionDao missionDao;
	@Autowired
	private MissionCompleteService missionCompleteService;
	@Autowired
	private QuestService questService;
	
	public Mission getMission(long userId, long missionId){
		Mission mission = missionDao.getMission(missionId);
		initMission(mission, userId);
		return mission;
	}
	
	public int amountAllMission() {
		return missionDao.amountAllMission();
	}
	
	private void initMission(Mission mission, long userId){
		long missionId = mission.getId();
		mission.setAmountComplete(missionCompleteService.getAmountMissionCompleted(userId, missionId));
		mission.setQuests(questService.getQuestsByMissionId(userId, missionId));
	}
	
}
