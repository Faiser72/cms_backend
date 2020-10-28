package com.vetologic.cms.controller.appointment;

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
import com.vetologic.cms.dto.response.CmsResponse;
import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.model.service.appointment.AppointmentService;
import com.vetologic.cms.utils.AppUtil;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("admin/appointment")
public class AppointmentController {

	private static Logger log = LoggerFactory.getLogger(AppointmentController.class);

	@Autowired
	private AppointmentService appointmentService;

	@PostMapping(path = "/addAppointment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse saveAppointmentDetails(@RequestBody AppointmentDto appointment, CmsResponse cmsResponse,
			Principal principal) {
		appointment.setActiveFlag(1);
		appointment.setTestedFlag(0);
		appointment.setDeletionFlag(0);
		UserDto userDto = appointmentService.getUserByName(principal.getName());
		appointment.setCreatedBy(userDto);
		appointment.setCreatedDate(AppUtil.currentDateWithTime());
		int id = appointmentService.save(appointment);
		if (id != 0) {
			appointment.setAppointmentId(id);
			cmsResponse.setObject(appointment);
			cmsResponse.setSuccess(true);
			cmsResponse.setMessage("Saved Successfully");
			log.info("Saved Successfully & Saved Appointment Id is: " + id);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Saved UnSuccessfully");
			log.info("Saved UnSuccessfully");
		}
		return cmsResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllAppointmentDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllAppointmentDetails(CmsResponse cmsResponse) {
		List<AppointmentDto> allAppointmentDetails = (List<AppointmentDto>) appointmentService.getAll("AppointmentDto");
		if (allAppointmentDetails.size() > 0) {
			cmsResponse.setListObject(allAppointmentDetails);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Appointment List is Empty");
			log.info("Appointment List is Empty");
		}
		return cmsResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllAppointmentDetailsExceptThis", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllAppointmentDetailsExceptThis(@RequestParam("appointmentId") int appointmentId,
			CmsResponse cmsResponse) {
		List<AppointmentDto> allAppointmentDetails = (List<AppointmentDto>) appointmentService
				.getAllExceptThis("AppointmentDto", appointmentId);
		if (allAppointmentDetails.size() > 0) {
			cmsResponse.setListObject(allAppointmentDetails);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Appointment List is Empty");
			log.info("Appointment List is Empty");
		}
		return cmsResponse;
	}

	@PutMapping(path = "/updateAppointment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse updateAppointment(@RequestBody AppointmentDto appointment, CmsResponse cmsResponse,
			Principal principal) {

		AppointmentDto appointmentDetails = (AppointmentDto) appointmentService.getById("AppointmentDto",
				appointment.getAppointmentId());
		if (appointmentDetails != null) {
			appointmentDetails.setPatientNumber(appointment.getPatientNumber());
			appointmentDetails.setAppointmentDate(appointment.getAppointmentDate());
			appointmentDetails.setAppointmentTime(appointment.getAppointmentTime());
			appointmentDetails.setDoctorName(appointment.getDoctorName());
			appointmentDetails.setPatientName(appointment.getPatientName());
			appointmentDetails.setPhoneNumber(appointment.getPhoneNumber());

			UserDto userDto = appointmentService.getUserByName(principal.getName());
			appointmentDetails.setUpdatedBy(userDto);
			appointmentDetails.setUpdatedDate(AppUtil.currentDateWithTime());

			if (appointmentService.update(appointmentDetails)) {
				cmsResponse.setSuccess(true);
				cmsResponse.setObject(appointmentDetails);
				cmsResponse.setMessage("Updated Successfully");
				log.info("This Appointment Id: " + appointmentDetails.getAppointmentId() + " Updated Successfully");
			} else {
				cmsResponse.setSuccess(false);
				cmsResponse.setMessage("Fails to Update");
				log.info("Fails to Update");
			}
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("This Appointment Not Exist");
			log.info("This Appointment Id: " + appointment.getAppointmentId() + " Not Exist");
		}
		return cmsResponse;
	}

	@PutMapping(path = "/deleteAppointmentDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse deleteAppointmentDetails(@RequestParam("appointmentId") int appointmentId,
			CmsResponse cmsResponse) {
		AppointmentDto appointmentDetails = (AppointmentDto) appointmentService.getById("AppointmentDto",
				appointmentId);
		if (appointmentDetails != null) {
			appointmentDetails.setUpdatedDate(AppUtil.currentDateWithTime());
			appointmentDetails.setActiveFlag(0);
			if (appointmentService.update(appointmentDetails)) {
				cmsResponse.setSuccess(true);
				cmsResponse.setMessage("Deleted Successfully");
				log.info("This Appointment Id: " + appointmentId + " Deleted Successfully");
			} else {
//				patientDetails.UndoAllCandIntervAndJobsByCompanyId(patientDetails.getPatientId());
				cmsResponse.setSuccess(false);
				cmsResponse.setMessage("Deletion Failed");
				log.info("Deletion Failed");
			}

		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("This Appointment Not Exist");
			log.info("This Appointment Id: " + appointmentId + " Not Exist");
		}
		return cmsResponse;
	}

	@GetMapping(path = "/getAppointmentDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAppointmentDetailsById(@PathVariable int id, CmsResponse cmsResponse) {
		AppointmentDto appointment = (AppointmentDto) appointmentService.getById("AppointmentDto", id);
		if (appointment != null) {
			cmsResponse.setObject(appointment);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Appointment Not Exist");
			log.info("This Appointment Id: " + id + " Not Exist");
		}
		return cmsResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAppointmentDetailsByDoctorId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAppointmentDetailsByDoctorId(@PathVariable int id, CmsResponse cmsResponse) {
		List<AppointmentDto> appointment = (List<AppointmentDto>) appointmentService.getByDoctorId("AppointmentDto",
				id);
		if (appointment.size() > 0) {
			cmsResponse.setListObject(appointment);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Appointment for: " + id + " Not Exist");
			log.info("This Appointment Id: " + id + " Not Exist");
		}
		return cmsResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAppointmentDetailsByDoctorIdAndDate/{doctorId}/{currentDate}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAppointmentDetailsByDoctorId(@PathVariable int doctorId, @PathVariable String currentDate,
			CmsResponse cmsResponse) {
//		String currentDate=AppUtil.currentDateWithoutTime();

		List<AppointmentDto> appointment = (List<AppointmentDto>) appointmentService
				.getByDoctorIdAndDate("AppointmentDto", doctorId, currentDate);
		if (appointment.size() > 0) {
			cmsResponse.setListObject(appointment);
			System.err.println(appointment);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Appointment for: " + doctorId + " Not Exist");
			log.info("This Appointment Id: " + doctorId + " Not Exist");
		}
		return cmsResponse;
	}

//	to make diagonosed or tested
	@PutMapping(path = "/testedAppointment", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse testedAppointment(@RequestParam("appointmentId") int appointmentId, CmsResponse cmsResponse) {
		AppointmentDto appointmentDetails = (AppointmentDto) appointmentService.getById("AppointmentDto",
				appointmentId);
		if (appointmentDetails != null) {
			appointmentDetails.setTestedDate(AppUtil.currentDateWithoutTime());
			appointmentDetails.setTestedFlag(1);
			if (appointmentService.update(appointmentDetails)) {
				cmsResponse.setSuccess(true);
				cmsResponse.setMessage("Tested Successfully");
				log.info("This Appointment Id: " + appointmentId + " Tested Successfully");
			} else {
//				patientDetails.UndoAllCandIntervAndJobsByCompanyId(patientDetails.getPatientId());
				cmsResponse.setSuccess(false);
				cmsResponse.setMessage("Tested Failed");
				log.info("Tested Failed");
			}

		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("This Appointment Not Exist");
			log.info("This Appointment Id: " + appointmentId + " Not Exist");
		}
		return cmsResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllTestedPatientDetailsBtwnDates/{fromDate}/{toDate}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllTestedPatientDetailsBtwnDates(@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate, CmsResponse cmsResponse) {
		List<AppointmentDto> allPatientDetails = (List<AppointmentDto>) appointmentService
				.getAllTestedBtwnDates("AppointmentDto", fromDate, toDate);
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

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllAppointmentsDetailsBtwnDates/{fromDate}/{toDate}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllAppointmentsDetailsBtwnDates(@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate, CmsResponse cmsResponse) {
		List<AppointmentDto> allPatientDetails = (List<AppointmentDto>) appointmentService
				.getAllAppointmentBtwnDates("AppointmentDto", fromDate, toDate);
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

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllAppointmentsDetailsOfDoctorBtwnDates/{id}/{fromDate}/{toDate}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllAppointmentsDetailsOfDoctorBtwnDates(@PathVariable("id") int id,
			@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate, CmsResponse cmsResponse) {
		List<AppointmentDto> allPatientDetails = (List<AppointmentDto>) appointmentService
				.getAllAppointmentOfDoctorBtwnDates("AppointmentDto", id, fromDate, toDate);
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

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllTestedDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllTestedDetails(CmsResponse cmsResponse) {
		List<AppointmentDto> allAppointmentDetails = (List<AppointmentDto>) appointmentService
				.getAllTestedList("AppointmentDto");
		if (allAppointmentDetails.size() > 0) {
			cmsResponse.setListObject(allAppointmentDetails);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Appointment List is Empty");
			log.info("Appointment List is Empty");
		}
		return cmsResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAppointmentDetailsByDate/{currentDate}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAppointmentDetailsByDate(@PathVariable String currentDate, CmsResponse cmsResponse) {
//		String currentDate=AppUtil.currentDateWithoutTime();

		List<AppointmentDto> appointment = (List<AppointmentDto>) appointmentService.getByDate("AppointmentDto",
				currentDate);
		if (appointment.size() > 0) {
			cmsResponse.setListObject(appointment);
			System.err.println(appointment);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Appointment On: " + currentDate + " Not Exist");
			log.info("This Appointment on: " + currentDate + " Not Exist");
		}
		return cmsResponse;
	}
}
