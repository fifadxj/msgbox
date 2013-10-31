package com.cangshudoudou.msgbox.ws.endpoint.impl;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cangshudoudou.msgbox.service.SecurityService;
import com.cangshudoudou.msgbox.utils.SessionData;
import com.cangshudoudou.msgbox.vo.User;
import com.cangshudoudou.msgbox.ws.endpoint.SecurityEndpoint;
import com.cangshudoudou.msgbox.ws.vo.LoginRequest;
import com.cangshudoudou.msgbox.ws.vo.LoginResponse;
import com.cangshudoudou.msgbox.ws.vo.LogoutRequest;
import com.cangshudoudou.msgbox.ws.vo.LogoutResponse;

@Path("/security")
public class SecurityEndpointImpl implements SecurityEndpoint {

    private SecurityService securityService;
    private SessionData sessionData;
    
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
    
    public void setSessionData(SessionData sessionData) {
        this.sessionData = sessionData;
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public LoginResponse login(LoginRequest request) {
        LoginResponse response = new LoginResponse();
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        
        user = securityService.authenticateUser(user);
        sessionData.setAuthenticated(user);

        User resonseUser = new User();
        resonseUser.setUsername(user.getUsername());
        response.setUser(resonseUser);

        return response;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/logout")
    public LogoutResponse logout(LogoutRequest request) {
        LogoutResponse response = new LogoutResponse();
        sessionData.setAuthenticated(null);
        
        return response;
    }

}
