package com.fstg.painCare.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fstg.painCare.models.TypeQuestionEntity;

@Repository
public interface TypeQuestionDao extends JpaRepository<TypeQuestionEntity, Integer>{

}
