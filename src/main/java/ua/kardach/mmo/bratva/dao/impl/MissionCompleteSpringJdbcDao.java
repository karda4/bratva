package ua.kardach.mmo.bratva.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ua.kardach.mmo.bratva.dao.MissionCompleteDao;

@Repository
public class MissionCompleteSpringJdbcDao extends AbstractSpringJdbcDao implements MissionCompleteDao{
	
	private final static String SQL_SELECT_MISSION_COMPLETE = "SELECT a_mission_complete FROM mission_complete WHERE id_user=? AND id_mission=?";

	@Override
	public int getAmountCompleted(long userId, long missionId) {
		List<Integer> list = (List<Integer>)getJdbcTemplate().query(SQL_SELECT_MISSION_COMPLETE, new Object[]{userId, missionId}, new MissionCompleteRowMapper());
		if(list.isEmpty()){
			return 0;
		}
		else if(list.size() == 1){
			return list.get(0);
		}
		throw new RuntimeException("Finded more then one row with id_nick='" + userId + " and id_mission='" + missionId + " in database!");
	}
	
	private static class MissionCompleteRowMapper implements RowMapper<Integer>{

		@Override
		public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getInt("a_mission_complete");
		}
		
	}
}
