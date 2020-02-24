package org.nerdcore.customerservicev2.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* This class filters access to any RequestMapping with certain URL chains. It checks to see if the client has logged in
   with appropriate login credentials, and redirects the client accordingly.
* */
@WebFilter("/site/*")
public class LoginFilter implements Filter {
    //Required method for a Filter interface. No special init code needs to be executed, so the method is empty
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    //checks if a user has been assigned to the current session. If not, redirects to the login view. Otherwise, allows
    //the client to navigate as desired.
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if(request.getSession().getAttribute("username") == null){
            response.sendRedirect("/login");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    //Required method for a Filter interface. No special destroy code needs to be executed, so the method is empty
    @Override
    public void destroy() {

    }
}
