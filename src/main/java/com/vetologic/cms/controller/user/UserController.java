package com.vetologic.cms.controller.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vetologic.cms.dto.response.CmsResponse;
import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.dto.user.UserTypeDto;
import com.vetologic.cms.model.service.user.UserService;
import com.vetologic.cms.utils.AppUtil;
import com.vetologic.cms.utils.MailUtil;
import com.vetologic.cms.utils.PasswordUtil;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("admin/user")
public class UserController {

	private static Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	MailUtil mailUtil;

	@PostMapping(path = "/saveUserDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse saveUserDetails(@RequestBody UserDto user, CmsResponse cmsResponse) {
		user.setActiveFlag(1);
//		UserTypeDto userTypeUser = userService.getDesignationByRoleName("ROLE_USER");
//		user.setUserType(userTypeUser);;
		user.setCreatedDate(AppUtil.currentDateWithTime());
		String generatePassword = new PasswordUtil().generatePassword();
		String hashedPassword = passwordEncoder.encode(generatePassword);
		user.setPassword(hashedPassword);
		user.setEmailId(user.getEmailId().toLowerCase());
		int id = userService.save(user);
		if (id != 0) {
			user.setPassword(generatePassword);
			user.setUserId(id);

			try {
//				UserTypeDto userTypeAdmin = userService.getDesignationByRoleName("ROLE_ADMIN");
//				UserDto usersDetails = userService.getUserByDesignation(userTypeAdmin).get(0);
				mailUtil.sendCreateUserEmail(user);
			} catch (Exception e) {
				// user.setUserId(id);
				if (userService.deleteUser(user)) {
					cmsResponse.setSuccess(false);
					cmsResponse.setMessage("Saved UnSuccessfully! Due to failure to send mail.");
					log.info("Saved UnSuccessfully for User Id is: " + id + " Due to failure to send mail.");
				} else {
					cmsResponse.setSuccess(true);
					cmsResponse.setMessage("Saved Successfully! But Fails to send mail.");
					log.info("Saved Successfully & Saved User Id is: " + id + " But Fails to send mail.");
				}
				log.error(e.getMessage());
				return cmsResponse;
			}
			cmsResponse.setSuccess(true);
			cmsResponse.setMessage("Saved Successfully");
			log.info("Saved Successfully & Saved User Id is: " + id + " Sent Mail also Successfully.");
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Saved UnSucessfully");
			log.info("Saved UnSucessfully.");
		}
		return cmsResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllUserDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllUserDetails(CmsResponse cmsResponse) {
		List<UserDto> usersDetailsList = (List<UserDto>) userService.getAll("UserDto");
		if (usersDetailsList.size() > 0) {
			cmsResponse.setListObject(usersDetailsList);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("User List is Empty");
			log.info("User List is Empty");
		}
		return cmsResponse;
	}

	@GetMapping(path = "/getUserDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getUserDetailsById(@PathVariable int id, CmsResponse cmsResponse) {
		UserDto user = (UserDto) userService.getById("UserDto", "userId", id);
		if (user != null) {
			cmsResponse.setObject(user);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("This User Not Exist");
			log.info("This User Id: " + id + " Not Exist");
		}
		return cmsResponse;
	}

	@PutMapping(path = "/updateUserDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse updateUserDetails(@RequestBody UserDto user, UserTypeDto userType, CmsResponse cmsResponse) {
		UserDto userDetails = (UserDto) userService.getById("UserDto", "userId", user.getUserId());
		if (userDetails != null) {
			userDetails.setDisplayName(user.getDisplayName());
			userDetails.setEmailId(user.getEmailId().toLowerCase());
			userDetails.setMobileNo(user.getMobileNo());
			userDetails.setUpdatedDate(AppUtil.currentDateWithTime());
			userDetails.setUserType(user.getUserType());
			if (userService.update(userDetails)) {
				cmsResponse.setSuccess(true);
				cmsResponse.setMessage("Updated Successfully");
				log.info("This User Id: " + userDetails.getUserId() + " Updated Successfully");
			} else {
				cmsResponse.setSuccess(false);
				cmsResponse.setMessage("Failed to Update");
				log.info("Failed to Update");
			}
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("This User Not Exist");
			log.info("This User Id: " + user.getUserId() + " Not Exist");
		}
		return cmsResponse;
	}

	@PutMapping(path = "/deleteUserDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse deleteUserDetails(@RequestParam("userId") int userId, CmsResponse cmsResponse) {
		UserDto userDetails = (UserDto) userService.getById("UserDto", "userId", userId);
		if (userDetails != null) {
			userDetails.setUpdatedDate(AppUtil.currentDateWithTime());
			userDetails.setActiveFlag(0);
			if (userService.update(userDetails)) {
				cmsResponse.setSuccess(true);
				cmsResponse.setMessage("Deleted Successfully");
				log.info("This User Id: " + userId + "Deleted Successfully");
			} else {
				cmsResponse.setSuccess(false);
				cmsResponse.setMessage("Deletion Failed");
				log.info("Deletion Failed");
			}
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("This User Not Exist");
			log.info("This User Id: " + userId + " Not Exist");
		}
		return cmsResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllUserDetailsExceptOneUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllUserDetailsExceptOneUser(@RequestParam("userId") int userId, CmsResponse cmsResponse) {
		List<UserDto> usersDetailsListExceptOneUser = (List<UserDto>) userService.getAllExceptOneUser("UserDto",
				userId);
		if (usersDetailsListExceptOneUser.size() > 0) {
			cmsResponse.setListObject(usersDetailsListExceptOneUser);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("User List is Empty");
			log.info("User List is Empty");
		}
		return cmsResponse;
	}

	// Getting user with active flag both 0 & 1 for save. To validate unique
	// username.
	@GetMapping(path = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllUsers(CmsResponse cmsResponse) {
		List<UserDto> allUsersList = userService.getAllUsers();
		if (allUsersList.size() > 0) {
			cmsResponse.setListObject(allUsersList);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("User List is Empty");
			log.info("User List is Empty");
		}
		return cmsResponse;
	}

//	Getting user with active flag both 0 & 1 for edit. To validate unique username.
	@GetMapping(path = "/getAllUsersExceptOneUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllUsersExceptOneUser(@RequestParam("userId") int userId, CmsResponse cmsResponse) {
		List<UserDto> allUsersListExceptOneUser = userService.getAllUsersExceptOneUser(userId);
		if (allUsersListExceptOneUser.size() > 0) {
			cmsResponse.setListObject(allUsersListExceptOneUser);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("User List is Empty");
			log.info("User List is Empty");
		}
		return cmsResponse;
	}

}
