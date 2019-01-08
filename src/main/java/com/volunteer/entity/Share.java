package com.volunteer.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="t_share")
public class Share {
	private Long id;
	private String title;  //标题
	private String author;//作�??
	private String endResp;//�?后回�?
	private String respName;//�?后回复的名字
	@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private Date fbtime;//发布时间
	private String img;//头像
	private String content;//内容
	private String resp;//回复
	private String zan;//点赞
	private Integer state;//状�??
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public String getEndResp() {
		return endResp;
	}
	public String getRespName() {
		return respName;
	}
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
	public Date getFbtime() {
		return fbtime;
	}
	public String getImg() {
		return img;
	}
	public String getContent() {
		return content;
	}
	public String getResp() {
		return resp;
	}
	public String getZan() {
		return zan;
	}
	public Integer getState() {
		return state;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setEndResp(String endResp) {
		this.endResp = endResp;
	}
	public void setRespName(String respName) {
		this.respName = respName;
	}
	public void setFbtime(Date fbtime) {
		this.fbtime = fbtime;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setResp(String resp) {
		this.resp = resp;
	}
	public void setZan(String zan) {
		this.zan = zan;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	

}
