package com.vetologic.cms.controller.frontdesk;

import java.security.Principal;
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

import com.vetologic.cms.controller.doctor.DoctorController;
import com.vetologic.cms.dto.frontdesk.FrontDeskDto;
import com.vetologic.cms.dto.response.CmsResponse;
import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.dto.user.UserTypeDto;
import com.vetologic.cms.model.service.frontdesk.FrontDeskService;
import com.vetologic.cms.model.service.user.UserService;
import com.vetologic.cms.utils.AppUtil;
import com.vetologic.cms.utils.MailUtil;
import com.vetologic.cms.utils.PasswordUtil;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("admin/frontdesk")
public class FrontDeskController {

	private static Logger log = LoggerFactory.getLogger(DoctorController.class);

	@Autowired
	private FrontDeskService frontdeskService;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	MailUtil mailUtil;

	@PostMapping(path = "/addFrontdesk", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse saveDoctorDetails(@RequestBody FrontDeskDto frontdesk, UserDto user, CmsResponse cmsResponse,
			Principal principal) {
		// for saving doctor details to user.
		user.setActiveFlag(1);
		user.setCreatedDate(AppUtil.currentDateWithTime());
		String[] userName = frontdesk.getEmailId().split("@");
		String generatePassword = new PasswordUtil().generatePassword();
		String hashedPassword = passwordEncoder.encode(generatePassword);
		user.setPassword(hashedPassword);
		user.setUsername(userName[0]);
		user.setEmailId(frontdesk.getEmailId().toLowerCase());
		user.setDisplayName(frontdesk.getFrontDeskName());
		user.setMobileNo(frontdesk.getMobileNo());
		UserTypeDto userType = userService.getDesignationByRoleName("ROLE_ADMIN");
		user.setUserType(userType);
		int userId = userService.save(user);
		if (userId != 0) {
			user.setUserId(userId);
			int frontdeskId = 0;
			try {
				// for saving doctor details.
				frontdesk.setActiveFlag(1);
				UserDto userDto = frontdeskService.getUserByName(principal.getName());
				frontdesk.setCreatedBy(userDto);
				frontdesk.setUser(user);
				frontdesk.setCreatedDate(AppUtil.currentDateWithTime());
				frontdesk.setEmailId(frontdesk.getEmailId().toLowerCase());
				frontdeskId = frontdeskService.save(frontdesk);
				if (frontdeskId != 0) {
					// UserDto userDetails = (UserDto) userService.getById("UserDto", "userId",
					// userId);
					frontdesk.setFrontdeskId(frontdeskId);
					// userDetails.setDoctor(doctor);
					// userService.update(userDetails);
					user.setPassword(generatePassword);
					mailUtil.sendCreateUserEmail(user);
					cmsResponse.setObject(frontdesk);
					cmsResponse.setSuccess(true);
				} else {
					if (userService.deleteUser(user)) {
						cmsResponse.setSuccess(false);
						cmsResponse.setMessage("Saved UnSuccessfully!");
						log.info("Saved UnSuccessfully for User Id is: " + userId
								+ " Due to failure to save doctor details.");
					}
				}
			} catch (Exception e) {

				if (frontdeskService.deleteFrontDesk(frontdesk) && userService.deleteUser(user)) {
					cmsResponse.setSuccess(false);
					cmsResponse.setMessage("Saved UnSuccessfully! Due to failure to send mail.");
					log.info("Saved UnSuccessfully for User Id is: " + userId + " Due to failure to send mail.");
					log.info("Saved UnSuccessfully for FrontDesk Id is: " + frontdeskId
							+ " Due to failure to send mail.");
				} else {
					cmsResponse.setSuccess(true);
					cmsResponse.setMessage("Saved Successfully! But Fails to send mail.");
					log.info("Saved Successfully & Saved User Id is: " + userId + " But Fails to send mail.");
					log.info("Saved Successfully & Saved FrontDesk Id is: " + userId + " But Fails to send mail.");
				}
				log.error(e.getMessage());
				return cmsResponse;
			}
			cmsResponse.setSuccess(true);
			cmsResponse.setMessage("Saved Successfully");
			log.info("Saved Successfully & Saved User Id is: " + userId + " Sent Mail also Successfully.");

		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Saved UnSuccessfully");
			log.info("Saved UnSuccessfully.");
		}

		return cmsResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllFrontDeskDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllFrontDeskDetails(CmsResponse cmsResponse) {
		List<FrontDeskDto> allFrontDeskDetails = (List<FrontDeskDto>) frontdeskService.getAll("FrontDeskDto");
		if (allFrontDeskDetails.size() > 0) {
			cmsResponse.setListObject(allFrontDeskDetails);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("FrontDesk List is Empty");
			log.info("FrontDesk List is Empty");
		}
		return cmsResponse;
	}

	@PutMapping(path = "/updateFrontDesk", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse updateFrontDesk(@RequestBody FrontDeskDto frontdesk, CmsResponse cmsResponse,
			Principal principal) {

		FrontDeskDto frontdeskDetails = (FrontDeskDto) frontdeskService.getById("FrontDeskDto",
				frontdesk.getFrontdeskId());
		if (frontdeskDetails != null) {
			frontdeskDetails.setAge(frontdesk.getAge());
			frontdeskDetails.setDob(frontdesk.getDob());
			frontdeskDetails.setEmailId(frontdesk.getEmailId());
			frontdeskDetails.setFrontDeskName(frontdesk.getFrontDeskName());
			frontdeskDetails.setGender(frontdesk.getGender());
			frontdeskDetails.setMobileNo(frontdesk.getMobileNo());

			UserDto userDto = frontdeskService.getUserByName(principal.getName());
			frontdeskDetails.setUpdatedBy(userDto);
			frontdeskDetails.setUpdatedDate(AppUtil.currentDateWithTime());

			if (frontdeskService.update(frontdeskDetails)) {
				UserDto userDetails = (UserDto) userService.getById("UserDto", "userId",
						frontdeskDetails.getUser().getUserId());
				userDetails.setUpdatedDate(AppUtil.currentDateWithTime());
				userDetails.setDisplayName(frontdesk.getFrontDeskName());
				userDetails.setMobileNo(frontdesk.getMobileNo());
				userService.update(userDetails);

				cmsResponse.setSuccess(true);
				cmsResponse.setObject(frontdeskDetails);
				cmsResponse.setMessage("Updated Successfully");
				log.info("This FrontDesk Id: " + frontdeskDetails.getFrontdeskId() + " Updated Successfully");
			} else {
				cmsResponse.setSuccess(false);
				cmsResponse.setMessage("Fails to Update");
				log.info("Fails to Update");
			}
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("This FrontDesk Not Exist");
			log.info("This FrontDesk Id: " + frontdesk.getFrontdeskId() + " Not Exist");
		}
		return cmsResponse;
	}

	@PutMapping(path = "/deleteFrontDeskDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse deleteFrontDeskDetails(@RequestParam("frontdeskId") int frontdeskId, CmsResponse cmsResponse) {
		FrontDeskDto frontdeskDetails = (FrontDeskDto) frontdeskService.getById("FrontDeskDto", frontdeskId);
		if (frontdeskDetails != null) {
			int userId = frontdeskDetails.getUser().getUserId();
			UserDto userDetails = (UserDto) userService.getById("UserDto", "userId", userId);
			userDetails.setActiveFlag(0);
			userService.update(userDetails);
			frontdeskDetails.setUpdatedDate(AppUtil.currentDateWithTime());
			frontdeskDetails.setActiveFlag(0);
			if (frontdeskService.update(frontdeskDetails)) {
				cmsResponse.setSuccess(true);
				cmsResponse.setMessage("Deleted Successfully");
				log.info("This FrontDesk Id: " + frontdeskId + " Deleted Successfully");
			} else {
//				doctorDetails.UndoAllCandIntervAndJobsByCompanyId(doctorDetails.getDoctorId());
				cmsResponse.setSuccess(false);
				cmsResponse.setMessage("Deletion Failed");
				log.info("Deletion Failed");
			}

		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("This FrontDesk Not Exist");
			log.info("This FrontDesk Id: " + frontdeskId + " Not Exist");
		}
		return cmsResponse;
	}

	@GetMapping(path = "/getFrontDeskDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getFrontDeskDetailsById(@PathVariable int id, CmsResponse cmsResponse) {
		FrontDeskDto frontdesk = (FrontDeskDto) frontdeskService.getById("FrontDeskDto", id);
		if (frontdesk != null) {
			cmsResponse.setObject(frontdesk);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("frontdesk Not Exist");
			log.info("This frontdesk Id: " + id + " Not Exist");
		}
		return cmsResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllDeletedFrontDeskDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllDeletedFrontDeskDetails(CmsResponse cmsResponse) {
		List<FrontDeskDto> allFrontDeskDetails = (List<FrontDeskDto>) frontdeskService.getAllDeleted("FrontDeskDto");
		if (allFrontDeskDetails.size() > 0) {
			cmsResponse.setListObject(allFrontDeskDetails);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("FrontDesk List is Empty");
			log.info("FrontDesk List is Empty");
		}
		return cmsResponse;
	}

	@PutMapping(path = "/undoFrontDeskDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse undoFrontDeskDetails(@RequestParam("frontdeskId") int frontdeskId, CmsResponse cmsResponse) {
		FrontDeskDto frontdeskDetails = (FrontDeskDto) frontdeskService.getByIdForUndo("FrontDeskDto", frontdeskId);
		if (frontdeskDetails != null) {
			int userId = frontdeskDetails.getUser().getUserId();
			UserDto userDetails = (UserDto) userService.getByIdForUndo("UserDto", "userId", userId);
			userDetails.setActiveFlag(1);
			userService.update(userDetails);
			frontdeskDetails.setUpdatedDate(AppUtil.currentDateWithTime());
			frontdeskDetails.setActiveFlag(1);
			if (frontdeskService.update(frontdeskDetails)) {
				cmsResponse.setSuccess(true);
				cmsResponse.setMessage("Undo Successfully");
				log.info("This FrontDesk Id: " + frontdeskId + " Undo Successfully");
			} else {
//				doctorDetails.UndoAllCandIntervAndJobsByCompanyId(doctorDetails.getDoctorId());
				cmsResponse.setSuccess(false);
				cmsResponse.setMessage("Undo Failed");
				log.info("Undo Failed");
			}

		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("This FrontDesk Not Exist");
			log.info("This FrontDesk Id: " + frontdeskId + " Not Exist");
		}
		return cmsResponse;
	}

}
