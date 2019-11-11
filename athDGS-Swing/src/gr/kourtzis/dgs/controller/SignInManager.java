/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.kourtzis.dgs.controller;

import gr.kourtzis.dgs.ejb.UserAdministrationBeanRemote;
import gr.kourtzis.dgs.entity.User;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Athanasios Kourtzis
 */
@Getter
@Setter

public class SignInManager {
    private User user;
    
    public SignInManager(final String email) {
        readUserFromDb(email);
    }
        
    /**
     * The method searches a user from the database with a specified
     * email address and returns that user.
     * @param email A String variable containing an email address.
     * @return A user if one exist or null.
     */
    public User readUserFromDb(final String email) {
        user = lookupUserAdminitrationBeanRemote().readUser(email);
        return user;
    }
    
    /**
     * The method checks if the member variables of the user object is
     * matching with the username and password variables passed as parameters.
     * @param username A String variable containing the username.
     * @param password A String variable containing the password.
     * @return true or false.
     */
    public boolean credentialMatched(final String username, final String password) {
        if(user != null)
            return user.getPassword().equals(password) && user.getEmail().equals(username);
        
        return false;
    }
    
    private UserAdministrationBeanRemote lookupUserAdminitrationBeanRemote()  {
        UserAdministrationBeanRemote userRemote = null;
        
        try {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
            props.setProperty(Context.URL_PKG_PREFIXES, "com.sun.enterprise.naming");
            props.setProperty(Context.STATE_FACTORIES, "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
            props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
            props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");

            Context context = new InitialContext(props);
            Socket socket  = new Socket("localhost", 3700);
            userRemote = (UserAdministrationBeanRemote) context.lookup("ejb/userAdministration");
        }
        catch(NamingException | IOException ex) {
            throw new RuntimeException("Exception occured:" + ex.getMessage(), ex);
        }
        
        return userRemote;
    }
}
