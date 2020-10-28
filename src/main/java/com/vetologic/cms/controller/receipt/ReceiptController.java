package com.vetologic.cms.controller.receipt;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vetologic.cms.dto.receipt.ReceiptDto;
import com.vetologic.cms.dto.response.CmsResponse;
import com.vetologic.cms.dto.user.UserDto;
import com.vetologic.cms.model.service.receipt.ReceiptService;
import com.vetologic.cms.utils.AppUtil;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("admin/receipt")
public class ReceiptController {

	private static Logger log = LoggerFactory.getLogger(ReceiptController.class);

	@Autowired
	private ReceiptService receiptService;

	@PostMapping(path = "/addReceipt", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse saveReceiptDetails(@RequestBody ReceiptDto receipt, CmsResponse cmsResponse,
			Principal principal) {
		ReceiptDto receiptDetails = (ReceiptDto) receiptService.getByAppointmentId("ReceiptDto",
				receipt.getAppointment().getAppointmentId());
		if (receiptDetails != null) {
			receiptDetails.setAmount(receipt.getAmount());
			receiptDetails.setAmountInWords(receipt.getAmountInWords());
			receiptDetails.setAppointment(receipt.getAppointment());
			receiptDetails.setDate(receipt.getDate());
			receiptDetails.setServiceName(receipt.getServiceName());

			UserDto userDto = receiptService.getUserByName(principal.getName());
			receiptDetails.setUpdatedBy(userDto);
			receiptDetails.setUpdatedDate(AppUtil.currentDateWithTime());

			if (receiptService.update(receiptDetails)) {
				cmsResponse.setSuccess(true);
				cmsResponse.setObject(receiptDetails);
				cmsResponse.setMessage("Updated Sucessfully");
				log.info("This Receipt Id: " + receiptDetails.getBillId() + " Updated Sucessfully");
			} else {
				cmsResponse.setSuccess(false);
				cmsResponse.setMessage("Fails to Update");
				log.info("Fails to Update");
			}
		} else {
			receipt.setActiveFlag(1);
			receipt.setGeneratedFlag(0);
			UserDto userDto = receiptService.getUserByName(principal.getName());
			receipt.setCreatedBy(userDto);
			receipt.setCreatedDate(AppUtil.currentDateWithTime());
			int id = receiptService.save(receipt);
			if (id != 0) {
				receipt.setBillId(id);
				cmsResponse.setObject(receipt);
				cmsResponse.setSuccess(true);
				cmsResponse.setMessage("Saved Sucessfully");
				log.info("Saved Sucessfully & Saved Receipt Id is: " + id);
			} else {
				cmsResponse.setSuccess(false);
				cmsResponse.setMessage("Saved UnSucessfully");
				log.info("Saved UnSucessfully");
			}
		}

		return cmsResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllReceiptDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getAllReceiptDetails(CmsResponse cmsResponse) {
		List<ReceiptDto> allReceiptDetails = (List<ReceiptDto>) receiptService.getAll("ReceiptDto");
		if (allReceiptDetails.size() > 0) {
			cmsResponse.setListObject(allReceiptDetails);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("Receipt List is Empty");
			log.info("Receipt List is Empty");
		}
		return cmsResponse;
	}

	@PutMapping(path = "/updateReceipt", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse updateReceipt(@RequestBody ReceiptDto receipt, CmsResponse cmsResponse, Principal principal) {

		ReceiptDto receiptDetails = (ReceiptDto) receiptService.getById("ReceiptDto", receipt.getBillId());
		if (receiptDetails != null) {
			receiptDetails.setAmount(receipt.getAmount());
			receiptDetails.setAmountInWords(receipt.getAmountInWords());
			receiptDetails.setAppointment(receipt.getAppointment());
			receiptDetails.setDate(receipt.getDate());
			receiptDetails.setServiceName(receipt.getServiceName());

			UserDto userDto = receiptService.getUserByName(principal.getName());
			receiptDetails.setUpdatedBy(userDto);
			receiptDetails.setUpdatedDate(AppUtil.currentDateWithTime());

			if (receiptService.update(receiptDetails)) {
				cmsResponse.setSuccess(true);
				cmsResponse.setObject(receiptDetails);
				cmsResponse.setMessage("Updated Sucessfully");
				log.info("This Receipt Id: " + receiptDetails.getBillId() + " Updated Sucessfully");
			} else {
				cmsResponse.setSuccess(false);
				cmsResponse.setMessage("Fails to Update");
				log.info("Fails to Update");
			}
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("This Receipt Not Exist");
			log.info("This Receipt Id: " + receipt.getBillId() + " Not Exist");
		}
		return cmsResponse;
	}

	@PutMapping(path = "/deleteReceiptDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse deleteReceiptDetails(@RequestParam("billId") int billId, CmsResponse cmsResponse) {
		ReceiptDto receiptDetails = (ReceiptDto) receiptService.getById("ReceiptDto", billId);
		if (receiptDetails != null) {
			receiptDetails.setUpdatedDate(AppUtil.currentDateWithTime());
			receiptDetails.setActiveFlag(0);
			if (receiptService.update(receiptDetails)) {
				cmsResponse.setSuccess(true);
				cmsResponse.setMessage("Deleted Sucessfully");
				log.info("This Receipt Id: " + billId + " Deleted Sucessfully");
			} else {
//				patientDetails.UndoAllCandIntervAndJobsByCompanyId(patientDetails.getPatientId());
				cmsResponse.setSuccess(false);
				cmsResponse.setMessage("Deletion Failed");
				log.info("Deletion Failed");
			}

		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("This Receipt Not Exist");
			log.info("This Receipt Id: " + billId + " Not Exist");
		}
		return cmsResponse;
	}

	@GetMapping(path = "/getReceiptDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getReceiptDetailsById(@PathVariable int id, CmsResponse cmsResponse) {
		ReceiptDto receipt = (ReceiptDto) receiptService.getById("ReceiptDto", id);
		if (receipt != null) {
			cmsResponse.setObject(receipt);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("receipt Not Exist");
			log.info("This receipt Id: " + id + " Not Exist");
		}
		return cmsResponse;
	}

	@GetMapping(path = "/getReceiptDetailsByAppointmentId/{appointmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getReceiptDetailsByAppointmentId(@PathVariable int appointmentId, CmsResponse cmsResponse) {
		ReceiptDto receipt = (ReceiptDto) receiptService.getByAppointmentId("ReceiptDto", appointmentId);
		if (receipt != null) {
			cmsResponse.setObject(receipt);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("receipt Not Exist");
			log.info("This receipt Id: " + appointmentId + " Not Exist");
		}
		return cmsResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getReceiptDetailsBtwnDates/{fromDate}/{toDate}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CmsResponse getReceiptDetailsBtwnDates(@PathVariable String fromDate, @PathVariable String toDate,
			CmsResponse cmsResponse) {
		List<ReceiptDto> receipt = (List<ReceiptDto>) receiptService.getAllBtwnTwoDates("ReceiptDto", fromDate, toDate);
		if (receipt.size() > 0) {
			cmsResponse.setListObject(receipt);
			cmsResponse.setSuccess(true);
		} else {
			cmsResponse.setSuccess(false);
			cmsResponse.setMessage("receipt Not Exist");
			log.info("This receipt Id:  Not Exist");
		}
		return cmsResponse;
	}
}
