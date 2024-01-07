package com.fstg.painCare.models;

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
@Table(name = "diagnostics")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DiagnosticEntity {

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Diag_id")
	Integer DiagId;
	
	@Column(nullable = false , name = "pain_level")
	String painLevel;
	
	@Column(nullable = false , name = "pain_locations")
	String painLocations;
	
	@Column(nullable = false)
	String symptoms;
	
	@Column(nullable = false , name = "pain_worse")
	String painWorse;
	
	@Column(nullable = false )
	String fellings;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "diagnostic_date", nullable = false, updatable = false)
    private Date diagnosticDate;
	
	@ManyToOne()
	@JoinColumn(name = "femme_id")
	FemmeEntity femme ;
	
	@PrePersist
    protected void onCreate() {
        this.diagnosticDate = new Date();
    }
	
}
