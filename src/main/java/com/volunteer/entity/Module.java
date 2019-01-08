package com.volunteer.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 
 * @author miao
 * @date 2017�?12�?3日下�?2:58:13
 *
 */
@Entity
@Table(name="t_module")
public class Module implements Serializable {
	private Long id;
	private String parentId;   //父模块的id
	private String parentName;  //父模块的名字  冗余 用空间换时间
	private String moduleName;        //模块名字
	private Integer layerNum;   //层数
	private Integer isLeaf;     //叶子节点
	private String icon;        //图片
	private String cpermission;  //权限
	private String curl;   //链接地址
	private Integer ctype;       //类型  1主菜�? 2左侧菜单 3按钮 4链接 5状�??
	private Integer state;       //状�??  1启用   2停用
	private String belong;       //从属
	private String remark;       //备注
	private Integer orderNo;     //序号   编号
	private String createBy; //创建者id
	private String createCollege;  //创建者学�?
	@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private Date createTime;  //创建时间
	private String updateBy;   //修改者id
	@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private Date updateTime;   //修改时间	
	private Set<Role> roles = new HashSet<Role>();  //模块跟角�?  多对�?
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	public Set<Role> getRoles() {
		return roles;
	}
	
	public String getParentId() {
		return parentId;
	}
	public String getParentName() {
		return parentName;
	}
	public String getModuleName() {
		return moduleName;
	}
	public Integer getLayerNum() {
		return layerNum;
	}
	public Integer getIsLeaf() {
		return isLeaf;
	}
	public String getIcon() {
		return icon;
	}
	public String getCpermission() {
		return cpermission;
	}
	public String getCurl() {
		return curl;
	}
	public Integer getCtype() {
		return ctype;
	}
	public Integer getState() {
		return state;
	}
	public String getBelong() {
		return belong;
	}
	public String getRemark() {
		return remark;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public String getCreateBy() {
		return createBy;
	}
	public String getCreateCollege() {
		return createCollege;
	}
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public void setLayerNum(Integer layerNum) {
		this.layerNum = layerNum;
	}
	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public void setCpermission(String cpermission) {
		this.cpermission = cpermission;
	}
	public void setCurl(String curl) {
		this.curl = curl;
	}
	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public void setBelong(String belong) {
		this.belong = belong;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public void setCreateCollege(String createCollege) {
		this.createCollege = createCollege;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
