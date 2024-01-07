package com.fstg.painCare.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table( name = "users" , uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	Integer userId;
	
	@ManyToOne()
	@JoinColumn(name = "role_id")
	RoleEntity role;
	
	@Column( nullable = false , unique = true )
	String email;
	
	@Column( nullable = false )
	String password;

	@OneToOne(mappedBy = "user" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	FemmeEntity femme;
	
	@OneToOne(mappedBy = "user" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	AdminEntity admin;
	
}
