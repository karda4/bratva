package ua.kardach.mmo.bratva.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ua.kardach.mmo.bratva.dao.UserDao;
import ua.kardach.mmo.bratva.model.User;

@Repository
public class UserSpringJdbcDao extends AbstractSpringJdbcDao implements UserDao{
	
	private final static String SQL_SELECT_USER = "SELECT * FROM user WHERE nick=? AND password=?";
	private final static String SQL_SELECT_USER_BY_ID = "SELECT * FROM user WHERE id=?";
	private final static String SQL_SELECT_USER_BY_NICK = "SELECT * FROM user WHERE nick=?";
	private final static String SQL_INSERT_USER = "INSERT INTO user (nick, password, phone, sex, avatar_id, time_reg) VALUES (?, ?, ?, ?, ?, ?)";
	
	@Override
	public User findUser(String nick, String password) {
		return getJdbcTemplate().queryForObject(SQL_SELECT_USER, new Object[]{nick, password}, new UserRowMapper());
	}
	
	@Override
	public User findUserById(Long userId) {
		return getJdbcTemplate().queryForObject(SQL_SELECT_USER_BY_ID, new Object[]{userId}, new UserRowMapper());
	}

	@Override
	public User findUserByNick(String nick) {
		List<User> userList = (List<User>)getJdbcTemplate().query(SQL_SELECT_USER_BY_NICK, new Object[]{nick}, new UserRowMapper());
		if(userList.isEmpty()){
			return null;
		}
		else if(userList.size() == 1){
			return userList.get(0);
		}
		throw new RuntimeException("Finded more then one user with nick '" + nick + "' in database!");
	}
	
	@Override
	public boolean saveUser(User user) {
		long registrationTime = user.getRegistrationTime().getTime();
		long avatarId = user.getAvatar().getId();
		int rows = getJdbcTemplate().update(SQL_INSERT_USER, new Object[]{user.getNick(), user.getPassword(), user.getPhone(), user.getSex(), avatarId, registrationTime});
		return rows == 1;
	}
	
	@Override
	public void updateUser(User user) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteUser(User user) {
		throw new UnsupportedOperationException();
	}
	
	private static class UserRowMapper implements RowMapper<User>{

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			Date registrationTime = new Date(rs.getLong("time_reg"));
			User user = new User();
			user.setId(rs.getLong("id"));
			user.setNick(rs.getString("nick"));
			user.setPassword(rs.getString("password"));
			user.setSex(rs.getInt("sex"));
			user.setRegistrationTime(registrationTime);
			return user;
		}
		
	}

}
