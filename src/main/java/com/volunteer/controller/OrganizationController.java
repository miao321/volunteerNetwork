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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxx.volunterNetwork.anno.SysControllerLog;
import com.xxx.volunterNetwork.domain.Organization;
import com.xxx.volunterNetwork.dto.OrganizationQueryDTO;
import com.xxx.volunterNetwork.service.IOrganizationService;
import com.xxx.volunterNetwork.util.ExtAjaxResponse;
import com.xxx.volunterNetwork.util.ExtPageable;
import com.xxx.volunterNetwork.util.ResultMap;



@Controller
@RequestMapping("/organization")
public class OrganizationController {
	private static final Logger logger = LoggerFactory.getLogger(OrganizationController.class);
	@Autowired
	private IOrganizationService organizationService;
	
	String sPath = System.getProperty("evan.webapp");
	@RequestMapping("/saveOrUpdate")
	@SysControllerLog(module="组织管理",methods="添加组织")
	public @ResponseBody ExtAjaxResponse saveOrUpdate(Organization organization) {
		
		/*if (organizationService.findOrganization(organization.getOrganizationName()) != null) {
			return new ExtAjaxResponse(false, "该模块已经存在不用再添加");
		}*/
		try {
			organizationService.saveOrUpdate(organization);
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}	
	}
	@RequestMapping("/update")
	@SysControllerLog(module="组织管理",methods="修改组织信息")
	public @ResponseBody ExtAjaxResponse update(Organization organization) {
		/*if (collegeService.findCollege(college.getCollegeName()) != null) {
			return new ExtAjaxResponse(false, "该部门已经存在 不用再添加");
		}*/
		Organization m = organizationService.findOne(organization.getId());
		
		try {
			organizationService.saveOrUpdate(m);
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}	
	}	
	@RequestMapping("/delete")
	@SysControllerLog(module="组织管理",methods="删除组织信息")
	public @ResponseBody ExtAjaxResponse delete(@RequestParam Long id) {
		try {
			Organization organization = organizationService.findOne(id);
			if (organization != null) {
				organizationService.delete(organization);
			}
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}		
	}
	@RequestMapping("/deleteOrganizations")
	@SysControllerLog(module="组织管理",methods="批量删除组织信息")
	public @ResponseBody ExtAjaxResponse delete(@RequestParam Long[] ids) {
		try {
			List<Long> idsLists = Arrays.asList(ids);
			if (idsLists != null) {
				organizationService.delete(idsLists);
			}
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}
	}	
	@RequestMapping("/findOne")
	@SysControllerLog(module="组织管理",methods="查看组织信息")
	public @ResponseBody Organization findOne(@RequestParam Long id,HttpSession session) {
		Organization organization = organizationService.findOne(id);
		session.setAttribute("organization", organization);
		return organization;
	}	
	@RequestMapping("/findAll")
	public @ResponseBody List<Organization> findAll(HttpSession session){
		List<Organization> organizationLists = organizationService.findAll();
		return organizationLists;		
	}	
	@RequestMapping("/findPage")
	@SysControllerLog(module="组织管理",methods="查找所有数据并分页排序")
	public String findPage(@RequestParam(value="query",required=false, defaultValue="") String query,HttpSession session,OrganizationQueryDTO organizationQueryDTO,ExtPageable extPageable){
		organizationQueryDTO.setCollegeName(query);
		organizationQueryDTO.setOrganization(query);
		organizationQueryDTO.setXjorganization(query);
		Page<Organization> page = organizationService.findAll(organizationQueryDTO.getSpecification(organizationQueryDTO), extPageable.getPageable());
		System.out.println("organizationLists:+++"+page.getContent());
		session.setAttribute("organizationLists", page.getContent());//内容
		session.setAttribute("pageNumber", page.getNumber());//当前页
		session.setAttribute("pageSize", page.getSize());//当前页条数
		session.setAttribute("pageTotalPages", page.getTotalPages());//共几页
		session.setAttribute("pageTotalElements", page.getTotalElements());//总条数
		return "/WEB-INF/pages/system/organization";	
	}
	
	@RequestMapping("disableOrganization")
	@SysControllerLog(module="组织管理",methods="禁用组织")
	public @ResponseBody Organization disableOrganization(@RequestParam Long id) {
		Organization organization = organizationService.findOne(id);
		if (organization.getState() != null) {
			organizationService.updateState(id, 0);
		}
		return organization;
	}
	@RequestMapping("enableOrganization")
	@SysControllerLog(module="组织管理",methods="启用组织")
	public @ResponseBody Organization enableOrganization(@RequestParam Long id) {
		Organization organization = organizationService.findOne(id);
		if (organization.getState() != null) {
			organizationService.updateState(id, 1);
		}
		return organization;
	}
	@RequestMapping("uploadImg")
	@SysControllerLog(module="组织管理",methods="上传信息")
	public String upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {	
			
			String real_title = null;
			String real_content = null;
			String real_fwyq = null;
			String real_hdjj = null;
			String real_xxdz = null;
			String real_fbman = null;
			String real_lxfs = null;
			
			Date real_beginTime = null;
			Date real_endTime = null;
			String real_img = null;
			String real_hdlx = null;
			String real_fbzz = null;
			Date real_fbtime = null;
			Integer real_state = null;
			Integer real_zmrs = null;
			
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
						System.out.println("real_content:"+real_content);
					} else if (filename.equalsIgnoreCase("fwyp")) {
						real_fwyq = value;
						System.out.println("real_fwyq:"+real_fwyq);
					} else if (filename.equalsIgnoreCase("hdjj")) {
						real_hdjj = value;
					}else if (filename.equalsIgnoreCase("xxdz")) {
						real_xxdz = value;
					}else if (filename.equalsIgnoreCase("fbman")) {
						real_fbman = value;
					}else if (filename.equalsIgnoreCase("lxfs")) {
						real_lxfs = value;
					}else if (filename.equalsIgnoreCase("beginTime")) {
						real_beginTime = dateFormat.parse(value);
					}else if (filename.equalsIgnoreCase("endTime")) {
						real_endTime = dateFormat.parse(value);
					}else if (filename.equalsIgnoreCase("hdlx")) {
						real_hdlx = value;
					}else if (filename.equalsIgnoreCase("fbzz")) {
						real_fbzz = value;
					}else if (filename.equalsIgnoreCase("fbtime")) {
						real_fbtime = dateFormat.parse(value);
						System.out.println("real_fbtime:"+real_fbtime);
					}else if (filename.equalsIgnoreCase("zmrs")) {
						real_zmrs = Integer.valueOf(value);
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
			/*Organization bean = ResultMap.ReflectMap(real_title, real_content,
					real_fwyq, real_hdjj, real_xxdz,real_fbman, real_lxfs,real_beginTime,real_endTime, real_img,
					real_hdlx, real_fbzz, new Date(System.currentTimeMillis()), real_state, real_zmrs);
			organizationService.saveOrUpdate(bean);
			*/return "/WEB-INF/pages/front/organizationvityDetail";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
