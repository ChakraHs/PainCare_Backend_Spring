package com.fstg.painCare.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fstg.painCare.dao.UserDao;
import com.fstg.painCare.models.UserEntity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	// Cette méthode est appelée par Spring Security lors de l'authentification d'un utilisateur.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// Recherchez l'utilisateur dans la base de données en fonction de son nom d'utilisateur.
		UserEntity userEntity = userDao.findByEmail(username).
				orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + username));
		
		// Utilisez la classe UserDetailsImpl pour construire un objet UserDetails à partir de l'entité UserEntity.
		return UserDetailsImpl.build(userEntity);
	}

}
