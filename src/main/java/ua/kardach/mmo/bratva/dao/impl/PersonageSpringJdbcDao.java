package ua.kardach.mmo.bratva.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ua.kardach.mmo.bratva.dao.PersonageDao;
import ua.kardach.mmo.bratva.model.Personage;

@Repository
public class PersonageSpringJdbcDao extends AbstractSpringJdbcDao implements PersonageDao {
	
	private final static String SQL_SELECT_PERSONAGE = "SELECT * FROM pers WHERE id_user=?";
	private final static String SQL_INSERT_PERSONAGE = "INSERT INTO pers (id_user, max_health, max_energy, max_power, attack, protect, time) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private final static String SQL_UPDATE_PERSONAGE = "UPDATE pers SET t_health=?, t_energy=?, t_power=? WHERE id_user=?";
	
	@Override
	public boolean addPersonage(Personage pers) {
		long time = pers.getTime().getTime();
		int rows = getJdbcTemplate().update(SQL_INSERT_PERSONAGE, new Object[]{pers.getUserId(), pers.getMaxHealth(), pers.getMaxEnergy(), pers.getMaxPower(), pers.getAttack(), pers.getProtect(), time});
		return rows == 1;
	}

	@Override
	public boolean updatePersonage(Personage pers) {
		int rows = getJdbcTemplate().update(SQL_UPDATE_PERSONAGE, new Object[]{pers.gettHealth(), pers.gettEnergy(), pers.gettPower(), pers.getUserId()});
		return rows == 1;
	}

	@Override
	public boolean deletePersonage(Personage pers) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Personage findPersonageByUserId(Long userId) {
		Personage personage = getJdbcTemplate().queryForObject(SQL_SELECT_PERSONAGE, new Object[]{userId}, new PersonageRowMapper());
		return personage;
	}
	
	private static class PersonageRowMapper implements RowMapper<Personage>{

		@Override
		public Personage mapRow(ResultSet rs, int rowNum) throws SQLException {
			Date time = new Date(rs.getLong("time"));
			Personage personage = new Personage.PersonageBuilder().setUserId(rs.getLong("id_user"))
																  .setAttack(rs.getInt("attack"))
																  .setProtect(rs.getInt("protect"))
																  .setMaxHealth(rs.getInt("max_health"))
																  .settHealth(rs.getLong("t_health"))
																  .setMaxEnergy(rs.getInt("max_energy"))
																  .settEnergy(rs.getLong("t_energy"))
																  .setMaxPower(rs.getInt("max_power"))
																  .settPower(rs.getLong("t_power"))
																  .setWin(rs.getInt("win"))
																  .setLose(rs.getInt("lose"))
																  .setAuthority(rs.getInt("authority"))
																  .setLevel(rs.getInt("level"))
																  .setTime(time)
																  .setLastMission(rs.getInt("last_mission"))
																  .setCash(rs.getInt("cash"))
																  .setMoney(rs.getInt("money"))
																  .setPoints(rs.getInt("points"))
																  .setRank(rs.getInt("rank"))
																  .setdRank(rs.getInt("d_rank"))
																  .setaBratva(rs.getInt("a_bratva"))
																  .setaMission(rs.getInt("a_mission"))
																  .setBank(rs.getInt("bank"))
																  .setForwardAttack(rs.getInt("forward_attack"))
																  .setForwardProtect(rs.getInt("forward_protect"))
																  .setDefenderAttack(rs.getInt("defender_attack"))
																  .setDefenderProtect(rs.getInt("defender_protect"))
																  .setBratvaForwardAttack(rs.getInt("bratva_forward_attack"))
																  .setBratvaForwardProtect(rs.getInt("bratva_forward_protect"))
																  .setBratvaDefenderAttack(rs.getInt("bratva_defender_attack"))
																  .setBratvaDefenderProtect(rs.getInt("bratva_defender_protect"))
																  .setStern(rs.getInt("stern"))
																  .setRespect(rs.getInt("respect"))
																  .setLifeFight(rs.getInt("life_fight"))
																  .build();
			return personage;
		}
		
	}
}
