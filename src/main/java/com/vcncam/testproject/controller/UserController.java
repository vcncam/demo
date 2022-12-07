package com.vcncam.testproject.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vcncam.testproject.exception.CustomException;
import com.vcncam.testproject.model.Role;
import com.vcncam.testproject.model.User;
import com.vcncam.testproject.payload.GeneralResponse;
import com.vcncam.testproject.payload.GeneralStatusResponse;
import com.vcncam.testproject.service.UserService;
import com.vcncam.testproject.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping (path = "/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/user-information")
    public ResponseEntity<GeneralResponse<User>> getUserInformation() {
        String username = Common.getCurrentUser();
        User user = userService.getUserInformation(username);
        return success(user);
    }
    
    @PostMapping("/edit-information")
    public ResponseEntity<GeneralResponse<User>> editUserInformation(@RequestBody User user) {
        User userResponse = userService.editUserInformation(user);
        return success(userResponse);
    }
    
    @GetMapping ("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring(7);
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                User user = userService.getUserInformation(username);
                user.setRoles(new HashSet<>(userService.getRoles(user.getUserId())));
                String accessToken = JWT.create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                    .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("accessToken", accessToken);
                tokens.put("refreshToken", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception e) {
                e.printStackTrace();
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("errorMessage", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new CustomException("301","Refresh Token Invalid");
        }
    }
}
