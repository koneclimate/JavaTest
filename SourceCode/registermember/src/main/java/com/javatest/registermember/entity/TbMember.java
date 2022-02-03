package com.javatest.registermember.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.javatest.registermember.entity.id.TbMemberId;

import lombok.Data;


@Data
@Entity
@Table(name = "TB_MEMBER")
@IdClass(TbMemberId.class)
public class TbMember {

	@Id
	private String regRefId;
	@Id
	private String username;
	
	private String memberType;
	
	private String password;
	private String name;
	private String address;
	private String phone;
	private String email;
	private int salary;
	private String createDate;
	private String createTime;
	private String status;
	
}
