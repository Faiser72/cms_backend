package com.vetologic.cms.model.dao.doctor;

import java.util.List;

import com.vetologic.cms.dto.doctor.DoctorDto;
import com.vetologic.cms.dto.user.UserDto;

public interface DoctorDao {

	List<?> getAll(String beanClassName);

	int save(Object object);

	UserDto getUserByName(String username);

	Object getById(String beanClassName, int id);

	Object getByIdForUndo(String beanClassName, int id);

	Object getByUserId(String beanClassName, int id);

	boolean update(Object object);

	boolean deleteDoctor(DoctorDto doctor);

	List<?> getAllDeleted(String beanClassName);

	boolean deleteAllAppointmentsByDoctorId(int doctorId);

	boolean UndoAllAppointmentsByDoctorId(int doctorId);

}
