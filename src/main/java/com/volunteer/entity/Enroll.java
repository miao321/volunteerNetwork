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
@Table(name="t_enroll")
public class Enroll {
	private Long id;
	private String userName;//用户�?
	private String studentNo;//学号
	private String college;//学院
	private String major;//专业
	private String sex;//性别
	private String phone; //手机�?
	private String idCard;//身份证号
	private String organization;//组织
	private String xjorganization;//下级组织
	private Integer duration;//时长
	private String img;//头像
	private Integer state;//状�??
	private String remark;//备注
	private String hdName;//活动名字
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date hdTime;//活动�?始时�? 
	private String hdType;//活动类别
	private Integer pxTime;//培训时长
	private Long actiId;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public String getUserName() {
		return userName;
	}
	public String getStudentNo() {
		return studentNo;
	}
	public String getCollege() {
		return college;
	}
	public String getMajor() {
		return major;
	}
	public String getSex() {
		return sex;
	}
	public String getIdCard() {
		return idCard;
	}
	public String getOrganization() {
		return organization;
	}
	public String getXjorganization() {
		return xjorganization;
	}
	public Integer getDuration() {
		return duration;
	}
	public String getImg() {
		return img;
	}
	@JsonFormat(pattern = "yyyy/MM/dd",timezone = "GMT+8")
	public Date getHdTime() {
		return hdTime;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public void setXjorganization(String xjorganization) {
		this.xjorganization = xjorganization;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getHdName() {
		return hdName;
	}
	public void setHdName(String hdName) {
		this.hdName = hdName;
	}
	public void setHdTime(Date hdTime) {
		this.hdTime = hdTime;
	}
	public String getHdType() {
		return hdType;
	}
	public void setHdType(String hdType) {
		this.hdType = hdType;
	}
	public Integer getPxTime() {
		return pxTime;
	}
	public void setPxTime(Integer pxTime) {
		this.pxTime = pxTime;
	}
	public Long getActiId() {
		return actiId;
	}
	public void setActiId(Long actiId) {
		this.actiId = actiId;
	}
	

}
