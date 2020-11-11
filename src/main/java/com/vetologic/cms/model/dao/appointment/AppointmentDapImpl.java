package com.vetologic.cms.model.dao.appointment;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vetologic.cms.dto.user.UserDto;

@Repository
public class AppointmentDapImpl implements AppointmentDao {

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
	public List<?> getAllExceptThis(String beanClassName, int appointmentId) {
		Session session = getSession();
		List<?> listOfObjects = null;
		try {
			Query<?> query = session
					.createQuery("FROM " + beanClassName + " WHERE activeFlag = ?0 AND appointmentId NOT IN(?1)");
			query.setParameter(0, 1);
			query.setParameter(1, appointmentId);
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
	public List<?> getByDoctorId(String beanClassName, int id) {
		Session session = getSession();
		List<?> listOfObjects = null;
		try {
			Query<?> query = session
					.createQuery("FROM " + beanClassName + " WHERE activeFlag = ?0 AND doctorName.doctorId = ?1");
			query.setParameter(0, 1);
			query.setParameter(1, id);
			listOfObjects = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfObjects;
	}

	public List<?> getByDoctorIdAndDate(String beanClassName, int doctorId, String currentDate) {
		Session session = getSession();
		List<?> listOfObjects = null;
		try {
			Query<?> query = session.createQuery("FROM " + beanClassName
					+ " WHERE activeFlag = ?0 AND doctorName.doctorId = ?1 AND appointmentDate = ?2 AND testedFlag = ?3");
			query.setParameter(0, 1);
			query.setParameter(1, doctorId);
			query.setParameter(2, currentDate);
			query.setParameter(3, 0);
			listOfObjects = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfObjects;
	}

	@Override
	public List<?> getAllTestedBtwnDates(String beanClassName, String fromDate, String toDate) {
		Session session = getSession();
		List<?> listOfObjects = null;
		try {
			Query<?> query = session.createQuery("FROM " + beanClassName
					+ " WHERE activeFlag = ?0 AND testedFlag = ?1 AND testedDate BETWEEN ?2 AND ?3");
			query.setParameter(0, 1);
			query.setParameter(1, 1);
			query.setParameter(2, fromDate);
			query.setParameter(3, toDate);
			listOfObjects = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfObjects;
	}

	@Override
	public List<?> getAllAppointmentBtwnDates(String beanClassName, String fromDate, String toDate) {
		Session session = getSession();
		List<?> listOfObjects = null;
		try {
			Query<?> query = session.createQuery(
					"FROM " + beanClassName + " WHERE activeFlag = ?0 AND appointmentDate BETWEEN ?1 AND ?2");
			query.setParameter(0, 1);
			query.setParameter(1, fromDate);
			query.setParameter(2, toDate);
			listOfObjects = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfObjects;
	}

	@Override
	public List<?> getAllAppointmentOfDoctorBtwnDates(String beanClassName, int id, String fromDate, String toDate) {
		Session session = getSession();
		List<?> listOfObjects = null;
		try {
			Query<?> query = session.createQuery("FROM " + beanClassName
					+ " WHERE activeFlag = ?0 AND doctorName.doctorId = ?1 AND appointmentDate BETWEEN ?2 AND ?3");
			query.setParameter(0, 1);
			query.setParameter(1, id);
			query.setParameter(2, fromDate);
			query.setParameter(3, toDate);
			listOfObjects = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfObjects;
	}

	@Override
	public List<?> getAllTestedList(String beanClassName) {
		Session session = getSession();
		List<?> listOfObjects = null;
		try {
			Query<?> query = session.createQuery("FROM " + beanClassName + " WHERE activeFlag = ?0 AND testedFlag =?1");
			query.setParameter(0, 1);
			query.setParameter(1, 1);
			listOfObjects = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfObjects;
	}

	public List<?> getByDate(String beanClassName, String currentDate) {
		Session session = getSession();
		List<?> listOfObjects = null;
		try {
			Query<?> query = session.createQuery(
					"FROM " + beanClassName + " WHERE activeFlag = ?0 AND testedFlag = ?1 AND appointmentDate = ?2");
			query.setParameter(0, 1);
			query.setParameter(1, 0);
			query.setParameter(2, currentDate);
			listOfObjects = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfObjects;
	}

	@Override
	public Object getLastAppointmentDetail() {
		Session session = getSession();
		Object object = null;
		try {
			Query<?> query = session
					.createQuery("FROM AppointmentDto WHERE activeFlag = ?0 ORDER BY appointmentId DESC");
			query.setParameter(0, 1);
			query.setMaxResults(1);
			object = query.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
	
	public List<?> getByCurrentDate(String beanClassName, String currentDate) {
		Session session = getSession();
		List<?> listOfObjects = null;
		try {
			Query<?> query = session.createQuery(
					"FROM " + beanClassName + " WHERE activeFlag = ?0 AND appointmentDate = ?1 ORDER BY appointmentTime");
			query.setParameter(0, 1);
			query.setParameter(1, currentDate);
			listOfObjects = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfObjects;
	}
}
