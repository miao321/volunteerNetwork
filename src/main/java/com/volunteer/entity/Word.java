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
@Table(name="t_word")
public class Word {
	private Long id;
	private String title;//标题
	private String content;//内容
	private String author;//作�??
	@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private Date fbtime;//发布时间
	private String img;//头像
	private String resp;//回复条数
	private Integer state;//状�??
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getAuthor() {
		return author;
	}
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
	public Date getFbtime() {
		return fbtime;
	}
	public String getImg() {
		return img;
	}
	public String getResp() {
		return resp;
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
	public void setContent(String content) {
		this.content = content;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setFbtime(Date fbtime) {
		this.fbtime = fbtime;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public void setResp(String resp) {
		this.resp = resp;
	}
	public void setState(Integer state) {
		this.state = state;
	}

}
