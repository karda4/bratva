package ua.kardach.mmo.bratva.dao.impl;

import org.springframework.stereotype.Repository;

import ua.kardach.mmo.bratva.dao.DevelopPersonageLevelDao;

@Repository
public class DevelopPersonageLevelSpringJdbcDao extends AbstractSpringJdbcDao implements DevelopPersonageLevelDao{
	
	private final static String SQL_SELECT_AUTHORITY_FOR_NEXT_LEVEL = "SELECT authority FROM prokachka WHERE num_level=?";
	
	@Override
	public int getAuthorityForNextLevel(int currentLevel) {
		return getJdbcTemplate().queryForObject(SQL_SELECT_AUTHORITY_FOR_NEXT_LEVEL, new Object[]{currentLevel}, Integer.class);
	}

}
