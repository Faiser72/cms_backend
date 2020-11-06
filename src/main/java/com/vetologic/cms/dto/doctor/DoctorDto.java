package com.vetologic.cms.dto.doctor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vetologic.cms.dto.user.UserDto;

@Entity
@Table(name = "DOCTOR_DETAILS")
public class DoctorDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DOCTOR_ID")
	private int doctorId;

	@Column(name = "ACTIVE_FLAG")
	private int activeFlag;

	@Column(name = "DOCTOR_NAME")
	private String doctorName;

	@Column(name = "QUALIFICATION")
	private String qualification;

	@Column(name = "SPECIALIZATION")
	private String specialization;

	@Column(name = "PROFILE_PHOTO")
	private String profilePhoto;

	@Column(name = "DOB")
	private String dob;

	@Column(name = "AGE")
	private String age;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "EXPERIENCE")
	private String experience;

	@Column(name = "JOINING_DATE")
	private String joiningDate;

	@Column(name = "LEAVING_DATE")
	private String leavingDate;

	@Column(name = "MORNING_VISIT_FROM")
	private String morningVisitFrom;

	@Column(name = "MORNING_VISIT_TO")
	private String morningVisitTo;

	@Column(name = "EVENING_VISIT_FROM")
	private String eveningVisitFrom;

	@Column(name = "EVENING_VISIT_TO")
	private String eveningVisitTo;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "EMAIL_ID")
	private String emailId;

	@Column(name = "PAN_NO")
	private String panNo;

	@Column(name = "AADHAR_NO")
	private String aadharNo;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private UserDto user;

	@ManyToOne
	@JoinColumn(name = "DOCTOR_ROLE_ID")
	private DoctorRoleMasterDto doctorRole;

	@Column(name = "CREATED_DATE")
	private String createdDate;

	@Column(name = "UPDATED_DATE")
	private String updatedDate;

	@ManyToOne
	@JoinColumn(name = "CREATED_BY")
	private UserDto createdBy;

	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	private UserDto updatedBy;

	@Column(name = "REGISTER_NO")
	private String registerNo;

	@Column(name = "AMT_TO_CLINIC")
	private String amtToClinic;

	@Column(name = "CLINIC_COST")
	private String clinicCost;

	@Column(name = "INITIAL_REG_COST")
	private String initialRegCost;

	@Column(name = "DOCTOR_COST")
	private String doctorCost;

	@Column(name = "FLAT_OR_SHARE_LABEL")
	private String flatOrShareLabel;

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public int getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getLeavingDate() {
		return leavingDate;
	}

	public void setLeavingDate(String leavingDate) {
		this.leavingDate = leavingDate;
	}

	public String getMorningVisitFrom() {
		return morningVisitFrom;
	}

	public void setMorningVisitFrom(String morningVisitFrom) {
		this.morningVisitFrom = morningVisitFrom;
	}

	public String getMorningVisitTo() {
		return morningVisitTo;
	}

	public void setMorningVisitTo(String morningVisitTo) {
		this.morningVisitTo = morningVisitTo;
	}

	public String getEveningVisitFrom() {
		return eveningVisitFrom;
	}

	public void setEveningVisitFrom(String eveningVisitFrom) {
		this.eveningVisitFrom = eveningVisitFrom;
	}

	public String getEveningVisitTo() {
		return eveningVisitTo;
	}

	public void setEveningVisitTo(String eveningVisitTo) {
		this.eveningVisitTo = eveningVisitTo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public DoctorRoleMasterDto getDoctorRole() {
		return doctorRole;
	}

	public void setDoctorRole(DoctorRoleMasterDto doctorRole) {
		this.doctorRole = doctorRole;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public UserDto getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserDto createdBy) {
		this.createdBy = createdBy;
	}

	public UserDto getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(UserDto updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getRegisterNo() {
		return registerNo;
	}

	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
	}

	public String getAmtToClinic() {
		return amtToClinic;
	}

	public void setAmtToClinic(String amtToClinic) {
		this.amtToClinic = amtToClinic;
	}

	public String getClinicCost() {
		return clinicCost;
	}

	public void setClinicCost(String clinicCost) {
		this.clinicCost = clinicCost;
	}

	public String getInitialRegCost() {
		return initialRegCost;
	}

	public void setInitialRegCost(String initialRegCost) {
		this.initialRegCost = initialRegCost;
	}

	public String getDoctorCost() {
		return doctorCost;
	}

	public void setDoctorCost(String doctorCost) {
		this.doctorCost = doctorCost;
	}

	public String getFlatOrShareLabel() {
		return flatOrShareLabel;
	}

	public void setFlatOrShareLabel(String flatOrShareLabel) {
		this.flatOrShareLabel = flatOrShareLabel;
	}

	@Override
	public String toString() {
		return "DoctorDto [doctorId=" + doctorId + ", activeFlag=" + activeFlag + ", doctorName=" + doctorName
				+ ", qualification=" + qualification + ", specialization=" + specialization + ", profilePhoto="
				+ profilePhoto + ", dob=" + dob + ", age=" + age + ", gender=" + gender + ", experience=" + experience
				+ ", joiningDate=" + joiningDate + ", leavingDate=" + leavingDate + ", morningVisitFrom="
				+ morningVisitFrom + ", morningVisitTo=" + morningVisitTo + ", eveningVisitFrom=" + eveningVisitFrom
				+ ", eveningVisitTo=" + eveningVisitTo + ", address=" + address + ", phoneNumber=" + phoneNumber
				+ ", emailId=" + emailId + ", panNo=" + panNo + ", aadharNo=" + aadharNo + ", user=" + user
				+ ", doctorRole=" + doctorRole + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
				+ ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", registerNo=" + registerNo
				+ ", amtToClinic=" + amtToClinic + ", clinicCost=" + clinicCost + ", initialRegCost=" + initialRegCost
				+ ", doctorCost=" + doctorCost + ", flatOrShareLabel=" + flatOrShareLabel + "]";
	}

}
