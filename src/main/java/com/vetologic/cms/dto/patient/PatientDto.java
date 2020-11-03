package com.vetologic.cms.dto.patient;

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
@Table(name = "PATIENT_DETAILS")
public class PatientDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PATIENT_ID")
	private int patientId;

	@Column(name = "ACTIVE_FLAG")
	private int activeFlag;

	@Column(name = "PATIENT_NUMBER")
	private String patientNumber;

	@Column(name = "PATIENT_NAME")
	private String patientName;

	@Column(name = "DOB")
	private String dob;

	@Column(name = "AGE")
	private String age;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "EMAIL_ID")
	private String emailId;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "REASON_FOR_VISIT")
	private String reasonForVisit;

	@Column(name = "REGISTERED_DATE")
	private String registeredDate;

	@Column(name = "EMERGENCY_CONTACT_NAME")
	private String emergencyContactName;

	@Column(name = "EMERGENCY_CONTACT_NUMBER")
	private String emergencyContactNumber;

	@Column(name = "EMERGENCY_CONTACT_RELATION")
	private String emergencyContactRelation;

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

	@Column(name = "WHATSAPP_NUMBER")
	private String whatsAppNumber;

	public String getWhatsAppNumber() {
		return whatsAppNumber;
	}

	public void setWhatsAppNumber(String whatsAppNumber) {
		this.whatsAppNumber = whatsAppNumber;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getPatientNumber() {
		return patientNumber;
	}

	public void setPatientNumber(String patientNumber) {
		this.patientNumber = patientNumber;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReasonForVisit() {
		return reasonForVisit;
	}

	public void setReasonForVisit(String reasonForVisit) {
		this.reasonForVisit = reasonForVisit;
	}

	public String getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(String registeredDate) {
		this.registeredDate = registeredDate;
	}

	public String getEmergencyContactName() {
		return emergencyContactName;
	}

	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}

	public String getEmergencyContactNumber() {
		return emergencyContactNumber;
	}

	public void setEmergencyContactNumber(String emergencyContactNumber) {
		this.emergencyContactNumber = emergencyContactNumber;
	}

	public String getEmergencyContactRelation() {
		return emergencyContactRelation;
	}

	public void setEmergencyContactRelation(String emergencyContactRelation) {
		this.emergencyContactRelation = emergencyContactRelation;
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

	@Override
	public String toString() {
		return "PatientDto [patientId=" + patientId + ", activeFlag=" + activeFlag + ", patientNumber=" + patientNumber
				+ ", patientName=" + patientName + ", dob=" + dob + ", age=" + age + ", gender=" + gender
				+ ", phoneNumber=" + phoneNumber + ", emailId=" + emailId + ", address=" + address + ", reasonForVisit="
				+ reasonForVisit + ", registeredDate=" + registeredDate + ", emergencyContactName="
				+ emergencyContactName + ", emergencyContactNumber=" + emergencyContactNumber
				+ ", emergencyContactRelation=" + emergencyContactRelation + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy
				+ ", whatsAppNumber=" + whatsAppNumber + "]";
	}

}
