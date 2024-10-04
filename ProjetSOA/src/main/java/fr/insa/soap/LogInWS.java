package fr.insa.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName="logIn")
public class LogInWS {
	@WebMethod(operationName="getUser")
	public User getUser(@WebParam(name="userId") String userId, @WebParam(name="password") String password) {
		User thisUser = new User(userId, password);
		
		thisUser.setLastName("testLN");
		thisUser.setFirstName("testFN");
		thisUser.setUserType("testUT");
		
		return thisUser;
	}

}
