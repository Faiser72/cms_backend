package com.vetologic.cms.dto.labtest;

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
@Table(name = "LAB_TEST")
public class LabTestDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LAB_TEST_ID")
	private int labTestId;

	@Column(name = "ACTIVE_FLAG")
	private int activeFlag;

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

	@ManyToOne
	@JoinColumn(name = "PATIENT_ID")
	private PatientDto patient;

	@ManyToOne
	@JoinColumn(name = "APPOINTMENT_ID")
	private AppointmentDto appointment;

	@ManyToOne
	@JoinColumn(name = "DOCTOR_ID")
	private DoctorDto doctor;

	@Column(name = "COMPLETEHEAMOGRAM")
	private String completeheamogram;

	@Column(name = "BLOODGROUPRHTYPE")
	private String bloodGrouprhtype;

	@Column(name = "POSTPRANDIALBLOODSUGAR")
	private String postPrandialBloodSugar;

	@Column(name = "BLOODUREA")
	private String bloodUrea;

	@Column(name = "BLOODUREANITROGEN")
	private String bloodUreaNitrogen;

	@Column(name = "SERUMCREATININE")
	private String serumCreatinine;

	@Column(name = "URICACID")
	private String uricAcid;

	@Column(name = "LIPIDPROFILE")
	private String lipidProfile;

	@Column(name = "LIVERFUNCTIONTEST")
	private String liverFunctionTest;

	@Column(name = "TSH")
	private String tsh;

	@Column(name = "SERUMCALCIUM")
	private String serumCalcium;

	@Column(name = "HIVELISA")
	private String hivElisa;

	@Column(name = "HBSAGELISA")
	private String hbsagElisa;

	@Column(name = "URINEROUTINE")
	private String urineRoutine;

	@Column(name = "CHESTXRAY")
	private String chestXRay;

	@Column(name = "ECHOCARDIOGRAM")
	private String Echocardiogram;

	@Column(name = "TREADMILLTEST")
	private String treadmillTest;

	@Column(name = "ULTRASOUNDABDOMENANDPELVIS")
	private String ultraSoundAbdomenAndPelvis;

	@Column(name = "URINECOMPLETEANALYSIS")
	private String urineCompleteAnalysis;

	@Column(name = "ECG")
	private String ecg;

	@Column(name = "ESR")
	private String esr;

	@Column(name = "ASLOQUANTITATIVE")
	private String asloQuantitative;

	@Column(name = "RAQUANTITATIVE")
	private String raQuantitative;

	@Column(name = "CRPQUANTITATIVE")
	private String crpQuantitative;

	@Column(name = "ANAELISA")
	private String anaElisa;

	@Column(name = "LH")
	private String lh;

	@Column(name = "PROLACTIN")
	private String prolactin;

	@Column(name = "FSHLHRATIO")
	private String fshLHRatio;

	@Column(name = "GLYCATEDHB")
	private String glycatedHb;

	@Column(name = "ELECTROLYTES")
	private String electrolytes;

	@Column(name = "DATE")
	private String date;

	@Column(name = "PROBLEM_SUSPECTED")
	private String problemSuspected;

	public int getLabTestId() {
		return labTestId;
	}

	public void setLabTestId(int labTestId) {
		this.labTestId = labTestId;
	}

	public int getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
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

	public PatientDto getPatient() {
		return patient;
	}

	public void setPatient(PatientDto patient) {
		this.patient = patient;
	}

	public AppointmentDto getAppointment() {
		return appointment;
	}

	public void setAppointment(AppointmentDto appointment) {
		this.appointment = appointment;
	}

	public DoctorDto getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorDto doctor) {
		this.doctor = doctor;
	}

	public String getCompleteheamogram() {
		return completeheamogram;
	}

	public void setCompleteheamogram(String completeheamogram) {
		this.completeheamogram = completeheamogram;
	}

	public String getBloodGrouprhtype() {
		return bloodGrouprhtype;
	}

	public void setBloodGrouprhtype(String bloodGrouprhtype) {
		this.bloodGrouprhtype = bloodGrouprhtype;
	}

	public String getPostPrandialBloodSugar() {
		return postPrandialBloodSugar;
	}

	public void setPostPrandialBloodSugar(String postPrandialBloodSugar) {
		this.postPrandialBloodSugar = postPrandialBloodSugar;
	}

	public String getBloodUrea() {
		return bloodUrea;
	}

	public void setBloodUrea(String bloodUrea) {
		this.bloodUrea = bloodUrea;
	}

	public String getBloodUreaNitrogen() {
		return bloodUreaNitrogen;
	}

	public void setBloodUreaNitrogen(String bloodUreaNitrogen) {
		this.bloodUreaNitrogen = bloodUreaNitrogen;
	}

	public String getSerumCreatinine() {
		return serumCreatinine;
	}

	public void setSerumCreatinine(String serumCreatinine) {
		this.serumCreatinine = serumCreatinine;
	}

	public String getUricAcid() {
		return uricAcid;
	}

	public void setUricAcid(String uricAcid) {
		this.uricAcid = uricAcid;
	}

	public String getLipidProfile() {
		return lipidProfile;
	}

	public void setLipidProfile(String lipidProfile) {
		this.lipidProfile = lipidProfile;
	}

	public String getLiverFunctionTest() {
		return liverFunctionTest;
	}

	public void setLiverFunctionTest(String liverFunctionTest) {
		this.liverFunctionTest = liverFunctionTest;
	}

	public String getTsh() {
		return tsh;
	}

	public void setTsh(String tsh) {
		this.tsh = tsh;
	}

	public String getSerumCalcium() {
		return serumCalcium;
	}

	public void setSerumCalcium(String serumCalcium) {
		this.serumCalcium = serumCalcium;
	}

	public String getHivElisa() {
		return hivElisa;
	}

	public void setHivElisa(String hivElisa) {
		this.hivElisa = hivElisa;
	}

	public String getHbsagElisa() {
		return hbsagElisa;
	}

	public void setHbsagElisa(String hbsagElisa) {
		this.hbsagElisa = hbsagElisa;
	}

	public String getUrineRoutine() {
		return urineRoutine;
	}

	public void setUrineRoutine(String urineRoutine) {
		this.urineRoutine = urineRoutine;
	}

	public String getChestXRay() {
		return chestXRay;
	}

	public void setChestXRay(String chestXRay) {
		this.chestXRay = chestXRay;
	}

	public String getEchocardiogram() {
		return Echocardiogram;
	}

	public void setEchocardiogram(String echocardiogram) {
		Echocardiogram = echocardiogram;
	}

	public String getTreadmillTest() {
		return treadmillTest;
	}

	public void setTreadmillTest(String treadmillTest) {
		this.treadmillTest = treadmillTest;
	}

	public String getUltraSoundAbdomenAndPelvis() {
		return ultraSoundAbdomenAndPelvis;
	}

	public void setUltraSoundAbdomenAndPelvis(String ultraSoundAbdomenAndPelvis) {
		this.ultraSoundAbdomenAndPelvis = ultraSoundAbdomenAndPelvis;
	}

	public String getUrineCompleteAnalysis() {
		return urineCompleteAnalysis;
	}

	public void setUrineCompleteAnalysis(String urineCompleteAnalysis) {
		this.urineCompleteAnalysis = urineCompleteAnalysis;
	}

	public String getEcg() {
		return ecg;
	}

	public void setEcg(String ecg) {
		this.ecg = ecg;
	}

	public String getEsr() {
		return esr;
	}

	public void setEsr(String esr) {
		this.esr = esr;
	}

	public String getAsloQuantitative() {
		return asloQuantitative;
	}

	public void setAsloQuantitative(String asloQuantitative) {
		this.asloQuantitative = asloQuantitative;
	}

	public String getRaQuantitative() {
		return raQuantitative;
	}

	public void setRaQuantitative(String raQuantitative) {
		this.raQuantitative = raQuantitative;
	}

	public String getCrpQuantitative() {
		return crpQuantitative;
	}

	public void setCrpQuantitative(String crpQuantitative) {
		this.crpQuantitative = crpQuantitative;
	}

	public String getAnaElisa() {
		return anaElisa;
	}

	public void setAnaElisa(String anaElisa) {
		this.anaElisa = anaElisa;
	}

	public String getLh() {
		return lh;
	}

	public void setLh(String lh) {
		this.lh = lh;
	}

	public String getProlactin() {
		return prolactin;
	}

	public void setProlactin(String prolactin) {
		this.prolactin = prolactin;
	}

	public String getFshLHRatio() {
		return fshLHRatio;
	}

	public void setFshLHRatio(String fshLHRatio) {
		this.fshLHRatio = fshLHRatio;
	}

	public String getGlycatedHb() {
		return glycatedHb;
	}

	public void setGlycatedHb(String glycatedHb) {
		this.glycatedHb = glycatedHb;
	}

	public String getElectrolytes() {
		return electrolytes;
	}

	public void setElectrolytes(String electrolytes) {
		this.electrolytes = electrolytes;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getProblemSuspected() {
		return problemSuspected;
	}

	public void setProblemSuspected(String problemSuspected) {
		this.problemSuspected = problemSuspected;
	}

	@Override
	public String toString() {
		return "LabTestDto [labTestId=" + labTestId + ", activeFlag=" + activeFlag + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy
				+ ", patient=" + patient + ", appointment=" + appointment + ", doctor=" + doctor
				+ ", completeheamogram=" + completeheamogram + ", bloodGrouprhtype=" + bloodGrouprhtype
				+ ", postPrandialBloodSugar=" + postPrandialBloodSugar + ", bloodUrea=" + bloodUrea
				+ ", bloodUreaNitrogen=" + bloodUreaNitrogen + ", serumCreatinine=" + serumCreatinine + ", uricAcid="
				+ uricAcid + ", lipidProfile=" + lipidProfile + ", liverFunctionTest=" + liverFunctionTest + ", tsh="
				+ tsh + ", serumCalcium=" + serumCalcium + ", hivElisa=" + hivElisa + ", hbsagElisa=" + hbsagElisa
				+ ", urineRoutine=" + urineRoutine + ", chestXRay=" + chestXRay + ", Echocardiogram=" + Echocardiogram
				+ ", treadmillTest=" + treadmillTest + ", ultraSoundAbdomenAndPelvis=" + ultraSoundAbdomenAndPelvis
				+ ", urineCompleteAnalysis=" + urineCompleteAnalysis + ", ecg=" + ecg + ", esr=" + esr
				+ ", asloQuantitative=" + asloQuantitative + ", raQuantitative=" + raQuantitative + ", crpQuantitative="
				+ crpQuantitative + ", anaElisa=" + anaElisa + ", lh=" + lh + ", prolactin=" + prolactin
				+ ", fshLHRatio=" + fshLHRatio + ", glycatedHb=" + glycatedHb + ", electrolytes=" + electrolytes
				+ ", date=" + date + ", problemSuspected=" + problemSuspected + "]";
	}

}
