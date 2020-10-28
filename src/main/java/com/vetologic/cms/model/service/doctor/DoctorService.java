package com.vetologic.cms.model.service.doctor;

import java.util.List;

import com.vetologic.cms.dto.doctor.DoctorDto;
import com.vetologic.cms.dto.user.UserDto;

public interface DoctorService {

	List<?> getAll(String beanClassName);

	int save(Object object);

	UserDto getUserByName(String username);

	Object getById(String beanClassName, int id);

	Object getByIdForUndo(String beanClassName, int id);

	boolean update(Object object);

	boolean deleteDoctor(DoctorDto doctor);

	Object getByUserId(String beanClassName, int id);

	List<?> getAllDeleted(String beanClassName);

	boolean deleteAllAppointmentsByDoctorId(int doctorId);

	boolean UndoAllAppointmentsByDoctorId(int doctorId);

}
