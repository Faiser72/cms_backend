package com.vetologic.cms.controller.reference;

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

import com.vetologic.cms.dto.appointment.AppointmentDto;
import com.vetologic.cms.dto.reference.ReferenceDto;
import com.vetologic.cms.dto.response.CmsResponse;
import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.model.service.reference.ReferenceService;
import com.vetologic.cms.utils.AppUtil;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("reference")
public class ReferenceController {

	private static Logger log = LoggerFactory.getLogger(ReferenceController.class);

	@Autowired
	private ReferenceService referenceService;

	@PostMapping(path = "/addReference", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse saveReferenceDetails(@RequestBody ReferenceDto reference, CmsResponse cmsResponse,
			Principal principal) {
		reference.setActiveFlag(1);
		UserDto userDto = referenceService.getUserByName(principal.getName());
		reference.setCreatedBy(userDto);
		reference.setCreatedDate(AppUtil.currentDateWithTime());
		int id = referenceService.save(reference);
		if (id != 0) {
			reference.setReferenceId(id);
			cmsResponse.setObject(reference);
			cmsResponse.setSuccess(true);
			cmsResponse.setMessage("Saved Successfully");
			log.info("Saved Successfully & Saved Reference Id is: " + id);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Saved UnSuccessfully");
			log.info("Saved UnSuccessfully");
		}
		return cmsResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllReferenceDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllReferenceDetails(CmsResponse cmsResponse) {
		List<ReferenceDto> allReferenceDetails = (List<ReferenceDto>) referenceService.getAll("ReferenceDto");
		if (allReferenceDetails.size() > 0) {
			cmsResponse.setListObject(allReferenceDetails);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Reference List is Empty");
			log.info("Reference List is Empty");
		}
		return cmsResponse;
	}

	@GetMapping(path = "/getReferenceDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getReferenceDetailsById(@PathVariable int id, CmsResponse cmsResponse) {
		ReferenceDto reference = (ReferenceDto) referenceService.getById("ReferenceDto", id);
		if (reference != null) {
			cmsResponse.setObject(reference);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("reference Not Exist");
			log.info("This reference Id: " + id + " Not Exist");
		}
		return cmsResponse;
	}

	@GetMapping(path = "/getReferenceDetailsByPatientIdAndDate/{id}/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getReferenceDetailsByPatientIdAndDate(@PathVariable int id, @PathVariable String date,
			CmsResponse cmsResponse) {
		ReferenceDto reference = (ReferenceDto) referenceService.getByPatientIdAndDate("ReferenceDto", id, date);
		if (reference != null) {
			cmsResponse.setObject(reference);
			cmsResponse.setSuccess(true);
			cmsResponse.setMessage("You can take print of Referal Note");
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("No Referals for this patient at this appointment Date");
			log.info("This reference Id: " + id + " Not Exist");
		}
		return cmsResponse;
	}

//	@GetMapping(path = "/checkSavedAndGetData", produces = MediaType.APPLICATION_JSON_VALUE)
//	public CmsResponse checkSavedAndGetData(@RequestParam("appointmentId") int appointmentId, CmsResponse cmsResponse,
//			ReferenceDto reference, Principal principal) {
//
//		ReferenceDto referenceDetails = (ReferenceDto) referenceService.checkSavedAndGetData("ReferenceDto",
//				appointmentId);
//		if (referenceDetails != null) {
//			cmsResponse.setObject(referenceDetails);
//			cmsResponse.setSuccess(true);
//		} else {
//			AppointmentDto appointmentDetails = (AppointmentDto) referenceService.getById("AppointmentDto",
//					appointmentId);
//			reference.setActiveFlag(1);
//			System.err.println(appointmentDetails);
//			reference.setAppointmentId(appointmentDetails);
//			reference.setDoctorId(appointmentDetails.getDoctorName());
//			reference.setPatientId(appointmentDetails.getPatientNumber());
//
//			UserDto userDto = referenceService.getUserByName(principal.getName());
//			reference.setCreatedBy(userDto);
//			reference.setCreatedDate(AppUtil.currentDateWithTime());
//			int id = referenceService.save(reference);
//			if (id != 0) {
//				reference.setReferenceId(id);
//				cmsResponse.setObject(reference);
//				cmsResponse.setSuccess(true);
//				log.info("Saved Sucessfully & Saved Reference Id is: " + id);
//			} else {
//				cmsResponse.setSuccess(false);
//				log.info("Saved UnSucessfully");
//			}
//		}
//		return cmsResponse;
//	}

	@GetMapping(path = "/checkSavedAndGetData", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse checkSavedAndGetData(@RequestParam("appointmentId") int appointmentId, CmsResponse cmsResponse,
			ReferenceDto reference, Principal principal) {
		System.err.println(appointmentId);

		ReferenceDto referenceDetails = (ReferenceDto) referenceService.checkSavedAndGetData("ReferenceDto",
				appointmentId);
		if (referenceDetails != null) {
			cmsResponse.setObject(referenceDetails);
			cmsResponse.setSuccess(true);
		} else {
			AppointmentDto appointmentDetails = (AppointmentDto) referenceService.getById("AppointmentDto",
					appointmentId);
			reference.setActiveFlag(1);
			System.err.println(appointmentDetails);
			reference.setAppointment(appointmentDetails);
			reference.setDoctorId(appointmentDetails.getDoctorName());
			reference.setPatientId(appointmentDetails.getPatientNumber());
			UserDto userDto = referenceService.getUserByName(principal.getName());
			reference.setCreatedBy(userDto);
			reference.setCreatedDate(AppUtil.currentDateWithTime());
			int id = referenceService.save(reference);
			if (id != 0) {
				reference.setReferenceId(id);
				cmsResponse.setObject(reference);
				cmsResponse.setSuccess(true);
				log.info("Saved Successfully & Saved reference Id is: " + id);
			} else {
				cmsResponse.setSuccess(false);
				log.info("Saved UnSuccessfully");
			}
		}
		return cmsResponse;
	}

	@PutMapping(path = "/updateReference", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse updateReference(@RequestBody ReferenceDto reference, CmsResponse cmsResponse,
			Principal principal) {

		ReferenceDto referenceDetails = (ReferenceDto) referenceService.getById("ReferenceDto",
				reference.getReferenceId());
		if (referenceDetails != null) {
			referenceDetails.setAge(reference.getAge());
			referenceDetails.setAppointment(reference.getAppointment());
			referenceDetails.setDate(reference.getDate());
			referenceDetails.setDoctorId(reference.getDoctorId());
			referenceDetails.setPatientId(reference.getPatientId());
			referenceDetails.setReferedBy(reference.getReferedBy());
			referenceDetails.setRemarks(reference.getRemarks());

			UserDto userDto = referenceService.getUserByName(principal.getName());
			referenceDetails.setUpdatedBy(userDto);
			referenceDetails.setUpdatedDate(AppUtil.currentDateWithTime());

			if (referenceService.update(referenceDetails)) {
				cmsResponse.setSuccess(true);
				cmsResponse.setObject(referenceDetails);
				cmsResponse.setMessage("Updated Successfully");
				log.info("This reference Id: " + referenceDetails.getReferenceId() + " Updated Successfully");
			} else {
				cmsResponse.setSuccess(false);
				cmsResponse.setMessage("Fails to Update");
				log.info("Fails to Update");
			}
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("This Reference Not Exist");
			log.info("This Reference Id: " + reference.getReferenceId() + " Not Exist");
		}
		return cmsResponse;
	}
}
