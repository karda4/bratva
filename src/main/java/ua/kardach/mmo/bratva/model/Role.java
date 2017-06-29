package ua.kardach.mmo.bratva.model;

public class Role {
	
	public static enum Param{
		MONEY_MISSION,
		ENERGY_MISSION,
		MONEY_FIGHT,
		HEALTH_FIGHT,
		ATTACK,
		PROTECT
	}

	private long id;
	private String name;
	private String text;
	private int moneyMission;
	private int energyMission;
	private int moneyFight;
	private int healthFight;
	private int attack;
	private int protect;
	private int levelStep;
	
	private long supervisorId;
	private User supervisor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getMoneyMission() {
		return moneyMission;
	}

	public void setMoneyMission(int moneyMission) {
		this.moneyMission = moneyMission;
	}

	public int getEnergyMission() {
		return energyMission;
	}

	public void setEnergyMission(int energyMission) {
		this.energyMission = energyMission;
	}

	public int getMoneyFight() {
		return moneyFight;
	}

	public void setMoneyFight(int moneyFight) {
		this.moneyFight = moneyFight;
	}

	public int getHealthFight() {
		return healthFight;
	}

	public void setHealthFight(int healthFight) {
		this.healthFight = healthFight;
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

	public int getLevelStep() {
		return levelStep;
	}

	public void setLevelStep(int levelStep) {
		this.levelStep = levelStep;
	}
	
	public long getSupervisorId() {
		return supervisorId;
	}
	
	public void setSupervisorId(long supervisorId) {
		this.supervisorId = supervisorId;
	}
	
	public User getSupervisor() {
		return supervisor;
	}
	
	public void setSupervisor(User supervisor) {
		this.supervisor = supervisor;
	}
	
	public int getParamValue(Param param){
		switch(param){
			case MONEY_MISSION:
				return getMoneyMission();
			case ENERGY_MISSION:
				return getEnergyMission();
			case MONEY_FIGHT:
				return getMoneyFight();
			case HEALTH_FIGHT:
				return getHealthFight();
			case ATTACK:
				return getAttack();
			case PROTECT:
				return getProtect();
		}
		throw new RuntimeException("Unknown error!");
	}
}
