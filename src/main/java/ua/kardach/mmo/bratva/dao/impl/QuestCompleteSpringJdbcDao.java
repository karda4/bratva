package ua.kardach.mmo.bratva.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ua.kardach.mmo.bratva.dao.QuestCompleteDao;

@Repository
public class QuestCompleteSpringJdbcDao extends AbstractSpringJdbcDao implements QuestCompleteDao{

	private final static String SQL_SELECT_QUEST_COMPLETE = "SELECT a_quest_complete FROM quest_complete WHERE id_user=? AND id_quest=?";

	@Override
	public int getAmountCompleted(long userId, long questId) {
		List<Integer> list = (List<Integer>)getJdbcTemplate().query(SQL_SELECT_QUEST_COMPLETE, new Object[]{userId, questId}, new QuestCompleteRowMapper());
		if(list.isEmpty()){
			return 0;
		}
		else if(list.size() == 1){
			return list.get(0);
		}
		throw new RuntimeException("Finded more then one row with id_nick='" + userId + " and id_quest='" + questId + " in database!");
	}
	
	private static class QuestCompleteRowMapper implements RowMapper<Integer>{

		@Override
		public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getInt("a_quest_complete");
		}
		
	}
}
