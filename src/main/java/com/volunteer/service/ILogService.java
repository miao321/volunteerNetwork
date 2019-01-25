package com.volunteer.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.xxx.volunterNetwork.domain.Log;
import com.xxx.volunterNetwork.domain.User;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:32:02
 *
 */


public interface ILogService {
	public void saveOrUpdate(Log log);
	public void delete(Log log);
	public void delete(List<Long> ids);
	public Log findOne(Long id);
	public List<Log> findAll();
	
	
	//动态条件查询
	public Page<Log> findAll(Specification<Log> spec,Pageable pageable);
	
/*	public User findUser(String userName);*/

}
