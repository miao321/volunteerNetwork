package com.volunteer.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.xxx.volunterNetwork.domain.Share;

/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:32:12
 *
 */

public interface IShareService {
	public void saveOrUpdate(Share share);
	public void delete(Share share);
	public void delete(List<Long> ids);
	public Share findOne(Long id);
	public List<Share> findAll();
	public void updateState(Long id,Integer state);
	
	public List<Share> findShare();
	//动态条件查询
	public Page<Share> findAll(Specification<Share> spec,Pageable pageable);


}
