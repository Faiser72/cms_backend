package com.vetologic.cms.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vetologic.cms.dto.response.CmsResponse;
import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.dto.user.UserTypeDto;
import com.vetologic.cms.model.service.user.UserService;
import com.vetologic.cms.utils.MailUtil;
import com.vetologic.cms.utils.PasswordUtil;

@RestController
@CrossOrigin(origins = "*")
public class ForgetPasswordController {
	private static Logger log = LoggerFactory.getLogger(ForgetPasswordController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	MailUtil mailUtil;

	@PutMapping(path = "/forgotPassword", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse forgotPassword(CmsResponse cmsResponse,
			@RequestParam("forgotPasswordEmailId") String forgotPasswordEmailId) {
		try {
			UserDto user = userService.getUserDetailsByEmailId(forgotPasswordEmailId.toLowerCase());
			if (user != null) {
				String generatedPassword = new PasswordUtil().generatePassword();
				String hashedPassword = passwordEncoder.encode(generatedPassword);
				user.setPassword(hashedPassword);
				if (userService.update(user)) {
					user.setPassword(generatedPassword);
					try {
						UserTypeDto designationAdmin = userService.getDesignationByRoleName("ROLE_ADMIN");
						UserDto usersDetails = userService.getUserByDesignation(designationAdmin).get(0);
						mailUtil.sendForgotPasswordEmail(user, usersDetails.getEmailId());
					} catch (Exception e) {
						cmsResponse.setSuccess(false);
						cmsResponse.setMessage("Password Reset Successful! But Fails to send mail.");
						log.info("Password Reset Successful! But Fails to send mail.");
						log.error(e.getMessage());
						return cmsResponse;
					}
					cmsResponse.setSuccess(true);
					log.info("Password Reset Successful and Mail Sent to your EmailId");
					cmsResponse.setMessage("Password Reset Successful and Mail Sent to your EmailId");
				} else {
					cmsResponse.setSuccess(false);
					log.info("Fails to Reset Password");
					cmsResponse.setMessage("Fails to Reset Password");
				}
			} else {
				cmsResponse.setSuccess(false);
				log.info("This EmailId Not Exist");
				cmsResponse.setMessage("This EmailId Not Exist");
			}
		} catch (Exception e) {
			cmsResponse.setSuccess(false);
			log.info("Something Went Wrong");
			cmsResponse.setMessage("Something Went Wrong");
			log.error(e.getMessage());
		}
		return cmsResponse;
	}
}
