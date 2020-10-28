package com.vetologic.cms.controller.testreports;

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
import com.vetologic.cms.dto.response.CmsResponse;
import com.vetologic.cms.dto.testreports.TestReportsDto;
import com.vetologic.cms.model.service.testreports.TestReportsService;
import com.vetologic.cms.utils.FileUploader;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("testreports")
public class TestReportsController {

	private static Logger log = LoggerFactory.getLogger(TestReportsController.class);

	@Autowired
	private TestReportsService testReportsService;

	// Diagnosis Test Reports file *
	@PostMapping(path = "/saveOrUpdateTestReportFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse saveOrUpdateTestReportFiles(CmsResponse cmsResponse, int testReportsId,
			MultipartFile testReportsFile, FileUploader fileUploader) {
		try {
			TestReportsDto testReportsDetails = (TestReportsDto) testReportsService.getById("TestReportsDto",
					testReportsId);

			if (testReportsFile != null) {
				if (!testReportsFile.getOriginalFilename()
						.equalsIgnoreCase(testReportsDetails.getTestReportFileName())) {
					if (fileUploader.uploadTestReportFiles(testReportsFile, "TestReport", testReportsId)) {
						String TestReportsFileName = testReportsId + "_" + testReportsFile.getOriginalFilename();
						testReportsDetails.setTestReportFileName(TestReportsFileName);
						testReportsService.update(testReportsDetails);
						log.info("Test Report File: " + TestReportsFileName + " is Uploaded Successfully.");
						cmsResponse.setSuccess(true);
					} else {
						log.info("Fails to Upload Test Report File");
						cmsResponse.setSuccess(false);
					}
				} else {
					cmsResponse.setSuccess(true);
					cmsResponse.setMessage("Already Uploaded");
					log.info("This Test Report file is Already Uploaded");
				}
			} else {
				log.info("Test Report File is Empty.");
				cmsResponse.setSuccess(false);
			}
		} catch (Exception e) {
			cmsResponse.setSuccess(false);
			e.printStackTrace();
		}
		return cmsResponse;
	}

