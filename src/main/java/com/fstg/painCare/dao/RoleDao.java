package com.fstg.painCare.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fstg.painCare.models.RoleEntity;

@Repository
public interface RoleDao extends JpaRepository<RoleEntity, Integer>{

	RoleEntity findByName(String name);
	
}
