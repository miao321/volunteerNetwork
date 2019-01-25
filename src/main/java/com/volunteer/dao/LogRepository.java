package com.volunteer.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.xxx.volunterNetwork.domain.Log;
import com.xxx.volunterNetwork.domain.User;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:35:06
 *
 */
@Repository 
public interface LogRepository extends PagingAndSortingRepository<Log, Long>,JpaSpecificationExecutor<Log> {
	@Query("from User user where user.userName =?1")
	public User findUser(String userName);
}
