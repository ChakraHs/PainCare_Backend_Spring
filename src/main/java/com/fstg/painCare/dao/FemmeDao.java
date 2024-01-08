package com.fstg.painCare.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fstg.painCare.models.FemmeEntity;
import com.fstg.painCare.models.UserEntity;
import java.util.List;


@Repository
public interface FemmeDao extends JpaRepository<FemmeEntity, Integer> {

	FemmeEntity findByUser(UserEntity user);
	
}
