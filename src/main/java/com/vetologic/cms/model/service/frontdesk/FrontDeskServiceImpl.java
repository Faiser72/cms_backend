package com.vetologic.cms.model.service.frontdesk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vetologic.cms.dto.frontdesk.FrontDeskDto;
import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.model.dao.frontdesk.FrontDeskDao;

@Service
public class FrontDeskServiceImpl implements FrontDeskService {

	@Autowired
	private FrontDeskDao frontdeskDao;

	@Override
	public List<?> getAll(String beanClassName) {
		return frontdeskDao.getAll(beanClassName);
	}

	@Transactional
	@Override
	public int save(Object object) {
		return frontdeskDao.save(object);
	}

	@Override
	public UserDto getUserByName(String username) {
		return frontdeskDao.getUserByName(username);
	}

	@Override
	public Object getById(String beanClassName, int id) {
		return frontdeskDao.getById(beanClassName, id);
	}

	@Transactional
	@Override
	public boolean update(Object object) {
		return frontdeskDao.update(object);
	}

	@Transactional
	@Override
	public boolean deleteFrontDesk(FrontDeskDto frontdesk) {
		return frontdeskDao.deleteFrontDesk(frontdesk);
	}

	@Override
	public List<?> getAllDeleted(String beanClassName) {
		return frontdeskDao.getAllDeleted(beanClassName);
	}

	@Override
	public Object getByIdForUndo(String beanClassName, int id) {
		return frontdeskDao.getByIdForUndo(beanClassName, id);
	}

//	@Override
//	public Object getByUserId(String beanClassName, int id) {
//		return frontdeskDao.getByUserId(beanClassName, id);
//	}

}
