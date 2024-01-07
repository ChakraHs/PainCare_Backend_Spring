package com.fstg.painCare.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fstg.painCare.config.service.UserDetailsServiceImpl;



public class AuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	  
	// Utilise le framework SLF4J pour enregistrer les journaux
	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
	
	// Implémente la méthode doFilterInternal() de l'interface OncePerRequestFilter
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			// Récupère le jeton JWT de la requête
		    String jwt = parseJwt(request);
		    
		 // Vérifie la validité du jeton JWT
		    if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
		    	
		    	// Extrait le nom d'utilisateur à partir du jeton JWT
		    	String username = jwtUtils.getUserNameFromJwtToken(jwt);

		    	// Charge les détails de l'utilisateur à partir du service UserDetailsServiceImpl
		        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		        
		        // Crée une instance d'authentification avec les détails de l'utilisateur
		        UsernamePasswordAuthenticationToken authentication =
		            new UsernamePasswordAuthenticationToken(
		                userDetails,
		                null,
		                userDetails.getAuthorities());
		        
		        // Ajoute les détails de l'authentification à l'objet SecurityContextHolder
		        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		        SecurityContextHolder.getContext().setAuthentication(authentication);
		    }
	    } catch (Exception e) {
	    	// Enregistre un message d'erreur en cas d'échec de l'authentification de l'utilisateur
	    	logger.error("Cannot set user authentication: {}", e);
	    }
		
		// Passe la requête au filtre suivant dans la chaîne
	    filterChain.doFilter(request, response);
		
	}
	
	// Méthode utilitaire pour extraire le jeton JWT de l'en-tête Authorization de la requête
	private String parseJwt(HttpServletRequest request) {
	    String headerAuth = request.getHeader("Authorization");

	    // Vérifie si l'en-tête Authorization contient le préfixe "Bearer"
	    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
	    	// Retourne le jeton JWT (en supprimant le préfixe "Bearer")
	    	return headerAuth.substring(7);
	    }
	    // Retourne null si le jeton JWT n'est pas trouvé dans l'en-tête Authorization
	    return null;
	}
}
