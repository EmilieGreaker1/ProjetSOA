Project README

Frontend and Backend Setup Instructions

This guide explains how to set up and run both the frontend and backend for the project. Each has two execution options based on your development preferences.

Backend: Running Microservices

Prerequisites

	•	Ensure you have Java JDK 17 or later installed.
	•	Ensure Maven is installed and added to your PATH.

Steps

	1.	Navigate to the microservice folder:

cd <microservice-folder-name>


	2.	Run the microservice using one of the two options:
Option 1: Using mvn spring-boot:run (Development)

	•	Run the command:

mvn clean install
mvn spring-boot:run

	•	Pros:
	•	Automatically rebuilds the code on changes.
	•	Suitable for development.
 
Option 2: Using java -jar (Production or Pre-built)

	•	Build the application:

mvn package


	•	Navigate to the target directory:

cd target


	•	Run the JAR file:

java -jar <microservice-name>.jar


	•	Pros:
	•	Faster as it uses the pre-packaged application.
	•	Maven is not required at runtime.

Frontend: Running the Application

Prerequisites

	•	Ensure Node.js and npm are installed. Check the versions:

node -v
npm -v



Steps

	1.	Navigate to the frontend folder:

cd frontend


	2.	Run the frontend using one of the two options:
Option 1: Using http-server Globally Installed

	•	Install http-server globally if not already installed:

npm install -g http-server


	•	Start the server:

http-server -p 9090


	•	Pros:
	•	Quick to set up once globally installed.
Option 2: Using npx http-server (Local or On-the-Fly)

	•	Run the following command:

npx http-server -p 9090


	•	Pros:
	•	No global installation required.
	•	Ensures the use of a specific version of http-server.

	3.	Access the frontend in your browser:
	•	Open: http://localhost:9090

Project Notes

	•	Port Configuration:
	•	The backend microservices may run on dynamic ports depending on the configuration server.
	•	The frontend may use http://localhost:9090 or another specified port.
	•	Cache Issues:
	•	If the frontend fails to load after updates, clear your browser cache or use a “hard refresh” (Ctrl + Shift + R or Cmd + Shift + R).

With these instructions, you can seamlessly set up and run both the frontend and backend of the project!
