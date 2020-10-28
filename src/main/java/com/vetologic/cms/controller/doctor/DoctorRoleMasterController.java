package com.vetologic.cms.controller.doctor;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vetologic.cms.dto.doctor.DoctorRoleMasterDto;
import com.vetologic.cms.dto.response.CmsResponse;
import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.model.service.doctor.DoctorRoleMasterService;
import com.vetologic.cms.utils.AppUtil;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/admin/doctorRoleMaster")
public class DoctorRoleMasterController {
	private static Logger log = LoggerFactory.getLogger(DoctorRoleMasterController.class);

	@Autowired
	private DoctorRoleMasterService doctorRoleMasterService;

	@PostMapping(path = "/saveDoctorRoleDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse saveDoctorRoleDetails(@RequestBody DoctorRoleMasterDto doctorRole, CmsResponse cmsResponse,
			Principal principal) {
		doctorRole.setActiveFlag(1);
		UserDto userDto = doctorRoleMasterService.getUserByName(principal.getName());
		doctorRole.setCreatedBy(userDto);
		doctorRole.setCreatedDate(AppUtil.currentDateWithTime());
		int id = doctorRoleMasterService.save(doctorRole);
		if (id != 0) {
			cmsResponse.setSuccess(true);
			cmsResponse.setMessage("Saved Sucessfully");
			log.info("Saved Sucessfully & Saved JobTitle Id is: " + id);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Saved UnSucessfully");
			log.info("Saved UnSucessfully");
		}
		return cmsResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/doctorRole", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse listAllDoctorRole(CmsResponse cmsResponse) {
		List<DoctorRoleMasterDto> doctorRoleList = (List<DoctorRoleMasterDto>) doctorRoleMasterService
				.getAll("DoctorRoleMasterDto");
		if (doctorRoleList.size() > 0) {
			System.out.println("doctorRoleList" + doctorRoleList);
			cmsResponse.setListObject(doctorRoleList);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("DoctorRole List is Empty");
			log.info("DoctorRole List is Empty");
		}
		return cmsResponse;
	}

	@PutMapping(path = "/deleteDoctorRoleDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse deleteDoctorRoleDetails(@RequestParam("doctorRoleId") int doctorRoleId,
			CmsResponse cmsResponse) {
		DoctorRoleMasterDto doctorRole = (DoctorRoleMasterDto) doctorRoleMasterService.getById("DoctorRoleMasterDto",
				doctorRoleId);
		if (doctorRole != null) {
			doctorRole.setActiveFlag(0);
			if (doctorRoleMasterService.update(doctorRole)) {
				cmsResponse.setSuccess(true);
				cmsResponse.setMessage("Deleted Successfully");
				log.info("This DoctorRole Id: " + doctorRoleId + " Deleted Successfully");
			} else {
				cmsResponse.setSuccess(false);
				cmsResponse.setMessage("Deletion Failed");
				log.info("Deletion Failed");
			}
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("This DoctorRole Not Exist");
			log.info("This DoctorRole Id: " + doctorRoleId + " is Not Exist");
		}
		return cmsResponse;
	}

	@PutMapping(path = "/updateDoctorRoleDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse updateDoctorRoleDetails(@RequestBody DoctorRoleMasterDto DoctorRole, CmsResponse cmsResponse,
			Principal principal) {
		DoctorRoleMasterDto doctorRoleDetails = (DoctorRoleMasterDto) doctorRoleMasterService
				.getById("DoctorRoleMasterDto", DoctorRole.getDoctorRoleId());
		if (doctorRoleDetails != null) {
			UserDto user = doctorRoleMasterService.getUserByName(principal.getName());
			doctorRoleDetails.setDoctorRoleName(DoctorRole.getDoctorRoleName());
			doctorRoleDetails.setUpdatedBy(user);
			doctorRoleDetails.setUpdatedDate(AppUtil.currentDateWithTime());
			if (doctorRoleMasterService.update(doctorRoleDetails)) {
				cmsResponse.setSuccess(true);
				cmsResponse.setMessage("Updated Successfully");
				log.info("This DoctorRole Id: " + DoctorRole.getDoctorRoleId() + " Updated Successfully");
			} else {
				cmsResponse.setSuccess(false);
				cmsResponse.setMessage("Fails to Update");
				log.info("Fails to Update");
			}
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("This DoctorRole Not Exist");
			log.info("This DoctorRole Id: " + DoctorRole.getDoctorRoleId() + " is Not Exist");
		}

		return cmsResponse;
	}

	// get list of data except this id for validate unique in (edit)
	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getDoctorRoleListExceptOne/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getDoctorRoleListExceptOne(@PathVariable int id, CmsResponse cmsResponse) {
		List<DoctorRoleMasterDto> doctorRoleListExceptOne = (List<DoctorRoleMasterDto>) doctorRoleMasterService
				.getAllExceptOne("DoctorRoleMasterDto", id);
		if (doctorRoleListExceptOne.size() > 0) {
			cmsResponse.setListObject(doctorRoleListExceptOne);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("DoctorRole List is Empty");
			log.info("DoctorRole List is Empty");
		}
		return cmsResponse;
	}

}
