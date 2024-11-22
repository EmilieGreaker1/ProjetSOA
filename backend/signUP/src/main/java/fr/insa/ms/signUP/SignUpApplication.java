package fr.insa.ms.signUP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SignUpApplication {

	public static void main(String[] args) {
		SpringApplication.run(SignUpApplication.class, args);

		System.out.println("Hello world!");
		// JFrame frame = new frameGUI();
	/*	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {


					SignUP signupFrame = new SignUP();
					signupFrame.setVisible(true);


				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}); */
	}

}