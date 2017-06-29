package ua.kardach.mmo.bratva.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kardach.mmo.bratva.dao.QuestInventoriesDao;
import ua.kardach.mmo.bratva.model.Inventory;

@Service
public class QuestInventoriesService {
	
	@Autowired
	private QuestInventoriesDao questInventoriesDao;
	@Autowired
	private InventoryService inventoryService;
	
	public Map<Inventory, Integer> getRequiredInventories(long questId){
		Map<Long, Integer> map = questInventoriesDao.getRequiredIdInventories(questId);
		Map<Inventory, Integer> requiredInventories = init(map);
		return requiredInventories;
	}
	
	public Map<Inventory, Integer> getBonusInventories(long questId){
		Map<Long, Integer> map = questInventoriesDao.getBonusIdInventories(questId);
		Map<Inventory, Integer> bonusInventories = init(map);
		return bonusInventories;
	}
	
	private Map<Inventory, Integer> init(Map<Long, Integer> map){
		Map<Inventory, Integer> result = new HashMap<>();
		for(Map.Entry<Long, Integer> entry : map.entrySet()){
			Inventory inventory = inventoryService.getInventory(entry.getKey());
			result.put(inventory, entry.getValue());
		}
		return result;
	}
}
