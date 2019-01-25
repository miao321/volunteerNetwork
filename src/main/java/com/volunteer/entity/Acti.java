
package com.volunteer.entity;
/**
 * @Description: TODO
 *
 * @author miaominfeng
 * @date 2019年1月25日 下午3:40:40
 * @version V1.0
 * @Company: 深圳卫宁中天软件有限公司 *
 * @Copyright (c) 2019, miaominfeng@winning.com.cn All Rights Reserved. 
 */

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
//@Entity
//@Table(name="t_acti")
public class Acti {
	private Long id;
	private String title;  //标题
	private String content; //服务内容
	private String fwyq;//服务要求
	private String hdjj;//活动�?�?
	private String xxdz;//详细地址
	private String fbman;//联系�?
	private String lxfs;//联系方式
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date beginTime;//活动�?始时�?
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date endTime;//活动结束时间
	private String img;//图片
	private String hdlx;//活动类型
	private String fbzz;//发布组织
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date fbtime;//发布时间
	private Integer state;//状�??
	private Integer zmrs;//招募人数
	private Integer zan;//点赞次数
	private Integer attention;//关注
	private String attentionName;//关注人名�?
	private Integer duration;//时长
	public Acti() {
		super();		
	}
	public Acti(Long id, String title, String content, String fwyq, String hdjj, String xxdz,String fbman, String lxfs, Date beginTime,Date endTime,
			String img, String hdlx, String fbzz, Date fbtime, Integer state, Integer zmrs, Integer zan,
			Integer attention) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.fwyq = fwyq;
		this.hdjj = hdjj;
		this.xxdz = xxdz;
		this.fbman = fbman;
		this.lxfs = lxfs;
		this.beginTime = beginTime;
		this.endTime = endTime;
		
		this.img = img;
		this.hdlx = hdlx;
		this.fbzz = fbzz;
		this.fbtime = fbtime;
		this.state = state;
		this.zmrs = zmrs;
		this.zan = zan;
		this.attention = attention;
	}
	public Acti(String title, String content, String fwyq, String hdjj, String xxdz,String fbman, String lxfs, Date beginTime,Date endTime,
			Integer duration,String img, String hdlx, String fbzz, Date fbtime, Integer state, Integer zmrs) {	
		this.title = title;
		this.content = content;
		this.fwyq = fwyq;
		this.hdjj = hdjj;
		this.xxdz = xxdz;
		this.fbman = fbman;
		this.lxfs = lxfs;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.duration = duration;
		this.img = img;
		this.hdlx = hdlx;
		this.fbzz = fbzz;
		this.fbtime = fbtime;
		this.state = state;
		this.zmrs = zmrs;
		this.zan = zan;
		this.attention = attention;
	}
	
	//@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getFwyq() {
		return fwyq;
	}
	public String getHdjj() {
		return hdjj;
	}
	public String getXxdz() {
		return xxdz;
	}
	public String getLxfs() {
		return lxfs;
	}
	@JsonFormat(pattern = "yyyy/MM/dd",timezone = "GMT+8")
	public Date getBeginTime() {
		return beginTime;
	}
	@JsonFormat(pattern = "yyyy/MM/dd",timezone = "GMT+8")
	public Date getEndTime() {
		return endTime;
	}
	public String getImg() {
		return img;
	}
	public String getHdlx() {
		return hdlx;
	}
	public String getFbzz() {
		return fbzz;
	}
	@JsonFormat(pattern = "yyyy/MM/dd",timezone = "GMT+8")
	public Date getFbtime() {
		return fbtime;
	}
	public Integer getState() {
		return state;
	}
	public Integer getZmrs() {
		return zmrs;
	}
	public Integer getZan() {
		return zan;
	}
	public Integer getAttention() {
		return attention;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setFwyq(String fwyq) {
		this.fwyq = fwyq;
	}
	public void setHdjj(String hdjj) {
		this.hdjj = hdjj;
	}
	public void setXxdz(String xxdz) {
		this.xxdz = xxdz;
	}
	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public void setHdlx(String hdlx) {
		this.hdlx = hdlx;
	}
	public void setFbzz(String fbzz) {
		this.fbzz = fbzz;
	}
	public void setFbtime(Date fbtime) {
		this.fbtime = fbtime;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public void setZmrs(Integer zmrs) {
		this.zmrs = zmrs;
	}
	public void setZan(Integer zan) {
		this.zan = zan;
	}
	public void setAttention(Integer attention) {
		this.attention = attention;
	}
	public String getFbman() {
		return fbman;
	}
	public void setFbman(String fbman) {
		this.fbman = fbman;
	}
	public String getAttentionName() {
		return attentionName;
	}
	public void setAttentionName(String attentionName) {
		this.attentionName = attentionName;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	

}

