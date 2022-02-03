package com.javatest.registermember.controller;

import java.util.Calendar;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.javatest.registermember.entity.TbMember;
import com.javatest.registermember.enums.RcodeEnum;
import com.javatest.registermember.model.RegisterRequest;
import com.javatest.registermember.model.RegisterResponse;
import com.javatest.registermember.model.Response;
import com.javatest.registermember.service.TbMemberService;
import com.javatest.registermember.util.DateUtil;
import com.javatest.registermember.util.HashingUtil;
import com.javatest.registermember.validator.Validator;

import lombok.extern.log4j.Log4j2;



@RestController
@CrossOrigin(value = "*")
@Log4j2
public class RegisterController {
	@Autowired
	private Gson gson;
	
	@Autowired
	protected SmartValidator smartValid;
	
	@Autowired
	private TbMemberService memberService;
	
	

	@PostMapping(value = "/regist")
	public ResponseEntity<Object> Register(@Valid @RequestBody RegisterRequest request,BindingResult result)  {
		log.debug("----- Register -----");
		RegisterResponse resp = new RegisterResponse();
		Response valid = new Response();
		try {
			
		
		
		String jsonRequest = gson.toJson(request);
		log.debug("request-->> " + jsonRequest);
		
		//validate
		smartValid.validate(request, result);
		valid= Validator.ValidateField( result);
		
		if( !Validator.isEmpty(valid.getResp_code())) {
			return new ResponseEntity<Object>(resp, HttpStatus.OK);
		}
		
		//Action
		String memtype="";
		if(request.getSalary()>50000) {
			memtype="Platinum";
		}
		else if(request.getSalary()>=30000 ) {
			memtype="Gold";
		}
		else if(request.getSalary()>=15000 ) {
			memtype="Silver";
		}
		else {
			resp.setResp_code(RcodeEnum._MEMBER_EXISTING.getRcode());
			resp.setResp_desc(RcodeEnum._MEMBER_EXISTING.toString());
		}
		
		if(!memtype.equals("")) {
			String date = DateUtil.date();
			String time =DateUtil.time();
			String regrefId =date+request.getPhone().substring(6);
			TbMember member = new TbMember();
			member.setUsername(request.getUsername());
			member.setPassword(HashingUtil.doHashing(request.getPassword()));
			member.setRegRefId(regrefId);
			member.setMemberType(memtype);
			member.setName(request.getName());
			member.setAddress(request.getAddress());
			member.setPhone(request.getPhone());
			member.setEmail(request.getEmail());
			member.setSalary(request.getSalary());
			member.setCreateDate(date);
			member.setCreateTime(time);
			member.setStatus("1");
			memberService.create(member);
			
			resp.setResp_code(RcodeEnum._SUCCESS.getRcode());
			resp.setResp_desc(RcodeEnum._SUCCESS.toString());
		}
		}
		catch (Exception e) {
			resp.setResp_code(RcodeEnum._SYSTEM_ERROR.getRcode());
			resp.setResp_desc(RcodeEnum._SYSTEM_ERROR.toString());
		}
		
		
		log.debug("response-->> " + gson.toJson(resp));
		
		return new ResponseEntity<Object>(resp, HttpStatus.OK);
		
	}
	
	
	
}
