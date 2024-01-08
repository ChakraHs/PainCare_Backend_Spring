package com.fstg.painCare.config.service;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAuthentication {

	private Integer getUserIdAfterAuthentication() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		// Assurez-vous que l'authentification n'est pas nulle et qu'elle contient des détails sur l'utilisateur
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

	        // Vous pouvez maintenant accéder à l'ID de l'utilisateur à partir de votre UserDetails
	        Integer userId = getUserIdFromUserDetails(userDetails);
	        return userId;
	    }

	    // Gérer le cas où l'ID de l'utilisateur n'est pas disponible
	    return null;
	}
	
	// Méthode utilitaire pour extraire l'ID de l'utilisateur à partir de UserDetails
	private Integer getUserIdFromUserDetails(UserDetails userDetails) {
	    // Votre implémentation pour extraire l'ID de l'utilisateur, cela dépend de la structure de votre UserDetails
	    // Par exemple, si UserDetailsImpl est votre implémentation UserDetails, vous pouvez avoir une méthode getId() dans cette classe.
	    if (userDetails instanceof UserDetailsImpl) {
	        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) userDetails;
	        return userDetailsImpl.getId();
	    }

	    // Gérer le cas où l'ID de l'utilisateur n'est pas disponible
	    return null;
	}
}
