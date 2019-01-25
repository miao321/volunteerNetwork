package com.volunteer.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xxx.volunterNetwork.domain.Borad;
import com.xxx.volunterNetwork.domain.Comment;
import com.xxx.volunterNetwork.domain.Enroll;
import com.xxx.volunterNetwork.domain.Opinion;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:35:19
 *
 */

@Repository
public interface PersonalRepository extends PagingAndSortingRepository<Comment, Long>,JpaSpecificationExecutor<Comment> {	
	@Modifying
	@Query("update Comment comment set comment.state = ?2 where comment.id = ?1")
	public void updateState(Long id,Integer state);
	@Query("from Opinion opinion where opinion.author =?1 and opinion.state =1")
	public List<Opinion> findOpinion(String userName);
	@Query("from Enroll enroll where enroll.userName =?1 and enroll.state = 1")
	public List<Enroll> findEnroll(String userName);
	@Query(value="select * from t_borad where state = 1 and fblx=\"信息公告\" ORDER BY id desc limit 3",nativeQuery=true)
	public List<Borad> findBorad();
	@Query("from Enroll enroll where enroll.userName =?1")
	public List<Enroll> findRecord(String userName);
	@Query(value="select sum(duration) from t_enroll where userName =:userName",nativeQuery=true)
	public Integer findDuration(@Param("userName") String userName);
	@Query(value="select sum(pxTime) from t_enroll where userName =:userName",nativeQuery=true)
	public Integer findpxTime(@Param("userName") String userName);
	@Query(value="select count(hdName) from t_enroll where userName =:userName",nativeQuery=true)
	public Integer findCount(@Param("userName") String userName);
}
