package com.volunteer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.xxx.volunterNetwork.domain.Enroll;
import com.xxx.volunterNetwork.domain.Img;
import com.xxx.volunterNetwork.domain.User;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:35:19
 *
 */

@Repository
public interface EnrollRepository extends PagingAndSortingRepository<Enroll, Long>,JpaSpecificationExecutor<Enroll> {
	@Query(value="select * from t_enroll ORDER BY duration desc limit 15",nativeQuery=true)
	public List<Enroll> findEnroll();
	
	
	@Modifying
	@Query("update Enroll enroll set enroll.state = ?2 where enroll.id = ?1")
	public void updateState(Long id,Integer state);
	@Modifying
	@Query("from Enroll enroll where enroll.actiId = ?1")
	public List<Enroll> findEnroll(Long id);
	@Query("from Enroll enroll where enroll.userName =?1")
	public Enroll findUser(String userName);
}
