package com.volunteer.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;

import com.xxx.volunterNetwork.domain.Acti;
import com.xxx.volunterNetwork.domain.Borad;
import com.xxx.volunterNetwork.domain.Enroll;
import com.xxx.volunterNetwork.domain.Opinion;

/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:32:12
 *
 */

public interface IPersonalService {
	public List<Opinion> findOpinion(String userName);
	public List<Enroll> findEnroll(String userName);
	public List<Borad> findBorad();
	public List<Enroll> findRecord(String userName);
	public Integer findDuration(String userName);
	public Integer findpxTime(String userName);
	public Integer findCount(String userName);
}
