package com.javatest.registermember.entity.id;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class TbMemberId implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2671088576180525849L;
	private String username;
	private String regRefId;
}
