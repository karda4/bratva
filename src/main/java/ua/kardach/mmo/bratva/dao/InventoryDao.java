package ua.kardach.mmo.bratva.dao;

import java.util.Map;

import ua.kardach.mmo.bratva.model.Inventory;

public interface InventoryDao {
	
	public Inventory getInventory(long inventoryId);
	
	public Map<Long, Integer> getUserInventories(long userId);
}
