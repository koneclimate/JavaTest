package com.javatest.registermember.model;

import lombok.Data;

@Data
public class RegisterResponse {
	private String resp_code;
	private String resp_desc;
	private String reg_ref_id;
}
