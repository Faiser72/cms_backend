package com.vetologic.cms.controller.labtest;

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
import com.vetologic.cms.dto.labtest.LabTestDto;
import com.vetologic.cms.dto.response.CmsResponse;
import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.model.service.labtest.LabTestService;
import com.vetologic.cms.utils.AppUtil;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("labtest")
public class LatTestController {

	private static Logger log = LoggerFactory.getLogger(LatTestController.class);

	@Autowired
	private LabTestService labTestService;

	@PostMapping(path = "/addLabTest", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse saveLabTest(@RequestBody LabTestDto labTest, CmsResponse cmsResponse, Principal principal) {
		labTest.setActiveFlag(1);
		UserDto userDto = labTestService.getUserByName(principal.getName());
		labTest.setCreatedBy(userDto);
		labTest.setCreatedDate(AppUtil.currentDateWithTime());
		int id = labTestService.save(labTest);
		if (id != 0) {
			labTest.setLabTestId(id);
			;
			cmsResponse.setObject(labTest);
			cmsResponse.setSuccess(true);
			cmsResponse.setMessage("Saved Successfully");
			log.info("Saved Successfully & Saved labTest Id is: " + id);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Failed To Save");
			log.info("Saved UnSuccessfully");
		}
		return cmsResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllLabTestDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllLabTestDetails(CmsResponse cmsResponse) {
		List<LabTestDto> allLabTestDetails = (List<LabTestDto>) labTestService.getAll("LabTestDto");
		if (allLabTestDetails.size() > 0) {
			cmsResponse.setListObject(allLabTestDetails);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("LabTest List is Empty");
			log.info("LabTest List is Empty");
		}
		return cmsResponse;
	}

	@GetMapping(path = "/checkSavedAndGetData", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse checkSavedAndGetData(@RequestParam("appointmentId") int appointmentId, CmsResponse cmsResponse,
			LabTestDto labtest, Principal principal) {

		LabTestDto LabtestDetails = (LabTestDto) labTestService.checkSavedAndGetData("LabTestDto", appointmentId);
		if (LabtestDetails != null) {
			cmsResponse.setObject(LabtestDetails);
			cmsResponse.setSuccess(true);
		} else {
			AppointmentDto appointmentDetails = (AppointmentDto) labTestService.getById("AppointmentDto",
					appointmentId);
			labtest.setActiveFlag(1);
			System.err.println(appointmentDetails);
			labtest.setAppointment(appointmentDetails);
			labtest.setDoctor(appointmentDetails.getDoctorName());
			labtest.setPatient(appointmentDetails.getPatientNumber());

			UserDto userDto = labTestService.getUserByName(principal.getName());
			labtest.setCreatedBy(userDto);
			labtest.setCreatedDate(AppUtil.currentDateWithTime());
			int id = labTestService.save(labtest);
			if (id != 0) {
				labtest.setLabTestId(id);
				cmsResponse.setObject(labtest);
				cmsResponse.setSuccess(true);
				log.info("Saved Successfully & Saved Labtest Id is: " + id);
			} else {
				cmsResponse.setSuccess(false);
				log.info("Saved UnSuccessfully");
			}
		}
		return cmsResponse;
	}

	@PutMapping(path = "/updateLabTest", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse updateLabTest(@RequestBody LabTestDto labtest, CmsResponse cmsResponse, Principal principal) {

		LabTestDto labtestDetails = (LabTestDto) labTestService.getById("LabTestDto", labtest.getLabTestId());
		if (labtestDetails != null) {
			labtestDetails.setAnaElisa(labtest.getAnaElisa());
			labtestDetails.setAppointment(labtest.getAppointment());
			labtestDetails.setAsloQuantitative(labtest.getAsloQuantitative());
			labtestDetails.setBloodGrouprhtype(labtest.getBloodGrouprhtype());
			labtestDetails.setBloodUrea(labtest.getBloodUrea());
			labtestDetails.setBloodUreaNitrogen(labtest.getBloodUreaNitrogen());
			labtestDetails.setChestXRay(labtest.getChestXRay());
			labtestDetails.setCompleteheamogram(labtest.getCompleteheamogram());
			labtestDetails.setCrpQuantitative(labtest.getCrpQuantitative());
			labtestDetails.setDate(labtest.getDate());
			labtestDetails.setDoctor(labtest.getDoctor());
			labtestDetails.setEcg(labtest.getEcg());
			labtestDetails.setEchocardiogram(labtest.getEchocardiogram());
			labtestDetails.setElectrolytes(labtest.getElectrolytes());
			labtestDetails.setEsr(labtest.getEsr());
			labtestDetails.setFshLHRatio(labtest.getFshLHRatio());
			labtestDetails.setGlycatedHb(labtest.getGlycatedHb());
			labtestDetails.setHbsagElisa(labtest.getHbsagElisa());
			labtestDetails.setHivElisa(labtest.getHivElisa());
			labtestDetails.setLh(labtest.getLh());
			labtestDetails.setLipidProfile(labtest.getLipidProfile());
			labtestDetails.setLiverFunctionTest(labtest.getLiverFunctionTest());
			labtestDetails.setPatient(labtest.getPatient());
			labtestDetails.setPostPrandialBloodSugar(labtest.getPostPrandialBloodSugar());
			labtestDetails.setProblemSuspected(labtest.getProblemSuspected());
			labtestDetails.setProlactin(labtest.getProlactin());
			labtestDetails.setRaQuantitative(labtest.getRaQuantitative());
			labtestDetails.setSerumCalcium(labtest.getSerumCalcium());
			labtestDetails.setSerumCreatinine(labtest.getSerumCreatinine());
			labtestDetails.setTreadmillTest(labtest.getTreadmillTest());
			labtestDetails.setTsh(labtest.getTsh());

			labtestDetails.setUltraSoundAbdomenAndPelvis(labtest.getUltraSoundAbdomenAndPelvis());
			labtestDetails.setUricAcid(labtest.getUricAcid());
			labtestDetails.setUrineCompleteAnalysis(labtest.getUrineCompleteAnalysis());
			labtestDetails.setUrineRoutine(labtest.getUrineRoutine());

			UserDto userDto = labTestService.getUserByName(principal.getName());
			labtestDetails.setUpdatedBy(userDto);
			labtestDetails.setUpdatedDate(AppUtil.currentDateWithTime());

			if (labTestService.update(labtestDetails)) {
				cmsResponse.setSuccess(true);
				cmsResponse.setObject(labtestDetails);
				cmsResponse.setMessage("Updated Successfully");
				log.info("This Prescription Id: " + labtestDetails.getLabTestId() + " Updated Successfully");
			} else {
				cmsResponse.setSuccess(false);
				cmsResponse.setMessage("Fails to Update");
				log.info("Fails to Update");
			}
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("This Labtest Not Exist");
			log.info("This Labtest Id: " + labtest.getLabTestId() + " Not Exist");
		}
		return cmsResponse;
	}

	@GetMapping(path = "/getLabTestDetailsByPatientIdAndDate/{id}/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getLabTestDetailsByPatientIdAndDate(@PathVariable int id, @PathVariable String date,
			CmsResponse cmsResponse) {
		LabTestDto labtest = (LabTestDto) labTestService.getByPatientIdAndDate("LabTestDto", id, date);
		if (labtest != null) {
			cmsResponse.setObject(labtest);
			cmsResponse.setSuccess(true);
			cmsResponse.setMessage("You can take print of Lab Test");
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("No Labtest for this patient at this appointment Date");
			log.info("This LabTest Id: " + id + " Not Exist");
		}
		return cmsResponse;
	}
}
