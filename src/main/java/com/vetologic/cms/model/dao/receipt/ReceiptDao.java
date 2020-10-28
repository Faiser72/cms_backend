package com.vetologic.cms.model.dao.receipt;

import java.util.List;

import com.vetologic.cms.dto.user.UserDto;

public interface ReceiptDao {

	List<?> getAll(String beanClassName);

	int save(Object object);

	UserDto getUserByName(String username);

	Object getById(String beanClassName, int id);

	boolean update(Object object);

	Object getByAppointmentId(String beanClassName, int appointmentId);

	List<?> getAllBtwnTwoDates(String beanClassName, String fromDate, String toDate);
}
