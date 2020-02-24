package org.nerdcore.customerservicev2.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//This controller catches all URLs that start with '/site', and manages navigation through features of the site
//not related to client login.
@RestController
@RequestMapping("/site")
public class SourceController {

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }


}
