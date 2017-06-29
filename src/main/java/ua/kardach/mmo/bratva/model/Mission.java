package ua.kardach.mmo.bratva.model;

import java.util.List;

public class Mission {

	private long id;
	private String name;
	private int levelAvailable;
	private int maxComplete;

	private List<Quest> quests;
	
	//user specific
	private int amountComplete;


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

	public int getLevelAvailable() {
		return levelAvailable;
	}

	public void setLevelAvailable(int levelAvailable) {
		this.levelAvailable = levelAvailable;
	}

	public int getMaxComplete() {
		return maxComplete;
	}

	public void setMaxComplete(int maxComplete) {
		this.maxComplete = maxComplete;
	}
	
	public List<Quest> getQuests() {
		return quests;
	}

	public void setQuests(List<Quest> quests) {
		this.quests = quests;
	}
	
	public int getAmountComplete() {
		return amountComplete;
	}
	
	public void setAmountComplete(int amountComplete) {
		this.amountComplete = amountComplete;
	}
}
