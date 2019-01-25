package com.volunteer.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.xxx.volunterNetwork.anno.SysLog;
import com.xxx.volunterNetwork.dao.OrganizationRepository;
import com.xxx.volunterNetwork.domain.Enroll;
import com.xxx.volunterNetwork.domain.Organization;
import com.xxx.volunterNetwork.dto.EnrollQueryDTO;
import com.xxx.volunterNetwork.dto.OrganizationQueryDTO;
import com.xxx.volunterNetwork.service.IOrganizationService;
import com.xxx.volunterNetwork.util.DateUtil;


/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:43:36
 *
 */
@Service
@Transactional
public class OrganizationServiceImpl implements IOrganizationService {
	private static final Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class);
	@Autowired
	private OrganizationRepository organizationRepository;
	@SysLog(module="模块管理",methods="保存或者更新数据")
	public void saveOrUpdate(Organization organization) {
		organizationRepository.save(organization);
	}
	@SysLog(module="模块管理",methods="删除一条数据")
	public void delete(Organization organization) {
		organizationRepository.delete(organization);
	}
	@SysLog(module="模块管理",methods="删除多条数据")
	public void delete(List<Long> ids) {
		List<Organization> organizationLists = (List<Organization>) organizationRepository.findAll(ids);
		if (organizationLists != null) {
			organizationRepository.delete(organizationLists);
		}

	}
	@SysLog(module="模块管理",methods="查找一条数据")
	public Organization findOne(Long id) {
		return organizationRepository.findOne(id);
	}
	@SysLog(module="模块管理",methods="查找所有数据")
	public List<Organization> findAll() {
		return (List<Organization>) organizationRepository.findAll();
	}
	@SysLog(module="模块管理",methods="查找所有数据并分页排序")
	public Page<Organization> findAll(Specification<Organization> spec, Pageable pageable) {
		return organizationRepository.findAll(spec, pageable);
	}
/*	@Override
	public Organization findOrganization(String organizationName) {
		return organizationRepository.findOrganization(organizationName);
	}*/
	@Override
	public void updateState(Long id, Integer state) {
		organizationRepository.updateState(id, state);
		
	}
	
	@Override
	public List<OrganizationQueryDTO> findOrganization() {
		List<OrganizationQueryDTO> list = new ArrayList<OrganizationQueryDTO>();
		List<Object[]> organization = organizationRepository.findOrganization();
		for(Object[] obj : organization) {
			OrganizationQueryDTO dto = new OrganizationQueryDTO();
			dto.setId(Long.valueOf(obj[0]+""));
			dto.setoNum(obj[1]+"");
			dto.setOrganization(obj[2]+"");
			dto.setImg(obj[3]+"");
			dto.setMenNum(obj[4]+"");
			list.add(dto);			
		}
		return list;
	}
	@Override
	public List<EnrollQueryDTO> findEnroll() {
		List<EnrollQueryDTO> list = new ArrayList<EnrollQueryDTO>();
		List<Object[]> enroll = organizationRepository.findEnroll();
		for(Object[] obj : enroll) {
			EnrollQueryDTO dto = new EnrollQueryDTO();
			dto.setCollege(obj[0]+"");			
			dto.setDuration(Integer.valueOf(obj[1]+""));						
			list.add(dto);
		}
		return list;
	}
	@Override
	public List<Organization> findVolunter() {
		return organizationRepository.findVolunter();
	}
	@Override
	public List<OrganizationQueryDTO> findNum() {
		List<OrganizationQueryDTO> list = new ArrayList<OrganizationQueryDTO>();
		List<Object[]> organization = organizationRepository.findNum();
		for(Object[] obj : organization) {
			OrganizationQueryDTO dto = new OrganizationQueryDTO();
			dto.setmNum(obj[0]+"");
			dto.setOrNum(obj[1]+"");
			dto.setXjorNum(obj[2]+"");
			list.add(dto);
		}
		return list;
	}
	/*@Override
	public List<OrganizationQueryDTO> findDuration() {
		List<OrganizationQueryDTO> list = new ArrayList<OrganizationQueryDTO>();
		List<Object[]> enroll = organizationRepository.findDuration();		
		for(Object[] obj : enroll) {
			OrganizationQueryDTO dto = new OrganizationQueryDTO();
			
			dto.setDurationNum((BigDecimal)obj[0]+"");
			list.add(dto);
		}
		return list;
	}*/
	@Override
	public Integer findDuration() {
		return organizationRepository.findDuration();
	}

	
	
}
