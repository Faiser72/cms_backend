package com.vetologic.cms.model.service.appointment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.model.dao.appointment.AppointmentDao;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentDao appointmentDao;

	@Override
	public List<?> getAll(String beanClassName) {
		return appointmentDao.getAll(beanClassName);
	}

	@Override
	public List<?> getAllExceptThis(String beanClassName, int appointmentId) {
		return appointmentDao.getAllExceptThis(beanClassName, appointmentId);
	}

	@Transactional
	@Override
	public int save(Object object) {
		return appointmentDao.save(object);
	}

	@Override
	public UserDto getUserByName(String username) {
		return appointmentDao.getUserByName(username);
	}

	@Override
	public Object getById(String beanClassName, int id) {
		return appointmentDao.getById(beanClassName, id);
	}

	@Override
	public List<?> getByDoctorId(String beanClassName, int id) {
		return appointmentDao.getByDoctorId(beanClassName, id);
	}

	@Override
	public List<?> getByDoctorIdAndDate(String beanClassName, int id, String currentDate) {
		return appointmentDao.getByDoctorIdAndDate(beanClassName, id, currentDate);
	}

	@Transactional
	@Override
	public boolean update(Object object) {
		return appointmentDao.update(object);
	}

	@Override
	public List<?> getAllTestedBtwnDates(String beanClassName, String fromDate, String toDate) {
		return appointmentDao.getAllTestedBtwnDates(beanClassName, fromDate, toDate);
	}

	@Override
	public List<?> getAllAppointmentBtwnDates(String beanClassName, String fromDate, String toDate) {
		return appointmentDao.getAllAppointmentBtwnDates(beanClassName, fromDate, toDate);
	}

	@Override
	public List<?> getAllAppointmentOfDoctorBtwnDates(String beanClassName, int id, String fromDate, String toDate) {
		return appointmentDao.getAllAppointmentOfDoctorBtwnDates(beanClassName, id, fromDate, toDate);
	}

	@Override
	public List<?> getAllTestedList(String beanClassName) {
		return appointmentDao.getAllTestedList(beanClassName);
	}

	@Override
	public List<?> getByDate(String beanClassName, String currentDate) {
		return appointmentDao.getByDate(beanClassName, currentDate);
	}

	@Override
	public Object getLastAppointmentDetail() {
		return appointmentDao.getLastAppointmentDetail();
	}
	
	@Override
	public List<?> getByCurrentDate(String beanClassName, String currentDate) {
		return appointmentDao.getByCurrentDate(beanClassName, currentDate);
	}
}
