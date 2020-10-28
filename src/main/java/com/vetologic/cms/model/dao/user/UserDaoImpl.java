package com.vetologic.cms.model.dao.user;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.dto.user.UserTypeDto;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private EntityManager entityManager;

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Override
	public UserDto getUserByName(String username) {
		Session session = getSession();
		UserDto user = null;
		try {
			Query<UserDto> query = session.createQuery("FROM UserDto WHERE activeFlag=?0 AND username=?1",
					UserDto.class);
			query.setParameter(0, 1);
			query.setParameter(1, username);
			user = query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int save(Object object) {
		Serializable serializable = 0;
		Session session = getSession();
		try {
			serializable = session.save(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (int) serializable;
	}

	@Override
	public List<?> getAll(String beanClassName) {
		Session session = getSession();
		List<?> listOfObjects = null;
		try {
			Query<?> query = session.createQuery("FROM " + beanClassName + " WHERE activeFlag=?0");
			query.setParameter(0, 1);
			listOfObjects = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfObjects;
	}

	@Override
	public Object getById(String beanClassName, String beanPropName, int id) {
		Session session = getSession();
		Object object = null;
		try {
			Query<?> query = session
					.createQuery("FROM " + beanClassName + " WHERE activeFlag=?0 AND " + beanPropName + "=?1");
			query.setParameter(0, 1);
			query.setParameter(1, id);
			object = query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public boolean update(Object object) {
		Session session = getSession();
		try {
			session.update(object);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public UserTypeDto getDesignationByRoleName(String role) {
		Session session = getSession();
		UserTypeDto userType = null;
		try {
			Query<UserTypeDto> query = session.createQuery("FROM UserTypeDto WHERE activeFlag=?0 AND role=?1",
					UserTypeDto.class);
			query.setParameter(0, 1);
			query.setParameter(1, role);
			userType = query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userType;
	}

	@Override
	public UserDto getUserDetailsByEmailId(String emailId) {
		Session session = getSession();
		UserDto user = null;
		try {
			Query<UserDto> query = session.createQuery("FROM UserDto WHERE activeFlag=?0 AND emailId=?1",
					UserDto.class);
			query.setParameter(0, 1);
			query.setParameter(1, emailId);
			user = query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<?> getAllExceptOneUser(String beanClassName, int id) {
		Session session = getSession();
		List<?> listOfObjects = null;
		try {
			Query<?> query = session
					.createQuery("FROM " + beanClassName + " WHERE activeFlag=?0 AND userId NOT IN(?1)");
			query.setParameter(0, 1);
			query.setParameter(1, id);
			listOfObjects = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfObjects;
	}

	@Override
	public boolean deleteUser(UserDto user) {
		Session session = getSession();
		try {
			session.delete(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<UserDto> getUserByDesignation(UserTypeDto userType) {
		Session session = getSession();
		List<UserDto> listOfUsers = null;
		try {
			Query<UserDto> query = session.createQuery("FROM UserDto WHERE activeFlag=?0 AND userType.userTypeId=?1",
					UserDto.class);
			query.setParameter(0, 1);
			query.setParameter(1, userType.getUserTypeId());
			listOfUsers = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfUsers;
	}

	@Override
	public List<UserDto> getAllUsers() {
		Session session = getSession();
		List<UserDto> listOfObjects = null;
		try {
			Query<UserDto> query = session.createQuery("FROM UserDto", UserDto.class);
			listOfObjects = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfObjects;
	}

	@Override
	public List<UserDto> getAllUsersExceptOneUser(int userId) {
		Session session = getSession();
		List<UserDto> listOfObjects = null;
		try {
			Query<UserDto> query = session.createQuery("FROM UserDto WHERE userId NOT IN(?0)", UserDto.class);
			query.setParameter(0, userId);
			listOfObjects = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfObjects;
	}

	@Override
	public Object getByIdForUndo(String beanClassName, String beanPropName, int id) {
		Session session = getSession();
		Object object = null;
		try {
			Query<?> query = session
					.createQuery("FROM " + beanClassName + " WHERE activeFlag=?0 AND " + beanPropName + "=?1");
			query.setParameter(0, 0);
			query.setParameter(1, id);
			object = query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
}
