package com.vetologic.cms.controller.prescription;

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
import com.vetologic.cms.dto.prescription.PrescriptionDto;
import com.vetologic.cms.dto.response.CmsResponse;
import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.model.service.prescription.PrescriptionService;
import com.vetologic.cms.utils.AppUtil;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("prescription")
public class PrescriptionController {

	private static Logger log = LoggerFactory.getLogger(PrescriptionController.class);

	@Autowired
	private PrescriptionService prescriptionService;

	@PostMapping(path = "/addPrescription", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse savePrescriptionDetails(@RequestBody PrescriptionDto prescription, CmsResponse cmsResponse,
			Principal principal) {
		prescription.setActiveFlag(1);
		UserDto userDto = prescriptionService.getUserByName(principal.getName());
		prescription.setCreatedBy(userDto);
		prescription.setCreatedDate(AppUtil.currentDateWithTime());
		int id = prescriptionService.save(prescription);
		if (id != 0) {
			prescription.setPrescriptionId(id);
			cmsResponse.setObject(prescription);
			cmsResponse.setSuccess(true);
			cmsResponse.setMessage("Saved Sucessfully");
			log.info("Saved Sucessfully & Saved Prescription Id is: " + id);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Saved UnSucessfully");
			log.info("Saved UnSucessfully");
		}
		return cmsResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllPrescriptionDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllPrescriptionDetails(CmsResponse cmsResponse) {
		List<PrescriptionDto> allPrescriptionDetails = (List<PrescriptionDto>) prescriptionService
				.getAll("PrescriptionDto");
		if (allPrescriptionDetails.size() > 0) {
			cmsResponse.setListObject(allPrescriptionDetails);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Prescription List is Empty");
			log.info("Prescription List is Empty");
		}
		return cmsResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllPrescriptionDetailsByPatientId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllPrescriptionDetailsBtPatientId(@PathVariable int id, CmsResponse cmsResponse) {
		List<PrescriptionDto> allPrescriptionDetails = (List<PrescriptionDto>) prescriptionService
				.getAllByPatientId("PrescriptionDto", id);
		System.out.println(id);
		if (allPrescriptionDetails.size() > 0) {
			cmsResponse.setListObject(allPrescriptionDetails);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Prescription List is Empty for " + id);
			log.info("Prescription List is Empty for " + id);
		}
		return cmsResponse;
	}

	@PutMapping(path = "/updatePrescription", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse updatePrescription(@RequestBody PrescriptionDto prescription, CmsResponse cmsResponse,
			Principal principal) {

		PrescriptionDto prescriptionDetails = (PrescriptionDto) prescriptionService.getById("PrescriptionDto",
				prescription.getPrescriptionId());
		if (prescriptionDetails != null) {
			prescriptionDetails.setAfternoonDosage(prescription.getAfternoonDosage());
			prescriptionDetails.setAppointment(prescription.getAppointment());
			prescriptionDetails.setDoctorName(prescription.getDoctorName());
			prescriptionDetails.setDrugName(prescription.getDrugName());
			prescriptionDetails.setDuration(prescription.getDuration());
			prescriptionDetails.setMorningDosage(prescription.getMorningDosage());
			prescriptionDetails.setNightDosage(prescription.getNightDosage());
			prescriptionDetails.setPatient(prescription.getPatient());
			prescriptionDetails.setRemarks(prescription.getRemarks());
			prescriptionDetails.setStrength(prescription.getStrength());
			prescriptionDetails.setDate(prescription.getDate());

			UserDto userDto = prescriptionService.getUserByName(principal.getName());
			prescriptionDetails.setUpdatedBy(userDto);
			prescriptionDetails.setUpdatedDate(AppUtil.currentDateWithTime());

			if (prescriptionService.update(prescriptionDetails)) {
				cmsResponse.setSuccess(true);
				cmsResponse.setObject(prescriptionDetails);
				cmsResponse.setMessage("Updated Sucessfully");
				log.info("This Prescription Id: " + prescriptionDetails.getPrescriptionId() + " Updated Sucessfully");
			} else {
				cmsResponse.setSuccess(false);
				cmsResponse.setMessage("Fails to Update");
				log.info("Fails to Update");
			}
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("This Appointment Not Exist");
			log.info("This Prescription Id: " + prescription.getPrescriptionId() + " Not Exist");
		}
		return cmsResponse;
	}

	@PutMapping(path = "/deletePrescriptionDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse deletePrescriptionDetails(@RequestParam("prescriptionId") int prescriptionId,
			CmsResponse cmsResponse) {
		PrescriptionDto prescriptionDetails = (PrescriptionDto) prescriptionService.getById("PrescriptionDto",
				prescriptionId);
		if (prescriptionDetails != null) {
			prescriptionDetails.setUpdatedDate(AppUtil.currentDateWithTime());
			prescriptionDetails.setActiveFlag(0);
			if (prescriptionService.update(prescriptionDetails)) {
				cmsResponse.setSuccess(true);
				cmsResponse.setMessage("Deleted Sucessfully");
				log.info("This Prescription Id: " + prescriptionId + " Deleted Sucessfully");
			} else {
//				patientDetails.UndoAllCandIntervAndJobsByCompanyId(patientDetails.getPatientId());
				cmsResponse.setSuccess(false);
				cmsResponse.setMessage("Deletion Failed");
				log.info("Deletion Failed");
			}

		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("This Prescription Not Exist");
			log.info("This Prescription Id: " + prescriptionId + " Not Exist");
		}
		return cmsResponse;
	}

	@GetMapping(path = "/getPrescriptionDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getPrescriptionDetailsById(@PathVariable int id, CmsResponse cmsResponse) {
		PrescriptionDto patient = (PrescriptionDto) prescriptionService.getById("PrescriptionDto", id);
		if (patient != null) {
			cmsResponse.setObject(patient);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Prescription Not Exist");
			log.info("This Prescription Id: " + id + " Not Exist");
		}
		return cmsResponse;
	}

	@GetMapping(path = "/getPrescriptionDetailsByAppointment/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getPrescriptionDetailsByAppointmentId(@PathVariable int id, CmsResponse cmsResponse) {
		PrescriptionDto patient = (PrescriptionDto) prescriptionService.getById("PrescriptionDto", id);
		if (patient != null) {
			cmsResponse.setObject(patient);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Prescription Not Exist");
			log.info("This Prescription Id: " + id + " Not Exist");
		}
		return cmsResponse;
	}

	@GetMapping(path = "/checkSavedAndGetData", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse checkSavedAndGetData(@RequestParam("appointmentId") int appointmentId, CmsResponse cmsResponse,
			PrescriptionDto prescription, Principal principal) {

		PrescriptionDto patientDiagnosisDetails = (PrescriptionDto) prescriptionService
				.checkSavedAndGetData("PrescriptionDto", appointmentId);
		if (patientDiagnosisDetails != null) {
			cmsResponse.setObject(patientDiagnosisDetails);
			cmsResponse.setSuccess(true);
		} else {
			AppointmentDto appointmentDetails = (AppointmentDto) prescriptionService.getById("AppointmentDto",
					appointmentId);
			prescription.setActiveFlag(1);
			System.err.println(appointmentDetails);
			prescription.setAppointment(appointmentDetails);
			prescription.setDoctorName(appointmentDetails.getDoctorName());
			prescription.setPatient(appointmentDetails.getPatientNumber());

			UserDto userDto = prescriptionService.getUserByName(principal.getName());
			prescription.setCreatedBy(userDto);
			prescription.setCreatedDate(AppUtil.currentDateWithTime());
			int id = prescriptionService.save(prescription);
			if (id != 0) {
				prescription.setPrescriptionId(id);
				cmsResponse.setObject(prescription);
				cmsResponse.setSuccess(true);
				log.info("Saved Sucessfully & Saved Diagnosis Id is: " + id);
			} else {
				cmsResponse.setSuccess(false);
				log.info("Saved UnSucessfully");
			}
		}
		return cmsResponse;
	}

	@GetMapping(path = "/getPrescriptionDetailsByPatientIdAndDate/{id}/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getPrescriptionDetailsByPatientIdAndDate(@PathVariable int id, @PathVariable String date,
			CmsResponse cmsResponse) {
		PrescriptionDto prescription = (PrescriptionDto) prescriptionService.getByPatientIdAndDate("PrescriptionDto",
				id, date);
		if (prescription != null) {
			cmsResponse.setObject(prescription);
			cmsResponse.setSuccess(true);
			cmsResponse.setMessage("You can take print of prescription");
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("No prescriptions for this patient at this appointment Date");
			log.info("This prescription Id: " + id + " Not Exist");
		}
		return cmsResponse;
	}
}
