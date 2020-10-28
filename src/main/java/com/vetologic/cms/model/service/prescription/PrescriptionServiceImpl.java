package com.vetologic.cms.model.service.prescription;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.model.dao.prescription.PrescriptionDao;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

	@Autowired
	private PrescriptionDao prescriptionDao;

	@Override
	public List<?> getAll(String beanClassName) {
		return prescriptionDao.getAll(beanClassName);
	}

	@Override
	public List<?> getAllByPatientId(String beanClassName, int id) {
		return prescriptionDao.getAllByPatientId(beanClassName, id);
	}

	@Transactional
	@Override
	public int save(Object object) {
		return prescriptionDao.save(object);
	}

	@Override
	public UserDto getUserByName(String username) {
		return prescriptionDao.getUserByName(username);
	}

	@Override
	public Object getById(String beanClassName, int id) {
		return prescriptionDao.getById(beanClassName, id);
	}

	@Override
	public Object getByAppointmentId(String beanClassName, int id) {
		return prescriptionDao.getByAppointmentId(beanClassName, id);
	}

	@Override
	public Object getByPatientId(String beanClassName, int id) {
		return prescriptionDao.getByPatientId(beanClassName, id);
	}

	@Transactional
	@Override
	public boolean update(Object object) {
		return prescriptionDao.update(object);
	}

	@Override
	public Object checkSavedAndGetData(String beanClassName, int id) {
		return prescriptionDao.checkSavedAndGetData(beanClassName, id);
	}

	@Override
	public Object getByPatientIdAndDate(String beanClassName, int id, String date) {
		return prescriptionDao.getByPatientIdAndDate(beanClassName, id, date);
	}

}
