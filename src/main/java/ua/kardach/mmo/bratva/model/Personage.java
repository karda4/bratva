package ua.kardach.mmo.bratva.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.kardach.mmo.bratva.util.Koof;

/*
 * @author Yura Kardach
 * 
 */

public class Personage {
	private Long userId;
	private int attack;
	private int protect;
	private int maxHealth;
	private long tHealth;
	private int maxEnergy;
	private long tEnergy;
	private int maxPower;
	private long tPower;
	private int win;
	private int lose;
	private int authority;
	private int level;
	private Date time;
	private int lastMission;
	private int cash;
	private int money;
	private int points;
	private int rank;
	private int dRank;
	private int aBratva;
	private int aMission;
	private int bank;
	private int forwardAttack;
	private int forwardProtect;
	private int defenderAttack;
	private int defenderProtect;
	private int bratvaForwardAttack;
	private int bratvaForwardProtect;
	private int bratvaDefenderAttack;
	private int bratvaDefenderProtect;
	private int stern;
	private int respect;
	private int lifeFight;
	
	private Map<Inventory, Integer> inventories;
	private List<UserPodgon> userPodgons;
	private List<Boost> boosts;
	private List<Role> roles;
	private Map<Role.Param, Role> mapRoles;

	// Dynamic parameters. They need to refresh for actual data
	private int health;
	private int energy;
	private int power;
	private boolean reachNewLevel;
	
	public Personage(PersonageBuilder builder) {
		this.userId = builder.userId;
		this.attack = builder.attack;
		this.protect = builder.protect;
		this.maxHealth = builder.maxHealth;
		this.tHealth = builder.tHealth;
		this.maxEnergy = builder.maxEnergy;
		this.tEnergy = builder.tEnergy;
		this.maxPower = builder.maxPower;
		this.tPower = builder.tPower;
		this.win = builder.win;
		this.lose = builder.lose;
		this.authority = builder.authority;
		this.level = builder.level;
		this.time = builder.time;
		this.lastMission = builder.lastMission;
		this.cash = builder.cash;
		this.money = builder.money;
		this.points = builder.points;
		this.rank = builder.rank;
		this.dRank = builder.dRank;
		this.aBratva = builder.aBratva;
		this.aMission = builder.aMission;
		this.bank = builder.bank;
		this.forwardAttack = builder.forwardAttack;
		this.forwardProtect = builder.forwardProtect;
		this.defenderAttack = builder.defenderAttack;
		this.defenderProtect = builder.defenderProtect;
		this.bratvaForwardAttack = builder.bratvaForwardAttack;
		this.bratvaForwardProtect = builder.bratvaForwardProtect;
		this.bratvaDefenderAttack = builder.bratvaDefenderAttack;
		this.bratvaDefenderProtect = builder.bratvaDefenderProtect;
		this.stern = builder.stern;
		this.respect = builder.respect;
		this.lifeFight = builder.lifeFight;
	}

