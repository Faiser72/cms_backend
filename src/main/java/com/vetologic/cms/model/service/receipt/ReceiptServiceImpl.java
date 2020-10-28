package com.vetologic.cms.model.service.receipt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.model.dao.receipt.ReceiptDao;

@Service
public class ReceiptServiceImpl implements ReceiptService {

	@Autowired
	private ReceiptDao receiptDao;

	@Override
	public List<?> getAll(String beanClassName) {
		return receiptDao.getAll(beanClassName);
	}

	@Transactional
	@Override
	public int save(Object object) {
		return receiptDao.save(object);
	}

	@Override
	public UserDto getUserByName(String username) {
		return receiptDao.getUserByName(username);
	}

	@Override
	public Object getById(String beanClassName, int id) {
		return receiptDao.getById(beanClassName, id);
	}

	@Override
	public Object getByAppointmentId(String beanClassName, int appointmentId) {
		return receiptDao.getByAppointmentId(beanClassName, appointmentId);
	}

	@Transactional
	@Override
	public boolean update(Object object) {
		return receiptDao.update(object);
	}

	@Override
	public List<?> getAllBtwnTwoDates(String beanClassName, String fromDate, String toDate) {
		return receiptDao.getAllBtwnTwoDates(beanClassName, fromDate, toDate);
	}

}
