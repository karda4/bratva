package ua.kardach.mmo.bratva.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ua.kardach.mmo.bratva.dao.QuestDao;
import ua.kardach.mmo.bratva.model.Quest;

@Repository
public class QuestSpringJdbcDao extends AbstractSpringJdbcDao implements QuestDao{
	
	private final static String SQL_SELECT_QUEST_BY_QUEST_ID = "SELECT * FROM quest WHERE id_quest=?";
	private final static String SQL_SELECT_QUESTS_BY_MISSION_ID = "SELECT * FROM quest WHERE id_mission=?";

	@Override
	public Quest getQuest(long questId) {
		return getJdbcTemplate().queryForObject(SQL_SELECT_QUEST_BY_QUEST_ID, new Object[]{questId}, new QuestRowMapper());
	}

	@Override
	public List<Quest> getQuestsByMissionId(long missionId) {
		List<Quest> questList = (List<Quest>)getJdbcTemplate().query(SQL_SELECT_QUESTS_BY_MISSION_ID, new Object[]{missionId}, new QuestRowMapper());
		return questList;
	}
	
	private static class QuestRowMapper implements RowMapper<Quest>{

		@Override
		public Quest mapRow(ResultSet rs, int rowNum) throws SQLException {
			Quest quest = new Quest();
			quest.setId(rs.getLong("id_quest"));
			quest.setMissionId(rs.getLong("id_mission"));
			quest.setName(rs.getString("text_quest"));
			quest.setMoney(rs.getInt("money_quest"));
			quest.setAuthority(rs.getInt("authority_quest"));
			quest.setEnergy(rs.getInt("energy_quest"));
			quest.setClickToComplete(rs.getInt("click_quest"));
			return quest;
		}
		
	}
	
}
