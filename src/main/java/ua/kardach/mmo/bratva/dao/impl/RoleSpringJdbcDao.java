package ua.kardach.mmo.bratva.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ua.kardach.mmo.bratva.dao.RoleDao;
import ua.kardach.mmo.bratva.model.Role;

@Repository
public class RoleSpringJdbcDao extends AbstractSpringJdbcDao implements RoleDao{

	private final static String SQL_SELECT_ROLE_BY_ID = "SELECT * FROM role WHERE id_role=?";
	private final static String SQL_SELECT_USER_ROLE_BY_USER_ID = "SELECT * FROM role_user ru LEFT JOIN role r ON ru.id_role=r.id_role WHERE ru.id_user=?";
	
	
	@Override
	public Role getRole(long roleId) {
		return getJdbcTemplate().queryForObject(SQL_SELECT_ROLE_BY_ID, new Object[]{roleId}, new RoleRowMapper());
	}
	
	@Override
	public List<Role> getUserRole(long userId) {
		List<Role> list = getJdbcTemplate().query(SQL_SELECT_USER_ROLE_BY_USER_ID, new Object[]{userId}, new UserRoleRowMapper());
		return list;
	}
	
	private static class RoleRowMapper implements RowMapper<Role>{

		@Override
		public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
			Role role = row(rs, rowNum);
			return role;
		}
		
	}
	
	private static class UserRoleRowMapper implements RowMapper<Role>{

		@Override
		public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
			Role role = row(rs, rowNum);
			role.setSupervisorId(rs.getLong("supervisor"));
			return role;
		}
		
	}
	
	private static Role row(ResultSet rs, int rowNum) throws SQLException{
		Role role = new Role();
		role.setId(rs.getLong("id_role"));
		role.setName(rs.getString("name"));
		role.setText(rs.getString("text"));
		role.setMoneyMission(rs.getInt("money_mission"));
		role.setEnergyMission(rs.getInt("energy_mission"));
		role.setMoneyFight(rs.getInt("money_fight"));
		role.setHealthFight(rs.getInt("health_fight"));
		role.setAttack(rs.getInt("attack"));
		role.setProtect(rs.getInt("protect"));
		role.setLevelStep(rs.getInt("level_step"));
		return role;
	}
}
