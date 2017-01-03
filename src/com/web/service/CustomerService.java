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

	public List loadCustomerList() {
		return customerDao.fetchCustomerList();
	}

	public Customer loadCustomer(String id) {
		Customer customer = new Customer();
		if (id != null) {
			customer = customerDao.fetchCustomer(Integer.parseInt(id));
		}
		return customer;
	}
	
	public boolean checkDuplicateCustomerName(String name) {
		boolean exist_name = false;
		int i = customerDao.fetchCustomerByName(name);
		if (i > 0) { //ID 存在意思是客户名字已经存在不能在用这个名字注册了
			exist_name = true;
		}
		return exist_name;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Customer saveCustomer(Customer customer) {
		try {
			if (customer.getId() == 0) {
				customerDao.saveCustomer(customer);
				int customerId = customerDao.selectLastInsertId();
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

		} catch (DataAccessException e) {
			throw new BusinessException("01", "数据库访问异常");
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
}
