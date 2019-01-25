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
import com.xxx.volunterNetwork.dao.ShareRepository;
import com.xxx.volunterNetwork.domain.Share;
import com.xxx.volunterNetwork.service.IShareService;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:43:54
 *
 */

@Service
@Transactional
public class ShareServiceImpl implements IShareService {
	private static final Logger logger = LoggerFactory.getLogger(ShareServiceImpl.class);
	@Autowired
	private ShareRepository shareRepository;
	@SysLog(module="角色管理",methods="保存或者更新数据")
	public void saveOrUpdate(Share share) {
		shareRepository.save(share);
	}
	@SysLog(module="角色管理",methods="删除一条数据")
	public void delete(Share share) {
		shareRepository.delete(share);
	}
	@SysLog(module="角色管理",methods="删除多条数据")
	public void delete(List<Long> ids) {
		List<Share> shareLists = (List<Share>) shareRepository.findAll(ids);
		if(shareLists != null) {
			shareRepository.delete(shareLists);
		}			
	}
	@SysLog(module="角色管理",methods="查找一条数据")
	public Share findOne(Long id) {		
		return shareRepository.findOne(id);
	}
	@SysLog(module="角色管理",methods="查找所有数据")
	public List<Share> findAll() {
		return (List<Share>) shareRepository.findAll();
	}
	@SysLog(module="角色管理",methods="查找所有数据并分页排序")
	public Page<Share> findAll(Specification<Share> spec, Pageable pageable) {
		return shareRepository.findAll(spec, pageable);
	}
/*	@Override
	public Share findShare(String shareName) {
		return shareRepository.findShare(shareName);
	}*/
	@Override
	public void updateState(Long id, Integer state) {
		shareRepository.updateState(id, state);
		
	}
	@Override
	public List<Share> findShare() {		
		return shareRepository.findShare();
	}
	
}
