package com.vetologic.cms.model.service.doctor;

import java.util.List;

import com.vetologic.cms.dto.user.UserDto;

public interface DoctorRoleMasterService {

	int save(Object object);

	List<?> getAll(String beanClassName);

	boolean update(Object object);

	Object getById(String beanClassName, int id);

	UserDto getUserByName(String username);

	List<?> getAllExceptOne(String beanClassName, int id);

}
