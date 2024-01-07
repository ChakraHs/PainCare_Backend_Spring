package com.fstg.painCare.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "test_date", nullable = false, updatable = false)
    private Date testDate;
	
	@ManyToOne()
	@JoinColumn(name = "femme_id" )
	FemmeEntity femme;
	
	@PrePersist
    protected void onCreate() {
        this.testDate = new Date();
    }
	
	
}
