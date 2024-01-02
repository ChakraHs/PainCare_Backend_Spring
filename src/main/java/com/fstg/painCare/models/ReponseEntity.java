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
@Table(name = "reponse_test")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReponseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reponse_id")
	Integer reponseId ;
	
	@Column(nullable = false)
	String reponse;
	
	@ManyToOne()
	@JoinColumn(name = "question_id" )
	QuestionEntity question;
	
}
