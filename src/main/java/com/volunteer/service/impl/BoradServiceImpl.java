package com.volunteer.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xxx.volunterNetwork.anno.SysLog;
import com.xxx.volunterNetwork.dao.BoradRepository;
import com.xxx.volunterNetwork.domain.Acti;
import com.xxx.volunterNetwork.domain.Borad;
import com.xxx.volunterNetwork.service.IBoradService;

/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:43:36
 *
 */






@Service
@Transactional
public class BoradServiceImpl implements IBoradService {
	private static final Logger logger = LoggerFactory.getLogger(BoradServiceImpl.class);
	@Autowired
	private BoradRepository boradRepository;
	@SysLog(module="模块管理",methods="保存或者更新数据")
	public void saveOrUpdate(Borad borad) {
		boradRepository.save(borad);
	}
	@SysLog(module="模块管理",methods="删除一条数据")
	public void delete(Borad borad) {
		boradRepository.delete(borad);
	}
	@SysLog(module="模块管理",methods="删除多条数据")
	public void delete(List<Long> ids) {
		List<Borad> boradLists = (List<Borad>) boradRepository.findAll(ids);
		if (boradLists != null) {
			boradRepository.delete(boradLists);
		}

	}
	@SysLog(module="模块管理",methods="查找一条数据")
	public Borad findOne(Long id) {
		return boradRepository.findOne(id);
	}
	@SysLog(module="模块管理",methods="查找所有数据")
	public List<Borad> findAll() {
		return (List<Borad>) boradRepository.findAll();
	}
	@SysLog(module="模块管理",methods="查找所有数据并分页排序")
	public Page<Borad> findAll(Specification<Borad> spec, Pageable pageable) {
		return boradRepository.findAll(spec, pageable);
	}
/*	@Override
	public Borad findBorad(String boradName) {
		return boradRepository.findBorad(boradName);
	}*/
	@Override
	public void updateState(Long id, Integer state) {
		boradRepository.updateState(id, state);
		
	}
	@Override
	public List<Borad> findInfo() {
		// TODO Auto-generated method stub
		return boradRepository.findInfo();
	}
	@Override
	public List<Borad> findCulture() {
		// TODO Auto-generated method stub
		return boradRepository.findCulture();
	}
	@Override
	public List<Borad> findMessage() {
		// TODO Auto-generated method stub
		return boradRepository.findMessage();
	}

}
