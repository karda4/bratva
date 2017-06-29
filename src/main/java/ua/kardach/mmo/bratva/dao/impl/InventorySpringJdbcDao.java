package ua.kardach.mmo.bratva.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ua.kardach.mmo.bratva.dao.InventoryDao;
import ua.kardach.mmo.bratva.model.Inventory;

@Repository
public class InventorySpringJdbcDao extends AbstractSpringJdbcDao implements InventoryDao{
	
	private final static String SQL_SELECT_INVENTORY_BY_ID = "SELECT * FROM inventory WHERE id_inventory=?";
	private final static String SQL_SELECT_USER_INVENTORIES_BY_USER_ID = "SELECT * FROM inventory_user iu LEFT JOIN inventory i ON iu.id_inventory=i.id_inventory WHERE iu.id_user=? AND iu.id_inventory>0 AND iu.amount>0";
	
	@Override
	public Inventory getInventory(long inventoryId) {
		return getJdbcTemplate().queryForObject(SQL_SELECT_INVENTORY_BY_ID, new Object[]{inventoryId}, new InventoryRowMapper());
	}
	
	@Override
	public Map<Long, Integer> getUserInventories(long userId) {
		return getJdbcTemplate().query(SQL_SELECT_USER_INVENTORIES_BY_USER_ID, new Object[]{userId}, new UserInventoriesResultSetExtractor());
	}

	private static class InventoryRowMapper implements RowMapper<Inventory>{

		@Override
		public Inventory mapRow(ResultSet rs, int rowNum) throws SQLException {
			Inventory inventory = new Inventory();
			inventory.setId(rs.getLong("id_inventory"));
			inventory.setCategoryId(rs.getLong("id_cat_inventory"));
			inventory.setName(rs.getString("name_inventory"));
			inventory.setCash(rs.getInt("cash_inventory"));
			inventory.setMoney(rs.getInt("money_inventory"));
			inventory.setAttack(rs.getInt("attack_inventory"));
			inventory.setProtect(rs.getInt("protect_inventory"));
			inventory.setLevel(rs.getInt("level_inventory"));
			inventory.setMembers(rs.getInt("members"));
			inventory.setNoBuy(rs.getInt("no_buy"));
			return inventory;
		}
		
	}
	
	private static class UserInventoriesResultSetExtractor implements ResultSetExtractor<Map<Long, Integer>>{

		@Override
		public Map<Long, Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
			Map<Long, Integer> result = new HashMap<>();
			while(rs.next()){
				long key = rs.getLong("id_inventory");
				int value = rs.getInt("amount");
				result.put(key, value);
			}
			return result;
		}
		
	}
}
