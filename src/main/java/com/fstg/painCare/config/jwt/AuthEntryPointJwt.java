package com.fstg.painCare.config.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint{

	// Utilise le framework SLF4J pour enregistrer les journaux
	private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		// Enregistre un message d'erreur non autorisé dans les journaux
		logger.error("Unauthorized error: {}", authException.getMessage());
		
		// Configure la réponse HTTP pour renvoyer un message d'erreur JSON
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		// Définit le statut de la réponse HTTP comme non autorisé
	    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

	    // Crée un corps de réponse JSON avec des informations sur l'erreur
	    final Map<String, Object> body = new HashMap<>();
	    // Statut HTTP
	    body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
	    // Type d'erreur
	    body.put("error", "Unauthorized");
	    // Message d'erreur
	    body.put("message", authException.getMessage());
	    // Chemin de la requête
	    body.put("path", request.getServletPath());
	    
	    // Utilise ObjectMapper pour convertir le corps en format JSON et l'écrire dans le flux de sortie de la réponse
	    final ObjectMapper mapper = new ObjectMapper();
	    mapper.writeValue(response.getOutputStream(), body);
		
	}

}
