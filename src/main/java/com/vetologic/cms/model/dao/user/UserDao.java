package com.vetologic.cms.model.dao.user;

import java.util.List;

import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.dto.user.UserTypeDto;

public interface UserDao {

	UserDto getUserByName(String username);

	int save(Object object);

	List<?> getAll(String beanClassName);

	Object getById(String beanClassName, String beanPropName, int id);

	Object getByIdForUndo(String beanClassName, String beanPropName, int id);

	boolean update(Object object);

	UserTypeDto getDesignationByRoleName(String role);

	UserDto getUserDetailsByEmailId(String emailId);

	List<?> getAllExceptOneUser(String beanClassName, int id);

	boolean deleteUser(UserDto user);

	List<UserDto> getUserByDesignation(UserTypeDto userType);

	List<UserDto> getAllUsers();

	List<UserDto> getAllUsersExceptOneUser(int userId);
}
