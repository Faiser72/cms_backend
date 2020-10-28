package com.vetologic.cms.model.dao.receipt;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vetologic.cms.dto.user.UserDto;

@Repository
public class ReceiptDaoImpl implements ReceiptDao {

	@Autowired
	private EntityManager entityManager;

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Override
	public List<?> getAll(String beanClassName) {
		Session session = getSession();
		List<?> listOfObjects = null;
		try {
			Query<?> query = session.createQuery("FROM " + beanClassName + " WHERE activeFlag = ?0");
			query.setParameter(0, 1);
			listOfObjects = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfObjects;
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
	public Object getById(String beanClassName, int id) {
		Session session = getSession();
		Object object = null;
		try {
			Query<?> query = session
					.createQuery("FROM " + beanClassName + " WHERE activeFlag = ?0 AND appointmentId = ?1");
			query.setParameter(0, 1);
			query.setParameter(1, id);
			object = query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public Object getByAppointmentId(String beanClassName, int appointmentId) {
		Session session = getSession();
		Object object = null;
		try {
			Query<?> query = session
					.createQuery("FROM " + beanClassName + " WHERE activeFlag = ?0 AND appointment.appointmentId =?1");
			query.setParameter(0, 1);
			query.setParameter(1, appointmentId);
			object = query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public List<?> getAllBtwnTwoDates(String beanClassName, String fromDate, String toDate) {
		Session session = getSession();
		List<?> listOfObjects = null;
		try {
			Query<?> query = session.createQuery("FROM " + beanClassName
					+ " WHERE activeFlag = ?0 AND generatedFlag = ?1 AND (date BETWEEN ?2 AND ?3)");
			query.setParameter(0, 1);
			query.setParameter(1, 0);
			query.setParameter(2, fromDate);
			query.setParameter(3, toDate);
			listOfObjects = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfObjects;
	}

}
