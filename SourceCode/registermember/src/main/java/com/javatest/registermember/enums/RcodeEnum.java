package com.javatest.registermember.enums;

public enum RcodeEnum {

	_SUCCESS("0000", "Success"),
	
	_MEMBER_EXISTING("1000","Member Existing"),
	
	_INCORRECT_FORMAT("1001","Incorrect format"),

	_SYSTEM_ERROR("2999", "System Error");

	private String rcode;
	private String description;

	private RcodeEnum(final String rcode, final String description) {
		this.rcode = rcode;
		this.description = description;
	}


	public String getRcode() {
		return rcode;
	}



	public String toString() {
		return description;
	}

	public static String byRcode(final String rcode) {
        for (RcodeEnum e : RcodeEnum.values()) {
            if (e.getRcode().equals(rcode)) {
                return e.toString();
            }
        }
        return _SYSTEM_ERROR.toString();
    }
}
