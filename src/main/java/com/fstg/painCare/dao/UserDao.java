package com.fstg.painCare.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fstg.painCare.models.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Integer> {

	
	
}
