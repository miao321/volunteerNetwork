package com.volunteer.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxx.volunterNetwork.anno.SysControllerLog;
import com.xxx.volunterNetwork.domain.Acti;
import com.xxx.volunterNetwork.domain.Borad;
import com.xxx.volunterNetwork.domain.Module;
import com.xxx.volunterNetwork.dto.BoradQueryDTO;
import com.xxx.volunterNetwork.dto.ModuleQueryDTO;
import com.xxx.volunterNetwork.service.IBoradService;
import com.xxx.volunterNetwork.util.ExtAjaxResponse;
import com.xxx.volunterNetwork.util.ExtPageable;
import com.xxx.volunterNetwork.util.ResultMap;
@Controller
@RequestMapping("/borad")
public class BoradController {
	@Autowired
	private IBoradService boradService;
	
	@RequestMapping("/saveOrUpdate")
	@SysControllerLog(module="添加公告",methods="添加公告")
	public String saveOrUpdate(Borad borad) {		
		try {		
			//boradService.saveOrUpdate(borad);
			//return new ExtAjaxResponse(true, "操作成功");
			return "/WEB-INF/pages/borad/addBorad";
		} catch (Exception e) {
			//return new ExtAjaxResponse(false, "操作失败");
			return "/WEB-INF/pages/borad/addBorad";
		}	
	}
	@RequestMapping("/update")
	@SysControllerLog(module="修改公告",methods="修改公告")
	public @ResponseBody ExtAjaxResponse update(Borad borad) {
		/*if (collegeService.findCollege(college.getCollegeName()) != null) {
			return new ExtAjaxResponse(false, "该部门已经存在 不用再添加");
		}*/
		Borad b = boradService.findOne(borad.getId());
		
		try {
			boradService.saveOrUpdate(b);
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}	
	}	
	@RequestMapping("/delete")
	@SysControllerLog(module="添加公告",methods="删除公告")
	public @ResponseBody ExtAjaxResponse delete(@RequestParam Long id) {
		try {
			Borad borad = boradService.findOne(id);
			if (borad != null) {
				boradService.delete(borad);
			}
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}		
	}
	@RequestMapping("/deleteborads")
	@SysControllerLog(module="添加公告",methods="批量删除公告")
	public @ResponseBody ExtAjaxResponse delete(@RequestParam Long[] ids) {
		try {
			List<Long> idsLists = Arrays.asList(ids);
			if (idsLists != null) {
				boradService.delete(idsLists);
			}
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}
	}	
	@RequestMapping("/findOne")
	@SysControllerLog(module="添加公告",methods="查看一条公告")
	public @ResponseBody Borad findOne(@RequestParam Long id,HttpSession session) {
		Borad borad = boradService.findOne(id);
		session.setAttribute("borad", borad);
		return borad;
	}	
	@RequestMapping("/findAll")
	public @ResponseBody List<Borad> findAll(){
		List<Borad>  boradLists = boradService.findAll();
		return  boradLists;		
	}	
	@RequestMapping("/findPage")
	@SysControllerLog(module="公告管理",methods="查找所有数据并分页排序")
	public String findPage(@RequestParam(value="query",required=false, defaultValue="") String query,HttpSession session,BoradQueryDTO boradQueryDTO,ExtPageable extPageable){
		boradQueryDTO.setFbman(query);
		boradQueryDTO.setFblx(query);
		boradQueryDTO.setContent(query);
		boradQueryDTO.setTitle(query);
		Page<Borad> page = boradService.findAll(boradQueryDTO.getSpecification2(boradQueryDTO), extPageable.getPageable());
		session.setAttribute("boradLists", page.getContent());//内容
		session.setAttribute("pageNumber", page.getNumber());//当前页
		session.setAttribute("pageSize", page.getSize());//当前页条数
		session.setAttribute("pageTotalPages", page.getTotalPages());//共几页
		session.setAttribute("pageTotalElements", page.getTotalElements());//总条数
		return "/WEB-INF/pages/borad/seeBorad";	
	}
	
	@RequestMapping("disableborad")
	@SysControllerLog(module="添加公告",methods="禁用公告")
	public @ResponseBody Borad disableBorad(@RequestParam Long id) {
		Borad borad = boradService.findOne(id);
		if (borad.getState() != null) {
			boradService.updateState(id, 0);
		}
		return borad;
	}
	@RequestMapping("enableborad")
	@SysControllerLog(module="添加公告",methods="启用公告")
	public @ResponseBody Borad enableBorad(@RequestParam Long id) {
		Borad borad = boradService.findOne(id);
		if (borad.getState() != null) {
			boradService.updateState(id, 1);
		}
		return borad;
	}
	
	@RequestMapping("uploadImg")
	@SysControllerLog(module="添加公告",methods="添加公告")
	public String upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {				
			String real_title = null;
			String real_content = null;			
			String real_fbman = null;			
			String real_img = null;			
			Date real_fbtime = null;
			String real_fblx = null;	
			Integer real_state = null;
			DiskFileItemFactory factory = new DiskFileItemFactory();
			File f = new File("e:/target");
			if (!f.exists()) {
				f.mkdir();
			}
			factory.setRepository(f);
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> fileitems = upload.parseRequest(request);
			// 遍历
			for (FileItem fileitem : fileitems) {
				// 判断是文件还是字符串
				if (fileitem.isFormField()) {
					// 文件
					String filename = fileitem.getFieldName();
					String value = fileitem.getString("utf-8");
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					if (filename.equalsIgnoreCase("title")) {
						real_title = value;
					} else if (filename.equalsIgnoreCase("content")) {
						real_content = value;	
						System.out.println("content:"+real_content);
					}else if (filename.equalsIgnoreCase("fbman")) {
						real_fbman = value;
					}else if (filename.equalsIgnoreCase("fbtime")) {
						real_fbtime = dateFormat.parse(value);
					}else if (filename.equalsIgnoreCase("fblx")) {
						real_fblx = value;
					}else if (filename.equalsIgnoreCase("state")) {
						real_state = Integer.valueOf(value);
					}
				} else {
					// 文件名
					String filename = fileitem.getName();
					filename = filename
							.substring(filename.lastIndexOf("/") + 1);
					// 随机编辑名称
					filename = filename.substring(filename.lastIndexOf("."));
					filename = UUID.randomUUID().toString() + filename;
					// 获取完整的名称
					String webpath = "/upload/";
					String filepath = request.getServletContext().getRealPath(
							webpath + filename);
					real_img = webpath + filename;
					// 创建文件
					File file = new File(filepath);
					file.getParentFile().mkdirs();
					file.createNewFile();
					// 获取图像文件
					BufferedImage image = ImageIO.read(fileitem
							.getInputStream());
					// 获取输入流
					InputStream in = fileitem.getInputStream();
					// 获取输出流
					OutputStream out = new FileOutputStream(file);
					// 文件对拷
					byte[] buffer = new byte[1024];
					int len;
					while ((len = in.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
					// 关闭流
					in.close();
					out.close();
					// 删除缓存文件
					fileitem.delete();
				}
			}
			Borad bean = ResultMap.ReflectMap3(real_title, real_content,
					real_fbman,  real_img,
					 new Date(System.currentTimeMillis()),real_fblx, real_state);
			boradService.saveOrUpdate(bean);
			return "/WEB-INF/pages/borad/seeBorad";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
