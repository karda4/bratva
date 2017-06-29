package ua.kardach.mmo.bratva.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ua.kardach.mmo.bratva.dao.AvatarDao;
import ua.kardach.mmo.bratva.model.Avatar;

@Repository
public class AvatarSpringJdbcDao implements AvatarDao {
	
	private final static String SQL_SELECT_AVATAR_BY_ID = "SELECT * FROM avatar WHERE id=?";
	private final static String SQL_SELECT_RANDOM_ALL_AVATAR = "SELECT * FROM avatar ORDER BY rand() LIMIT 1";
	private final static String SQL_SELECT_RANDOM_FREE_AVATAR = "SELECT * FROM avatar WHERE cash=0 ORDER BY rand() LIMIT 1";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Avatar getAvatar(long id) {
		return jdbcTemplate.queryForObject(SQL_SELECT_AVATAR_BY_ID, new Object[]{id}, new AvatarRowMapper());
	}

	@Override
	public Avatar getRandomAvatar(int sex, boolean all) {
		String sql = all ? SQL_SELECT_RANDOM_ALL_AVATAR : SQL_SELECT_RANDOM_FREE_AVATAR;
		return jdbcTemplate.queryForObject(sql, new AvatarRowMapper());
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private static class AvatarRowMapper implements RowMapper<Avatar>{

		@Override
		public Avatar mapRow(ResultSet rs, int rowNum) throws SQLException {
			Avatar avatar = new Avatar();
			avatar.setId(rs.getLong("id"));
			avatar.setFile(rs.getString("file"));
			avatar.setSex(rs.getInt("sex"));
			avatar.setCash(rs.getInt("cash"));
			avatar.setPopularity(rs.getInt("pop"));
			return avatar;
		}
		
	}
}
