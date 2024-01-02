package com.fstg.painCare.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fstg.painCare.models.BlogEntity;
@Repository
public interface BlogDao extends JpaRepository<BlogEntity, Integer> {

}
