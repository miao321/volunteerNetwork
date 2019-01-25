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
import com.xxx.volunterNetwork.dao.LogRepository;
import com.xxx.volunterNetwork.domain.Log;
import com.xxx.volunterNetwork.domain.User;
import com.xxx.volunterNetwork.service.ILogService;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:43:21
 *
 */

@Service
@Transactional
public class LogServiceImpl implements ILogService {
	private static final Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);
	@Autowired
	private LogRepository logRepository;
	@SysLog(module="日志管理",methods="保存或者更新数据")
	public void saveOrUpdate(Log log) {
		logRepository.save(log);
	}
	@SysLog(module="日志管理",methods="删除数据")
	public void delete(Log log) {
		logRepository.delete(log);
	}
	@SysLog(module="日志管理",methods="删除多条数据")
	public void delete(List<Long> ids) {
		List<Log> logLists = (List<Log>) logRepository.findAll(ids);
		if(logLists != null) {
			logRepository.delete(logLists);
		}			
	}
	@SysLog(module="日志管理",methods="查找一条数据")
	public Log findOne(Long id) {		
		return logRepository.findOne(id);
	}
	@SysLog(module="日志管理",methods="查找所有数据")
	public List<Log> findAll() {
		return (List<Log>) logRepository.findAll();
	}
	@SysLog(module="日志管理",methods="查找所有数据并分页排序")
	public Page<Log> findAll(Specification<Log> spec, Pageable pageable) {
		return logRepository.findAll(spec, pageable);
	}
	/*@SysLog(module="日志管理",methods="查找用户")
	public User findUser(String userName) {		
		return logRepository.findUser(userName);
	}*/

}
