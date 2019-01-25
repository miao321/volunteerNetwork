package com.volunteer.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;

import com.xxx.volunterNetwork.domain.Acti;
import com.xxx.volunterNetwork.domain.Borad;



/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:32:12
 *
 */

public interface IBoradService {
	public void saveOrUpdate(Borad borad);
	public void delete(Borad borad);
	public void delete(List<Long> ids);
	public Borad findOne(Long id);
	public List<Borad> findAll();
	public void updateState(Long id,Integer state);
	/*public Borad findBorad(String boradName);*/
	public List<Borad> findInfo();
	public List<Borad> findCulture();
	public List<Borad> findMessage();
	//动态条件查询
	public Page<Borad> findAll(Specification<Borad> spec,Pageable pageable);


}
