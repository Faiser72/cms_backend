package com.vetologic.cms.model.service.doctor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vetologic.cms.dto.doctor.DoctorDto;
import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.model.dao.doctor.DoctorDao;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorDao doctorDao;

	@Override
	public List<?> getAll(String beanClassName) {
		return doctorDao.getAll(beanClassName);
	}

	@Transactional
	@Override
	public int save(Object object) {
		return doctorDao.save(object);
	}

	@Override
	public UserDto getUserByName(String username) {
		return doctorDao.getUserByName(username);
	}

	@Override
	public Object getById(String beanClassName, int id) {
		return doctorDao.getById(beanClassName, id);
	}

	@Transactional
	@Override
	public boolean update(Object object) {
		return doctorDao.update(object);
	}

	@Transactional
	@Override
	public boolean deleteDoctor(DoctorDto doctor) {
		return doctorDao.deleteDoctor(doctor);
	}

	@Override
	public Object getByUserId(String beanClassName, int id) {
		return doctorDao.getByUserId(beanClassName, id);
	}

	@Override
	public List<?> getAllDeleted(String beanClassName) {
		return doctorDao.getAllDeleted(beanClassName);
	}

	@Override
	public Object getByIdForUndo(String beanClassName, int id) {
		return doctorDao.getByIdForUndo(beanClassName, id);
	}

	@Transactional
	@Override
	public boolean deleteAllAppointmentsByDoctorId(int doctorId) {
		return doctorDao.deleteAllAppointmentsByDoctorId(doctorId);
	}

	@Transactional
	@Override
	public boolean UndoAllAppointmentsByDoctorId(int doctorId) {
		return doctorDao.UndoAllAppointmentsByDoctorId(doctorId);
	}

}
