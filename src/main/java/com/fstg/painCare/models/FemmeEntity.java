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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "femmes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FemmeEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "femme_id")
	Integer femmeId;
	
	@Column(nullable = false)
	String nom;
	
	@Column(nullable = false)
	String prenom;
	
	@Column(nullable = false)
	String profil;
	
	@Column()
	String ville;
	
	@Column()
	String rue_adresse;
	
	@Column()
	String numero_adresse;
	
	@Column()
	String numero_telephone;
	
	
	@Column()
	String numero_cin;
	
	@OneToMany(mappedBy = "femme" ,cascade = CascadeType.ALL , fetch = FetchType.LAZY )
	List<BlogEntity> blogs ;
	
	@OneToMany(mappedBy = "femme" ,cascade = CascadeType.ALL , fetch = FetchType.LAZY )
	List<CommantaireEntity> commantaires ;
	
	@OneToOne()
	@JoinColumn(name = "user_id")
	UserEntity user;
	
	@OneToMany(mappedBy = "femme" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	List<TestEntity> tests ;
	
	@OneToMany(mappedBy = "femme" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	List<DiagnosticEntity> diagnostic ;
	
}
