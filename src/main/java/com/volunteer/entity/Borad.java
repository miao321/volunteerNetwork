package com.volunteer.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

//@Entity
//@Table(name="t_borad")
public class Borad {
	private Long id;
	private String title;    //公告标题
	private String content;  //公告内容
	private String fbman;    //发布�?
	private String img;      //照片
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date fbtime;     //发布时间
	private String fblx;     //发布类型
	private Integer state;   //显示状�??
	
	public Borad() {
		super();
	}
	public Borad(String title, String content, String fbman, String img, Date fbtime,String fblx, Integer state) {
		super();		
		this.title = title;
		this.content = content;
		this.fbman = fbman;
		this.img = img;
		this.fbtime = fbtime;
		this.fblx = fblx;
		this.state = state;
	}
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getFbman() {
		return fbman;
	}
	public Date getFbtime() {
		return fbtime;
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
	public void setFbman(String fbman) {
		this.fbman = fbman;
	}
	@JsonFormat(pattern = "yyyy/MM/dd",timezone = "GMT+8")
	public void setFbtime(Date fbtime) {
		this.fbtime = fbtime;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getFblx() {
		return fblx;
	}
	public void setFblx(String fblx) {
		this.fblx = fblx;
	}

}
