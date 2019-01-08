package com.volunteer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_img")
public class Img {
	private Long id;
	private String img;
	private Integer state;
	
	public Img() {
		super();
	}
	public Img(String img, Integer state) {
		super();
		this.img = img;
		this.state = state;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public String getImg() {
		return img;
	}
	public Integer getState() {
		return state;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Img [id=" + id + ", img=" + img + ", state=" + state + "]";
	}
	
}
