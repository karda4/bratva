package ua.kardach.mmo.bratva.dao;

import java.util.List;

import ua.kardach.mmo.bratva.model.Role;

public interface RoleDao {
	
	public Role getRole(long roleId);
	
	public List<Role> getUserRole(long userId);
}
