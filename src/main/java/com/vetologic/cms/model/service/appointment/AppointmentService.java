package com.vetologic.cms.model.service.appointment;

import java.util.List;

import com.vetologic.cms.dto.user.UserDto;

public interface AppointmentService {

	List<?> getAll(String beanClassName);

	List<?> getAllExceptThis(String beanClassName, int appointmentId);

	int save(Object object);

	UserDto getUserByName(String username);

	Object getById(String beanClassName, int id);

	List<?> getByDoctorId(String beanClassName, int id);

	List<?> getByDoctorIdAndDate(String beanClassName, int id, String currentDate);

	boolean update(Object object);

	List<?> getAllTestedBtwnDates(String beanClassName, String fromDate, String toDate);

	List<?> getAllAppointmentBtwnDates(String beanClassName, String fromDate, String toDate);

	List<?> getAllAppointmentOfDoctorBtwnDates(String beanClassName, int id, String fromDate, String toDate);

	List<?> getAllTestedList(String beanClassName);

	List<?> getByDate(String beanClassName, String currentDate);

	Object getLastAppointmentDetail();
	
	List<?> getByCurrentDate(String beanClassName, String currentDate);

}
