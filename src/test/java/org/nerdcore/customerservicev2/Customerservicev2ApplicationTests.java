package org.nerdcore.customerservicev2;

import org.junit.jupiter.api.Test;
import org.nerdcore.customerservicev2.models.FlightModel;
import org.nerdcore.customerservicev2.models.UserModel;
import org.nerdcore.customerservicev2.services.LoginService;
import org.nerdcore.customerservicev2.services.XMLProcessorService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Customerservicev2ApplicationTests {

    @Test
    void loadUsers() {
        assertEquals(new UserModel("username", "password"),
        XMLProcessorService.getUsers().get(0), "UserModel should have same username and password fields");
    }

    @Test
    void loadFlights(){
        assertEquals(new FlightModel("Seattle", "WA", "SEA",
                "2020-02-26T11:00", "Los Angeles", "California", "LAX",
                "2020-02-26T15:00", 10,20),
                XMLProcessorService.getFlights().get(0), "All fields should be matching terms.");
    }

    @Test
    void testCredentialVerification(){
        MockHttpServletRequest testRequest = new MockHttpServletRequest();
        testRequest.addParameter("username", "username");
        testRequest.addParameter("password", "password");
        assertTrue(LoginService.processLoginCredentials(testRequest));
    }

    @Test
    void testWriteNewCredentialsFail(){
        MockHttpServletRequest testRequest = new MockHttpServletRequest();
        testRequest.addParameter("username", "username");
        testRequest.addParameter("password", "password");
        assertFalse(LoginService.addNewCredentials(testRequest));
    }

}
