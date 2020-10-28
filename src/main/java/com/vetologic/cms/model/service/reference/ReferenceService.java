package com.vetologic.cms.model.service.reference;

import java.util.List;

import com.vetologic.cms.dto.user.UserDto;

public interface ReferenceService {

	List<?> getAll(String beanClassName);

	int save(Object object);

	UserDto getUserByName(String username);

	Object getById(String beanClassName, int id);

	Object getByPatientIdAndDate(String beanClassName, int id, String date);

	boolean update(Object object);

	Object checkSavedAndGetData(String beanClassName, int id);

}
