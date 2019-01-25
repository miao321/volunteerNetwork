package com.volunteer.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.xxx.volunterNetwork.domain.Acti;
import com.xxx.volunterNetwork.util.ExtPageable;

/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:32:12
 *
 */

public interface IActiService {
	public void saveOrUpdate(Acti acti);
	public void delete(Acti acti);
	public void delete(List<Long> ids);
	public Acti findOne(Long id);
	public List<Acti> findAll();
	public void updateState(Long id,Integer state);
	
	public List<Acti> findActi();
	public List<Acti> findActi2();
	public List<Acti> findActi3();
	public List<Acti> findActi4();
	public List<Acti> findActi5();
	public List<Acti> findActi6();
	public List<Acti> findActi7();
	public List<Acti> findActi8();
	public List<Acti> findActi9();
	public List<Acti> findActi0();
	public Acti findByHdlx(String hdlx);
	//动态条件查询
	Page<Acti> findSearch(String query, Pageable pageable);
	public Page<Acti> findAll(Specification<Acti> spec,Pageable pageable);


}
