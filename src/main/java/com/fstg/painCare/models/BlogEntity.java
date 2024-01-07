package com.fstg.painCare.models;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "blogs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlogEntity implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "blog_id")
	Integer blogId;
	
	@Column(nullable = false)
	String titre;
	
	@Column(nullable = false)
	String image;
	
	@Column(nullable = false)
	String description;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "blog_date", nullable = false, updatable = false)
    private Date blogDate;
	
	@ManyToOne()
	@JoinColumn(name = "femme_id")
	FemmeEntity femme ;
	
	@OneToMany(mappedBy = "blog" ,cascade = CascadeType.ALL , fetch = FetchType.LAZY )
	List<CommantaireEntity> commantaires ; 
	
	@PrePersist
    protected void onCreate() {
        this.blogDate = new Date();
    }

}
