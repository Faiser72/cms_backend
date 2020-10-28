package com.vetologic.cms.dto.appointment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vetologic.cms.dto.doctor.DoctorDto;
import com.vetologic.cms.dto.patient.PatientDto;
import com.vetologic.cms.dto.user.UserDto;

@Entity
@Table(name = "APPOINTMENT_DETAILS")
public class AppointmentDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "APPOINTMENT_ID")
	private int appointmentId;

	@Column(name = "ACTIVE_FLAG")
	private int activeFlag;

	@Column(name = "TESTED_FLAG")
	private int testedFlag;

	@Column(name = "TESTED_DATE")
	private String testedDate;

	@ManyToOne
	@JoinColumn(name = "PATIENT_ID")
	private PatientDto patientNumber;

	@Column(name = "PATIENT_NAME")
	private String patientName;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "APPOINTMENT_DATE")
	private String appointmentDate;

	@Column(name = "APPOINTMENT_TIME")
	private String appointmentTime;

	@ManyToOne
	@JoinColumn(name = "DOCTOR_ID")
	private DoctorDto doctorName;

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

	@Column(name = "DELETION_FLAG")
	private int deletionFlag;

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public int getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}

	public PatientDto getPatientNumber() {
		return patientNumber;
	}

	public void setPatientNumber(PatientDto patientNumber) {
		this.patientNumber = patientNumber;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public DoctorDto getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(DoctorDto doctorName) {
		this.doctorName = doctorName;
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

	public int getTestedFlag() {
		return testedFlag;
	}

	public void setTestedFlag(int testedFlag) {
		this.testedFlag = testedFlag;
	}

	public String getTestedDate() {
		return testedDate;
	}

	public void setTestedDate(String testedDate) {
		this.testedDate = testedDate;
	}

	public int getDeletionFlag() {
		return deletionFlag;
	}

	public void setDeletionFlag(int deletionFlag) {
		this.deletionFlag = deletionFlag;
	}

	@Override
	public String toString() {
		return "AppointmentDto [appointmentId=" + appointmentId + ", activeFlag=" + activeFlag + ", testedFlag="
				+ testedFlag + ", testedDate=" + testedDate + ", patientNumber=" + patientNumber + ", patientName="
				+ patientName + ", phoneNumber=" + phoneNumber + ", appointmentDate=" + appointmentDate
				+ ", appointmentTime=" + appointmentTime + ", doctorName=" + doctorName + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy
				+ ", deletionFlag=" + deletionFlag + "]";
	}

}
