package ua.kardach.mmo.bratva.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kardach.mmo.bratva.dao.AvatarDao;
import ua.kardach.mmo.bratva.model.Avatar;

@Service
public class AvatarService {
	
	@Autowired
	private AvatarDao avatarDao;
	
	public Avatar getRandomAvatar(int sex, boolean all){
		return avatarDao.getRandomAvatar(sex, all);
	}
}
