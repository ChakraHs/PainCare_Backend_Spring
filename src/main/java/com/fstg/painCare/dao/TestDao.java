package com.fstg.painCare.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fstg.painCare.models.TestEntity;

@Repository
public interface TestDao extends JpaRepository<TestEntity, Integer>{

}
