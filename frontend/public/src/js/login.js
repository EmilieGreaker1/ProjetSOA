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
            alert("Login successful!");
            console.log(await response.json());

            const jsonLoginResp = await response.json();

            if(jsonLoginResp.userId !== 0) {
                alert("Login successful!");

                if(jsonLoginResp.userType === "admin") {
                    alert("Redirecting admin...");
                }
                else if(jsonLoginResp.userType === "Volunteer") {
                    alert("Redirecting volunteer...");
                }
                else {
                    alert("Redirecting other...");
                }

                // TODO Redirect to next page
                //window.location.href = "../views/...";  
            }
            else {
                alert("User does not exist, please try again");
            }
        } else {
            const error = await response.json();
            alert(`Error: ${error.message}`);
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Something went wrong. Please try again.");
    }
});