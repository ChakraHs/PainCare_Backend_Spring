package com.fstg.painCare.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fstg.painCare.models.DiagnosticEntity;
import com.fstg.painCare.models.FemmeEntity;

@Repository
public interface DiagnosticDao extends JpaRepository<DiagnosticEntity, Integer> {

	List<DiagnosticEntity> findByFemmeOrderByDiagnosticDateDesc( FemmeEntity femme , Pageable pageable);
	
}
