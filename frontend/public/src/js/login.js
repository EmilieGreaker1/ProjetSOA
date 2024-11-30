document.getElementById("loginForm").addEventListener("submit", async function (event) {
    event.preventDefault();

    const formData = {
        userid: document.getElementById("userid").value,
        password: document.getElementById("password").value
    };

    try {
        // Send GET request with the retrieved URL
        const response = await fetch(`http://localhost:8090/login/` + formData.userid + "+" + formData.password, {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        });

        if (response.ok) {
            const jsonLoginResp = await response.json();
            console.log(jsonLoginResp);

            // Usa el userType del servidor para decidir la redirección
            const userType = jsonLoginResp.userType; // Asegúrate de que esta propiedad exista en la respuesta

            if (jsonLoginResp.userId !== 0) {
                if (userType === "Admin") {
                    window.location.href = "../../views/AdmViewRequests.html";
                } else if (userType === "Volunteer") {
                    window.location.href = "../../views/VolMyMissions.html";
                } else {
                    window.location.href = "../../views/UsrHelpRequest.html";
                }
            } else {
                alert("User does not exist, please try again");
            }

        } else {
            const error = await response.json();
            alert(`Error: ${error.message || "Incorrect email or password"}`);
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Something went wrong. Please try again.");
    }
});