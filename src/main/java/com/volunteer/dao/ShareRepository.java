package com.volunteer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.xxx.volunterNetwork.domain.Acti;
import com.xxx.volunterNetwork.domain.Share;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:35:19
 *
 */

@Repository
public interface ShareRepository extends PagingAndSortingRepository<Share, Long>,JpaSpecificationExecutor<Share> {
	/**
	 * 查询根节点
	 */
	/*@Query("from Share m where m.parentNode.id = null")
	public List<Share> findParentNodes();
	*//**
	 * 根据父节点ID查询出子节点, 父节点为null的时候被data jpa过滤掉。所以无法传null
	 *//*
	@Query("from Share m where m.parentNode.id = ?1")
	public List<Share> findChildNodes(Long parentId);//null
	
	@Query("from Share share where share.shareName =?1")
	public Share findShare(String shareName);*/
	
	@Modifying
	@Query("update Share share set share.state = ?2 where share.id = ?1")
	public void updateState(Long id,Integer state);
	@Query(value="select * from t_share where state = 1 ORDER BY id desc",nativeQuery=true)
	public List<Share> findShare();
}
