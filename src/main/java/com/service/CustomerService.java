package com.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.tags.shaded.org.apache.bcel.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.CustomerEntity;
import com.Entity.UserEntity;
import com.dto.Customerdto;
import com.factory.RepositoryFactory;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

@Service
public class CustomerService {

	@Autowired
	RepositoryFactory repofactory;

	public List<CustomerEntity> listcustomer(Integer userId) {

		return repofactory.getCustomerRepositry().findByUserUserId(userId);
	

	}

	public List<CustomerEntity> customerIdbasetransaction(String email) {

		return repofactory.getCustomerRepositry().findByEmail(email);
	}

	/*
	 * public List<CustomerEntity> TodayAndEmailBaseCustomer(String email) {
	 * 
	 * LocalDate date = LocalDate.now(); DateTimeFormatter formatter =
	 * DateTimeFormatter.ofPattern("yyyy-MM-dd"); String todayString =
	 * date.format(formatter);
	 * 
	 * 
	 * return repofactory.getCustomerRepositry().findByEmailAndTodayRecords(email,
	 * todayString); }
	 */
	
	public List<CustomerEntity> customer(String email) {

		return repofactory.getCustomerRepositry().findCustomerByEmail(email);
	}

	public List<CustomerEntity> ListAllCustomer() {
		
		return  repofactory.getCustomerRepositry().findAllcustomer();
		
		 
	}

	public List<CustomerEntity> findAllCustomersByUserId(Integer userId) {
	    return repofactory.getCustomerRepositry().findByUserId(userId);
	}
	
	  public CustomerEntity saveCustomer(CustomerEntity customerEntity) {
	        return repofactory.getCustomerRepositry().save(customerEntity);
	    }
	  
		/*
		 * public List<CustomerEntity> listOfCustomer(UserEntity users) {
		 * 
		 * List<CustomerEntity> customerActiveTrue =
		 * repofactory.getCustomerRepositry().findByActiveTrue();
		 * customerActiveTrue.stream().filter(CustomerEntity::isActive).collect(
		 * Collectors.toList()); return customerActiveTrue; }
		 */
	   
	   public List<CustomerEntity> listOfCustomer(UserEntity user) {
		    List<CustomerEntity> listcustomer = repofactory.getCustomerRepositry().findByUser(user);
		                     List<CustomerEntity> collect = listcustomer.stream().filter(CustomerEntity::isActive).collect(Collectors.toList());
		                     return collect;
		}

}
