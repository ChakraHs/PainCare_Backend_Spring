package com.fstg.painCare.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fstg.painCare.models.BlogEntity;
import java.util.List;
import com.fstg.painCare.models.FemmeEntity;

@Repository
public interface BlogDao extends JpaRepository<BlogEntity, Integer> {

	List<BlogEntity> findByFemme(FemmeEntity femme);
	
}
