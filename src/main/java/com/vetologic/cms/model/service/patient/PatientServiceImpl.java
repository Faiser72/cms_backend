package com.vetologic.cms.model.service.patient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.model.dao.patient.PatientDao;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientDao patientDao;

	@Override
	public List<?> getAll(String beanClassName) {
		return patientDao.getAll(beanClassName);
	}

	@Transactional
	@Override
	public int save(Object object) {
		return patientDao.save(object);
	}

	@Override
	public UserDto getUserByName(String username) {
		return patientDao.getUserByName(username);
	}

	@Override
	public Object getById(String beanClassName, int id) {
		return patientDao.getById(beanClassName, id);
	}

	@Transactional
	@Override
	public boolean update(Object object) {
		return patientDao.update(object);
	}

	@Override
	public List<?> getAllExceptOnePatient(String beanClassName, int id) {
		return patientDao.getAllExceptOnePatient(beanClassName, id);
	}

	@Transactional
	@Override
	public boolean deleteAllAppointmentsByPatientId(int patientId) {
		return patientDao.deleteAllAppointmentsByPatientId(patientId);
	}

	@Transactional
	@Override
	public boolean UndoAllAppointmentsByPatientId(int patientId) {
		return patientDao.UndoAllAppointmentsByPatientId(patientId);
	}

}
