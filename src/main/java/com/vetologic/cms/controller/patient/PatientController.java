package com.vetologic.cms.controller.patient;

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

import com.vetologic.cms.dto.patient.PatientDto;
import com.vetologic.cms.dto.response.CmsResponse;
import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.model.service.patient.PatientService;
import com.vetologic.cms.utils.AppUtil;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("admin/patient")
public class PatientController {

	private static Logger log = LoggerFactory.getLogger(PatientController.class);

	@Autowired
	private PatientService patientService;

	@PostMapping(path = "/addPatient", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse savePatientDetails(@RequestBody PatientDto patient, CmsResponse cmsResponse,
			Principal principal) {
		patient.setActiveFlag(1);
		UserDto userDto = patientService.getUserByName(principal.getName());
		patient.setCreatedBy(userDto);
		patient.setCreatedDate(AppUtil.currentDateWithTime());
		patient.setEmailId(patient.getEmailId().toLowerCase());
		int id = patientService.save(patient);
		if (id != 0) {
			patient.setPatientId(id);
			cmsResponse.setObject(patient);
			cmsResponse.setSuccess(true);
			cmsResponse.setMessage("Saved Sucessfully");
			log.info("Saved Sucessfully & Saved Patient Id is: " + id);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Saved UnSucessfully");
			log.info("Saved UnSucessfully");
		}
		return cmsResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllPatientDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllPatientDetails(CmsResponse cmsResponse) {
		List<PatientDto> allPatientDetails = (List<PatientDto>) patientService.getAll("PatientDto");
		if (allPatientDetails.size() > 0) {
			cmsResponse.setListObject(allPatientDetails);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Patient List is Empty");
			log.info("Patient List is Empty");
		}
		return cmsResponse;
	}

	@PutMapping(path = "/updatePatient", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse updatePatient(@RequestBody PatientDto patient, CmsResponse cmsResponse, Principal principal) {

		PatientDto patientDetails = (PatientDto) patientService.getById("PatientDto", patient.getPatientId());
		System.out.println("iddd" + patient.getPatientId());
		if (patientDetails != null) {
			patientDetails.setPatientName(patient.getPatientName());
			patientDetails.setPatientNumber(patient.getPatientNumber());
			patientDetails.setAddress(patient.getAddress());
			patientDetails.setAge(patient.getAge());
			patientDetails.setDob(patient.getDob());
			patientDetails.setEmailId(patient.getEmailId());
			patientDetails.setEmergencyContactName(patient.getEmergencyContactName());
			patientDetails.setEmergencyContactNumber(patient.getEmergencyContactNumber());
			patientDetails.setEmergencyContactRelation(patient.getEmergencyContactRelation());
			patientDetails.setGender(patient.getGender());
			patientDetails.setPhoneNumber(patient.getPhoneNumber());
			patientDetails.setReasonForVisit(patient.getReasonForVisit());
			patientDetails.setRegisteredDate(patient.getRegisteredDate());
			patientDetails.setWhatsAppNumber(patient.getWhatsAppNumber());

			UserDto userDto = patientService.getUserByName(principal.getName());
			patientDetails.setUpdatedBy(userDto);
			patientDetails.setUpdatedDate(AppUtil.currentDateWithTime());

			if (patientService.update(patientDetails)) {
				cmsResponse.setSuccess(true);
				cmsResponse.setObject(patientDetails);
				cmsResponse.setMessage("Updated Sucessfully");
				log.info("This Patient Id: " + patientDetails.getPatientId() + " Updated Sucessfully");
			} else {
				cmsResponse.setSuccess(false);
				cmsResponse.setMessage("Fails to Update");
				log.info("Fails to Update");
			}
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("This Patient Not Exist");
			log.info("This Patient Id: " + patient.getPatientId() + " Not Exist");
		}
		return cmsResponse;
	}

//	@PutMapping(path = "/deletePatientDetails", produces = MediaType.APPLICATION_JSON_VALUE)
//	public CmsResponse deletePatientDetails(@RequestParam("patientId") int patientId, CmsResponse cmsResponse) {
//		PatientDto patientDetails = (PatientDto) patientService.getById("PatientDto", patientId);
//		if (patientDetails != null) {
//			patientDetails.setUpdatedDate(AppUtil.currentDateWithTime());
//			patientDetails.setActiveFlag(0);
//			if (patientService.update(patientDetails)) {
//				cmsResponse.setSuccess(true);
//				cmsResponse.setMessage("Deleted Sucessfully");
//				log.info("This Patient Id: " + patientId + " Deleted Sucessfully");
//			} else {
////				patientDetails.UndoAllCandIntervAndJobsByCompanyId(patientDetails.getPatientId());
//				cmsResponse.setSuccess(false);
//				cmsResponse.setMessage("Deletion Failed");
//				log.info("Deletion Failed");
//			}
//
//		} else {
//			cmsResponse.setSuccess(false);
//			cmsResponse.setMessage("This Company Not Exist");
//			log.info("This Patient Id: " + patientId + " Not Exist");
//		}
//		return cmsResponse;
//	}

	@PutMapping(path = "/deletePatientDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse deletePatientDetails(@RequestParam("patientId") int patientId, CmsResponse cmsResponse,
			Principal principal) {
		PatientDto patientDetails = (PatientDto) patientService.getById("PatientDto", patientId);
		if (patientDetails != null) {
			if (patientService.deleteAllAppointmentsByPatientId(patientDetails.getPatientId())) {
				patientDetails.setUpdatedDate(AppUtil.currentDateWithTime());
				patientDetails.setActiveFlag(0);
				if (patientService.update(patientDetails)) {
					cmsResponse.setSuccess(true);
					cmsResponse.setMessage("Deleted Sucessfully");
					log.info("This Patient Id: " + patientId + " Deleted Sucessfully");
				} else {
					patientService.UndoAllAppointmentsByPatientId(patientDetails.getPatientId());
					cmsResponse.setSuccess(false);
					cmsResponse.setMessage("Deletion Failed");
					log.info("Deletion Failed");
				}
			} else {
				cmsResponse.setSuccess(false);
				cmsResponse.setMessage("Deletion Failed");
				log.info("Deletion Failed *");
			}
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("This Patient Not Exist");
			log.info("This Patient Id: " + patientId + " Not Exist");
		}
		return cmsResponse;
	}

	@GetMapping(path = "/getPatientDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getPatientDetailsById(@PathVariable int id, CmsResponse cmsResponse) {
		PatientDto patient = (PatientDto) patientService.getById("PatientDto", id);
		if (patient != null) {
			cmsResponse.setObject(patient);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Patient Not Exist");
			log.info("This Patient Id: " + id + " Not Exist");
		}
		return cmsResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllExceptThisId", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllExceptThisId(@RequestParam("id") int id, CmsResponse cmsResponse) {
		List<PatientDto> allPatientsExceptThisId = (List<PatientDto>) patientService
				.getAllExceptOnePatient("PatientDto", id);
		if (allPatientsExceptThisId.size() > 0) {
			cmsResponse.setListObject(allPatientsExceptThisId);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Patient List is Empty");
			log.info("Patient List is Empty");
		}
		return cmsResponse;
	}

}
