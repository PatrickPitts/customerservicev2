package org.nerdcore.customerservicev2;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class SourceController {

    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
        System.out.println("We made it boys");
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("Greeting", "Hello World");
        return mv;
    }

    @GetMapping("/new-user")
    public ModelAndView getNewUser(){
        return new ModelAndView("new-user");
    }

    @PostMapping("/new-user")
    public ModelAndView postNewUser(@ModelAttribute("username") String username, @ModelAttribute("password") String password){
        ModelAndView mv = new ModelAndView("index");
        System.out.println(username);
        System.out.println(password);

        return mv;

    }
}
