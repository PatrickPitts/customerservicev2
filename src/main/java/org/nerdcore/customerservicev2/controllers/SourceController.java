package org.nerdcore.customerservicev2.controllers;

import org.nerdcore.customerservicev2.models.FlightModel;
import org.nerdcore.customerservicev2.services.XMLProcessorService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

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

    @GetMapping("/new-flight")
    public ModelAndView newFlightView(){
        ModelAndView mv = new ModelAndView("new-flight");
        return mv;
    }

    @PostMapping("/new-flight")
    public ModelAndView postFlightData(@ModelAttribute("departureCity") String departureCity,
                                       @ModelAttribute("departureState") String departureState,
                                       @ModelAttribute("departureAirportCode") String departureAirportCode,
                                       @ModelAttribute("departureDate")String departureDate,
                                       @ModelAttribute("arrivalCity") String arrivalCity,
                                       @ModelAttribute("arrivalState") String arrivalState,
                                       @ModelAttribute("arrivalAirportCode") String arrivalAirportCode,
                                       @ModelAttribute("arrivalDate") String arrivalDate,
                                       @ModelAttribute("numFirstClass") int numFirstClass,
                                       @ModelAttribute("numBusinessClass") int numBusinessClass){

        XMLProcessorService.writeNewFlight(new FlightModel(departureCity, departureState, departureAirportCode, departureDate, arrivalCity, arrivalState, arrivalAirportCode, arrivalDate, numFirstClass, numBusinessClass));

        return new ModelAndView("index");
    }


}
