package com.fstg.painCare.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fstg.painCare.models.ArticleEntity;

@Repository
public interface ArticleDao extends JpaRepository<ArticleEntity, Integer>{

}
