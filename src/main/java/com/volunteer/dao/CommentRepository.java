package com.volunteer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.xxx.volunterNetwork.domain.Acti;
import com.xxx.volunterNetwork.domain.Comment;
import com.xxx.volunterNetwork.domain.Img;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:35:19
 *
 */

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long>,JpaSpecificationExecutor<Comment> {	
	@Modifying
	@Query("update Comment comment set comment.state = ?2 where comment.id = ?1")
	public void updateState(Long id,Integer state);
	@Query(value="select c.*,u.userName,u.studentNo,u.img from t_comment c,t_user u,t_share s where c.userId=u.id and c.parentId=s.id and s.id=?1 ORDER BY c.respTime desc limit 5",nativeQuery=true)
	public List<Object[]> findComment(Long id);
	@Query(value="select c.*,u.userName,u.studentNo,u.img from t_comment c,t_user u,t_acti a where c.userId=u.id and c.parentId=a.id and a.id=?1 ORDER BY c.respTime desc limit 5",nativeQuery=true)
	public List<Object[]> findComment2(Long id);
}
