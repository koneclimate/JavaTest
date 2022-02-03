package com.javatest.registermember.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatest.registermember.entity.TbMember;
import com.javatest.registermember.repository.TbMemberRepository;

import lombok.extern.log4j.Log4j2;


@Service
@Log4j2
public class TbMemberService {

	@Autowired
	TbMemberRepository memberRepository;
	
	public void create(TbMember member) {
		TbMember resp = new TbMember();
		resp = memberRepository.save(member);
		log.debug(resp);
	}
}
