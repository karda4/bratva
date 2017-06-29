package ua.kardach.mmo.bratva.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import ua.kardach.mmo.bratva.dao.QuestInventoriesDao;

@Repository
public class QuestInventoriesSpringJdbcDao extends AbstractSpringJdbcDao implements QuestInventoriesDao{
	
	private final static String SQL_SELECT_QUEST_REQUIRED_INVENTORIES_BY_ID = "SELECT * FROM quest_required_inventory WHERE id_quest=? AND id_inventory>0";
	private final static String SQL_SELECT_QUEST_BONUS_INVENTORIES_BY_ID = "SELECT * FROM quest_bonus_inventory WHERE id_quest=? AND id_inventory>0";	
	
	@Override
	public Map<Long, Integer> getRequiredIdInventories(long questId) {
		return getJdbcTemplate().query(SQL_SELECT_QUEST_REQUIRED_INVENTORIES_BY_ID, new Object[]{questId}, new QuestRequiredInventoriesResultSetExtractor());
	}
	
	@Override
	public Map<Long, Integer> getBonusIdInventories(long questId) {
		return getJdbcTemplate().query(SQL_SELECT_QUEST_BONUS_INVENTORIES_BY_ID, new Object[]{questId}, new QuestBonusInventoriesResultSetExtractor());
	}
	
	private static class QuestRequiredInventoriesResultSetExtractor implements ResultSetExtractor<Map<Long, Integer>>{

		@Override
		public Map<Long, Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
			Map<Long, Integer> result = new HashMap<>();
			while(rs.next()){
				long key = rs.getLong("id_inventory");
				int value = rs.getInt("amount_required");
				result.put(key, value);
			}
			return result;
		}
		
	}	
	
	private static class QuestBonusInventoriesResultSetExtractor implements ResultSetExtractor<Map<Long, Integer>>{

		@Override
		public Map<Long, Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
			Map<Long, Integer> result = new HashMap<>();
			while(rs.next()){
				long key = rs.getLong("id_inventory");
				int value = rs.getInt("amount_bonus");
				result.put(key, value);
			}
			return result;
		}
		
	}
}
