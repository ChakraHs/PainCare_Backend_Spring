package com.fstg.painCare.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tests")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "test_id")
	Integer testId ;
	
	@ManyToOne()
	@JoinColumn(name = "question_id" )
	QuestionEntity question ;
	
	@Column( nullable = false )
	String reponse;
	
	@ManyToOne()
	@JoinColumn(name = "femme_id" )
	FemmeEntity femme;
	
	
}
