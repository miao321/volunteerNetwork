package com.volunteer.utils;

import java.util.Date;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.StringIdGenerator;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:59:47
 *
 */
public class ExtResponse {
	private boolean success= true;
	private String msg= "";
	private Long id;
	private String userNo;//职员编号
	private String userName;//用户姓名
	private String password;//用户密码
	private String userNickName;//用户昵称
	private String sex;   //性别
	@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private Date birthday;//出生日期
	private Integer age;//年龄
	private String nativePlace;//籍贯
	private String nation;//民族
	private String culture;//文化程度
	private String college;//毕业学校
	private String body;//健康情况
	private String marriage;//婚姻状况
	private String idCord;//身份证号码
	private String phone;//手机号
	private String familyPhone;//家庭电话
	private String email;//电子邮箱地址
	private String userAccount;//职工账号
	private String deptName;//部门编号
	private String remark;//备注
	public ExtResponse() {
		
	}
	public ExtResponse(boolean success,String msg) {
		this.success = success;
		this.msg = msg;
		
	}
	public ExtResponse(boolean success, String msg, Long id, String userNo, String userName, String password,
			String userNickName, String sex, Date birthday, Integer age, String nativePlace, String nation,
			String culture, String college, String body, String marriage, String idCord, String phone,
			String familyPhone, String email, String userAccount, String deptName, String remark) {
		this.success = success;
		this.msg = msg;
		this.id = id;
		this.userNo = userNo;
		this.userName = userName;
		this.password = password;
		this.userNickName = userNickName;
		this.sex = sex;
		this.birthday = birthday;
		this.age = age;
		this.nativePlace = nativePlace;
		this.nation = nation;
		this.culture = culture;
		this.college = college;
		this.body = body;
		this.marriage = marriage;
		this.idCord = idCord;
		this.phone = phone;
		this.familyPhone = familyPhone;
		this.email = email;
		this.userAccount = userAccount;
		this.deptName = deptName;
		this.remark = remark;
	}
	public boolean isSuccess() {
		return success;
	}
	public String getMsg() {
		return msg;
	}
	public Long getId() {
		return id;
	}
	public String getUserNo() {
		return userNo;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public String getUserNickName() {
		return userNickName;
	}
	public String getSex() {
		return sex;
	}
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
	public Date getBirthday() {
		return birthday;
	}
	public Integer getAge() {
		return age;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public String getNation() {
		return nation;
	}
	public String getCulture() {
		return culture;
	}
	public String getCollege() {
		return college;
	}
	public String getBody() {
		return body;
	}
	public String getMarriage() {
		return marriage;
	}
	public String getIdCord() {
		return idCord;
	}
	public String getPhone() {
		return phone;
	}
	public String getFamilyPhone() {
		return familyPhone;
	}
	public String getEmail() {
		return email;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public String getDeptName() {
		return deptName;
	}
	public String getRemark() {
		return remark;
	}
	

}
