package com.renga.services.composite.controllers;


import com.renga.api.utils.JWTUtil;
import com.renga.services.composite.lookups.AuthRequest;
import com.renga.services.composite.lookups.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private JWTUtil jWTUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
            authenticationManager.authenticate(authentication);
            String userId =  SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            String token = this.jWTUtil.generateToken(userId);
            return new ResponseEntity<>(new TokenResponse(token, userId ), HttpStatus.OK);
        } catch (Exception ex) {
            throw new Exception("In valid username/password");
        }
    }

}
