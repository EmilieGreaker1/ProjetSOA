document.getElementById("loginForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    const formData = {
        email: document.getElementById("email").value,
        password: document.getElementById("password").value,
    };

    if (!formData.email || !formData.password) {
        alert("All fields must be filled out.");
        return;
    }

    try {
        // Call your backend login endpoint
        const response = await fetch("http://localhost:8088/users/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(formData),
        });

        if (response.ok) {
            alert("Login successful!");
            // Redirect to a different page if needed
            window.location.href = "/dashboard.html";
        } else {
            const error = await response.json();
            alert(`Error: ${error.message || "Incorrect email or password"}`);
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Something went wrong. Please try again later.");
    }
});