package com.vetologic.cms.dto.prescription;

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
@Table(name = "PRESCRIPTION")
public class PrescriptionDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRESCRIPTION_ID")
	private int prescriptionId;

	@Column(name = "ACTIVE_FLAG")
	private int activeFlag;

	@ManyToOne
	@JoinColumn(name = "PATIENT_ID")
	private PatientDto patient;

	@Column(name = "DRUG_NAME")
	private String drugName;

	@Column(name = "STRENGTH")
	private String strength;

	@Column(name = "MORNING")
	private String morningDosage;

	@Column(name = "AFTERNOON")
	private String afternoonDosage;

	@Column(name = "NIGHT")
	private String nightDosage;

	@Column(name = "DURATION")
	private String duration;

	@Column(name = "REMARKS")
	private String remarks;

	@ManyToOne
	@JoinColumn(name = "DOCTOR_ID")
	private DoctorDto doctorName;

	@ManyToOne
	@JoinColumn(name = "APPOINTMENT_ID")
	private AppointmentDto appointment;

	@Column(name = "DATE")
	private String date;

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

	public int getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public int getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}

	public PatientDto getPatient() {
		return patient;
	}

	public void setPatient(PatientDto patient) {
		this.patient = patient;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getMorningDosage() {
		return morningDosage;
	}

	public void setMorningDosage(String morningDosage) {
		this.morningDosage = morningDosage;
	}

	public String getAfternoonDosage() {
		return afternoonDosage;
	}

	public void setAfternoonDosage(String afternoonDosage) {
		this.afternoonDosage = afternoonDosage;
	}

	public String getNightDosage() {
		return nightDosage;
	}

	public void setNightDosage(String nightDosage) {
		this.nightDosage = nightDosage;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public DoctorDto getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(DoctorDto doctorName) {
		this.doctorName = doctorName;
	}

	public AppointmentDto getAppointment() {
		return appointment;
	}

	public void setAppointment(AppointmentDto appointment) {
		this.appointment = appointment;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
		return "PrescriptionDto [prescriptionId=" + prescriptionId + ", activeFlag=" + activeFlag + ", patient="
				+ patient + ", drugName=" + drugName + ", strength=" + strength + ", morningDosage=" + morningDosage
				+ ", afternoonDosage=" + afternoonDosage + ", nightDosage=" + nightDosage + ", duration=" + duration
				+ ", remarks=" + remarks + ", doctorName=" + doctorName + ", appointment=" + appointment + ", date="
				+ date + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", createdBy=" + createdBy
				+ ", updatedBy=" + updatedBy + "]";
	}

}
