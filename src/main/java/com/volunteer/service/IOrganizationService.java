package com.volunteer.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.xxx.volunterNetwork.domain.Enroll;
import com.xxx.volunterNetwork.domain.Organization;
import com.xxx.volunterNetwork.dto.EnrollQueryDTO;
import com.xxx.volunterNetwork.dto.OrganizationQueryDTO;

/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:32:12
 *
 */

public interface IOrganizationService {
	public void saveOrUpdate(Organization organization);
	public void delete(Organization organization);
	public void delete(List<Long> ids);
	public Organization findOne(Long id);
	public List<Organization> findAll();
	public void updateState(Long id,Integer state);
	public List<OrganizationQueryDTO> findNum();
	public Integer findDuration();
	
	public List<OrganizationQueryDTO> findOrganization();
	public List<Organization> findVolunter();
	public List<EnrollQueryDTO> findEnroll();
	//动态条件查询
	public Page<Organization> findAll(Specification<Organization> spec,Pageable pageable);


}
