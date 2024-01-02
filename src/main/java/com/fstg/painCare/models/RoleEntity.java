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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	Integer roleId;
	
	@Column( nullable = false )
	String name;
	
	@OneToMany(mappedBy = "role" , cascade = CascadeType.ALL , fetch = FetchType.LAZY )
	List<UserEntity> user ;
	
}
