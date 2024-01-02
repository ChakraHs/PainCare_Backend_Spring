package com.fstg.painCare.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fstg.painCare.models.CommantaireEntity;

@Repository
public interface CommantaireDao extends JpaRepository<CommantaireEntity, Integer>{

}
