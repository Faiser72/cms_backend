package com.vetologic.cms.model.service.labtest;

import java.util.List;

import com.vetologic.cms.dto.user.UserDto;

public interface LabTestService {

	List<?> getAll(String beanClassName);

	List<?> getAllByPatientId(String beanClassName, int id);

	int save(Object object);

	UserDto getUserByName(String username);

	Object getById(String beanClassName, int id);

	boolean update(Object object);

	Object getByAppointmentId(String beanClassName, int id);

	Object getByPatientIdAndDate(String beanClassName, int id, String date);

	Object checkSavedAndGetData(String beanClassName, int id);
}
