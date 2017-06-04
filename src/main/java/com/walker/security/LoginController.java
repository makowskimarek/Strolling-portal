package com.walker.security;

import com.walker.core.entities.LoginData;
import com.walker.rest.resources.UserAndUserDataResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rafal on 04.06.2017.
 */
/*
@RestController
@RequestMapping("/rest/api/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(method = RequestMethod.POST, headers = {"Accept=application/json"})
    public ResponseEntity<LoginData> login(@RequestBody LoginData loginData) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginData.getUsername(), loginData.getPassword());
        try {
            Authentication auth = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            return new ResponseEntity<LoginData>(HttpStatus.OK);

        } catch (BadCredentialsException ex) {
            return new ResponseEntity<LoginData>(HttpStatus.UNAUTHORIZED);

        }
    }}
*/
