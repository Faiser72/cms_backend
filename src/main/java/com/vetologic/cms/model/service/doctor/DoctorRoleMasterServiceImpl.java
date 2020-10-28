package com.vetologic.cms.model.service.doctor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.model.dao.doctor.DoctorRoleMasterDao;

@Service
public class DoctorRoleMasterServiceImpl implements DoctorRoleMasterService {

	@Autowired
	private DoctorRoleMasterDao doctorRoleMasterDao;

	@Transactional
	@Override
	public int save(Object object) {
		return doctorRoleMasterDao.save(object);
	}

	@Override
	public List<?> getAll(String beanClassName) {
		return doctorRoleMasterDao.getAll(beanClassName);
	}

	@Override
	public Object getById(String beanClassName, int id) {
		return doctorRoleMasterDao.getById(beanClassName, id);
	}

	@Transactional
	@Override
	public boolean update(Object object) {
		return doctorRoleMasterDao.update(object);
	}

	@Override
	public UserDto getUserByName(String username) {
		return doctorRoleMasterDao.getUserByName(username);
	}

	@Override
	public List<?> getAllExceptOne(String beanClassName, int id) {
		return doctorRoleMasterDao.getAllExceptOne(beanClassName, id);
	}
}
