package com.ex.web;

import com.ex.app.dto.CredsDTO;
import com.ex.app.dto.GenericDTO;
import com.ex.app.entities.User;
import com.ex.app.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController{
    private final Logger LOG = Logger.getLogger(this.getClass());

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericDTO login(@RequestBody CredsDTO credsDTO){
        GenericDTO dto = new GenericDTO();
        boolean authenticated;

        LOG.debug("LoginController: Attempting to login");
        authenticated = userService.authenticate(credsDTO.getCredUsername(), credsDTO.getCredPassword());

        if(authenticated) {
            LOG.debug("LoginController: Login authenticated");
            dto.setMessage("authenticated");
        } else {
            LOG.debug("LoginController: Login NOT authenticated");
            dto.setMessage("not authenticated");
        }
        return dto;
    }

    @CrossOrigin
    @PostMapping(path = "/newuser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createNewUser(@RequestBody User user){

        // add new user
        LOG.debug("LoginController: Attempting to add new user");
        try {
            userService.addNew(user);
            LOG.info("LoginController: Successfully added new user");
        } catch(Exception ex) {
            LOG.error("LoginController: Error with UserServlet: add new user");
            ex.printStackTrace();
        }
    }

}
