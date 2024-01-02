package com.fstg.painCare.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "commantaires")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommantaireEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	CommantaireKey key;
	
	@ManyToOne()
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	FemmeEntity user ;
	
	@ManyToOne()
	@MapsId("blogId")
	@JoinColumn(name = "blog_id")
	BlogEntity blog ;
	
	@Column(nullable = false)
	String message ;
	
	
}
