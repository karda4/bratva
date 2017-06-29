package ua.kardach.mmo.bratva.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kardach.mmo.bratva.dao.QuestPodgonsDao;
import ua.kardach.mmo.bratva.model.Podgon;

@Service
public class QuestPodgonsService {
	
	@Autowired
	private QuestPodgonsDao questPodgonsDao;
	@Autowired
	private PodgonService podgonService;
	
	Map<Podgon, Integer> getBonusPodgons(long questId){
		Map<Long, Integer> map = questPodgonsDao.getBonusPodgons(questId);
		Map<Podgon, Integer> bonusPodgons = init(map);
		return bonusPodgons;
	}
	
	private Map<Podgon, Integer> init(Map<Long, Integer> map){
		Map<Podgon, Integer> result = new HashMap<>();
		for(Map.Entry<Long, Integer> entry : map.entrySet()){
			Podgon podgon = podgonService.getPodgon(entry.getKey());
			result.put(podgon, entry.getValue());
		}
		return result;
	}
}
