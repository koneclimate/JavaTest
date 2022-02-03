package com.javatest.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javatest.security.entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query("select c from Customer c where c.cusname like concat(:cusname, '%')" +
			" and c.custel like concat(:custel, '%')")
	// select * from customer where cusname like :cusname + "%"
	public List<Customer> getCusByOption(@Param("cusname") String cusname,
			@Param("custel") String custel);
}
