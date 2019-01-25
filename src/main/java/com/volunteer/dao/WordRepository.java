package com.volunteer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.xxx.volunterNetwork.domain.Word;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:35:19
 *
 */

@Repository
public interface WordRepository extends PagingAndSortingRepository<Word, Long>,JpaSpecificationExecutor<Word> {
	/**
	 * 查询根节点
	 */
	/*@Query("from Word m where m.parentNode.id = null")
	public List<Word> findParentNodes();
	*//**
	 * 根据父节点ID查询出子节点, 父节点为null的时候被data jpa过滤掉。所以无法传null
	 *//*
	@Query("from Word m where m.parentNode.id = ?1")
	public List<Word> findChildNodes(Long parentId);//null
	
	@Query("from Word word where word.wordName =?1")
	public Word findWord(String wordName);*/
	
	@Modifying
	@Query("update Word word set word.state = ?2 where word.id = ?1")
	public void updateState(Long id,Integer state);
}
