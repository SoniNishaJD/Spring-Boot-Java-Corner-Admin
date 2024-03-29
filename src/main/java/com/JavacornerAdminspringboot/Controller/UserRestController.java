package com.JavacornerAdminspringboot.Controller;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.JavacornerAdminspringboot.Entity.User;

import com.JavacornerAdminspringboot.Entity.Role;
import com.JavacornerAdminspringboot.helper.JWTHelper;
import com.JavacornerAdminspringboot.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.JavacornerAdminspringboot.constant.JWTUtil.AUTH_HEADER;
import static com.JavacornerAdminspringboot.constant.JWTUtil.SECRET;

@RestController
@CrossOrigin("*")
public class UserRestController {

	private UserService userService;

	private JWTHelper jwtHelper;

	public UserRestController(UserService userService, JWTHelper jwtHelper) {
		this.userService = userService;
		this.jwtHelper = jwtHelper;
	}

	@GetMapping("/users")
	public boolean checkIfEmailExists(@RequestParam(name = "email", defaultValue = "") String email) {
		return userService.loadUserByEmail(email) != null;
	}

	@GetMapping("/refresh-token")
	public void generateNewAccessToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String jwtRefreshToken = jwtHelper.extractTokenFromHeaderIfExists(request.getHeader(AUTH_HEADER));
		if (jwtRefreshToken != null) {
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			JWTVerifier jwtVerifier = JWT.require(algorithm).build();
			DecodedJWT decodedJWT = jwtVerifier.verify(jwtRefreshToken);
			String email = decodedJWT.getSubject();
			User user = userService.loadUserByEmail(email);
			String jwtAccessToken = jwtHelper.generateAccessToken(user.getEmail(),
					user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
			response.setContentType("application/json");
			new ObjectMapper().writeValue(response.getOutputStream(),
					jwtHelper.getTokenMap(jwtAccessToken, jwtRefreshToken));
		} else {
			throw new RuntimeException("Refresh token required");
		}
	}

}
