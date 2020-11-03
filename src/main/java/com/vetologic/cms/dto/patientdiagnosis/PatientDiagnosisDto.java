package com.vetologic.cms.dto.patientdiagnosis;

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
@Table(name = "PATIENT_DIAGNOSIS")
public class PatientDiagnosisDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DIAGNOSIS_ID")
	private int diagnosisId;

	@Column(name = "ACTIVE_FLAG")
	private int activeFlag;

	@ManyToOne
	@JoinColumn(name = "PATIENT_ID")
	private PatientDto patient;

	@Column(name = "HEIGHT")
	private String height;

	@Column(name = "HEIGHT_UNITS")
	private String heightUnits;

	@Column(name = "WEIGHT")
	private String weight;

	@Column(name = "WEIGHT_UNITS")
	private String weightUnits;

	@Column(name = "BLOOD_PREASURE")
	private String bloodPreasure;

	@Column(name = "TEMPERATURE")
	private String temperature;

	@Column(name = "TEMPERATURE_UNITS")
	private String temperatureUnits;

	@Column(name = "THYROID")
	private String thyroid;

	@Column(name = "THYROID_REPORTS")
	private String thyroidReports;

	@Column(name = "DIAGNOSIS")
	private String diagnosis;

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

	@Column(name = "INVESTIGATION")
	private String investigation;

	@Column(name = "FOLLOW_UPDATE")
	private String followUpdate;

	public int getDiagnosisId() {
		return diagnosisId;
	}

	public void setDiagnosisId(int diagnosisId) {
		this.diagnosisId = diagnosisId;
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

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getHeightUnits() {
		return heightUnits;
	}

	public void setHeightUnits(String heightUnits) {
		this.heightUnits = heightUnits;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getWeightUnits() {
		return weightUnits;
	}

	public void setWeightUnits(String weightUnits) {
		this.weightUnits = weightUnits;
	}

	public String getBloodPreasure() {
		return bloodPreasure;
	}

	public void setBloodPreasure(String bloodPreasure) {
		this.bloodPreasure = bloodPreasure;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getTemperatureUnits() {
		return temperatureUnits;
	}

	public void setTemperatureUnits(String temperatureUnits) {
		this.temperatureUnits = temperatureUnits;
	}

	public String getThyroid() {
		return thyroid;
	}

	public void setThyroid(String thyroid) {
		this.thyroid = thyroid;
	}

	public String getThyroidReports() {
		return thyroidReports;
	}

	public void setThyroidReports(String thyroidReports) {
		this.thyroidReports = thyroidReports;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
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

	public String getInvestigation() {
		return investigation;
	}

	public void setInvestigation(String investigation) {
		this.investigation = investigation;
	}

	public String getFollowUpdate() {
		return followUpdate;
	}

	public void setFollowUpdate(String followUpdate) {
		this.followUpdate = followUpdate;
	}

	@Override
	public String toString() {
		return "PatientDiagnosisDto [diagnosisId=" + diagnosisId + ", activeFlag=" + activeFlag + ", patient=" + patient
				+ ", height=" + height + ", heightUnits=" + heightUnits + ", weight=" + weight + ", weightUnits="
				+ weightUnits + ", bloodPreasure=" + bloodPreasure + ", temperature=" + temperature
				+ ", temperatureUnits=" + temperatureUnits + ", thyroid=" + thyroid + ", thyroidReports="
				+ thyroidReports + ", diagnosis=" + diagnosis + ", doctorName=" + doctorName + ", appointment="
				+ appointment + ", date=" + date + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
				+ ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", investigation=" + investigation
				+ ", followUpdate=" + followUpdate + "]";
	}

}
