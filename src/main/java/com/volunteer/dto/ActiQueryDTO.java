package com.volunteer.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.dom4j.CDATA;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.xxx.volunterNetwork.domain.Acti;

/**
 * 
 * @author miao
 * @date 2018年1月20日下午4:07:01
 *
 */
public class ActiQueryDTO {	
	private String hdlx;
	private String title;
	private String fbzz;
	private String xxdz;
	private String query;
	private String content;
	public String getHdlx() {
		return hdlx;
	}
	public void setHdlx(String hdlx) {
		this.hdlx = hdlx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFbzz() {
		return fbzz;
	}
	public void setFbzz(String fbzz) {
		this.fbzz = fbzz;
	}
	public String getXxdz() {
		return xxdz;
	}
	public void setXxdz(String xxdz) {
		this.xxdz = xxdz;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ActiQueryDTO() {

	}
	public ActiQueryDTO(String hdlx, String title, String fbzz, String xxdz, String query) {
		this.hdlx = hdlx;
		this.title = title;
		this.fbzz = fbzz;
		this.xxdz = xxdz;
		this.query = query;
	}
	//提供static的工具方法： 根据当前actiQueryDTO对象来组装动态查询条件
	public static Specification<Acti> getSpecification(ActiQueryDTO actiQueryDTO){
		Specification<Acti> spec = new Specification<Acti>() {
			public Predicate toPredicate(Root<Acti> root, CriteriaQuery<?> query, CriteriaBuilder cb) {							
				//1.Predicate查询条件集合
				List<Predicate> list = new ArrayList<Predicate>();				
				//2.根据QueryDTO数据字段的值进行判断以及条件的组装	
				if(actiQueryDTO != null && !StringUtils.isEmpty(actiQueryDTO.getHdlx())) {
					Predicate p1 = cb.like(root.get("hdlx").as(String.class), "%"+actiQueryDTO.getHdlx()+"");
					list.add(p1);
				}	
				if(actiQueryDTO != null && !StringUtils.isEmpty(actiQueryDTO.getTitle())) {
					Predicate p2 = cb.like(root.get("title").as(String.class), "%"+actiQueryDTO.getTitle()+"%");
					list.add(p2);
				}
				if(actiQueryDTO != null && !StringUtils.isEmpty(actiQueryDTO.getFbzz())) {
					Predicate p3 = cb.like(root.get("fbzz").as(String.class), "%"+actiQueryDTO.getFbzz()+"%");
					list.add(p3);
				}
				if(actiQueryDTO != null && !StringUtils.isEmpty(actiQueryDTO.getXxdz())) {
					Predicate p4 = cb.like(root.get("xxdz").as(String.class), "%"+actiQueryDTO.getXxdz()+"%");
					list.add(p4);
				}
				//3.Predicate查询条件集合的size创建对应的Predicate查询条件数组
				Predicate[] p = new Predicate[list.size()];
				//4.CirteriaBuilder的and函数组装  查询条件数组
				return cb.and(list.toArray(p));	
				
				  
				/*Date now = new Date(System.currentTimeMillis());
				
				
				//Predicate beginTime = cb.like(root.get("endTime").as(Date.class), "%"+actiQueryDTO.getHdlx()+"");
				//Predicate hdlx = cb.between(now,root.get("beginTime").as(Date.class), root.get("endTime").as(Date.class));
				Predicate beginTime = cb.lessThanOrEqualTo(root.get("beginTime").as(Date.class), now);
				Predicate endTime = cb.greaterThanOrEqualTo(root.get("endTime").as(Date.class), now);
				Predicate hdlx = cb.like(root.get("hdlx").as(String.class), "%"+actiQueryDTO.getHdlx()+"");
				Predicate title = cb.like(root.get("title").as(String.class), "%"+actiQueryDTO.getTitle()+"%");
				Predicate fbzz = cb.like(root.get("fbzz").as(String.class), "%"+actiQueryDTO.getFbzz()+"%");
				Predicate xxdz = cb.like(root.get("xxdz").as(String.class), "%"+actiQueryDTO.getXxdz()+"%");				
				Predicate p = cb.or(title,fbzz,xxdz);
			
				if (actiQueryDTO.getQuery() == "进行中") {
					p = cb.and(hdlx,p,beginTime,endTime); 
				}else if (actiQueryDTO.getQuery() == "已结束") {
					p = cb.and(hdlx,p,endTime);
				}else if(actiQueryDTO.getQuery() == "不限时间") {
					p = cb.and(hdlx,p,beginTime,endTime);
				}else if(actiQueryDTO.getQuery() == "最近一周") {
					p = cb.and(hdlx,p,beginTime,endTime);
				}else if(actiQueryDTO.getQuery() == "最近一个月") {
					p = cb.and(hdlx,p,beginTime,endTime);
				}else if(actiQueryDTO.getQuery() == "最近一个季度") {
					p = cb.and(hdlx,p,beginTime,endTime);
				}else if(actiQueryDTO.getQuery() == "不限排序") {
					p = cb.and(hdlx,p,beginTime,endTime);
				}else if(actiQueryDTO.getQuery() == "开始时间先后") {
					p = cb.and(hdlx,p,beginTime,endTime);
				}else if(actiQueryDTO.getQuery() == "浏览人数最多") {
					p = cb.and(hdlx,p,beginTime,endTime);
				}else {
					p = cb.and(hdlx,p);
				}				 
				return p;	*/			
			}
		};		
		return spec;
	}
	
	
	//提供static的工具方法： 根据当前actiQueryDTO对象来组装动态查询条件
		public static Specification<Acti> getSpecification2(ActiQueryDTO actiQueryDTO){
			Specification<Acti> spec = new Specification<Acti>() {
				public Predicate toPredicate(Root<Acti> root, CriteriaQuery<?> query, CriteriaBuilder cb) {							
					/*//1.Predicate查询条件集合
					List<Predicate> list = new ArrayList<Predicate>();				
					//2.根据QueryDTO数据字段的值进行判断以及条件的组装	
					
					//3.Predicate查询条件集合的size创建对应的Predicate查询条件数组
					
					//4.CirteriaBuilder的and函数组装  查询条件数组
						*/
					
					  
					Date now = new Date(System.currentTimeMillis());
					
					
					//Predicate beginTime = cb.like(root.get("endTime").as(Date.class), "%"+actiQueryDTO.getHdlx()+"");
					//Predicate hdlx = cb.between(now,root.get("beginTime").as(Date.class), root.get("endTime").as(Date.class));
					Predicate beginTime = cb.lessThanOrEqualTo(root.get("beginTime").as(Date.class), now);
					Predicate endTime = cb.greaterThanOrEqualTo(root.get("endTime").as(Date.class), now);
					Predicate hdlx = cb.like(root.get("hdlx").as(String.class), "%"+actiQueryDTO.getHdlx()+"");
					Predicate title = cb.like(root.get("title").as(String.class), "%"+actiQueryDTO.getTitle()+"%");
					Predicate fbzz = cb.like(root.get("fbzz").as(String.class), "%"+actiQueryDTO.getFbzz()+"%");
					Predicate xxdz = cb.like(root.get("xxdz").as(String.class), "%"+actiQueryDTO.getXxdz()+"%");				
					Predicate p = cb.or(title,fbzz,xxdz);
				
					if (actiQueryDTO.getQuery() == "进行中") {
						p = cb.and(hdlx,p,beginTime,endTime); 
					}else if (actiQueryDTO.getQuery() == "已结束") {
						p = cb.and(hdlx,p,endTime);
					}else if(actiQueryDTO.getQuery() == "不限时间") {
						p = cb.and(hdlx,p,beginTime,endTime);
					}else if(actiQueryDTO.getQuery() == "最近一周") {
						p = cb.and(hdlx,p,beginTime,endTime);
					}else if(actiQueryDTO.getQuery() == "最近一个月") {
						p = cb.and(hdlx,p,beginTime,endTime);
					}else if(actiQueryDTO.getQuery() == "最近一个季度") {
						p = cb.and(hdlx,p,beginTime,endTime);
					}else if(actiQueryDTO.getQuery() == "不限排序") {
						p = cb.and(hdlx,p,beginTime,endTime);
					}else if(actiQueryDTO.getQuery() == "开始时间先后") {
						p = cb.and(hdlx,p,beginTime,endTime);
					}else if(actiQueryDTO.getQuery() == "浏览人数最多") {
						p = cb.and(hdlx,p,beginTime,endTime);
					}else {
						p = cb.and(hdlx,p);
					}				 
					return p;				
				}
			};		
			return spec;
		}
		
		//提供static的工具方法： 根据当前actiQueryDTO对象来组装动态查询条件
		public static Specification<Acti> getSpecification3(ActiQueryDTO actiQueryDTO){
			Specification<Acti> spec = new Specification<Acti>() {
				public Predicate toPredicate(Root<Acti> root, CriteriaQuery<?> query, CriteriaBuilder cb) {							
					/*//1.Predicate查询条件集合
					List<Predicate> list = new ArrayList<Predicate>();				
					//2.根据QueryDTO数据字段的值进行判断以及条件的组装	
					
					//3.Predicate查询条件集合的size创建对应的Predicate查询条件数组
					
					//4.CirteriaBuilder的and函数组装  查询条件数组
						*/
					
					  
					Date now = new Date(System.currentTimeMillis());
					
					
					Predicate beginTime = cb.lessThanOrEqualTo(root.get("beginTime").as(Date.class), now);
					Predicate endTime = cb.greaterThanOrEqualTo(root.get("endTime").as(Date.class), now);
					//Predicate hdlx = cb.like(root.get("hdlx").as(String.class), "%"+actiQueryDTO.getHdlx()+"");
					Predicate title = cb.like(root.get("title").as(String.class), "%"+actiQueryDTO.getTitle()+"%");
					Predicate fbzz = cb.like(root.get("fbzz").as(String.class), "%"+actiQueryDTO.getFbzz()+"%");
					Predicate xxdz = cb.like(root.get("xxdz").as(String.class), "%"+actiQueryDTO.getXxdz()+"%");				
					Predicate p = cb.or(title,fbzz,xxdz);
					//p = cb.and(hdlx,p);
							 
					return p;				
				}
			};		
			return spec;
		}
		
		//提供static的工具方法： 根据当前actiQueryDTO对象来组装动态查询条件
		public static Specification<Acti> getSpecification4(ActiQueryDTO actiQueryDTO){
			Specification<Acti> spec = new Specification<Acti>() {
				public Predicate toPredicate(Root<Acti> root, CriteriaQuery<?> query, CriteriaBuilder cb) {							
					Predicate fbzz = cb.like(root.get("fbzz").as(String.class), "%"+actiQueryDTO.getFbzz()+"%");
					Predicate content = cb.like(root.get("content").as(String.class), "%"+actiQueryDTO.getContent()+"%");				
					Predicate title = cb.like(root.get("title").as(String.class), "%"+actiQueryDTO.getTitle()+"%");				
					Predicate p = cb.or(fbzz,content,title);
					return p;				
				}
			};		
			return spec;
		}
}
