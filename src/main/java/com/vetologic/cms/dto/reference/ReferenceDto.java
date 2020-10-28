package com.vetologic.cms.dto.reference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vetologic.cms.dto.appointment.AppointmentDto;
import com.vetologic.cms.dto.doctor.DoctorDto;
import com.vetologic.cms.dto.patient.PatientDto;
import com.vetologic.cms.dto.user.UserDto;

@Entity
@Table(name = "REFERENCE")
public class ReferenceDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REFERENCE_ID")
	private int referenceId;

	@Column(name = "ACTIVE_FLAG")
	private int activeFlag;

	@ManyToOne
	@JoinColumn(name = "PATIENT_ID")
	private PatientDto patientId;

	@Column(name = "AGE")
	private String age;

	@Column(name = "DATE")
	private String date;

	@Column(name = "REFERED_BY")
	private String referedBy;

	@Column(name = "REMARKS")
	private String remarks;

	@ManyToOne
	@JoinColumn(name = "APPOINTMENT_ID")
	private AppointmentDto appointment;

	@ManyToOne
	@JoinColumn(name = "DOCTOR_ID")
	private DoctorDto doctorId;

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

	public int getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(int referenceId) {
		this.referenceId = referenceId;
	}

	public int getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}

	public PatientDto getPatientId() {
		return patientId;
	}

	public void setPatientId(PatientDto patientId) {
		this.patientId = patientId;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getReferedBy() {
		return referedBy;
	}

	public void setReferedBy(String referedBy) {
		this.referedBy = referedBy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public AppointmentDto getAppointment() {
		return appointment;
	}

	public void setAppointment(AppointmentDto appointment) {
		this.appointment = appointment;
	}

	public DoctorDto getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(DoctorDto doctorId) {
		this.doctorId = doctorId;
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
		return "ReferenceDto [referenceId=" + referenceId + ", activeFlag=" + activeFlag + ", patientId=" + patientId
				+ ", age=" + age + ", date=" + date + ", referedBy=" + referedBy + ", remarks=" + remarks
				+ ", appointment=" + appointment + ", doctorId=" + doctorId + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + "]";
	}

}
