package com.fstg.painCare.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "questions_test")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id")
	Integer questionId ;
	
	@Column(nullable = false)
	String question;
	
	@ManyToOne()
	@JoinColumn(name = "type_id")
	TypeQuestionEntity type;
	
	@OneToMany(mappedBy = "question" , cascade = CascadeType.ALL , fetch = FetchType.LAZY )
	List<ReponseEntity> reponse;

}