	@RequestMapping(value = "/getTestReportsFile")
	public CmsResponse getTestReportsFile(@RequestParam("diagnosisId") int diagnosisId, CmsResponse cmsResponse)
			throws IOException {
		try {
			TestReportsDto testReportsDetails = (TestReportsDto) testReportsService.getByDiagnosisId("TestReportsDto",
					diagnosisId);
			if (testReportsDetails != null) {
				String subFolderName = "TestReport";
				Path rootPath = FileSystems.getDefault().getPath("").toAbsolutePath();
				String filePath = rootPath + File.separator + "Uploads" + File.separator + "patient" + File.separator
						+ File.separator + subFolderName + File.separator + testReportsDetails.getTestReportFileName();

				File file = new File(filePath);

				// Creating a byte array using the length of the file
				// file.length returns long which is cast to int.
				byte[] byteArray = new byte[(int) file.length()];
				FileInputStream fis = null;

				fis = new FileInputStream(file);
				fis.read(byteArray);
				fis.close();

				cmsResponse.setObject(testReportsDetails);
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

//	@SuppressWarnings("unchecked")
//	@RequestMapping(value = "/getTestReportsFile")
//	public CmsResponse getTestReportsFile(@RequestParam("diagnosisId") int diagnosisId, CmsResponse cmsResponse)
//			throws IOException {
//		try {
//			List<TestReportsDto> allTestReportsDetails = (List<TestReportsDto>) testReportsService.getAllByDiagnosisId("TestReportsDto",diagnosisId);
//			if (allTestReportsDetails.size() > 0) {
//				String subFolderName = "TestReport";
//				Path rootPath = FileSystems.getDefault().getPath("").toAbsolutePath();
//				String filePath = rootPath + File.separator + "Uploads" + File.separator + "patient" + File.separator
//						+ File.separator + subFolderName + File.separator + ((TestReportsDto) allTestReportsDetails).getTestReportFileName();
//
//				File file = new File(filePath);
//
//				// Creating a byte array using the length of the file
//				// file.length returns long which is cast to int.
//				byte[] byteArray = new byte[(int) file.length()];
//				FileInputStream fis = null;
//
//				fis = new FileInputStream(file);
//				fis.read(byteArray);
//				fis.close();
//
//				cmsResponse.setListObject(allTestReportsDetails);
//				cmsResponse.setByteArray(byteArray);
//				cmsResponse.setSuccess(true);
//			} else {
//				cmsResponse.setSuccess(false);
//			}
//		} catch (Exception e) {
//			cmsResponse.setSuccess(false);
//			log.error(e.getMessage());
//		}
//		return cmsResponse;
//	}

	@PostMapping(path = "/addTestReports", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse saveTestReports(@RequestBody TestReportsDto testReports, CmsResponse cmsResponse) {
		testReports.setActiveFlag(1);
		int id = testReportsService.save(testReports);
		if (id != 0) {
			testReports.setTestReportsId(id);
			cmsResponse.setObject(testReports);
			cmsResponse.setSuccess(true);
			cmsResponse.setMessage("Saved Sucessfully");
			log.info("Saved Sucessfully & Saved Test Reports Id is: " + id);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Saved UnSucessfully");
			log.info("Saved UnSucessfully");
		}
		return cmsResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllTestReportsDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllTestReportsDetails(CmsResponse cmsResponse) {
		List<TestReportsDto> allTestReportsDetails = (List<TestReportsDto>) testReportsService.getAll("TestReportsDto");
		if (allTestReportsDetails.size() > 0) {
			cmsResponse.setListObject(allTestReportsDetails);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Test Reports List is Empty");
			log.info("Test Reports List is Empty");
		}
		return cmsResponse;
	}

	@PutMapping(path = "/updateTestReports", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse updateTestReports(@RequestBody TestReportsDto testReports, CmsResponse cmsResponse,
			Principal principal) {

		TestReportsDto testReportsDetails = (TestReportsDto) testReportsService.getById("TestReportsDto",
				testReports.getTestReportsId());
		if (testReportsDetails != null) {
			testReportsDetails.setDiagnosisId(testReports.getDiagnosisId());
			testReportsDetails.setTestName(testReports.getTestName());
//			testReportsDetails.setTestReportFileName(testReports.getTestReportFileName());

			if (testReportsService.update(testReportsDetails)) {
				cmsResponse.setSuccess(true);
				cmsResponse.setObject(testReportsDetails);
				cmsResponse.setMessage("Updated Sucessfully");
				log.info("This Test Reports Id: " + testReportsDetails.getDiagnosisId() + " Updated Sucessfully");
			} else {
				cmsResponse.setSuccess(false);
				cmsResponse.setMessage("Fails to Update");
				log.info("Fails to Update");
			}
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("This Test Report Not Exist");
			log.info("This Test Report Id: " + testReports.getTestReportsId() + " Not Exist");
		}
		return cmsResponse;
	}

	@PutMapping(path = "/deleteTestReportsDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse deleteTestReportsDetails(@RequestParam("testReportsId") int testReportsId,
			CmsResponse cmsResponse) {
		TestReportsDto testReportsDetails = (TestReportsDto) testReportsService.getById("TestReportsDto",
				testReportsId);
		if (testReportsDetails != null) {
			testReportsDetails.setActiveFlag(0);
			if (testReportsService.update(testReportsDetails)) {
				cmsResponse.setSuccess(true);
				cmsResponse.setMessage("Deleted Sucessfully");
				log.info("This TestReports Id: " + testReportsId + " Deleted Sucessfully");
			} else {
//				patientDetails.UndoAllCandIntervAndJobsByCompanyId(patientDetails.getPatientId());//already comment
				cmsResponse.setSuccess(false);
				cmsResponse.setMessage("Deletion Failed");
				log.info("Deletion Failed");
			}

		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("This TestReports Not Exist");
			log.info("This Test Reports Id: " + testReportsId + " Not Exist");
		}
		return cmsResponse;
	}

	@GetMapping(path = "/getTestReportsDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getTestReportsDetailsById(@PathVariable int id, CmsResponse cmsResponse) {
		TestReportsDto testReports = (TestReportsDto) testReportsService.getById("TestReportsDto", id);
		if (testReports != null) {
			cmsResponse.setObject(testReports);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("testReports Not Exist");
			log.info("This testReports Id: " + id + " Not Exist");
		}
		return cmsResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllTestReportsDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllTestReportsDetails(@PathVariable int id, CmsResponse cmsResponse) {
		List<TestReportsDto> testReports = (List<TestReportsDto>) testReportsService
				.getAllByDiagnosisId("TestReportsDto", id);
		if (testReports.size() > 0) {
			cmsResponse.setListObject(testReports);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("testReports Not Exist");
			log.info("This testReports Id: " + id + " Not Exist");
		}
		return cmsResponse;
	}

//	public CmsResponse getAllAppointmentDetails(CmsResponse cmsResponse) {
//		List<AppointmentDto> allAppointmentDetails = (List<AppointmentDto>) appointmentService.getAll("AppointmentDto");
//		if (allAppointmentDetails.size() > 0) {
//			cmsResponse.setListObject(allAppointmentDetails);
//			cmsResponse.setSuccess(true);
//		} else {
//			cmsResponse.setSuccess(false);
//			cmsResponse.setMessage("Appointment List is Empty");
//			log.info("Appointment List is Empty");
//		}
//		return cmsResponse;
//	}

}
