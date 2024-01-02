package com.fstg.painCare.models;

import java.io.Serializable;

import javax.persistence.Column;

public class CommantaireKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="blog_id")
	private Integer blogId;

}
