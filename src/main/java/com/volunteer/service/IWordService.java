package com.volunteer.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.xxx.volunterNetwork.domain.Word;

/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:32:12
 *
 */

public interface IWordService {
	public void saveOrUpdate(Word word);
	public void delete(Word word);
	public void delete(List<Long> ids);
	public Word findOne(Long id);
	public List<Word> findAll();
	public void updateState(Long id,Integer state);
	
	/*public Word findWord(String wordName);*/
	//动态条件查询
	public Page<Word> findAll(Specification<Word> spec,Pageable pageable);


}
