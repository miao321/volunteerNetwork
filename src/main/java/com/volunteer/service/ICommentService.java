package com.volunteer.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.xxx.volunterNetwork.domain.Comment;
import com.xxx.volunterNetwork.dto.CommentQueryDTO;

/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:32:12
 *
 */

public interface ICommentService {
	public void saveOrUpdate(Comment comment);
	public void delete(Comment comment);
	public void delete(List<Long> ids);
	public Comment findOne(Long id);
	public List<Comment> findAll();
	public void updateState(Long id,Integer state);
	public List<CommentQueryDTO> findComment(Long id);
	public List<CommentQueryDTO> findComment2(Long id);
	//动态条件查询
	public Page<Comment> findAll(Specification<Comment> spec,Pageable pageable);


}
