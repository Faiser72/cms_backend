package com.vetologic.cms.model.service.frontdesk;

import java.util.List;

import com.vetologic.cms.dto.frontdesk.FrontDeskDto;
import com.vetologic.cms.dto.user.UserDto;

public interface FrontDeskService {

	List<?> getAll(String beanClassName);

	List<?> getAllDeleted(String beanClassName);

	int save(Object object);

	UserDto getUserByName(String username);

	Object getById(String beanClassName, int id);

	boolean update(Object object);

	boolean deleteFrontDesk(FrontDeskDto frontdesk);

	Object getByIdForUndo(String beanClassName, int id);

//	Object getByUserId(String beanClassName, int id);

}
