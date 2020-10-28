package com.vetologic.cms.dto.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_TYPE")
public class UserTypeDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_TYPE_ID")
	private int userTypeId;

	@Column(name = "ACTIVE_FLAG")
	private int activeFlag;

	@Column(name = "ROLE_NAME")
	private String roleName;

	@Column(name = "ROLE")
	private String role;

	public int getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}

	public int getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserTypeDto [userTypeId=" + userTypeId + ", activeFlag=" + activeFlag + ", roleName=" + roleName
				+ ", role=" + role + "]";
	}

}
