package org.nerdcore.customerservicev2.models;

import java.util.Date;

public class FlightModel {

    private String departureCity;
    private String departureState;
    private String departureAirportCode;
    private String departureDate;

    private String arrivalCity;
    private String arrivalState;
    private String arrivalAirportCode;
    private String arrivalDate;

    private int numFirstClass;
    private int numBusinessClass;

    public FlightModel(String departureCity, String departureState, String departureAirportCode, String departureDate, String arrivalCity, String arrivalState, String arrivalAirportCode, String arrivalDate, int numFirstClass, int numBusinessClass) {
        this.departureCity = departureCity;
        this.departureState = departureState;
        this.departureAirportCode = departureAirportCode;
        this.departureDate = departureDate;
        this.arrivalCity = arrivalCity;
        this.arrivalState = arrivalState;
        this.arrivalAirportCode = arrivalAirportCode;
        this.arrivalDate = arrivalDate;
        this.numFirstClass = numFirstClass;
        this.numBusinessClass = numBusinessClass;
    }

    public int getNumFirstClass() {
        return numFirstClass;
    }

    public void setNumFirstClass(int numFirstClass) {
        this.numFirstClass = numFirstClass;
    }

    public int getNumBusinessClass() {
        return numBusinessClass;
    }

    public void setNumBusinessClass(int numBusinessClass) {
        this.numBusinessClass = numBusinessClass;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDepartureState() {
        return departureState;
    }

    public void setDepartureState(String departureState) {
        this.departureState = departureState;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getArrivalState() {
        return arrivalState;
    }

    public void setArrivalState(String arrivalState) {
        this.arrivalState = arrivalState;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}
