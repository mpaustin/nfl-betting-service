package com.ex.web;

import com.ex.app.entities.User;
import com.ex.app.service.UserService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class UserController {
    private final Logger LOG = Logger.getLogger(this.getClass());

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @GetMapping(path = "/deposit")
    public void deposit(@RequestParam float deposit, @RequestParam String username) {

        // deposit funds into user account
        LOG.debug("UserController: Attempting to deposit funds");
        try {
            User user = (User) userService.getById(username);
            user.setUserFunds(user.getUserFunds() + deposit);
            userService.update(user);
//            resp.setStatus(200);
            LOG.info("UserController: Successfully deposited $" + deposit + " into account username: " + username);
        } catch (Exception ex) {
            LOG.error("UserController: Error with UserServlet: deposit funds");
            ex.printStackTrace();
        }
    }

    @CrossOrigin
    @GetMapping(path = "/viewprof", produces = MediaType.APPLICATION_JSON_VALUE)
    public User view(@RequestParam String username){

        // view user account information
        LOG.debug("UserController: Attempting to view user account info");
        try {
            User user = (User)userService.getById(username);
            LOG.info("UserController: Successfully got user account info");
            System.out.println(user);
            return user;
        } catch (Exception ex) {
            LOG.error("UserController: Error with UserServlet: view user account info");
            ex.printStackTrace();
            return null;
        }
    }
}
