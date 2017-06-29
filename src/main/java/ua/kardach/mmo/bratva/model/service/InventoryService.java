package ua.kardach.mmo.bratva.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kardach.mmo.bratva.dao.InventoryDao;
import ua.kardach.mmo.bratva.model.Inventory;
import ua.kardach.mmo.bratva.util.Util;

@Service
public class InventoryService {
	
	@Autowired
	private InventoryDao inventoryDao;
	@Autowired
	private InventoryCategoryService categoryService;

	public Inventory getInventory(long inventoryId){
		Inventory inventory = inventoryDao.getInventory(inventoryId);
		init(inventory);
		return inventory;
	}
	
	public Map<Inventory, Integer> getUserInventories(long userId){
		Map<Long, Integer> map = inventoryDao.getUserInventories(userId);
		Map<Inventory, Integer> userInventories = new HashMap<Inventory, Integer>();
		for(Map.Entry<Long, Integer> entry : map.entrySet()){
			Inventory inventory = getInventory(entry.getKey());
			userInventories.put(inventory, entry.getValue());
		}
		return userInventories;
	}
	
	private void init(Inventory inventory){
		long inventoryCategoryId = inventory.getCategoryId();
		inventory.setCategory(categoryService.getInventoryCategory(inventoryCategoryId));
		inventory.setImagePath(Util.imageInventory(inventory) + Util.imageIndex(inventory.getId()) + ".png");		
	}
}
