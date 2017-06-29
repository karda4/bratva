package ua.kardach.mmo.bratva.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import ua.kardach.mmo.bratva.dao.QuestPodgonsDao;

@Repository
public class QuestPodgonsSpringJdbcDao extends AbstractSpringJdbcDao implements QuestPodgonsDao {
	
	private final static String SQL_SELECT_QUEST_BONUS_PODGONS_BY_ID = "SELECT * FROM quest_bonus_podgon WHERE id_quest=? AND id_podgon>0";
	
	@Override
	public Map<Long, Integer> getBonusPodgons(long questId) {
		return getJdbcTemplate().query(SQL_SELECT_QUEST_BONUS_PODGONS_BY_ID, new Object[]{questId}, new QuestBonusPodgonsResultSetExtractor());
	}
	
	private static class QuestBonusPodgonsResultSetExtractor implements ResultSetExtractor<Map<Long, Integer>>{

		@Override
		public Map<Long, Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
			Map<Long, Integer> result = new HashMap<>();
			while(rs.next()){
				long key = rs.getLong("id_podgon");
				int value = rs.getInt("amount");
				result.put(key, value);
			}
			return result;
		}
		
	}
}
