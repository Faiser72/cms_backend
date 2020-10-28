package com.vetologic.cms.dto.receipt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vetologic.cms.dto.appointment.AppointmentDto;
import com.vetologic.cms.dto.user.UserDto;

@Entity
@Table(name = "RECEIPT_DETAILS")
public class ReceiptDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BILL_ID")
	private int billId;

	@Column(name = "ACTIVE_FLAG")
	private int activeFlag;

	@Column(name = "GENERATED_FLAG")
	private int generatedFlag;

	@ManyToOne
	@JoinColumn(name = "APPOINTMENT_ID")
	private AppointmentDto appointment;

	@Column(name = "SERVICE_NAME")
	private String serviceName;

	@Column(name = "AMOUNT_IN_WORDS")
	private String amountInWords;

	@Column(name = "AMOUNT")
	private double amount;

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

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}

	public int getGeneratedFlag() {
		return generatedFlag;
	}

	public void setGeneratedFlag(int generatedFlag) {
		this.generatedFlag = generatedFlag;
	}

	public AppointmentDto getAppointment() {
		return appointment;
	}

	public void setAppointment(AppointmentDto appointment) {
		this.appointment = appointment;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getAmountInWords() {
		return amountInWords;
	}

	public void setAmountInWords(String amountInWords) {
		this.amountInWords = amountInWords;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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
		return "ReceiptDto [billId=" + billId + ", activeFlag=" + activeFlag + ", generatedFlag=" + generatedFlag
				+ ", appointment=" + appointment + ", serviceName=" + serviceName + ", amountInWords=" + amountInWords
				+ ", amount=" + amount + ", date=" + date + ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + "]";
	}

}
