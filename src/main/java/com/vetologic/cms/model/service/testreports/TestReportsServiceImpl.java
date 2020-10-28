package com.vetologic.cms.model.service.testreports;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vetologic.cms.model.dao.testreports.TestReportsDao;

@Service
public class TestReportsServiceImpl implements TestReportsService {

	@Autowired
	private TestReportsDao testReportsDao;

	@Override
	public List<?> getAll(String beanClassName) {
		return testReportsDao.getAll(beanClassName);
	}

//	@Override
//	public List<?> getAllByPatientId(String beanClassName, int id) {
//		return testReportsDao.getAllByPatientId(beanClassName, id);
//	}

	@Transactional
	@Override
	public int save(Object object) {
		return testReportsDao.save(object);
	}

	@Override
	public Object getById(String beanClassName, int id) {
		return testReportsDao.getById(beanClassName, id);
	}

	@Transactional
	@Override
	public boolean update(Object object) {
		return testReportsDao.update(object);
	}

	@Override
	public Object checkSavedAndGetData(String beanClassName, int id) {
		return testReportsDao.checkSavedAndGetData(beanClassName, id);
	}

	@Override
	public List<?> getAllByDiagnosisId(String beanClassName, int diagnosisId) {
		return testReportsDao.getAllByDiagnosisId(beanClassName, diagnosisId);
	}

	@Override
	public Object getByDiagnosisId(String beanClassName, int id) {
		return testReportsDao.getByDiagnosisId(beanClassName, id);
	}

}
