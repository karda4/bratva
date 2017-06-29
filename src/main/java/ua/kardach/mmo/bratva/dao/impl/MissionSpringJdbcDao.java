package ua.kardach.mmo.bratva.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ua.kardach.mmo.bratva.dao.MissionDao;
import ua.kardach.mmo.bratva.model.Mission;

@Repository
public class MissionSpringJdbcDao extends AbstractSpringJdbcDao implements MissionDao{

	private final static String SQL_SELECT_MISSION_BY_ID = "SELECT * FROM mission WHERE id_mission=?";
	private final static String SQL_COUNT_MISSIONS = "SELECT COUNT(*) FROM mission";
	
	@Override
	public Mission getMission(long missionId) {
		return getJdbcTemplate().queryForObject(SQL_SELECT_MISSION_BY_ID, new Object[]{missionId}, new MissionRowMapper());
	}

	@Override
	public int amountAllMission() {
		return getJdbcTemplate().queryForObject(SQL_COUNT_MISSIONS, Integer.class);
	}
	
	private static class MissionRowMapper implements RowMapper<Mission>{

		@Override
		public Mission mapRow(ResultSet rs, int rowNum) throws SQLException {
			Mission mission = new Mission();
			mission.setId(rs.getLong("id_mission"));
			mission.setName(rs.getString("name_mission"));
			mission.setLevelAvailable(rs.getInt("level_available"));
			mission.setMaxComplete(rs.getInt("max_complete"));
			return mission;
		}		
	}	
}
