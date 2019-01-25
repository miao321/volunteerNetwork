package com.volunteer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.xxx.volunterNetwork.domain.Acti;
import com.xxx.volunterNetwork.domain.Borad;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:35:42
 *
 */
@Repository 
public interface BoradRepository extends PagingAndSortingRepository<Borad, Long>,JpaSpecificationExecutor<Borad> {	
	@Modifying
	@Query("update Borad borad set borad.state = ?2 where borad.id = ?1")
	public void updateState(Long id,Integer state);
	@Query(value="select * from t_borad where state = 1 and fblx=\"志愿资讯\" ORDER BY id desc limit 3",nativeQuery=true)
	public List<Borad> findInfo();
	@Query(value="select * from t_borad where state = 1 and fblx=\"信息公告\" ORDER BY id desc limit 3",nativeQuery=true)
	public List<Borad> findCulture();
	@Query(value="select * from t_borad where state = 1 and fblx=\"志愿文化\" ORDER BY id desc limit 6",nativeQuery=true)
	public List<Borad> findMessage();
}
