package com.vetologic.cms.model.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.dto.user.UserTypeDto;
import com.vetologic.cms.model.dao.user.UserDao;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDto getUserByName(String username) {
		return userDao.getUserByName(username);
	}

	@Transactional
	@Override
	public int save(Object object) {
		return userDao.save(object);
	}

	@Override
	public List<?> getAll(String beanClassName) {
		return userDao.getAll(beanClassName);
	}

	@Override
	public Object getById(String beanClassName, String beanPropName, int id) {
		return userDao.getById(beanClassName, beanPropName, id);
	}

	@Transactional
	@Override
	public boolean update(Object object) {
		return userDao.update(object);
	}

	@Override
	public UserTypeDto getDesignationByRoleName(String role) {
		return userDao.getDesignationByRoleName(role);
	}

	@Override
	public UserDto getUserDetailsByEmailId(String emailId) {
		return userDao.getUserDetailsByEmailId(emailId);
	}

	@Override
	public List<?> getAllExceptOneUser(String beanClassName, int id) {
		return userDao.getAllExceptOneUser(beanClassName, id);
	}

	@Transactional
	@Override
	public boolean deleteUser(UserDto user) {
		return userDao.deleteUser(user);
	}

	@Override
	public List<UserDto> getUserByDesignation(UserTypeDto userType) {
		return userDao.getUserByDesignation(userType);
	}

	@Override
	public List<UserDto> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public List<UserDto> getAllUsersExceptOneUser(int userId) {
		return userDao.getAllUsersExceptOneUser(userId);
	}

	@Override
	public Object getByIdForUndo(String beanClassName, String beanPropName, int id) {
		return userDao.getByIdForUndo(beanClassName, beanPropName, id);
	}
}
