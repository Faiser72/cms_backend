package com.vetologic.cms.model.service.reference;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.model.dao.reference.ReferenceDao;

@Service
public class ReferenceServiceImpl implements ReferenceService {

	@Autowired
	private ReferenceDao referenceDao;

	@Override
	public List<?> getAll(String beanClassName) {
		return referenceDao.getAll(beanClassName);
	}

	@Transactional
	@Override
	public int save(Object object) {
		return referenceDao.save(object);
	}

	@Override
	public UserDto getUserByName(String username) {
		return referenceDao.getUserByName(username);
	}

	@Override
	public Object getById(String beanClassName, int id) {
		return referenceDao.getById(beanClassName, id);
	}

	@Transactional
	@Override
	public boolean update(Object object) {
		return referenceDao.update(object);
	}

	@Override
	public Object getByPatientIdAndDate(String beanClassName, int id, String date) {
		return referenceDao.getByPatientIdAndDate(beanClassName, id, date);
	}

	@Override
	public Object checkSavedAndGetData(String beanClassName, int id) {
		return referenceDao.checkSavedAndGetData(beanClassName, id);
	}
}
