package com.javatest.registermember.validator;

import org.springframework.validation.BindingResult;

import com.javatest.registermember.enums.RcodeEnum;
import com.javatest.registermember.model.Response;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class Validator {
	
	public static boolean isEmpty(String value) {
		boolean resp = false;
		try {
			if(value.equals(null) && value.equals("")) {
				resp = true;
			}
		}
		catch(Exception e) {
			resp = true;
		}
		
		return resp;
		
	}
	public static boolean isEmpty(Object value) {
		if( value == null ){
			return true;
		}
		return false;
		
	}

	public static Response ValidateField(BindingResult result) {
		Response resp = new Response();
		if(result.hasErrors()) {
			log.debug("RCode:"+RcodeEnum._MEMBER_EXISTING.getRcode());
			log.debug("RCodeDesc:"+result.getAllErrors().get(0).getDefaultMessage());
			resp.setResp_code(RcodeEnum._INCORRECT_FORMAT.getRcode());
			resp.setResp_desc(RcodeEnum._INCORRECT_FORMAT.toString());
		}
		return resp;
	}
}
