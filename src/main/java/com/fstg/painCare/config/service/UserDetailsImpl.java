package com.fstg.painCare.config.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;	

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fstg.painCare.models.UserEntity;


public class UserDetailsImpl implements UserDetails	 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private String email;

	@JsonIgnore
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Integer id, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}
	
	// Méthode statique pour construire un UserDetailsImpl à partir d'un objet UserEntity
	public static UserDetailsImpl build(UserEntity user) {
	    List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName()));

	    return new UserDetailsImpl(
	        user.getUserId(), 
	        user.getEmail(),
	        user.getPassword(), 
	        authorities);
	  }

	// Méthode pour obtenir les autorités (rôles) de l'utilisateur
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
		
	}

	// Méthode pour obtenir l'identifiant de l'utilisateur
	public Integer getId() {
		
	    return id;
	    
	}
	  
	// Méthode pour obtenir le mot de passe de l'utilisateur
	@Override
	public String getPassword() {
		return password;
	}

	// Méthode pour obtenir le nom d'utilisateur de l'utilisateur
	@Override
	public String getUsername() {
		return email;
	}

	// Méthodes pour gérer l'état du compte de l'utilisateur
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	// Méthode pour comparer deux instances UserDetailsImpl par leur identifiant
	@Override
	public boolean equals(Object o) {
	    if (this == o)
	      return true;
	    if (o == null || getClass() != o.getClass())
	      return false;
	    UserDetailsImpl user = (UserDetailsImpl) o;
	    return Objects.equals(id, user.id);
	}

}
