package ua.kardach.mmo.bratva.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kardach.mmo.bratva.dao.BoostDao;
import ua.kardach.mmo.bratva.model.Boost;
import ua.kardach.mmo.bratva.model.Personage;

@Service
public class BoostService {

	@Autowired
	private BoostDao boostDao;
	
	public List<Boost> getBoosts(long userId){
		return boostDao.getBoosts(userId);
	}
	
	public boolean updateBoost(Boost boost){
		return boostDao.updateBoost(boost);
	}
	
	public boolean deleteBoost(Boost boost){
		return boostDao.deleteBoost(boost);
	}

	public void removeBoosts(Personage pers, boolean bFight) {
		List<Boost> list = selectBoostsForDelete(pers.getBoosts(), bFight);
		for(Boost boost : list){
			boost.setAmount(boost.getAmount() - 1);
			if(boost.getAmount() > 0){
				updateBoost(boost);
			}
			else{
				deleteBoost(boost);
			}
		}
	}
	
	private List<Boost> selectBoostsForDelete(List<Boost> raw, boolean bFight){
		List<Boost> list = new ArrayList<Boost>();
		for(Boost boost : raw){
			if(bFight){
				if(boost.getAuthority() == 0 && boost.getMoney() == 0){
					list.add(boost);
				}
			}
			else{
				if(boost.getAuthority() != 0 || boost.getMoney() != 0){
					list.add(boost);
				}
			}
		}
		return list;
	}
}
