package ua.kardach.mmo.bratva.model;

import java.util.Map;

public class Quest {

	private long id;
	private long missionId;
	private String name;
	private int money;
	private int authority;
	private int energy;
	private int clickToComplete;
	
	private Map<Inventory, Integer> requiredInventories;	
	private Map<Inventory, Integer> bonusInventories;
	private Map<Podgon, Integer> bonusPodgons;
	
	//user specific
	private int amountComplete;
	private int completePercentage;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMissionId() {
		return missionId;
	}

	public void setMissionId(long missionId) {
		this.missionId = missionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getClickToComplete() {
		return clickToComplete;
	}

	public void setClickToComplete(int click) {
		this.clickToComplete = click;
	}
	
	public Map<Inventory, Integer> getRequiredInventories() {
		return requiredInventories;
	}
	
	public void setRequiredInventories(Map<Inventory, Integer> requiredInventories) {
		this.requiredInventories = requiredInventories;
	}
	
	public Map<Inventory, Integer> getBonusInventories() {
		return bonusInventories;
	}
	
	public void setBonusInventories(Map<Inventory, Integer> bonusInventories) {
		this.bonusInventories = bonusInventories;
	}
	
	public Map<Podgon, Integer> getBonusPodgons() {
		return bonusPodgons;
	}
	
	public void setBonusPodgons(Map<Podgon, Integer> bonusPodgons) {
		this.bonusPodgons = bonusPodgons;
	}
			
	public int getAmountComplete() {
		return amountComplete;
	}
	
	public void setAmountComplete(int amountComplete) {
		this.amountComplete = amountComplete;
		calculateCompletePercentage();
	}
	
	public int getCompletePercentage() {
		return completePercentage;
	}
	
	public void setCompletePercentage(int completePercentage) {
		this.completePercentage = completePercentage;
	}
	
	private void calculateCompletePercentage(){
		int result = 0;
		if(getAmountComplete() == getClickToComplete()){
			result = 100;
		}
		else{
			result = (getAmountComplete() * 100) / getClickToComplete();
		}
		setCompletePercentage(result);		
	}
	
}
