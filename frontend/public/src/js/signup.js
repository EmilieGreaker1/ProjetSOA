/*
async function getBaseUrl() {

    try {
        // Fetch dynamic port from Config Server
       //  const response = await fetch("http://localhost:8888/config/server-port");
      //  const response = await fetch("http://localhost:8888/client-service/dev");
        if (response.ok) {
            const data = await response.json(); // Parse JSON response
            console.log("Full Config Data:", data); // Log full response for debugging

            const port = data.propertySources[0].source["server.port"]; // Extract server.port
            console.log(`Recovered port from Config Server: ${port}`); // Imprimir el puerto en consola
            return `http://localhost:${port}`; // Construct base URL
        } else {
            console.error("Error fetching server port:", response.status);
            return "http://localhost:8088"; // Default fallback value
        }
    } catch (error) {
        console.error("Error fetching server port:", error);
        return "http://localhost:8088"; // Default fallback value
    }
}
*/

document.getElementById("signupForm").addEventListener("submit", async function (event) {
    event.preventDefault();

    const formData = {
        firstName: document.getElementById("firstName").value,
        lastName: document.getElementById("lastName").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value,
        userType: document.getElementById("userType").value
    };

    try {
        // Get the dynamic base URL
        // const baseUrl = await getBaseUrl();

        // Send POST request with the retrieved URL
        //const response = await fetch(`${baseUrl}/signup`, {
         const response = await fetch(`http://localhost:8095/orc/signup`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(formData)
        });

        if (response.ok) {
            // alert("User registered successfully!");

            // Login this newly created user to redirect them
            const response = await fetch(`http://localhost:8095/orc/` + formData.email + "+" + formData.password, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            });

            const jsonUserResp = await response.json();
            localStorage.setItem('user', jsonUserResp);

            if(document.getElementById("userType").value === "Admin") {
                //alert("Redirecting admin...");
                window.location.href = "../../views/AdmViewRequests.html";
            }
            else if(document.getElementById("userType").value === "Volunteer") {
                // alert("Redirecting volunteer...");
                window.location.href = "../../views/VolMyMissions.html"; // Redirect to my Missions page
            }
            else {
                //alert("Redirecting other - Person in need...");
                window.location.href = "../../views/UsrHelpRequest.html";
            }

        } else {
            const error = await response.json();
            console.error("Error:", error);
            alert("Please try again. Problem connecting with the server");
           //  alert(`Error: ${error.message}`);
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Something went wrong. Please try again.");
    }
});

