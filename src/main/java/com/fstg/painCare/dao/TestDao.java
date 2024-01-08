package com.fstg.painCare.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fstg.painCare.models.TestEntity;


import com.fstg.painCare.models.FemmeEntity;


@Repository
public interface TestDao extends JpaRepository<TestEntity, Integer>{

	List<TestEntity> findByFemmeOrderByTestDateDesc(FemmeEntity femme, Pageable pageable);
	
}
