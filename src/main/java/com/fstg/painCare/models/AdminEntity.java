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
@Table(name = "admins")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "admin_id")
	Integer adminId;
	
	@Column(nullable = false)
	String nom ;
	
	@Column(nullable = false)
	String  prenom;
	
	@OneToMany(mappedBy = "admin" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	List<ArticleEntity> articles;
	
	@OneToOne()
	@JoinColumn(name = "user_id")
	UserEntity user;
}
