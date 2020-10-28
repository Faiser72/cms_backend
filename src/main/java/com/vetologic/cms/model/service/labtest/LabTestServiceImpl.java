package com.vetologic.cms.model.service.labtest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.model.dao.labtest.LabTestDao;

@Service
public class LabTestServiceImpl implements LabTestService {

	@Autowired
	private LabTestDao labTestDao;

	@Override
	public List<?> getAll(String beanClassName) {
		return labTestDao.getAll(beanClassName);
	}

	@Override
	public List<?> getAllByPatientId(String beanClassName, int id) {
		return labTestDao.getAllByPatientId(beanClassName, id);
	}

	@Transactional
	@Override
	public int save(Object object) {
		return labTestDao.save(object);
	}

	@Override
	public UserDto getUserByName(String username) {
		return labTestDao.getUserByName(username);
	}

	@Override
	public Object getById(String beanClassName, int id) {
		return labTestDao.getById(beanClassName, id);
	}

	@Transactional
	@Override
	public boolean update(Object object) {
		return labTestDao.update(object);
	}

	@Override
	public Object checkSavedAndGetData(String beanClassName, int id) {
		return labTestDao.checkSavedAndGetData(beanClassName, id);
	}

	@Override
	public Object getByPatientIdAndDate(String beanClassName, int id, String date) {
		return labTestDao.getByPatientIdAndDate(beanClassName, id, date);
	}

	@Override
	public Object getByAppointmentId(String beanClassName, int id) {
		return labTestDao.getByAppointmentId(beanClassName, id);
	}

}
