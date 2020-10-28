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
@Table(name = "DOCTOR_ROLE_MASTER")
public class DoctorRoleMasterDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DOCTOR_ROLE_ID")
	private int doctorRoleId;

	@Column(name = "ACTIVE_FLAG")
	private int activeFlag;

	@Column(name = "DOCTOR_ROLE_NAME")
	private String doctorRoleName;

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

	public int getDoctorRoleId() {
		return doctorRoleId;
	}

	public void setDoctorRoleId(int doctorRoleId) {
		this.doctorRoleId = doctorRoleId;
	}

	public int getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getDoctorRoleName() {
		return doctorRoleName;
	}

	public void setDoctorRoleName(String doctorRoleName) {
		this.doctorRoleName = doctorRoleName;
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
		return "DoctorRoleMasterDto [doctorRoleId=" + doctorRoleId + ", activeFlag=" + activeFlag + ", doctorRoleName="
				+ doctorRoleName + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", createdBy="
				+ createdBy + ", updatedBy=" + updatedBy + "]";
	}

}