	@Override
	public String toString() {
		return "Personage [userId=" + userId + ", attack=" + attack + ", protect=" + protect + ", maxHealth=" + maxHealth + ", tHealth=" + tHealth + ", maxEnergy=" + maxEnergy + ", tEnergy=" + tEnergy + ", maxPower=" + maxPower + ", tPower=" + tPower
				+ ", win=" + win + ", lose=" + lose + ", authority=" + authority + ", level=" + level + ", time=" + time + ", lastMission=" + lastMission + ", cash=" + cash + ", money=" + money + ", points=" + points + ", rank=" + rank + ", dRank="
				+ dRank + ", aBratva=" + aBratva + ", aMission=" + aMission + ", bank=" + bank + ", forwardAttack=" + forwardAttack + ", forwardProtect=" + forwardProtect + ", defenderAttack=" + defenderAttack + ", defenderProtect=" + defenderProtect
				+ ", bratvaForwardAttack=" + bratvaForwardAttack + ", bratvaForwardProtect=" + bratvaForwardProtect + ", bratvaDefenderAttack=" + bratvaDefenderAttack + ", bratvaDefenderProtect=" + bratvaDefenderProtect + ", stern=" + stern
				+ ", respect=" + respect + ", lifeFight=" + lifeFight + "]";
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public boolean isReachNewLevel() {
		return reachNewLevel;
	}

	public void setReachNewLevel(boolean reachNewLevel) {
		this.reachNewLevel = reachNewLevel;
	}
	
	public void regenerateHealth() {
		setHealth(getStatHealth());
	}

	public void regeneratePower() {
		setPower(getStatPower());
	}

	public void regenerateEnergy() {
		setEnergy(getStatEnergy());
	}
	
	private int getStatHealth(){
		return getStatCommon(Koof.regenerationHealth, gettHealth(), getMaxHealth());		
	}
	
	private int getStatEnergy(){
		return getStatCommon(Koof.regenerationEnergy, gettEnergy(), getMaxEnergy());		
	}
	
	private int getStatPower(){
		return getStatCommon(Koof.regenerationPower, gettPower(), getMaxPower());		
	}
	
	private int getStatCommon(int koofRegenaration, long lastTime, int maxValue){
		long currentTime = System.currentTimeMillis();
		if (koofRegenaration < 1) {
			koofRegenaration = 1;
		}
		long value = currentTime - lastTime;
		value = value / koofRegenaration;
		if (value < 0) {
			value = 0;
		}
		if (value > maxValue) {
			value = maxValue;
		}
		return (int)value;
	}
	
	private void updateStatHealth(int delta){
		long time = getTimeStatCommon(Koof.regenerationHealth, getStatHealth(), getMaxHealth(), delta);
		settHealth(time);
	}
	
	private void updateStatEnergy(int delta){
		long time = getTimeStatCommon(Koof.regenerationEnergy, getStatEnergy(), getMaxEnergy(), delta);
		settEnergy(time);
	}
	
	private void updateStatPower(int delta){
		long time = getTimeStatCommon(Koof.regenerationPower, getStatPower(), getMaxPower(), delta);
		settPower(time);
	}
	
	private long getTimeStatCommon(int koofRegenaration, int value, int maxValue, int delta){
		long currentTime = System.currentTimeMillis();
		
		int riznucya = value + delta;
		if(riznucya < 0){
			riznucya = 0;
		}
		if(riznucya > maxValue){
			riznucya = maxValue;
		}
		long t = currentTime - (riznucya * koofRegenaration);
		return t;
	}
	
	public void checkReachNewLevel(int authorityForNextLevel) {
		if (getAuthority() >= authorityForNextLevel) {
			setReachNewLevel(true);

			setAuthority(getAuthority() - authorityForNextLevel);
			setLevel(getLevel() + 1);
			setPoints(getPoints() + Koof.points);
			updateStatHealth(getMaxHealth());
			regenerateHealth();
			updateStatEnergy(getMaxEnergy());
			regenerateEnergy();
			updateStatPower(getMaxPower());
			regeneratePower();
		}
	}

	public static class PersonageBuilder {
		private Long userId;
		private int attack;
		private int protect;
		private int maxHealth;
		private long tHealth;
		private int maxEnergy;
		private long tEnergy;
		private int maxPower;
		private long tPower;
		private int win;
		private int lose;
		private int authority;
		private int level;
		private Date time;
		private int lastMission;
		private int cash;
		private int money;
		private int points;
		private int rank;
		private int dRank;
		private int aBratva;
		private int aMission;
		private int bank;
		private int forwardAttack;
		private int forwardProtect;
		private int defenderAttack;
		private int defenderProtect;
		private int bratvaForwardAttack;
		private int bratvaForwardProtect;
		private int bratvaDefenderAttack;
		private int bratvaDefenderProtect;
		private int stern;
		private int respect;
		private int lifeFight;

		public PersonageBuilder setUserId(Long userId) {
			this.userId = userId;
			return this;
		}

		public PersonageBuilder setAttack(int attack) {
			this.attack = attack;
			return this;
		}

		public PersonageBuilder setProtect(int protect) {
			this.protect = protect;
			return this;
		}

		public PersonageBuilder setMaxHealth(int maxHealth) {
			this.maxHealth = maxHealth;
			return this;
		}

		public PersonageBuilder settHealth(long tHealth) {
			this.tHealth = tHealth;
			return this;
		}

		public PersonageBuilder setMaxEnergy(int maxEnergy) {
			this.maxEnergy = maxEnergy;
			return this;
		}

		public PersonageBuilder settEnergy(long tEnergy) {
			this.tEnergy = tEnergy;
			return this;
		}

		public PersonageBuilder setMaxPower(int maxPower) {
			this.maxPower = maxPower;
			return this;
		}

		public PersonageBuilder settPower(long tPower) {
			this.tPower = tPower;
			return this;
		}

		public PersonageBuilder setWin(int win) {
			this.win = win;
			return this;
		}

		public PersonageBuilder setLose(int lose) {
			this.lose = lose;
			return this;
		}

		public PersonageBuilder setAuthority(int authority) {
			this.authority = authority;
			return this;
		}

		public PersonageBuilder setLevel(int level) {
			this.level = level;
			return this;
		}

		public PersonageBuilder setTime(Date time) {
			this.time = time;
			return this;
		}

		public PersonageBuilder setLastMission(int lastMission) {
			this.lastMission = lastMission;
			return this;
		}

		public PersonageBuilder setCash(int cash) {
			this.cash = cash;
			return this;
		}

		public PersonageBuilder setMoney(int money) {
			this.money = money;
			return this;
		}

		public PersonageBuilder setPoints(int points) {
			this.points = points;
			return this;
		}

		public PersonageBuilder setRank(int rank) {
			this.rank = rank;
			return this;
		}

		public PersonageBuilder setdRank(int dRank) {
			this.dRank = dRank;
			return this;
		}

		public PersonageBuilder setaBratva(int aBratva) {
			this.aBratva = aBratva;
			return this;
		}

		public PersonageBuilder setaMission(int aMission) {
			this.aMission = aMission;
			return this;
		}

		public PersonageBuilder setBank(int bank) {
			this.bank = bank;
			return this;
		}

		public PersonageBuilder setForwardAttack(int forwardAttack) {
			this.forwardAttack = forwardAttack;
			return this;
		}

		public PersonageBuilder setForwardProtect(int forwardProtect) {
			this.forwardProtect = forwardProtect;
			return this;
		}

		public PersonageBuilder setDefenderAttack(int defenderAttack) {
			this.defenderAttack = defenderAttack;
			return this;
		}

		public PersonageBuilder setDefenderProtect(int defenderProtect) {
			this.defenderProtect = defenderProtect;
			return this;
		}

		public PersonageBuilder setBratvaForwardAttack(int bratvaForwardAttack) {
			this.bratvaForwardAttack = bratvaForwardAttack;
			return this;
		}

		public PersonageBuilder setBratvaForwardProtect(int bratvaForwardProtect) {
			this.bratvaForwardProtect = bratvaForwardProtect;
			return this;
		}

		public PersonageBuilder setBratvaDefenderAttack(int bratvaDefenderAttack) {
			this.bratvaDefenderAttack = bratvaDefenderAttack;
			return this;
		}

		public PersonageBuilder setBratvaDefenderProtect(int bratvaDefenderProtect) {
			this.bratvaDefenderProtect = bratvaDefenderProtect;
			return this;
		}

		public PersonageBuilder setStern(int stern) {
			this.stern = stern;
			return this;
		}

		public PersonageBuilder setRespect(int respect) {
			this.respect = respect;
			return this;
		}

		public PersonageBuilder setLifeFight(int lifeFight) {
			this.lifeFight = lifeFight;
			return this;
		}

		public Personage build() {
			return new Personage(this);
		}
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getProtect() {
		return protect;
	}

	public void setProtect(int protect) {
		this.protect = protect;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public long gettHealth() {
		return tHealth;
	}

	public void settHealth(long tHealth) {
		this.tHealth = tHealth;
	}

	public int getMaxEnergy() {
		return maxEnergy;
	}

	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}

	public long gettEnergy() {
		return tEnergy;
	}

	public void settEnergy(long tEnergy) {
		this.tEnergy = tEnergy;
	}

	public int getMaxPower() {
		return maxPower;
	}

	public void setMaxPower(int maxPower) {
		this.maxPower = maxPower;
	}

	public long gettPower() {
		return tPower;
	}

	public void settPower(long tPower) {
		this.tPower = tPower;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getLastMission() {
		return lastMission;
	}

	public void setLastMission(int lastMission) {
		this.lastMission = lastMission;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getdRank() {
		return dRank;
	}

	public void setdRank(int dRank) {
		this.dRank = dRank;
	}

	public int getaBratva() {
		return aBratva;
	}

	public void setaBratva(int aBratva) {
		this.aBratva = aBratva;
	}

	public int getaMission() {
		return aMission;
	}

	public void setaMission(int aMission) {
		this.aMission = aMission;
	}

	public int getBank() {
		return bank;
	}

	public void setBank(int bank) {
		this.bank = bank;
	}

	public int getForwardAttack() {
		return forwardAttack;
	}

	public void setForwardAttack(int forwardAttack) {
		this.forwardAttack = forwardAttack;
	}

	public int getForwardProtect() {
		return forwardProtect;
	}

	public void setForwardProtect(int forwardProtect) {
		this.forwardProtect = forwardProtect;
	}

	public int getDefenderAttack() {
		return defenderAttack;
	}

	public void setDefenderAttack(int defenderAttack) {
		this.defenderAttack = defenderAttack;
	}

	public int getDefenderProtect() {
		return defenderProtect;
	}

	public void setDefenderProtect(int defenderProtect) {
		this.defenderProtect = defenderProtect;
	}

	public int getBratvaForwardAttack() {
		return bratvaForwardAttack;
	}

	public void setBratvaForwardAttack(int bratvaForwardAttack) {
		this.bratvaForwardAttack = bratvaForwardAttack;
	}

	public int getBratvaForwardProtect() {
		return bratvaForwardProtect;
	}

	public void setBratvaForwardProtect(int bratvaForwardProtect) {
		this.bratvaForwardProtect = bratvaForwardProtect;
	}

	public int getBratvaDefenderAttack() {
		return bratvaDefenderAttack;
	}

	public void setBratvaDefenderAttack(int bratvaDefenderAttack) {
		this.bratvaDefenderAttack = bratvaDefenderAttack;
	}

	public int getBratvaDefenderProtect() {
		return bratvaDefenderProtect;
	}

	public void setBratvaDefenderProtect(int bratvaDefenderProtect) {
		this.bratvaDefenderProtect = bratvaDefenderProtect;
	}

	public int getStern() {
		return stern;
	}

	public void setStern(int stern) {
		this.stern = stern;
	}

	public int getRespect() {
		return respect;
	}

	public void setRespect(int respect) {
		this.respect = respect;
	}

	public int getLifeFight() {
		return lifeFight;
	}

	public void setLifeFight(int lifeFight) {
		this.lifeFight = lifeFight;
	}
	
	public Map<Inventory, Integer> getInventories() {
		return inventories;
	}
	
	public void setInventories(Map<Inventory, Integer> inventories) {
		this.inventories = inventories;
	}
	
	public List<UserPodgon> getUserPodgons() {
		return userPodgons;
	}
	
	public void setUserPodgons(List<UserPodgon> userPodgons) {
		this.userPodgons = userPodgons;
	}
	
	public List<Boost> getBoosts() {
		return boosts;
	}
	
	public void setBoosts(List<Boost> boosts) {
		this.boosts = boosts;
	}
	
	public int getBoostAuthority(){
		int result = 0;
		for(Boost boost : getBoosts()){
			result += boost.getAuthority();
		}
		return result;
	}

	public int getBoostMoney() {
		int result = 0;
		for(Boost boost : getBoosts()){
			result += boost.getMoney();
		}
		return result;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
		initMapRoles();
	}
	
	private void initMapRoles(){
		mapRoles = new HashMap<>();
		for(Role role : getRoles()){
			for(Role.Param param : Role.Param.values()){
				if(role.getParamValue(param) > 0){
					mapRoles.put(param, role);
					break;
				}
			}
		}
	}
	
	private Role getRoleFromMapRoles(Role.Param param){
		return mapRoles.get(param);
	}
	
	public int getRoleProcent(Role.Param param){
		Role role = getRoleFromMapRoles(param);
		if(role != null){
			int levelStep = role.getLevelStep();
			if(levelStep > 0){
				int multiple = getLevel() / levelStep;
				int paramValue = role.getParamValue(param);
				return paramValue * multiple;
			}
		}
		return 0;
	}
}
