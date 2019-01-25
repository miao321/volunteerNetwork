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

import com.xxx.volunterNetwork.domain.Permission;
import com.xxx.volunterNetwork.domain.Acti;
import com.xxx.volunterNetwork.domain.Img;
import com.xxx.volunterNetwork.dto.PermissionQueryDTO;
import com.xxx.volunterNetwork.dto.ImgQueryDTO;
import com.xxx.volunterNetwork.service.IPermissionService;
import com.xxx.volunterNetwork.service.IImgService;
import com.xxx.volunterNetwork.util.ExtAjaxResponse;
import com.xxx.volunterNetwork.util.ExtJsonResult;
import com.xxx.volunterNetwork.util.ExtPageable;
import com.xxx.volunterNetwork.util.ResultMap;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午11:09:18
 *
 */

@Controller
@RequestMapping("/img")
public class ImgController {
	private static final Logger logger = LoggerFactory.getLogger(ImgController.class);
	@Autowired
	private IImgService imgService;
	@Autowired
	private IPermissionService permissionService;
	
	@RequestMapping("/saveOrUpdate")
//	@RequiresPermissions("img/saveOrUpdate")
//	@RequiresImgs("管理员")
	public @ResponseBody ExtAjaxResponse saveOrUpdate(Img img) {
		
		/*if (imgService.findImg(img.getImgName()) != null) {
			return new ExtAjaxResponse(false, "该角色已经存在不用再添加");
		}*/
		try {
			imgService.saveOrUpdate(img);
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}	
	}
	
	
	@RequestMapping("/delete")
//	@RequiresPermissions("img/delete")
//	@RequiresImgs("管理员")
	public @ResponseBody ExtAjaxResponse delete(@RequestParam Long id) {
		try {
			Img img = imgService.findOne(id);
			imgService.delete(img);
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}		
	}
	@RequestMapping("/deleteImgs")
//	@RequiresPermissions("img/deleteImgs")
//	@RequiresImgs("管理员")
	public @ResponseBody ExtAjaxResponse delete(@RequestParam Long[] ids) {
		try {
			List<Long> idsLists = Arrays.asList(ids);
			if (idsLists != null) {
				imgService.delete(idsLists);
			}
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}
	}	
	@RequestMapping("/findOne")
	public @ResponseBody Img findOne(@RequestParam Long id) {
		Img img = imgService.findOne(id);
		return img;
	}	
	
	@RequestMapping("/findAll")
	public @ResponseBody ExtJsonResult<Img> findAll(HttpSession session){
		List<Img> imgLists = imgService.findAll();
		session.setAttribute("imgLists", imgLists);
		System.out.println("imgLists:"+imgLists);
		return new ExtJsonResult<Img>(imgLists);
	}
	@RequestMapping("/findPage")
	public String findPage(HttpSession session,ImgQueryDTO imgQueryDTO,ExtPageable extPageable){
		Page<Img> page = imgService.findAll(imgQueryDTO.getSpecification(imgQueryDTO), extPageable.getPageable());
		session.setAttribute("imgLists", page.getContent());//内容
		System.out.println(" page.getContent():+"+ page.getContent());
		session.setAttribute("pageNumber", page.getNumber());//当前页
		session.setAttribute("pageSize", page.getSize());//当前页条数
		session.setAttribute("pageTotalPages", page.getTotalPages());//共几页
		session.setAttribute("pageTotalElements", page.getTotalElements());//总条数
		return "/WEB-INF/pages/blog/share";
		
	}
	
	@RequestMapping("/findImg")
	public String findImg(HttpSession session) {
		List<Img> img = imgService.findImg();
		session.setAttribute("imgLists", img);
		return "/WEB-INF/pages/front/banFront";
	}
	
	@RequestMapping("disableImg")
	public @ResponseBody Img disableImg(@RequestParam Long id) {
		Img img = imgService.findOne(id);
		if (img.getState() != null) {
			imgService.updateState(id, 0);
		}
		return img;
	}
	@RequestMapping("enableImg")
	public @ResponseBody Img enableImg(@RequestParam Long id) {
		Img img = imgService.findOne(id);
		if (img.getState() != null) {
			imgService.updateState(id, 1);
		}
		return img;
	}
	
	@RequestMapping("uploadImg")
	public String upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {	
			String real_img = null;
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
					if (filename.equalsIgnoreCase("state")) {
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
			Img bean = ResultMap.ReflectMap2( real_img,real_state);
			imgService.saveOrUpdate(bean);
			return "/WEB-INF/pages/front/activityDetail";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
