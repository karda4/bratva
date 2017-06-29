package ua.kardach.mmo.bratva.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kardach.mmo.bratva.dao.InventoryCategoryDao;
import ua.kardach.mmo.bratva.model.InventoryCategory;

@Service
public class InventoryCategoryService {
	
	@Autowired
	private InventoryCategoryDao inventoryCategoryDao;
	
	public InventoryCategory getInventoryCategory(long categoryId){
		return inventoryCategoryDao.getInventoryCategory(categoryId);
	}
}
