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
import com.xxx.volunterNetwork.dao.EnrollRepository;
import com.xxx.volunterNetwork.domain.Enroll;
import com.xxx.volunterNetwork.service.IEnrollService;


/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:43:36
 *
 */
@Service
@Transactional
public class EnrollServiceImpl implements IEnrollService {
	private static final Logger logger = LoggerFactory.getLogger(EnrollServiceImpl.class);
	@Autowired
	private EnrollRepository enrollRepository;
	@SysLog(module="模块管理",methods="保存或者更新数据")
	public void saveOrUpdate(Enroll enroll) {
		enrollRepository.save(enroll);
	}
	@SysLog(module="模块管理",methods="删除一条数据")
	public void delete(Enroll enroll) {
		enrollRepository.delete(enroll);
	}
	@SysLog(module="模块管理",methods="删除多条数据")
	public void delete(List<Long> ids) {
		List<Enroll> enrollLists = (List<Enroll>) enrollRepository.findAll(ids);
		if (enrollLists != null) {
			enrollRepository.delete(enrollLists);
		}

	}
	@SysLog(module="模块管理",methods="查找一条数据")
	public Enroll findOne(Long id) {
		return enrollRepository.findOne(id);
	}
	@SysLog(module="模块管理",methods="查找所有数据")
	public List<Enroll> findAll() {
		return (List<Enroll>) enrollRepository.findAll();
	}
	@SysLog(module="模块管理",methods="查找所有数据并分页排序")
	public Page<Enroll> findAll(Specification<Enroll> spec, Pageable pageable) {
		return enrollRepository.findAll(spec, pageable);
	}
/*	@Override
	public Enroll findEnroll(String enrollName) {
		return enrollRepository.findEnroll(enrollName);
	}*/
	@Override
	public void updateState(Long id, Integer state) {
		enrollRepository.updateState(id, state);
		
	}
	@Override
	public List<Enroll> findEnroll() {
		return enrollRepository.findEnroll();
	}
	@Override
	public List<Enroll> findEnroll(Long id) {	
		return enrollRepository.findEnroll(id);
	}
	@Override
	public Enroll findUser(String userName) {
		return enrollRepository.findUser(userName);
	}
	
}
