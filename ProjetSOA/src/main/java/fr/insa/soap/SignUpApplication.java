package fr.insa.soap;

import java.net.MalformedURLException;
import javax.xml.ws.Endpoint;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class SignUpApplication {
    public static String host="localhost";
    public static short port=8088;

    public void startService() {
        String url="http://"+host+":"+port+"/";
        Endpoint.publish(url, new SignUpWS());
    }

    public static void main(String[] args) throws MalformedURLException {
        new SignUpApplication().startService();
        System.out.println("Service SignUp started");

  /*      try {
            URL wsdlURL = new URL("http://localhost:8088/SignUp?wsdl");
            QName qname = new QName("http://soap.insa.fr/", "SignUpWSService");
            Service service = Service.create(wsdlURL, qname);
            SignUpWS signUpWS = service.getPort(SignUpWS.class);

            // Llamada al método newUser a través del cliente SOAP
            signUpWS.newUser("Cristian", "Martinez", "crimaco197@gmail.com", "helper", "$iT$ktdm77$t8HM&");
            System.out.println("User created via SOAP.");
        } catch (Exception e) {
            e.printStackTrace();
        } */
    }
}