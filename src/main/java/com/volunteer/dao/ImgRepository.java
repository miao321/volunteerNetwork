package com.volunteer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.xxx.volunterNetwork.domain.Img;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:35:19
 *
 */

@Repository
public interface ImgRepository extends PagingAndSortingRepository<Img, Long>,JpaSpecificationExecutor<Img> {
	@Query(value="select * from t_img t ORDER BY id desc LIMIT 6",nativeQuery=true)
	public List<Img> findImg();
	@Query(value="select * from t_img t where t.state =1 ORDER BY id desc LIMIT 3",nativeQuery=true)
	public List<Img> findImg2();
	@Modifying
	@Query("update Img img set img.state = ?2 where img.id = ?1")
	public void updateState(Long id,Integer state);
}
