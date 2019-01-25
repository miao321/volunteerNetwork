package com.volunteer.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.xxx.volunterNetwork.domain.Acti;
import com.xxx.volunterNetwork.domain.Img;
import com.xxx.volunterNetwork.util.ExtPageable;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:35:19
 *
 */

@Repository
public interface ActiRepository extends PagingAndSortingRepository<Acti, Long>,JpaSpecificationExecutor<Acti> {	
	@Query(value="select * from t_acti where state = 1 and hdlx=\"青少年服务\" ORDER BY id desc limit 10",nativeQuery=true)
	public List<Acti> findActi();
	@Query(value="select * from t_acti where state = 1 and hdlx=\"敬老助残\" ORDER BY id desc limit 10",nativeQuery=true)
	public List<Acti> findActi2();
	@Query(value="select * from t_acti where state = 1 and hdlx=\"扶贫帮困\" ORDER BY id desc limit 10",nativeQuery=true)
	public List<Acti> findActi3();
	@Query(value="select * from t_acti where state = 1 and hdlx=\"文明礼仪\" ORDER BY id desc limit 10",nativeQuery=true)
	public List<Acti> findActi4();
	@Query(value="select * from t_acti where state = 1 and hdlx=\"平安守护\" ORDER BY id desc limit 10",nativeQuery=true)
	public List<Acti> findActi5();
	@Query(value="select * from t_acti where state = 1 and hdlx=\"环境保护\" ORDER BY id desc limit 10",nativeQuery=true)
	public List<Acti> findActi6();
	@Query(value="select * from t_acti where state = 1 and hdlx=\"文化体育\" ORDER BY id desc limit 10",nativeQuery=true)
	public List<Acti> findActi7();
	@Query(value="select * from t_acti where state = 1 and hdlx=\"便民服务\" ORDER BY id desc limit 10",nativeQuery=true)
	public List<Acti> findActi8();
	@Query(value="select * from t_acti where state = 1 and hdlx=\"其他\" ORDER BY id desc limit 10",nativeQuery=true)
	public List<Acti> findActi9();
	@Query(value="select * from t_acti where state = 1",nativeQuery=true)
	public List<Acti> findActi0();
	@Query(value="select * from t_acti t where t.state = 1 and t.hdlx=?1",nativeQuery=true)
	public Acti findByHdlx(String hdlx);
	@Modifying
	@Query("update Acti acti set acti.state = ?2 where acti.id = ?1")
	public void updateState(Long id,Integer state);
	
	@Query("select a from Acti a where a.title like %?1% or a.xxdz like %?1% or a.fbzz like %?1% or a.endTime like %?1% or a.id = ?1")
    Page<Acti> findSearch(String query, Pageable pageable);

}
