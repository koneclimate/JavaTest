package com.javatest.security.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Customer {
	  @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    @Column(unique=true, nullable=false, precision=19)
	    private long id;
	    @Column(nullable=false, length=255)
	    private String cusname;
	    @Column(nullable=false, length=255)
	    private String custel;
}
