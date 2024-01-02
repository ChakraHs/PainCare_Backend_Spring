package com.fstg.painCare.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fstg.painCare.models.AdminEntity;

@Repository
public interface AdminDao extends JpaRepository<AdminEntity, Integer> {

}
