package com.volunteer.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * 
 * @author miao
 * @date 2017�?12�?3日下�?2:58:22
 *
 */
@Entity
@Table(name="t_role")
public class Role implements Serializable {
	private Long id;
	private String roleName;   //角色名字
	private String remark;  //评价  说明
	private Integer orderNo;  //编号  序号
	private String createBy; //创建者id
	private String createCollege;  //创建者学�?
	@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private Date createTime;  //创建时间
	private String updateBy;   //修改者id
	@DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private Date updateTime;   //修改时间
	private Integer state;    //角色状�??
	private Set<User> users = new HashSet<User>();  //角色跟用�?  多对�?
	private Set<Module> modules = new HashSet<Module>();  //角色跟模�?  多对�?
	private List<Permission> permission = new ArrayList<Permission>();
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JsonIgnore
	public Set<User> getUsers() {
		return users;
	}
	
	@ManyToMany(cascade=CascadeType.ALL,mappedBy="roles",fetch=FetchType.EAGER)
	public Set<Module> getModules() {
		return modules;
	}
	@ManyToMany(cascade={CascadeType.MERGE,CascadeType.PERSIST},  
			   mappedBy = "role", targetEntity = Permission.class) 
	@JsonIgnore
	public List<Permission> getPermission() {
		return permission;
	}
	public String getRoleName() {
		return roleName;
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
	/*@ManyToMany(cascade = { CascadeType.PERSIST },targetEntity = Role.class,  fetch = FetchType.EAGER)  
    @JoinTable(name = "t_user_t_role", joinColumns = { @JoinColumn(name = "user_id", updatable = true) }, inverseJoinColumns = { @JoinColumn(name = "role_id", updatable = true) })  
    @Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE })  
    */

	
	public Integer getState() {
		return state;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}
	public void setPermission(List<Permission> permission) {
		this.permission = permission;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	/*@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", remark=" + remark + ", orderNo=" + orderNo
				+ ", createBy=" + createBy + ", createCollege=" + createCollege + ", createTime=" + createTime
				+ ", updateBy=" + updateBy + ", updateTime=" + updateTime + ", state=" + state + ", users=" + users
				+ ", modules=" + modules + ", permission=" + permission + "]";
	}*/
	
}
