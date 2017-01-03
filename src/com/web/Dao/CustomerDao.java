package com.web.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.web.domain.Customer;
import com.web.domain.CustomerBillContact;
import com.web.domain.CustomerDeliveryContact;

@Repository
public class CustomerDao {
	
	Logger log = Logger.getLogger(CustomerDao.class);

	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	
	/***************************** Customer **********************************************/
	public List fetchCustomerList() {
		List  list = null;
		String sql = "select * from customer where id > 0";
		list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
	public int fetchCustomerByName(String name) {
		String sql = "select id from customer where name = '"+name+"'";
		Object o = jdbcTemplate.queryForObject(sql, Object.class);
		int i = 0;
		if (o != null) i = Integer.parseInt(o.toString());
		return i;
	}
	
	public Customer fetchCustomer(int id) {
		Customer customer = new Customer();
		String sql = "select id, name, migrationId from customer where id = ?";
		RowMapper<Customer> rowMapper = new RowMapper<Customer>() {
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Customer customer = new Customer();
				customer.setId(rs.getInt("id"));
				customer.setName(rs.getString("name"));
				customer.setMigrationId(rs.getString("migrationId"));
				return customer;
			}
		};
		customer = jdbcTemplate.queryForObject(sql, rowMapper, id);
		return customer;
	}
	
	public int saveCustomer(Customer customer) {
		String sql = "insert into customer (name, migrationId) values (?, ?)";
		int i = jdbcTemplate.update(sql, customer.getName(), customer.getMigrationId());
		log.debug("dao save customer sql = " + sql);
		return i;
	}
	
	public void updateCustomer(Customer customer) {
		String sql = "update customer set name = ?, migrationId = ? where id = ?";
		jdbcTemplate.update(sql, customer.getName(), customer.getMigrationId(), customer.getId());
		log.debug("dao update customer sql = " + sql);
	}
	/***************************** Customer **********************************************/
	
	/***************************** billContact **********************************************/
	public int saveCustomerBillContact(CustomerBillContact billContact) {
		String sql = "insert into customerBillContact (customerId, billAttention, billTelephone, billMobile, billEmail"
				+ ", billFax, billAddress1, billAddress2, billAddress3, billPostcode) values (?,?,?,?,?,?,?,?,?,?)";
		int i =
		jdbcTemplate.update(sql, null, billContact.getBillAttention(), billContact.getBillTelephone()
				, billContact.getBillMobile(), billContact.getBillEmail(), billContact.getBillFax(), billContact.getBillAddress1()
				, billContact.getBillAddress2(), billContact.getBillAddress3(), billContact.getBillPostcode());
		log.debug("doo save bill contact sql = " + sql);
		return i;
	}
	public int updateCustomerBillContact(CustomerBillContact billContact) {
		String sql = "update customerBillContact set customerId = ?, billAttention = ?, billTelephone = ?"
				+ ", billMobile = ?, billEmail = ?"
				+ ", billFax = ?, billAddress1 = ?, billAddress2 = ?, billAddress3 = ?, billPostcode = ? where id = ?";
		int i =
		jdbcTemplate.update(sql, billContact.getCustomerId(), billContact.getBillAttention(), billContact.getBillTelephone()
				, billContact.getBillMobile(), billContact.getBillEmail(), billContact.getBillFax(), billContact.getBillAddress1()
				, billContact.getBillAddress2(), billContact.getBillAddress3(), billContact.getBillPostcode(), billContact.getId());
		log.debug("doo save bill contact sql = " + sql);
		return i;
	}
	/***************************** billContact **********************************************/
	
	/***************************** deliveryContact **********************************************/
	public int saveCustomerDeliveryContact(CustomerDeliveryContact deliveryContact) {
		String sql = "insert into customerDeliveryContact (customerId, deliveryAttention, deliveryTelephone, deliveryMobile, deliveryEmail"
				+ ", deliveryAddress1, deliveryAddress2, deliveryAddress3, deliveryPostcode) values (?,?,?,?,?,?,?,?,?)";
		int i = 
		jdbcTemplate.update(sql,null, deliveryContact.getDeliveryAttention(), deliveryContact.getDeliveryTelephone()
				, deliveryContact.getDeliverylMobile(), deliveryContact.getDeliveryEmail(), deliveryContact.getDeliveryAddress1()
				, deliveryContact.getDeliveryAddress2(), deliveryContact.getDeliveryAddress3(), deliveryContact.getDeliveryPostcode());
		log.debug("dao save deliveryContact sql = " + sql);
		return i;
	}
	
	public int updateCustomerDeliveryContact(CustomerDeliveryContact deliveryContact) {
		String sql = "update customerDeliveryContact set customerId = ?, deliveryAttention = ?, deliveryTelephone = ?"
				+ ", deliveryMobile = ?, deliveryEmail = ?"
				+ ", deliveryAddress1 = ?, deliveryAddress2 = ?, deliveryAddress3 = ?, deliveryPostcode =? where id = ?";
		int i = 
		jdbcTemplate.update(sql, deliveryContact.getCustomerId(), deliveryContact.getDeliveryAttention(), deliveryContact.getDeliveryTelephone()
				, deliveryContact.getDeliverylMobile(), deliveryContact.getDeliveryEmail(), deliveryContact.getDeliveryAddress1()
				, deliveryContact.getDeliveryAddress2(), deliveryContact.getDeliveryAddress3(), deliveryContact.getDeliveryPostcode()
				, deliveryContact.getId());
		log.debug("dao update deliveryContact sql = " + sql);
		return i;
	}
	/***************************** deliveryContact **********************************************/
	
	public int selectLastInsertId(){
		String sql = "select last_insert_id()";
		int last_inserted_id = jdbcTemplate.queryForObject(sql, Integer.class);
		
		return last_inserted_id;
	}
}
