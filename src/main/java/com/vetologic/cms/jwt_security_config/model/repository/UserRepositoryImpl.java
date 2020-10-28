package com.vetologic.cms.jwt_security_config.model.repository;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vetologic.cms.dto.user.UserDto;

@Repository
public class UserRepositoryImpl implements UserRepository {
	@Autowired
	private EntityManager entityManager;

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Transactional
	@Override
	public UserDto findByUsername(String username) {
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

}
