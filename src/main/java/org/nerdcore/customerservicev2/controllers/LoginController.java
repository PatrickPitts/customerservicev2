package org.nerdcore.customerservicev2.controllers;

import org.nerdcore.customerservicev2.services.LoginService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* This controller handles all of the login functionality. Notably, this controller handles URL's not caught
by the LoginFilter class.
* */

@RestController
public class LoginController {

    //Returns the View to log in to the site
    @GetMapping(value={"/login", "/"})
    public ModelAndView getLoginView(){
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    //Takes the login credentials passed by the client and processes them. If the credentials are valid,
    //the method attempts to redirect to the main site (ie the index.jsp view)
    @PostMapping(value="/login")
    public void submitLogin(@ModelAttribute("username") String username, @ModelAttribute("password") String password,
            HttpServletRequest request, HttpServletResponse response){
        try {
            //checks if the submitted credentials are valid within the users.xml data store.
            if(LoginService.processLoginCredentials(request)){
                request.getSession().setAttribute("username", username);
            }
            //attempts to redirect to the main index view, pending LoginFilter verification
            response.sendRedirect("/site/index");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Returns the View for the user to upload new login credentials
    @GetMapping("/new-user")
    public ModelAndView getNewUser(){
        return new ModelAndView("new-user");
    }

    //Takes the new login credentials passed by the client and processes them. If the credentials are not already
    //stored in the local users.xml file, redirects to the login view. Otherwise, redirects to the new-user view
    //prompting the user to submit different credential data.
    @PostMapping("/new-user")
    public void postNewUser(HttpServletResponse response, HttpServletRequest request){
        try {
            //attempts to add new user credentials to the users.xml data store
            if(LoginService.addNewCredentials(request)){
                response.sendRedirect("/login");
                return;
            } else {
                //Sets a flag to be caught by the new-user view, prompting the user to submit new login credentials.
                request.getSession().setAttribute("UsernameAlreadyTaken", true);
                response.sendRedirect("/new-user");
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
