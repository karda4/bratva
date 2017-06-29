package ua.kardach.mmo.bratva.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
	private Long id;
	@NotNull
	@Size(min=4, max=12)
	private String nick;
	@NotNull
	@Size(min=3)
	private String password;
	private String phone;
	private boolean bot;
	private int sex;
	private Date registrationTime;
	private Avatar avatar;
	private String purse;
	private Personage personage;
	
	public User() {
	
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isBot() {
		return bot;
	}

	public void setBot(boolean bot) {
		this.bot = bot;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
	}

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	public String getPurse() {
		return purse;
	}

	public void setPurse(String purse) {
		this.purse = purse;
	}

	public Personage getPersonage() {
		return personage;
	}

	public void setPersonage(Personage personage) {
		this.personage = personage;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nick=" + nick + ", password=" + password + ", phone=" + phone + ", bot=" + bot + ", sex=" + sex + ", registrationTime=" + registrationTime + ", avatar=" + avatar + ", purse=" + purse + ", personage=" + personage+ "]";
	}
		
}