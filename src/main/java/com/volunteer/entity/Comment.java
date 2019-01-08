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
@Table(name="t_comment")
public class Comment {
	private Long id;
	private Long parentId; //父id
	private Long userId;  //作�??
	private String content;//头像
	@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private Date respTime;//回复时间
	private String zan;//点赞
	private Integer state;//状�??
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public Long getParentId() {
		return parentId;
	}
	public Long getUserId() {
		return userId;
	}
	public String getContent() {
		return content;
	}
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
	public Date getRespTime() {
		return respTime;
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
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setRespTime(Date respTime) {
		this.respTime = respTime;
	}
	public void setZan(String zan) {
		this.zan = zan;
	}
	public void setState(Integer state) {
		this.state = state;
	}

}
