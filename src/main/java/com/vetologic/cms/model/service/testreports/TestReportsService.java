package com.vetologic.cms.model.service.testreports;

import java.util.List;

public interface TestReportsService {

	List<?> getAll(String beanClassName);

//	List<?> getAllByPatientId(String beanClassName, int id);

	int save(Object object);

	Object getById(String beanClassName, int id);

	Object getByDiagnosisId(String beanClassName, int id);

	boolean update(Object object);

	Object checkSavedAndGetData(String beanClassName, int id);

	List<?> getAllByDiagnosisId(String beanClassName, int diagnosisId);

}
