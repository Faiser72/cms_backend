package com.vetologic.cms.model.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vetologic.cms.model.dao.user.UserTypeDao;

@Service
public class UserTypeServiceImpl implements UserTypeService {

	@Autowired
	private UserTypeDao userTypeDao;

	@Override
	public List<?> getAll(String beanClassName) {
		return userTypeDao.getAll(beanClassName);
	}

}
