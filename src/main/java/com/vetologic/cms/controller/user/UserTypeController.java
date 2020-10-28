package com.vetologic.cms.controller.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vetologic.cms.dto.response.CmsResponse;
import com.vetologic.cms.dto.user.UserTypeDto;
import com.vetologic.cms.model.service.user.UserTypeService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("admin/userType")
public class UserTypeController {

	private static Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserTypeService userTypeService;

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllUserTypeDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllUserTypeDetails(CmsResponse cmsResponse) {
		List<UserTypeDto> usersTypeDetailsList = (List<UserTypeDto>) userTypeService.getAll("UserTypeDto");
		if (usersTypeDetailsList.size() > 0) {
			cmsResponse.setListObject(usersTypeDetailsList);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("User List is Empty");
			log.info("User List is Empty");
		}
		return cmsResponse;
	}
}
