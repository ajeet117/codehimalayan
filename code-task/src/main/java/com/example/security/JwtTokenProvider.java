package com.example.security;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.*;

@Component
public class JwtTokenProvider {

	private static final Logger logger= LoggerFactory.getLogger(JwtTokenProvider.class);
	
	private String jwtSecret="9a02115a835ee03d5fb83cd8a468ea33e4090aaaec87f53c9fa54512bbef4db8dc656c82a315fa0c785c08b0134716b81ddcd0153d2a7556f2e154912cf5675f";
	
	private int jwtExpirationInMs= 24* 60 *60 *1000 ;
	
	public String generateToken(Authentication authentication)
	{
		StudentPrincipal studentPrincipal = (StudentPrincipal) authentication.getPrincipal();
		Date now= new Date();
		Date expiryDate=new Date(now.getTime() + jwtExpirationInMs);
		
		return Jwts.builder()
				.setSubject(Long.toString(studentPrincipal.getId()))
				.setIssuedAt(new Date())
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512,jwtSecret)
				.compact();
	}
	
	public long getUserIdFromJwt(String token)
	{
		Claims claims = Jwts.parser()
						.setSigningKey(jwtSecret)
						.parseClaimsJws(token)
						.getBody();
		return Long.parseLong(claims.getSubject());
	}
	
	 public boolean validateToken(String authToken) {
	        try {
	            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
	            return true;
	        } catch (SignatureException ex) {
	            logger.error("Invalid JWT signature");
	        } catch (MalformedJwtException ex) {
	            logger.error("Invalid JWT token");
	        } catch (ExpiredJwtException ex) {
	            logger.error("Expired JWT token");
	        } catch (UnsupportedJwtException ex) {
	            logger.error("Unsupported JWT token");
	        } catch (IllegalArgumentException ex) {
	            logger.error("JWT claims string is empty.");
	        }
	        return false;
	    }
	}

