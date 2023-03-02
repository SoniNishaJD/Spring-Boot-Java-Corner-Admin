package com.JavacornerAdminspringboot.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.JavacornerAdminspringboot.Entity.Role;
import com.JavacornerAdminspringboot.Entity.User;
import com.JavacornerAdminspringboot.dao.RoleDao;
import com.JavacornerAdminspringboot.dao.UserDao;
import com.JavacornerAdminspringboot.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private RoleDao roleDao;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public User loadUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User createUser(String email, String password) {
        return userDao.save(new User(email, password));
    }

    @Override
    public void assignRoleToUser(String email, String roleName) {
        User user = loadUserByEmail(email);
        Role role = roleDao.findByName(roleName);
        user.assignRoleToUser(role);
    }
}
