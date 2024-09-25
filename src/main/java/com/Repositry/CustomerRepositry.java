package com.Repositry;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Entity.CustomerEntity;
import com.Entity.GstTransaction;
import com.Entity.UserEntity;
import com.dto.Customerdto;

public interface CustomerRepositry extends JpaRepository<CustomerEntity, Integer> {



	List<CustomerEntity> findByUserUserId(Integer userId);

	List<CustomerEntity> findByEmail(String email);

	List<CustomerEntity> findByCustomerId(Integer CustomerId);

	List<CustomerEntity> findCustomerByEmail(String email);

	List<CustomerEntity> findAllByEmail(String email);

	/*
	 * @Query(value = "SELECT * FROM gst_transaction t " +
	 * "JOIN customer c ON c.customer_id = t.id " +
	 * "WHERE c.email = ?1 AND DATE(t.date) = ?2", nativeQuery = true)
	 * List<CustomerEntity> findByEmailAndTodayRecords(String email , String date);
	 */

	@Query(value = "SELECT * FROM customer WHERE active = true", nativeQuery = true)
	List<CustomerEntity> findAllcustomer();

	@Query(value = "SELECT * FROM customer WHERE user_user_id = ?1", nativeQuery = true)
	List<CustomerEntity> findByUserId(Integer userId);

	  List<CustomerEntity> findByActiveTrue();

	List<CustomerEntity> findByUser(UserEntity user);
	
	

	


}
