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
import com.xxx.volunterNetwork.dao.OpinionRepository;
import com.xxx.volunterNetwork.domain.Opinion;
import com.xxx.volunterNetwork.service.IOpinionService;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:43:54
 *
 */

@Service
@Transactional
public class OpinionServiceImpl implements IOpinionService {
	private static final Logger logger = LoggerFactory.getLogger(OpinionServiceImpl.class);
	@Autowired
	private OpinionRepository opinionRepository;
	@SysLog(module="角色管理",methods="保存或者更新数据")
	public void saveOrUpdate(Opinion opinion) {
		opinionRepository.save(opinion);
	}
	@SysLog(module="角色管理",methods="删除一条数据")
	public void delete(Opinion opinion) {
		opinionRepository.delete(opinion);
	}
	@SysLog(module="角色管理",methods="删除多条数据")
	public void delete(List<Long> ids) {
		List<Opinion> opinionLists = (List<Opinion>) opinionRepository.findAll(ids);
		if(opinionLists != null) {
			opinionRepository.delete(opinionLists);
		}			
	}
	@SysLog(module="角色管理",methods="查找一条数据")
	public Opinion findOne(Long id) {		
		return opinionRepository.findOne(id);
	}
	@SysLog(module="角色管理",methods="查找所有数据")
	public List<Opinion> findAll() {
		return (List<Opinion>) opinionRepository.findAll();
	}
	@SysLog(module="角色管理",methods="查找所有数据并分页排序")
	public Page<Opinion> findAll(Specification<Opinion> spec, Pageable pageable) {
		return opinionRepository.findAll(spec, pageable);
	}
/*	@Override
	public Opinion findOpinion(String opinionName) {
		return opinionRepository.findOpinion(opinionName);
	}*/
	@Override
	public void updateState(Long id, Integer state) {
		opinionRepository.updateState(id, state);
		
	}

}
