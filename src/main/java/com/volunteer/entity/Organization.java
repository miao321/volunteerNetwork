package com.volunteer.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 
 * @author miao
 * @date 2017�?12�?2日下�?7:48:28
 *
 */
@Entity
@Table(name="t_organization")
public class Organization implements Serializable {
	private Long id;
	private String collegeName;  //学院名字
	/*private String major;        //专业名字
*/	private String organization;//组织
	private String xjorganization;//下级组织
	private String img;//组织标识图片
	private Integer state;       //1启用  0停用
	private String menNum;
	private User user;//用户跟学院是�?对多关系	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	@JsonIgnore
	public User getUser() {
		return user;
	}
	
	public String getCollegeName() {
		return collegeName;
	}
	/*public String getMajor() {
		return major;
	}*/
	public Integer getState() {
		return state;
	}	
	public String getOrganization() {
		return organization;
	}
	public String getXjorganization() {
		return xjorganization;
	}
	public String getImg() {
		return img;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	/*public void setMajor(String major) {
		this.major = major;
	}*/
	public void setState(Integer state) {
		this.state = state;
	}
	public void setUser(User user) {
		this.user = user;
	}	
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public void setXjorganization(String xjorganization) {
		this.xjorganization = xjorganization;
	}
	public void setImg(String img) {
		this.img = img;
	}
	/*@Override
	public String toString() {
		return "College [id=" + id + ", collegeName=" + collegeName + ", major=" + major + ", state=" + state
				+ ", user=" + user + "]";
	}*/
	public String getMenNum() {
		return menNum;
	}
	public void setMenNum(String menNum) {
		this.menNum = menNum;
	}
	
	
	
}
