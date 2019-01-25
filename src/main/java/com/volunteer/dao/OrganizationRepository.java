package com.volunteer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.xxx.volunterNetwork.domain.Enroll;
import com.xxx.volunterNetwork.domain.Organization;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:35:19
 *
 */


@Repository
public interface OrganizationRepository extends PagingAndSortingRepository<Organization, Long>,JpaSpecificationExecutor<Organization> {
	
	@Query(value="select o.id,count(o.organization) oNum,o.organization organization,o.img img,o.menNum menNum from t_organization o join t_acti a where a.fbzz = o.organization GROUP BY o.organization order by id",nativeQuery=true)
	public List<Object[]> findOrganization();
	@Modifying
	@Query("update Organization organization set organization.state = ?2 where organization.id = ?1")
	public void updateState(Long id,Integer state);
	@Query(value="select * from t_organization ORDER BY menNum desc",nativeQuery=true)
	public List<Organization> findVolunter();
	
	@Query(value="select t.college,sum(t.duration) from t_enroll t group by t.college ORDER BY duration desc",nativeQuery=true)
	public List<Object[]> findEnroll();
	@Query(value="select FORMAT(sum(t.menNum),'0') menNum,count(t.organization) orNum,count(t.xjorganization) xjorNum from t_organization t",nativeQuery=true)
	public List<Object[]> findNum();
	@Query(value="select sum(t.duration) durationNum from t_enroll t",nativeQuery=true)
	public Integer findDuration();
}
