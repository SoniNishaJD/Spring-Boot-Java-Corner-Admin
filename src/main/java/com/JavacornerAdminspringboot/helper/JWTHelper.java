package com.JavacornerAdminspringboot.helper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.JavacornerAdminspringboot.constant.JWTUtil.ISSUER;
import static com.JavacornerAdminspringboot.constant.JWTUtil.EXPIRE_ACCESS_TOKEN;
import static com.JavacornerAdminspringboot.constant.JWTUtil.EXPIRE_REFRESH_TOKEN;
import static com.JavacornerAdminspringboot.constant.JWTUtil.SECRET;
import static com.JavacornerAdminspringboot.constant.JWTUtil.BEARER_PREFIX;


import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JWTHelper {
	
	Algorithm algorithm = Algorithm.HMAC256(SECRET);

	public String generateAccessToken(String email, List<String> roles) {
		return JWT.create()
				.withSubject(email)
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_ACCESS_TOKEN))
				.withIssuer(ISSUER)
				.withClaim("roles", roles)
				.sign(algorithm);
	}
	
	public String generateRefereshToken(String email) {
		return JWT.create()
				.withSubject(email)
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_REFRESH_TOKEN))
				.withIssuer(ISSUER)
				.sign(algorithm);
	}
	
	public String extractTokenFromHeaderIfExists(String authorizationHeader) {
		if(authorizationHeader!= null && authorizationHeader.startsWith(BEARER_PREFIX)) {
			return authorizationHeader.substring(BEARER_PREFIX.length());
		}
		return null;
	}
	
	public Map<String, String> getTokenMap(String jwtAccessToken, String jwtRefreshToken){
		Map<String,String> idTokens = new HashMap<>();
		idTokens.put("accessToken", jwtAccessToken);
		idTokens.put("refreshToken", jwtRefreshToken);
		return idTokens;
	}
}
