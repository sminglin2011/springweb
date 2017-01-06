package com.web.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.sun.swing.internal.plaf.metal.resources.metal;
import com.web.Dao.CustomerDao;
import com.web.domain.Customer;
import com.web.domain.CustomerBillContact;
import com.web.domain.User;
import com.web.exception.ParameterException;
import com.web.service.CustomerService;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

@Controller
public class CustomerController { //extends BaseController
	
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
			e.printStackTrace();
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
	
	@RequestMapping(value="/customerContactMaster.htm")
	public ModelAndView customerContactMaster(String customerId) {
		log.debug("customerContactMaster come " + customerId);
		int custId = 0;
		try {
			custId = Integer.parseInt(customerId);
		} catch (ParameterException e) {
			log.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		List billContactList = customerService.loadCustomerBillContactList(custId);
		List deliveryContactList = customerService.loadCustomerDeliveryContactList(custId);
		Customer customer = customerService.loadCustomer(customerId);
		Map model = new HashMap<>();
		model.put("billContactList", billContactList);
		model.put("deliveryContactList", deliveryContactList);
		model.put("customer", customer);
		log.debug(billContactList);
		log.debug(deliveryContactList);
		log.debug(customer);
		
		return new ModelAndView("customer/contact_main","model", model);
	}
	
	@RequestMapping(value="/newCustomerBillContact.htm")
	public ModelAndView newCustomerBillContact(String customerId) {
		log.debug("new Customer Bill contact customer id = " + customerId);
		Map model = new HashMap<>();
		Customer customer = customerService.loadCustomer(customerId);
		model.put("customer", customer);
		model.put("billContact", new CustomerBillContact());
		return new ModelAndView("customer/new_customerBillContact", "model", model);
	}
	@RequestMapping(value="/newCustomerDeliveryContact.htm")
	public ModelAndView newCustomerDeliveryContact(String customerId) {
		log.debug("??????????");
		Map model = new HashMap<>();
		Customer customer = customerService.loadCustomer(customerId);
		model.put("customer", customer);
		return new ModelAndView("customer/new_customerDeliveryContact", "model", model);
	}
	@ResponseBody
	@RequestMapping(value="/saveCustomerBillContact.htm") //, consumes = "application/json"
	public String saveCustomerBillContact(ModelMap model,CustomerBillContact billContact) {
		log.debug("model  =" + model);
		log.debug(" <save bill contact + ????" + billContact);
		try {
			customerService.saveCustomerBillContact(billContact);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "shuiming";
	}
}
