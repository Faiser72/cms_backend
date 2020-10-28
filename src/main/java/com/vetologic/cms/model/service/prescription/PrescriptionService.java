package com.vetologic.cms.model.service.prescription;

import java.util.List;

import com.vetologic.cms.dto.user.UserDto;

public interface PrescriptionService {

	List<?> getAll(String beanClassName);

	List<?> getAllByPatientId(String beanClassName, int id);

	int save(Object object);

	UserDto getUserByName(String username);

	Object getById(String beanClassName, int id);

	Object getByPatientId(String beanClassName, int id);

	Object getByAppointmentId(String beanClassName, int id);

	Object getByPatientIdAndDate(String beanClassName, int id, String date);

	boolean update(Object object);

	Object checkSavedAndGetData(String beanClassName, int id);
}
