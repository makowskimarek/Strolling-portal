package com.walker.rest.mvc;

import com.walker.core.entities.Id;
import com.walker.core.entities.LoginData;
import com.walker.core.services.UserService;
import com.walker.core.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Rafal on 04.06.2017.
 */
@Controller
public class LoginController {

    @Autowired
    private AuthenticationManager myAuthenticationManager;

    @Autowired
    private Id id;

    private UserService service = new UserServiceImpl();

    @RequestMapping(value = "/rest/login", method = RequestMethod.POST)
    public ResponseEntity<LoginData> login(@RequestBody LoginData loginData , HttpServletRequest request)
    {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginData.getUsername(), loginData.getPassword());
            token.setDetails(new WebAuthenticationDetails(request));

            Authentication auth = myAuthenticationManager.authenticate(token);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(auth);

            if(auth.isAuthenticated()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

                return new ResponseEntity<LoginData>(HttpStatus.OK);
            }
            else
                return new ResponseEntity<LoginData>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<LoginData>(HttpStatus.UNAUTHORIZED);
        }

    }

}
