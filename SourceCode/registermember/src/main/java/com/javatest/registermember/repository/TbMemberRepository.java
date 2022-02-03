package com.javatest.registermember.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javatest.registermember.entity.TbMember;
import com.javatest.registermember.entity.id.TbMemberId;


@Repository
public interface TbMemberRepository extends CrudRepository<TbMember,TbMemberId>{

}
