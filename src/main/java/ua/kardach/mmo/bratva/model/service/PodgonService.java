package ua.kardach.mmo.bratva.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kardach.mmo.bratva.dao.PodgonDao;
import ua.kardach.mmo.bratva.model.Podgon;
import ua.kardach.mmo.bratva.model.UserPodgon;
import ua.kardach.mmo.bratva.util.Util;

@Service
public class PodgonService {

	@Autowired
	private PodgonDao podgonDao;
	
	public Podgon getPodgon(long podgonId){
		Podgon podgon = podgonDao.getPodgon(podgonId);
		init(podgon);
		return podgon;
	}
	
	public List<UserPodgon> getUserPodgons(long userId){
		List<UserPodgon> list = podgonDao.getUserPodgons(userId);
		for(UserPodgon userPodgon : list){
			long podgonId = userPodgon.getPodgonId();
			userPodgon.setPodgon(getPodgon(podgonId));
		}
		return podgonDao.getUserPodgons(userId);
	}
	
	private void init(Podgon podgon){
		podgon.setImagePath(Util.imagePrefix("podgon") + Util.imageIndex(podgon.getId()) + ".png");		
	}
	
	public Podgon getFirstEnergyPodgon(List<UserPodgon> userPodgons){
		for(UserPodgon userPodgon : userPodgons){
			if(userPodgon.getAmount() > 0 && userPodgon.getPodgon().getEnergy() > 0){
				return userPodgon.getPodgon();
			}
		}
		return null;
	}
}
