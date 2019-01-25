package com.volunteer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.xxx.volunterNetwork.domain.Opinion;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:35:19
 *
 */

@Repository
public interface OpinionRepository extends PagingAndSortingRepository<Opinion, Long>,JpaSpecificationExecutor<Opinion> {
	/**
	 * 查询根节点
	 */
	/*@Query("from Opinion m where m.parentNode.id = null")
	public List<Opinion> findParentNodes();
	*//**
	 * 根据父节点ID查询出子节点, 父节点为null的时候被data jpa过滤掉。所以无法传null
	 *//*
	@Query("from Opinion m where m.parentNode.id = ?1")
	public List<Opinion> findChildNodes(Long parentId);//null
	
	@Query("from Opinion opinion where opinion.opinionName =?1")
	public Opinion findOpinion(String opinionName);*/
	
	@Modifying
	@Query("update Opinion opinion set opinion.state = ?2 where opinion.id = ?1")
	public void updateState(Long id,Integer state);
}
