package com.vetologic.cms.model.service.user;

import java.util.List;

import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.dto.user.UserTypeDto;

public interface UserService {

	UserDto getUserByName(String username);

	int save(Object object);

	List<?> getAll(String beanClassName);

	Object getById(String beanClassName, String beanPropName, int id);

	boolean update(Object object);

	UserTypeDto getDesignationByRoleName(String role);

	UserDto getUserDetailsByEmailId(String emailId);

	List<?> getAllExceptOneUser(String beanClassName, int id);

	boolean deleteUser(UserDto user);

	List<UserDto> getUserByDesignation(UserTypeDto designation);

	List<UserDto> getAllUsers();

	List<UserDto> getAllUsersExceptOneUser(int userId);

	Object getByIdForUndo(String beanClassName, String beanPropName, int id);

}
