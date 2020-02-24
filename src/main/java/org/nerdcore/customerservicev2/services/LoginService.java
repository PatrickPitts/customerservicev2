package org.nerdcore.customerservicev2.services;

import org.nerdcore.customerservicev2.models.UserModel;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
/* This service is used to manage client log in processes
* */
@Service
public class LoginService {

    //Returns true if the client provided login credentials are stored in the users.xml data store. Otherwise returns false.
    public static boolean processLoginCredentials(HttpServletRequest request) {
        ArrayList<UserModel> users = XMLProcessorService.getUsers();
        UserModel userToVerify = new UserModel(request.getParameter("username"),
                request.getParameter("password"));
        for (UserModel user : users) {
            if (user.getPassword().equals(userToVerify.getPassword()) && user.getUsername().equals(userToVerify.getUsername())) {
                return true;
            }
        }
        return false;
    }

    //Returns false if the client provided credentials already exist in the users.xml data store. Otherwise, adds
    //those credentials to the data store, and returns true.
    public static boolean addNewCredentials(HttpServletRequest request) {
        ArrayList<UserModel> registeredUsers = XMLProcessorService.getUsers();
        UserModel userToAdd = new UserModel(request.getParameter("username"),
                request.getParameter("password"));
        for (UserModel user : registeredUsers) {
            if (user.getUsername().equals(userToAdd.getUsername())) {
                return false;
            }
        }
        XMLProcessorService.writeNewLoginCredentials(userToAdd);
        return true;
    }
}
