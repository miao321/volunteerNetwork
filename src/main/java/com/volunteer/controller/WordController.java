package com.volunteer.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxx.volunterNetwork.domain.Permission;
import com.xxx.volunterNetwork.domain.Word;
import com.xxx.volunterNetwork.dto.PermissionQueryDTO;
import com.xxx.volunterNetwork.dto.WordQueryDTO;
import com.xxx.volunterNetwork.service.IPermissionService;
import com.xxx.volunterNetwork.service.IWordService;
import com.xxx.volunterNetwork.util.ExtAjaxResponse;
import com.xxx.volunterNetwork.util.ExtJsonResult;
import com.xxx.volunterNetwork.util.ExtPageable;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午11:09:18
 *
 */

@Controller
@RequestMapping("/word")
public class WordController {
	private static final Logger logger = LoggerFactory.getLogger(WordController.class);
	@Autowired
	private IWordService wordService;
	@Autowired
	private IPermissionService permissionService;
	
	@RequestMapping("/saveOrUpdate")
//	@RequiresPermissions("word/saveOrUpdate")
//	@RequiresWords("管理员")
	public @ResponseBody ExtAjaxResponse saveOrUpdate(Word word) {
		
		/*if (wordService.findWord(word.getWordName()) != null) {
			return new ExtAjaxResponse(false, "该角色已经存在不用再添加");
		}*/
		try {
			wordService.saveOrUpdate(word);
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}	
	}
	
	@RequestMapping("/delete")
//	@RequiresPermissions("word/delete")
//	@RequiresWords("管理员")
	public @ResponseBody ExtAjaxResponse delete(@RequestParam Long id) {
		try {
			Word word = wordService.findOne(id);
			wordService.delete(word);
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}		
	}
	@RequestMapping("/deleteWords")
//	@RequiresPermissions("word/deleteWords")
//	@RequiresWords("管理员")
	public @ResponseBody ExtAjaxResponse delete(@RequestParam Long[] ids) {
		try {
			List<Long> idsLists = Arrays.asList(ids);
			if (idsLists != null) {
				wordService.delete(idsLists);
			}
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}
	}	
	@RequestMapping("/findOne")
	public @ResponseBody Word findOne(@RequestParam Long id) {
		Word word = wordService.findOne(id);
		return word;
	}	
	
	@RequestMapping("/findAll")
	public @ResponseBody ExtJsonResult<Word> findAll(HttpSession session){
		List<Word> wordLists = wordService.findAll();
		session.setAttribute("wordLists", wordLists);
		System.out.println("wordLists:"+wordLists);
		return new ExtJsonResult<Word>(wordLists);
	}
	@RequestMapping("/findPage")
	public String findPage(HttpSession session,WordQueryDTO wordQueryDTO,ExtPageable extPageable){
		Page<Word> page = wordService.findAll(wordQueryDTO.getSpecification(wordQueryDTO), extPageable.getPageable());
		session.setAttribute("wordLists", page.getContent());//内容
		session.setAttribute("pageNumber", page.getNumber());//当前页
		session.setAttribute("pageSize", page.getSize());//当前页条数
		session.setAttribute("pageTotalPages", page.getTotalPages());//共几页
		session.setAttribute("pageTotalElements", page.getTotalElements());//总条数
		return "/WEB-INF/pages/blog/word";
		
	}
	/*
	@RequestMapping("/findWordPermission")
	public @ResponseBody ExtJsonResult<WordPermissionQueryDTO> findWordPermission(){
		List<WordPermissionQueryDTO> findWordPermission = wordPermissionService.findWordPermission();
		return new ExtJsonResult<WordPermissionQueryDTO>(findWordPermission);
	}
	@RequestMapping("/findPermission")
	public @ResponseBody ExtJsonResult<WordPermissionQueryDTO> findPermission(){
		List<WordPermissionQueryDTO> findPermission = wordPermissionService.findPermission();
		return new ExtJsonResult<WordPermissionQueryDTO>(findPermission);
	}*/
	
	@RequestMapping("disableWord")
	public @ResponseBody Word disableWord(@RequestParam Long id) {
		Word word = wordService.findOne(id);
		if (word.getState() != null) {
			wordService.updateState(id, 0);
		}
		return word;
	}
	@RequestMapping("enableWord")
	public @ResponseBody Word enableWord(@RequestParam Long id) {
		Word word = wordService.findOne(id);
		if (word.getState() != null) {
			wordService.updateState(id, 1);
		}
		return word;
	}

}
