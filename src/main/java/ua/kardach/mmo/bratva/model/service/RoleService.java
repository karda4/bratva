package ua.kardach.mmo.bratva.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kardach.mmo.bratva.dao.RoleDao;
import ua.kardach.mmo.bratva.model.Role;

@Service
public class RoleService {
	
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserService userService;
	
	public Role getRole(long roleId){
		return roleDao.getRole(roleId);
	}
	
	public List<Role> getUserRole(long userId){
		List<Role> roles = roleDao.getUserRole(userId);
		for(Role role : roles){
			long supervisorId = role.getSupervisorId();
			role.setSupervisor(userService.getUserById(supervisorId));
		}
		return roles;
	}
}
