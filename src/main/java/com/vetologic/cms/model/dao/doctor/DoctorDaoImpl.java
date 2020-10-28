package com.vetologic.cms.model.dao.doctor;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vetologic.cms.dto.doctor.DoctorDto;
import com.vetologic.cms.dto.user.UserDto;

@Repository
public class DoctorDaoImpl implements DoctorDao {

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
			Query<?> query = session.createQuery("FROM " + beanClassName + " WHERE activeFlag = ?0 AND doctorId = ?1");
			query.setParameter(0, 1);
			query.setParameter(1, id);
			object = query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public boolean deleteDoctor(DoctorDto doctor) {
		Session session = getSession();
		try {
			session.delete(doctor);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Object getByUserId(String beanClassName, int id) {
		Session session = getSession();
		Object object = null;
		try {
			Query<?> query = session
					.createQuery("FROM " + beanClassName + " WHERE activeFlag = ?0 AND user.userId = ?1");
			query.setParameter(0, 1);
			query.setParameter(1, id);
			object = query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public List<?> getAllDeleted(String beanClassName) {
		Session session = getSession();
		List<?> listOfObjects = null;
		try {
			Query<?> query = session.createQuery("FROM " + beanClassName + " WHERE activeFlag = ?0");
			query.setParameter(0, 0);
			listOfObjects = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfObjects;
	}

	@Override
	public Object getByIdForUndo(String beanClassName, int id) {
		Session session = getSession();
		Object object = null;
		try {
			Query<?> query = session.createQuery("FROM " + beanClassName + " WHERE activeFlag = ?0 AND doctorId = ?1");
			query.setParameter(0, 0);
			query.setParameter(1, id);
			object = query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public boolean deleteAllAppointmentsByDoctorId(int doctorId) {
		Session session = getSession();
		try {
			Query<?> query = session.createQuery("UPDATE AppointmentDto SET activeFlag=?0, deletionFlag=?1 "
					+ "WHERE doctorName.doctorId=?2 AND activeFlag=?3");
			query.setParameter(0, 0);
			query.setParameter(1, 1);
			query.setParameter(2, doctorId);
			query.setParameter(3, 1);
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean UndoAllAppointmentsByDoctorId(int doctorId) {
		Session session = getSession();
		try {
			Query<?> query = session.createQuery("UPDATE AppointmentDto SET activeFlag=?0, deletionFlag=?1 "
					+ "WHERE doctorName.doctorId=?2 AND deletionFlag=?3");
			query.setParameter(0, 1);
			query.setParameter(1, 0);
			query.setParameter(2, doctorId);
			query.setParameter(3, 1);
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
