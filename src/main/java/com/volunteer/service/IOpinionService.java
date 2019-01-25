package com.volunteer.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.xxx.volunterNetwork.domain.Opinion;

/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:32:12
 *
 */

public interface IOpinionService {
	public void saveOrUpdate(Opinion opinion);
	public void delete(Opinion opinion);
	public void delete(List<Long> ids);
	public Opinion findOne(Long id);
	public List<Opinion> findAll();
	public void updateState(Long id,Integer state);
	
	/*public Opinion findOpinion(String opinionName);*/
	//动态条件查询
	public Page<Opinion> findAll(Specification<Opinion> spec,Pageable pageable);


}
