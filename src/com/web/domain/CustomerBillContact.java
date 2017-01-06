package com.web.domain;

import lombok.Data;

@Data
public class CustomerBillContact {
	
	private int id;
	private int customerId;
	private String customerName;
	private String billAttention;
	private String billTelephone;
	private String billMobile;
	private String billFax;
	private String billEmail;
	private String billAddress1;
	private String billAddress2;
	private String billAddress3;
	private int billPostcode;
}
