package com.vetologic.cms.model.dao.doctor;

import java.util.List;

import com.vetologic.cms.dto.user.UserDto;

public interface DoctorRoleMasterDao {

	int save(Object object);

	List<?> getAll(String beanClassName);

	Object getById(String beanClassName, int id);

	boolean update(Object object);

	UserDto getUserByName(String username);

	List<?> getAllExceptOne(String beanClassName, int id);
}
