package ua.kardach.mmo.bratva.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ua.kardach.mmo.bratva.dao.BoostDao;
import ua.kardach.mmo.bratva.model.Boost;

@Repository
public class BoostSpringJdbcDao extends AbstractSpringJdbcDao implements BoostDao{

	private final static String SQL_SELECT_BOOST_BY_USER_ID = "SELECT * FROM boost_use WHERE id_user=?";
	private final static String SQL_UPDATE_BOOST = "UPDATE boost_use SET amount=? WHERE id_boost_use=? AND id_user=?";
	private final static String SQL_DELETE_BOOST = "DELETE FROM boost_use WHERE id_boost_use=? AND id_user=?";
	
	@Override
	public List<Boost> getBoosts(long userId) {
		return getJdbcTemplate().query(SQL_SELECT_BOOST_BY_USER_ID, new Object[]{userId}, new BoostRowMapper());
	}
	
	@Override
	public boolean updateBoost(Boost boost) {
		int rows = getJdbcTemplate().update(SQL_UPDATE_BOOST, boost.getAmount(), boost.getId(), boost.getUserId());
		return rows == 1;
	}

	@Override
	public boolean deleteBoost(Boost boost) {
		int rows = getJdbcTemplate().update(SQL_DELETE_BOOST, boost.getId(), boost.getUserId());
		return rows == 1;
	}
	
	private static class BoostRowMapper implements RowMapper<Boost>{

		@Override
		public Boost mapRow(ResultSet rs, int rowNum) throws SQLException {
			Boost boostUse = new Boost();
			boostUse.setId(rs.getLong("id_boost_use"));
			boostUse.setUserId(rs.getLong("id_user"));
			boostUse.setInventoryId(rs.getLong("id_inventory"));
			boostUse.setPodgonId(rs.getLong("id_podgon"));
			boostUse.setGiftId(rs.getLong("id_gift"));
			boostUse.setAttack(rs.getInt("attack"));
			boostUse.setProtect(rs.getInt("protect"));
			boostUse.setAuthority(rs.getInt("authority"));
			boostUse.setMoney(rs.getInt("money"));
			boostUse.setAmount(rs.getInt("amount"));
			return boostUse;
		}
		
	}	
}
