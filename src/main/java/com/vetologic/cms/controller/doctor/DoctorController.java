package com.vetologic.cms.controller.doctor;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
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
import org.springframework.web.multipart.MultipartFile;

import com.vetologic.cms.dto.doctor.DoctorDto;
import com.vetologic.cms.dto.doctor.DoctorRoleMasterDto;
import com.vetologic.cms.dto.response.CmsResponse;
import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.dto.user.UserTypeDto;
import com.vetologic.cms.model.service.doctor.DoctorService;
import com.vetologic.cms.model.service.user.UserService;
import com.vetologic.cms.utils.AppUtil;
import com.vetologic.cms.utils.FileUploader;
import com.vetologic.cms.utils.MailUtil;
import com.vetologic.cms.utils.PasswordUtil;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("admin/doctor")
public class DoctorController {

	private static Logger log = LoggerFactory.getLogger(DoctorController.class);

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	MailUtil mailUtil;

	@PostMapping(path = "/addDoctor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse saveDoctorDetails(@RequestBody DoctorDto doctor, DoctorRoleMasterDto doctorRole, UserDto user,
			CmsResponse cmsResponse, Principal principal) {
		// for saving doctor details to user.
		user.setActiveFlag(1);
		user.setCreatedDate(AppUtil.currentDateWithTime());
		String[] userName = doctor.getEmailId().split("@");

		String generatePassword = new PasswordUtil().generatePassword();
		String hashedPassword = passwordEncoder.encode(generatePassword);
		System.err.println(hashedPassword);
		user.setPassword(hashedPassword);
		user.setUsername(userName[0]);
		user.setEmailId(doctor.getEmailId().toLowerCase());
		user.setDisplayName(doctor.getDoctorName());
		user.setMobileNo(doctor.getPhoneNumber());
		UserTypeDto userType = userService.getDesignationByRoleName("ROLE_USER");
		user.setUserType(userType);
		int userId = userService.save(user);
		if (userId != 0) {

			user.setUserId(userId);
			int doctorId = 0;
			try {
				// for saving doctor details.
				doctor.setActiveFlag(1);
				UserDto userDto = doctorService.getUserByName(principal.getName());
				doctor.setCreatedBy(userDto);
				doctor.setUser(user);
				doctor.setCreatedDate(AppUtil.currentDateWithTime());
				doctor.setEmailId(doctor.getEmailId().toLowerCase());
				doctorId = doctorService.save(doctor);
				if (doctorId != 0) {
					// UserDto userDetails = (UserDto) userService.getById("UserDto", "userId",
					// userId);
					doctor.setDoctorId(doctorId);
					// userDetails.setDoctor(doctor);
					// userService.update(userDetails);
					user.setPassword(generatePassword);
					mailUtil.sendCreateUserEmail(user);
					cmsResponse.setObject(doctor);
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

				if (doctorService.deleteDoctor(doctor) && userService.deleteUser(user)) {
					cmsResponse.setSuccess(false);
					cmsResponse.setMessage("Saved UnSuccessfully! Due to failure to send mail.");
					log.info("Saved UnSuccessfully for User Id is: " + userId + " Due to failure to send mail.");
					log.info("Saved UnSuccessfully for Doctor Id is: " + doctorId + " Due to failure to send mail.");
				} else {
					cmsResponse.setSuccess(true);
					cmsResponse.setMessage("Saved Successfully! But Fails to send mail.");
					log.info("Saved Successfully & Saved User Id is: " + userId + " But Fails to send mail.");
					log.info("Saved Successfully & Saved Doctor Id is: " + userId + " But Fails to send mail.");
				}
				log.error(e.getMessage());
				return cmsResponse;
			}
			cmsResponse.setSuccess(true);
			cmsResponse.setMessage("Saved Successfully");
			log.info("Saved Successfully & Saved User Id is: " + userId + " Sent Mail also Sucessfully.");

		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Saved UnSuccessfully");
			log.info("Saved UnSuccessfully.");
		}

		return cmsResponse;
	}

	// Doctor profile Photo file
	@PostMapping(path = "/saveOrUpdateDoctorProfilePhoto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse saveOrUpdateDoctorProfilePhoto(CmsResponse cmsResponse, int doctorId,
			MultipartFile profilePicture, FileUploader fileUploader) {
		try {
			DoctorDto doctorDetails = (DoctorDto) doctorService.getById("DoctorDto", doctorId);
			if (profilePicture != null) {
				if (!profilePicture.getOriginalFilename().equalsIgnoreCase(doctorDetails.getProfilePhoto())) {
					if (fileUploader.uploadDoctorFiles(profilePicture, "Profile_Pic", doctorId)) {
						String profilePictureFileName = doctorId + "_" + profilePicture.getOriginalFilename();
						doctorDetails.setProfilePhoto(profilePictureFileName);
						doctorService.update(doctorDetails);
						log.info("Profile Photo: " + profilePictureFileName + " is Uploaded Successfully.");
						cmsResponse.setSuccess(true);
					} else {
						log.info("Fails to Upload Profile Picture");
						cmsResponse.setSuccess(false);
					}
				} else {
					cmsResponse.setSuccess(true);
					cmsResponse.setMessage("Already Uploaded");
					log.info("This Profile Picture Already Uploaded");
				}
			} else {
				cmsResponse.setSuccess(true);
				log.info("Profile Picture File is Empty.");
			}
		} catch (Exception e) {
			cmsResponse.setSuccess(false);
			e.printStackTrace();
		}
		return cmsResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllDoctorDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllDoctorDetails(CmsResponse cmsResponse) {
		List<DoctorDto> allDoctorDetails = (List<DoctorDto>) doctorService.getAll("DoctorDto");
		if (allDoctorDetails.size() > 0) {
			cmsResponse.setListObject(allDoctorDetails);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Doctor List is Empty");
			log.info("Doctor List is Empty");
		}
		return cmsResponse;
	}

	@PutMapping(path = "/updateDoctor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse updateDoctor(@RequestBody DoctorDto doctor, CmsResponse cmsResponse, Principal principal) {

		DoctorDto doctorDetails = (DoctorDto) doctorService.getById("DoctorDto", doctor.getDoctorId());
		if (doctorDetails != null) {
			doctorDetails.setDoctorName(doctor.getDoctorName());
			doctorDetails.setQualification(doctor.getQualification());
			doctorDetails.setSpecialization(doctor.getSpecialization());
			doctorDetails.setExperience(doctor.getExperience());
			doctorDetails.setDob(doctor.getDob());
			doctorDetails.setAge(doctor.getAge());
			doctorDetails.setGender(doctor.getGender());
			doctorDetails.setMorningVisitFrom(doctor.getMorningVisitFrom());
			doctorDetails.setMorningVisitTo(doctor.getMorningVisitTo());
			doctorDetails.setEveningVisitFrom(doctor.getEveningVisitFrom());
			doctorDetails.setEveningVisitTo(doctor.getEveningVisitTo());
			doctorDetails.setJoiningDate(doctor.getJoiningDate());
			doctorDetails.setLeavingDate(doctor.getLeavingDate());
			doctorDetails.setPhoneNumber(doctor.getPhoneNumber());
			doctorDetails.setEmailId(doctor.getEmailId());
			doctorDetails.setAadharNo(doctor.getAadharNo());
			doctorDetails.setAddress(doctor.getAddress());
			doctorDetails.setDoctorRole(doctor.getDoctorRole());
			doctorDetails.setPanNo(doctor.getPanNo());
			doctorDetails.setRegisterNo(doctor.getRegisterNo());
			doctorDetails.setInitialRegCost(doctor.getInitialRegCost());
			doctorDetails.setAmtToClinic(doctor.getAmtToClinic());
			doctorDetails.setClinicCost(doctor.getClinicCost());
			doctorDetails.setDoctorCost(doctor.getDoctorCost());
			doctorDetails.setFlatOrShareLabel(doctor.getFlatOrShareLabel());

			UserDto userDto = doctorService.getUserByName(principal.getName());
			doctorDetails.setUpdatedBy(userDto);
			doctorDetails.setUpdatedDate(AppUtil.currentDateWithTime());

			if (doctorService.update(doctorDetails)) {
				UserDto userDetails = (UserDto) userService.getById("UserDto", "userId",
						doctorDetails.getUser().getUserId());
				userDetails.setUpdatedDate(AppUtil.currentDateWithTime());
				userDetails.setDisplayName(doctor.getDoctorName());
				userDetails.setMobileNo(doctor.getPhoneNumber());
				userService.update(userDetails);

				cmsResponse.setSuccess(true);
				cmsResponse.setObject(doctorDetails);
				cmsResponse.setMessage("Updated Successfully");
				log.info("This Doctor Id: " + doctorDetails.getDoctorId() + " Updated Successfully");
			} else {
				cmsResponse.setSuccess(false);
				cmsResponse.setMessage("Fails to Update");
				log.info("Fails to Update");
			}
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("This Doctor Not Exist");
			log.info("This Doctor Id: " + doctor.getDoctorId() + " Not Exist");
		}
		return cmsResponse;
	}

//	@PutMapping(path = "/deleteDoctorDetails", produces = MediaType.APPLICATION_JSON_VALUE)
//	public CmsResponse deleteDoctorDetails(@RequestParam("doctorId") int doctorId, CmsResponse cmsResponse) {
//		DoctorDto doctorDetails = (DoctorDto) doctorService.getById("DoctorDto", doctorId);
//		if (doctorDetails != null) {
//			int userId =doctorDetails.getUser().getUserId();
//			UserDto userDetails = (UserDto) userService.getById("UserDto", "userId", userId);
//			userDetails.setActiveFlag(0);
//			userService.update(userDetails);
//			doctorDetails.setUpdatedDate(AppUtil.currentDateWithTime());
//			doctorDetails.setActiveFlag(0);
//			if (doctorService.update(doctorDetails)) {
//				cmsResponse.setSuccess(true);
//				cmsResponse.setMessage("Deleted Successfully");
//				log.info("This Doctor Id: " + doctorId + " Deleted Successfully");
//			} else {
////				doctorDetails.UndoAllCandIntervAndJobsByCompanyId(doctorDetails.getDoctorId());
//				cmsResponse.setSuccess(false);
//				cmsResponse.setMessage("Deletion Failed");
//				log.info("Deletion Failed");
//			}
//
//		} else {
//			cmsResponse.setSuccess(false);
//			cmsResponse.setMessage("This Doctor Not Exist");
//			log.info("This Doctor Id: " + doctorId + " Not Exist");
//		}
//		return cmsResponse;
//	}

	@PutMapping(path = "/deleteDoctorDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse deleteDoctorDetails(@RequestParam("doctorId") int doctorId, CmsResponse cmsResponse,
			Principal principal) {
		DoctorDto doctorDetails = (DoctorDto) doctorService.getById("DoctorDto", doctorId);
		if (doctorDetails != null) {
			if (doctorService.deleteAllAppointmentsByDoctorId(doctorDetails.getDoctorId())) {
				int userId = doctorDetails.getUser().getUserId();
				UserDto userDetails = (UserDto) userService.getById("UserDto", "userId", userId);
				userDetails.setActiveFlag(0);
				userService.update(userDetails);
				doctorDetails.setUpdatedDate(AppUtil.currentDateWithTime());
				doctorDetails.setActiveFlag(0);
				if (doctorService.update(doctorDetails)) {
					cmsResponse.setSuccess(true);
					cmsResponse.setMessage("Deleted Successfully");
					log.info("This Doctor Id: " + doctorId + " Deleted Successfully");
				} else {
					doctorService.UndoAllAppointmentsByDoctorId(doctorDetails.getDoctorId());
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
			cmsResponse.setMessage("This Doctor Not Exist");
			log.info("This Doctor Id: " + doctorId + " Not Exist");
		}
		return cmsResponse;
	}

	@RequestMapping(value = "/getProfileFile")
	public CmsResponse getProfileFile(@RequestParam("DoctorId") int DoctorId, CmsResponse cmsResponse) {
		try {
			DoctorDto doctorDetails = (DoctorDto) doctorService.getById("DoctorDto", DoctorId);
			if (doctorDetails != null) {
				String subFolderName = "Profile_Pic";
				Path rootPath = FileSystems.getDefault().getPath("").toAbsolutePath();
				String filePath = rootPath + File.separator + "Uploads" + File.separator + "doctors" + File.separator
						+ File.separator + subFolderName + File.separator + doctorDetails.getProfilePhoto();

				File file = new File(filePath);

				// Creating a byte array using the length of the file
				// file.length returns long which is cast to int
				byte[] byteArray = new byte[(int) file.length()];
				FileInputStream fis = null;

				fis = new FileInputStream(file);
				fis.read(byteArray);
				fis.close();

				cmsResponse.setObject(doctorDetails);
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

	@GetMapping(path = "/getDoctorDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getDoctorDetailsById(@PathVariable int id, CmsResponse cmsResponse) {
		DoctorDto doctor = (DoctorDto) doctorService.getById("DoctorDto", id);
		if (doctor != null) {
			cmsResponse.setObject(doctor);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Doctor Not Exist");
			log.info("This Doctor Id: " + id + " Not Exist");
		}
		return cmsResponse;
	}

	@GetMapping(path = "/getDoctorDetailsByUserId/{doctorId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getDoctorDetailsByUserId(@PathVariable int doctorId, CmsResponse cmsResponse) {
		DoctorDto doctor = (DoctorDto) doctorService.getByUserId("DoctorDto", doctorId);
		if (doctor != null) {
			cmsResponse.setObject(doctor);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Doctor Not Exist");
			log.info("This Doctor Id: " + doctorId + " Not Exist");
		}
		return cmsResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllDeletedDoctorDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllDeletedDoctorDetails(CmsResponse cmsResponse) {
		List<DoctorDto> allDoctorDetails = (List<DoctorDto>) doctorService.getAllDeleted("DoctorDto");
		if (allDoctorDetails.size() > 0) {
			cmsResponse.setListObject(allDoctorDetails);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Doctor List is Empty");
			log.info("Doctor List is Empty");
		}
		return cmsResponse;
	}

	@PutMapping(path = "/undoDoctorDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse undoDoctorDetails(@RequestParam("doctorId") int doctorId, CmsResponse cmsResponse) {
		DoctorDto doctorDetails = (DoctorDto) doctorService.getByIdForUndo("DoctorDto", doctorId);
		if (doctorDetails != null) {
			int userId = doctorDetails.getUser().getUserId();
			UserDto userDetails = (UserDto) userService.getByIdForUndo("UserDto", "userId", userId);
			userDetails.setActiveFlag(1);
			userService.update(userDetails);
			doctorDetails.setUpdatedDate(AppUtil.currentDateWithTime());
			doctorDetails.setActiveFlag(1);
			if (doctorService.update(doctorDetails)) {
				cmsResponse.setSuccess(true);
				cmsResponse.setMessage("Deleted Successfully");
				log.info("This Doctor Id: " + doctorId + " Deleted Successfully");
			} else {
//				doctorDetails.UndoAllCandIntervAndJobsByCompanyId(doctorDetails.getDoctorId());
				cmsResponse.setSuccess(false);
				cmsResponse.setMessage("Deletion Failed");
				log.info("Deletion Failed");
			}

		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("This Doctor Not Exist");
			log.info("This Doctor Id: " + doctorId + " Not Exist");
		}
		return cmsResponse;
	}
}
