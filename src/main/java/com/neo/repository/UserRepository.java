package com.neo.repository;

import com.neo.entity.User;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public static final String TABLE_NAME = "User";
	
    User findById(long id);

    Long deleteById(Long id);
    @Cacheable()
    @Query("select a.id from "+TABLE_NAME+" a where a.id =:id")
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })  
    List<User> findByEmailAddress(@Param("id") Long id);
}