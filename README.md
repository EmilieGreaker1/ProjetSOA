# **Project Setup Guide**

This README explains how to set up and run both the frontend and backend of the project. The instructions include options for running the microservices and the frontend application.

---

# **Backend: Running Microservices**

## **Prerequisites**
>- **Java JDK**: Version 17 or later.
>> **MacOs**
>> ```
>> brew install openjdk@17
>> echo 'export PATH="/usr/local/opt/openjdk@17/bin:$PATH"' >> ~/.zshrc
>> source ~/.zshrc
>> java -version
>> ```
>- **Maven**: Installed and added to your `PATH`.

## **Steps**

## 1. **Navigate to the microservice folder:**
> ```
> cd <microservice-folder-name>
> ```
## 2. **Run the microservice using one of the two options:**
   
#### **Option 1: Using mvn spring-boot:run (Development)**
>#### •	Run the following command:
> ```
> mvn spring-boot:run
> ```

>#### Advantages:
>##### • Automatically rebuilds the code upon changes.
>##### • Suitable for development environments.

#### **Option 2: Using java -jar (Production or Pre-built)**
>#### •	Build the application:
> ```
> mvn package
> ```
> 
>#### •	Navigate to the target directory:
> ```
> cd target
> ```
> 
>#### •	Run the JAR file:
> ```
> java -jar <microservice-name>.jar
> ```
	
>#### Advantages:
>##### • Faster execution as it uses the pre-packaged application.
>##### • Maven is not required at runtime.

# **Frontend: Running the Application**

## **Prerequisites**
>- **Node.js and npm**: Version 17 or later.
>- Verify installation by checking their versions:
> ```
> node -v
> npm -v
> ```

## **Steps**

## 1. **Navigate to the frontend folder:**
> ```
> cd frontend
> ```

## 2. **Run the frontend using one of the two options:**
#### **Option 1: Using http-server Globally Installed**

>#### •	Install http-server globally (if not already installed):
> ```
> npm install -g http-server
> ```
> 
>#### •	Start the server:
> ```
> http-server -p 9090
> ```
	
>#### Advantages:
>##### • Quick to set up once globally installed.

#### **Option 2: Using npx http-server (Local or On-the-Fly)**
>#### •	Run the following command:
> ```
> npx http-server -p 9090
> ```
	
>#### Advantages:
>##### • No global installation required.
>##### • Ensures you use a specific version of http-server.

## 3. **Access the frontend in your browser:**
Open: **http://localhost:9090**


>## **Additional Notes**
>
> ## Dynamic Port Handling for Backend:
>> Backend microservices may run on dynamic ports determined by the configuration server.
>> Ensure the frontend fetches the correct backend port dynamically.
> ## Frontend Port:
>> The frontend defaults to http://localhost:9090. If this port is busy, update the port in your http-server command.
> ## Cache Issues:
>> If the frontend doesn’t reflect changes:
>> Perform a “hard refresh” in your browser:
>> ```
>> Windows/Linux: Ctrl + Shift + R
>> Mac: Cmd + Shift + R
>> ```
>> Alternatively, clear your browser cache.



