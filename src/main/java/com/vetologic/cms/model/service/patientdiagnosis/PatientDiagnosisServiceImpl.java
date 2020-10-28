package com.vetologic.cms.model.service.patientdiagnosis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.model.dao.patientdiagnosis.PatientDiagnosisDao;

@Service
public class PatientDiagnosisServiceImpl implements PatientDiagnosisService {

	@Autowired
	private PatientDiagnosisDao patientDiagnosisDao;

	@Override
	public List<?> getAll(String beanClassName) {
		return patientDiagnosisDao.getAll(beanClassName);
	}

	@Override
	public List<?> getAllByPatientId(String beanClassName, int id) {
		return patientDiagnosisDao.getAllByPatientId(beanClassName, id);
	}

	@Transactional
	@Override
	public int save(Object object) {
		return patientDiagnosisDao.save(object);
	}

	@Override
	public UserDto getUserByName(String username) {
		return patientDiagnosisDao.getUserByName(username);
	}

	@Override
	public Object getById(String beanClassName, int id) {
		return patientDiagnosisDao.getById(beanClassName, id);
	}

	@Transactional
	@Override
	public boolean update(Object object) {
		return patientDiagnosisDao.update(object);
	}

	@Override
	public List<?> getAllExceptOnePatientDiagnosis(String beanClassName, int id) {
		return patientDiagnosisDao.getAllExceptOnePatientDiagnosis(beanClassName, id);
	}

	@Override
	public Object checkSavedAndGetData(String beanClassName, int id) {
		return patientDiagnosisDao.checkSavedAndGetData(beanClassName, id);
	}

	@Override
	public Object getByAppointmentId(String beanClassName, int id) {
		return patientDiagnosisDao.getByAppointmentId(beanClassName, id);
	}
}
