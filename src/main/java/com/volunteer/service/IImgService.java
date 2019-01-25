package com.volunteer.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.xxx.volunterNetwork.domain.Img;

/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:32:12
 *
 */

public interface IImgService {
	public void saveOrUpdate(Img img);
	public void delete(Img img);
	public void delete(List<Long> ids);
	public Img findOne(Long id);
	public List<Img> findAll();
	public void updateState(Long id,Integer state);
	public List<Img> findImg();
	public List<Img> findImg2();
	/*public Img findImg(String imgName);*/
	//动态条件查询
	public Page<Img> findAll(Specification<Img> spec,Pageable pageable);


}
