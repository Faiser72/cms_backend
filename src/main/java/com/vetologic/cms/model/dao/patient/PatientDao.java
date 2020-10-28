package com.vetologic.cms.model.dao.patient;

import java.util.List;

import com.vetologic.cms.dto.user.UserDto;

public interface PatientDao {

	List<?> getAll(String beanClassName);

	int save(Object object);

	UserDto getUserByName(String username);

	Object getById(String beanClassName, int id);

	boolean update(Object object);

	List<?> getAllExceptOnePatient(String beanClassName, int id);

	boolean deleteAllAppointmentsByPatientId(int patientId);

	boolean UndoAllAppointmentsByPatientId(int patientId);

}
