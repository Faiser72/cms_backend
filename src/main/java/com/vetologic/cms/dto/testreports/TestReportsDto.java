package com.vetologic.cms.dto.testreports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vetologic.cms.dto.patientdiagnosis.PatientDiagnosisDto;

@Entity
@Table(name = "TEST_REPORTS")
public class TestReportsDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TEST_REPORTS_ID")
	private int testReportsId;

	@Column(name = "ACTIVE_FLAG")
	private int activeFlag;

	@ManyToOne
	@JoinColumn(name = "DIAGNOSIS_ID")
	private PatientDiagnosisDto diagnosisId;

	@Column(name = "TEST_NAME")
	private String testName;

	@Column(name = "TEST_REPORT_FILE_NAME")
	private String testReportFileName;

	public int getTestReportsId() {
		return testReportsId;
	}

	public void setTestReportsId(int testReportsId) {
		this.testReportsId = testReportsId;
	}

	public int getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}

	public PatientDiagnosisDto getDiagnosisId() {
		return diagnosisId;
	}

	public void setDiagnosisId(PatientDiagnosisDto diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestReportFileName() {
		return testReportFileName;
	}

	public void setTestReportFileName(String testReportFileName) {
		this.testReportFileName = testReportFileName;
	}

	@Override
	public String toString() {
		return "TestReportsDto [testReportsId=" + testReportsId + ", activeFlag=" + activeFlag + ", diagnosisId="
				+ diagnosisId + ", testName=" + testName + ", testReportFileName=" + testReportFileName + "]";
	}

}
