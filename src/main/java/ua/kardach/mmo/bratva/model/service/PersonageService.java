package ua.kardach.mmo.bratva.model.service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kardach.mmo.bratva.dao.DevelopPersonageLevelDao;
import ua.kardach.mmo.bratva.dao.PersonageDao;
import ua.kardach.mmo.bratva.model.Boost;
import ua.kardach.mmo.bratva.model.Inventory;
import ua.kardach.mmo.bratva.model.Personage;
import ua.kardach.mmo.bratva.model.Role;
import ua.kardach.mmo.bratva.model.UserPodgon;
import ua.kardach.mmo.bratva.util.Koof;

@Service
public class PersonageService {
	
	@Autowired
	private PersonageDao personageDao;
	@Autowired
	private DevelopPersonageLevelDao developPersonageLevelDao;
	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private PodgonService podgonService;
	@Autowired
	private BoostService boostService;
	@Autowired
	private RoleService roleService;
	
	public boolean addPersonage(Long userId){
		Personage pers = new Personage.PersonageBuilder().setUserId(userId)
				.setMaxHealth(Koof.startHealth)
				.setMaxEnergy(Koof.startEnergy)
				.setMaxPower(Koof.startPower)
				.setAttack(Koof.startAttack)
				.setProtect(Koof.startProtect)
				.setTime(Calendar.getInstance().getTime())
				.build();
		return personageDao.addPersonage(pers);
	}
	
	public Personage findPersonageByUserId(Long userId){
		return personageDao.findPersonageByUserId(userId);
	}
	
	public boolean updatePersonage(Personage pers){
		return personageDao.updatePersonage(pers);
	}
	
	private int getAuthorityForNextLevel(int numLevel){
		return developPersonageLevelDao.getAuthorityForNextLevel(numLevel);
	}
	
	public void refreshPersonage(Personage pers){
		updateState(pers);
		updateInventories(pers);
		updateUserPodgons(pers);
		updateBoosts(pers);
		updateRoles(pers);
	}
	
	private void updateState(Personage pers) {
		pers.regenerateHealth();
		pers.regeneratePower();
		pers.regenerateEnergy();
		pers.checkReachNewLevel(getAuthorityForNextLevel(pers.getLevel()));
		
		updatePersonage(pers);
	}
	
	private void updateInventories(Personage pers){
		Map<Inventory, Integer> inventories = inventoryService.getUserInventories(pers.getUserId());
		pers.setInventories(inventories);
	}
	
	private void updateUserPodgons(Personage pers){
		List<UserPodgon> userPodgons = podgonService.getUserPodgons(pers.getUserId());
		pers.setUserPodgons(userPodgons);
	}
	
	private void updateBoosts(Personage pers){
		List<Boost> boosts = boostService.getBoosts(pers.getUserId());
		pers.setBoosts(boosts);
	}
	
	private void updateRoles(Personage pers) {
		List<Role> roles = roleService.getUserRole(pers.getUserId());
		pers.setRoles(roles);
	}
}
