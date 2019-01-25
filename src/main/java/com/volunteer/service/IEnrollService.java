package com.volunteer.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.xxx.volunterNetwork.domain.Enroll;

/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:32:12
 *
 */

public interface IEnrollService {
	public void saveOrUpdate(Enroll enroll);
	public void delete(Enroll enroll);
	public void delete(List<Long> ids);
	public Enroll findOne(Long id);
	public List<Enroll> findAll();
	public void updateState(Long id,Integer state);
	public List<Enroll> findEnroll();
	public Enroll findUser(String userName);
	public List<Enroll> findEnroll(Long id);
	//动态条件查询
	public Page<Enroll> findAll(Specification<Enroll> spec,Pageable pageable);


}
