package fr.insa.soap;

import java.net.MalformedURLException;
import javax.xml.ws.Endpoint;

public class AnalyserChaineApplication {
    public static String host="localhost";
    public static short port=8088;

    public void startService() {
        String url="http://"+host+":"+port+"/";
        Endpoint.publish(url, new AnalyserChaineWS());
    }

    public static void main(String[] args) throws MalformedURLException {
        new SignUpApplication().startService();
        System.out.println("Service Analyser started");
    }
}