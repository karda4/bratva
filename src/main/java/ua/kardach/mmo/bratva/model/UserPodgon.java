package ua.kardach.mmo.bratva.model;

public class UserPodgon {

	private long userId;
	private long friendId;
	private long podgonId;
	private Podgon podgon;
	private int amount;
	private int time;
	
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getFriendId() {
		return friendId;
	}

	public void setFriendId(long friendId) {
		this.friendId = friendId;
	}

	public long getPodgonId() {
		return podgonId;
	}

	public void setPodgonId(long podgonId) {
		this.podgonId = podgonId;
	}

	public Podgon getPodgon() {
		return podgon;
	}

	public void setPodgon(Podgon podgon) {
		this.podgon = podgon;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

}
