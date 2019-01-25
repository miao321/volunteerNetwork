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
import com.xxx.volunterNetwork.dao.ImgRepository;
import com.xxx.volunterNetwork.domain.Img;
import com.xxx.volunterNetwork.service.IImgService;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:43:54
 *
 */

@Service
@Transactional
public class ImgServiceImpl implements IImgService {
	private static final Logger logger = LoggerFactory.getLogger(ImgServiceImpl.class);
	@Autowired
	private ImgRepository imgRepository;
	@SysLog(module="角色管理",methods="保存或者更新数据")
	public void saveOrUpdate(Img img) {
		imgRepository.save(img);
	}
	@SysLog(module="角色管理",methods="删除一条数据")
	public void delete(Img img) {
		imgRepository.delete(img);
	}
	@SysLog(module="角色管理",methods="删除多条数据")
	public void delete(List<Long> ids) {
		List<Img> imgLists = (List<Img>) imgRepository.findAll(ids);
		if(imgLists != null) {
			imgRepository.delete(imgLists);
		}			
	}
	@SysLog(module="角色管理",methods="查找一条数据")
	public Img findOne(Long id) {		
		return imgRepository.findOne(id);
	}
	@SysLog(module="角色管理",methods="查找所有数据")
	public List<Img> findAll() {
		return (List<Img>) imgRepository.findAll();
	}
	@SysLog(module="角色管理",methods="查找所有数据并分页排序")
	public Page<Img> findAll(Specification<Img> spec, Pageable pageable) {
		return imgRepository.findAll(spec, pageable);
	}
/*	@Override
	public Img findImg(String imgName) {
		return imgRepository.findImg(imgName);
	}*/
	@Override
	public void updateState(Long id, Integer state) {
		imgRepository.updateState(id, state);
		
	}
	@Override
	public List<Img> findImg() {
		return imgRepository.findImg();
		
	}
	@Override
	public List<Img> findImg2() {
		return imgRepository.findImg2();
	}

}
