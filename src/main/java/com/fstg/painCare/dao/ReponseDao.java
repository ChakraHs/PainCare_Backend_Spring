package com.fstg.painCare.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fstg.painCare.models.ReponseEntity;

@Repository
public interface ReponseDao extends JpaRepository<ReponseEntity, Integer>{

}
