package com.vetologic.cms.model.service.patientdiagnosis;

import java.util.List;

import com.vetologic.cms.dto.user.UserDto;

public interface PatientDiagnosisService {

	List<?> getAll(String beanClassName);

	List<?> getAllByPatientId(String beanClassName, int id);

	int save(Object object);

	UserDto getUserByName(String username);

	Object getById(String beanClassName, int id);

	boolean update(Object object);

	List<?> getAllExceptOnePatientDiagnosis(String beanClassName, int id);

	Object checkSavedAndGetData(String beanClassName, int id);

	Object getByAppointmentId(String beanClassName, int id);

}
