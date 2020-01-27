package com.renga.services.composite.controllers;


import com.renga.api.utils.JWTUtil;
import com.renga.services.composite.lookups.AuthRequest;
import com.renga.services.composite.lookups.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
            Object pricipal = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            ).getDetails();
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }

        String token = this.jWTUtil.generateToken("userId");
        return new ResponseEntity<>(new TokenResponse(token, "userId"), HttpStatus.OK);
    }

}
