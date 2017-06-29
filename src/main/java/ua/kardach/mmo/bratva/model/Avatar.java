package ua.kardach.mmo.bratva.model;

public class Avatar {
	private Long id;
	private String file;
	private int sex;
	private int cash;
	private int popularity;

	public Avatar() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	@Override
	public String toString() {
		return "Avatar [id=" + id + ", file=" + file + ", sex=" + sex + ", cash=" + cash + ", popularity=" + popularity + "]";
	}
		
}
