package com.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.web.domain.Customer;
import com.web.service.CustomerService;

@Controller
public class CustomerController extends BaseController{
	
	Logger log = Logger.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="/customerMain.htm")
	public ModelAndView customerMain() {
		Map model = new HashMap<>();
		List customerList = customerService.loadCustomerList();
		model.put("list", customerList);
		return new ModelAndView("customer/customer_main","model",model);
	}
	
	@RequestMapping(value="/customerDetails.htm")
	public ModelAndView customerDetails() {
		return new ModelAndView("customer/customer_details");
	}
	
	@ResponseBody
	@RequestMapping(value="/saveCustomer.htm")
	public String saveCustomer(Customer customer) {
		log.debug("save customer = " + customer);
		try {
			customer = customerService.saveCustomer(customer);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		customerService.updateCustomerBillContact(customer.getBillContact());
		customerService.updateCustomerDeliveryContact(customer.getDeliveryContact());
		return "{'info':'save info', 'status':'y'}";
	}
	
	@ResponseBody
	@RequestMapping(value="/checkDuplicateName.htm")
	public String checkDuplicateName(HttpServletRequest req) {
		log.debug("checkDuplicateName.........." + req.getParameter("param"));
//		req.getParameter("param") 这个参数可以得到从页面传来的参数
		String customerName = req.getParameter("param");
		try {
			boolean exist = customerService.checkDuplicateCustomerName(customerName);
			if (exist) {
				return "Customer Name Exist";
			} 
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		// return "y" if correct return y
		return "y";
	}
}
