package com.volunteer.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xxx.volunterNetwork.anno.SysLog;
import com.xxx.volunterNetwork.dao.ActiRepository;
import com.xxx.volunterNetwork.domain.Acti;
import com.xxx.volunterNetwork.service.IActiService;


/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:43:36
 *
 */
@Service
@Transactional
public class ActiServiceImpl implements IActiService {
	private static final Logger logger = LoggerFactory.getLogger(ActiServiceImpl.class);
	@Autowired
	private ActiRepository actiRepository;
	
	@SysLog(module="模块管理",methods="保存或者更新数据")
	public void saveOrUpdate(Acti acti) {
		actiRepository.save(acti);
	}
	@SysLog(module="模块管理",methods="删除一条数据")
	public void delete(Acti acti) {
		actiRepository.delete(acti);
	}
	@SysLog(module="模块管理",methods="删除多条数据")
	public void delete(List<Long> ids) {
		List<Acti> actiLists = (List<Acti>) actiRepository.findAll(ids);
		if (actiLists != null) {
			actiRepository.delete(actiLists);
		}

	}
	@SysLog(module="模块管理",methods="查找一条数据")
	public Acti findOne(Long id) {
		return actiRepository.findOne(id);
	}
	@SysLog(module="模块管理",methods="查找所有数据")
	public List<Acti> findAll() {
		return (List<Acti>) actiRepository.findAll();
	}
	@SysLog(module="模块管理",methods="查找所有数据并分页排序")
	public Page<Acti> findAll(Specification<Acti> spec, Pageable pageable) {
		return actiRepository.findAll(spec, pageable);
	}
	
	@Override
	public void updateState(Long id, Integer state) {
		actiRepository.updateState(id, state);
		
	}
	@Override
	public List<Acti> findActi() {
		return actiRepository.findActi();
	}
	@Override
	public List<Acti> findActi2() {
		return actiRepository.findActi2();
	}
	@Override
	public List<Acti> findActi3() {
		return actiRepository.findActi3();
	}
	@Override
	public List<Acti> findActi4() {
		return actiRepository.findActi4();
	}
	@Override
	public List<Acti> findActi5() {
		return actiRepository.findActi5();
	}
	@Override
	public List<Acti> findActi6() {
		return actiRepository.findActi6();
	}
	@Override
	public List<Acti> findActi7() {
		return actiRepository.findActi7();
	}
	@Override
	public List<Acti> findActi8() {
		return actiRepository.findActi8();
	}
	@Override
	public List<Acti> findActi9() {
		return actiRepository.findActi9();
	}
	@Override
	public List<Acti> findActi0() {
		return actiRepository.findActi0();
	}
	@Override
	public Page<Acti> findSearch(String query, Pageable pageable) {
		return actiRepository.findSearch(query, pageable);
	}
	@Override
	public Acti findByHdlx(String hdlx) {
		return actiRepository.findByHdlx(hdlx);
	}
	
	

}
