package ua.kardach.mmo.bratva.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ua.kardach.mmo.bratva.dao.InventoryCategoryDao;
import ua.kardach.mmo.bratva.model.InventoryCategory;

@Repository
public class InventoryCategorySpringJdbcDao extends AbstractSpringJdbcDao implements InventoryCategoryDao{
	
	private final static String SQL_SELECT_INVENTORY_CATEGORY_BY_ID = "SELECT * FROM cat_inventory WHERE id_cat_inventory=?";

	@Override
	public InventoryCategory getInventoryCategory(long categoryId) {
		return getJdbcTemplate().queryForObject(SQL_SELECT_INVENTORY_CATEGORY_BY_ID, new Object[]{categoryId}, new InventoryCategoryRowMapper());
	}
	
	private static class InventoryCategoryRowMapper implements RowMapper<InventoryCategory>{

		@Override
		public InventoryCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
			InventoryCategory inventoryCategory = new InventoryCategory();
			inventoryCategory.setId(rs.getLong("id_cat_inventory"));
			inventoryCategory.setName(rs.getString("name_cat_inventory"));
			inventoryCategory.setImageName(rs.getString("image_name"));
			return inventoryCategory;
		}
		
	}
}
