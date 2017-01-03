package com.web.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.web.Dao.CustomerDao;
import com.web.domain.Customer;
import com.web.domain.CustomerBillContact;
import com.web.domain.CustomerDeliveryContact;
import com.web.exception.BusinessException;

@Service
public class CustomerService {

	Logger log = Logger.getLogger(CustomerService.class);

	@Autowired
	private CustomerDao customerDao;

	/********************************** Customer *********************************************/
	public List loadCustomerList() {
		return customerDao.fetchCustomerList();
	}

	public Customer loadCustomer(String id) {
		Customer customer = new Customer();
		if (id != null && !id.equals("0")) {
			try {
				customer = customerDao.fetchCustomer(Integer.parseInt(id));
			} catch (Exception e) {
				throw e;
			}
		}
		return customer;
	}
	
	public boolean checkDuplicateCustomerName(String name) {
		boolean exist_name = false;
		Customer customer = new Customer();
		log.debug("service checkDuplicateCustomerName " + customer);
		try {
			customer = customerDao.fetchCustomerByName(name);
		} catch (Exception e) {
			throw e;
		}
		if (customer.getId() > 0) { //ID 存在意思是客户名字已经存在不能在用这个名字注册了
			exist_name = true;
		}
		return exist_name;
	}

	/**
	 * save customer, on customer table default record id = 0;
	 * FK on the billContact table default setup customer id = 0;
	 * after save need updated billContact table customer id to new customer id.
	 * @param customer
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Customer saveCustomer(Customer customer) {
		try {
			if (customer.getId() == 0) {
				customerDao.saveCustomer(customer);
				int customerId = customerDao.selectLastInsertId();
//				if(customer.getBillContact().getBillAttention() == null){
//					
//				}
				customerDao.saveCustomerBillContact(customer.getBillContact());
				int billContactId = customerDao.selectLastInsertId();
				customerDao.saveCustomerDeliveryContact(customer.getDeliveryContact());
				int deliveryContactId = customerDao.selectLastInsertId();

				customer.getBillContact().setCustomerId(customerId);
				customer.getBillContact().setId(billContactId);

				customer.getDeliveryContact().setCustomerId(customerId);
				customer.getDeliveryContact().setId(deliveryContactId);
			} else {
				customerDao.updateCustomer(customer);
			}

		} catch (Exception e) {
			throw e;
		}
		return customer;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateCustomerBillContact(CustomerBillContact billContact) {
		customerDao.updateCustomerBillContact(billContact);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateCustomerDeliveryContact(CustomerDeliveryContact deliveryContact) {
		customerDao.updateCustomerDeliveryContact(deliveryContact);
	}
	
	/********************************** Customer *********************************************/
	
	/********************************** Customer BillContact *********************************************/
	public List loadCustomerBillContactList(int customerId) {
		List billContoncatList = customerDao.fetchCustomerBillContactList(customerId);
		return billContoncatList;
	}
	public CustomerBillContact loadCustomerBillContact(int id) {
		CustomerBillContact billContact = new CustomerBillContact();
		billContact = customerDao.fetchCustomerBillContact(id);
		return billContact;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void saveCustomerBillContact(CustomerBillContact billContact) {
		if (billContact.getId() == 0 ) {
			customerDao.saveCustomerBillContact(billContact);
		} else {
			customerDao.updateCustomerBillContact(billContact);
		}
	}
	/********************************** Customer BillContact *********************************************/
	
	/********************************** Customer DeliveryContact *********************************************/
	public List loadCustomerDeliveryContactList(int customerId) {
		List deliveryContoncatList = customerDao.fetchCustomerDeliveryContactList(customerId);
		return deliveryContoncatList;
	}
	
	public CustomerDeliveryContact loadDeliveryContact(int id) {
		CustomerDeliveryContact deliveryContact = new CustomerDeliveryContact();
		deliveryContact = customerDao.fetchCustomerDeliveryContact(id);
		return deliveryContact;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void saveCustomerDeliveryContact(CustomerDeliveryContact deliveryContact){
		// id = 0, means created new entity, else updated entity
		if (deliveryContact.getId() == 0) {
			customerDao.saveCustomerDeliveryContact(deliveryContact);
		} else {
			customerDao.updateCustomerDeliveryContact(deliveryContact);
		}
	}
	/********************************** Customer DeliveryContact *********************************************/
	
}
