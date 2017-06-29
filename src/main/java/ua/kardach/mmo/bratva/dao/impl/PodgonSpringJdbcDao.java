package ua.kardach.mmo.bratva.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ua.kardach.mmo.bratva.dao.PodgonDao;
import ua.kardach.mmo.bratva.model.Podgon;
import ua.kardach.mmo.bratva.model.UserPodgon;

@Repository
public class PodgonSpringJdbcDao extends AbstractSpringJdbcDao implements PodgonDao{
	
	private final static String SQL_SELECT_PODGON_BY_ID = "SELECT * FROM podgon WHERE id_podgon=?";
	private final static String SQL_SELECT_USER_PODGON_BY_USER_ID = "SELECT * FROM podgon_user WHERE id_user=?";

	@Override
	public Podgon getPodgon(long podgonId) {
		return getJdbcTemplate().queryForObject(SQL_SELECT_PODGON_BY_ID, new Object[]{podgonId}, new PodgonRowMapper());
	}
	
	@Override
	public List<UserPodgon> getUserPodgons(long userId) {
		return getJdbcTemplate().query(SQL_SELECT_USER_PODGON_BY_USER_ID, new Object[]{userId}, new UserPodgonRowMapper());
	}

	private static class PodgonRowMapper implements RowMapper<Podgon>{

		@Override
		public Podgon mapRow(ResultSet rs, int rowNum) throws SQLException {
			Podgon podgon = new Podgon();
			podgon.setId(rs.getLong("id_podgon"));
			podgon.setName(rs.getString("name"));
			podgon.setEnergy(rs.getInt("energy"));
			podgon.setHealth(rs.getInt("health"));
			podgon.setClick(rs.getInt("click"));
			podgon.setAuthority(rs.getInt("authority"));
			podgon.setMoney(rs.getInt("money"));
			podgon.setAmountFight(rs.getInt("a_fight"));
			podgon.setAttack(rs.getInt("attack"));
			podgon.setProtect(rs.getInt("protect"));
			podgon.setShowing(rs.getInt("showing") == 1);
			return podgon;
		}
		
	}
	
	private static class UserPodgonRowMapper implements RowMapper<UserPodgon>{

		@Override
		public UserPodgon mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserPodgon userPodgon = new UserPodgon();
			userPodgon.setUserId(rs.getLong("id_user"));
			userPodgon.setFriendId(rs.getLong("id_from"));
			userPodgon.setPodgonId(rs.getLong("id_podgon"));
			userPodgon.setAmount(rs.getInt("amount"));
			userPodgon.setTime(rs.getInt("time"));
			return userPodgon;
		}
		
	}
	
}
