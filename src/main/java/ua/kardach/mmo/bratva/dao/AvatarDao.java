package ua.kardach.mmo.bratva.dao;

import ua.kardach.mmo.bratva.model.Avatar;

public interface AvatarDao {
	
	public Avatar getAvatar(long id);
	
	public Avatar getRandomAvatar(int sex, boolean all);
}
