package com.javatest.registermember.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegisterRequest {
	
	@NotEmpty(message ="username should not be null")
	@Size(min=1 ,max=40,message ="username size should not be greater than 40")
	private String username;
	
	@NotEmpty(message ="password should not be null")
	@Size(min=8 ,max=40,message ="password size should not be greater than 40")
	private String password;
	
	@NotEmpty(message ="address should not be null")
	@Size(min=1 ,max=200,message ="address size should not be greater than 200")
	private String address;
	
	@NotEmpty(message ="phone should not be null")
	@Size(min=10 ,max=10,message ="username size should not be greater than 10")
	private String phone;
	
	@NotEmpty(message ="name should not be null")
	@Size(min=1 ,max=90,message ="name size should not be greater than 90")
	private String name;
	
	@Min(1)
	private int salary;
	
	@NotEmpty(message ="email should not be null")
	@Size(min=1 ,max=80,message ="email size should not be greater than 80")
	private String email;
	
	
}
