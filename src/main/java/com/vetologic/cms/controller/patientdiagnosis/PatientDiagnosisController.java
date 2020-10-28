package com.vetologic.cms.controller.patientdiagnosis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
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
import org.springframework.web.multipart.MultipartFile;

import com.vetologic.cms.dto.appointment.AppointmentDto;
import com.vetologic.cms.dto.patientdiagnosis.PatientDiagnosisDto;
import com.vetologic.cms.dto.response.CmsResponse;
import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.model.service.patientdiagnosis.PatientDiagnosisService;
import com.vetologic.cms.utils.AppUtil;
import com.vetologic.cms.utils.FileUploader;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("patientdiagnosis")
public class PatientDiagnosisController {
	private static Logger log = LoggerFactory.getLogger(PatientDiagnosisController.class);

	@Autowired
	private PatientDiagnosisService patientDiagnosisService;

	@PostMapping(path = "/addDiagnosis", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse saveDiagnosisPatient(@RequestBody PatientDiagnosisDto patientDiagnosis, CmsResponse cmsResponse,
			Principal principal) {
		patientDiagnosis.setActiveFlag(1);
		UserDto userDto = patientDiagnosisService.getUserByName(principal.getName());
		patientDiagnosis.setCreatedBy(userDto);
		patientDiagnosis.setCreatedDate(AppUtil.currentDateWithTime());
		int id = patientDiagnosisService.save(patientDiagnosis);
		if (id != 0) {
			patientDiagnosis.setDiagnosisId(id);
			cmsResponse.setObject(patientDiagnosis);
			cmsResponse.setSuccess(true);
			cmsResponse.setMessage("Saved Sucessfully");
			log.info("Saved Sucessfully & Saved Diagnosis Id is: " + id);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Saved UnSucessfully");
			log.info("Saved UnSucessfully");
		}
		return cmsResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllPatientDiagnosisDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllPatientDiagnosisDetails(CmsResponse cmsResponse) {
		List<PatientDiagnosisDto> allPatientDiagnosisDetails = (List<PatientDiagnosisDto>) patientDiagnosisService
				.getAll("PatientDiagnosisDto");
		if (allPatientDiagnosisDetails.size() > 0) {
			cmsResponse.setListObject(allPatientDiagnosisDetails);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("PatientDiagnosis List is Empty");
			log.info("PatientDiagnosis List is Empty");
		}
		return cmsResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllPatientDiagnosisDetailsByPatientId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllPatientDiagnosisDetailsByPatientId(@PathVariable int id, CmsResponse cmsResponse) {
		List<PatientDiagnosisDto> allPatientDiagnosisDetails = (List<PatientDiagnosisDto>) patientDiagnosisService
				.getAllByPatientId("PatientDiagnosisDto", id);
		if (allPatientDiagnosisDetails.size() > 0) {
			cmsResponse.setListObject(allPatientDiagnosisDetails);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("PatientDiagnosis List is Empty");
			log.info("PatientDiagnosis List is Empty");
		}
		return cmsResponse;
	}

	@PutMapping(path = "/updatePatientDiagnosis", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse updatePatientDiagnosis(@RequestBody PatientDiagnosisDto patientDiagnosis,
			CmsResponse cmsResponse, Principal principal) {

		PatientDiagnosisDto patientDiagnosisDetails = (PatientDiagnosisDto) patientDiagnosisService
				.getById("PatientDiagnosisDto", patientDiagnosis.getDiagnosisId());
		if (patientDiagnosisDetails != null) {
			patientDiagnosisDetails.setAppointment(patientDiagnosis.getAppointment());
			patientDiagnosisDetails.setBloodPreasure(patientDiagnosis.getBloodPreasure());
//			patientDiagnosisDetails.setDiagnosis(patientDiagnosis.getDiagnosis());
			patientDiagnosisDetails.setDiagnosisId(patientDiagnosis.getDiagnosisId());
			patientDiagnosisDetails.setDoctorName(patientDiagnosis.getDoctorName());
			patientDiagnosisDetails.setHeight(patientDiagnosis.getHeight());
			patientDiagnosisDetails.setHeightUnits(patientDiagnosis.getHeightUnits());
			patientDiagnosisDetails.setWeight(patientDiagnosis.getWeight());
			patientDiagnosisDetails.setWeightUnits(patientDiagnosis.getWeightUnits());
			patientDiagnosisDetails.setTemperature(patientDiagnosis.getTemperature());
			patientDiagnosisDetails.setTemperatureUnits(patientDiagnosis.getTemperatureUnits());
			patientDiagnosisDetails.setPatient(patientDiagnosis.getPatient());
			patientDiagnosisDetails.setThyroid(patientDiagnosis.getThyroid());
			// patientDiagnosisDetails.setThyroidReports(patientDiagnosis.getThyroidReports());
			patientDiagnosisDetails.setDate(patientDiagnosis.getDate());

			if (patientDiagnosis.getDiagnosis() != null) {
				patientDiagnosisDetails.setDiagnosis(patientDiagnosis.getDiagnosis());
			}

			UserDto userDto = patientDiagnosisService.getUserByName(principal.getName());
			patientDiagnosisDetails.setUpdatedBy(userDto);
			patientDiagnosisDetails.setUpdatedDate(AppUtil.currentDateWithTime());

			if (patientDiagnosisService.update(patientDiagnosisDetails)) {
				cmsResponse.setSuccess(true);
				cmsResponse.setObject(patientDiagnosisDetails);
				cmsResponse.setMessage("Updated Sucessfully");
				log.info("This PatientDiagnosis Id: " + patientDiagnosisDetails.getDiagnosisId()
						+ " Updated Sucessfully");
			} else {
				cmsResponse.setSuccess(false);
				cmsResponse.setMessage("Fails to Update");
				log.info("Fails to Update");
			}
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("This PatientDiagnosis Not Exist");
			log.info("This PatientDiagnosis Id: " + patientDiagnosis.getDiagnosisId() + " Not Exist");
		}
		return cmsResponse;
	}

	@PutMapping(path = "/deletePatientDiagnosisDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse deletePatientDiagnosisDetails(@RequestParam("diagnosisId") int diagnosisId,
			CmsResponse cmsResponse) {
		PatientDiagnosisDto patientDiagnosisDetails = (PatientDiagnosisDto) patientDiagnosisService
				.getById("PatientDiagnosisDto", diagnosisId);
		if (patientDiagnosisDetails != null) {
			patientDiagnosisDetails.setUpdatedDate(AppUtil.currentDateWithTime());
			patientDiagnosisDetails.setActiveFlag(0);
			if (patientDiagnosisService.update(patientDiagnosisDetails)) {
				cmsResponse.setSuccess(true);
				cmsResponse.setMessage("Deleted Sucessfully");
				log.info("This PatientDiagnosis Id: " + diagnosisId + " Deleted Sucessfully");
			} else {
//				patientDetails.UndoAllCandIntervAndJobsByCompanyId(patientDetails.getPatientId());//already comment
				cmsResponse.setSuccess(false);
				cmsResponse.setMessage("Deletion Failed");
				log.info("Deletion Failed");
			}

		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("This Patient Diagnosis Not Exist");
			log.info("This Patient Diagnosis Id: " + diagnosisId + " Not Exist");
		}
		return cmsResponse;
	}

	@GetMapping(path = "/getPatientDiagnosisDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getPatientDiagnosisDetailsById(@PathVariable int id, CmsResponse cmsResponse) {
		PatientDiagnosisDto patientDiagnosis = (PatientDiagnosisDto) patientDiagnosisService
				.getById("PatientDiagnosisDto", id);
		if (patientDiagnosis != null) {
			cmsResponse.setObject(patientDiagnosis);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("PatientDiagnosis Not Exist");
			log.info("This PatientDiagnosis Id: " + id + " Not Exist");
		}
		return cmsResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllExceptThisId", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllExceptThisId(@RequestParam("id") int id, CmsResponse cmsResponse) {
		List<PatientDiagnosisDto> allPatientDiagnosisExceptThisId = (List<PatientDiagnosisDto>) patientDiagnosisService
				.getAllExceptOnePatientDiagnosis("PatientDiagnosisDto", id);
		if (allPatientDiagnosisExceptThisId.size() > 0) {
			cmsResponse.setListObject(allPatientDiagnosisExceptThisId);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Patient Diagnosis List is Empty");
			log.info("Patient Diagnosis List is Empty");
		}
		return cmsResponse;
	}

	// Diagnosis Thyroid file *
	@PostMapping(path = "/saveOrUpdateThyroidFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse saveOrUpdateThyroidFiles(CmsResponse cmsResponse, int diagnosisId, MultipartFile thyroidFile,
			FileUploader fileUploader) {
		try {
			PatientDiagnosisDto patientDiagnosisDetails = (PatientDiagnosisDto) patientDiagnosisService
					.getById("PatientDiagnosisDto", diagnosisId);

			if (thyroidFile != null) {
				if (!thyroidFile.getOriginalFilename().equalsIgnoreCase(patientDiagnosisDetails.getThyroidReports())) {
					if (fileUploader.uploadThyroidFiles(thyroidFile, "Thyroid", diagnosisId)) {
						String thyroidFileName = diagnosisId + "_" + thyroidFile.getOriginalFilename();
						patientDiagnosisDetails.setThyroidReports(thyroidFileName);
						System.err.println(patientDiagnosisDetails.toString());
						patientDiagnosisService.update(patientDiagnosisDetails);
						log.info("Resume File: " + thyroidFileName + " is Uploaded Successfully.");
						cmsResponse.setSuccess(true);
					} else {
						log.info("Fails to Upload Resume File");
						cmsResponse.setSuccess(false);
					}
				} else {
					cmsResponse.setSuccess(true);
					cmsResponse.setMessage("Already Uploaded");
					log.info("This Thyroid file is Already Uploaded");
				}
			} else {
				log.info("Thyroid File is Empty.");
				cmsResponse.setSuccess(false);
			}
		} catch (Exception e) {
			cmsResponse.setSuccess(false);
			e.printStackTrace();
		}
		return cmsResponse;
	}

	@GetMapping(path = "/checkSavedAndGetData", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse checkSavedAndGetData(@RequestParam("appointmentId") int appointmentId, CmsResponse cmsResponse,
			PatientDiagnosisDto patientDiagnosis, Principal principal) {

		PatientDiagnosisDto patientDiagnosisDetails = (PatientDiagnosisDto) patientDiagnosisService
				.checkSavedAndGetData("PatientDiagnosisDto", appointmentId);
		if (patientDiagnosisDetails != null) {
			cmsResponse.setObject(patientDiagnosisDetails);
			cmsResponse.setSuccess(true);
		} else {
			AppointmentDto appointmentDetails = (AppointmentDto) patientDiagnosisService.getById("AppointmentDto",
					appointmentId);
			patientDiagnosis.setActiveFlag(1);
			System.err.println(appointmentDetails);
			patientDiagnosis.setAppointment(appointmentDetails);
			patientDiagnosis.setDoctorName(appointmentDetails.getDoctorName());
			patientDiagnosis.setPatient(appointmentDetails.getPatientNumber());
			UserDto userDto = patientDiagnosisService.getUserByName(principal.getName());
			patientDiagnosis.setCreatedBy(userDto);
			patientDiagnosis.setCreatedDate(AppUtil.currentDateWithTime());
			int id = patientDiagnosisService.save(patientDiagnosis);
			if (id != 0) {
				patientDiagnosis.setDiagnosisId(id);
				cmsResponse.setObject(patientDiagnosis);
				cmsResponse.setSuccess(true);
				log.info("Saved Sucessfully & Saved Diagnosis Id is: " + id);
			} else {
				cmsResponse.setSuccess(false);
				log.info("Saved UnSucessfully");
			}
		}
		return cmsResponse;
	}

	@RequestMapping(value = "/getThyroidFile")
	public CmsResponse getThyroidFile(@RequestParam("diagnosisId") int diagnosisId, CmsResponse cmsResponse)
			throws IOException {
		try {
			PatientDiagnosisDto patientDiagnosisDetails = (PatientDiagnosisDto) patientDiagnosisService
					.getById("PatientDiagnosisDto", diagnosisId);
			if (patientDiagnosisDetails != null) {
				String subFolderName = "Thyroid";
				Path rootPath = FileSystems.getDefault().getPath("").toAbsolutePath();
				String filePath = rootPath + File.separator + "Uploads" + File.separator + "patient" + File.separator
						+ File.separator + subFolderName + File.separator + patientDiagnosisDetails.getThyroidReports();

				File file = new File(filePath);

				// Creating a byte array using the length of the file
				// file.length returns long which is cast to int.
				byte[] byteArray = new byte[(int) file.length()];
				FileInputStream fis = null;

				fis = new FileInputStream(file);
				fis.read(byteArray);
				fis.close();

				cmsResponse.setObject(patientDiagnosisDetails);
				cmsResponse.setByteArray(byteArray);
				cmsResponse.setSuccess(true);
			} else {
				cmsResponse.setSuccess(false);
			}
		} catch (Exception e) {
			cmsResponse.setSuccess(false);
			log.error(e.getMessage());
		}
		return cmsResponse;
	}

	@GetMapping(path = "/getPatientDiagnosisDetailsByAppointmentId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getPatientDiagnosisDetailsByAppointmentId(@PathVariable int id, CmsResponse cmsResponse) {
		PatientDiagnosisDto patientDiagnosis = (PatientDiagnosisDto) patientDiagnosisService
				.getByAppointmentId("PatientDiagnosisDto", id);
		if (patientDiagnosis != null) {
			cmsResponse.setObject(patientDiagnosis);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("PatientDiagnosis Not Exist");
			log.info("This PatientDiagnosis Id: " + id + " Not Exist");
		}
		return cmsResponse;
	}
}
