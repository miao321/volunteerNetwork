package com.volunteer.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xxx.volunterNetwork.anno.SysLog;
import com.xxx.volunterNetwork.dao.WordRepository;
import com.xxx.volunterNetwork.domain.Word;
import com.xxx.volunterNetwork.service.IWordService;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:43:54
 *
 */

@Service
@Transactional
public class WordServiceImpl implements IWordService {
	private static final Logger logger = LoggerFactory.getLogger(WordServiceImpl.class);
	@Autowired
	private WordRepository wordRepository;
	@SysLog(module="角色管理",methods="保存或者更新数据")
	public void saveOrUpdate(Word word) {
		wordRepository.save(word);
	}
	@SysLog(module="角色管理",methods="删除一条数据")
	public void delete(Word word) {
		wordRepository.delete(word);
	}
	@SysLog(module="角色管理",methods="删除多条数据")
	public void delete(List<Long> ids) {
		List<Word> wordLists = (List<Word>) wordRepository.findAll(ids);
		if(wordLists != null) {
			wordRepository.delete(wordLists);
		}			
	}
	@SysLog(module="角色管理",methods="查找一条数据")
	public Word findOne(Long id) {		
		return wordRepository.findOne(id);
	}
	@SysLog(module="角色管理",methods="查找所有数据")
	public List<Word> findAll() {
		return (List<Word>) wordRepository.findAll();
	}
	@SysLog(module="角色管理",methods="查找所有数据并分页排序")
	public Page<Word> findAll(Specification<Word> spec, Pageable pageable) {
		return wordRepository.findAll(spec, pageable);
	}
/*	@Override
	public Word findWord(String wordName) {
		return wordRepository.findWord(wordName);
	}*/
	@Override
	public void updateState(Long id, Integer state) {
		wordRepository.updateState(id, state);
		
	}

}
