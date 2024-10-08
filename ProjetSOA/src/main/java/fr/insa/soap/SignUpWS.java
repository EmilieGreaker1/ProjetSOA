package fr.insa.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName="SignUp")
public class SignUpWS {
    @WebMethod(operationName="newUser")
    public void newUser(@WebParam(name="firstName") String firstName, @WebParam(name="lastName") String lastName, @WebParam(name="email") String email, @WebParam(name="userType") String userType, @WebParam(name="password") String password) {
        User thisUser = new User(firstName, lastName, email, userType, password);

        thisUser.setFirstName(firstName);
        thisUser.setLastName(lastName);
        thisUser.setEmail(email);
        thisUser.setUserType(userType);
        thisUser.setPassword(password);
     /*
        thisUser.setFirstName("Cristian");
        thisUser.setLastName("Martinez");
        thisUser.setEmail("crimaco197@gmail.com");
        thisUser.setUserType("helper");
        thisUser.setPassword("$iT$ktdm77$t8HM&");
*/
        System.out.println("User created: " + email);
    }

}