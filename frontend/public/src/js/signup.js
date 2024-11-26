async function getBaseUrl() {
    try {
        // Fetch dynamic port from Config Server
        const response = await fetch("http://localhost:8888/config/server-port");
        if (response.ok) {
            const port = await response.text(); // Get port as text
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
        const baseUrl = await getBaseUrl();

        // Send POST request with the retrieved URL
        const response = await fetch(`${baseUrl}/users/sign-up`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(formData)
        });

        if (response.ok) {
            alert("User registered successfully!");
            window.location.href = "/login"; // Redirect to login page
        } else {
            const error = await response.json();
            alert(`Error: ${error.message}`);
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Something went wrong. Please try again.");
    }
});

/*
* TODO
*  MIRAR COMO QUITAR EL CORS POLICY PARA PODER ENVIAR PETICITONES DESDE EL FRONT
*  - LINEA 4 NO ESTÁ ACCEDIENDO AL SERVIDOR
* */