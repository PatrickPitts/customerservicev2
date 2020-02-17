package org.nerdcore.customerservicev2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
