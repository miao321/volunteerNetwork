package com.volunteer.utils;

import java.util.Date;

import com.xxx.volunterNetwork.domain.Acti;
import com.xxx.volunterNetwork.domain.Borad;
import com.xxx.volunterNetwork.domain.Img;



public class ResultMap {
	public static Acti ReflectMap(String title,String content,String fwyq,String hdjj,String xxdz,
			String fbman,String lxfs,Date BeginTime,Date endTime,Integer duration,String img,String hdlx,String fbzz,Date fbtime,
			Integer state,Integer zmrs) {
		Acti bean = new Acti(title,content,fwyq,hdjj,xxdz,fbman,lxfs,BeginTime,endTime,duration,
				img,hdlx,fbzz,fbtime,state,zmrs);
		return bean;
	}
	public static Img ReflectMap2(String img,Integer state) {
		Img bean = new Img(img,state);
		return bean;
	}
	public static Borad ReflectMap3(String title, String content, String fbman, String img, Date fbtime,String fblx, Integer state) {
		Borad bean = new Borad(title, content, fbman, img, fbtime,fblx, state);
		return bean;
	}
}
