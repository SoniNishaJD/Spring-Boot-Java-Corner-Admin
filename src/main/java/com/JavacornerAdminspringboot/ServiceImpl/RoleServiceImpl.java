package com.JavacornerAdminspringboot.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.JavacornerAdminspringboot.Entity.Role;
import com.JavacornerAdminspringboot.dao.RoleDao;
import com.JavacornerAdminspringboot.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	private RoleDao roleDao;

	public RoleServiceImpl(RoleDao roleDao) {
		super();
		this.roleDao = roleDao;
	}

	@Override
	public Role createRole(String roleName) {
		return roleDao.save(new Role(roleName));
	}

}
