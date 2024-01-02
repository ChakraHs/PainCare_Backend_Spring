package com.fstg.painCare.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fstg.painCare.models.QuestionEntity;

@Repository
public interface QuestionDao extends JpaRepository<QuestionEntity, Integer> {

}
